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

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Bean.NetFaDanBean;
import efan.com.money.Main.MainActivity;
import efan.com.money.R;
import efan.com.money.Util.net.rx.BaseSubscriber;
import efan.com.money.Util.net.rx.RxRestClient;
import efan.com.money.Util.storage.MainPreference;
import efan.com.money.staticfunction.StaticUrl;
import efan.com.money.staticfunction.StaticValue;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
    //界面
    private TextView jd_main_item_lx_tv;
    private TextView jd_main_item_title_tv;
    private TextView jd_xiangqing_qian;
    private TextView jd_xiangxi_zongshu;
    private ImageView jd_main_item_iv;
    private NetFaDanBean netFaDanBean;

    int id;
    JSONObject object = new JSONObject();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jd_xiangxi);
        id = getIntent().getIntExtra("id", 0);
        InitView();
        InitEvent();
        GetFaDan(id);

    }

    private void GetFaDan(int i) {
        RxRestClient.builder()
                .url(StaticUrl.GET_FA_DAN)
                .params("fd_id", i)
                .params("page", -1)
                .load(JD_Xiangxi.this)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>(this) {
                    @Override
                    public void onNext(String s) {

                        if (object.parseObject(s).getString("success").equals("true")) {
                            List<NetFaDanBean> mList = object.parseObject(object.parseObject(s).getString("data"),
                                    new TypeReference<ArrayList<NetFaDanBean>>() {
                                    });
                            netFaDanBean = mList.get(0);
                            SetValue(mList.get(0));
                            setSelect(0);
                        } else {
                            Toast.makeText(JD_Xiangxi.this, "获取数据失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void SetValue(NetFaDanBean netFaDanBean) {
        jd_main_item_lx_tv.setText("[" + netFaDanBean.getTuiGuang() + "]");
        jd_main_item_title_tv.setText(netFaDanBean.getFd_MingCheng());
        jd_xiangqing_qian.setText("￥" + netFaDanBean.getFd_JiaGe());
        Picasso.with(this)
                .load(StaticUrl.BASE_URL + netFaDanBean.getTg_leixing_iv())
                .error(R.mipmap.ic_launcher)
                .into(jd_main_item_iv);
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

        jd_main_item_lx_tv = (TextView) findViewById(R.id.jd_main_item_lx_tv);
        jd_main_item_title_tv = (TextView) findViewById(R.id.jd_main_item_title_tv);
        jd_xiangqing_qian = (TextView) findViewById(R.id.jd_xiangqing_qian);
        jd_xiangxi_zongshu = (TextView) findViewById(R.id.jd_xiangxi_zongshu);
        jd_main_item_iv = (ImageView) findViewById(R.id.jd_main_item_iv);
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
        ppw_fin_indent_get_qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReceivingOrder();
            }
        });
    }

    private void ReceivingOrder() {
        RxRestClient.builder()
                .url(StaticUrl.INDEX_DING_DAN)
                .params("User_Jd_Id", MainPreference.getCustomAppProfile("uid"))
                .params("Fd_Id", id)
                .params("Dd_ZhuangTai", StaticValue.INDENT_CHECK)
                .params("Dd_ShenHe_iv1", "")
                .params("Dd_ShenHe_iv2", "")
                .params("Dd_Time", System.currentTimeMillis())
                .build()
                .post()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>(JD_Xiangxi.this) {
                    @Override
                    public void onNext(String s) {
                        if (poPupWindow != null) {
                            poPupWindow.dismiss();
                            poPupWindow = null;
                        }
                        if (object.parseObject(s).getString("success").equals("true")) {
                            Toast.makeText(JD_Xiangxi.this, "领取成功，快去做任务吧！", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(JD_Xiangxi.this, MainActivity.class);
                            startActivity(intent);
                            JD_Xiangxi.this.finish();
                        } else {
                            Toast.makeText(JD_Xiangxi.this, "领取失败！", Toast.LENGTH_SHORT).show();
                        }

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
                    xx_fra = JD_Xiangxi_Rw_Fragment.newInstence(netFaDanBean);
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
