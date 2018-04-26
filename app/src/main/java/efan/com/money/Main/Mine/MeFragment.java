package efan.com.money.Main.Mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.squareup.picasso.Picasso;

import efan.com.money.Main.MainActivity;
import efan.com.money.Main.Mine.Indent.Mai_Fd_Fragment;
import efan.com.money.Main.Mine.Indent.Mai_Jd_Fragment;
import efan.com.money.Main.Mine.Other.Setting;
import efan.com.money.R;
import efan.com.money.UIView.RoundImageView;
import efan.com.money.Util.callback.CallbackManager;
import efan.com.money.Util.callback.CallbackType;
import efan.com.money.Util.callback.IGlobalCallback;
import efan.com.money.Util.net.rx.BaseSubscriber;
import efan.com.money.Util.net.rx.RxRestClient;
import efan.com.money.staticfunction.StaticUrl;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/9/10.
 */
public class MeFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout main_mj1_rl;
    private RelativeLayout main_mj2_rl;

    private TextView main_mj1_tv;
    private TextView main_mj2_tv;

    private Fragment Mai_1_Fragment;
    private Fragment Mai_2_Fragment;

    private ImageView main_mj1_iv;
    private ImageView main_mj2_iv;

    private View view;
    private RoundImageView main_head;
    private ImageView main_setting;

    private JSONObject object = new JSONObject();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.mefragment, container, false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);// 先移除
        }
        InitView();
        InitEvent();
        setSelect(0);
        return view;
    }

    private void InitEvent() {
        main_mj1_rl.setOnClickListener(this);
        main_mj2_rl.setOnClickListener(this);
        main_head.setOnClickListener(this);
        main_setting.setOnClickListener(this);
    }

    private void InitView() {
        main_head = (RoundImageView) view.findViewById(R.id.main_head);
        main_mj1_rl = (RelativeLayout) view.findViewById(R.id.main_mj1_rl);
        main_mj2_rl = (RelativeLayout) view.findViewById(R.id.main_mj2_rl);
        main_mj1_tv = (TextView) view.findViewById(R.id.main_mj1_tv);
        main_mj2_tv = (TextView) view.findViewById(R.id.main_mj2_tv);
        main_mj1_iv = (ImageView) view.findViewById(R.id.main_mj1_iv);
        main_mj2_iv = (ImageView) view.findViewById(R.id.main_mj2_iv);
        main_setting = (ImageView) view.findViewById(R.id.main_setting);
        main_head.setBorderRadius(90);
    }

    @Override
    public void onClick(View v) {
        resetImgs();
        switch (v.getId()) {
            case R.id.main_mj1_rl:
                setSelect(0);
                break;
            case R.id.main_mj2_rl:
                setSelect(1);
                break;
            case R.id.main_head:
                SelectMainHead();
                break;
            case R.id.main_setting:
                Intent intent = new Intent(getActivity(), Setting.class);
                startActivity(intent);
                break;
        }
    }


    private void SelectMainHead() {
        CallbackManager.getInstence()
                .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {

                    @Override
                    public void executeCallback(@Nullable Uri args) {
                        Picasso.with(getActivity()).load(args).into(main_head);
                        upHeadUrlData(args.getPath());
                        Toast.makeText(getActivity(), args + "", Toast.LENGTH_LONG).show();
                    }
                });
        if (getActivity() != null) {
            ((MainActivity) getActivity()).startCameraWithCheck();
        }
    }

    private void upHeadUrlData(String path) {
        Log.i("图片位置", path);
        RxRestClient.builder()
                .url(StaticUrl.POST_FILE)
                .load(getActivity())
                .file(path)
                .build()
                .upload()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>(getActivity()) {
                    @Override
                    public void onNext(String s) {
                        if (object.parseObject(s).getString("success").equals("true")) {
                            Toast.makeText(getActivity(), "上传成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void setSelect(int i) {
        // 获取Fragement管理者
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        // 隐藏所有fragment
        hideFragment(transaction);
        // 把图片变亮
        // 设置内容区域
        switch (i) {
            case 0:
                if (Mai_1_Fragment == null) {
                    Mai_1_Fragment = new Mai_Jd_Fragment();
                    transaction.add(R.id.main_frame, Mai_1_Fragment);
                } else {
                    transaction.show(Mai_1_Fragment);
                }
                main_mj1_tv.setTextColor(0xff6699ff);
                main_mj1_iv.setVisibility(View.VISIBLE);
                break;
            case 1:
                if (Mai_2_Fragment == null) {
                    Mai_2_Fragment = new Mai_Fd_Fragment();
                    transaction.add(R.id.main_frame, Mai_2_Fragment);
                } else {
                    transaction.show(Mai_2_Fragment);
                }
                main_mj2_tv.setTextColor(0xff6699ff);
                main_mj2_iv.setVisibility(View.VISIBLE);
                break;
        }
        transaction.commit();

    }

    private void hideFragment(FragmentTransaction transaction) {

        if (Mai_1_Fragment != null) {
            transaction.hide(Mai_1_Fragment);
        }
        if (Mai_2_Fragment != null) {
            transaction.hide(Mai_2_Fragment);
        }

    }

    private void resetImgs() {
        main_mj1_tv.setTextColor(0xffffffff);
        main_mj2_tv.setTextColor(0xffffffff);
        main_mj1_iv.setVisibility(View.GONE);
        main_mj2_iv.setVisibility(View.GONE);
    }
}
