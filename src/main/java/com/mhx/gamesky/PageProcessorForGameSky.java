package com.mhx.gamesky;

import com.mhx.AbstractPageProcessor;
import com.mhx.Article;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class PageProcessorForGameSky extends AbstractPageProcessor {

    private List<Article> articles;

    PageProcessorForGameSky(List<Article> articles) {
        super(Site.me());
        this.articles = Objects.requireNonNull(articles);
    }

    @Override
    public void process(Page page) {
        Article article = new Article();

        //set url
        String url = page.getUrl().toString();
        GameSkyApp.THE_LOGGER.info(url);
        article.setUrl(url);

        //set title
        String titleContent = page.getHtml().xpath("//div[@class='Mid2L_tit']/h1/text()").toString();
        GameSkyApp.THE_LOGGER.info(titleContent);
        article.setTitle(titleContent.trim());

        //set dateTime
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeContent = page.getHtml().xpath("//div[@class='Mid2L_tit']/div/text()").toString().trim().substring(0, 19);
        article.setDateTime(LocalDateTime.parse(dateTimeContent, dateTimeFormatter));
        GameSkyApp.THE_LOGGER.info(dateTimeContent);

        //set content
        StringBuilder articleContent = new StringBuilder();
        List<String> articleContents = page.getHtml().xpath("//div[@class='Mid2L_con']/p").all();
        for (String s : articleContents) {
            articleContent.append(s);
        }
        GameSkyApp.THE_LOGGER.info(articleContent.toString());
        article.appendContent(articleContent.toString().trim());

        articles.add(article);

        String regexUrl = url.replaceAll(".shtml", "\\w+.shtml");
        List<String> list = page.getHtml().links().regex(regexUrl).all();
        page.addTargetRequests(list);

        GameSkyApp.THE_LOGGER.info("success");
    }

}
