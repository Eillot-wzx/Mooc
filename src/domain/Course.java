package domain;

public class Course {

    /**
     * Course类与数据库中Course表对应
     * 提供空参构造和满参构造
     * 提供toString方法
     * 提供get-set方法
     */

    private int cid;
    private String cname;
    private String classify;
    private String cteacher;
    private String cpath;
    private int ctime;
    private String cmore;
    private boolean climit;
    private boolean cdel;

    public Course() {
    }

    public Course(int cid, String cname, String classify, String cteacher, String cpath, int ctime, String cmore, boolean climit, boolean cdel) {
        this.cid = cid;
        this.cname = cname;
        this.classify = classify;
        this.cteacher = cteacher;
        this.cpath = cpath;
        this.ctime = ctime;
        this.cmore = cmore;
        this.climit = climit;
        this.cdel = cdel;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", classify='" + classify + '\'' +
                ", cteacher='" + cteacher + '\'' +
                ", cpath='" + cpath + '\'' +
                ", ctime=" + ctime +
                ", cmore='" + cmore + '\'' +
                ", climit=" + climit +
                ", cdel=" + cdel +
                '}';
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getCteacher() {
        return cteacher;
    }

    public void setCteacher(String cteacher) {
        this.cteacher = cteacher;
    }

    public String getCpath() {
        return cpath;
    }

    public void setCpath(String cpath) {
        this.cpath = cpath;
    }

    public int getCtime() {
        return ctime;
    }

    public void setCtime(int ctime) {
        this.ctime = ctime;
    }

    public String getCmore() {
        return cmore;
    }

    public void setCmore(String cmore) {
        this.cmore = cmore;
    }

    public boolean isClimit() {
        return climit;
    }

    public void setClimit(boolean climit) {
        this.climit = climit;
    }

    public boolean isCdel() {
        return cdel;
    }

    public void setCdel(boolean cdel) {
        this.cdel = cdel;
    }

}
