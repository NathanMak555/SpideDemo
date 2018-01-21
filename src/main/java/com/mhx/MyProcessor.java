package com.mhx;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

public class MyProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private  Article article;

    public MyProcessor(Article article){
        if(article==null){
            throw new NullPointerException();
        }
        this.article = article;
    }

    public MyProcessor(){

    }

    public void process(Page page) {
        List<String> list = page.getHtml().links().regex("http://www.gamersky.com/wenku/201801/1005335\\w+.shtml").all();
        page.addTargetRequests(list);

        //set url
        String url = page.getUrl().toString();
        logger.info(url);
        article.setUrl(url);

        //set title
        String titleContent = page.getHtml().xpath("//div[@class='Mid2L_tit']/h1/text()").toString();
        logger.info(titleContent);
        article.setTitle(titleContent);

        //set dateTime
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeContent = page.getHtml().xpath("//div[@class='Mid2L_tit']/div/text()").toString().trim().substring(0, 19);
        article.setDateTime(LocalDateTime.parse(dateTimeContent, dateTimeFormatter));
        logger.info(dateTimeContent);

        //set content
        StringBuilder articleContent = new StringBuilder();
        List<String> articleContents = page.getHtml().xpath("//div[@class='Mid2L_con']/p").all();
        for (String s : articleContents) {
            articleContent.append(s);
        }
        logger.info(articleContent.toString());
        article.setContent(articleContent.toString());
        logger.info("success");
    }

    public Site getSite() {
        return site;
    }
}
