package fr.lonesocket.garage.library.loader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

public class ReferenceParser {
    Map<Integer, String> getReferenceItems(Document doc) throws ReferenceParserException {
        Elements elements = doc.getElementsByAttributeValue("data-placeholder", "Item");
        if (elements.size() != 1) {
            throw new ReferenceParserException("Cannot find the items");
        }
        Map<Integer, String> items = new HashMap<>();
        for (Element element : elements.get(0).getElementsByTag("option")) {
            parseItem(items, element);
        }
        return items;
    }

    private void parseItem(Map<Integer, String> map, Element element) {
        int id = Integer.parseInt(element.attr("value"));
        map.put(id, element.text());
    }
}
