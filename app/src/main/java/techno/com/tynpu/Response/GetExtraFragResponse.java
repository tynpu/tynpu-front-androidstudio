package techno.com.tynpu.Response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import techno.com.tynpu.Model.GetExtraFragResult;

/**
 * Created by user1 on 1/30/2018.
 */

public class GetExtraFragResponse {
    @SerializedName("result")
    @Expose
    private List<GetExtraFragResult> result = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    public List<GetExtraFragResult> getResult() {
        return result;
    }

    public void setResult(List<GetExtraFragResult> result) {
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
