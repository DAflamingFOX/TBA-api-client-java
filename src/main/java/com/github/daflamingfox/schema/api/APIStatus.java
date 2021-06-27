package com.github.daflamingfox.schema.api;

import com.github.daflamingfox.util.ObjectUtils;
import com.google.gson.annotations.SerializedName;

public class APIStatus {

    @SerializedName("current_season")
    public int currentSeason;

    @SerializedName("max_season")
    public int maxSeason;

    @SerializedName("is_datafeed_down")
    public boolean isDatafeedDown;

    @SerializedName("down_events")
    public String[] downEvents;

    @SerializedName("ios")
    public API_Status_App_Version ios;
    
    @SerializedName("android")
    public API_Status_App_Version android;

    @Override
    public String toString() {
        return ObjectUtils.objToString(this);
    }
}
