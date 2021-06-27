package com.github.daflamingfox.schema.team;

import com.github.daflamingfox.schema.other.WLTRecord;
import com.google.gson.annotations.SerializedName;

public class TeamEventStatusRank {
    
    @SerializedName("num_teams")
    public int numTeams;
    
    @SerializedName("ranking")
    public Ranking rank;
    
    @SerializedName("sort_order_info")
    public SortOrders[] sortOrderInfo;
    
    @SerializedName("status")
    public String status;

    public class SortOrders {
        
        @SerializedName("precision")
        public int precision;
        
        @SerializedName("name")
        public String name;
    }

    public class Ranking {
        
        @SerializedName("matches_played")
        public int matchesPlayed;
        
        @SerializedName("qual_average")
        public double qualAverage;
        
        @SerializedName("sort_orders")
        public double[] sortOrders;
        
        @SerializedName("record")
        public WLTRecord record;
        
        @SerializedName("rank")
        public int rank;
        
        @SerializedName("dq")
        public int dq;
        
        @SerializedName("team_key")
        public String teamKey;
    }
}
