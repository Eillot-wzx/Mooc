package domain;

public class User {

    /**
     * User类与数据库中Users表对应
     * 提供空参构造和满参构造
     * 提供toString方法
     * 提供get-set方法
     */

    private int uid;
    private String uname;
    private String upassword;
    private String uQQ;
    private String uidCard;
    private boolean ulimit;
    private boolean udel;

    public User() {
    }

    public User(int uid, String uname, String upassword, String uQQ, String uidCard, boolean ulimit, boolean udel) {
        this.uid = uid;
        this.uname = uname;
        this.upassword = upassword;
        this.uQQ = uQQ;
        this.uidCard = uidCard;
        this.ulimit = ulimit;
        this.udel = udel;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", upassword='" + upassword + '\'' +
                ", uQQ='" + uQQ + '\'' +
                ", uidCard='" + uidCard + '\'' +
                ", ulimit=" + ulimit +
                ", udel=" + udel +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getuQQ() {
        return uQQ;
    }

    public void setuQQ(String uQQ) {
        this.uQQ = uQQ;
    }

    public String getUidCard() {
        return uidCard;
    }

    public void setUidCard(String uidCard) {
        this.uidCard = uidCard;
    }

    public boolean isUlimit() {
        return ulimit;
    }

    public void setUlimit(boolean ulimit) {
        this.ulimit = ulimit;
    }

    public boolean isUdel() {
        return udel;
    }

    public void setUdel(boolean udel) {
        this.udel = udel;
    }

}
