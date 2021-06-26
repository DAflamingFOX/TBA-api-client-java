package com.github.daflamingfox.schema.zebra;

import com.google.gson.annotations.SerializedName;

public class Zebra_team {
    
    @SerializedName("team_key")
    public String teamKey;
    
    @SerializedName("xs")
    public double[] xs;
    
    @SerializedName("ys")
    public double[] ys;
}
