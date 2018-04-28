package efan.com.money.Main.Mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import efan.com.money.Main.Login.Login;
import efan.com.money.Main.MainActivity;
import efan.com.money.Main.Mine.Indent.Mai_Fd_Fragment;
import efan.com.money.Main.Mine.Indent.Mai_Jd_Fragment;
import efan.com.money.Main.Mine.Other.Collect;
import efan.com.money.Main.Mine.Other.InCome;
import efan.com.money.Main.Mine.Other.Setting;
import efan.com.money.R;
import efan.com.money.UIView.RoundImageView;
import efan.com.money.Util.callback.CallbackManager;
import efan.com.money.Util.callback.CallbackType;
import efan.com.money.Util.callback.IGlobalCallback;
import efan.com.money.Util.net.rx.BaseSubscriber;
import efan.com.money.Util.net.rx.RxRestClient;
import efan.com.money.Util.storage.MainPreference;
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

import static com.alibaba.fastjson.JSON.parseObject;

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
    private TextView me_frag_name;

    private RelativeLayout me_frag_collect_rl;
    private RelativeLayout me_frag_income_rl;

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
        GetData();
        return view;
    }

    private void GetData() {
        RxRestClient.builder()
                .load(getActivity())
                .url(StaticUrl.SIGN_IN)
                .params("name", MainPreference.getCustomAppProfile("name"))
                .params("password", MainPreference.getCustomAppProfile("password"))
                .build()
                .post()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>(getActivity()) {
                    @Override
                    public void onNext(String s) {
                        if (parseObject(s).getString("success").equals("true")) {
                            JSONObject object = MeFragment.this.object.parseObject(parseObject(s).getString("data"));
                            String head = object.getString("head");
                            setValue(head);
                        } else {
                            Toast.makeText(getActivity(), "登录失败,请重新登录", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), Login.class);
                            startActivity(intent);
                        }
                    }
                });
    }

    public void setValue(String value) {
        Picasso.with(getActivity())
                .load(StaticUrl.BASE_URL + value)
                .placeholder(R.mipmap.main_touxiang)
                .into(main_head);
        me_frag_name.setText(MainPreference.getCustomAppProfile("name"));
    }

    private void InitEvent() {
        main_mj1_rl.setOnClickListener(this);
        main_mj2_rl.setOnClickListener(this);
        main_head.setOnClickListener(this);
        main_setting.setOnClickListener(this);
        me_frag_collect_rl.setOnClickListener(this);
        me_frag_income_rl.setOnClickListener(this);
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
        me_frag_name = (TextView) view.findViewById(R.id.me_frag_name);
        me_frag_collect_rl = (RelativeLayout) view.findViewById(R.id.me_frag_collect_rl);
        me_frag_income_rl = (RelativeLayout) view.findViewById(R.id.me_frag_income_rl);
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
            case R.id.me_frag_collect_rl:
                Intent intent1 = new Intent(getActivity(), Collect.class);
                startActivity(intent1);
                break;
            case R.id.me_frag_income_rl:
                Intent intent2 = new Intent(getActivity(), InCome.class);
                startActivity(intent2);
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
                    }
                });
        if (getActivity() != null) {
            ((MainActivity) getActivity()).startCameraWithCheck();
        }
    }

    private void upHeadUrlData(String path) {
        OkHttpClient client = new OkHttpClient();
        File file = new File(path);
        if (!file.exists()) {
            Toast.makeText(getActivity(), file.getAbsolutePath() + "图片不存在", Toast.LENGTH_SHORT).show();
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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                if (object.parseObject(str).getString("success").equals("true")) {
                    UpDataHead(object.parseObject(str).getString("massage"));
                } else {
                    Toast.makeText(getActivity(), "上传失败", Toast.LENGTH_SHORT).show();
                }

            }
        });
//        RxRestClient.builder()
//                .url(StaticUrl.POST_FILE)
//                .load(getActivity())
////                .file(path)
//                .raw(path)
//                .build()
//                .post_raw()
////                .upload()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BaseSubscriber<String>(getActivity()) {
//                    @Override
//                    public void onNext(String s) {
//                        if (parseObject(s).getString("success").equals("true")) {
//                            Toast.makeText(getActivity(), "上传成功", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
    }

    private void UpDataHead(String headPath) {
        RxRestClient
                .builder()
                .url(StaticUrl.UP_DATA_HEAD)
                .params("uid", MainPreference.getCustomAppProfile(StaticValue.USER_ID))
                .params("head", headPath)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>(getActivity()) {
                    @Override
                    public void onNext(String s) {
                        if (object.parseObject(s).getString("success").equals("true")) {
                            Toast.makeText(getActivity(), "更新头像成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "更新头像失败！", Toast.LENGTH_SHORT).show();
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
