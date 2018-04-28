package efan.com.money.Main.Mine.Other;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Adapter.OtherDealAdapter;
import efan.com.money.Bean.NetDingDanBean;
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
 * 时间：2018/4/28.
 */

public class Deal extends BaseActivity {
    private ImageView other_deal_fanhui;
    private RecyclerView other_deal_recycle;
    private RelativeLayout other_deal_rl;
    JSONObject object = new JSONObject();
    private OtherDealAdapter adapter;
    private List<NetDingDanBean> mList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_deal);
        initView();
        GetListData();
    }

    private void initView() {
        other_deal_fanhui = findViewById(R.id.other_deal_fanhui);
        other_deal_rl = findViewById(R.id.other_deal_rl_rl);
        other_deal_recycle = (RecyclerView) findViewById(R.id.other_deal_recycle);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        other_deal_recycle.setLayoutManager(manager);
        other_deal_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void GetListData() {
        adapter = new OtherDealAdapter(this);
        RxRestClient.builder()
                .load(this)
                .url(StaticUrl.GET_DING_DAN)
                .params("fd_id", "")
                .params("jd_id", MainPreference.getCustomAppProfile(StaticValue.USER_ID))
                .params("page", 0)
                .params("zhuangtai", StaticValue.INDENT_SUCCESS)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>(this) {
                    @Override
                    public void onNext(String s) {
                        if (object.parseObject(s).getString("success").equals("true")) {
                            mList = object.parseObject(object.parseObject(s).getString("data"),
                                    new TypeReference<ArrayList<NetDingDanBean>>() {
                                    });
                            if (mList.size() != 0) {
                                adapter.initData(mList);
                                other_deal_recycle.setAdapter(adapter);
                                other_deal_rl.setVisibility(View.GONE);
                            } else {
                                other_deal_rl.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(Deal.this, "获取数据失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
