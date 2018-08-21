package techno.com.tynpu.Other;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import techno.com.tynpu.Connection.PostInterface;

/**
 * Created by user1 on 11/16/2017.
 */

public class AppConfig {
    private static Retrofit retrofit = null;
    private static PostInterface postInterface = null;


    private static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl("http://mobileappdevelop.co/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static PostInterface PostInterface() {
        if (postInterface == null) {
            postInterface = AppConfig.getClient().create(PostInterface.class);
        }
        return postInterface;
    }




}
