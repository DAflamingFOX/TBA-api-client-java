package com.github.daflamingfox.schema.match;

import com.google.gson.annotations.SerializedName;

public class Match_alliance {
    
    @SerializedName("score")
    public int score;
    
    @SerializedName("team_keys")
    public String[] teamKeys;
    
    @SerializedName("surrogate_team_keys")
    public String[] surrogateTeamKeys;
    
    @SerializedName("dq_team_keys")
    public String[] dqTeamKeys;
}
