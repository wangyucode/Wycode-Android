package cn.wycode.wycode.activity.fragement;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import cn.wycode.wycode.Constants;
import cn.wycode.wycode.R;
import cn.wycode.wycode.ServerURL;
import cn.wycode.wycode.adapter.MovieAdapter;
import cn.wycode.wycode.adapter.baseadapter.MyRecycleViewDecoration;
import cn.wycode.wycode.model.MoviePage;
import cn.wycode.wycode.net.EasyHttp;
import cn.wycode.wycode.net.ResultBean;
import cn.wycode.wycode.utils.ToastUtil;


/**
 * Created by huangyi on 16/2/25.
 * Movie Fragment
 */
public class MovieFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.rec_movie_fragment)
    RecyclerView recMovieFragment;

    MovieAdapter adapter;

    private static int page = 0;
    private static final int size = 5;

    @Bind(R.id.swp_movie_fragment)
    SwipeRefreshLayout swpMoveFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_movie);
    }

    @Override
    protected void initView() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext);
        recMovieFragment.setLayoutManager(manager);
        recMovieFragment.addItemDecoration(new MyRecycleViewDecoration(mContext,
                MyRecycleViewDecoration.VERTICAL_LIST));
        adapter = new MovieAdapter(mContext, null, R.layout.item_movie);
        recMovieFragment.setAdapter(adapter);
        swpMoveFragment.setOnRefreshListener(this);
        swpMoveFragment.setColorSchemeResources(R.color.colorPrimary);
    }

    @Override
    public void getHttpData() {
        Map<String, String> params = new HashMap<>();
        params.put("page", "" + page);
        params.put("size", "" + size);
        EasyHttp.doPost(mContext, ServerURL.GET_MOVIE_INFO, params, null, MoviePage.class, false, new EasyHttp.OnEasyHttpDoneListener<MoviePage>() {
            @Override
            public void onEasyHttpSuccess(ResultBean<MoviePage> resultBean) {
                if (page == 0) {
                    adapter.mData.clear();
                }
                adapter.mData.addAll(resultBean.data.content);
                adapter.notifyDataSetChanged();
                swpMoveFragment.setRefreshing(false);
            }

            @Override
            public void onEasyHttpFailure(int code, String message) {
                ToastUtil.showToastDefault(mContext, message);
                swpMoveFragment.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        page = 0;
        getHttpData();
    }

}
