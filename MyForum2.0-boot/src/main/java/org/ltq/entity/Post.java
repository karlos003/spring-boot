package org.ltq.entity;

import java.math.BigInteger;
import java.util.List;

public class Post {
	private int post_id;
	private String post_type;
	private String post_title;
	private String post_content;
	private String user_account;
	private String user_name;
	private String post_time;
	private int post_image;
	private int post_likeNum;
	private int post_subscribeNum;
	private int post_viewNum;
	private int post_replyNum;
	private List<BigInteger> comment_id;
	public Post() {
		super();
	}
	
	public Post(String user_account, String post_time) {
		super();
		this.user_account = user_account;
		this.post_time = post_time;
	}

	public Post(int post_id, String post_type, String post_title, String post_content, String user_account,
			String user_name, String post_time, List<BigInteger> comment_id) {
		super();
		this.post_id = post_id;
		this.post_type = post_type;
		this.post_title = post_title;
		this.post_content = post_content;
		this.user_account = user_account;
		this.user_name = user_name;
		this.post_time = post_time;
		this.comment_id = comment_id;
	}
	
	public Post(String post_type, String post_title, String post_content, String user_account, String user_name,
			String post_time, int post_image) {
		super();
		this.post_type = post_type;
		this.post_title = post_title;
		this.post_content = post_content;
		this.user_account = user_account;
		this.user_name = user_name;
		this.post_time = post_time;
		this.post_image = post_image;
	}
	
	public Post(int post_id, String post_type, String post_title, String post_content, String user_account,
			String user_name, String post_time, int image, List<BigInteger> comment_id) {
		super();
		this.post_id = post_id;
		this.post_type = post_type;
		this.post_title = post_title;
		this.post_content = post_content;
		this.user_account = user_account;
		this.user_name = user_name;
		this.post_time = post_time;
		this.post_image = image;
		this.comment_id = comment_id;
	}
	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getPost_type() {
		return post_type;
	}

	public void setPost_type(String post_type) {
		this.post_type = post_type;
	}

	public String getPost_title() {
		return post_title;
	}

	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}

	public String getPost_content() {
		return post_content;
	}

	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}

	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPost_time() {
		return post_time;
	}

	public void setPost_time(String post_time) {
		this.post_time = post_time;
	}
	public int getImage() {
		return post_image;
	}
	public void setImage(int image) {
		this.post_image = image;
	}
	public List<BigInteger> getComment_id() {
		return comment_id;
	}
	public void setComment_id(List<BigInteger> comment_id) {
		this.comment_id = comment_id;
	}

	public int getPost_image() {
		return post_image;
	}

	public void setPost_image(int post_image) {
		this.post_image = post_image;
	}

	public int getPost_likeNum() {
		return post_likeNum;
	}

	public void setPost_likeNum(int post_likeNum) {
		this.post_likeNum = post_likeNum;
	}

	public int getPost_subscribeNum() {
		return post_subscribeNum;
	}

	public void setPost_subscribeNum(int post_subscribeNum) {
		this.post_subscribeNum = post_subscribeNum;
	}

	public int getPost_viewNum() {
		return post_viewNum;
	}

	public void setPost_viewNum(int post_viewNum) {
		this.post_viewNum = post_viewNum;
	}

	public int getPost_replyNum() {
		return post_replyNum;
	}

	public void setPost_replyNum(int post_replyNum) {
		this.post_replyNum = post_replyNum;
	}
	
	
}
