package oauth.naver;

public class NaverUser {

    private String       resultcode;
    private String       message;
    private NaverAccount response;

    /* Boilerplate Code */

    public NaverUser() {
    }

    public NaverUser(String resultcode, String message, NaverAccount response) {
        this.resultcode = resultcode;
        this.message = message;
        this.response = response;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NaverAccount getResponse() {
        return response;
    }

    public void setResponse(NaverAccount response) {
        this.response = response;
    }
}
