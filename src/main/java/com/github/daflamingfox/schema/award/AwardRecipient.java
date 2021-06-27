package com.github.daflamingfox.schema.award;

import com.google.gson.annotations.SerializedName;

public class AwardRecipient {
    
    @SerializedName("team_key")
    public String teamKey;
    
    @SerializedName("awaredee")
    public String awaredee;
}
