package fr.lonesocket.garage.library.loader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

public class ReferenceParser {
    Map<String, String> getReferenceItems(Document doc) throws ReferenceParserException {
        return parseOptions("Item", doc);
    }

    Map<String, String> getCertifications(Document doc) throws ReferenceParserException {
        return parseOptions("Certification", doc);
    }

    Map<String, String> getPaints(Document doc) throws ReferenceParserException {
        return parseOptions("Paint", doc);
    }

    Map<String, String> getPlatforms(Document doc) throws ReferenceParserException {
        return parseOptions("Platform", doc);
    }

    private Map<String, String> parseOptions(String key, Document doc) throws ReferenceParserException {
        Elements elements = doc.getElementsByAttributeValue("data-placeholder", key);
        if (elements.size() != 1) {
            throw new ReferenceParserException("Cannot find the " + key + "s");
        }
        Map<String, String> map = new HashMap<>();
        for (Element element : elements.get(0).getElementsByTag("option")) {
            map.put(element.attr("value"), element.text());
        }
        return map;
    }
}
