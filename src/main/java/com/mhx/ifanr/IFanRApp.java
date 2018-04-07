package com.mhx.ifanr;

import com.mhx.Article;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class IFanRApp {
    public static final Logger THE_LOGGER = Logger.getLogger("theLogger");
    public static final String IFanR_Url = "http://www.ifanr.com/";

    public static void main(String[] args){
        List<String> links = new ArrayList<>();
        List<Article> articles = new ArrayList<>();
        Spider linkSpider = Spider.create(new LinksProcessorForIFanR(links)).addUrl(IFanRApp.IFanR_Url);
        Spider webPageSpider = Spider.create(new PageProcessorForIFanR(articles));
        linkSpider.run();
        linkSpider.close();
        links.forEach(link->{
            webPageSpider.addUrl(link).run();
        });
        webPageSpider.close();
    }
}
