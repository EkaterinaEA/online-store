package info.sjd.service;


import info.sjd.model.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.logging.Logger;


public class PromDataExtraction implements DataExtraction {

    public static Logger log = Logger.getLogger(PromDataExtraction.class.getName());

    @Override
    public Item getItemFromProductUrl(String url) {

        Item item = new Item();

        try {
            Document document = Jsoup.connect(url).timeout(10000).validateTLSCertificates(false).get();
            item.setUrl(url);
            item.setName(getName(document));
            item.setItemId(getItemId(document));
            item.setPrice(getPrice(document));
            item.setImageUrl(getImageUrl(document));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }

    private String getName(Document document) {
        String name = "";
        name = document.getElementsByTag("h1").first().text();
        return name;
    }

    private String getItemId(Document document){

        String itemId = document.getElementsByClass("js-product-buy-button").attr("data-product-id");
        return itemId;

    }

    private String getImageUrl(Document document){

        String imageUrl = document.getElementsByClass("js-product-buy-button").attr("data-product-big-picture");
        return imageUrl;
    }

    private Integer getPrice(Document document){
        String priceText = document.getElementsByClass("js-product-buy-button").attr("data-product-price")
                .replaceAll("\\D", "");
        return Integer.parseInt(priceText);
    }

    public void printItem(String url){
        log.info("Name: " + String.valueOf(getItemFromProductUrl(url).getName()));
        log.info("url: " + String.valueOf(getItemFromProductUrl(url).getUrl()));
        log.info("itemId: " + String.valueOf(getItemFromProductUrl(url).getItemId()));
        log.info("imageUrl: " + String.valueOf(getItemFromProductUrl(url).getImageUrl()));
        log.info("itemPrice: " + String.valueOf(getItemFromProductUrl(url).getPrice()));
    }

    public String printItemString(String url){
        return "Name: " + String.valueOf(getItemFromProductUrl(url).getName()) + "\n" +
                "url: " + String.valueOf(getItemFromProductUrl(url).getUrl()) + "\n" +
                "itemId: " + String.valueOf(getItemFromProductUrl(url).getItemId()) + "\n" +
                "imageUrl: " + String.valueOf(getItemFromProductUrl(url).getImageUrl()) + "\n" +
                "itemPrice: " + String.valueOf(getItemFromProductUrl(url).getPrice());
    }

}

