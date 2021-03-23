package top.paakciu.utils;

/**
 * 这个是自己设计的Session，比较简单
 * @author paakciu
 * @ClassName: Session
 * @date: 2021/3/5 16:51
 */
public class Session {
    // 用户唯一性标识
    private String userId;
    private String userName;

    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}