package fr.lonesocket.garage.library.loader;

import fr.lonesocket.garage.library.model.Offer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Loader {
    private static final String URL = "https://rocket-league.com/trading?filterItem=0&filterCertification=0&filterPaint=0&filterPlatform=0&filterSearchType=1";
    private static final String USER_AGENT_HEADER = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:58.0) Gecko/20100101 Firefox/58.0";

    public List<Offer> getOffers() throws LoaderException {
        return parseDocument(getPage());
    }

    private Document getPage() throws LoaderException {
        try {
            HttpsURLConnection conn = (HttpsURLConnection) new URL(URL).openConnection();
            conn.setRequestProperty("User-Agent", USER_AGENT_HEADER);
            conn.connect();
            StringBuilder builder = new StringBuilder();
            String line;
            if (conn.getResponseCode() == 200) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    while ((line = reader.readLine()) != null) {
                        builder.append(line).append("\n");
                    }
                }
                return Jsoup.parse(builder.toString());
            }
            throw new LoaderException("The server returned an " + conn.getResponseCode() + " error");
        } catch (IOException e) {
            throw new LoaderException("Cannot connect to the server : " + e.getMessage(), e);
        }
    }

    private List<Offer> parseDocument(Document doc) {
        Element grid = doc.getElementsByClass("rlg-grid").get(1);
        Elements list = grid.child(0).child(6).children();
        List<Offer> offers = new ArrayList<>();
        for (Element element : list) {
            if (element.attr("class").contains("rlg-trade-display-container")) {
                offers.add(parseOffer(element));
            }
        }
        return offers;
    }

    private Offer parseOffer(Element element) {
        System.out.println(element.child(3).child(1).child(0).child(1).text());
        return null;
    }
}
