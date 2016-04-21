package cn.wycode.wycode.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.List;

import cn.wycode.wycode.R;
import cn.wycode.wycode.ServerURL;
import cn.wycode.wycode.activity.MusicDetailActivity;
import cn.wycode.wycode.adapter.baseadapter.CommonAdapter4RecyclerView;
import cn.wycode.wycode.adapter.baseadapter.CommonHolder4RecyclerView;
import cn.wycode.wycode.adapter.baseadapter.ListenerWithPosition;
import cn.wycode.wycode.model.Message;
import cn.wycode.wycode.model.Music;

/**
 * 音乐适配器
 * Created by wy on 16/2/26.
 */
public class MessageAdapter extends CommonAdapter4RecyclerView<Message>{
    public MessageAdapter(Context context, List<Message> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(CommonHolder4RecyclerView holder, Message message) {
        holder.setTextViewText(R.id.tv_message_item_content, message.content);
        holder.setTextViewText(R.id.tv_message_item_name, message.name);
        holder.setTextViewText(R.id.tv_message_item_time, message.time);
    }

}
