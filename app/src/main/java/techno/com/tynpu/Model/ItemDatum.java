package techno.com.tynpu.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user1 on 3/20/2018.
 */

public class ItemDatum {
    @SerializedName("item_name")
    @Expose
    private String itemName;
    @SerializedName("product_price")
    @Expose
    private String productPrice;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("subitem_data")
    @Expose
    private List<SubitemDatum> subitemData = null;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SubitemDatum> getSubitemData() {
        return subitemData;
    }

    public void setSubitemData(List<SubitemDatum> subitemData) {
        this.subitemData = subitemData;
    }
}
