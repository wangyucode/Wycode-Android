package cn.wycode.wycode.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import cn.wycode.wycode.BaseActivity;
import cn.wycode.wycode.R;
import cn.wycode.wycode.ServerURL;
import cn.wycode.wycode.model.Message;
import cn.wycode.wycode.net.EasyHttp;
import cn.wycode.wycode.net.ResultBean;
import cn.wycode.wycode.utils.ToastUtil;

/**
 * 添加留言
 * Created by wangyu on 16/3/13.
 */
public class MessageAddActivity extends BaseActivity {


    @Bind(R.id.et_message_add_content)
    EditText etMessageAddContent;
    @Bind(R.id.et_message_add_name)
    EditText etMessageAddName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithDefaultTitle(R.layout.activity_message_add, "新增留言");
    }

    @Override
    protected void initView() {

    }

    public void submit(View view) {
        if (!checkData()) {
            ToastUtil.showToastOnce(mContext, "请填写完整再提交");
            return;
        }

        Map<String, String> params = new HashMap<>();
        params.put("content", etMessageAddContent.getText().toString());
        params.put("name", etMessageAddName.getText().toString());
        params.put("secret", ServerURL.SECRET);
        EasyHttp.doPost(mContext, ServerURL.ADD_MESSAGE, params, null, Message.class, false, new EasyHttp.OnEasyHttpDoneListener<Message>() {
            @Override
            public void onEasyHttpSuccess(ResultBean<Message> resultBean) {
                setResult(RESULT_OK, getIntent().putExtra("message", resultBean.data));
                finish();
            }

            @Override
            public void onEasyHttpFailure(int code, String message) {
                ToastUtil.showToastDefault(mContext, message);
            }
        });

    }

    private boolean checkData() {
        return etMessageAddContent.getText().length() > 0 && etMessageAddName.length() > 0;
    }
}
