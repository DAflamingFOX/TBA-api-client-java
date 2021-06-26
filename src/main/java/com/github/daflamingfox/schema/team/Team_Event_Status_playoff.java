package com.github.daflamingfox.schema.team;

import com.github.daflamingfox.schema.other.WLT_Record;
import com.google.gson.annotations.SerializedName;

public class Team_Event_Status_playoff {
    
    @SerializedName("description")
    public String description;
    
    @SerializedName("level")
    public String level;
    
    @SerializedName("current_level_record")
    public WLT_Record current_level_record;
    
    @SerializedName("record")
    public WLT_Record record;
    
    @SerializedName("status")
    public String status;
}
