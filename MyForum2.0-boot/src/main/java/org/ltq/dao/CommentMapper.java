package org.ltq.dao;

import java.math.BigInteger;
import java.util.List;
import org.ltq.entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper {
	void addComment(Comment comment);
	int addCommentByPID(Comment comment);
	int deleteCommentByCommentId(BigInteger commentId);
	int deleteCommentByCommentIdList(List<BigInteger> commentidList);
	int queryCommentCountByCommentId(BigInteger commentId);
	List<Comment> queryAllCommentsWithoutPID();
	List<Comment> queryCommentByPID(int post_id);
	int queryCommentCountByPID(int post_id);
	int queryAllCommentCount();
	List<BigInteger> queryCIDByPID(int post_id);

}
