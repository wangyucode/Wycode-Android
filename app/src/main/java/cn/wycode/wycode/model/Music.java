package cn.wycode.wycode.model;

import java.io.Serializable;

/**
 *
 * Created by wangyu on 16/3/11.
 */
public class Music implements Serializable{
    public String album; //(string, optional): 专辑 ,
    public String artist; //(string, optional): 艺术家 ,
    public int id; //(integer, optional): 音乐id ,
    public String image; //(string, optional): 专辑封面，相对地址 ,
    public String name; //(string, optional): 歌曲名 ,
    public String url; //(string, optional): 播放链接 ,
    public String year; //(integer, optional): 年代，2007
}
