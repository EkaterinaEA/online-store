package info.sjd.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cart {

    private Integer cartId;
    private Long creationTime;
    private Boolean closed;
    private Integer userId;

    public Cart(Long creationTime, boolean closed, Integer userId) {
        this.creationTime = creationTime;
        this.closed = closed;
        this.userId = userId;
    }
}
