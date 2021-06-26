package com.github.daflamingfox.schema.event;

import com.github.daflamingfox.schema.district.District_List;
import com.github.daflamingfox.schema.other.Webcast;
import com.google.gson.annotations.SerializedName;

public class Event {
    
    @SerializedName("key")
    public String key;

    @SerializedName("name")
    public String name;

    @SerializedName("event_code")
    public String eventCode;

    @SerializedName("event_type")
    public int eventType;

    @SerializedName("ditrict")
    public District_List district;

    @SerializedName("city")
    public String city;

    @SerializedName("state_prov")
    public String stateProv;

    @SerializedName("country")
    public String country;

    @SerializedName("start_date")
    public String startDate;

    @SerializedName("end_date")
    public String endDate;

    @SerializedName("year")
    public int year;

    @SerializedName("short_name")
    public String shortName;

    @SerializedName("event_type_string")
    public String eventTypeString;

    @SerializedName("week")
    public int week;

    @SerializedName("address")
    public String address;

    @SerializedName("postal_code")
    public String postalCode;

    @SerializedName("gmaps_place_id")
    public String gmapsPlaceId;

    @SerializedName("gmaps_url")
    public String gmapsUrl;

    @SerializedName("lat")
    public double latitude;

    @SerializedName("lng")
    public double longitude;

    @SerializedName("location_name")
    public String locationName;

    @SerializedName("timezone")
    public String timezone;

    @SerializedName("website")
    public String website;

    @SerializedName("first_event_id")
    public String firstEventId;

    @SerializedName("first_event_code")
    public String firstEventCode;

    @SerializedName("webcasts")
    public Webcast[] webcasts;

    @SerializedName("division_keys")
    public String[] divisionKeys;

    @SerializedName("parent_event_key")
    public String parentEventKey;

    @SerializedName("playoff_type")
    public int playoffType;

    @SerializedName("playoff_type_string")
    public String playoffTypeString;

    public Event_Simple toSimple() {
        return new Event_Simple(this);
    }
}
