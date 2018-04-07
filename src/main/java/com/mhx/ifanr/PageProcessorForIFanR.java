package com.mhx.ifanr;

import com.mhx.AbstractPageProcessor;
import com.mhx.Article;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class PageProcessorForIFanR extends AbstractPageProcessor {

    private List<Article> articles;

    PageProcessorForIFanR(List<Article> articles) {
        super(Site.me());
        this.articles = Objects.requireNonNull(articles);
    }

    @Override
    public void process(Page page) {
        Article article = new Article();

        String url = page.getUrl().toString();
        article.setUrl(url);

        String titleContent = page.getHtml().regex("//h1[@class='.c-single-normal__title']/text()").toString();
        article.setTitle(titleContent);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeContent = page.getHtml().regex("//meta[@property='article:published_time']/@content").toString().substring(0,19).replace("T"," ");
        article.setDateTime(LocalDateTime.parse(dateTimeContent,dateTimeFormatter));

        String articleContent = page.getHtml().regex("//article").toString();
        article.setContent(articleContent);

        articles.add(article);

        IFanRApp.THE_LOGGER.info("success");
    }
}
