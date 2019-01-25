package org.ltq.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.ltq.entity.Comment;
import org.ltq.entity.User;
import org.ltq.service.CommentService;
import org.ltq.service.PostService;
import org.ltq.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {
	@Resource
	UserService userService;
	@Resource
	PostService postService;
	@Resource
	CommentService commentService;
	@RequestMapping("index")
	public String index(Map<String,Object> map) {
		List<Object> newPost = postService.queryNewPost();
		Collections.reverse(newPost);
		map.put("posts", newPost);
		return "main";
	}
	
	@RequestMapping("msgBoard")
	public String msgBoard(@RequestParam("currentPage")Integer currentPage ,HttpSession session,Map<String,Object> map) {
		List<Comment> comments = commentService.queryAllComments(currentPage,7);
		int totalNum = commentService.queryCommentCountByPID(0);
		if(comments.size() == 0) {
			totalNum++;
		}
		int pageSize = totalNum/7;
		if(totalNum%7 > 0) {
			pageSize++;
		}
		if(currentPage > pageSize) {
			map.put("resultType", 44);
			return "requestResult";
		}
		session.setAttribute("currentPage", currentPage);
		if(comments.size() > 0) {
			map.put("comments", comments);
		}
		return "msgBoard";
	}
	
	@RequestMapping("uprofile/{user_account}")
	public String uprofile(@PathVariable("user_account")String user_account,HttpSession session,Map<String,Object> map) {
		User user = userService.queryUserInfo(user_account);
		session.setAttribute("user_location", "uprofile");
		map.put("usr", user);
		return "uprofile";
	}
	
	@RequestMapping("sub/{user_account}/{currentPage}")
	public String sub(@PathVariable("user_account")String user_account,@PathVariable("currentPage")int currentPage,HttpSession session,Map<String,Object> map) {
		List<User> subs = userService.queryUserSub(user_account,currentPage,15);
		if(subs == null) {
			map.put("user_account", user_account);
			map.put("resultType", 40);
			return "requestResult";
		}
		session.setAttribute("currentPage", currentPage);
		if(session.getAttribute("user_location") == "uprofile" ||session.getAttribute("user_location") == "uprofile/sub") {
			session.setAttribute("user_location", "uprofile/sub");
		}
		session.setAttribute("user_location", "sub");
		map.put("user_account", user_account);
		map.put("subs", subs);
		return "sub";
	}
}
