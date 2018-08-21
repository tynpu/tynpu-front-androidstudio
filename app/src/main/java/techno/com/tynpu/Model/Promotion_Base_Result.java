package techno.com.tynpu.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user1 on 2/21/2018.
 */

public class Promotion_Base_Result {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("promotion_id")
    @Expose
    private String promotionId;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("sub_product_name")
    @Expose
    private String subProductName;
    @SerializedName("sub_product_price")
    @Expose
    private String subProductPrice;
    @SerializedName("sub_product_description")
    @Expose
    private String subProductDescription;
    @SerializedName("sub_product_image")
    @Expose
    private String subProductImage;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("promotion_product")
    @Expose
    private PromotionProduct promotionProduct;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSubProductName() {
        return subProductName;
    }

    public void setSubProductName(String subProductName) {
        this.subProductName = subProductName;
    }

    public String getSubProductPrice() {
        return subProductPrice;
    }

    public void setSubProductPrice(String subProductPrice) {
        this.subProductPrice = subProductPrice;
    }

    public String getSubProductDescription() {
        return subProductDescription;
    }

    public void setSubProductDescription(String subProductDescription) {
        this.subProductDescription = subProductDescription;
    }

    public String getSubProductImage() {
        return subProductImage;
    }

    public void setSubProductImage(String subProductImage) {
        this.subProductImage = subProductImage;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public PromotionProduct getPromotionProduct() {
        return promotionProduct;
    }

    public void setPromotionProduct(PromotionProduct promotionProduct) {
        this.promotionProduct = promotionProduct;
    }}
