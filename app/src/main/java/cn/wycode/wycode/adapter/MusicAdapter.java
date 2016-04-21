package cn.wycode.wycode.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.List;

import cn.wycode.wycode.R;
import cn.wycode.wycode.ServerURL;
import cn.wycode.wycode.activity.MovieDetailActivity;
import cn.wycode.wycode.activity.MusicDetailActivity;
import cn.wycode.wycode.adapter.baseadapter.CommonAdapter4RecyclerView;
import cn.wycode.wycode.adapter.baseadapter.CommonHolder4RecyclerView;
import cn.wycode.wycode.adapter.baseadapter.ListenerWithPosition;
import cn.wycode.wycode.model.Movie;
import cn.wycode.wycode.model.Music;

/**
 * 音乐适配器
 * Created by wy on 16/2/26.
 */
public class MusicAdapter extends CommonAdapter4RecyclerView<Music> implements ListenerWithPosition.OnClickWithPositionListener<CommonHolder4RecyclerView> {
    public MusicAdapter(Context context, List<Music> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(CommonHolder4RecyclerView holder, Music music) {
        holder.setTextViewText(R.id.tv_music_item_name, music.name);
        holder.setTextViewText(R.id.tv_music_item_year, "年代:" + music.year);
        holder.setTextViewText(R.id.tv_music_item_album, "专辑:" + music.album);
        holder.setTextViewText(R.id.tv_music_item_artist, music.artist);
        holder.setImageViewImage(R.id.iv_music_item, ServerURL.BASE_URL + music.image);
        holder.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v, int position, CommonHolder4RecyclerView holder) {
        Intent intent = new Intent(mContext, MusicDetailActivity.class);
        intent.putExtra("music", mData.get(position));
        mContext.startActivity(intent);
    }
}
