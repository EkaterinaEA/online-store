package info.sjd;

import info.sjd.UI.UserInterface;
import info.sjd.dao.CartDAO;
import info.sjd.dao.ItemDAO;
import info.sjd.dao.UserDAO;
import info.sjd.model.Cart;
import info.sjd.model.Item;
import info.sjd.model.Order;
import info.sjd.model.User;
import info.sjd.service.CSVFileManagerService;

import java.util.Date;

public class AppRunner {
    public static void main(String[] args) {
        //    javax.swing.SwingUtilities.invokeLater(new Runnable() {
        //        public void run() {
        //            //    JFrame.setDefaultLookAndFeelDecorated(true);
        //           new UserInterface();
        //       }
        //   });
        //    CSVFileManagerService.recordFile(false);

        User user = new User("Mike", "Mike@mail.ru", "vbuieuv");
        User userDAO = UserDAO.create(user);
        System.out.println(userDAO.getUserId());

        System.out.println(ItemDAO.findAll());

    }
}
