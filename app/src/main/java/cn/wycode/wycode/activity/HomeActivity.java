package cn.wycode.wycode.activity;


import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import cn.wycode.wycode.BaseActivity;
import cn.wycode.wycode.R;
import cn.wycode.wycode.activity.fragement.BaseFragment;
import cn.wycode.wycode.activity.fragement.MessageFragment;
import cn.wycode.wycode.activity.fragement.MovieFragment;
import cn.wycode.wycode.activity.fragement.MusicFragment;
import cn.wycode.wycode.adapter.PagerFragmentAdapter;
import cn.wycode.wycode.view.BottomNavigationLayout;


/**
 * Created by huangyi on 16/2/25.
 */
public class HomeActivity extends BaseActivity implements BottomNavigationLayout.OnTabSelectedListener {

    @Bind(R.id.vp_home)
    ViewPager vpHome;
    @Bind(R.id.bomNavLay_home)
    BottomNavigationLayout bomNavLayHome;
    FragmentManager mFragmentManager;
    PagerFragmentAdapter adapter;
    MovieFragment mMovieFragment = new MovieFragment();
    MusicFragment mMusicFragment = new MusicFragment();
    MessageFragment mMessageFragment = new MessageFragment();
    ArrayList<BaseFragment> mFragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithDefaultTitle(R.layout.activity_home, "首页");
    }

    @Override
    protected void initView() {
        mIvTitleBack.setVisibility(View.GONE);
        bomNavLayHome.setOnTabSelectedListener(this);
        mFragmentManager = getSupportFragmentManager();
        mFragmentList.add(mMovieFragment);
        mFragmentList.add(mMusicFragment);
        mFragmentList.add(mMessageFragment);
        ArrayList<String> tittleArray = new ArrayList<>();
        tittleArray.add("电影");
        tittleArray.add("音乐");
        tittleArray.add("留言板");
        adapter = new PagerFragmentAdapter(mFragmentManager, mFragmentList, tittleArray);
        vpHome.setAdapter(adapter);
        int[] unselected = new int[]{R.drawable.music_svg_gray, R.drawable.movie_svg_gray, R.drawable.music_svg_gray};
        int[] selected = new int[]{R.drawable.music_svg, R.drawable.movie_svg, R.drawable.music_svg};
        bomNavLayHome.setupWithViewPager(vpHome);
        bomNavLayHome.setTabImage(unselected, selected);
    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                mTvTitleName.setText("影视");
                break;
            case 1:
                mTvTitleName.setText("音乐");
                break;
            case 2:
                mTvTitleName.setText("留言板");
                break;
        }
    }
}
