package techno.com.tynpu.Connection;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by user1 on 1/15/2018.
 */

public interface PostInterface {
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% SignUp %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    @POST("TYNPU/webservice/signup")
    Call<ResponseBody> signup(@Query("firstname") String firstname,
                              @Query("lastname") String lastname,
                              @Query("email") String email,
                              @Query("mobile") String mobile,
                              @Query("password") String password,
                              @Query("lat") String lat,
                              @Query("lon") String lon);


//http://mobileappdevelop.co/TYNPU/webservice/signup?firstname=lorence&username=er_lorence&password=123&mobile=8965875455&email=lorence111@gmail.com&lat=22.123456&lon=75.123456&register_id=123&ios_register_id=321
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Login %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    @POST("TYNPU/webservice/login")
    Call<ResponseBody> Login(@Query("email") String email,
                             @Query("password") String password,
                             @Query("lat") String lat,
                             @Query("lon") String lon);

    //http://mobileappdevelop.co/TYNPU/webservice/login?username=vijay&password=123456

    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  SOCiAl Login %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    @GET("TYNPU/webservice/social_login")
    Call<ResponseBody> SocialLogin(@Query("social_id") String socialIdString,
                                   @Query("email") String emailString,
                                   @Query("name") String usernameString,
                                   @Query("mobile") String mobileString,
                                   @Query("image") String imageUrlString,
                                   @Query("lat") String lat,
                                   @Query("lon") String lon);
//http://mobileappdevelop.co/TYNPU/webservice/social_login?social_id=123&name=jiten&username=er_jiten&password=123&mobile=8965875455&email=jiten@gmail.com&lat=22.123456&lon=75.123456&register_id=123&ios_register_id=321&image=https://shopinhome.com/assets/images/user_img53518.png

    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  EDIT PROFILE %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    @Multipart
    @POST("TYNPU/webservice/user_update")
    Call<ResponseBody> EditProfile(@Query("user_id") String user_id,
                                   @Query("firstname") String name,
                                   @Query("mobile") String mobile,
                                   @Query("email") String email,
                                   @Query("date_of_birth") String date_of_birth,
                                   @Query("gender") String gender,
                                   @Part MultipartBody.Part body);
    //http://mobileappdevelop.co/TYNPU/webservice/user_update?user_id=68&name=kapilsthapak&mobile=8085363240&image=1.png
    //mobileappdevelop.co/TYNPU/webservice/user_update?user_id=68&name=kapilsthapak&mobile=8085363240&image=1.png&email=q@gmail.com&date_of_birth=10/03/1992&gender=Male

    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%   GET PROFILE %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    @GET("TYNPU/webservice/get_profile")
    Call<ResponseBody> GetProfile(@Query("user_id") String user_id);
    //http://mobileappdevelop.co/TYNPU/webservice/get_profile?user_id=67

//  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  Forget Password OTP %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    @POST("TYNPU/webservice/forgot_password")
    Call<ResponseBody> SendOTPForget(@Query("email") String email);
    //http://mobileappdevelop.co/WORKSPACE1/Lo_Taxi/webservice/forgot_password?email=kapil.technorizen@gmail.com
    //http://mobileappdevelop.co/TYNPU/webservice/forgot_password?email=lorence.technorizen@gmail.com

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  Change PAssword %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    @POST("TYNPU/webservice/set_new_password")
    Call<ResponseBody> ChangePassword(@Query("user_id") String user_id,
                                      @Query("otp_code") String otp_code,
                                      @Query("new_password") String new_password);

    //http://mobileappdevelop.co/TYNPU/webservice/set_new_password?user_id=59&otp_code=4534636&new_password=123456

    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  GET ALL REstrorent %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    @GET("TYNPU/webservice/all_restuarents")
    Call<ResponseBody> ALLRestro(@Query("lat") String lat,
                                 @Query("lon") String lon);
    //http://mobileappdevelop.co/TYNPU/webservice/all_restuarents
    //http://mobileappdevelop.co/TYNPU/webservice/all_restuarents?lat=22.7533&lon=75.8937

    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  GET RESTRO DETAIL %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    @GET("TYNPU/webservice/restaurant_details")
    Call<ResponseBody> RestroDetail(@Query("restaurant_id") String restaurant_id);

