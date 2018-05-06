package efan.com.money.Main.Mine.Indent.Particular;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;
import efan.com.money.Bean.NetDingDanBean;
import efan.com.money.Main.BaseActivity;
import efan.com.money.R;
import efan.com.money.Util.callback.CallbackManager;
import efan.com.money.Util.callback.CallbackType;
import efan.com.money.Util.callback.IGlobalCallback;
import efan.com.money.Util.net.rx.BaseSubscriber;
import efan.com.money.Util.net.rx.RxRestClient;
import efan.com.money.staticfunction.StaticUrl;
import efan.com.money.staticfunction.StaticValue;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/12.
 */

public class Jd_Dd_Jyz extends BaseActivity {
    @BindView(R.id.indent_title_bar)
    TextView indent_title_bar;
    @BindView(R.id.indent_back_iv)
    ImageView indent_back_iv;
    @BindView(R.id.indent_dingdan_iv)
    ImageView indent_dingdan_iv;
    @BindView(R.id.indent_dingdan_lx_tv)
    TextView indent_dingdan_lx_tv;
    @BindView(R.id.indent_dingdan_title_tv)
    TextView indent_dingdan_title_tv;
    @BindView(R.id.indent_dingdan_qian)
    TextView indent_dingdan_qian;
    @BindView(R.id.indent_dingdan_zhuangtai_tv)
    TextView indent_dingdan_zhuangtai_tv;
    @BindView(R.id.indent_dingdan_rulx_tv)
    TextView indent_dingdan_rulx_tv;
    @BindView(R.id.indent_dingdan_zhyq_tv)
    TextView indent_dingdan_zhyq_tv;
    @BindView(R.id.indent_dingdan_scjt_tv)
    TextView indent_dingdan_scjt_tv;
    @BindView(R.id.indent_dingdan_rwnr_tv)
    TextView indent_dingdan_rwnr_tv;
    @BindView(R.id.indent_dingdan_rwbz_tv)
    TextView indent_dingdan_rwbz_tv;
    @BindView(R.id.indent_dingdan_sc1_tv)
    RelativeLayout indent_dingdan_sc1_tv;
    @BindView(R.id.indent_dingdan_sc2_tv)
    RelativeLayout indent_dingdan_sc2_tv;
    @BindView(R.id.indent_dingdan_tj_tv)
    TextView indent_dingdan_tj_tv;
    @BindView(R.id.indent_dingdan_tj_rl)
    RelativeLayout indent_dingdan_tj_rl;
    @BindView(R.id.indent_dingdan_fdr_tv)
    TextView indent_dingdan_fdr_tv;
    @BindView(R.id.indent_dingdan_sxsj_tv)
    TextView indent_dingdan_sxsj_tv;
    @BindView(R.id.indent_dingdan_jt1_iv)
    ImageView indent_dingdan_jt1_iv;
    @BindView(R.id.indent_dingdan_jt2_iv)
    ImageView indent_dingdan_jt2_iv;
    @BindView(R.id.jd_indent_share_iv)
    ImageView jd_indent_share_iv;

    private int type;
    private NetDingDanBean data;
    JSONObject object = new JSONObject();

    private PopupWindow poPupWindow;
    private TextView ppw_fin_indent_get_qd;
    private TextView ppw_fin_indent_get_qx;
    private TextView main_xxwh_xx_ppw_nr;
    private int ddid;

    @OnClick({R.id.indent_back_iv, R.id.indent_dingdan_tj_rl, R.id.indent_dingdan_jt1_iv, R.id.indent_dingdan_jt2_iv, R.id.jd_indent_share_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.indent_back_iv:
                finish();
                break;
            case R.id.indent_dingdan_tj_rl:
                showpopupWindow(indent_dingdan_tj_rl);
                break;
            case R.id.indent_dingdan_jt1_iv:
                if (type == StaticValue.JYZ_TO_INDENT) {
                    SelectPhoto(1);
                } else {
                    smallImgClick(1);
                }
                break;
            case R.id.indent_dingdan_jt2_iv:
                if (type == StaticValue.JYZ_TO_INDENT) {
                    SelectPhoto(2);
                } else {
                    smallImgClick(2);
                }
                break;
            case R.id.jd_indent_share_iv:
                showShare();
                break;
        }
    }

