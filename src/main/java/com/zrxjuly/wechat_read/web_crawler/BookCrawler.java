package com.zrxjuly.wechat_read.web_crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.zrxjuly.wechat_read.model.ShareBook;

/**
 * 图书信息抓取.
 * 2017-12-26.
 * @author zhangrongxiang
 *
 */
public class BookCrawler {

	private Log logger = LogFactory.getLog(BookCrawler.class);

	public List<ShareBook> crawlerBook() {
		List<ShareBook> shareBookList = new ArrayList<ShareBook>();
		String url = "https://book.douban.com/top250/";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.select("div.pl2 a");
			for (Element element : elements) {
				String infoUrl = element.attr("href");
				if (infoUrl.contains("book.douban.com/subject")) {
					// 获取图书详情的url.
					// infoUrl = "https://book.douban.com/subject/" + infoUrl.replace("./", "");
					infoUrl = infoUrl.replace("./", "");
					logger.info("infoUrl is ===" + infoUrl);

					// 抓取图书详情内容.
					shareBookList.add(this.crawlerBookDetails(infoUrl));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return shareBookList;
	}

	/**
	 * 抓取图书详情内容.
	 * 
	 * @param infoUrl
	 * @param model
	 * @return
	 */
	private ShareBook crawlerBookDetails(String infoUrl) {
		ShareBook shareBook = new ShareBook();
		
		try {
			Document doc = Jsoup.connect(infoUrl).timeout(10000).get();
			
			// 获取图书名称.
			Elements bookName = doc.select("#wrapper > h1 > span");
			for (Element element : bookName) {
				String book = element.text();
				shareBook.setBookName(book);
			}
			
			// 获取图书的豆瓣评分.
			Elements douBan = doc.select("#interest_sectl > div > div.rating_self.clearfix > strong");
			for (Element element : douBan) {
				String doubanScore = element.text();
				shareBook.setDoubanScore(doubanScore);
			}
			
			// 获取图书作者.
			Elements bookAuthor = doc.select("#info > a:nth-child(2)");
			for (Element element : bookAuthor) {
				String author = element.text();
				shareBook.setBookAuthor(author);
			}
			
			// 图书图片url.
			Elements imgUrl = doc.select("#mainpic > a > img");
			for (Element element : imgUrl) {
				String url = element.attr("src");
				shareBook.setImgUrl(url);
			}
			
			// 图书内容简介.
			Elements bookContent = doc.select("#link-report .intro");
			for (Element element : bookContent) {
				String content = element.toString();
				System.out.println("content======" + content);
				shareBook.setBookContent(content);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info(shareBook);
		return shareBook;
	}

	public static void main(String[] args) {
		BookCrawler bookCrawler = new BookCrawler();
		bookCrawler.crawlerBook();
	}

}
