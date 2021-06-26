package com.github.daflamingfox.schema.other;

import com.google.gson.annotations.SerializedName;

public class Elimination_Alliance {
    
    @SerializedName("name")
    public String name;
    
    @SerializedName("backup")
    public Backup backup;
    
    @SerializedName("declines")
    public String[] declines;
    
    @SerializedName("picks")
    public String[] picks;
    
    @SerializedName("status")
    public Status status;

    public class Backup {
        
        @SerializedName("in")
        public String in;
        
        @SerializedName("out")
        public String out;
    }

    public class Status {
        
        @SerializedName("playoff_average")
        public double playoffAverage;
        
        @SerializedName("level")
        public String level;
        
        @SerializedName("record")
        public WLT_Record record;
        
        @SerializedName("current_level_record")
        public WLT_Record currentLevelRecord;
        
        @SerializedName("status")
        public String status;
    }
}
