package techno.com.tynpu.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import techno.com.tynpu.Model.GetPromotionAvailbleResult;

/**
 * Created by user1 on 2/5/2018.
 */

public class GetPromotionAvailbleResopnse {
    @SerializedName("result")
    @Expose
    private List<GetPromotionAvailbleResult> result = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    public List<GetPromotionAvailbleResult> getResult() {
        return result;
    }

    public void setResult(List<GetPromotionAvailbleResult> result) {
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
