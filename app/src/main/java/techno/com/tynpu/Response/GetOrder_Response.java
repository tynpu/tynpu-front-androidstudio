package techno.com.tynpu.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import techno.com.tynpu.Model.GetOrder_Result;

/**
 * Created by user1 on 3/20/2018.
 */

public class GetOrder_Response {

    @SerializedName("result")
    @Expose
    private List<GetOrder_Result> result = null;
    @SerializedName("final_price")
    @Expose
    private Integer finalPrice;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    public List<GetOrder_Result> getResult() {
        return result;
    }

    public void setResult(List<GetOrder_Result> result) {
        this.result = result;
    }

    public Integer getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Integer finalPrice) {
        this.finalPrice = finalPrice;
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
