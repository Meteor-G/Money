package efan.com.money.Main.Publish;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import efan.com.money.R;
import efan.com.money.Util.net.rx.BaseSubscriber;
import efan.com.money.Util.net.rx.RxRestClient;
import efan.com.money.staticfunction.StaticUrl;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/9/12.
 */

public class FD_Xiangxi extends AppCompatActivity implements View.OnClickListener {
    private ImageView fb_xx_fanhui;
    private EditText fd_xiangxi_rwzs_et;
    private EditText fd_xiangxi_rwjg_et;
    private TextView fd_xiangxi_xzf;
    private Double rwzs = 0.0, rwjg = 0.0;
    private RelativeLayout fd_xiangxi_zf_rl;
    private EditText fd_xiangxi_rwnr_ed;
    private EditText fd_xiangxi_rwmc_ed;
    private EditText fd_xiangxi_zhxq_ed;
    private EditText fd_xiangxi_rwbz_ed;
    private String zhanghao;
    private String tuiguang;
    private TextView fd_xiangxi_sxsj;
    private TextView fb_xx_lx;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fd_xiangxi);
        InitView();
        InitEvent();
        GetIntentData();
    }

    private void GetIntentData() {
        Intent intent = getIntent();
        zhanghao = intent.getStringExtra("zhanghao");
        tuiguang = intent.getStringExtra("tuiguang");
        fd_xiangxi_sxsj.setText(zhanghao);
        fb_xx_lx.setText(tuiguang);
    }

    private void InitEvent() {
        fb_xx_fanhui.setOnClickListener(this);
        fd_xiangxi_zf_rl.setOnClickListener(this);
    }

    private void InitView() {
        fb_xx_fanhui = (ImageView) findViewById(R.id.fb_xx_fanhui);
        fd_xiangxi_rwzs_et = (EditText) findViewById(R.id.fd_xiangxi_rwzs_et);
        fd_xiangxi_rwjg_et = (EditText) findViewById(R.id.fd_xiangxi_rwjg_et);
        fd_xiangxi_xzf = (TextView) findViewById(R.id.fd_xiangxi_xzf);
        fd_xiangxi_zf_rl = (RelativeLayout) findViewById(R.id.fd_xiangxi_zf_rl);
        fd_xiangxi_rwnr_ed = (EditText) findViewById(R.id.fd_xiangxi_rwnr_ed);
        fd_xiangxi_rwmc_ed = (EditText) findViewById(R.id.fd_xiangxi_rwmc_ed);
        fd_xiangxi_zhxq_ed = (EditText) findViewById(R.id.fd_xiangxi_zhxq_ed);
        fd_xiangxi_sxsj = (TextView) findViewById(R.id.fd_xiangxi_sxsj);
        fb_xx_lx = (TextView) findViewById(R.id.fb_xx_lx);
        fd_xiangxi_rwbz_ed = (EditText) findViewById(R.id.fd_xiangxi_rwbz_ed);

        fd_xiangxi_rwzs_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (fd_xiangxi_rwzs_et.getText().toString().trim().equals("")) {
                    double a1 = 0.0;
                    rwzs = a1;
                } else {
                    rwzs = Double.valueOf(fd_xiangxi_rwzs_et.getText().toString().trim());
                }
                fd_xiangxi_xzf.setText((rwzs * rwjg) + "");
//                ShowTips.showTips(FD_Xiangxi.this, rwzs + "");
            }
        });
        fd_xiangxi_rwjg_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (fd_xiangxi_rwjg_et.getText().toString().trim().equals("")) {
                    double a1 = 0.0;
                    rwjg = a1;
                } else {
                    rwjg = Double.valueOf(fd_xiangxi_rwjg_et.getText().toString().trim());
                }
                fd_xiangxi_xzf.setText((rwzs * rwjg) + "");
//                ShowTips.showTips(FD_Xiangxi.this, rwjg + "");
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fb_xx_fanhui:
                finish();
                break;
            case R.id.fd_xiangxi_zf_rl:
                SubmitData();
                break;
        }
    }

    private void SubmitData() {
        int i = 0, y = 0, z = 0, w = 0, n = 0;
        //任务名称
        String rwmc = fd_xiangxi_rwmc_ed.getText().toString().trim();
        //帐号需求
        String zhxq = fd_xiangxi_zhxq_ed.getText().toString().trim();
        //任务内容
        String rwnr = fd_xiangxi_rwnr_ed.getText().toString().trim();
        //任务总数
        String rwzs = fd_xiangxi_rwzs_et.getText().toString().trim();
        //任务价格
        String rwjg = fd_xiangxi_rwjg_et.getText().toString().trim();
        //任务备注
        String rwbz = fd_xiangxi_rwbz_ed.getText().toString().trim();
        if (rwmc.isEmpty()) {
            Toast.makeText(FD_Xiangxi.this, "任务名称不能为空", Toast.LENGTH_SHORT).show();
        } else {
            i = 1;
        }
        if (zhxq.isEmpty()) {
            Toast.makeText(FD_Xiangxi.this, "帐号要求不能为空", Toast.LENGTH_SHORT).show();
        } else {
            y = 1;
        }
        if (rwnr.isEmpty()) {
            Toast.makeText(FD_Xiangxi.this, "任务内容不能为空", Toast.LENGTH_SHORT).show();
        } else {
            z = 1;
        }
        if (rwzs.isEmpty()) {
            Toast.makeText(FD_Xiangxi.this, "任务总数不能为空", Toast.LENGTH_SHORT).show();
        } else {
            w = 1;
        }
        if (rwjg.isEmpty()) {
            Toast.makeText(FD_Xiangxi.this, "任务价格不能为空", Toast.LENGTH_SHORT).show();
        } else {
            n = 1;
        }
        if (i == 1 && y == 1 && z == 1 && w == 1 & n == 1) {
            upData(rwmc, zhxq, rwjg, rwzs, rwnr, rwbz);
            Toast.makeText(FD_Xiangxi.this, "提交成功", Toast.LENGTH_SHORT).show();
            i = 0;
            y = 0;
            z = 0;
            w = 0;
            n = 0;
        }
    }

    private void upData(String rwmc, String zhxq, String rwjg, String rwzs, String rwnr, String rwbz) {
        RxRestClient.builder()
                .url(StaticUrl.INDEX_FD_DAN)
                .params("User_Fd_Id", 10)
                .params("TuiGuang", tuiguang)
                .params("ZhangHao", zhanghao)
                .params("Fd_MingCheng", rwmc)
                .params("Fd_YaoQiu", zhxq)
                .params("Fd_JiaGe", rwjg)
                .params("Fd_ZongShu", rwzs)
                .params("Fd_NeiRong", rwnr)
                .params("Fd_BeiZhu", rwbz)
                .params("Fd_ZhuangTai", 0)
                .params("Fd_Time", System.currentTimeMillis())
                .load(FD_Xiangxi.this)
                .build()
                .post()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>(FD_Xiangxi.this) {
                    @Override
                    public void onNext(String s) {
                        Toast.makeText(FD_Xiangxi.this, s, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
