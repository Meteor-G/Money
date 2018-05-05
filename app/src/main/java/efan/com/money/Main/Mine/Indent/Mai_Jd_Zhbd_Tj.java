package efan.com.money.Main.Mine.Indent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Bean.NetZhangHao;
import efan.com.money.R;
import efan.com.money.Util.net.rx.BaseSubscriber;
import efan.com.money.Util.net.rx.RxRestClient;
import efan.com.money.staticfunction.StaticUrl;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/9/19.
 */

public class Mai_Jd_Zhbd_Tj extends AppCompatActivity implements View.OnClickListener {
    private ImageView jmai_1_zhbd_tj_iv;
    private TextView mai_1_zhbd_tj_lx_tv;
    private EditText mai_1_zhbd_tj_zh_ed;
    private RelativeLayout mai_1_zhbd_tj_sh;
    private MaterialDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mai_1_zhbd_tj);
        InitView();
        InitEvent();
    }

    private void InitEvent() {
        jmai_1_zhbd_tj_iv.setOnClickListener(this);
        mai_1_zhbd_tj_lx_tv.setOnClickListener(this);
        mai_1_zhbd_tj_sh.setOnClickListener(this);
    }

    private void InitView() {
        jmai_1_zhbd_tj_iv = (ImageView) findViewById(R.id.jmai_1_zhbd_tj_iv);
        mai_1_zhbd_tj_lx_tv = (TextView) findViewById(R.id.mai_1_zhbd_tj_lx_tv);
        mai_1_zhbd_tj_zh_ed = (EditText) findViewById(R.id.mai_1_zhbd_tj_zh_ed);
        mai_1_zhbd_tj_sh = (RelativeLayout) findViewById(R.id.mai_1_zhbd_tj_sh);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jmai_1_zhbd_tj_iv:
                finish();
                break;
            case R.id.mai_1_zhbd_tj_lx_tv:
                showDialogView();
                break;
            case R.id.mai_1_zhbd_tj_sh:
                if (TextUtils.isEmpty(mai_1_zhbd_tj_lx_tv.getText().toString())) {
                    Toast.makeText(Mai_Jd_Zhbd_Tj.this, "帐号类型不能为空", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(mai_1_zhbd_tj_zh_ed.getText().toString())) {
                    Toast.makeText(Mai_Jd_Zhbd_Tj.this, "帐号不能为空", Toast.LENGTH_SHORT).show();
                }
                if (!TextUtils.isEmpty(mai_1_zhbd_tj_lx_tv.getText().toString()) && !TextUtils.isEmpty(mai_1_zhbd_tj_zh_ed.getText().toString())) {
                    Toast.makeText(Mai_Jd_Zhbd_Tj.this, "帐号已提交审核", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    private void showDialogView() {
        RxRestClient.builder()
                .url(StaticUrl.GET_ZHANG_HAO)
                .load(Mai_Jd_Zhbd_Tj.this)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>(Mai_Jd_Zhbd_Tj.this) {
                    @Override
                    public void onNext(String s) {
                        JSONObject object = new JSONObject();
                        String data = object.parseObject(s).getString("data");

                        List<NetZhangHao> mList = object.parseObject(data,
                                new TypeReference<ArrayList<NetZhangHao>>() {
                                });
                        int size = mList.size();
                        String[] list = new String[size];
                        for (int i = 0; i < size; i++) {
                            list[i] = mList.get(i).getZh_leixing();
                        }
                        ShowDialogView(list);
                    }
                });
    }

    private void ShowDialogView(String[] list) {
        dialog = new MaterialDialog.Builder(Mai_Jd_Zhbd_Tj.this)
                .title("选择帐号类型")
                .items(list)
                .autoDismiss(false)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        mai_1_zhbd_tj_lx_tv.setText(text);
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
