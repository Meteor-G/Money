package efan.com.money.Main.Mine.Indent.Particular;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import efan.com.money.Bean.NetDingDanBean;
import efan.com.money.R;
import efan.com.money.Util.net.rx.BaseSubscriber;
import efan.com.money.Util.net.rx.RxRestClient;
import efan.com.money.staticfunction.StaticUrl;
import efan.com.money.staticfunction.StaticValue;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.R.attr.id;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/14.
 */

public class Fd_Dd_Indent extends AppCompatActivity {
    @BindView(R.id.indent_back_iv)
    ImageView indent_back_iv;
    @BindView(R.id.indent_title_bar)
    TextView indent_title_bar;
    @BindView(R.id.fd_indent_dingdan_iv)
    ImageView fd_indent_dingdan_iv;
    @BindView(R.id.fd_indent_dingdan_lx_tv)
    TextView fd_indent_dingdan_lx_tv;
    @BindView(R.id.fd_indent_dingdan_title_tv)
    TextView fd_indent_dingdan_title_tv;
    @BindView(R.id.fd_indent_dingdan_qian)
    TextView fd_indent_dingdan_qian;
    @BindView(R.id.fd_indent_dingdan_zhuangtai_tv)
    TextView fd_indent_dingdan_zhuangtai_tv;
    @BindView(R.id.fd_indent_dingdan_jdr_tv)
    TextView fd_indent_dingdan_jdr_tv;
    @BindView(R.id.fd_indent_dingdan_sxsj_tv)
    TextView fd_indent_dingdan_sxsj_tv;
    @BindView(R.id.fd_indent_dingdan_rulx_tv)
    TextView fd_indent_dingdan_rulx_tv;
    @BindView(R.id.fd_indent_dingdan_zhyq_tv)
    TextView fd_indent_dingdan_zhyq_tv;
    @BindView(R.id.fd_indent_dingdan_rwnr_tv)
    TextView fd_indent_dingdan_rwnr_tv;
    @BindView(R.id.fd_indent_dingdan_rwbz_tv)
    TextView fd_indent_dingdan_rwbz_tv;
    @BindView(R.id.fd_indent_dingdan_jt1_iv)
    ImageView fd_indent_dingdan_jt1_iv;
    @BindView(R.id.fd_indent_dingdan_jt2_iv)
    ImageView fd_indent_dingdan_jt2_iv;
    @BindView(R.id.fd_indent_sh_ll)
    LinearLayout fd_indent_sh_ll;
    @BindView(R.id.fd_indent_btg_rl)
    RelativeLayout fd_indent_btg_rl;
    @BindView(R.id.fd_indent_tg_rl)
    RelativeLayout fd_indent_tg_rl;

    private int type;
    private int ddid;
    private NetDingDanBean data;
    JSONObject object = new JSONObject();

    private PopupWindow poPupWindow;
    private TextView ppw_fin_indent_get_qd;
    private TextView ppw_fin_indent_get_qx;
    private TextView main_xxwh_xx_ppw_nr;

    @OnClick({R.id.indent_back_iv, R.id.fd_indent_btg_rl, R.id.fd_indent_tg_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.indent_back_iv:
                finish();
                break;
            case R.id.fd_indent_btg_rl:

                break;
            case R.id.fd_indent_tg_rl:
                showpopupWindow(fd_indent_tg_rl);
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
        main_xxwh_xx_ppw_nr = (TextView) view.findViewById(R.id.main_xxwh_xx_ppw_nr);
        main_xxwh_xx_ppw_nr.setText("是否确定通过审核");
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
                submitData();
            }
        });
    }

    private void submitData() {
        RxRestClient
                .builder()
                .load(Fd_Dd_Indent.this)
                .url(StaticUrl.UP_DATA_ZT)
                .params("ddid", ddid)
                .params("zhuangtai", StaticValue.INDENT_SUCCESS)
                .build()
                .post()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>(this) {
                    @Override
                    public void onNext(String s) {
                        if (poPupWindow != null) {
                            poPupWindow.dismiss();
                            poPupWindow = null;
                        }
                        JSONObject object = new JSONObject();
                        if (object.parseObject(s).getString("success").equals("true")) {
                            Toast.makeText(Fd_Dd_Indent.this, "更改状态成功", Toast.LENGTH_SHORT).show();
                            Fd_Dd_Indent.this.finish();
                        } else {
                            Toast.makeText(Fd_Dd_Indent.this, "更改状态失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fd_indent);
        ButterKnife.bind(this);
        GetData();
    }

    private void GetData() {
        ddid = getIntent().getIntExtra("id", 0);
        type = getIntent().getIntExtra("type", 0);
        Toast.makeText(this, "传过来的值为" + id + " 传输类型" + type, Toast.LENGTH_SHORT).show();
        initTitle();
        GetNetData();
    }

    private void GetNetData() {
        RxRestClient.builder()
                .url(StaticUrl.GET_DINGD_DAN_DDID)
                .params("ddid", ddid)
                .params("type", "jd")
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>(this) {
                    @Override
                    public void onNext(String s) {
                        if (object.parseObject(s).getString("success").equals("true")) {
                            data = object.parseObject(object.parseObject(s).getString("bean"), NetDingDanBean.class);
                            setValue();
                        } else {
                            Toast.makeText(Fd_Dd_Indent.this, "数据返回错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void setValue() {
        fd_indent_dingdan_lx_tv.setText("[" + data.getTuiGuang() + "]");
        fd_indent_dingdan_title_tv.setText(data.getFd_MingCheng());
        fd_indent_dingdan_qian.setText("￥" + data.getFd_JiaGe());
        fd_indent_dingdan_jdr_tv.setText(data.getName());
        fd_indent_dingdan_sxsj_tv.setText(data.getZhangHao());
        fd_indent_dingdan_rulx_tv.setText(data.getTuiGuang());
        fd_indent_dingdan_zhyq_tv.setText(data.getFd_YaoQiu());
        fd_indent_dingdan_rwnr_tv.setText(data.getFd_NeiRong());
        fd_indent_dingdan_rwbz_tv.setText(data.getFd_BeiZhu());
        Picasso.with(Fd_Dd_Indent.this)
                .load(StaticUrl.BASE_URL + data.getDd_ShenHe_iv1())
                .error(R.mipmap.tj)
                .into(fd_indent_dingdan_jt1_iv);
        Picasso.with(Fd_Dd_Indent.this)
                .load(StaticUrl.BASE_URL + data.getDd_ShenHe_iv1())
                .error(R.mipmap.tj)
                .into(fd_indent_dingdan_jt2_iv);
    }

    private void initTitle() {
        if (type == StaticValue.FD_DSH_TO_INDENT) {
            indent_title_bar.setText("待审核");
            fd_indent_dingdan_zhuangtai_tv.setText("待审核");
        } else if (type == StaticValue.FD_JXS_TO_INDENT) {
            indent_title_bar.setText("交易中");
            fd_indent_dingdan_zhuangtai_tv.setText("交易中");
            fd_indent_sh_ll.setVisibility(View.GONE);
        } else if (type == StaticValue.FD_JYCG_TO_INDENT) {
            indent_title_bar.setText("交易成功");
            fd_indent_dingdan_zhuangtai_tv.setText("交易成功");
            fd_indent_sh_ll.setVisibility(View.GONE);
        }
    }
}
