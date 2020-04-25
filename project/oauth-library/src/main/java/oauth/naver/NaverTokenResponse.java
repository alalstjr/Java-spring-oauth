package oauth.naver;

import com.google.api.client.util.Key;

public class NaverTokenResponse {

    @Key("access_token")
    private String accessToken;

    @Key("refresh_token")
    private String refreshToken;

    @Key("token_type")
    private String tokenType;

    @Key("expires_in")
    private String expiresIn;

    /* Boilerplate Code */

    public NaverTokenResponse() {
    }

    public NaverTokenResponse(String accessToken, String refreshToken, String tokenType,
            String expiresIn) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }
}
