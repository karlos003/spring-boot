package org.ltq.service;

import java.math.BigInteger;
import java.util.List;

import org.ltq.entity.Post;

public interface PostService {
	int addPost(Post post);
	void addOneLikeNumById(int post_id);
	List<BigInteger> deletePostByPID(int post_id);
	List<Post> queryPostInfoByTypeOrdByTime(String type,int currentPage,int pageSize);
	Post queryPostByPostID(int post_id);
	List<Post> queryPostInfoByAcc(String user_account,int currentPage,int pageSize);
	List<Object> queryNewPost();
	int queryPostCountByPType(String post_type);
}
