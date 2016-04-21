package cn.wycode.wycode.model;

import java.io.Serializable;

/**
 * 留言板消息实体
 * Created by wangyu on 16/3/12.
 */
public class Message implements Serializable{

    public String content; //(string, optional): 内容 ,
    public long id; //(integer, optional): id ,
    public String mobile; //(string, optional): 手机号 ,
    public String name; //(string, optional): 发表人 ,
    public String time; //(string, optional): 发表时间，yyyy-MM-dd HH:mm:ss

}
