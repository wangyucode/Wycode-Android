package cn.wycode.wycode.model;

import java.io.Serializable;

/**
 * Created by huangyi on 16/2/26.
 * 电影实体
 */
public class Movie implements Serializable {
    public int id;//(integer, optional): 电影id,
    public String actors;//(string, optional): 主演 ,
    public String name;//(string, optional): 电影名,
    public String description;// (string, optional): 电影简介,
    public float rating;//(number, optional): 电影评分,
    public String image;//(string, optional): 电影主图,
    public int year;//(integer, optional): 电影年代
}
