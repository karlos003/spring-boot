package org.ltq.utils;

import org.ltq.entity.User;
import org.springframework.web.multipart.MultipartFile;

public class ValidsUtil {
	
	public int imgValid(MultipartFile img) {
		String originalFilename = img.getOriginalFilename();
		//判断图片是否为空
		if(originalFilename.trim().length()==0) {
			return -1;				
		}
		String fileSubString = originalFilename.substring(originalFilename.lastIndexOf("."));
		//判断图片类型为jpg jpeg png
		if(!fileSubString.equalsIgnoreCase(".jpg")||fileSubString.equalsIgnoreCase(".jpeg")||fileSubString.equalsIgnoreCase(".png")) {
			return -2;
		}
		//判断图片大小
		if(img.getSize()>4194304) {
			return -3;
		}
		return 1;
	}
	
	public int signUpValid(User user) {
		//验证账号、密码、用户名不为空
		if(user.getUser_name().trim().length()==0||user.getUser_account().trim().length()==0||user.getUser_pwd().trim().length()==0) {
			return 4;
		}
		//验证账号（6-16位字母数字）
		String accPattern = "^[a-zA-Z0-9_-]{6,16}$";
		if(!user.getUser_account().matches(accPattern)) {
			return 23;
		}
		//验证密码（6-20位字母数字特殊符号）
		String pwdPattern = "^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()-_+=|{}':;',\\\\[\\\\].<>/?~]){6,20}$";
		if(!user.getUser_pwd().matches(pwdPattern)) {
			return 22;
		}
		//验证昵称合法性
		if(user.getUser_name().length()>13) {
			return 24;
		}
		return 0;
	}
	
	public boolean pwdValid(String pwd) {
		String pwdPattern = "^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()-_+=|{}':;',\\\\[\\\\].<>/?~]){6,20}$";
		if(pwd.matches(pwdPattern)) {
			return true;
		}else {
			return false;
		}
	}
}
