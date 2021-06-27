package com.github.daflamingfox.schema.zebra;

import com.google.gson.annotations.SerializedName;

public class Zebra {
    
    @SerializedName("key")
    public String key;
    
    @SerializedName("times")
    public double[] times;
    
    @SerializedName("alliances")
    public Alliances alliances;

    public class Alliances {
        
        @SerializedName("red")
        public ZebraTeam[] red;
        
        @SerializedName("blue")
        public ZebraTeam[] blue;
    }
}
