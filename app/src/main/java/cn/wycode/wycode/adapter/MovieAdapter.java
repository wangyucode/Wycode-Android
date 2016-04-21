package cn.wycode.wycode.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.List;

import cn.wycode.wycode.R;
import cn.wycode.wycode.ServerURL;
import cn.wycode.wycode.activity.MovieDetailActivity;
import cn.wycode.wycode.adapter.baseadapter.CommonAdapter4RecyclerView;
import cn.wycode.wycode.adapter.baseadapter.CommonHolder4RecyclerView;
import cn.wycode.wycode.adapter.baseadapter.ListenerWithPosition;
import cn.wycode.wycode.model.Movie;

/**
 *
 * Created by huangyi on 16/2/26.
 */
public class MovieAdapter extends CommonAdapter4RecyclerView<Movie> implements ListenerWithPosition.OnClickWithPositionListener<CommonHolder4RecyclerView> {
    public MovieAdapter(Context context, List<Movie> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(CommonHolder4RecyclerView holder, Movie movie) {
        holder.setTextViewText(R.id.tv_movie_item_name,movie.name);
        holder.setTextViewText(R.id.tv_movie_item_time,"年代:"+movie.year);
        holder.setTextViewText(R.id.tv_movie_item_introduce,"简介:"+movie.description);
        holder.setTextViewText(R.id.tv_movie_item_rating, movie.rating + "");
        holder.setImageViewImage(R.id.iv_movie_item_link, ServerURL.BASE_URL + movie.image);
        holder.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v, int position, CommonHolder4RecyclerView holder) {
        Intent intent = new Intent(mContext, MovieDetailActivity.class);
        intent.putExtra("movie",mData.get(position));
        mContext.startActivity(intent);
    }
}
