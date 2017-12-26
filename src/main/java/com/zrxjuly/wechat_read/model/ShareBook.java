package com.zrxjuly.wechat_read.model;

/**
 * 图书实体类.
 * 2017-12-26.
 * @author zhangrongxiang
 */
public class ShareBook {

	// 图书ID.
	private int bookId;

	// 图书名称.
	private String bookName;

	// 图书作者.
	private String bookAuthor;

	// 豆瓣评分.
	private String doubanScore;

	// 图书图片url.
	private String imgUrl;

	// 内容简介.
	private String bookContent;

	// 图书推荐时间.
	private String updateTime;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getDoubanScore() {
		return doubanScore;
	}

	public void setDoubanScore(String doubanScore) {
		this.doubanScore = doubanScore;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getBookContent() {
		return bookContent;
	}

	public void setBookContent(String bookContent) {
		this.bookContent = bookContent;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "ShareBook [bookId=" + bookId + ", bookName=" + bookName + ", bookAuthor=" + bookAuthor
				+ ", doubanScore=" + doubanScore + ", imgUrl=" + imgUrl + ", bookContent=" + bookContent
				+ ", updateTime=" + updateTime + "]";
	}

}