    //http://mobileappdevelop.co/TYNPU/webservice/restaurant_details?restaurant_id=1
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  GET All Foood Cat %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    @GET("TYNPU/webservice/all_food_category")
    Call<ResponseBody> AllFoodDetail(@Query("restaurant_id") String restaurant_id);
    //http://mobileappdevelop.co/TYNPU/webservice/all_food_category?restaurant_id=2

    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  GET All SUB Foood Cat %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    @GET("TYNPU/webservice/all_food_category_product")
    Call<ResponseBody> AllSubProduct(@Query("food_category_id") String food_category_id);
    //http://mobileappdevelop.co/TYNPU/webservice/all_food_category_product?food_category_id=1

    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  GET All SUB SUB SUB Foood Cat %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    @GET("TYNPU/webservice/all_food_category_sub_product")
    Call<ResponseBody> SubSubFoodProduct(@Query("food_category_product_id") String food_category_product_id);
    //http://mobileappdevelop.co/TYNPU/webservice/all_food_category_sub_product?food_category_product_id=3

//    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  Add to Cart %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//    @GET("TYNPU/webservice/add_to_cart")
//    Call<ResponseBody> Addtocart(@Query("user_id") String user_id,
//                                 @Query("item_id") String item_id);
//
//    //http://mobileappdevelop.co/TYNPU/webservice/add_to_cart?user_id=2&item_id=3
//
//    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  GET All SUB SUB SUB Foood Cat %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//    @GET("TYNPU/webservice/get_cart")
//    Call<ResponseBody> GetCart(@Query("user_id") String user_id);
//    //http://mobileappdevelop.co/TYNPU/webservice/get_cart?user_id=2
////%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  Update cproduct %%%%%%%%%%%%%%%%%%%%%%%%%
//
//    @GET("TYNPU/webservice/update_cart_item")
//    Call<ResponseBody> UpdateCart(@Query("cart_id") String cart_id);
//    //http://mobileappdevelop.co/TYNPU/webservice/update_cart_item?cart_id=75
//

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  Change PAssword %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    @POST("TYNPU/webservice/change_password")
    Call<ResponseBody> CreateNewPassword(@Query("user_id") String user_id,
                                         @Query("old_password") String old_password,
                                         @Query("new_password") String new_password);
    //http://mobileappdevelop.co/TYNPU/webservice/change_password?user_id=56&old_password=0&new_password=1234


//    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  Extra cproduct %%%%%%%%%%%%%%%%%%%%%%%%%
//
//    @GET("TYNPU/webservice/get_final_cart")
//    Call<ResponseBody>ExtraCart(@Query("user_id") String user_id);
//    //http://mobileappdevelop.co/TYNPU/webservice/get_final_cart?user_id=67
//
//
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  Send Quantity %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    @POST("TYNPU/webservice/add_to_cart")
    Call<ResponseBody> Sendquantity(@Query("user_id") String user_id,
                                    @Query("item_id") String item_id,
                                    @Query("sub_item_id") String sub_item_id,
                                    @Query("quantity") String quantity,
                                    @Query("type") String type);

    //http://mobileappdevelop.co/TYNPU/webservice/add_to_cart?user_id=1&item_id=18&sub_item_id=23&quantity=2

    //uper wali new hai

//    @POST("TYNPU/webservice/add_to_cart")
//    Call<ResponseBody> Sendquantity(@Query("user_id") String user_id,
//                                    @Query("item_id") String item_id,
//                                    @Query("quantity") String quantity,
//                                    @Query("type") String type);
    //type=Food
    //http://mobileappdevelop.co/TYNPU/webservice/update_cart?user_id=67&item_id=8&quantity=5
    //http://mobileappdevelop.co/TYNPU/webservice/add_to_cart?user_id=1&item_id=2&quantity=5


    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  Product active or deactivce%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    @POST("TYNPU/webservice/book_restaurent_items")
    Call<ResponseBody> ProductUpdate(@Query("restaurant_id") String restaurant_id,
                                     @Query("food_category_id") String food_category_id,
                                     @Query("food_category_product_id") String food_category_product_id,
                                     @Query("food_category_sub_product_id") String food_category_sub_product_id,
                                     @Query("user_id") String user_id,
                                     @Query("book_status") String book_status);
    //http://mobileappdevelop.co/TYNPU/webservice/book_restaurent_items?restaurant_id=1&food_category_id=2&food_category_product_id=3&food_category_sub_product_id=4&user_id=5&book_status=Active

    //**************************  Extra Fragment *******************************************************

    @GET("TYNPU/webservice/get_book_restaurent_items")
    Call<ResponseBody> ExtraFrag(@Query("user_id") String user_id,
                                 @Query("restaurant_id") String restaurant_id,
                                 @Query("food_category_id") String food_category_id,
                                 @Query("food_category_product_id") String food_category_product_id);
//http://mobileappdevelop.co/TYNPU/webservice/get_book_restaurent_items?user_id=85&restaurant_id=9&food_category_id=9&food_category_product_id=10
//http://mobileappdevelop.co/TYNPU/webservice/get_book_restaurent_items?user_id=85&restaurant_id=9&food_category_id=9&food_category_product_id=10


