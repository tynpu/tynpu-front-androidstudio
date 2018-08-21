package techno.com.tynpu.Model;

import java.util.List;

public class DataHolderActiveFood {
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

    public static List<GetFooddetailResult> getActivated() {
        return activated;
    }

    public static void setActivated(List<GetFooddetailResult> activated) {
        DataHolderActiveFood.activated = activated;
    }

    public static List<GetFooddetailResult> activated;
}

