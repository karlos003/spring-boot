package org.ltq.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@SuppressWarnings("serial")
public class User implements Serializable {
	private int user_id;
	private String user_account;
	private String user_pwd;
	private String user_name;
	private int user_photo;
	private BigInteger post_id;
	private Date sub_time;
	private String sub_account;
	private Date usub_time;
	public User() {
		super();
	}
	public User(String user_account, BigInteger post_id) {
		super();
		this.user_account = user_account;
		this.post_id = post_id;
	}

	public User(String account, int user_photo) {
		super();
		this.user_account = account;
		this.user_photo = user_photo;
	}
	public User(String account, String pwd) {
		super();
		this.user_account = account;
		this.user_pwd = pwd;
	}
	public User(String user_account, BigInteger post_id, Date sub_time) {
		super();
		this.user_account = user_account;
		this.post_id = post_id;
		this.sub_time = sub_time;
	}

	public User(String account, String pwd, String name) {
		super();
		this.user_account = account;
		this.user_pwd = pwd;
		this.user_name = name;
	}
	public User(String account, String pwd, String name, int user_photo) {
		super();
		this.user_account = account;
		this.user_pwd = pwd;
		this.user_name = name;
		this.user_photo = user_photo;
	}
	public String getUser_account() {
		return user_account;
	}
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getUser_photo() {
		return user_photo;
	}
	public void setUser_photo(int user_photo) {
		this.user_photo = user_photo;
	}
	public BigInteger getPost_id() {
		return post_id;
	}
	public void setPost_id(BigInteger post_id) {
		this.post_id = post_id;
	}
	public Date getSub_time() {
		return sub_time;
	}
	public void setSub_time(Date sub_time) {
		this.sub_time = sub_time;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getSub_account() {
		return sub_account;
	}
	public void setSub_account(String sub_account) {
		this.sub_account = sub_account;
	}
	public Date getUsub_time() {
		return usub_time;
	}
	public void setUsub_time(Date usub_time) {
		this.usub_time = usub_time;
	}
	
	
	
	
	
}
