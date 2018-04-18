package efan.com.money.Main.Publish;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Adapter.FD_LeixingAdapter;
import efan.com.money.Bean.NetTuiGuangBean;
import efan.com.money.R;
import efan.com.money.Util.net.rx.BaseSubscriber;
import efan.com.money.Util.net.rx.RxRestClient;
import efan.com.money.staticfunction.StaticUrl;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/9/11.
 */

public class FD_Leixing extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView lv;
    private FD_LeixingAdapter adapter;
    private ImageView fb_lx_fanhui;
    private List<NetTuiGuangBean> TuiGuangList;
    private String zhanghao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fd_leixing);
        GetIntentData();
        InitView();
        InitEvent();
        GetListViewData();
    }

    private void GetIntentData() {
        zhanghao = getIntent().getStringExtra("zhanghao");
    }

    private void GetListViewData() {
        adapter = new FD_LeixingAdapter(this);
        RxRestClient.builder()
                .url(StaticUrl.GET_TUI_GUANG)
                .load(FD_Leixing.this)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>(FD_Leixing.this) {
                    @Override
                    public void onNext(String s) {
                        JSONObject object = new JSONObject();
                        String data = object.parseObject(s).getString("data");
                        TuiGuangList = object.parseObject(data,
                                new TypeReference<ArrayList<NetTuiGuangBean>>() {
                                });
                        adapter.init(TuiGuangList);
                        lv.setAdapter(adapter);
                    }
                });
    }

    private void InitEvent() {
        fb_lx_fanhui.setOnClickListener(this);
        lv.setOnItemClickListener(this);
    }

    private void InitView() {
        lv = (ListView) findViewById(R.id.publx_lv);
        fb_lx_fanhui = (ImageView) findViewById(R.id.fb_lx_fanhui);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fb_lx_fanhui:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(FD_Leixing.this, FD_Xiangxi.class);
        intent.putExtra("zhanghao", zhanghao);
        intent.putExtra("tuiguang", TuiGuangList.get(position).getTg_leixing());
        startActivity(intent);
    }
}
