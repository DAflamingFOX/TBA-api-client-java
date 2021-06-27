package com.github.daflamingfox.schema.team;

import com.google.gson.annotations.SerializedName;

public class TeamRobot {
    
    @SerializedName("year")
    public int year;

    @SerializedName("robot_name")
    public String robotName;

    @SerializedName("key")
    public String key;

    @SerializedName("team_key")
    public String teamKey;

}