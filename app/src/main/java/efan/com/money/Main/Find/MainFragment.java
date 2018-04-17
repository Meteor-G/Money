package efan.com.money.Main.Find;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Adapter.MainGridViewRecycleAdapter;
import efan.com.money.Adapter.MainListViewAdapter;
import efan.com.money.Adapter.MainOptionalAdapter;
import efan.com.money.Adapter.OnItemClickListener;
import efan.com.money.Bean.MainListViewBean;
import efan.com.money.Bean.NetFaDanBean;
import efan.com.money.Bean.NetTuiGuangBean;
import efan.com.money.R;
import efan.com.money.Util.net.rx.BaseSubscriber;
import efan.com.money.Util.net.rx.RxRestClient;
import efan.com.money.staticfunction.StaticUrl;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/9/10.
 */

public class MainFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, OnItemClickListener {
    private View view;
    private ListView main_lv;
    private MainListViewAdapter adapter;

    private MainGridViewRecycleAdapter gv_adapter;
    private RecyclerView main_optional_recycle;
    private RecyclerView main_gridview;
    private MainOptionalAdapter recycleAdapter;
    private List<NetFaDanBean> FaDanList;
    private List<NetTuiGuangBean> TuiGuangList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.mainfragment, container, false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);// 先移除
        }
        InitView();
        InitEvent();
        getListViewData();
        getGridViewData();
        RecycleData();
//        onCallRxRestClient();
        return view;
    }

    private void onCallRxRestClient() {
        final String url = "";
        RxRestClient.builder()
                .url(url)
                .load(getActivity())
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>(getActivity()) {
                    @Override
                    public void onNext(String s) {

                    }
                });
    }


    private void RecycleData() {
        recycleAdapter = new MainOptionalAdapter(getActivity());
        recycleAdapter.setOnItemClickListener(this);
        RxRestClient.builder()
                .url(StaticUrl.GET_FA_DAN)
                .params("fd_id", "0")
                .params("page", "0")
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>(getActivity()) {
                    @Override
                    public void onNext(String s) {
                        JSONObject object = new JSONObject();
                        if (object.parseObject(s).getString("success").equals("true")) {
                            FaDanList = object.parseObject(object.parseObject(s).getString("data"),
                                    new TypeReference<ArrayList<NetFaDanBean>>() {
                                    });
                            recycleAdapter.initData(FaDanList);
                            main_optional_recycle.setAdapter(recycleAdapter);
                        } else {
                            Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void getGridViewData() {
        gv_adapter = new MainGridViewRecycleAdapter(getActivity());
        gv_adapter.setOnItemClickListener(this);
        RxRestClient.builder()
                .url(StaticUrl.GET_TUI_GUANG)
                .load(getContext())
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>(getActivity()) {
                    @Override
                    public void onNext(String s) {
                        JSONObject object = new JSONObject();
                        String data = object.parseObject(s).getString("data");
                        TuiGuangList = object.parseObject(data,
                                new TypeReference<ArrayList<NetTuiGuangBean>>() {
                                });
                        gv_adapter.initData(TuiGuangList);
                        main_gridview.setAdapter(gv_adapter);
                    }
                });
    }

    private void getListViewData() {
        adapter = new MainListViewAdapter(getActivity());
        List<MainListViewBean> list = new ArrayList<MainListViewBean>();
        MainListViewBean bean = new MainListViewBean();
        bean.setMain_item_iv(R.mipmap.main_wx);
        bean.setMain_item_title("微信");
        bean.setMain_item_text1("[热推] 最高佣金达到了25元");
        bean.setMain_item_text2("125件新任务");
        list.add(bean);

        MainListViewBean bean1 = new MainListViewBean();
        bean1.setMain_item_iv(R.mipmap.main_pyq);
        bean1.setMain_item_title("朋友圈");
        bean1.setMain_item_text1("[热推] 最高佣金达到了25元");
        bean1.setMain_item_text2("125件新任务");
        list.add(bean1);

        MainListViewBean bean2 = new MainListViewBean();
        bean2.setMain_item_iv(R.mipmap.main_qq);
        bean2.setMain_item_title("QQ群");
        bean2.setMain_item_text1("[热推] 最高佣金达到了25元");
        bean2.setMain_item_text2("125件新任务");
        list.add(bean2);

        MainListViewBean bean3 = new MainListViewBean();
        bean3.setMain_item_iv(R.mipmap.main_kj);
        bean3.setMain_item_title("QQ空间");
        bean3.setMain_item_text1("[热推] 最高佣金达到了25元");
        bean3.setMain_item_text2("125件新任务");
        list.add(bean3);
        adapter.init(list);
        main_lv.setAdapter(adapter);
    }

    private void InitView() {
        main_lv = (ListView) view.findViewById(R.id.main_lv);
        main_gridview = (RecyclerView) view.findViewById(R.id.main_gridview);
        GridLayoutManager gv_manager = new GridLayoutManager(getActivity(), 4);
        main_gridview.setLayoutManager(gv_manager);
        main_optional_recycle = (RecyclerView) view.findViewById(R.id.main_optional_recycle);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        main_optional_recycle.setLayoutManager(manager);
    }

    private void InitEvent() {
        main_lv.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.main_lv:
                Intent intent1 = new Intent(getActivity(), JD_Main.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void onItemClick(View view, int Position) {
        switch (view.getId()) {
            case R.id.main_frame_rl:
                Intent intent = new Intent(getActivity(), JD_Tuiguang.class);
                startActivity(intent);
                break;
            case R.id.item_main_optional_rl:
                Intent intent1 = new Intent(getActivity(), JD_Xiangxi.class);
                startActivity(intent1);
                break;

        }

    }
}