    private void smallImgClick(int i) {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        ImageView imgView = getView(i);
        dialog.setContentView(imgView);
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.hide();
                dialog.cancel();
            }
        });
        dialog.show();
    }

    private ImageView getView(int i) {
        ImageView imgView = new ImageView(this);
        imgView.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
        if (i == 1) {
            Picasso.with(Jd_Dd_Jyz.this)
                    .load(StaticUrl.BASE_URL + data.getDd_ShenHe_iv1())
                    .error(R.mipmap.tj)
                    .into(imgView);
        } else {
            Picasso.with(Jd_Dd_Jyz.this)
                    .load(StaticUrl.BASE_URL + data.getDd_ShenHe_iv2())
                    .error(R.mipmap.tj)
                    .into(imgView);
        }
        return imgView;
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("闲钱分享");
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("https://www.baidu.com/?tn=87048150_dg&ch=1");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl(StaticUrl.BASE_URL + "Money/files/banner/yingxiao.jpg");
        //  oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("https://www.baidu.com/?tn=87048150_dg&ch=1");
        // 启动分享GUI
        oks.show(this);
    }


    private void SelectPhoto(final int i) {
        CallbackManager.getInstence()
                .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {

                    @Override
                    public void executeCallback(@Nullable Uri args) {
                        if (i == 1) {
                            Picasso.with(Jd_Dd_Jyz.this)
                                    .load(args)
                                    .into(indent_dingdan_jt1_iv);
                        } else if (i == 2) {
                            Picasso.with(Jd_Dd_Jyz.this)
                                    .load(args)
                                    .into(indent_dingdan_jt2_iv);
                        }
                        upPhotoUrlData(args.getPath(), i);
                    }
                });
        startCameraWithCheck();
    }

    private void upPhotoUrlData(String path, final int i) {
        OkHttpClient client = new OkHttpClient();
        File file = new File(path);
        if (!file.exists()) {
            Toast.makeText(Jd_Dd_Jyz.this, file.getAbsolutePath() + "图片不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        RequestBody body = RequestBody.create(MediaType.parse("applection/octet-stream"), file);
        final Request request = new Request
                .Builder()
                .url(StaticUrl.BASE_URL + StaticUrl.POST_FILE)
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                Jd_Dd_Jyz.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Jd_Dd_Jyz.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                if (object.parseObject(str).getString("success").equals("true")) {
                    UpDataPhoto(object.parseObject(str).getString("massage"), i);
                } else {
                    Toast.makeText(Jd_Dd_Jyz.this, "上传失败", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void UpDataPhoto(String path, int i) {
        RxRestClient.builder()
                .url(StaticUrl.UP_DATA_PHOTO)
                .params("ddid", ddid)
                .params("path", path)
                .params("tag", i)
                .build()
                .post()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>(this) {
                    @Override
                    public void onNext(String s) {
                        if (object.parseObject(s).getString("success").equals("true")) {
                            Toast.makeText(Jd_Dd_Jyz.this, "上传图片成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Jd_Dd_Jyz.this, "上传图片失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


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
        String Type = "";
        if (type == StaticValue.JYZ_TO_INDENT) {//交易中，提交审核
            main_xxwh_xx_ppw_nr.setText("是否确定提交审核");
            Type = StaticValue.INDENT_CHECK;
        } else if (type == StaticValue.SHZ_TO_INDENT) {//审核中，取消审核
            main_xxwh_xx_ppw_nr.setText("是否确定取消审核");
            Type = StaticValue.INDENT_CENTER;
        }
        ppw_fin_indent_get_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poPupWindow.dismiss();
                poPupWindow = null;
            }
        });

        final String finalType = Type;
        ppw_fin_indent_get_qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitData(finalType);
            }
        });
    }

    /**
     * 更新状态
     */
    private void submitData(String Type) {
        RxRestClient
                .builder()
                .load(Jd_Dd_Jyz.this)
                .url(StaticUrl.UP_DATA_ZT)
                .params("ddid", ddid)
                .params("zhuangtai", Type)
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
                            Jd_Dd_Jyz.this.finish();
                        } else {
                            Toast.makeText(Jd_Dd_Jyz.this, "更改状态失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jd_indent);
        ButterKnife.bind(this);
        getData();
    }


    private void getData() {
        ddid = getIntent().getIntExtra("id", 0);
        type = getIntent().getIntExtra("type", 0);
        initTitle();
        GetNetData();
    }

    private void GetNetData() {
        RxRestClient.builder()
                .load(Jd_Dd_Jyz.this)
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
                            Toast.makeText(Jd_Dd_Jyz.this, "数据返回错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void setValue() {
//        Picasso.with(Jd_Dd_Jyz.this)
//                .load(StaticUrl.BASE_URL+data.)
        indent_dingdan_lx_tv.setText("[" + data.getTuiGuang() + "]");
        indent_dingdan_title_tv.setText(data.getFd_MingCheng());
        indent_dingdan_qian.setText("￥" + data.getFd_JiaGe());
        indent_dingdan_fdr_tv.setText(data.getName());
        indent_dingdan_sxsj_tv.setText(data.getZhangHao());
        indent_dingdan_rulx_tv.setText(data.getTuiGuang());
        indent_dingdan_zhyq_tv.setText(data.getFd_YaoQiu());
        indent_dingdan_rwnr_tv.setText(data.getFd_NeiRong());
        indent_dingdan_rwbz_tv.setText(data.getFd_BeiZhu());
        Picasso.with(Jd_Dd_Jyz.this)
                .load(StaticUrl.BASE_URL + data.getDd_ShenHe_iv1())
                .error(R.mipmap.tj)
                .into(indent_dingdan_jt1_iv);
        Picasso.with(Jd_Dd_Jyz.this)
                .load(StaticUrl.BASE_URL + data.getDd_ShenHe_iv2())
                .error(R.mipmap.tj)
                .into(indent_dingdan_jt2_iv);
    }


    private void initTitle() {
        if (type == StaticValue.JYCG_TO_INDENT) {
            indent_title_bar.setText("交易成功");
            indent_dingdan_zhuangtai_tv.setText("已完成");
            indent_dingdan_tj_rl.setVisibility(View.GONE);
            indent_dingdan_sc1_tv.setVisibility(View.GONE);
            indent_dingdan_sc2_tv.setVisibility(View.GONE);
        } else if (type == StaticValue.JYZ_TO_INDENT) {
            indent_title_bar.setText("交易中");
            indent_dingdan_zhuangtai_tv.setText("已完成");
        } else if (type == StaticValue.SHZ_TO_INDENT) {
            indent_title_bar.setText("审核中");
            indent_dingdan_tj_tv.setText("取消审核");
            indent_dingdan_zhuangtai_tv.setText("已完成");
            indent_dingdan_tj_tv.setBackgroundResource(R.drawable.jd_jx_sc_rj);
            indent_dingdan_sc1_tv.setVisibility(View.GONE);
            indent_dingdan_sc2_tv.setVisibility(View.GONE);
        }
    }
}
