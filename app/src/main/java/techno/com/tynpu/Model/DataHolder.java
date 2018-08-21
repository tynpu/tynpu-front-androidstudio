package techno.com.tynpu.Model;

import java.util.List;

/**
 * Created by user1 on 3/16/2018.
 */

public class DataHolder {

    public static List<GetCart_Result> getSummaryResults;

    public static List<GetCart_Result> getGetSummaryResults() {
        return getSummaryResults;
    }

    public static void setGetSummaryResults(List<GetCart_Result> getSummaryResults) {
        DataHolder.getSummaryResults = getSummaryResults;
    }
}
