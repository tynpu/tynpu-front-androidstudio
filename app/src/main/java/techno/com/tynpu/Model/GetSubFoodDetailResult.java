package techno.com.tynpu.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user1 on 1/23/2018.
 */

public class GetSubFoodDetailResult {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("restuarent_id")
    @Expose
    private String restuarentId;
    @SerializedName("food_category_name")
    @Expose
    private String foodCategoryName;
    @SerializedName("food_category_product_name")
    @Expose
    private String foodCategoryProductName;
    @SerializedName("food_category_product_price")
    @Expose
    private String foodCategoryProductPrice;
    @SerializedName("food_category_product_description")
    @Expose
    private String foodCategoryProductDescription;
    @SerializedName("food_category_product_image")
    @Expose
    private String foodCategoryProductImage;
    @SerializedName("food_category_product_type")
    @Expose
    private String foodCategoryProductType;
    @SerializedName("created_date")
    @Expose
    private String createdDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestuarentId() {
        return restuarentId;
    }

    public void setRestuarentId(String restuarentId) {
        this.restuarentId = restuarentId;
    }

    public String getFoodCategoryName() {
        return foodCategoryName;
    }

    public void setFoodCategoryName(String foodCategoryName) {
        this.foodCategoryName = foodCategoryName;
    }

    public String getFoodCategoryProductName() {
        return foodCategoryProductName;
    }

    public void setFoodCategoryProductName(String foodCategoryProductName) {
        this.foodCategoryProductName = foodCategoryProductName;
    }

    public String getFoodCategoryProductPrice() {
        return foodCategoryProductPrice;
    }

    public void setFoodCategoryProductPrice(String foodCategoryProductPrice) {
        this.foodCategoryProductPrice = foodCategoryProductPrice;
    }

    public String getFoodCategoryProductDescription() {
        return foodCategoryProductDescription;
    }

    public void setFoodCategoryProductDescription(String foodCategoryProductDescription) {
        this.foodCategoryProductDescription = foodCategoryProductDescription;
    }

    public String getFoodCategoryProductImage() {
        return foodCategoryProductImage;
    }

    public void setFoodCategoryProductImage(String foodCategoryProductImage) {
        this.foodCategoryProductImage = foodCategoryProductImage;
    }

    public String getFoodCategoryProductType() {
        return foodCategoryProductType;
    }

    public void setFoodCategoryProductType(String foodCategoryProductType) {
        this.foodCategoryProductType = foodCategoryProductType;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
