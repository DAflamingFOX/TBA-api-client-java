package com.github.daflamingfox.schema.match;

import com.google.gson.annotations.SerializedName;

public class Match {
    
    @SerializedName("key")
    public String key;
    
    @SerializedName("comp_level")
    public String compLevel;
    
    @SerializedName("set_number")
    public int setNumber;
    
    @SerializedName("match_number")
    public int matchNumber;
    
    @SerializedName("alliances")
    public Alliances alliances;
    
    @SerializedName("winning_alliance")
    public String winningAlliance;
    
    @SerializedName("event_key")
    public String eventKey;
    
    @SerializedName("time")
    public long time;
    
    @SerializedName("actual_time")
    public long actualTime;
    
    @SerializedName("predicted_time")
    public long predictedTime;
    
    @SerializedName("post_result_time")
    public long postResultTime;
    
    @SerializedName("videos")
    public Video[] videos;
    
    public Match_Simple toSimple() {
        return new Match_Simple(this);
    }


    public class Alliances {
        
        @SerializedName("red")
        public Match_alliance red;
        
        @SerializedName("blue")
        public Match_alliance blue;
    }

    public class Video {
        
        @SerializedName("type")
        public String type;
        
        @SerializedName("key")
        public String key;
    }

}
