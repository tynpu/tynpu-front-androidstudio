package techno.com.tynpu.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import techno.com.tynpu.Model.GetSubFoodDetailResult;

/**
 * Created by user1 on 1/23/2018.
 */

public class GetSubFoodDetailResponse {
    @SerializedName("result")
    @Expose
    private List<GetSubFoodDetailResult> result = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    public List<GetSubFoodDetailResult> getResult() {
        return result;
    }

    public void setResult(List<GetSubFoodDetailResult> result) {
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
