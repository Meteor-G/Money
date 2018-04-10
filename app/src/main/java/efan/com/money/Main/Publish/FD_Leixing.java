package efan.com.money.Main.Publish;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Adapter.FD_LeixingAdapter;
import efan.com.money.Bean.FD_LeixingBean;
import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/11.
 */

public class FD_Leixing extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView lv;
    private FD_LeixingAdapter adapter;
    private ImageView fb_lx_fanhui;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fd_leixing);
        InitView();
        InitEvent();
        lv();
    }

    private void lv() {
        adapter = new FD_LeixingAdapter(this);
        List<FD_LeixingBean> list = new ArrayList<FD_LeixingBean>();

        FD_LeixingBean bean = new FD_LeixingBean();
        bean.setLeixing("推广");
        list.add(bean);
        FD_LeixingBean bean1 = new FD_LeixingBean();
        bean1.setLeixing("集赞");
        list.add(bean1);
        FD_LeixingBean bean2 = new FD_LeixingBean();
        bean2.setLeixing("点击量");
        list.add(bean2);
        FD_LeixingBean bean3 = new FD_LeixingBean();
        bean3.setLeixing("投票");
        list.add(bean3);
        FD_LeixingBean bean4 = new FD_LeixingBean();
        bean4.setLeixing("营销");
        list.add(bean4);
        FD_LeixingBean bean5 = new FD_LeixingBean();
        bean5.setLeixing("广告");
        list.add(bean5);
        FD_LeixingBean bean6 = new FD_LeixingBean();
        bean6.setLeixing("兴趣");
        list.add(bean6);
        FD_LeixingBean bean7 = new FD_LeixingBean();
        bean7.setLeixing("其他");
        list.add(bean7);

        adapter.init(list);
        lv.setAdapter(adapter);

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
        startActivity(intent);
    }
}
