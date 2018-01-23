package com.mhx.ithome;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.Objects;

public class LinksProcessorForITHome implements PageProcessor {

    private Site site = Site.me();
    private List<String> links;

    public LinksProcessorForITHome() {
        links = null;
    }

    public LinksProcessorForITHome(List<String> links) {
        this.links = Objects.requireNonNull(links);
    }

    @Override
    public void process(Page page) {
        List<String> originLinks = page.getHtml().css(".new-list>div").regex("https://www.ithome.com/html/it/\\w+.htm").all();
        links.addAll(originLinks);
        ITHomeApp.THE_LOGGER.info(links.toString());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
