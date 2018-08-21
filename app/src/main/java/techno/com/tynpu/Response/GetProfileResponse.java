package techno.com.tynpu.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import techno.com.tynpu.Model.GetProfileResult;

/**
 * Created by user1 on 1/20/2018.
 */

public class GetProfileResponse {
    @SerializedName("result")
    @Expose
    private GetProfileResult result;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    public GetProfileResult getResult() {
        return result;
    }

    public void setResult(GetProfileResult result) {
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
