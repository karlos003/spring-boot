package org.ltq.controller;


import org.ltq.entity.Comment;
import org.ltq.entity.User;
import org.ltq.service.CommentService;
import org.ltq.utils.IOUtil;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/commentController")
public class CommentController {

    @Resource
    CommentService commentService;

    private ValidsUtil validUtil = new ValidsUtil();

    private IOUtil ioUtil = new IOUtil();

    private Utils util = new Utils();

    /**
     *添加评论
     * @param content，评论内容
     * @param comPic，评论图片(数字，表示图片数)
     * @param session，HttpSession
     */
    @RequestMapping("/addCommentControl")
    public String addCommentControl(@RequestParam("content") String content, @RequestParam("comPic") MultipartFile comPic, HttpSession session, Map<String, Object> map) {
        //使用util的空字符串检测方法
        int result1 = util.strNullValid(content);
        int result2 = util.strNullValid(comPic.getOriginalFilename());
        //判断，result为-1，字符串为空，图片和文字都没有时返回失败
        if (result1 == -1 && result2 == -1) {
            map.put("resultType", 6);
            return "requestResult";
        }

        int imgNum = 0;
        //当有图片时，使用validUtil类的imgValid方法检测图片合法性
        if (result2 == 0) {
            //检测方法的输出结果
            int validResult = validUtil.imgValid(comPic);

            if (validResult == -2) {
                //图片类型不对
                map.put("resultType", 8);
                return "requestResult";
            } else if (validResult == -3) {
                //图片过大
                map.put("resultType", 36);
                return "requestResult";
            } else if (validResult > 0) {
                //图片合法，图片数量加1。
                imgNum++;
            }
        }
        //获取存入数据库所需的基本信息
        User user = (User) session.getAttribute("user");
        String uname = user.getUser_name();
        String account = user.getUser_account();
        String currTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        Comment comment = new Comment(account, uname, currTime, content, imgNum);
        BigInteger commentid = commentService.addComment(comment);
        //当有图片时
        if (imgNum > 0) {
            //IO流操作，把图片存入服务器的硬盘
            ioUtil.copyCommentImage(commentid, comPic);
        }
        //返回客户端
        map.put("resultType", 5);
        return "requestResult";
    }

    /**
     * 删除评论
     * @param commentId，评论ID
     */
    @RequestMapping("/deleteCommentControl")
    public String deleteCommentControl(@RequestParam("commentid") BigInteger commentId, Map<String, Object> map) {
        commentService.deleteCommentByCommentId(commentId);
        //删除的文件
        File file = new File("D:\\MyForumUploadDir\\c" + commentId + ".jpg");
        file.delete();
        map.put("resultType", 7);
        return "requestResult";
    }

    /**
     * 删除帖子中的评论
     * @param post_id，帖子ID
     * @param commentId，评论ID
     */
    @RequestMapping("/deleteCommentInPost")
    public String deleteCommentInPost(@RequestParam("post_id") BigInteger post_id, @RequestParam("commentid") BigInteger commentId, Map<String, Object> map) {
        commentService.deleteCommentByCommentId(commentId);
        File file = new File("D:\\MyForumUploadDir\\c" + commentId + ".jpg");
        file.delete();
        map.put("post_id", post_id);
        map.put("resultType", 32);
        return "requestResult";
    }

    /**
     * 添加帖子中的评论
     * @param post_id，帖子ID
     * @param content，评论内容
     * @param comPic，评论图片
     * @param session，HttpSession
     */
    @RequestMapping("/addPostComment")
    public String addPostComment(@RequestParam("post_id") BigInteger post_id, @RequestParam(value = "content") String content, @RequestParam(value = "comPic", required = false) MultipartFile comPic, HttpSession session, Map<String, Object> map) {
        int result1 = util.strNullValid(content);
        int result2 = util.strNullValid(comPic.getOriginalFilename());
        //判断，图片和文字都没有时返回失败
        if (result1 == -1 && result2 == -1) {
            map.put("post_id", post_id);
            map.put("resultType", 29);
            return "requestResult";
        }
        int imgNum = 0;
        //如果有图片，判断图片合法性
        if (result2 == 0) {
            int validResult = validUtil.imgValid(comPic);
            if (validResult == -2) {
                //图片类型不对
                map.put("resultType", 30);
                map.put("post_id", post_id);
                return "requestResult";
            } else if (validResult == -3) {
                //图片过大
                map.put("resultType", 31);
                map.put("post_id", post_id);
                return "requestResult";
            } else if (validResult > 0) {
                imgNum++;
            }
        }
        //获取存入数据库所需要的信息
        Date currTime = new Date(System.currentTimeMillis());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(currTime);
        User user = (User) session.getAttribute("user");
        String uname = user.getUser_name();
        String account = user.getUser_account();
        Comment comment = new Comment(account, uname, time, content, imgNum, post_id);
        BigInteger commentid = commentService.addCommentByPID(comment);
        //如果有图片，把图片用IO流操作存储进服务器的硬盘
        if (imgNum > 0) {
            ioUtil.copyCommentImage(commentid, comPic);
        }
        //返回客户端
        map.put("post_id", post_id);
        map.put("resultType", 28);
        return "requestResult";
    }
}
