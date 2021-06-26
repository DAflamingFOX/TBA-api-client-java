package com.github.daflamingfox.schema.team;

import com.google.gson.annotations.SerializedName;

public class Team_Event_Status {
    
    @SerializedName("qual")
    public Team_Event_Status_rank qual;
    
    @SerializedName("alliance")
    public Team_Event_Status_alliance alliance;
    
    @SerializedName("playoff")
    public Team_Event_Status_playoff playoff;
    
    @SerializedName("aliance_status_str")
    public String allianceStatusStr;
    
    @SerializedName("playoff_status_str")
    public String playoffStatusStr;
    
    @SerializedName("overall_status_str")
    public String overallStatusStr;
    
    @SerializedName("next_match_key")
    public String nextMatchKey;
    
    @SerializedName("last_match_key")
    public String lastMatchKey;

}
