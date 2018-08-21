package techno.com.tynpu.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import techno.com.tynpu.Model.GetSummaryFood_Result;

/**
 * Created by user1 on 3/20/2018.
 */

public class GetSummaryFood_Response {
    @SerializedName("user_address")
    @Expose
    private List<Object> userAddress = null;
    @SerializedName("cart_count")
    @Expose
    private Integer cartCount;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("result")
    @Expose
    private List<GetSummaryFood_Result> result = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Object> getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(List<Object> userAddress) {
        this.userAddress = userAddress;
    }

    public Integer getCartCount() {
        return cartCount;
    }

    public void setCartCount(Integer cartCount) {
        this.cartCount = cartCount;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<GetSummaryFood_Result> getResult() {
        return result;
    }

    public void setResult(List<GetSummaryFood_Result> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
