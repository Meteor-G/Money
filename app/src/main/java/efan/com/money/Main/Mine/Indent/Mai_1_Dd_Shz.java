package efan.com.money.Main.Mine.Indent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Adapter.Mai_1_Dd_Shz_Adapter;
import efan.com.money.Bean.Mai_1_Dd_Shz_Bean;
import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/14.
 */

public class Mai_1_Dd_Shz extends Fragment {
    private View view;
    private ListView lv;
    private Mai_1_Dd_Shz_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.mai_1_dd_shz, container, false);
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
        adapter = new Mai_1_Dd_Shz_Adapter(getActivity());
        List<Mai_1_Dd_Shz_Bean> mlist = new ArrayList<>();

        Mai_1_Dd_Shz_Bean bean = new Mai_1_Dd_Shz_Bean();
        bean.setMai_1_dd_shz_item_lx("[微信]");
        bean.setMai_1_dd_shz_item_rwm("商品推销，朋友圈保留一天。");
        bean.setMai_1_dd_shz_item_time("2017-09-12");
        bean.setMai_1_dd_shz_item_yhm("遗忘");
        bean.setMai_1_dd_shz_item_zt("未上传截图");
        bean.setMai_1_dd_shz_jiage("￥1");
        bean.setMai_1_dd_shz_iv_1(R.mipmap.mai_1_dd_shz_tv);
        bean.setMai_1_dd_shz_iv_2(R.mipmap.mai_1_dd_shz_tv);
        mlist.add(bean);

        Mai_1_Dd_Shz_Bean bean1 = new Mai_1_Dd_Shz_Bean();
        bean1.setMai_1_dd_shz_item_lx("[贴吧]");
        bean1.setMai_1_dd_shz_item_rwm("贴吧推销");
        bean1.setMai_1_dd_shz_item_time("2017-09-12");
        bean1.setMai_1_dd_shz_item_yhm("遗忘");
        bean1.setMai_1_dd_shz_item_zt("未上传截图");
        bean1.setMai_1_dd_shz_jiage("￥3");
        bean1.setMai_1_dd_shz_iv_1(R.mipmap.mai_1_dd_shz_tv);
        bean1.setMai_1_dd_shz_iv_2(R.mipmap.mai_1_dd_shz_tv);
        mlist.add(bean1);


        Mai_1_Dd_Shz_Bean bean2 = new Mai_1_Dd_Shz_Bean();
        bean2.setMai_1_dd_shz_item_lx("[陌陌]");
        bean2.setMai_1_dd_shz_item_rwm("你是我找的人吗");
        bean2.setMai_1_dd_shz_item_time("2017-09-12");
        bean2.setMai_1_dd_shz_item_yhm("遗忘");
        bean2.setMai_1_dd_shz_item_zt("未上传截图");
        bean2.setMai_1_dd_shz_jiage("￥2");
        bean2.setMai_1_dd_shz_iv_1(R.mipmap.mai_1_dd_shz_tv);
        bean2.setMai_1_dd_shz_iv_2(R.mipmap.mai_1_dd_shz_tv);
        mlist.add(bean2);

        Mai_1_Dd_Shz_Bean bean3 = new Mai_1_Dd_Shz_Bean();
        bean3.setMai_1_dd_shz_item_lx("[探探]");
        bean3.setMai_1_dd_shz_item_rwm("寻找另一半");
        bean3.setMai_1_dd_shz_item_time("2017-09-12");
        bean3.setMai_1_dd_shz_item_yhm("遗忘");
        bean3.setMai_1_dd_shz_item_zt("未上传截图");
        bean3.setMai_1_dd_shz_jiage("￥1");
        bean3.setMai_1_dd_shz_iv_1(R.mipmap.mai_1_dd_shz_tv);
        bean3.setMai_1_dd_shz_iv_2(R.mipmap.mai_1_dd_shz_tv);
        mlist.add(bean3);
        adapter.init(mlist);
        lv.setAdapter(adapter);
    }

    private void InitView() {
        lv = (ListView) view.findViewById(R.id.mai_1_dd_shz_lv);
    }

    private void InitEvent() {
    }

}

