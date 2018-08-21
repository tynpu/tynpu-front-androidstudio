package techno.com.tynpu.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import techno.com.tynpu.Model.GetComment_Result;

public class GetComment_Response {
    @SerializedName("result")
    @Expose
    private List<GetComment_Result> result = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    public List<GetComment_Result> getResult() {
        return result;
    }

    public void setResult(List<GetComment_Result> result) {
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
