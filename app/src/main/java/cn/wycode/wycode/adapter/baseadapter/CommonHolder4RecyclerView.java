package cn.wycode.wycode.adapter.baseadapter;

import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import cn.wycode.wycode.MyApplication;

/**
 * Created by HY on 2015/11/16.
 */
public class CommonHolder4RecyclerView extends RecyclerView.ViewHolder  {

    public View mConvertView;
    public int position;
    private SparseArray<View> mViews;

    public CommonHolder4RecyclerView(View itemView) {
        super(itemView);
        this.mConvertView = itemView;
        this.mViews = new SparseArray<>();
    }


    /**
     * 得到item上的控件
     *
     * @param viewId 控件的id
     * @return 对应的控件
     */
    public <T extends View> T getView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }

        return (T) view;

    }

    /**
     * 为TextView设置Text
     *
     * @param textViewId textView的Id
     * @param text       字符串的Id
     * @return ViewHolder
     */
    public CommonHolder4RecyclerView setTextViewText(@IdRes int textViewId, String text) {
        TextView tv = getView(textViewId);
        if (!TextUtils.isEmpty(text)) {
            tv.setText(text);
        }
        return this;
    }

    /**
     * 为TextView设置Text
     *
     * @param textViewId textView的Id
     * @param strId      字符串的Id
     * @return ViewHolder
     */
    public CommonHolder4RecyclerView setTextViewText(@IdRes int textViewId, @StringRes int strId) {
        TextView tv = getView(textViewId);
        tv.setText(strId);
        return this;
    }

    /**
     * 为TextView设置Text
     *
     * @param textViewId             textView的Id
     * @param spannableStringBuilder 字符串的Builder用于改变字色   字体
     * @return ViewHolder
     */
    public CommonHolder4RecyclerView setTextViewText(@IdRes int textViewId, SpannableStringBuilder spannableStringBuilder) {
        TextView tv = getView(textViewId);
        tv.setText(spannableStringBuilder);
        return this;
    }

    /**
     * 获取TextView的Text
     *
     * @param textViewId textView的Id
     * @return textView的text
     */
    public String getTextViewText(@IdRes int textViewId) {
        TextView tv = getView(textViewId);
        return tv.getText().toString();
    }


    /**
     * 为ImageView设置资源图片
     *
     * @param imageViewId ImageView的Id
     * @param drawableId  图片资源Id
     * @return
     */
    public CommonHolder4RecyclerView setImageViewImage(@IdRes int imageViewId, @DrawableRes int drawableId) {
        ImageView imageView = getView(imageViewId);
        imageView.setImageResource(drawableId);
        return this;
    }

    /**
     * 为CheckBox设置是否check
     *
     * @param checkBoxId CheckBox的Id
     * @param isCheck    是否选择
     * @return
     */
    public CommonHolder4RecyclerView setCheckBoxCheck(@IdRes int checkBoxId, boolean isCheck) {
        CheckBox cb = getView(checkBoxId);
        cb.setChecked(isCheck);
        return this;
    }

    /**
     * 为CheckBox设置CheckListener 回调带position
     *
     * @param checkBoxId    CheckBox的Id
     * @param checkListener Listener
     * @return
     */
    public CommonHolder4RecyclerView setCheckBoxCheckChangeListener(@IdRes int checkBoxId, ListenerWithPosition.OnCheckedChangeWithPositionListener checkListener) {
        CheckBox cb = getView(checkBoxId);
        ListenerWithPosition listener = new ListenerWithPosition(position, this);
        cb.setOnCheckedChangeListener(listener);
        listener.setCheckChangeListener(checkListener);
        return this;
    }

    /**
     * 为View设置ClickListener,可传多个ViewId
     *
     * @param viewIds       CheckBox的Id
     * @param clickListener Listener
     * @return
     */
    public CommonHolder4RecyclerView setOnClickListener(ListenerWithPosition.OnClickWithPositionListener clickListener, @IdRes int... viewIds) {
        ListenerWithPosition listener = new ListenerWithPosition(position, this);
        listener.setOnClickListener(clickListener);
        for (int id : viewIds) {
            View v = getView(id);
            v.setOnClickListener(listener);
        }
        return this;
    }

    /**
     * 为ImageView设置网络图片
     *
     * @param imageViewId ImageView的Id
     * @param url         图片资源Id
     * @return
     */
    public CommonHolder4RecyclerView setImageViewImage(@IdRes int imageViewId, String url) {
        ImageView imageView = getView(imageViewId);
		MyApplication.imageLoader.displayImage(url, imageView);
        return this;
    }

    /**
     * 为View设置选择状态
     *
     * @param ViewId     View的Id
     * @param isSelected 是否选择
     * @return
     */
    public CommonHolder4RecyclerView setViewSelect(@IdRes int ViewId, boolean isSelected) {
        View view = getView(ViewId);
        view.setSelected(isSelected);
        return this;
    }


    /**
     * 为View设置显示状态
     *
     * @param ViewId     View的Id
     * @param visibility 是否显示
     * @return
     */
    public CommonHolder4RecyclerView setViewVisibility(@IdRes int ViewId,int visibility) {
        View view = getView(ViewId);
        view.setVisibility(visibility);
        return this;
    }


    /**
     * 为View设置背景
     *
     * @param ViewId     View的Id
     * @param drawableId drawable的Id
     * @return
     */
    public CommonHolder4RecyclerView setViewBackground(@IdRes int ViewId, @DrawableRes int drawableId) {
        View view = getView(ViewId);
        view.setBackgroundResource(drawableId);
        return this;
    }


    public CommonHolder4RecyclerView setOnItemClickListener(ListenerWithPosition.OnClickWithPositionListener clickListener){
        ListenerWithPosition listener = new ListenerWithPosition(position, this);
        listener.setOnClickListener(clickListener);
        this.mConvertView.setOnClickListener(listener);
        return this;
    }


}
