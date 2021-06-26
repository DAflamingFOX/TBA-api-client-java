package com.github.daflamingfox.schema.award;

import com.google.gson.annotations.SerializedName;

public class Award {
    
    @SerializedName("name")
    public String name;
    
    @SerializedName("award_type")
    public int awardType;
    
    @SerializedName("event_key")
    public String eventKey;
    
    @SerializedName("recipient_list")
    public Award_Recipient[] recipientList;
    
    @SerializedName("year")
    public int year;
}
