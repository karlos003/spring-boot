package org.ltq.dao;

import java.util.List;

import org.ltq.entity.Post;
import org.springframework.stereotype.Repository;

@Repository
public interface PostMapper {
	int addPost(Post post);
	void addPostViewNumByPid(int post_id);
	void addPostLikeNumByPid(int post_id);
	int deletePostByPID(int post_id);
	List<Post> queryPostInfoByTypeOrdByTime(String post_type);
	List<Post> queryPostInfoByAccOrdByTime(String user_account);
	List<Post> queryPostInfoByPIDList(List<Integer> post_id);
	Post queryPostByPostID(int post_id);
	int queryPostCountByPType(String post_type);
	int queryPostCountByUAcc(String user_account);
}
