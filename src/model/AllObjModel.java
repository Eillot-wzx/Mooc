package model;

import org.apache.commons.dbutils.QueryRunner;
import utils.C3P0Utils;

public class AllObjModel {

    /**
     * Model层
     * 接收Controller层的合法数据 操作数据库
     */

    //共有对象 由C3P0连接池获取QueryRunner对象用于操作数据库
    static QueryRunner qr = new QueryRunner(C3P0Utils.getDataSourse());

    //所执行的sql语句
    String sql;

}
