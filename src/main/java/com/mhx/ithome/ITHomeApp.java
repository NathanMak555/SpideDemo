package com.mhx.ithome;

import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class ITHomeApp {
    public static final Logger THE_LOGGER = Logger.getLogger("theLogger");
    public static final String IT_Home_Url = "https://www.ithome.com/";
    public static void main(String[] args) {
        List<String> links  = new ArrayList<>();
        Spider.create(new LinksProcessorForITHome(links)).addUrl(IT_Home_Url).run();
    }
}
