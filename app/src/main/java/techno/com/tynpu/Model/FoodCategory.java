package techno.com.tynpu.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodCategory {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("restaurent_name")
    @Expose
    private String restaurentName;
    @SerializedName("food_category_name")
    @Expose
    private String foodCategoryName;
    @SerializedName("food_category_price")
    @Expose
    private String foodCategoryPrice;
    @SerializedName("food_category_description")
    @Expose
    private String foodCategoryDescription;
    @SerializedName("food_category_type")
    @Expose
    private String foodCategoryType;
    @SerializedName("food_category_image")
    @Expose
    private String foodCategoryImage;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("food_category_product")
    @Expose
    private List<FoodCategoryProduct> foodCategoryProduct = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurentName() {
        return restaurentName;
    }

    public void setRestaurentName(String restaurentName) {
        this.restaurentName = restaurentName;
    }

    public String getFoodCategoryName() {
        return foodCategoryName;
    }

    public void setFoodCategoryName(String foodCategoryName) {
        this.foodCategoryName = foodCategoryName;
    }

    public String getFoodCategoryPrice() {
        return foodCategoryPrice;
    }

    public void setFoodCategoryPrice(String foodCategoryPrice) {
        this.foodCategoryPrice = foodCategoryPrice;
    }

    public String getFoodCategoryDescription() {
        return foodCategoryDescription;
    }

    public void setFoodCategoryDescription(String foodCategoryDescription) {
        this.foodCategoryDescription = foodCategoryDescription;
    }

    public String getFoodCategoryType() {
        return foodCategoryType;
    }

    public void setFoodCategoryType(String foodCategoryType) {
        this.foodCategoryType = foodCategoryType;
    }

    public String getFoodCategoryImage() {
        return foodCategoryImage;
    }

    public void setFoodCategoryImage(String foodCategoryImage) {
        this.foodCategoryImage = foodCategoryImage;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public List<FoodCategoryProduct> getFoodCategoryProduct() {
        return foodCategoryProduct;
    }

    public void setFoodCategoryProduct(List<FoodCategoryProduct> foodCategoryProduct) {
        this.foodCategoryProduct = foodCategoryProduct;
    }

}
