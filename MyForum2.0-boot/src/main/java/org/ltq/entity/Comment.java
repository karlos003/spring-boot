package org.ltq.entity;

import java.math.BigInteger;

public class Comment {
	private BigInteger commentid;
	private String account;
	private String uname;
	private String time;
	private String content;
	private int image;
	private BigInteger post_id;
	public Comment() {
		super();
	}
	public Comment(String account, String time) {
		super();
		this.account = account;
		this.time = time;
	}

	public Comment(String account, String uname, String time, String content, int image) {
		super();
		this.account = account;
		this.uname = uname;
		this.time = time;
		this.content = content;
		this.image = image;
	}
	
	public Comment(String account, String uname, String time, String content, int image, BigInteger post_id) {
		super();
		this.account = account;
		this.uname = uname;
		this.time = time;
		this.content = content;
		this.image = image;
		this.post_id = post_id;
	}
	public BigInteger getCommentid() {
		return commentid;
	}
	public void setCommentid(BigInteger commentid) {
		this.commentid = commentid;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}
	public BigInteger getPost_id() {
		return post_id;
	}
	public void setPost_id(BigInteger post_id) {
		this.post_id = post_id;
	}
	
	
	

	
	
}
