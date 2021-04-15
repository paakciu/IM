package top.paakciu.utils.info;

/**
 * 这个是自己设计的Session，比较简单
 * @author paakciu
 * @ClassName: Session
 * @date: 2021/3/5 16:51
 */
public class ChannelUser {
    // 用户唯一性标识
    private Long userId;
    private String userName;

    public ChannelUser(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}