package techno.com.tynpu.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import techno.com.tynpu.Model.GetRestoDetailResult;

/**
 * Created by user1 on 1/23/2018.
 */

public class GetRestroDetalResponse {
    @SerializedName("result")
    @Expose
    private GetRestoDetailResult result;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    public GetRestoDetailResult getResult() {
        return result;
    }

    public void setResult(GetRestoDetailResult result) {
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
