package org.ltq.controller;

import org.ltq.entity.Comment;
import org.ltq.entity.Post;
import org.ltq.entity.User;
import org.ltq.service.CommentService;
import org.ltq.service.PostService;
import org.ltq.utils.IOUtil;
import org.ltq.utils.RedisUtil;
import org.ltq.utils.Utils;
import org.ltq.utils.ValidsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/postController")
public class PostController {

    @Resource
    PostService postService;

    @Resource
    CommentService commentService;

    @Resource
    RedisUtil redisUtil;

    private ValidsUtil validUtil = new ValidsUtil();

    private IOUtil ioUtil = new IOUtil();

    private Utils util = new Utils();

    /**
     * 添加帖子
     * @param post_title，标题
     * @param post_content，内容
     * @param post_type，类型
     * @param fileList，图片列表
     * @param session，HttpSession
     */
    @RequestMapping("/addPostControl")
    public String addPostControl(@RequestParam("post_title") String post_title, @RequestParam("post_content") String post_content, @RequestParam("post_type") String post_type, @RequestParam("post_image") List<MultipartFile> fileList,  HttpSession session, Map<String, Object> map) {
        //判断标题内容是否为空，为空返回失败
        if (post_title.trim().length() == 0) {
            map.put("resultType", 15);
            return "requestResult";
        }
        int post_image = 0;
        //计算图片数量，用于后面存储，并判断文件类型，类型错误返回错误页面
        for (MultipartFile temp : fileList) {
            int validResult = validUtil.imgValid(temp);
            if (validResult == -2) {
                map.put("resultType", 46);
                return "requestResult";
            } else if (validResult == -3) {
                map.put("resultType", 18);
                return "requestResult";
            } else if (validResult > 0) {
                post_image++;
            }
        }
        //内容和图片都没有时，返回错误信息
        if (post_content.trim().length() == 0 && post_image == 0) {
            map.put("resultType", 21);
            return "requestResult";
        }

        //获取Post信息以及用户信息，用于存储入数据库
        User user = (User) session.getAttribute("user");
        String user_account = user.getUser_account();
        String user_name = user.getUser_name();
        String post_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        Post post = new Post(post_type, post_title, post_content, user_account, user_name, post_time, post_image);
        int post_id = postService.addPost(post);
        //Redis数据库操作，在newPost 链表中存储最新发布的10篇帖子
        synchronized (this) {
            long postListSize = redisUtil.lGetListSize("newPost");
            if (postListSize <= 10) {
                redisUtil.lPush("newPost", post);
                if (postListSize == 10) {
                    redisUtil.lPop("newPost");
                }
            }
        }

        //当有文件时，IO流操作把图片存入服务器的硬盘
        if (post_image > 0) {
            ioUtil.copyPostImage(post_id, fileList);
        }
        //返回客户端
        map.put("resultType", 9);
        return "requestResult";
    }

    /**
     * 按类型查看帖子（基本信息）
     * @param currentPage，当前页码
     * @param post_type，类型
     * @param session，HttpSession
     */
    @RequestMapping("/queryPostByType")
    public String queryPostByType(@RequestParam("currentPage") Integer currentPage, Map<String, Object> map, @RequestParam("post_type") String post_type, HttpSession session) {
        //按照传入的类型所查询到的当前页码的帖子
        List<Post> posts = postService.queryPostInfoByTypeOrdByTime(post_type, currentPage, 15);
        //查询该类型的所有帖子数量，用于判断页面总数
        int totalNum = postService.queryPostCountByPType(post_type);
        if (posts.size() == 0) {
            totalNum++;
        }
        int pageSize = totalNum / 15;
        if (totalNum % 15 > 0) {
            pageSize++;
        }
        //当前页码大于总页数时，返回失败
        if (currentPage > pageSize) {
            map.put("type", post_type);
            map.put("resultType", 42);
            return "requestResult";
        }
        //用session记录用户当前浏览的页码
        session.setAttribute("currentPage", currentPage);
        //用session记录用户查看的帖子类型
        session.setAttribute("post_type", post_type);
        //用session记录用户的浏览位置
        session.setAttribute("user_location", "nav");
        //返回客户端
        map.put("title", post_type);
        map.put("posts", posts);
        return "nav";
    }

    /**
     * 查看帖子
     * @param currentPage，当前页码
     * @param post_id
     * @param session
     */
    @RequestMapping("/queryPostByPostId")
    public String queryPostByPostId(@RequestParam("currentPage") Integer currentPage, @RequestParam("post_id") int post_id, HttpSession session, Map<String, Object> map) {
        //查询当前页码该帖子的所有评论
        List<Comment> comments = commentService.queryCommentByPID(post_id, currentPage, 7);
        //查询该帖子评论总数，用于验证页码合法性
        int totalNum = commentService.queryCommentCountByPID(post_id);
        if (comments.size() == 0) {
            totalNum++;
        }
        int pageSize = totalNum / 7;
        if (totalNum % 7 > 0) {
            pageSize++;
        }
        //当前页码大于总页数时，返回失败
        if (currentPage > pageSize) {
            map.put("post_id", post_id);
            map.put("resultType", 45);
            return "requestResult";
        }
        //查询帖子的所有信息
        Post post = postService.queryPostByPostID(post_id);
        //用session记录用户当前浏览的页码
        session.setAttribute("currentPage", currentPage);
        //返回客户端
        map.put("post", post);
        if (comments.size() > 0) {
            map.put("comments", comments);
        }
        return "postContent";
    }

    /**
     * 添加赞
     * @param post_id，帖子ID
     */
    @RequestMapping("/addLikeNum")
    public String addLikeNum(Map<String, Object> map, @RequestParam("post_id") int post_id) {
        postService.addOneLikeNumById(post_id);
        map.put("post_id", post_id);
        map.put("resultType", 20);
        return "requestResult";
    }

    /**
     * 删除帖子
     * @param post_id，帖子ID
     * @param post_image，帖子图片
     */
    @RequestMapping("/deletePost")
    public String deletePost(@RequestParam("post_id") int post_id, @RequestParam("post_image") int post_image, Map<String, Object> map) {
        //在数据库中删除该帖子与该帖子的所有评论
        List<BigInteger> commentidList = postService.deletePostByPID(post_id);
        //循环删除该帖子的所有图片
        for (int i = 1; i <= post_image; i++) {
            File file = new File("D:\\MyForumUploadDir\\p" + post_id + "_" + i + ".jpg");
            file.delete();
        }
        //循环删除该帖子的评论的图片
        for (BigInteger commentId : commentidList) {
            File file = new File("D:\\MyForumUploadDir\\c" + commentId + ".jpg");
            file.delete();
        }
        //返回客户端
        map.put("resultType", 35);
        return "requestResult";
    }

}
