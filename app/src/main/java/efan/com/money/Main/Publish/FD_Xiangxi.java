package efan.com.money.Main.Publish;

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

import efan.com.money.R;
import efan.com.money.staticfunction.ShowTips;

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fd_xiangxi);
        InitView();
        InitEvent();
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
        fd_xiangxi_rwnr_ed=(EditText)findViewById(R.id.fd_xiangxi_rwnr_ed);
        fd_xiangxi_rwmc_ed=(EditText)findViewById(R.id.fd_xiangxi_rwmc_ed);
        fd_xiangxi_zhxq_ed=(EditText)findViewById(R.id.fd_xiangxi_zhxq_ed);

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
                ShowTips.showTips(FD_Xiangxi.this, rwzs + "");
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
                ShowTips.showTips(FD_Xiangxi.this, rwjg + "");
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

                break;
        }
    }
}
