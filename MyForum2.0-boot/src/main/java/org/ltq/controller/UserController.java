package org.ltq.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ltq.entity.Post;
import org.ltq.entity.User;
import org.ltq.service.PostService;
import org.ltq.service.UserService;
import org.ltq.utils.ValidsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@RequestMapping("userController")
@Controller
public class UserController {
	
	@Resource
	UserService userService;
	
	@Resource
	PostService postService;
	
	private ValidsUtil validUtil = new ValidsUtil();
	
	@RequestMapping(value="signUpControl")
	public String signUpControl(User user,HttpSession session,HttpServletResponse response,Map<String,Object> map) {
		int validResult = validUtil.signUpValid(user);
		
		//注册验证
		if(validResult != 0) {
			map.put("resultType", validResult);
			return "requestResult";
		}
		
		int result = userService.addUser(user);
		if(result == 1) {	
			//注册成功
			session.setAttribute("user", user);
			Cookie accountCookie = new Cookie("account",user.getUser_account());
			Cookie pwdCookie = new Cookie("pwd",user.getUser_pwd());
			accountCookie.setMaxAge(604800);
			accountCookie.setPath("/");
			pwdCookie.setMaxAge(604800);
			pwdCookie.setPath("/");
			response.addCookie(accountCookie);
			response.addCookie(pwdCookie);
			map.put("resultType", 2);
			return "requestResult";
		}else if(result == -1){
			//存在相同账号
			map.put("resultType", 3);
			return "requestResult";
		}else{
			//存在相同昵称
			map.put("resultType", 27);
			return "requestResult";
		}
	}
	
	@RequestMapping(value="loginControl")
	public String loginControl(User user,HttpSession session,HttpServletResponse response,Map<String,Object> map) {
		if(user.getUser_account().trim().length()==0 || user.getUser_pwd().trim().length() == 0) {
			map.put("resultType", 26);
			return "requestResult";
		}
		User result = userService.queryUserByAccount(user.getUser_account());
		if(result!=null) {
			if(result.getUser_pwd().equals(user.getUser_pwd())) {
				session.setAttribute("user", result);
				Cookie accountCookie = new Cookie("account",user.getUser_account());
				Cookie pwdCookie = new Cookie("pwd",user.getUser_pwd());
				accountCookie.setMaxAge(604800);
				accountCookie.setPath("/");
				pwdCookie.setMaxAge(604800);
				pwdCookie.setPath("/");
				response.addCookie(accountCookie);
				response.addCookie(pwdCookie);
				map.put("resultType", 1);
				return "requestResult";
			}
			map.put("resultType", 10);
			return "requestResult";
		}
		map.put("resultType", 10);
		return "requestResult";
	}
	
	@RequestMapping("logOffControl")
	public String logOffControl(HttpSession session,Map<String,Object> map) {
		session.removeAttribute("user");
		map.put("resultType", 19);
		return "requestResult";		
	}
	
