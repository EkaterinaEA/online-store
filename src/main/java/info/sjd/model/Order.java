package info.sjd.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {

    private Integer orderId;
    private Integer productId; // autoincrement product id in database
    private int amount;
    private Integer cartId;

    public Order(Integer productId, int amount, Integer cartId) {
        this.productId = productId;
        this.amount = amount;
        this.cartId = cartId;
    }
}
