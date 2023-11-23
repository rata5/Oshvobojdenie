package utility;

public class User {
    private String userEGN = "";
    private String userCode = "";


    public User(String userEGN, String userCode){
        this.userEGN = userEGN;
        this.userCode = userCode;
    }

    public User(){

    }

    public String getUserEGN() {
        return userEGN;
    }

    public void setUserEGN(String userEGN) {
        this.userEGN = userEGN;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
