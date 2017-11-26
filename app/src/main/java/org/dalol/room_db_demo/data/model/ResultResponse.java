package org.dalol.room_db_demo.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by filippo on 25/11/2017.
 */

public class ResultResponse {

    @Expose
    @SerializedName("response")
    private Integer response;

    public Integer getResponse() {
        return response;
    }
}
