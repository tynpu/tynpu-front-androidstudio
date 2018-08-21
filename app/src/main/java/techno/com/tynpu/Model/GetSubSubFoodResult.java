package techno.com.tynpu.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user1 on 1/25/2018.
 */

public class GetSubSubFoodResult {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("food_category_product_name")
    @Expose
    private String foodCategoryProductName;
    @SerializedName("food_category_sub_product_name")
    @Expose
    private String foodCategorySubProductName;
    @SerializedName("food_category_sub_product_price")
    @Expose
    private String foodCategorySubProductPrice;
    @SerializedName("food_category_sub_product_description")
    @Expose
    private String foodCategorySubProductDescription;
    @SerializedName("food_category_sub_product_image")
    @Expose
    private String foodCategorySubProductImage;
    @SerializedName("created_date")
    @Expose
    private String createdDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoodCategoryProductName() {
        return foodCategoryProductName;
    }

    public void setFoodCategoryProductName(String foodCategoryProductName) {
        this.foodCategoryProductName = foodCategoryProductName;
    }

    public String getFoodCategorySubProductName() {
        return foodCategorySubProductName;
    }

    public void setFoodCategorySubProductName(String foodCategorySubProductName) {
        this.foodCategorySubProductName = foodCategorySubProductName;
    }

    public String getFoodCategorySubProductPrice() {
        return foodCategorySubProductPrice;
    }

    public void setFoodCategorySubProductPrice(String foodCategorySubProductPrice) {
        this.foodCategorySubProductPrice = foodCategorySubProductPrice;
    }

    public String getFoodCategorySubProductDescription() {
        return foodCategorySubProductDescription;
    }

    public void setFoodCategorySubProductDescription(String foodCategorySubProductDescription) {
        this.foodCategorySubProductDescription = foodCategorySubProductDescription;
    }

    public String getFoodCategorySubProductImage() {
        return foodCategorySubProductImage;
    }

    public void setFoodCategorySubProductImage(String foodCategorySubProductImage) {
        this.foodCategorySubProductImage = foodCategorySubProductImage;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
