package com.mhx.gamesky;


import com.mhx.AbstractPageProcessor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

import java.util.List;
import java.util.Objects;

public class LinksProcessorForGameSky extends AbstractPageProcessor {

    private List<String> links;

    LinksProcessorForGameSky(List<String> links) {
        super(Site.me());
        this.links = Objects.requireNonNull(links);
    }

    @Override
    public void process(Page page) {
        List<String> originLinks = page.getHtml().css(".Mid1_M>:first-child>:nth-child(2)>.ptxt").links().regex("http://www.gamersky.com/news/\\w+/\\w+.shtml").all();
        links.addAll(originLinks);
        GameSkyApp.THE_LOGGER.info(links.toString());
    }

}
