package info.sjd.service.backEndService;

import info.sjd.dao.ItemDAO;
import info.sjd.model.Item;

import java.util.List;

public class ItemService {

    public static List<Item> findAll() {
        return ItemDAO.findAll();
    }
}
