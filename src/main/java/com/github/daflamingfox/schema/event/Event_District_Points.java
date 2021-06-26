package com.github.daflamingfox.schema.event;

import java.util.Arrays;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class Event_District_Points {
    
    @SerializedName("points")
    public Map<String, TeamPoints> points;
    
    @SerializedName("tiebreakers")
    public Map<String, TeamTieBreakers> tiebreakers;

    public class TeamPoints {
        @SerializedName("total")
        public int total;
        
        @SerializedName("alliance_points")
        public int alliancePoints;
        
        @SerializedName("elim_points")
        public int elimPoints;
        
        @SerializedName("award_points")
        public int awardPoints;
        
        @SerializedName("qual_points")
        public int qualPoints;

    }
    
    public class TeamTieBreakers {
            
        @SerializedName("highest_qual_scores")
        public int[] highestQualScores;
        
        @SerializedName("qualWins")
        public int qualWins;
   
    }
}