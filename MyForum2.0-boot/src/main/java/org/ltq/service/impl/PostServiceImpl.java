package org.ltq.service.impl;

import com.github.pagehelper.PageHelper;
import org.ltq.dao.CommentMapper;
import org.ltq.dao.PostMapper;
import org.ltq.entity.Post;
import org.ltq.service.PostService;
import org.ltq.utils.PageBean;
import org.ltq.utils.RedisUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;


@Service("postService")
public class PostServiceImpl implements PostService {
	
	@Resource
	PostMapper postMapper;
	
	@Resource
	CommentMapper commentMapper;
	
	@Resource
	RedisUtil redisUtil;
	
	public void setPostMapper(PostMapper postMapper) {
		this.postMapper = postMapper;
	}
	
	@Override
	public int addPost(Post post) {
		postMapper.addPost(post);
		return post.getPost_id();
	}
	
	@Override
	public List<Post> queryPostInfoByTypeOrdByTime(String type,int currentPage,int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<Post> allItems = postMapper.queryPostInfoByTypeOrdByTime(type);
		int countNums = postMapper.queryPostCountByPType(type);
		PageBean<Post> pageData = new PageBean<Post>(currentPage, pageSize, countNums);
		pageData.setItems(allItems);
		return pageData.getItems();
	}

	@Override
	public Post queryPostByPostID(int post_id) {
		Post post = postMapper.queryPostByPostID(post_id);
		postMapper.addPostViewNumByPid(post_id);
		return post;
	}

	@Override
	public void addOneLikeNumById(int post_id) {
		postMapper.addPostLikeNumByPid(post_id);
	}
	
	@Override
	public List<Post> queryPostInfoByAcc(String user_account, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<Post> allItems = postMapper.queryPostInfoByAccOrdByTime(user_account);
		int countNums = postMapper.queryPostCountByUAcc(user_account);
		PageBean<Post> pageData = new PageBean<Post>(currentPage, pageSize, countNums);
		PageBean<Post> testBean = new PageBean<Post>(currentPage-1, pageSize, countNums);
		if(testBean.getIsMore() == 0) {
			return null;
		}
		pageData.setItems(allItems);
		return pageData.getItems();
	}
	
	@Transactional
	@Override
	public List<BigInteger> deletePostByPID(int post_id) {
		postMapper.deletePostByPID(post_id);
		List<BigInteger> commentidList = commentMapper.queryCIDByPID(post_id);
		if(commentidList.size() > 0) {
			commentMapper.deleteCommentByCommentIdList(commentidList);
		}
		return commentidList;
	}

	@Override
	public List<Object> queryNewPost() {
		List<Object> posts = redisUtil.lGet("newPost", 0, 9);
		return posts;
	}

	@Override
	public int queryPostCountByPType(String post_type) {
		int count = postMapper.queryPostCountByPType(post_type);
		return count;
	}

}
