package efan.com.money.Main.Find;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import efan.com.money.R;
import efan.com.money.staticfunction.ShowTips;

/**
 * Created by Administrator on 2017/9/12.
 */

public class JD_Xiangxi extends AppCompatActivity implements View.OnClickListener {
    private ImageView jd_xiangxi_fanhui;

    private RelativeLayout jd_xiangxi_rwxx_rl;
    private RelativeLayout jd_xiangxi_rwlc_rl;

    private View jd_xiangxi_rwxx_view;
    private View jd_xiangxi_rwlc_view;

    private Fragment lc_fra;
    private Fragment xx_fra;
    private RelativeLayout jd_xiangqing_lqrw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jd_xiangxi);
        int i = getIntent().getIntExtra("id", 0);
        ShowTips.showTips(this, i + "");
        InitView();
        InitEvent();
        setSelect(0);
    }

    private void InitEvent() {
        jd_xiangxi_fanhui.setOnClickListener(this);
        jd_xiangxi_rwxx_rl.setOnClickListener(this);
        jd_xiangxi_rwlc_rl.setOnClickListener(this);
        jd_xiangqing_lqrw.setOnClickListener(this);
    }

    private void InitView() {
        jd_xiangxi_fanhui = (ImageView) findViewById(R.id.jd_xiangxi_fanhui);
        jd_xiangxi_rwxx_rl = (RelativeLayout) findViewById(R.id.jd_xiangxi_rwxx_rl);
        jd_xiangxi_rwlc_rl = (RelativeLayout) findViewById(R.id.jd_xiangxi_rwlc_rl);
        jd_xiangxi_rwxx_view = findViewById(R.id.jd_xiangxi_rwxx_view);
        jd_xiangxi_rwlc_view = findViewById(R.id.jd_xiangxi_rwlc_view);
        jd_xiangqing_lqrw = (RelativeLayout) findViewById(R.id.jd_xiangqing_lqrw);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jd_xiangxi_fanhui:
                finish();
                break;
            case R.id.jd_xiangxi_rwxx_rl:
                resetImgs();
                setSelect(0);
                break;
            case R.id.jd_xiangxi_rwlc_rl:
                resetImgs();
                setSelect(1);
                break;
            case R.id.jd_xiangqing_lqrw:
                Intent intent = new Intent(this, JD_Jx.class);
                startActivity(intent);
                break;
        }
    }

    private void setSelect(int i) {
        // 获取Fragement管理者
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        // 隐藏所有fragment
        hideFragment(transaction);
        // 把图片变亮
        // 设置内容区域
        switch (i) {
            case 0:
                if (xx_fra == null) {
                    xx_fra = new JD_Xiangxi_Rw_Fragment();
                    transaction.add(R.id.jd_xiangxi_frame, xx_fra);
                } else {
                    transaction.show(xx_fra);
                }
                jd_xiangxi_rwxx_view.setVisibility(View.VISIBLE);
                break;
            case 1:
                if (lc_fra == null) {
                    lc_fra = new JD_Xiangxi_Lc_Fragment();
                    transaction.add(R.id.jd_xiangxi_frame, lc_fra);
                } else {
                    transaction.show(lc_fra);
                }
                jd_xiangxi_rwlc_view.setVisibility(View.VISIBLE);
                break;
        }
        transaction.commit();

    }

    private void hideFragment(FragmentTransaction transaction) {
        // TODO Auto-generated method stub
        if (xx_fra != null) {
            transaction.hide(xx_fra);
        }
        if (lc_fra != null) {
            transaction.hide(lc_fra);
        }

    }

    private void resetImgs() {
        jd_xiangxi_rwxx_view.setVisibility(View.GONE);
        jd_xiangxi_rwlc_view.setVisibility(View.GONE);
    }
}
