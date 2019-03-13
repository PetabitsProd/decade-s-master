package fr.agopiantexier;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetToken {
    public GetToken() {
        super();
    }

    @JsonProperty("access_token")
    private String accessToken;


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String toString(){
        return getAccessToken()+"";
    }
}
