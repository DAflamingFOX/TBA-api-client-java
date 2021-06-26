package com.github.daflamingfox.schema.team;

import com.google.gson.annotations.SerializedName;

public class Team_Event_Status_alliance {
    
    @SerializedName("name")
    public String name;
    
    @SerializedName("number")
    public int number;
    
    @SerializedName("backup")
    public Team_Event_Status_alliance_backup backup;
    
    @SerializedName("pick")
    public int pick;

}
