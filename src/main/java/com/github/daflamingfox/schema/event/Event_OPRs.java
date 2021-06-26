package com.github.daflamingfox.schema.event;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class Event_OPRs {
    // offense power rating
    @SerializedName("oprs")
    public Map<String, Double> oprs;
    
    // defense power rating
    @SerializedName("dprs")
    public Map<String, Double> dprs;
    
    // calculates contribution to win
    @SerializedName("ccwms")
    public Map<String, Double> ccwms;
    
}
