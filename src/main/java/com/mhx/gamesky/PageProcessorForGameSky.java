package com.mhx.gamesky;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class PageProcessorForGameSky implements PageProcessor {

    private Site site = Site.me();
    private List<ArticleOfGameSky> articles;

    public PageProcessorForGameSky(List<ArticleOfGameSky> articles) {
        this.articles = Objects.requireNonNull(articles);
    }

    public PageProcessorForGameSky() {
        this.articles = null;
    }

    @Override
    public void process(Page page) {
        ArticleOfGameSky article = new ArticleOfGameSky();

        //set url
        String url = page.getUrl().toString();
        App.THE_LOGGER.info(url);
        article.setUrl(url);

        //set title
        String titleContent = page.getHtml().xpath("//div[@class='Mid2L_tit']/h1/text()").toString();
        App.THE_LOGGER.info(titleContent);
        article.setTitle(titleContent);

        //set dateTime
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeContent = page.getHtml().xpath("//div[@class='Mid2L_tit']/div/text()").toString().trim().substring(0, 19);
        article.setDateTime(LocalDateTime.parse(dateTimeContent, dateTimeFormatter));
        App.THE_LOGGER.info(dateTimeContent);

        //set content
        StringBuilder articleContent = new StringBuilder();
        List<String> articleContents = page.getHtml().xpath("//div[@class='Mid2L_con']/p").all();
        for (String s : articleContents) {
            articleContent.append(s);
        }
        App.THE_LOGGER.info(articleContent.toString());
        article.setContent(articleContent.toString());

        articles.add(article);

        String regexUrl = url.replaceAll(".shtml", "\\w+.shtml");
        List<String> list = page.getHtml().links().regex(regexUrl).all();
        page.addTargetRequests(list);

        App.THE_LOGGER.info("success");
    }

    @Override
    public Site getSite() {
        return site;
    }
}
