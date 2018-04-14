package efan.com.money.Main.Find;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import efan.com.money.Main.MainActivity;
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

    private PopupWindow poPupWindow;
    private TextView ppw_fin_indent_get_qd;
    private TextView ppw_fin_indent_get_qx;

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
                showpopupWindow(jd_xiangqing_lqrw);
                break;
        }
    }

    private void showpopupWindow(View parent) {
        if (poPupWindow == null) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.ppw_find_indent_get, null);
            poPupWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, true);
            initPop(view);

            view.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View arg0, MotionEvent arg1) {
                    if (poPupWindow != null && poPupWindow.isShowing()) {
                        poPupWindow.dismiss();
                        poPupWindow = null;
                    }
                    return true;
                }
            });

        }
        poPupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        poPupWindow.setFocusable(true);
        poPupWindow.setOutsideTouchable(true);
        poPupWindow.setBackgroundDrawable(new BitmapDrawable());
        poPupWindow
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        poPupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
    }

    private void initPop(View view) {
        ppw_fin_indent_get_qd = (TextView) view.findViewById(R.id.ppw_fin_indent_get_qd);
        ppw_fin_indent_get_qx = (TextView) view.findViewById(R.id.ppw_fin_indent_get_qd);
        ppw_fin_indent_get_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poPupWindow.dismiss();
                poPupWindow = null;
            }
        });
        ppw_fin_indent_get_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poPupWindow.dismiss();
                poPupWindow = null;
                Toast.makeText(JD_Xiangxi.this, "领取成功，快去做任务吧！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(JD_Xiangxi.this, MainActivity.class);
                startActivity(intent);
                JD_Xiangxi.this.finish();
            }
        });
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
