package com.github.daflamingfox.api;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import com.github.daflamingfox.schema.api.APIStatus;
import com.github.daflamingfox.schema.award.Award;
import com.github.daflamingfox.schema.district.DistrictList;
import com.github.daflamingfox.schema.district.DistrictRanking;
import com.github.daflamingfox.schema.event.Event;
import com.github.daflamingfox.schema.event.EventSimple;
import com.github.daflamingfox.schema.match.Match;
import com.github.daflamingfox.schema.match.MatchSimple;
import com.github.daflamingfox.schema.other.Media;
import com.github.daflamingfox.schema.team.Team;
import com.github.daflamingfox.schema.team.TeamEventStatus;
import com.github.daflamingfox.schema.team.TeamRobot;
import com.github.daflamingfox.schema.team.TeamSimple;
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
   public ListCalls lists;
   public TeamCalls team;
   public EventCalls event;
   public MatchCalls match;
   public DistrictCalls district;
   // Other
   public final String url = "https://www.thebluealliance.com/api/v3/";
   public String lMDate = "Fri,01 Jan 1990 00:00:00 GMT";
   
   public TBAApi(String readAuthToken) {
       this.readAuthToken = readAuthToken;
       this.okClient = new OkHttpClient();
       this.gson = new GsonBuilder().setPrettyPrinting().create();
       this.reqBuilder = new Request.Builder().addHeader(readAuthId, this.readAuthToken).addHeader(lM, lMDate);
       this.TBA = new TBA();
       this.lists = new ListCalls();
       this.team = new TeamCalls();
       this.event = new EventCalls();
       this.match = new MatchCalls();
       this.district = new DistrictCalls();
    }

    /**
     * Calls that expose TBA internals or status.
     */
    public class TBA {
        
        public APIStatus getStatus() throws IOException {
            Request req = reqBuilder.url(url+"status").build();
            
            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);
            
            return gson.fromJson(rsp.body().string(), APIStatus.class);
        }
    }
    /**
     * Calls that return a list of records.
     */
    public class ListCalls {
        
        public Team[] getTeamsByPageNumber(int pageNumber) throws IOException {
            Request req = reqBuilder.url(url+"teams/"+pageNumber).build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);
            
            return gson.fromJson(rsp.body().string(), Team[].class);
        }

        public TeamSimple[] getTeamsByPageNumberAsSimple(int pageNumber) throws IOException {
            Request req = reqBuilder.url(url+"teams/"+pageNumber+"/simple").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);
            
            return gson.fromJson(rsp.body().string(), TeamSimple[].class);
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

        public TeamSimple[] getTeamInYearByPageNumberAsSimple(int year, int pageNumber) throws IOException {
            Request req = reqBuilder.url(url+"teams/"+year+"/"+pageNumber+"/simple").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), TeamSimple[].class);
        }

        public String[] getTeamKeysFromYearByPageNumber(int year, int pageNumber) throws IOException {
            Request req = reqBuilder.url(url+"teams/"+year+"/"+pageNumber+"keys").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), String[].class);
        }

        public Map<String, TeamEventStatus> getTeamEventStatusesInYear(String teamKey, int year) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/events/"+year+"/statuses").build();
            
            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            Type tesMapType = new TypeToken<Map<String, TeamEventStatus>>() {}.getType();
            return gson.fromJson(rsp.body().string(), tesMapType);
        }

        public Event[] getEventsInYear(int year) throws IOException {
            Request req = reqBuilder.url(url+"events/"+year).build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Event[].class);
        }

        public EventSimple[] getEventsInYearAsSimple(int year) throws IOException {
            Request req = reqBuilder.url(url+"events/"+year+"/simple").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), EventSimple[].class);
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

        public TeamSimple[] getTeamsInEventAsSimple(String eventKey) throws IOException {
            Request req = reqBuilder.url(url+"event/"+eventKey+"/teams/simple").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), TeamSimple[].class);
        }

        public Team[] getTeamKeysForEvent(String eventKey) throws IOException {
            Request req = reqBuilder.url(url+"event/"+eventKey+"/teams/keys").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Team[].class);
        }

        public Map<String, TeamEventStatus> getTeamEventStatusesInEvent(String eventKey) throws IOException {
            Request req = reqBuilder.url(url+"event/"+eventKey+"/teams/statuses").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            Type tesMapType = new TypeToken<Map<String, TeamEventStatus>>() {}.getType();
            return gson.fromJson(rsp.body().string(), tesMapType);
        }

        public Event[] getEventsInDistrict(String districtKey) throws IOException {
            Request req = reqBuilder.url(url+"district/"+districtKey+"/events").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Event[].class);
        }

        public EventSimple[] getEventsInDistrictAsSimple(String districtKey) throws IOException {
            Request req = reqBuilder.url(url+"district/"+districtKey+"/events/simple").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), EventSimple[].class);
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

        public TeamSimple[] getTeamsInDistrictAsSimple(String districtKey) throws IOException {
            Request req = reqBuilder.url(url+"district/"+districtKey+"/teams/simple").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), TeamSimple[].class);
        }

        public String[] getTeamKeysInDistrict(String districtKey) throws IOException {
            Request req = reqBuilder.url(url+"district/"+districtKey+"/teams/keys").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), String[].class);
        }

        public DistrictRanking[] getTeamDistrictRankingsInDistrict(String districtKey) throws IOException {
            Request req = reqBuilder.url(url+"district/"+districtKey+"/rankings").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), DistrictRanking[].class);
        }

    } 
   
    /**
     * Calls that return team or team-specific information.
     */
    public class TeamCalls {
        public Team[] getTeamsByPageNumber(int pageNumber) throws IOException {
            Request req = reqBuilder.url(url+"teams/"+pageNumber).build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Team[].class);
        }

        public TeamSimple[] getTeamsByPageNumberAsSimple(int pageNumber) throws IOException {
            Request req = reqBuilder.url(url+"teams/"+pageNumber+"/simple").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), TeamSimple[].class);
        }
    
        public String[] getTeamKeysByPageNumber(int pageNumber) throws IOException {
            Request req = reqBuilder.url(url+"teams/"+pageNumber+"/keys").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), String[].class);
        }

        public Team[] getTeamsInYearByPageNumber(int year, int pageNumber) throws IOException {
            Request req = reqBuilder.url(url+"teams/"+year+"/"+pageNumber).build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Team[].class);
        }

        public TeamSimple[] getTeamsInYearByPageNumberAsSimple(int year, int pageNumber) throws IOException {
            Request req = reqBuilder.url(url+"teams/"+year+"/"+pageNumber+"/simple").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), TeamSimple[].class);
        }
    
        public String[] getTeamKeysInYearByPageNumber(int year, int pageNumber) throws IOException {
            Request req = reqBuilder.url(url+"teams/"+year+"/"+pageNumber+"/keys").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), String[].class);
        }

        public Team getTeam(String teamKey) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey).build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Team.class);
        }
        
        public TeamSimple getTeamAsSimple(String teamKey) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/simple").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), TeamSimple.class);
        }

        public Integer[] getYearsTeamParticipated(String teamKey) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/years_participated").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Integer[].class);
        }

        public DistrictList[] getDistrictsByTeamKey(String teamKey) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/districts").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), DistrictList[].class);
        }

        public TeamRobot[] getTeamRobotsByTeamKey(String teamKey) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/robots").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), TeamRobot[].class);
        }

        public Event[] getEventsByTeamKey(String teamKey) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/events").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Event[].class);
        }

        public EventSimple[] getEventsByTeamKeyAsSimple(String teamKey) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/events/simple").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), EventSimple[].class);
        }

        public String[] getEventKeysByTeamKey(String teamKey) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/events/keys").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), String[].class);
        }
        
        public Event[] getEventsByTeamKeyAndYear(String teamKey, int year) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/events/"+year).build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Event[].class);
        }

        public EventSimple[] getEventsByTeamKeyAndYearAsSimple(String teamKey, int year) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/events/"+year+"/simple").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), EventSimple[].class);
        }

        public String[] getEventKeysByTeamKeyAndYear(String teamKey, int year) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/events/"+year+"/keys").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), String[].class);
        }

        public Map<String, TeamEventStatus> getTeamEventStatusesByTeamKeyAndYear(String teamKey, int year) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/events/"+year+"/satuses").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            Type tesMapType = new TypeToken<Map<String, TeamEventStatus>>() {}.getType();
            return gson.fromJson(rsp.body().string(), tesMapType);
        }

        public Match[] getMatchesByTeamKeyAndEventKey(String teamKey, String eventKey) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/event/"+eventKey+"/matches").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Match[].class);
        }

        public MatchSimple[] getMatchesByTeamKeyAndEventKeyAsSimple(String teamKey, String eventKey) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/event/"+eventKey+"/matches").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), MatchSimple[].class);
        }

        public String[] getMatchKeysByTeamKeyAndEventKey(String teamKey, String eventKey) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/event/"+eventKey+"/matches/keys").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), String[].class);
        }

        public Award[] getAwardsByTeamKeyAndEventKey(String teamKey, String eventKey) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/event/"+eventKey+"/awards").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Award[].class);
        }

        public TeamEventStatus getTeamEventStatusByTeamKeyAndEventKey(String teamKey, String eventKey) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/event"+eventKey+"/status").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), TeamEventStatus.class);
        }

        public Award[] getAwardsByTeamKey(String teamKey) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/awards").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Award[].class);
        }

        public Award[] getAwardsByTeamKeyAndYear(String teamKey, int year) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/arards/"+year).build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Award[].class);
        }

        public Match[] getMatchesByTeamKeyAndYear(String teamKey, int year) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/matches/"+year).build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Match[].class);
        }

        public MatchSimple[] getMatchesByTeamKeyAndYearAsSimple(String teamKey, int year) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/matches/"+year+"/simple").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), MatchSimple[].class);
        }

        public String[] getMatchKeysByTeamKeyAndYear(String teamKey, int year) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/matches/"+year+"/keys").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), String[].class);
        }

        public Media[] getMediaByTeamKeyAndYear(String teamKey, int year) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/media/"+year).build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Media[].class);
        }

        public Media[] getMediabyTeamKeyAndMediaTag(String teamKey, String mediaTag) throws IOException{
            Request req = reqBuilder.url(url+"team/"+teamKey+"/media/tag/"+mediaTag).build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Media[].class);
        }

        public Media[] getMediaByTeamKeyAndMediaTagAndYear(String teamKey, String mediaTag, int year) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/medi/tag/"+mediaTag+"/"+year).build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Media[].class);
        }

        public Media[] getSocialMediaByTeamKey(String teamKey) throws IOException {
            Request req = reqBuilder.url(url+"team/"+teamKey+"/social_media").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Media[].class);
        }

        public Team[] getTeamsByEventKey(String eventKey) throws IOException {
            Request req = reqBuilder.url(url+"event/"+eventKey+"/teams/").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Team[].class);
        }

        public TeamSimple[] getTeamByEventKeyAsSimple(String eventKey) throws IOException {
            Request req = reqBuilder.url(url+"event/"+eventKey+"/teams/simple").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), TeamSimple[].class);
        }

        public String[] getTeamKeysByEventKey(String eventKey) throws IOException {
            Request req = reqBuilder.url(url+"event/"+eventKey+"/teams/keys").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), String[].class);
        }

        public Map<String, TeamEventStatus> getTeamEventStatusesByEventKey(String eventKey) throws IOException {
            Request req = reqBuilder.url(url+"event/"+eventKey+"/teams/statuses").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            Type tesMapType = new TypeToken<Map<String, TeamEventStatus>>() {}.getType();
            return gson.fromJson(rsp.body().string(), tesMapType);
        }

        public Team[] getTeamsByDistrictKey(String districtKey) throws IOException {
            Request req = reqBuilder.url(url+"district/"+districtKey+"/teams/").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), Team[].class);
        }

        public TeamSimple[] getTeamsByDistrictKeyAsSimple(String districtKey) throws IOException {
            Request req = reqBuilder.url(url+"district/"+districtKey+"/teams/simple").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), TeamSimple[].class);
        }

        public String[] getTeamKeysByDistrictKey(String districtKey) throws IOException {
            Request req = reqBuilder.url(url+"district/"+districtKey+"/teams/keys").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), String[].class);
        }

        public DistrictRanking[] getRankingsbyDistrictKey(String districtKey) throws IOException {
            Request req = reqBuilder.url(url+"district/"+districtKey+"/rankings/").build();

            Response rsp = okClient.newCall(req).execute();
            lMDate = rsp.headers().get(lM);

            return gson.fromJson(rsp.body().string(), DistrictRanking[].class);
        }


    }

    /**
     *  Calls that return event, or event-specific information. 
     */
    public class EventCalls {
        
    }

    /**
     * Calls that return match, or match-specific information.
     */
    public class MatchCalls {

    }

    /**
     * Calls that return district, or district-related information.
     */
    public class DistrictCalls {

    }
}
