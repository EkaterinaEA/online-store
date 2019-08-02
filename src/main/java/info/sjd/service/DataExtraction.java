package info.sjd.service;

import info.sjd.model.Item;

public interface DataExtraction {
    Item getItemFromProductUrl(String url);
}
