package com.mhx.ithome;

import com.mhx.Article;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class ITHomeApp {
    public static final Logger THE_LOGGER = Logger.getLogger("theLogger");
    public static final String IT_Home_Url = "https://www.ithome.com/";

    public static void main(String[] args) {
        List<String> links = new ArrayList<>();
        List<Article> articles = new ArrayList<>();
        Spider linkSpider = Spider.create(new LinksProcessorForITHome(links)).addUrl(IT_Home_Url);
        Spider webPageSpider = Spider.create(new PageProcessorForITHome(articles));
        linkSpider.run();
        linkSpider.close();
        links.forEach(l -> {
            webPageSpider.addUrl(l).run();
        });
        webPageSpider.close();
    }
}
