package com.github.daflamingfox.api;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import com.github.daflamingfox.schema.api.API_Status;
import com.github.daflamingfox.schema.district.District_Ranking;
import com.github.daflamingfox.schema.event.Event;
import com.github.daflamingfox.schema.event.Event_Simple;
import com.github.daflamingfox.schema.team.Team;
import com.github.daflamingfox.schema.team.Team_Event_Status;
import com.github.daflamingfox.schema.team.Team_Simple;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Request.Builder;

public class TBAApi {
    // Tokens & Token names
    public final String readAuthToken;
    public final String readAuthId = "X-TBA-Auth-Key";
    public final String lM = "Last-Modified";
    /*
    public String writeAuthToken;
    public final String writeAuthId = "X-TBA-Auth-Id";
    public String writeAuthSig;
    public final String writeAuthSigId = "X-TBA-Auth-Sig";
    */
   // Clients
   public final OkHttpClient okClient;
   public final Gson gson;
   public final Builder reqBuilder;
   // Classes
   public TBA TBA;
   public Lists lists;
   // Other
   public final String url = "https://www.thebluealliance.com/api/v3/";
   public String lMDate = "Fri,01 Jan 1990 00:00:00 GMT";
   
   public TBAApi(String readAuthToken) {
       this.readAuthToken = readAuthToken;
       this.okClient = new OkHttpClient();
       this.gson = new GsonBuilder().setPrettyPrinting().create();
       this.reqBuilder = new Request.Builder().addHeader(readAuthId, this.readAuthToken).addHeader(lM, lMDate);
       this.TBA = new TBA();
       this.lists = new Lists();
    }

    /**
     * Calls that expose TBA internals or status.
     */
    public class TBA {
        
        public API_Status getStatus() throws IOException {
            Request req = reqBuilder.url(url+"status").build();
            
            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);
            
            return gson.fromJson(rsp.body().string(), API_Status.class);
        }
    }
    /**
     * Calls that return a list of records.
     */
    public class Lists {
        
        public Team[] getTeamsByPageNumber(int pageNumber) throws IOException {
            Request req = reqBuilder.url(url+"teams/"+pageNumber).build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);
            
            return gson.fromJson(rsp.body().string(), Team[].class);
        }

        public Team_Simple[] getTeamsByPageNumberAsSimple(int pageNumber) throws IOException {
            Request req = reqBuilder.url(url+"teams/"+pageNumber+"/simple").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);
            
            return gson.fromJson(rsp.body().string(), Team_Simple[].class);
        }

        public String[] getTeamKeysByPageNumber(int pageNumber) throws IOException {
            Request req = reqBuilder.url(url+"teams/"+pageNumber+"/keys").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);
            
            return gson.fromJson(rsp.body().string(), String[].class);
        }

        public Team[] getTeamInYearByPageNumber(int year, int pageNumber) throws IOException {
            Request req = reqBuilder.url(url+"teams/"+year+"/"+pageNumber).build();
            
            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Team[].class);
        }

        public Team_Simple[] getTeamInYearByPageNumberAsSimple(int year, int pageNumber) throws IOException {
            Request req = reqBuilder.url(url+"teams/"+year+"/"+pageNumber+"/simple").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Team_Simple[].class);
        }

        public String[] getTeamKeysFromYearByPageNumber(int year, int pageNumber) throws IOException {
            Request req = reqBuilder.url(url+"teams/"+year+"/"+pageNumber+"keys").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), String[].class);
        }

        public Map<String, Team_Event_Status> getTeamEventStatusesInYear(String teamKey, int year) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/events/"+year+"/statuses").build();
            
            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            Type tesMapType = new TypeToken<Map<String, Team_Event_Status>>() {}.getType();
            return gson.fromJson(rsp.body().string(), tesMapType);
        }

        public Event[] getEventsInYear(int year) throws IOException {
            Request req = reqBuilder.url(url+"events/"+year).build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Event[].class);
        }

        public Event_Simple[] getEventsInYearAsSimple(int year) throws IOException {
            Request req = reqBuilder.url(url+"events/"+year+"/simple").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Event_Simple[].class);
        }

        public String[] getEventKeysInYear(int year) throws IOException {
            Request req = reqBuilder.url(url+"events/"+year+"/keys").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), String[].class);
        }
        
        public Team[] getTeamsInEvent(String eventKey) throws IOException {
            Request req = reqBuilder.url(url+"event/"+eventKey+"/teams").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Team[].class);
        }

        public Team_Simple[] getTeamsInEventAsSimple(String eventKey) throws IOException {
            Request req = reqBuilder.url(url+"event/"+eventKey+"/teams/simple").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Team_Simple[].class);
        }

        public Team[] getTeamKeysForEvent(String eventKey) throws IOException {
            Request req = reqBuilder.url(url+"event/"+eventKey+"/teams/keys").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Team[].class);
        }

        public Map<String, Team_Event_Status> getTeamEventStatusesInEvent(String eventKey) throws IOException {
            Request req = reqBuilder.url(url+"event/"+eventKey+"/teams/statuses").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            Type tesMapType = new TypeToken<Map<String, Team_Event_Status>>() {}.getType();
            return gson.fromJson(rsp.body().string(), tesMapType);
        }

        public Event[] getEventsInDistrict(String districtKey) throws IOException {
            Request req = reqBuilder.url(url+"district/"+districtKey+"/events").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Event[].class);
        }

        public Event_Simple[] getEventsInDistrictAsSimple(String districtKey) throws IOException {
            Request req = reqBuilder.url(url+"district/"+districtKey+"/events/simple").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Event_Simple[].class);
        }

        public String[] getEventKeysInDistrict(String districtKey) throws IOException {
            Request req = reqBuilder.url(url+"district/"+districtKey+"/events/keys").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), String[].class);
        }

        public Team[] getTeamsInDistrict(String districtKey) throws IOException {
            Request req = reqBuilder.url(url+"district/"+districtKey+"/teams").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Team[].class);
        }

        public Team_Simple[] getTeamsInDistrictAsSimple(String districtKey) throws IOException {
            Request req = reqBuilder.url(url+"district/"+districtKey+"/teams/simple").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Team_Simple[].class);
        }

        public String[] getTeamKeysInDistrict(String districtKey) throws IOException {
            Request req = reqBuilder.url(url+"district/"+districtKey+"/teams/keys").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), String[].class);
        }

        public District_Ranking[] getTeamDistrictRankingsInDistrict(String districtKey) throws IOException {
            Request req = reqBuilder.url(url+"district/"+districtKey+"/rankings").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), District_Ranking[].class);
        }

        
    } 
   
    /**
     * Calls that return team or team-specific information.
     */
    public class Team {

    }

    /**
     *  Calls that return event, or event-specific information. 
     */
    public class Event {

    }

    /**
     * Calls that return match, or match-specific information.
     */
    public class Match {

    }

    /**
     * Calls that return district, or district-related information.
     */
    public class District {

    }
}
