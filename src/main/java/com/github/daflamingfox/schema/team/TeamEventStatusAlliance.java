package com.github.daflamingfox.schema.team;

import com.google.gson.annotations.SerializedName;

public class TeamEventStatusAlliance {
    
    @SerializedName("name")
    public String name;
    
    @SerializedName("number")
    public int number;
    
    @SerializedName("backup")
    public TeamEventStatusAllianceBackup backup;
    
    @SerializedName("pick")
    public int pick;

}
