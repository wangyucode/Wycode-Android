package cn.wycode.wycode.adapter.baseadapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HY on 2015/11/16.
 * 酒店列表的适配器
 */
public abstract class CommonAdapter4RecyclerView<T> extends RecyclerView.Adapter {
    public Context mContext;
    public List<T> mData;
    private int layoutId;
    View mView;

    public CommonAdapter4RecyclerView(Context context, List<T> data, int layoutId) {
        this.mContext = context;
        this.mData = data == null ? (List<T>) new ArrayList<>() : data;
        this.layoutId = layoutId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(layoutId, parent, false);
        return new CommonHolder4RecyclerView(mView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CommonHolder4RecyclerView commonHolder = (CommonHolder4RecyclerView) holder;
        commonHolder.position = position;
        convert(commonHolder, mData.get(position));
    }

    @Override
    public int getItemCount() {
        return (mData != null) ? mData.size() : 0;
    }

    public abstract void convert(CommonHolder4RecyclerView holder, T t) ;


    /**
     * 通过类名启动Activity
     *
     * @param cls 需要跳转的类
     */
    protected void openActivity(Class<?> cls){
        Intent i = new Intent(mContext,cls);
        mContext.startActivity(i);
    }
}
