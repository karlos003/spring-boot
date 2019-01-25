package org.ltq.dao;

import java.util.List;

import org.ltq.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
	void addUser(User user);
	int addUserSubsPost(User user);
	int addUserSub(User user);
	User queryUserByAccount(String user_account);
	User queryUserInfoByAccount(String user_account);
	int queryUserCountByAccount(String user_account);
	int queryUserCountByUname(String user_account);
	void updateUserPhotoByAccount(User user);
	void updateUserPwdByAccount(User user);
	String queryUserPwdByAccount(String user_account);
	int querySubPostCount(User user);
	int querySubCount(User user);
	List<Integer> queryUserSubPostIDByAcc(String user_account);
	List<String> queryUserSubAccByAcc(String user_account);
	List<User> queryUserInfoByAccList(List<String> user_account);
	void removeUserSubPost(User user);
	void removeUserSub(User user);
}
