package info.sjd;

import info.sjd.model.Item;
import info.sjd.service.JaxbWorker;
import info.sjd.service.PromDataExtraction;

public class JsoupAppRunner {

    private static final String url = "https://prom.ua/p843085994-noutbuk-omen-dc0047ur.html";

    public static void main(String[] args) {
        PromDataExtraction promDataExtraction = new PromDataExtraction();
        Item item = promDataExtraction.getItemFromProductUrl(url);

        JaxbWorker.convertObjectToXml(item);
    }
}
