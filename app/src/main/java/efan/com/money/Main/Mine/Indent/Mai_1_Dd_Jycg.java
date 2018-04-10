package efan.com.money.Main.Mine.Indent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Adapter.Mai_1_Dd_Jycg_Adapter;
import efan.com.money.Bean.Mai_1_Dd_Jycg_Bean;
import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/14.
 */

public class Mai_1_Dd_Jycg extends Fragment {
    private View view;
    private ListView lv;
    private Mai_1_Dd_Jycg_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.mai_1_dd_jycg, container, false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        InitView();
        InitEvent();
        lv();
        return view;
    }

    private void lv() {
        adapter = new Mai_1_Dd_Jycg_Adapter(getActivity());
        List<Mai_1_Dd_Jycg_Bean> list = new ArrayList<Mai_1_Dd_Jycg_Bean>();

        Mai_1_Dd_Jycg_Bean bean = new Mai_1_Dd_Jycg_Bean();
        bean.setMai_1_dd_jycg_item_lx("[微信]");
        bean.setMai_1_dd_jycg_item_rwm("商品推销，朋友圈保留一天。");
        bean.setMai_1_dd_jycg_item_time("2017-09-12");
        bean.setMai_1_dd_jycg_item_yhm("遗忘");
        bean.setMai_1_dd_jycg_item_jg("￥1");
        bean.setMai_1_dd_jycg_item_tupian(R.mipmap.touxiang_1);
        list.add(bean);

        Mai_1_Dd_Jycg_Bean bean1 = new Mai_1_Dd_Jycg_Bean();
        bean1.setMai_1_dd_jycg_item_lx("[QQ]");
        bean1.setMai_1_dd_jycg_item_rwm("投票，分分钟");
        bean1.setMai_1_dd_jycg_item_time("2017-09-11");
        bean1.setMai_1_dd_jycg_item_yhm("edge");
        bean1.setMai_1_dd_jycg_item_jg("￥3");
        bean1.setMai_1_dd_jycg_item_tupian(R.mipmap.touxiang_2);
        list.add(bean1);

        Mai_1_Dd_Jycg_Bean bean2 = new Mai_1_Dd_Jycg_Bean();
        bean2.setMai_1_dd_jycg_item_lx("[微博]");
        bean2.setMai_1_dd_jycg_item_rwm("点赞点赞");
        bean2.setMai_1_dd_jycg_item_time("2017-09-10");
        bean2.setMai_1_dd_jycg_item_yhm("黑夜");
        bean2.setMai_1_dd_jycg_item_jg("￥1");
        bean2.setMai_1_dd_jycg_item_tupian(R.mipmap.touxiang_3);
        list.add(bean2);

        Mai_1_Dd_Jycg_Bean bean3 = new Mai_1_Dd_Jycg_Bean();
        bean3.setMai_1_dd_jycg_item_lx("[朋友圈]");
        bean3.setMai_1_dd_jycg_item_rwm("朋友圈点赞");
        bean3.setMai_1_dd_jycg_item_time("2017-09-19");
        bean3.setMai_1_dd_jycg_item_yhm("那个姑娘");
        bean3.setMai_1_dd_jycg_item_jg("￥1");
        bean3.setMai_1_dd_jycg_item_tupian(R.mipmap.touxiang_4);
        list.add(bean3);
        adapter.init(list);
        lv.setAdapter(adapter);
    }

    private void InitView() {
        lv = (ListView) view.findViewById(R.id.mai_1_dd_jycg_lv);
    }

    private void InitEvent() {
    }

}

