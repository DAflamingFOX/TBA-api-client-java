package com.github.daflamingfox.schema.team;

import com.github.daflamingfox.schema.other.WLTRecord;
import com.google.gson.annotations.SerializedName;

public class TeamEventStatusPlayoff {
    
    @SerializedName("description")
    public String description;
    
    @SerializedName("level")
    public String level;
    
    @SerializedName("current_level_record")
    public WLTRecord current_level_record;
    
    @SerializedName("record")
    public WLTRecord record;
    
    @SerializedName("status")
    public String status;
}
