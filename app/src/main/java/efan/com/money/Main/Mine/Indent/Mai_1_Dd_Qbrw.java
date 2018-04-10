package efan.com.money.Main.Mine.Indent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Adapter.Mai_1_Dd_Qbrw_Adapter;
import efan.com.money.Bean.Mai_1_Dd_Qbrw_Bean;
import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/14.
 */

public class Mai_1_Dd_Qbrw extends Fragment {
    private View view;
    private ListView lv;
    private Mai_1_Dd_Qbrw_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.mai_1_dd_qbrw, container, false);
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
        adapter = new Mai_1_Dd_Qbrw_Adapter(getActivity());
        List<Mai_1_Dd_Qbrw_Bean> list = new ArrayList<Mai_1_Dd_Qbrw_Bean>();

        Mai_1_Dd_Qbrw_Bean bean = new Mai_1_Dd_Qbrw_Bean();
        bean.setMai_1_dd_qbrw_item_lx("[微信]");
        bean.setMai_1_dd_qbrw_item_rwm("商品推销，朋友圈保留一天。");
        bean.setMai_1_dd_qbrw_item_time("2017-09-12");
        bean.setMai_1_dd_qbrw_item_yhm("那朵云");
        bean.setMai_1_dd_qbrw_item_zt("已完成");
        bean.setMai_1_dd_qbrw_item_tupian(R.mipmap.touxiang_1);
        list.add(bean);

        Mai_1_Dd_Qbrw_Bean bean1 = new Mai_1_Dd_Qbrw_Bean();
        bean1.setMai_1_dd_qbrw_item_lx("[QQ]");
        bean1.setMai_1_dd_qbrw_item_rwm("快点拿钱投票啦！");
        bean1.setMai_1_dd_qbrw_item_time("2017-09-8");
        bean1.setMai_1_dd_qbrw_item_yhm("是你");
        bean1.setMai_1_dd_qbrw_item_zt("已完成");
        bean1.setMai_1_dd_qbrw_item_tupian(R.mipmap.touxiang_8);
        list.add(bean1);
        Mai_1_Dd_Qbrw_Bean bean2 = new Mai_1_Dd_Qbrw_Bean();
        bean2.setMai_1_dd_qbrw_item_lx("[微博]");
        bean2.setMai_1_dd_qbrw_item_rwm("兴趣好友");
        bean2.setMai_1_dd_qbrw_item_time("2017-09-6");
        bean2.setMai_1_dd_qbrw_item_yhm("Edge");
        bean2.setMai_1_dd_qbrw_item_zt("已完成");
        bean2.setMai_1_dd_qbrw_item_tupian(R.mipmap.touxiang_6);
        list.add(bean2);

        Mai_1_Dd_Qbrw_Bean bean3 = new Mai_1_Dd_Qbrw_Bean();
        bean3.setMai_1_dd_qbrw_item_lx("[朋友圈]");
        bean3.setMai_1_dd_qbrw_item_rwm("商品大甩卖啦");
        bean3.setMai_1_dd_qbrw_item_time("2017-09-2");
        bean3.setMai_1_dd_qbrw_item_yhm("遗");
        bean3.setMai_1_dd_qbrw_item_zt("已完成");
        bean3.setMai_1_dd_qbrw_item_tupian(R.mipmap.touxiang_4);
        list.add(bean3);
        adapter.init(list);
        lv.setAdapter(adapter);
    }

    private void InitView() {
        lv = (ListView) view.findViewById(R.id.mai_1_dd_qbrw_lv);
    }

    private void InitEvent() {
    }

}
