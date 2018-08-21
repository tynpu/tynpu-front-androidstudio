package techno.com.tynpu.Model;

import java.util.List;

/**
 * Created by user1 on 3/20/2018.
 */

public class DataHolderFood {
    public static List<GetCart_Result> getSummaryFood_results;

    public static List<GetCart_Result> getGetSummaryFood_results() {
        return getSummaryFood_results;
    }

    public static void setGetSummaryFood_results(List<GetCart_Result> getSummaryFood_results) {
        DataHolderFood.getSummaryFood_results = getSummaryFood_results;
    }
}
