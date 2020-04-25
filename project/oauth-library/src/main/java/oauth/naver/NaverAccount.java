package oauth.naver;

public class NaverAccount {

    private String id;
    private String nickname;
    private String age;
    private String gender;
    private String email;
    private String name;
    private String birthday;

    /* Boilerplate Code */

    public NaverAccount() {
    }

    public NaverAccount(String id, String nickname, String age, String gender, String email,
            String name, String birthday) {
        this.id = id;
        this.nickname = nickname;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
