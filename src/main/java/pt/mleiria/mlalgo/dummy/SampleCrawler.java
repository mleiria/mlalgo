package pt.mleiria.mlalgo.dummy;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.regex.Pattern;

import static java.lang.System.out;

public class SampleCrawler extends WebCrawler {

    private static final Pattern IMAGE_EXTENSIONS = Pattern.compile(".*\\.(bmp|gif|jpg|png)$");

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        final String href = url.getURL().toLowerCase();
        if (IMAGE_EXTENSIONS.matcher(href).matches()) {
            return false;
        }
        return href.startsWith("https://www.imovirtual.com/");
    }

    @Override
    public void visit(Page page) {
        final int docid = page.getWebURL().getDocid();
        final String url = page.getWebURL().getURL();

        if (page.getParseData() instanceof HtmlParseData) {
            final HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            final String text = htmlParseData.getText();
            out.println("START ------------------------------------------------");
            out.println("\nURL: " + url);
            out.println("Text: " + text);
            out.println("Text length: " + text.length());
            out.println("END   ------------------------------------------------");
        }
    }
}
