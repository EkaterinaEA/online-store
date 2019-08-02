package info.sjd.servlet;

import info.sjd.model.Item;
import info.sjd.service.backEndService.ItemService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class ContextListener implements ServletContextListener {

    private Map<Integer, Item> itemMap;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        final ServletContext servletContext = servletContextEvent.getServletContext();

        itemMap = new ConcurrentHashMap<>();

        servletContext.setAttribute("itemMap", itemMap);

        final List<Item> itemList = ItemService.findAll();

        for (int i=0; i<itemList.size(); i++){
            this.itemMap.put(itemList.get(i).getProductId(), itemList.get(i));
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // close resources
    }
}