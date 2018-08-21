package techno.com.tynpu.Model;

import java.util.List;

/**
 * Created by user1 on 3/23/2018.
 */

public class DataHolderActive {
    String ID,Status;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public static List<Promotion_Base_Result> activated;

    public static List<Promotion_Base_Result> getActivated() {
        return activated;
    }

    public static void setActivated(List<Promotion_Base_Result> activated) {
        DataHolderActive.activated = activated;
    }
}
