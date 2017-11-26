package org.dalol.room_db_demo.data.api;

import org.dalol.room_db_demo.data.model.ResultResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by filippo on 25/11/2017.
 */

public interface ApiSum {

    @FormUrlEncoded
    @POST("/user_info_handler.php")
    Call<ResultResponse> calculateSum(@Field("value1") int firstNumber, @Field("value2") int secondNumber);
}
