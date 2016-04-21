package cn.wycode.wycode;

/**
 * 接口地址
 * Created by huangyi on 16/2/26.
 */
public class ServerURL {

    public static final String SECRET="wycode.cn";

    public static final String BASE_URL="http://wycode.cn/";

    public static final String GET_MOVIE_INFO=BASE_URL+"/api/movie/getMovies";//电影列表
    public static final String GET_MUSIC_INFO=BASE_URL+"/api/music/getMusics";//音乐列表
    public static final String GET_MESSAGE_INFO=BASE_URL+"/api/board/getMessages";//留言列表
    public static final String ADD_MESSAGE=BASE_URL+"/api/board/addMessage";//新增留言
}
