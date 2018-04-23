package efan.com.money.Main.Find;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Adapter.JD_TuiGuangRecycleAdapter;
import efan.com.money.Adapter.OnItemClickListener;
import efan.com.money.Bean.NetZhangHao;
import efan.com.money.R;
import efan.com.money.Util.net.rx.BaseSubscriber;
import efan.com.money.Util.net.rx.RxRestClient;
import efan.com.money.staticfunction.StaticUrl;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/9/11.
 */

public class JD_Tuiguang extends AppCompatActivity implements OnItemClickListener {

    private JD_TuiGuangRecycleAdapter adapter;
    private RecyclerView pub_tg_lv;
    private List<NetZhangHao> mList;
    private String tuiguang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jd_tuiguang);
        InitView();
        InitEvent();
        GetIntentData();
        GetListViewData();
    }

    private void GetIntentData() {
        tuiguang = getIntent().getStringExtra("tuiguang");
    }

    private void GetListViewData() {
        adapter = new JD_TuiGuangRecycleAdapter(this);
        adapter.setOnItemClickListener(this);
        RxRestClient.builder()
                .url(StaticUrl.GET_ZHANG_HAO)
                .load(JD_Tuiguang.this)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>(this) {
                    @Override
                    public void onNext(String s) {
                        JSONObject object = new JSONObject();
                        if (object.parseObject(s).getString("success").equals("true")) {
                            mList = object.parseObject(object.parseObject(s).getString("data"),
                                    new TypeReference<ArrayList<NetZhangHao>>() {
                                    });
                            adapter.initData(mList);
                            pub_tg_lv.setAdapter(adapter);
                        } else {
                            Toast.makeText(JD_Tuiguang.this, "获取数据失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void InitEvent() {

    }

    private void InitView() {
        pub_tg_lv = (RecyclerView) findViewById(R.id.pub_tg_lv);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        pub_tg_lv.setLayoutManager(manager);
    }

    @Override
    public void onItemClick(View view, int Position) {
        Intent intent = new Intent(this, JD_Main.class);
        intent.putExtra("tuiguang", tuiguang);
        intent.putExtra("zhanghao", mList.get(Position).getZh_leixing());
        startActivity(intent);
    }
}
