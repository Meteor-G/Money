package efan.com.money.Main.Mine.Indent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Adapter.Mai_1_Dd_Jyz_Adapter;
import efan.com.money.Bean.Mai_1_Dd_Jyz_Bean;
import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/14.
 */

public class Mai_1_Dd_Jyz extends Fragment {
    private View view;
    private ListView mai_1_dd_jyz_lv;
    private Mai_1_Dd_Jyz_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.mai_1_dd_jyz, container, false);
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
        adapter = new Mai_1_Dd_Jyz_Adapter(getActivity());
        List<Mai_1_Dd_Jyz_Bean> list = new ArrayList<Mai_1_Dd_Jyz_Bean>();

        Mai_1_Dd_Jyz_Bean bean = new Mai_1_Dd_Jyz_Bean();
        bean.setMai_1_dd_jyz_item_lx("[微信]");
        bean.setMai_1_dd_jyz_item_rwm("商品推销");
        bean.setMai_1_dd_jyz_item_time("2017-09-12");
        bean.setMai_1_dd_jyz_item_yhm("那天");
        bean.setMai_1_dd_jyz_item_zt("未上传截图");
        bean.setMai_1_dd_jyz_item_tupian(R.mipmap.touxiang_6);
        list.add(bean);

        Mai_1_Dd_Jyz_Bean bean1 = new Mai_1_Dd_Jyz_Bean();
        bean1.setMai_1_dd_jyz_item_lx("[QQ]");
        bean1.setMai_1_dd_jyz_item_rwm("投票选财主了");
        bean1.setMai_1_dd_jyz_item_time("2017-09-11");
        bean1.setMai_1_dd_jyz_item_yhm("遗忘");
        bean1.setMai_1_dd_jyz_item_zt("未上传截图");
        bean1.setMai_1_dd_jyz_item_tupian(R.mipmap.touxiang_5);
        list.add(bean1);

        Mai_1_Dd_Jyz_Bean bean2 = new Mai_1_Dd_Jyz_Bean();
        bean2.setMai_1_dd_jyz_item_lx("[微博]");
        bean2.setMai_1_dd_jyz_item_rwm("就是你了，快来拿钱");
        bean2.setMai_1_dd_jyz_item_time("2017-09-13");
        bean2.setMai_1_dd_jyz_item_yhm("Dreamer");
        bean2.setMai_1_dd_jyz_item_zt("未上传截图");
        bean2.setMai_1_dd_jyz_item_tupian(R.mipmap.touxiang_1);
        list.add(bean2);

        Mai_1_Dd_Jyz_Bean bean3 = new Mai_1_Dd_Jyz_Bean();
        bean3.setMai_1_dd_jyz_item_lx("[朋友圈]");
        bean3.setMai_1_dd_jyz_item_rwm("鞋子降价了");
        bean3.setMai_1_dd_jyz_item_time("2017-09-15");
        bean3.setMai_1_dd_jyz_item_yhm("忘掉");
        bean3.setMai_1_dd_jyz_item_zt("未上传截图");
        bean3.setMai_1_dd_jyz_item_tupian(R.mipmap.touxiang_8);
        list.add(bean3);
        adapter.init(list);
        mai_1_dd_jyz_lv.setAdapter(adapter);
    }

    private void InitView() {
        mai_1_dd_jyz_lv = (ListView) view.findViewById(R.id.mai_1_dd_jyz_lv);
    }

    private void InitEvent() {
    }

}

