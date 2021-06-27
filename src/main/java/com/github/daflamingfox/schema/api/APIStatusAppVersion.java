package com.github.daflamingfox.schema.api;

import com.github.daflamingfox.util.ObjectUtils;
import com.google.gson.annotations.SerializedName;

public class API_Status_App_Version {

    @SerializedName("min_app_version")
    public int minAppVersion;

    @SerializedName("latest_app_version")
    public int latestAppVersion;

    @Override
    public String toString() {
        return ObjectUtils.objToString(this);
    }
    
}