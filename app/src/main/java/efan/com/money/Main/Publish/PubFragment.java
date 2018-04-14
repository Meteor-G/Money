package efan.com.money.Main.Publish;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Adapter.PubGridViewAdapter;
import efan.com.money.Adapter.ScrollerViewAdapter;
import efan.com.money.Bean.PubGridViewBean;
import efan.com.money.Bean.PubScrollerBean;
import efan.com.money.R;
import efan.com.money.UIView.LimitScrollerView;


/**
 * Created by Administrator on 2017/9/10.
 */

public class PubFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private View view;
    private GridView pub_gv;
    private PubGridViewAdapter gv_adapter;
    private LimitScrollerView ScrollerView;
    private ScrollerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.pubfragment, container, false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);// 先移除
        }
        InitView();
        InitEvent();
        gv();
        Scroller();
        return view;
    }

    private void Scroller() {
        adapter = new ScrollerViewAdapter(getActivity());
        List<PubScrollerBean> list = new ArrayList<>();
        PubScrollerBean bean = new PubScrollerBean();
        bean.setFub_scroller_iv(R.mipmap.main_wx);
        bean.setFub_scroller_money_tv("￥12");
        bean.setFub_scroller_name_tv("微信转发");
        bean.setFub_scroller_time_tv("3分钟前完成订单");
        bean.setFub_scroller_title_tv("[微信]");
        list.add(bean);

        PubScrollerBean bean1 = new PubScrollerBean();
        bean1.setFub_scroller_iv(R.mipmap.main_wb);
        bean1.setFub_scroller_money_tv("￥1");
        bean1.setFub_scroller_name_tv("微博点赞");
        bean1.setFub_scroller_time_tv("12分钟前完成订单");
        bean1.setFub_scroller_title_tv("[微博]");
        list.add(bean1);

        PubScrollerBean bean2 = new PubScrollerBean();
        bean2.setFub_scroller_iv(R.mipmap.main_qq);
        bean2.setFub_scroller_money_tv("￥3");
        bean2.setFub_scroller_name_tv("群聊发广告");
        bean2.setFub_scroller_time_tv("3分钟前完成订单");
        bean2.setFub_scroller_title_tv("[Q Q]");
        list.add(bean2);

        adapter.setdata(list);
        ScrollerView.setDataAdapter(adapter);
        ScrollerView.startScroll();

    }

    private void gv() {
        gv_adapter = new PubGridViewAdapter(getActivity());
        List<PubGridViewBean> list = new ArrayList<>();
        PubGridViewBean bean = new PubGridViewBean();
        bean.setPub_grid_item_iv(R.mipmap.main_wb);
        bean.setPub_grid_item_tv("微博");
        list.add(bean);

        PubGridViewBean bean1 = new PubGridViewBean();
        bean1.setPub_grid_item_iv(R.mipmap.main_wb);
        bean1.setPub_grid_item_tv("微博");
        list.add(bean1);

        PubGridViewBean bean2 = new PubGridViewBean();
        bean2.setPub_grid_item_iv(R.mipmap.main_wb);
        bean2.setPub_grid_item_tv("微博");
        list.add(bean2);

        PubGridViewBean bean3 = new PubGridViewBean();
        bean3.setPub_grid_item_iv(R.mipmap.main_wb);
        bean3.setPub_grid_item_tv("微博");
        list.add(bean3);

        PubGridViewBean bean4 = new PubGridViewBean();
        bean4.setPub_grid_item_iv(R.mipmap.main_wb);
        bean4.setPub_grid_item_tv("微博");
        list.add(bean4);


        PubGridViewBean bean5 = new PubGridViewBean();
        bean5.setPub_grid_item_iv(R.mipmap.main_wb);
        bean5.setPub_grid_item_tv("微博");
        list.add(bean5);


        PubGridViewBean bean6 = new PubGridViewBean();
        bean6.setPub_grid_item_iv(R.mipmap.main_wb);
        bean6.setPub_grid_item_tv("微博");
        list.add(bean3);


        PubGridViewBean bean7 = new PubGridViewBean();
        bean7.setPub_grid_item_iv(R.mipmap.main_wb);
        bean7.setPub_grid_item_tv("微博");
        list.add(bean3);

        PubGridViewBean bean8 = new PubGridViewBean();
        bean8.setPub_grid_item_iv(R.mipmap.main_wb);
        bean8.setPub_grid_item_tv("微博");
        list.add(bean8);

        PubGridViewBean bean9 = new PubGridViewBean();
        bean9.setPub_grid_item_iv(R.mipmap.main_wb);
        bean9.setPub_grid_item_tv("微博");
        list.add(bean9);


        PubGridViewBean bean10 = new PubGridViewBean();
        bean10.setPub_grid_item_iv(R.mipmap.main_wb);
        bean10.setPub_grid_item_tv("微博");
        list.add(bean10);

        PubGridViewBean bean11 = new PubGridViewBean();
        bean11.setPub_grid_item_iv(R.mipmap.main_wb);
        bean11.setPub_grid_item_tv("微博");
        list.add(bean11);

        gv_adapter.init(list);
        pub_gv.setAdapter(gv_adapter);
    }

    private void InitEvent() {
        pub_gv.setOnItemClickListener(this);
    }

    private void InitView() {
        pub_gv = (GridView) view.findViewById(R.id.pub_gv);
        ScrollerView = (LimitScrollerView) view.findViewById(R.id.ScrollerView);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), FD_Leixing.class);
        startActivity(intent);
    }
}
