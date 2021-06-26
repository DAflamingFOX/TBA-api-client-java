package com.github.daflamingfox.schema.other;

import com.google.gson.annotations.SerializedName;

public class WLT_Record {
    
    @SerializedName("losses")
    public int losses;
    
    @SerializedName("wins")
    public int wins;
    
    @SerializedName("ties")
    public int ties;
}
