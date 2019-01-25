package org.ltq.service;

import java.math.BigInteger;
import java.util.List;
import org.ltq.entity.Comment;

public interface CommentService {
	BigInteger addComment(Comment comment);
	BigInteger addCommentByPID(Comment comment);
	boolean deleteCommentByCommentId(BigInteger commentId);
	List<Comment> queryAllComments(int currentPage,int pageSize);
	List<Comment> queryCommentByPID(int post_id,int currentPage,int pageSize);
	int queryCommentCountByPID(int post_id);
	
}