    //**************************  getCart Fragment *******************************************************
    @GET("TYNPU/webservice/get_cart")
    Call<ResponseBody> Getcart(@Query("user_id") String user_id,
                               @Query("type") String type);
//http://mobileappdevelop.co/TYNPU/webservice/get_cart?user_id=79

    //**************************  getCart Fragment *******************************************************
    @GET("TYNPU/webservice/all_promotions")
    Call<ResponseBody> Promotion();
    //http://mobileappdevelop.co/TYNPU/webservice/all_promotions

    //***********************  Get Avaialabel Promotion  ***************************************************************
    @GET("TYNPU/webservice/all_promotion_product")
    Call<ResponseBody> GetAvailablePromotion(@Query("promotion_id") String promotion_id);
    //http://mobileappdevelop.co/TYNPU/webservice/all_promotion_product?promotion_id=22

    //**********************  Base Promotion *****************************************************************************

    @GET("TYNPU/webservice/all_promotion_product_details")
    Call<ResponseBody> BasePromotion(@Query("promotion_id") String promotion_id,
                                     @Query("product_id") String product_id);
    //http://mobileappdevelop.co/TYNPU/webservice/all_promotion_product_details?promotion_id=22&product_id=17


    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  Product active or deactivce%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    @POST("TYNPU/webservice/book_promotion_restaurent_items")
    Call<ResponseBody> PromotionBase(@Query("restaurant_id") String restaurant_id,
                                     @Query("promotion_id") String promotion_id,
                                     @Query("promotion_product_id") String promotion_product_id,
                                     @Query("promotion_sub_product_id") String promotion_sub_product_id,
                                     @Query("user_id") String user_id,
                                     @Query("book_status") String book_status);
    //http://mobileappdevelop.co/TYNPU/webservice/book_promotion_restaurent_items?restaurant_id=1&promotion_id=2&promotion_product_id=3&promotion_sub_product_id=4&user_id=5&book_status=Active

//*******************  Extra Promotion Call  ****************************************************************************************

    @GET("TYNPU/webservice/get_book_restaurent_items")
    Call<ResponseBody> ExtraPromotion(@Query("user_id") String user_id,
                                      @Query("restaurant_id") String restaurant_id,
                                      @Query("promotion_id") String promotion_id,
                                      @Query("promotion_product_id") String promotion_product_id);
//http://mobileappdevelop.co/TYNPU/webservice/get_book_restaurent_items?user_id=5&restaurant_id=1&promotion_id=2&promotion_product_id=13
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  GET Promotion selected item  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


    @GET("TYNPU/webservice/get_book_promotion_restaurent_items")
    Call<ResponseBody> GetExtraPromotion(@Query("user_id") String user_id,
                                         @Query("restaurant_id") String restaurant_id,
                                         @Query("promotion_id") String promotion_id,
                                         @Query("promotion_product_id") String promotion_product_id);
    //http://mobileappdevelop.co/TYNPU/webservice/get_book_promotion_restaurent_items?user_id=79&restaurant_id=10&promotion_id=22&promotion_product_id=17


    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  PALCE ORDER  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    @POST("TYNPU/webservice/place_order")
    Call<ResponseBody> PlaceOrder(@Query("user_id") String user_id,
                                  @Query("cart_id") String cart_id,
                                  @Query("delivery_type") String delivery_type,
                                  @Query("type") String type);
    //http://mobileappdevelop.co/TYNPU/webservice/place_order?user_id=79&cart_id=206&delivery_type=Promotion

    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  GET ORDER  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    @GET("TYNPU/webservice/get_order")
    Call<ResponseBody> GetOrder(@Query("user_id") String user_id);

    //    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  Cart Count  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    @GET("TYNPU/webservice/get_cart_count")
    Call<ResponseBody> CartCount(@Query("user_id") String user_id);

    //http://mobileappdevelop.co/TYNPU/webservice/get_cart_count?user_id=1

    //    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  Add Comment  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    @POST("TYNPU/webservice/add_comment")
    Call<ResponseBody> Addcomment(@Query("user_id") String user_id,
                                  @Query("comment") String comment);

    //mobileappdevelop.co/TYNPU/webservice/add_comment?user_id=1&comment=dfsfs
    //    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  Comment List %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    @POST("TYNPU/webservice/comment_lists")
    Call<ResponseBody> CommentList();

    //http://mobileappdevelop.co/TYNPU/webservice/comment_lists

}