	@RequestMapping("updateUserPhotoControl")
	public String updateUserPhotoControl(@RequestParam(value="user_photo")MultipartFile img  ,HttpSession session,Map<String,Object> map) {
		try {
			int validResult = validUtil.imgValid(img);
			//判断图片是否为空
			if(validResult == -1) {
				map.put("resultType", 16);
				return "requestResult";				
			}else if(img.getSize()>2097152) {
				map.put("resultType", 25);
				return "requestResult";			
			}
			//判断图片类型为jpg jpeg png
			if(validResult>0) {
				
				//获取当前用户
				User user =(User)session.getAttribute("user");
				BufferedInputStream in = new BufferedInputStream(img.getInputStream());
				
				//存储在服务器中的目录
				String dirPath = "D:\\MyForumUploadDir\\user_photo";
				File file = new File(dirPath);
				
				//判断有无目录，无则创建
				if(!file.exists()) {
					file.mkdirs();
				}
				
				//IO流操作
				//将输出文件的文件名修改为[account.jpg]
				File imgOut = new File(dirPath+"\\"+user.getUser_account()+".jpg");
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(imgOut));
				int len = -1;
				byte[] flush = new byte[1024];
				while((len=in.read(flush))!=-1) {
					out.write(flush, 0, len);
				}
				out.flush();
				//关闭流
				in.close();
				out.close();
				User tempUser = new User(user.getUser_account(),1);
				userService.updateUserPhotoByAccount(tempUser);
				user.setUser_photo(1);
				
				//更新session中的user.photo信息
				session.setAttribute("user", user);
				map.put("resultType", 11);
				return "requestResult";
			}else {
				map.put("resultType", 12);
				return "requestResult";
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/userInfo.jsp";
	}
	
	@RequestMapping(value="changePasswordControl")
	public String changePasswordControl(@RequestParam("pwd")String newPwd,@RequestParam("currentPwd")String currentPwd ,HttpSession session,Map<String,Object> map) {
		if(newPwd.trim().length()==0||currentPwd.trim().length()==0||newPwd.trim().length()==0) {
			map.put("resultType", 17);
			return "requestResult";
		}
		if(validUtil.pwdValid(newPwd) == false) {
			map.put("resultType", 37);
			return "requestResult";
		}
		User user = (User)session.getAttribute("user");
		String systemPwd = userService.queryUserPwdByAccount(user.getUser_account());
		if(!systemPwd.equals(currentPwd)) {
			map.put("resultType", 13);
			return "requestResult";
		}else{
			User tempUser = new User(user.getUser_account(),newPwd);
			userService.updateUserPwdByAccount(tempUser);
			user.setUser_pwd(newPwd);
			session.setAttribute("user", user);
			map.put("resultType", 14);
			return "requestResult";
		}
	}
	
	@RequestMapping("addSubsPost")
	public String addSubsPost(@RequestParam("post_id")BigInteger post_id,HttpSession session,Map<String,Object> map) {
		Date currentTime = new Date(System.currentTimeMillis());
		String user_account = ((User) session.getAttribute("user")).getUser_account();
		User temp = new User(user_account,post_id,currentTime);
		boolean result = userService.addOrRemoveUserSubsPost(temp);
		if(result) {
			map.put("resultType", 33);
			map.put("post_id", post_id);
			return "requestResult";
		}else {
			map.put("resultType", 34);
			map.put("post_id", post_id);
			return "requestResult";
		}

	}
	
	@RequestMapping("showSubsPost/{user_account}/{currentPage}")
	public String showSubsPost(@PathVariable("user_account")String user_account ,@PathVariable("currentPage")int currentPage,HttpSession session,Map<String,Object> map) {
		List<Post> posts = userService.queryUserSubPost(user_account,currentPage,10);
		if(posts == null) {
			map.put("user_account", user_account);
			map.put("resultType", 41);
			return "requestResult";
		}
		session.setAttribute("user_location", "userSubPosts");
		session.setAttribute("currentPage", currentPage);
		map.put("user_account", user_account);
		map.put("posts", posts);
		return "userSubPosts";
	}

	
	@RequestMapping("queryUserPosts/{user_account}/{currentPage}")
	public String queryUserPosts(@PathVariable("user_account")String user_account,@PathVariable("currentPage")Integer currentPage ,HttpSession session,Map<String,Object> map) {
		List<Post> posts = postService.queryPostInfoByAcc(user_account, currentPage, 15);
		if(posts == null) {
			map.put("user_account", user_account);
			map.put("resultType", 43);
			return "requestResult";
		}
		session.setAttribute("currentPage", currentPage);
		session.setAttribute("user_location", "userPosts");
		map.put("user_account", user_account);
		map.put("posts", posts);
		return "userPosts";
	}
	
	@RequestMapping("addOrRmSub/{user_account}/{sub_account}")
	public String addOrRmSub(@PathVariable("user_account")String user_account,@PathVariable("sub_account")String sub_account,Map<String,Object> map) {
		User user = new User();
		Date usub_time = new Date(System.currentTimeMillis());
		user.setUser_account(user_account);
		user.setSub_account(sub_account);
		user.setUsub_time(usub_time);
		boolean result = userService.addOrRemoveUserSub(user);
		if(result) {
			map.put("sub_account", sub_account);
			map.put("user_account", user_account);
			map.put("resultType", 38);
			return "requestResult";
		}else {
			map.put("sub_account", sub_account);
			map.put("user_account", user_account);
			map.put("resultType", 39);
			return "requestResult";
		}
	}
	
}
