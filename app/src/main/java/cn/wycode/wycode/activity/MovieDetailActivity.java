package cn.wycode.wycode.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.Bind;
import cn.wycode.wycode.BaseActivity;
import cn.wycode.wycode.MyApplication;
import cn.wycode.wycode.R;
import cn.wycode.wycode.ServerURL;
import cn.wycode.wycode.model.Movie;

/**
 * 电影详情
 * Created by wangyu on 16/3/11.
 */
public class MovieDetailActivity extends BaseActivity {

    Movie movie;


    @Bind(R.id.iv_movie_detail)
    ImageView ivMovieDetail;
    @Bind(R.id.tv_movie_detail_name)
    TextView tvMovieDetailName;
    @Bind(R.id.rb_movie_detail)
    RatingBar rbMovieDetail;
    @Bind(R.id.tv_movie_detail_time)
    TextView tvMovieDetailTime;
    @Bind(R.id.tv_movie_detail_actors)
    TextView tvMovieDetailActors;
    @Bind(R.id.tv_movie_detail_desc)
    TextView tvMovieDetailDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movie = (Movie) getIntent().getExtras().get("movie");
        setContentViewWithDefaultTitle(R.layout.activity_movie_detail, "电影详情");
    }

    @Override
    protected void initView() {
        if (movie!=null){
            setData();
        }
    }

    private void setData() {
        MyApplication.imageLoader.displayImage(ServerURL.BASE_URL + movie.image, ivMovieDetail);
        tvMovieDetailName.setText(movie.name);
        tvMovieDetailTime.setText("年代:" + movie.year);
        if(!TextUtils.isEmpty(movie.actors)){
            tvMovieDetailActors.setText("主演:"+movie.actors);
        }
        rbMovieDetail.setRating(movie.rating);
        tvMovieDetailDesc.setText(movie.description);
    }
}
