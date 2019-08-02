package info.sjd.service.backEndService;

import info.sjd.dao.CartDAO;
import info.sjd.model.Cart;

public class CartService {

    public static Cart create(Cart cart){
        if (cart.getClosed() == null || cart.getClosed() == false){
            Cart createdCart = CartDAO.create(cart);
            if (createdCart != null){
                return createdCart;
            }
        }
        else{
            return null;
        }
        return null;
    }
}