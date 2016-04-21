package cn.wycode.wycode.activity.fragement;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import cn.wycode.wycode.R;
import cn.wycode.wycode.ServerURL;
import cn.wycode.wycode.adapter.MusicAdapter;
import cn.wycode.wycode.adapter.baseadapter.MyRecycleViewDecoration;
import cn.wycode.wycode.model.MusicPage;
import cn.wycode.wycode.net.EasyHttp;
import cn.wycode.wycode.net.ResultBean;
import cn.wycode.wycode.utils.ToastUtil;

/**
 * Created by huangyi on 16/2/26.
 * MusicFragment
 */
public class MusicFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.rec_music_fragment)
    RecyclerView recMusicFragment;
    @Bind(R.id.swp_music_fragment)
    SwipeRefreshLayout swpMusicFragment;

    MusicAdapter adapter;

    private static int page = 0;
    private static final int size = 5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_music);

    }


    @Override
    protected void initView() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext);
        recMusicFragment.setLayoutManager(manager);
        recMusicFragment.addItemDecoration(new MyRecycleViewDecoration(mContext,
                MyRecycleViewDecoration.VERTICAL_LIST));
        adapter = new MusicAdapter(mContext,null,R.layout.item_music);
        recMusicFragment.setAdapter(adapter);
        swpMusicFragment.setOnRefreshListener(this);
        swpMusicFragment.setColorSchemeResources(R.color.colorPrimary);
    }

    @Override
    public void getHttpData() {
        Map<String, String> params = new HashMap<>();
        params.put("page", "" + page);
        params.put("size", "" + size);
        EasyHttp.doPost(mContext, ServerURL.GET_MUSIC_INFO, params, null, MusicPage.class, false, new EasyHttp.OnEasyHttpDoneListener<MusicPage>() {
            @Override
            public void onEasyHttpSuccess(ResultBean<MusicPage> resultBean) {
                if (page == 0) {
                    adapter.mData.clear();
                }
                adapter.mData.addAll(resultBean.data.content);
                adapter.notifyDataSetChanged();
                swpMusicFragment.setRefreshing(false);
            }

            @Override
            public void onEasyHttpFailure(int code, String message) {
                ToastUtil.showToastDefault(mContext, message);
                swpMusicFragment.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        page = 0;
        getHttpData();
    }
}
