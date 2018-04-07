package com.mhx.ifanr;

import com.mhx.AbstractPageProcessor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

import java.util.List;
import java.util.Objects;

public class LinksProcessorForIFanR extends AbstractPageProcessor {

    private List<String> links;

    LinksProcessorForIFanR(List<String> links) {
        super(Site.me());
        this.links = Objects.requireNonNull(links);
    }

    @Override
    public void process(Page page) {
        List<String> originLinks = page.getHtml().css(".collection-zone>article-link").links().all();
        links.addAll(originLinks);
        IFanRApp.THE_LOGGER.info(links.toString());
    }
}
