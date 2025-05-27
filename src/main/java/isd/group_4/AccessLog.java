package isd.group_4;

import java.io.Serializable;
import java.sql.Timestamp;

public class AccessLog implements Serializable {
    private int id;
    private int userId;
    private Timestamp loginTime;
    private Timestamp logoutTime;

    public AccessLog() {}

    public AccessLog( int userId, Timestamp loginTime, Timestamp logoutTime) {
        this.userId = userId;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public java.sql.Timestamp getLoginTime() {
        return loginTime;
    }
    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }
    public Timestamp getLogoutTime() {
        return logoutTime;
    }
    public void setLogoutTime(Timestamp logoutTime) {
        this.logoutTime = logoutTime;
    }
}
