public class Login {

    //nome de usuario
    public String userName;
    //hora de entrada, hora de saida e tempo de conexão, respectivamente
    public int loginHr, logoutHr, logTime;

    //métodos "setters" e "getters"

    public void setUserName(String usrName) { this.userName = usrName; }

    public void setLoginHr(int inHr) { this.loginHr = inHr; }

    public void setLogoutHr(int outHr) { this.logoutHr = outHr; }

    public void setLogTime() { this.logTime = this.logoutHr - this.loginHr; }

    public String getUserName() { return this.userName; }

    public int getLoginHr() { return loginHr; }

    public int getLogoutHr() { return logoutHr; }

}
