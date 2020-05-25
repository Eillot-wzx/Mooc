package domain;

public class Study {

    /**
     * Study类与数据库中Study表对应
     * 提供空参构造和满参构造
     * 提供toString方法
     * 提供get-set方法
     */
    private int sid;
    private int uid;
    private int cid;

    public Study() {
    }

    public Study(int sid, int uid, int cid) {
        this.sid = sid;
        this.uid = uid;
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Study{" +
                "sid=" + sid +
                ", uid=" + uid +
                ", cid=" + cid +
                '}';
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
