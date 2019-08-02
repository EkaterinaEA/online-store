package info.sjd.servlet;

import info.sjd.model.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class GetIndexPageServlet extends HttpServlet {

    private Map<Integer, Item> itemMap;

    @Override
    public void init() throws ServletException {

        final Object itemMap = getServletContext().getAttribute("itemMap");

            this.itemMap = (ConcurrentHashMap<Integer, Item>) itemMap;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("itemMap", itemMap.values());
        req.getRequestDispatcher("/WEB-INF/jsp/catalog.jsp").forward(req, resp);
    }
}