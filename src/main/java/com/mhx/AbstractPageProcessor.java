package com.mhx;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Objects;

public abstract class AbstractPageProcessor implements PageProcessor {

    private Site site;

    public AbstractPageProcessor(Site site){
        this.site = Objects.requireNonNull(site);
    }

    @Override
    public abstract void process(Page page);

    @Override
    public Site getSite() {
        return Objects.requireNonNull(site);
    }
}
