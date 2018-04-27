package efan.com.money.Main.Mine.Other;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import efan.com.money.Adapter.Mai_1_Zhbd_Adapter;
import efan.com.money.Bean.Mai_1_Zhbd_Item_Bean;
import efan.com.money.Main.BaseActivity;
import efan.com.money.R;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/27.
 */

public class Collect extends BaseActivity {
    @BindView(R.id.other_collect_back)
    RelativeLayout other_collect_back;
    @BindView(R.id.other_collect_lv)
    ListView other_collect_lv;
    private Mai_1_Zhbd_Adapter adapter;

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
        other_collect_lv.setAdapter(adapter);
    }
}
