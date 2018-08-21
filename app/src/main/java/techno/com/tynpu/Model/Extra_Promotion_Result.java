package techno.com.tynpu.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user1 on 2/21/2018.
 */

public class Extra_Promotion_Result {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("restaurant_id")
    @Expose
    private String restaurantId;
    @SerializedName("promotion_id")
    @Expose
    private String promotionId;
    @SerializedName("promotion_product_id")
    @Expose
    private String promotionProductId;
    @SerializedName("promotion_sub_product_id")
    @Expose
    private String promotionSubProductId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("book_status")
    @Expose
    private String bookStatus;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("combo_count")
    @Expose
    private String comboCount;
    @SerializedName("promotion_product")
    @Expose
    private PromotionProduct promotionProduct;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionProductId() {
        return promotionProductId;
    }

    public void setPromotionProductId(String promotionProductId) {
        this.promotionProductId = promotionProductId;
    }

    public String getPromotionSubProductId() {
        return promotionSubProductId;
    }

    public void setPromotionSubProductId(String promotionSubProductId) {
        this.promotionSubProductId = promotionSubProductId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getComboCount() {
        return comboCount;
    }

    public void setComboCount(String comboCount) {
        this.comboCount = comboCount;
    }

    public PromotionProduct getPromotionProduct() {
        return promotionProduct;
    }

    public void setPromotionProduct(PromotionProduct promotionProduct) {
        this.promotionProduct = promotionProduct;
    }


}
