package com.mhx.ithome;

import com.mhx.AbstractPageProcessor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

import java.util.List;
import java.util.Objects;

public class LinksProcessorForITHome extends AbstractPageProcessor {

    private List<String> links;

    LinksProcessorForITHome(List<String> links) {
        super(Site.me());
        this.links = Objects.requireNonNull(links);
    }

    @Override
    public void process(Page page) {
        List<String> originLinks = page.getHtml().css(".new-list>div").regex("https://www.ithome.com/html/it/\\w+.htm").all();
        links.addAll(originLinks);
        ITHomeApp.THE_LOGGER.info(links.toString());
    }
}
