package info.sjd.UI;

import info.sjd.dao.CartDAO;
import info.sjd.dao.ItemDAO;
import info.sjd.dao.OrderDAO;
import info.sjd.dao.UserDAO;
import info.sjd.model.Cart;
import info.sjd.model.Item;
import info.sjd.model.Order;
import info.sjd.model.User;
import info.sjd.service.BotService;
import info.sjd.service.PromDataExtraction;
import info.sjd.service.WebDriverService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class UserInterface extends JFrame {

    ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    Validator validator = vf.getValidator();

    public UserInterface() {

        super("prom.ua");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("Verdana", Font.PLAIN, 15);
        final JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(font);

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());

        JPanel buttons = new JPanel();
        content.add(buttons, BorderLayout.NORTH);

        final JPanel productInformation = new JPanel();
        final Label productUrlLabel = new Label("Product url");
        final TextField productUrl = new TextField("", 40);
        productInformation.add(productUrlLabel);
        productInformation.add(productUrl);
        final Button submit = new Button("Submit");
        productInformation.add(submit);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                productInformation.remove(productUrlLabel);
                productInformation.remove(productUrl);
                productInformation.remove(submit);

                String url = String.valueOf(productUrl.getText().toString());

                Item item = new Item();
                item.setUrl(url);

                if (AppValidator.validateString(item, validator).replaceAll(" ", "").length()==0) {

                    PromDataExtraction promDataExtraction = new PromDataExtraction();
                    item = promDataExtraction.getItemFromProductUrl(url);

                    ItemDAO.create(item);

                    TextField itemId = new TextField(item.getItemId(), 40);
                    final JPanel itemIDPanel = new JPanel();
                    Label labelItemId = new Label("ID      ");
                    itemIDPanel.add(labelItemId);
                    itemIDPanel.add(itemId);
                    productInformation.add(itemIDPanel);

                    TextField itemName = new TextField(item.getName(), 40);
                    final JPanel itemNamePanel = new JPanel();
                    Label labelItemName = new Label("Name");
                    itemNamePanel.add(labelItemName);
                    itemNamePanel.add(itemName);
                    productInformation.add(itemNamePanel);

                    TextField itemUrl = new TextField(item.getUrl(), 40);
                    final JPanel itemUrlPanel = new JPanel();
                    Label labelItemUrl = new Label("Url     ");
                    itemUrlPanel.add(labelItemUrl);
                    itemUrlPanel.add(itemUrl);
                    productInformation.add(itemUrlPanel);

                    TextField itemImageUrl = new TextField(item.getImageUrl(), 40);
                    final JPanel itemImageUrlPanel = new JPanel();
                    Label labelItemImageUrl = new Label("Image");
                    itemImageUrlPanel.add(labelItemImageUrl);
                    itemImageUrlPanel.add(itemImageUrl);
                    productInformation.add(itemImageUrlPanel);

                    TextField itemPrice = new TextField(item.getPrice().toString(), 40);
                    final JPanel itemPricePanel = new JPanel();
                    Label labelItemPrice = new Label("Price ");
                    itemPricePanel.add(labelItemPrice);
                    itemPricePanel.add(itemPrice);
                    productInformation.add(itemPricePanel);

                } else {
                    final TextField validateUrl = new TextField(AppValidator.validateString(item, validator));
                    JPanel validateUrlPanel = new JPanel();
                    validateUrlPanel.add(validateUrl);
                    productInformation.add(validateUrlPanel);
                }
            }
        });

        final WebDriver driver = WebDriverService.getWebDriver();

        final JPanel accountRegistration = new JPanel();

        final JPanel accountNamePanel = new JPanel();
        Label accountNameLabel = new Label("Name      ");
        final TextField accountNameField = new TextField("", 40);
        accountNamePanel.add(accountNameLabel);
        accountNamePanel.add(accountNameField);
        accountRegistration.add(accountNamePanel);

        final JPanel accountEmailPanel = new JPanel();
        Label accountEmailLabel = new Label("Email       ");
        final TextField accountEmailField = new TextField("", 40);

        accountEmailPanel.add(accountEmailLabel);
        accountEmailPanel.add(accountEmailField);
        accountRegistration.add(accountEmailPanel);

        final JPanel accountPasswordPanel = new JPanel();
        Label accountPasswordLabel = new Label("Password");
        final TextField accountPasswordField = new TextField("", 40);
        accountPasswordPanel.add(accountPasswordLabel);
        accountPasswordPanel.add(accountPasswordField);
        accountRegistration.add(accountPasswordPanel);

        final Button createAccount = new Button("Create account");
        accountRegistration.add(createAccount);

        createAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = String.valueOf(accountNameField.getText().toString());
                String email = String.valueOf(accountEmailField.getText().toString());
                String password = String.valueOf(accountPasswordField.getText().toString());

                User user = new User(name, email, password);

                accountRegistration.remove(accountNamePanel);
                accountRegistration.remove(accountEmailPanel);
                accountRegistration.remove(accountPasswordPanel);
                accountRegistration.remove(createAccount);

                AppValidator.validate(user, validator);

                if (AppValidator.validateString(user, validator).replaceAll(" ", "").length()==0) {

                    BotService bot = new BotService();
                    try {
                        bot.botPromWithUserObject(driver, user);
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                    UserDAO.create(user);

                    TextField accountCreated = new TextField("Account with name: " + name + ", email: " +
                            email + ", password: " + password + " created", 50);
                    accountRegistration.add(accountCreated);
                } else {
                    final TextField validateUser = new TextField(AppValidator.validateString(user, validator));
                    JPanel validateUserPanel = new JPanel();
                    validateUserPanel.add(validateUser);
                    accountRegistration.add(validateUserPanel);
                }
            }
        });

        final JPanel addProductToCart = new JPanel();

        final JPanel productAddToCartUrl = new JPanel();
        Label productAddToCartUrlLabel = new Label("Product url");
        final TextField productAddToCartUrlField = new TextField("", 40);
        productAddToCartUrl.add(productAddToCartUrlLabel);
        productAddToCartUrl.add(productAddToCartUrlField);
        addProductToCart.add(productAddToCartUrl);

        final Button addProduct = new Button("Add to cart");
        addProductToCart.add(addProduct);

        addProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String url = String.valueOf(productAddToCartUrlField.getText().toString());
                Item item = new Item();
                item.setUrl(url);

                if (AppValidator.validateString(item, validator).replaceAll(" ", "").length()==0) {
                    PromDataExtraction promDataExtraction = new PromDataExtraction();
                    item =  promDataExtraction.getItemFromProductUrl(url);
                    ItemDAO.create(item);

                    User user = UserDAO.findAll().get(UserDAO.findAll().size());

                    Cart cart = new Cart();
                    cart.setUserId(user.getUserId());
                    CartDAO.create(cart);

                    if (CartDAO.fingByCartId(CartDAO.findAll().get(CartDAO.findAll().size()).getCartId()).getClosed()){
                        Order order = new Order();
                        order.setCartId(CartDAO.findAll().get(CartDAO.findAll().size()).getCartId());
                        order.setProductId(item.getProductId());
                        OrderDAO.create(order);
                    } else {
                        Cart cartNew = new Cart();
                        cartNew.setClosed(false);
                        CartDAO.create(cartNew);
                        Order order = new Order();
                        order.setCartId(CartDAO.findAll().get(CartDAO.findAll().size()).getCartId());
                        order.setProductId(item.getProductId());
                        OrderDAO.create(order);
                    }

                    driver.get(url);
                    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/table/tbody" +
                            "/tr/td/div[1]/div[1]/div[2]/div[7]/div[1]/div[1]/a")).click();

                } else {
                    final TextField validateUrl = new TextField(AppValidator.validateString(item, validator));
                    JPanel validateUrlPanel = new JPanel();
                    validateUrlPanel.add(validateUrl);
                    addProductToCart.add(validateUrlPanel);
                }
            }
        });

        tabbedPane.addTab("Product information" , productInformation);
        tabbedPane.addTab("Account Registration" , accountRegistration);
        tabbedPane.addTab("Add product to cart" , addProductToCart);

        content.add(tabbedPane, BorderLayout.CENTER);

        getContentPane().add(content);

        setPreferredSize(new Dimension(600, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}