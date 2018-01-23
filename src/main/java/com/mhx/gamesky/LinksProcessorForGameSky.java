package com.mhx.gamesky;


import com.mhx.gamesky.App;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.Objects;

public class LinksProcessorForGameSky implements PageProcessor {

    private Site site = Site.me();
    private List<String> links;

    public LinksProcessorForGameSky() {

    }

    public LinksProcessorForGameSky(List<String> links) {
        this.links = Objects.requireNonNull(links);
    }

    @Override
    public void process(Page page) {
        List<String> originLinks = page.getHtml().css(".Mid1_M>:first-child>:nth-child(2)>.ptxt").links().regex("http://www.gamersky.com/news/\\w+/\\w+.shtml").all();
        links.addAll(originLinks);
        App.THE_LOGGER.info(links.toString());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
