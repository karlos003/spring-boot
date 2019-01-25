package org.ltq.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.ltq.entity.Post;
import org.ltq.entity.User;
import org.ltq.dao.PostMapper;
import org.ltq.dao.UserMapper;
import org.ltq.service.UserService;
import org.ltq.utils.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	UserMapper userMapper;
	
	@Resource
	PostMapper postMapper;
	
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	@Transactional
	@Override
	public int addUser(User user) {
		int userCount = userMapper.queryUserCountByAccount(user.getUser_account());
		int userCount2 = userMapper.queryUserCountByUname(user.getUser_name());
		if(userCount > 0) {
			return -1;
		}else if(userCount2 > 0) {
			return -2 ;
		}else{
			userMapper.addUser(user);
			return 1;
		}
	}

	@Override
	public User queryUserByAccount(String account) {
		User user = userMapper.queryUserByAccount(account);
		return user;
	}
	
	@Transactional
	@Override
	public void updateUserPhotoByAccount(User user) {
		int count = userMapper.queryUserCountByAccount(user.getUser_account());
		if(count==1) {
			userMapper.updateUserPhotoByAccount(user);
		}
	}
	
	@Transactional
	@Override
	public void updateUserPwdByAccount(User user) {
		int count = userMapper.queryUserCountByAccount(user.getUser_account());
		if(count==1) {
			userMapper.updateUserPwdByAccount(user);
		}
	}

	@Override
	public String queryUserPwdByAccount(String account) {
		String pwd = userMapper.queryUserPwdByAccount(account);
		return pwd;
	}
	
	@Transactional
	@Override
	public boolean addOrRemoveUserSubsPost(User user) {
		int resultNum = userMapper.querySubPostCount(user);
		if(resultNum == 0) {
			userMapper.addUserSubsPost(user);
			return true;
		}else {
			userMapper.removeUserSubPost(user);
			return false;
		}
	}

	@Override
	public List<Post> queryUserSubPost(String user_account,int currentPage,int pageSize) {
		List<Integer> post_id_List = userMapper.queryUserSubPostIDByAcc(user_account);
		if(post_id_List.size()>0) {
			PageHelper.startPage(currentPage, pageSize);
			List<Post> allItems = postMapper.queryPostInfoByPIDList(post_id_List);
			int countNums = post_id_List.size();
			PageBean<Post> pageData = new PageBean<Post>(currentPage, pageSize, countNums);
			PageBean<Post> testBean = new PageBean<Post>(currentPage-1, pageSize, countNums);
			if(testBean.getIsMore() == 0) {
				return null;
			}
			pageData.setItems(allItems);
			return pageData.getItems();
		}else {
			return null;
		}
	}

	@Override
	public User queryUserInfo(String user_account) {
		User user = userMapper.queryUserInfoByAccount(user_account);
		return user;
	}
	
	@Transactional
	@Override
	public boolean addOrRemoveUserSub(User user) {
		int resultNum = userMapper.querySubCount(user);
		if(resultNum == 0) {
			userMapper.addUserSub(user);
			return true;
		}else {
			userMapper.removeUserSub(user);
			return false;
		}
	}

	@Override
	public List<User> queryUserSub(String user_account,int currentPage,int pageSize) {
		List<String> subs = userMapper.queryUserSubAccByAcc(user_account);
		if(subs.size()>0) {
			PageHelper.startPage(currentPage, pageSize);
			List<User> allItems = userMapper.queryUserInfoByAccList(subs);
			int countNums = subs.size();
			PageBean<User> pageData = new PageBean<User>(currentPage, pageSize, countNums);
			PageBean<User> testBean = new PageBean<User>(currentPage-1, pageSize, countNums);
			if(testBean.getIsMore() == 0) {
				return null;
			}
 			pageData.setItems(allItems);
			return pageData.getItems();
		}
		return null;
	}





}
