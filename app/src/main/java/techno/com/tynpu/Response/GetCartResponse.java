package techno.com.tynpu.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import techno.com.tynpu.Model.GetCartResult;
import techno.com.tynpu.Model.GetCart_Result;

/**
 * Created by user1 on 1/25/2018.
 */

public class GetCartResponse {
    @SerializedName("cart_count")
    @Expose
    private Integer cartCount;
    @SerializedName("result")
    @Expose
    private List<GetCart_Result> result = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getCartCount() {
        return cartCount;
    }

    public void setCartCount(Integer cartCount) {
        this.cartCount = cartCount;
    }

    public List<GetCart_Result> getResult() {
        return result;
    }

    public void setResult(List<GetCart_Result> result) {
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
