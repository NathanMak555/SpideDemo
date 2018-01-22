package com.mhx;

import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Hello world!
 */
public class App {

    public static final Logger THE_LOGGER = Logger.getLogger("TheLogger");
    public static final String GAME_SKY_URL = "http://www.gamersky.com/";

    public static void main(String[] args) {
        List<Article> articles = new ArrayList<>();
        List<String> links = new ArrayList<>();
        //also,we can user
        Spider linkSpider = Spider.create(new LinksProcessor(links)).addUrl(GAME_SKY_URL);
        Spider webPageSpider = Spider.create(new WebPageProcessor(articles));
        linkSpider.run();
        linkSpider.close();
        links.stream().forEach(l -> {
            webPageSpider.addUrl(l).run();
        });
        webPageSpider.close();
    }
}
