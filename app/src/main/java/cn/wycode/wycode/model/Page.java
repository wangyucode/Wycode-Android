package cn.wycode.wycode.model;

import java.util.List;

/**
 * page实体
 * Created by wy on 2016/3/12.
 * page实体并不需要解析Sort
 * content字段由具体子类实体声明
 */
public class Page {
    public int totalPages;// (integer, optional),   总页数

    public int totalElements;// (integer, optional),    总数量

    public int number;// (integer, optional), 查询起始页

    public int size;// (integer, optional), 查询size

//    public List<T> content;// (array[T], optional),

//    public Sort sort;// (Sort, optional),

    public int numberOfElements;// (integer, optional), 数量

    public boolean last;// (boolean, optional),

    public boolean first;// (boolean, optional)

    public boolean firstPage; //是否是第一页
    public boolean lastPage;    //是否是最后一页

}
