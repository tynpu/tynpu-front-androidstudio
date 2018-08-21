package techno.com.tynpu.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import techno.com.tynpu.Model.Promotion_Base_Result;

/**
 * Created by user1 on 2/21/2018.
 */

public class Promotion_Base_Resonse {
    @SerializedName("result")
    @Expose
    private List<Promotion_Base_Result> result = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Promotion_Base_Result> getResult() {
        return result;
    }

    public void setResult(List<Promotion_Base_Result> result) {
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
