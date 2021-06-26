package com.github.daflamingfox.schema.district;

import com.google.gson.annotations.SerializedName;

public class District_List {
    
    @SerializedName("abbreviation")
    public String abbreviation;
    
    @SerializedName("display_name")
    public String displayName;
    
    @SerializedName("key")
    public String key;
    
    @SerializedName("year")
    public int year;
}
