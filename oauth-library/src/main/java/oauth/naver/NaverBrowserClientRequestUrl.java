package oauth.naver;

import com.google.api.client.auth.oauth2.BrowserClientRequestUrl;
import java.util.Collection;

public class NaverBrowserClientRequestUrl extends BrowserClientRequestUrl {

    public NaverBrowserClientRequestUrl(
            String clientId, String redirectUri, Collection<String> scopes) {
        super(NaverOAuthConstants.AUTHORIZATION_SERVER_URL, clientId);
        setRedirectUri(redirectUri);
        setScopes(scopes);
    }
}
