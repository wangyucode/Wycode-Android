package cn.wycode.wycode.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;

import butterknife.Bind;
import cn.wycode.wycode.BaseActivity;
import cn.wycode.wycode.R;
import cn.wycode.wycode.model.Music;

/**
 * 音乐详情
 * Created by wangyu on 16/3/11.
 */
public class MusicDetailActivity extends BaseActivity {

    Music music;
    @Bind(R.id.web_music)
    WebView webMusic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        music = (Music) getIntent().getExtras().get("music");
        setContentViewWithDefaultTitle(R.layout.activity_music_detail, music != null ? music.name : "音乐详情");
    }

    @Override
    protected void initView() {
        WebSettings webSettings = webMusic.getSettings();
        webSettings.setJavaScriptEnabled(true);
        if (music != null && !TextUtils.isEmpty(music.url)) {
            webMusic.loadUrl(music.url);
        } else {
            finish();
        }
    }
}
