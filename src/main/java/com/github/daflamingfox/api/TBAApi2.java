package com.github.daflamingfox.api;

import java.io.IOException;

import com.github.daflamingfox.schema.api.APIStatus;
import com.github.daflamingfox.schema.team.Team;
import com.github.daflamingfox.schema.team.TeamSimple;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

public class TBAApi2 {
    // Tokens & Token names
    private final String readAuthToken;
    private final String readAuthId = "X-TBA-Auth-Key";
    private final String lM = "Last-Modified";
    // Clients
    private final OkHttpClient okClient;
    private final Gson gson;
    private final Builder reqBuilder;
    // Other
    private final String url = "https://www.thebluealliance.com/api/v3/";
    private String lMDate = "Fri,01 Jan 1990 00:00:00 GMT";

    public TBAApi2(String readAuthToken) {
        this.readAuthToken = readAuthToken;
        okClient = new OkHttpClient();
        gson = new Gson();
        this.reqBuilder = new Request.Builder().addHeader(readAuthId, this.readAuthToken).addHeader(lM, lMDate);
    }

    public void close() {
        okClient.dispatcher().executorService().shutdown();
        reqBuilder.delete();
        
    }

    /**
     * 
     * @return API status, and TBA status information.
     * @throws IOException
     */
    public APIStatus getStatus() throws IOException {
        Request req = reqBuilder.url(url + "status").build();

        Response rsp = okClient.newCall(req).execute();
        lMDate = rsp.headers().get(lM);

        return gson.fromJson(rsp.body().string(), APIStatus.class);
    }

    /**
     * 
     * @param pageNumber
     * @return Gets a list of {@code Team} objects, paginated in groups of 500.
     * @throws IOException
     */
    public Team[] getTeams(int pageNumber) throws IOException {
        Request req = reqBuilder.url(url + "teams/" + pageNumber).build();

        Response rsp = okClient.newCall(req).execute();
        lMDate = rsp.headers().get(lM);

        return gson.fromJson(rsp.body().string(), Team[].class);
    }

    /**
     * 
     * @param pageNumber
     * @return a list of short form Team_Simple objects, paginated in groups of 500.
     * @throws IOException
     */
    public TeamSimple[] getTeamsSimple(int pageNumber) throws IOException {
        Request req = reqBuilder.url(url + "teams/" + pageNumber + "/simple").build();

        Response rsp = okClient.newCall(req).execute();
        lMDate = rsp.headers().get(lM);

        return gson.fromJson(rsp.body().string(), TeamSimple[].class);
    }

    /**
     * 
     * @param pageNumber
     * @return Gets a list of Team keys, paginated in groups of 500. (Note, each
     *         page will not have 500 teams, but will include the teams within that
     *         range of 500.)
     * @throws IOException
     */
    public String[] getTeamKeys(int pageNumber) throws IOException {
        Request req = reqBuilder.url(url + "teams/" + pageNumber + "/keys").build();

        Response rsp = okClient.newCall(req).execute();
        lMDate = rsp.headers().get(lM);

        return gson.fromJson(rsp.body().string(), String[].class);
    }

    /**
     * 
     * @param pageNumber
     * @param year
     * @return Gets a list of {@code Team} objects that competed in the given year,
     *         paginated in groups of 500.
     * @throws IOException
     */
    public Team[] getTeams(int pageNumber, int year) throws IOException {
        Request req = reqBuilder.url(url + "teams/" + year + "/" + pageNumber).build();

        Response rsp = okClient.newCall(req).execute();
        lMDate = rsp.headers().get(lM);

        return gson.fromJson(rsp.body().string(), Team[].class);
    }

    /**
     * 
     * @param pageNumber
     * @param year
     * @return Gets a list of short form {@code Team_Simple}objects that competed in the given year, paginated in groups of 500.
     * @throws IOException
     */
    public TeamSimple[] getTeamsSimple(int pageNumber, int year) throws IOException {
        Request req = reqBuilder.url(url + "teams/" + year + "/" + pageNumber + "/simple").build();

        Response rsp = okClient.newCall(req).execute();
        lMDate = rsp.headers().get(lM);

        return gson.fromJson(rsp.body().string(), TeamSimple[].class);
    }

    /**
     * 
     * @param pageNumber
     * @param year
     * @return Gets a list Team Keys that competed in the given year, paginated in groups of 500.
     * @throws IOException
     */
    public String[] getTeamKeys(int pageNumber, int year) throws IOException {
        Request req = reqBuilder.url(url + "teams/" + year + "/" + pageNumber + "/keys").build();

        Response rsp = okClient.newCall(req).execute();
        lMDate = rsp.headers().get(lM);

        return gson.fromJson(rsp.body().string(), String[].class);
    }


}
