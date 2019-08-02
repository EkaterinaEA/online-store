package info.sjd.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Item")
@XmlType(propOrder = {"name", "price", "url", "imageUrl"})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Item {

    private String itemId;
    private String name;
    private Integer price;
    private String imageUrl;
    private int productId; // article_id in database

    @NotNull(message = "url must be set")
    @Pattern(regexp = "https://prom\\\\?.ua?/?.*", message = "incorrectly url")
    private String url;

    public Item(String itemId,String name, Integer price, String url, String imageUrl) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    @XmlAttribute
    public String getItemId() {
        return itemId;
    }
}
