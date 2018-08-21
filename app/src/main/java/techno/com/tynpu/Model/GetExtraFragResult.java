package techno.com.tynpu.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user1 on 1/30/2018.
 */

public class GetExtraFragResult {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("restaurant_id")
    @Expose
    private String restaurantId;
    @SerializedName("food_category_id")
    @Expose
    private String foodCategoryId;
    @SerializedName("food_category_product_id")
    @Expose
    private String foodCategoryProductId;
    @SerializedName("food_category_sub_product_id")
    @Expose
    private String foodCategorySubProductId;
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
    @SerializedName("food_category_product")
    @Expose
    private FoodCategoryProduct foodCategoryProduct;

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

    public String getFoodCategoryId() {
        return foodCategoryId;
    }

    public void setFoodCategoryId(String foodCategoryId) {
        this.foodCategoryId = foodCategoryId;
    }

    public String getFoodCategoryProductId() {
        return foodCategoryProductId;
    }

    public void setFoodCategoryProductId(String foodCategoryProductId) {
        this.foodCategoryProductId = foodCategoryProductId;
    }

    public String getFoodCategorySubProductId() {
        return foodCategorySubProductId;
    }

    public void setFoodCategorySubProductId(String foodCategorySubProductId) {
        this.foodCategorySubProductId = foodCategorySubProductId;
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

    public FoodCategoryProduct getFoodCategoryProduct() {
        return foodCategoryProduct;
    }

    public void setFoodCategoryProduct(FoodCategoryProduct foodCategoryProduct) {
        this.foodCategoryProduct = foodCategoryProduct;
    }

}
