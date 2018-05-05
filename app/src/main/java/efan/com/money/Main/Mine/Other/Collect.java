package efan.com.money.Main.Mine.Other;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import efan.com.money.Adapter.MineOtherCollectAdapter;
import efan.com.money.Bean.NetShouCang;
import efan.com.money.Main.BaseActivity;
import efan.com.money.R;
import efan.com.money.Util.net.rx.BaseSubscriber;
import efan.com.money.Util.net.rx.RxRestClient;
import efan.com.money.Util.storage.MainPreference;
import efan.com.money.staticfunction.StaticUrl;
import efan.com.money.staticfunction.StaticValue;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/27.
 */

public class Collect extends BaseActivity {
    @BindView(R.id.other_collect_back)
    RelativeLayout other_collect_back;
    @BindView(R.id.other_collect_lv)
    ListView other_collect_lv;
    @BindView(R.id.other_collect_rl)
    RelativeLayout other_collect_rl;
    private MineOtherCollectAdapter adapter;
    private JSONObject object = new JSONObject();

    @OnClick(R.id.other_collect_back)
    public void onClick(View view) {
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_collect);
        ButterKnife.bind(this);
        ListViewData();
    }

    private void ListViewData() {
        RxRestClient.builder()
                .load(Collect.this)
                .url(StaticUrl.GET_SHOU_CANG)
                .params("uid", MainPreference.getCustomAppProfile(StaticValue.USER_ID))
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>(Collect.this) {
                    @Override
                    public void onNext(String s) {
                        if (object.parseObject(s).getBoolean("success")) {
                            ArrayList<NetShouCang> mlist = object.parseObject(object.parseObject(s).getString("data"),
                                    new TypeReference<ArrayList<NetShouCang>>() {
                                    });
                            if (mlist.size() != 0) {
                                adapter = new MineOtherCollectAdapter(Collect.this);
                                adapter.init(mlist);
                                other_collect_lv.setAdapter(adapter);
                                other_collect_lv.setVisibility(View.VISIBLE);
                                other_collect_rl.setVisibility(View.GONE);
                            } else {
                                other_collect_lv.setVisibility(View.GONE);
                                other_collect_rl.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(Collect.this, "数据请求错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
