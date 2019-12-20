/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.dummy;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 *
 * @author manuel
 */
public class CrawlerController {

    public static void main(String[] args) throws Exception {
        final int numberOfCrawlers = 10;
        final CrawlConfig config = new CrawlConfig();
        final String crawlStorageFolder = "data";

        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setPolitenessDelay(500);
        config.setMaxDepthOfCrawling(2);
        config.setMaxPagesToFetch(100);
        config.setIncludeBinaryContentInCrawling(false);

        final PageFetcher pageFetcher = new PageFetcher(config);
        final RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        final RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        final CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        controller.addSeed("https://www.imovirtual.com/comprar/apartamento/");

        controller.start(SampleCrawler.class, numberOfCrawlers);

    }

}
