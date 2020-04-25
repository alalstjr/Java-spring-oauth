package oauth.kakao;

public class KakaoAccount {

    private String       email;
    private String       age_range;
    private String       birthday;
    private String       gender;
    private KakaoProfile profile;

    /* Boilerplate Code */

    public KakaoAccount() {
    }

    public KakaoAccount(String email, String age_range, String birthday, String gender,
            KakaoProfile profile) {
        this.email = email;
        this.age_range = age_range;
        this.birthday = birthday;
        this.gender = gender;
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge_range() {
        return age_range;
    }

    public void setAge_range(String age_range) {
        this.age_range = age_range;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public KakaoProfile getProfile() {
        return profile;
    }

    public void setProfile(KakaoProfile profile) {
        this.profile = profile;
    }
}
