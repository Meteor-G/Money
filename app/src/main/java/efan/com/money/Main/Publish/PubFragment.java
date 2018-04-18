package efan.com.money.Main.Publish;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Adapter.OnItemClickListener;
import efan.com.money.Adapter.PubGridViewRecycleAdapter;
import efan.com.money.Adapter.ScrollerViewAdapter;
import efan.com.money.Bean.NetZhangHao;
import efan.com.money.Bean.PubScrollerBean;
import efan.com.money.R;
import efan.com.money.UIView.LimitScrollerView;
import efan.com.money.Util.net.rx.BaseSubscriber;
import efan.com.money.Util.net.rx.RxRestClient;
import efan.com.money.staticfunction.StaticUrl;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Administrator on 2017/9/10.
 */

public class PubFragment extends Fragment implements View.OnClickListener, OnItemClickListener {
    private View view;
    //    private GridView pub_gv;
//    private PubGridViewAdapter gv_adapter;
    private LimitScrollerView ScrollerView;
    private ScrollerViewAdapter adapter;
    private RecyclerView pub_gv;
    private PubGridViewRecycleAdapter gv_adapter;
    private List<NetZhangHao> mList;

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
        GetGridViewData();
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

    private void GetGridViewData() {
        gv_adapter = new PubGridViewRecycleAdapter(getActivity());
        gv_adapter.setOnItemClickListener(this);
        RxRestClient.builder()
                .url(StaticUrl.GET_ZHANG_HAO)
                .load(getActivity())
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>(getActivity()) {
                    @Override
                    public void onNext(String s) {
                        JSONObject object = new JSONObject();
                        if (object.parseObject(s).getString("success").equals("true")) {
                            mList = object.parseObject(object.parseObject(s).getString("data"),
                                    new TypeReference<ArrayList<NetZhangHao>>() {
                                    });
                            gv_adapter.initData(mList);
                            pub_gv.setAdapter(gv_adapter);
                        } else {
                            Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void InitEvent() {

    }

    private void InitView() {
        pub_gv = (RecyclerView) view.findViewById(R.id.pub_gv);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        pub_gv.setLayoutManager(manager);
        ScrollerView = (LimitScrollerView) view.findViewById(R.id.ScrollerView);
    }

    @Override
    public void onItemClick(View view, int Position) {
        Intent intent = new Intent(getActivity(), FD_Leixing.class);
        intent.putExtra("zhanghao", mList.get(Position).getZh_leixing());
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }
}
