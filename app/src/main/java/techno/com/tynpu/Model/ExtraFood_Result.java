package techno.com.tynpu.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExtraFood_Result {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("item_id")
    @Expose
    private String itemId;
    @SerializedName("sub_item_id")
    @Expose
    private String subItemId;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("cart_id")
    @Expose
    private String cartId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("act_status")
    @Expose
    private String actStatus;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("food_category_type")
    @Expose
    private String foodCategoryType;
    @SerializedName("food_category_product_price")
    @Expose
    private String foodCategoryProductPrice;
    @SerializedName("food_category_product_name")
    @Expose
    private String foodCategoryProductName;
    @SerializedName("food_category_product_image")
    @Expose
    private String foodCategoryProductImage;
    @SerializedName("combo_count")
    @Expose
    private Integer comboCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getSubItemId() {
        return subItemId;
    }

    public void setSubItemId(String subItemId) {
        this.subItemId = subItemId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActStatus() {
        return actStatus;
    }

    public void setActStatus(String actStatus) {
        this.actStatus = actStatus;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getFoodCategoryType() {
        return foodCategoryType;
    }

    public void setFoodCategoryType(String foodCategoryType) {
        this.foodCategoryType = foodCategoryType;
    }

    public String getFoodCategoryProductPrice() {
        return foodCategoryProductPrice;
    }

    public void setFoodCategoryProductPrice(String foodCategoryProductPrice) {
        this.foodCategoryProductPrice = foodCategoryProductPrice;
    }

    public String getFoodCategoryProductName() {
        return foodCategoryProductName;
    }

    public void setFoodCategoryProductName(String foodCategoryProductName) {
        this.foodCategoryProductName = foodCategoryProductName;
    }

    public String getFoodCategoryProductImage() {
        return foodCategoryProductImage;
    }

    public void setFoodCategoryProductImage(String foodCategoryProductImage) {
        this.foodCategoryProductImage = foodCategoryProductImage;
    }

    public Integer getComboCount() {
        return comboCount;
    }

    public void setComboCount(Integer comboCount) {
        this.comboCount = comboCount;
    }
}
