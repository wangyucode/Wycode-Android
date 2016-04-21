package cn.wycode.wycode.activity.fragement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.MessageQueue;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import cn.wycode.wycode.Constants;
import cn.wycode.wycode.R;
import cn.wycode.wycode.ServerURL;
import cn.wycode.wycode.activity.MessageAddActivity;
import cn.wycode.wycode.adapter.MessageAdapter;
import cn.wycode.wycode.adapter.baseadapter.MyRecycleViewDecoration;
import cn.wycode.wycode.model.Message;
import cn.wycode.wycode.model.MessagePage;
import cn.wycode.wycode.model.MoviePage;
import cn.wycode.wycode.model.Page;
import cn.wycode.wycode.net.EasyHttp;
import cn.wycode.wycode.net.ResultBean;
import cn.wycode.wycode.utils.ToastUtil;

/**
 * Created by huangyi on 16/2/26.
 * MessageFragment
 */
public class MessageFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.rec_message_fragment)
    RecyclerView recMessageFragment;
    @Bind(R.id.swp_message_fragment)
    SwipeRefreshLayout swpMessageFragment;
    @Bind(R.id.fab_message_add)
    FloatingActionButton fabMessageAdd;

    MessageAdapter adapter;

    private static int page = 0;
    private static final int size = 5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_message);
    }


    @Override
    protected void initView() {
        setOnClickListeners(this, fabMessageAdd);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext);
        recMessageFragment.setLayoutManager(manager);
        recMessageFragment.addItemDecoration(new MyRecycleViewDecoration(mContext,
                MyRecycleViewDecoration.VERTICAL_LIST));

        adapter = new MessageAdapter(mContext, null, R.layout.item_message);
        recMessageFragment.setAdapter(adapter);
        swpMessageFragment.setOnRefreshListener(this);
        swpMessageFragment.setColorSchemeResources(R.color.colorPrimary);
    }

    @Override
    public void getHttpData() {
        Map<String, String> params = new HashMap<>();
        params.put("page", "" + page);
        params.put("size", "" + size);
        EasyHttp.doPost(mContext, ServerURL.GET_MESSAGE_INFO, params, null, MessagePage.class, false, new EasyHttp.OnEasyHttpDoneListener<MessagePage>() {
            @Override
            public void onEasyHttpSuccess(ResultBean<MessagePage> resultBean) {
                if (page == 0) {
                    adapter.mData.clear();
                }
                adapter.mData.addAll(resultBean.data.content);
                adapter.notifyDataSetChanged();
                swpMessageFragment.setRefreshing(false);
            }

            @Override
            public void onEasyHttpFailure(int code, String message) {
                ToastUtil.showToastDefault(mContext, message);
                swpMessageFragment.setRefreshing(false);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_message_add:
                Intent intent = new Intent(mContext, MessageAddActivity.class);
                startActivityForResult(intent, Constants.CODE_500);
                break;
        }
    }

    @Override
    public void onRefresh() {
        page = 0;
        getHttpData();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            Message message = (Message) data.getSerializableExtra("message");
            adapter.mData.add(0,message);
            adapter.notifyItemInserted(0);
        }
    }
}
