package com.mhx;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Article article = new Article();
        Spider.create(new MyProcessor(article)).addUrl("http://www.gamersky.com/wenku/201801/1005335.shtml").run();
        System.out.print("good");
    }
}
