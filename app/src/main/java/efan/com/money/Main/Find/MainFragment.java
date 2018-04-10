package efan.com.money.Main.Find;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Adapter.MainGridViewAdapter;
import efan.com.money.Adapter.MainListViewAdapter;
import efan.com.money.Bean.MainGridViewBean;
import efan.com.money.Bean.MainListViewBean;
import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/10.
 */

public class MainFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private View view;
    private ListView main_lv;
    private MainListViewAdapter adapter;
    //    private RelativeLayout main_frame_rl;
    private GridView main_gridview;
    private MainGridViewAdapter gv_adapter;

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
        Lv();
        gv();
        return view;
    }

    private void gv() {
        gv_adapter = new MainGridViewAdapter(getActivity());
        List<MainGridViewBean> list = new ArrayList<>();
        MainGridViewBean bean = new MainGridViewBean();
        bean.setMain_grid_item_iv(R.mipmap.main_tuiguang);
        bean.setMain_grid_item_tv("推广");
        list.add(bean);

        MainGridViewBean bean1 = new MainGridViewBean();
        bean1.setMain_grid_item_iv(R.mipmap.main_tuiguang);
        bean1.setMain_grid_item_tv("推广");
        list.add(bean1);

        MainGridViewBean bean2 = new MainGridViewBean();
        bean2.setMain_grid_item_iv(R.mipmap.main_tuiguang);
        bean2.setMain_grid_item_tv("推广");
        list.add(bean2);

        MainGridViewBean bean3 = new MainGridViewBean();
        bean3.setMain_grid_item_iv(R.mipmap.main_tuiguang);
        bean3.setMain_grid_item_tv("推广");
        list.add(bean3);

        MainGridViewBean bean4 = new MainGridViewBean();
        bean4.setMain_grid_item_iv(R.mipmap.main_tuiguang);
        bean4.setMain_grid_item_tv("推广");
        list.add(bean4);


        MainGridViewBean bean5 = new MainGridViewBean();
        bean5.setMain_grid_item_iv(R.mipmap.main_tuiguang);
        bean5.setMain_grid_item_tv("推广");
        list.add(bean5);


        MainGridViewBean bean6 = new MainGridViewBean();
        bean6.setMain_grid_item_iv(R.mipmap.main_tuiguang);
        bean6.setMain_grid_item_tv("推广");
        list.add(bean3);


        MainGridViewBean bean7 = new MainGridViewBean();
        bean7.setMain_grid_item_iv(R.mipmap.main_tuiguang);
        bean7.setMain_grid_item_tv("推广");
        list.add(bean3);
        gv_adapter.init(list);
        main_gridview.setAdapter(gv_adapter);
    }

    private void Lv() {
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
        main_gridview = (GridView) view.findViewById(R.id.main_gridview);
    }

    private void InitEvent() {
        main_gridview.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), JD_Tuiguang.class);
        startActivity(intent);

    }
}

