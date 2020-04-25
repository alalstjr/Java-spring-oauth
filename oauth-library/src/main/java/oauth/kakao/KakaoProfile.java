package oauth.kakao;

public class KakaoProfile {

    private String nickname;
    private String thumbnail_image_url;
    private String profile_image_url;

    /* Boilerplate Code */

    public KakaoProfile() {
    }

    public KakaoProfile(String nickname, String thumbnail_image_url, String profile_image_url) {
        this.nickname = nickname;
        this.thumbnail_image_url = thumbnail_image_url;
        this.profile_image_url = profile_image_url;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getThumbnail_image_url() {
        return thumbnail_image_url;
    }

    public void setThumbnail_image_url(String thumbnail_image_url) {
        this.thumbnail_image_url = thumbnail_image_url;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }
}
