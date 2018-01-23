package com.mhx.ithome;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.Objects;

public class PageProcessorForITHome implements PageProcessor {

    private Site site = Site.me();
    private List<ArticleOfITHome> articles;

    public PageProcessorForITHome() {
        articles = null;
    }

    public PageProcessorForITHome(List<ArticleOfITHome> articles) {
        this.articles = Objects.requireNonNull(articles);
    }

    @Override
    public void process(Page page) {
        ArticleOfITHome article = new ArticleOfITHome();
        //set url
        String url = page.getUrl().toString();
        ITHomeApp.THE_LOGGER.info(url);
        article.setUrl(url);
        //set  title
        String title = page.getHtml().xpath("//div[@class='post_title']/h1/text()").toString();
        ITHomeApp.THE_LOGGER.info(title);
        article.setTitle(title);
        //set content
    }

    @Override
    public Site getSite() {
        return site;
    }
}
