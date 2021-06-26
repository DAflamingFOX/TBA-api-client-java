package com.github.daflamingfox.api;

import java.io.IOException;

import com.github.daflamingfox.schema.api.API_Status;
import com.github.daflamingfox.schema.event.Event_District_Points;
import com.github.daflamingfox.schema.team.Team_Event_Status;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TBAApi {
    public final String readAuthToken;
    public final String readAuthTokenId = "X-TBA-Auth-Key";
    public String writeAuthToken;
    public final OkHttpClient client;
    public String url = "https://www.thebluealliance.com/api/v3/";
    public String lastModified = "Fri,01 Jan 1990 00:00:00 GMT";

    public TBAApi(String readAuthToken) {
        this.readAuthToken = readAuthToken;
        this.client = new OkHttpClient();
    }

    /**
     * Sets the write auth token to be used.
     * @param writeAuthToken The write auth token to be used.
     */
    public void setWriteAuthToken(String writeAuthToken) {
        this.writeAuthToken = writeAuthToken;
    }

    /**
     * Returns API status, and TBA status information.
     * @return 
     * @throws IOException
     */
    public API_Status getStatus() throws IOException {
        Request req = new Request.Builder().url(url+"status").addHeader(readAuthTokenId, readAuthToken).addHeader("Last-Modified", lastModified).build();

        Response rsp = client.newCall(req).execute();

        API_Status apiStatus = new Gson().fromJson(rsp.body().string(), API_Status.class);
        
        lastModified = rsp.headers().get("Last-Modified");

        return apiStatus;
    }

    /**
     * Gets a key-value list of the event statuses for events this team has competed at in the given year.
     * @param teamKey TBA Team Key, eg {@code frc7125}
     * @param year Competition Year (or Season). Must be 4 digits.
     * @return 
     * @throws IOException
     */
    public Team_Event_Status getTeam(String teamKey, int year) throws IOException {
        Request req = new Request.Builder().url(url+"team/"+teamKey+"/events/"+year+"/statuses").addHeader(readAuthTokenId, readAuthToken).addHeader("Last-Modified", lastModified).build();

        Response rsp = client.newCall(req).execute();

        Team_Event_Status teamEventStatus = new Gson().fromJson(rsp.body().string(), Team_Event_Status.class);
        
        lastModified = rsp.headers().get("Last-Modified");

        return teamEventStatus;
    }

    public Event_District_Points getDistrictPoints(String eventKey) throws IOException {
        Request req = new Request.Builder().url(url+"event/"+eventKey+"/district_points").addHeader(readAuthTokenId, readAuthToken).build();

        Response rsp = client.newCall(req).execute();

        Event_District_Points eventDistrictPoints = new Gson().fromJson(rsp.body().string(), Event_District_Points.class);

        return eventDistrictPoints;
    }
    
}
