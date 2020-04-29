package com.kosmo.onememo.service;

public class OneMemoDTO {

	private String no;
	private String title;
	private String content;
	private java.sql.Date postDate;
	private String id;
	private String name; // 목록에서 이름 출력용
	private String commentCount; // 댓글수

	
	
	
	
	public String getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public java.sql.Date getPostDate() {
		return postDate;
	}

	public void setPostDate(java.sql.Date postDate) {
		this.postDate = postDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "[no=" + no + ", title=" + title + ", content=" + content + ", postDate=" + postDate + ", id="
				+ id + ", name=" + name + "]";
	}

	
	

}
