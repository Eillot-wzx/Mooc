package domain;

public class Classification {

    /**
     * Classification类与数据库中classification表对应
     * 提供空参构造和满参构造
     * 提供toString方法
     * 提供get-set方法
     */

    private int class_id;
    private String class_name;

    public Classification() {
    }

    public Classification(int class_id, String class_name) {
        this.class_id = class_id;
        this.class_name = class_name;
    }

    @Override
    public String toString() {
        return "Classification{" +
                "class_id=" + class_id +
                ", class_name='" + class_name + '\'' +
                '}';
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }


}
