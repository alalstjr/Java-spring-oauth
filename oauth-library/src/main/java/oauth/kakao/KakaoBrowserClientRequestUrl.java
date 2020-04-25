package oauth.kakao;

import com.google.api.client.auth.oauth2.BrowserClientRequestUrl;
import java.util.Collection;

public class KakaoBrowserClientRequestUrl extends BrowserClientRequestUrl {

    public KakaoBrowserClientRequestUrl(
            String clientId, String redirectUri, Collection<String> scopes) {
        super(KakaoOAuthConstants.AUTHORIZATION_SERVER_URL, clientId);
        setRedirectUri(redirectUri);
        setScopes(scopes);
    }
}
