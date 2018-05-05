package efan.com.money.Main.Mine.Indent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Adapter.Mai_1_Zhbd_Adapter;
import efan.com.money.Bean.Mai_1_Zhbd_Item_Bean;
import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/19.
 */

public class Mai_Jd_Zhbd extends AppCompatActivity implements View.OnClickListener {
    private ImageView jmai_1_zhbd_iv;
    private ListView lv;
    private Mai_1_Zhbd_Adapter adapter;
    private ImageView mai_1_zhbd_tj_iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mai_1_zhbd);
        InitView();
        InitEvent();
        ListViewData();
    }

    private void ListViewData() {
        adapter = new Mai_1_Zhbd_Adapter(this);
        List<Mai_1_Zhbd_Item_Bean> mlist = new ArrayList<>();

        Mai_1_Zhbd_Item_Bean bean = new Mai_1_Zhbd_Item_Bean();
        bean.setMai_1_zhbd_itme_lx("[微信]");
        bean.setMai_1_zhbd_itme_zh("Dreamer");
        mlist.add(bean);

        Mai_1_Zhbd_Item_Bean bean1 = new Mai_1_Zhbd_Item_Bean();
        bean1.setMai_1_zhbd_itme_lx("[ Q Q ]");
        bean1.setMai_1_zhbd_itme_zh("68001667");
        mlist.add(bean1);

        Mai_1_Zhbd_Item_Bean bean2 = new Mai_1_Zhbd_Item_Bean();
        bean2.setMai_1_zhbd_itme_lx("[微博]");
        bean2.setMai_1_zhbd_itme_zh("15641665575");
        mlist.add(bean2);

        Mai_1_Zhbd_Item_Bean bean3 = new Mai_1_Zhbd_Item_Bean();
        bean3.setMai_1_zhbd_itme_lx("[朋友圈]");
        bean3.setMai_1_zhbd_itme_zh("Dreamer");
        mlist.add(bean3);

        adapter.init(mlist);
        lv.setAdapter(adapter);
    }

    private void InitView() {
        jmai_1_zhbd_iv = (ImageView) findViewById(R.id.jmai_1_zhbd_iv);
        lv = (ListView) findViewById(R.id.mai_1_zhbd_lv);
        mai_1_zhbd_tj_iv = (ImageView) findViewById(R.id.mai_1_zhbd_tj_iv);
    }

    private void InitEvent() {
        jmai_1_zhbd_iv.setOnClickListener(this);
        mai_1_zhbd_tj_iv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jmai_1_zhbd_iv:
                finish();
                break;
            case R.id.mai_1_zhbd_tj_iv:
                Intent intent = new Intent(this, Mai_Jd_Zhbd_Tj.class);
                startActivity(intent);
                break;
        }
    }
}
