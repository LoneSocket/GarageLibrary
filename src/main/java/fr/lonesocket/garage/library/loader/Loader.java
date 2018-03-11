package fr.lonesocket.garage.library.loader;

import fr.lonesocket.garage.library.model.Offer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Loader {
    public static final String BASE_URL = "https://rocket-league.com";
    private static final String URL = "https://rocket-league.com/trading";
    private static final String USER_AGENT_HEADER = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:58.0) Gecko/20100101 Firefox/58.0";
    private final ReferenceParser referenceParser;
    private final OfferParser offerParser;
    private Document doc;

    Loader() {
        doc = null;
        referenceParser = new ReferenceParser();
        offerParser = new OfferParser();
    }

    public List<Offer> getOffers() throws LoaderException {
        return offerParser.parseOffers(getLivePage(), getReferenceItems());
    }

    public Map<String, String> getReferenceItems() throws LoaderException {
        try {
            return referenceParser.getReferenceItems(getPage());
        } catch (ReferenceParserException e) {
            throw new LoaderException("Cannot load the reference items : " + e.getMessage(), e);
        }
    }

    private Document getLivePage() throws LoaderException {
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
                doc = Jsoup.parse(builder.toString());
                return doc;
            }
            throw new LoaderException("The server returned an " + conn.getResponseCode() + " error");
        } catch (IOException e) {
            throw new LoaderException("Cannot connect to the server : " + e.getMessage(), e);
        }
    }

    private Document getPage() throws LoaderException {
        if (doc == null) {
            getLivePage();
        }
        return doc;
    }
}
