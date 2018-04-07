package com.mhx.ithome;

import com.mhx.AbstractPageProcessor;
import com.mhx.Article;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class PageProcessorForITHome extends AbstractPageProcessor {

    private List<Article> articles;

    PageProcessorForITHome(List<Article> articles) {
        super(Site.me());
        this.articles = Objects.requireNonNull(articles);
    }

    @Override
    public void process(Page page) {
        Article article = new Article();
        //set url
        String url = page.getUrl().toString();
        ITHomeApp.THE_LOGGER.info(url);
        article.setUrl(url);
        //set  titleContent
        String titleContent = page.getHtml().xpath("//div[@class='post_title']/h1/text()").toString();
        ITHomeApp.THE_LOGGER.info(titleContent);
        article.setTitle(titleContent);
        //set  dateTime
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeContent = page.getHtml().xpath("//div[@class='post_title']/span/span[@id='pubtime_baidu']/text()").toString().trim();
        article.setDateTime(LocalDateTime.parse(dateTimeContent, dateTimeFormatter));
        //set articleContent
        String articleContent = page.getHtml().xpath("//div[@class='post_content']").toString().trim();
        article.setContent(articleContent);

        articles.add(article);

        ITHomeApp.THE_LOGGER.info("success");
    }
}
