package org.ltq.service;

import java.util.List;

import org.ltq.entity.Post;
import org.ltq.entity.User;

public interface UserService {
	int addUser(User user);
	boolean addOrRemoveUserSubsPost(User user);
	boolean addOrRemoveUserSub(User user);
	void updateUserPhotoByAccount(User user);
	void updateUserPwdByAccount(User user);
	User queryUserByAccount(String user_account);
	String queryUserPwdByAccount(String account);
	List<Post> queryUserSubPost(String user_account,int currentPage,int pageSize);
	User queryUserInfo(String user_account);
	List<User> queryUserSub(String user_account,int currentPage,int pageSize);
}
