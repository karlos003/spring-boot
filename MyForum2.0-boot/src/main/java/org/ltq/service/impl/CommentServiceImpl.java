package org.ltq.service.impl;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.ltq.entity.Comment;
import org.ltq.dao.CommentMapper;
import org.ltq.service.CommentService;
import org.ltq.utils.PageBean;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	
	@Resource
	CommentMapper commentMapper;
	
	public void setCommentMapper(CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}
	
	@Override
	public BigInteger addComment(Comment comment) {
		commentMapper.addComment(comment);
		return comment.getCommentid();
	}
	
	@Override
	public List<Comment> queryAllComments(int currentPage,int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<Comment> allItems = commentMapper.queryAllCommentsWithoutPID();
		int countNums = commentMapper.queryCommentCountByPID(0);
		PageBean<Comment> pageData = new PageBean<Comment>(currentPage, pageSize, countNums);
		pageData.setItems(allItems);
		return pageData.getItems();
	}
	
	@Override
	public boolean deleteCommentByCommentId(BigInteger commentId) {
		int count = commentMapper.queryCommentCountByCommentId(commentId);
		if(count!=1) {
			return false;
		}else if(count==1) {
			commentMapper.deleteCommentByCommentId(commentId);
			return true;
		}
		return false;
	}
	
	@Override
	public BigInteger addCommentByPID(Comment comment) {
		commentMapper.addCommentByPID(comment);
		return comment.getCommentid();
	}

	@Override
	public List<Comment> queryCommentByPID(int post_id, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<Comment> allItems = commentMapper.queryCommentByPID(post_id);
		int countNums = commentMapper.queryCommentCountByPID(post_id);
		PageBean<Comment> pageData = new PageBean<Comment>(currentPage, pageSize, countNums);
		pageData.setItems(allItems);
		return pageData.getItems();
	}

	@Override
	public int queryCommentCountByPID(int post_id) {
		int count = commentMapper.queryCommentCountByPID(post_id);
		return count;
	}
	
	



}
