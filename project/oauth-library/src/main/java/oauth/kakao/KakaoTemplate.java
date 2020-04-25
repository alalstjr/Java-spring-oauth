package oauth.kakao;

import java.net.URI;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import util.StringUtil;

public class KakaoTemplate extends AbstractOAuth2ApiBinding {

    public KakaoTemplate(String accessToken) {
        super(accessToken);
    }

    public <T> T fetchObject(Class<T> type, MultiValueMap<String, String> queryParameters) {
        URI uri = URIBuilder.fromUri(KakaoOAuthConstants.USER_INFO_URI).queryParams(queryParameters)
                .build();

        return getRestTemplate().getForObject(uri, type);
    }

    public <T> T fetchObject(Class<T> type, String... fields) {
        StringUtil                    stringUtil      = new StringUtil();
        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();

        if (fields.length > 0) {
            String joinedFields = stringUtil.appendString(fields);
            queryParameters.set("property_keys", joinedFields);
        }

        return this.fetchObject(type, queryParameters);
    }
}
