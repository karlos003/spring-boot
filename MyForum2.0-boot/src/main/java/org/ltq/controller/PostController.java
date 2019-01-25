package org.ltq.controller;

import java.io.File;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ltq.entity.Comment;
import org.ltq.entity.Post;
import org.ltq.entity.User;
import org.ltq.service.CommentService;
import org.ltq.service.PostService;
import org.ltq.utils.IOUtil;
import org.ltq.utils.RedisUtil;
import org.ltq.utils.ValidsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/postController")
public class PostController {
	
	@Resource
	PostService postService;
	
	@Resource
	CommentService commentService;
	
	@Resource
	RedisUtil redisUtil ;
	
	ValidsUtil validUtil = new ValidsUtil();
	
	IOUtil ioUtil = new IOUtil();
	
	
	@RequestMapping("/addPostControl")
	public String addPostControl(@RequestParam("post_title")String post_title,@RequestParam("post_content")String post_content,@RequestParam("post_type")String post_type,@RequestParam("post_image")List<MultipartFile> fileList,HttpServletRequest request,HttpSession session,Map<String,Object> map) {
		//判断标题内容是否为空，为空返回失败
		if(post_title.trim().length() == 0) {
			map.put("resultType", 15);
			return "requestResult";
		}
		int post_image = 0;
		//计算图片数量，用于后面存储，并判断文件类型，类型错误返回错误页面
		for(MultipartFile temp:fileList) {
			int validResult = validUtil.imgValid(temp);
			if(validResult == -2) {
				map.put("resultType", 46);
				return "requestResult";
			}else if(validResult == -3) {
				map.put("resultType", 18);
				return "requestResult";
			}else if(validResult > 0) {
				post_image++;
			}
		}
		
		if(post_content.trim().length() == 0 && post_image == 0) {
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
		long postListSize = redisUtil.lGetListSize("newPost");
		System.out.println("listSize:"+postListSize);
		if(postListSize <= 10) {
			synchronized(this) {
				postListSize = redisUtil.lGetListSize("newPost");
				if(postListSize <= 10) {
					redisUtil.lPush("newPost", post);
					System.out.println("List..push..."+post.getPost_id());
					if(postListSize == 10) {
						redisUtil.lPop("newPost");
						System.out.println("List..pop");
					}
				}
			}
		}
		
		//当有文件时
		if(post_image>0) {
			ioUtil.copyPostImage(post_id, fileList);
		}
		map.put("resultType", 9);
		return "requestResult";
	}
	
	@RequestMapping("/queryPostByType")
	public String queryPostByType(@RequestParam("currentPage")Integer currentPage ,Map<String,Object> map,@RequestParam("post_type")String post_type,HttpSession session) {
		List<Post> posts = postService.queryPostInfoByTypeOrdByTime(post_type,currentPage,15);
		int totalNum = postService.queryPostCountByPType(post_type);
		if(posts.size() == 0) {
			totalNum++;
		}
		int pageSize = totalNum/15;
		if(totalNum%15 > 0) {
			pageSize++;
		}
		if( currentPage > pageSize ) {
			map.put("type", post_type);
			map.put("resultType", 42);
			return "requestResult";
		}
		session.setAttribute("currentPage", currentPage);
		session.setAttribute("post_type", post_type);
		session.setAttribute("user_location", "nav");
		map.put("title", post_type);
		map.put("posts", posts);
		return "nav";
	}
	
	@RequestMapping("/queryPostByPostId")
	public String queryPostByPostId(@RequestParam("currentPage")Integer currentPage ,@RequestParam("post_id")int post_id,HttpSession session,Map<String,Object> map) {
		List<Comment> comments = commentService.queryCommentByPID(post_id,currentPage,7);
		int totalNum = commentService.queryCommentCountByPID(post_id);
		if(comments.size() == 0) {
			totalNum++;
		}
		int pageSize = totalNum/7;
		if(totalNum%7 > 0) {
			pageSize++;
		}
		if(currentPage > pageSize) {
			map.put("post_id", post_id);
			map.put("resultType", 45);
			return "requestResult";
		}
		Post post = postService.queryPostByPostID(post_id);
		session.setAttribute("currentPage", currentPage);
		map.put("post", post);
		if(comments.size() > 0) {
			map.put("comments", comments);
		}
		return "postContent";
	}
	
	@RequestMapping("/addLikeNum")
	public String addLikeNum(Map<String,Object> map,@RequestParam("post_id")int post_id) {
		postService.addOneLikeNumById(post_id);
		map.put("post_id", post_id);
		map.put("resultType", 20);
		return "requestResult";
	}

	
	@RequestMapping("/deletePost")
	public String deletePost(@RequestParam("post_id")int post_id,@RequestParam("post_image")int post_image,Map<String,Object> map) {
		List<BigInteger> commentidList = postService.deletePostByPID(post_id);
		for(int i=1;i<=post_image;i++) {
			File file = new File("D:\\MyForumUploadDir\\post_picture\\"+post_id+"_"+i+".jpg");
			file.delete();
		}
		for(BigInteger commentId:commentidList) {
			File file = new File("D:\\MyForumUploadDir\\comment_picture\\"+commentId+".jpg");
			file.delete();
		}
		map.put("resultType", 35);
		return "requestResult";
	}

}
