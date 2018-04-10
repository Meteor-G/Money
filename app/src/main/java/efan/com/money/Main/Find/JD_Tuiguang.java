package efan.com.money.Main.Find;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Adapter.JD_TuiguangAdapter;
import efan.com.money.Bean.JD_TuiguangBean;
import efan.com.money.R;
import efan.com.money.staticfunction.ListViewSize;

/**
 * Created by Administrator on 2017/9/11.
 */

public class JD_Tuiguang extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView listview;
    private JD_TuiguangAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jd_tuiguang);
        InitView();
        InitEvent();
        lv();
    }

    private void lv() {
        adapter=new JD_TuiguangAdapter(this);
        List<JD_TuiguangBean> list=new ArrayList<JD_TuiguangBean>();

        JD_TuiguangBean bean=new JD_TuiguangBean();
        bean.setPub_tg_item_iv(R.mipmap.main_wx);
        bean.setPub_tg_item_tv("微信");
        list.add(bean);

        JD_TuiguangBean bean1=new JD_TuiguangBean();
        bean1.setPub_tg_item_iv(R.mipmap.main_qq);
        bean1.setPub_tg_item_tv("QQ群");
        list.add(bean1);

        JD_TuiguangBean bean2=new JD_TuiguangBean();
        bean2.setPub_tg_item_iv(R.mipmap.main_pyq);
        bean2.setPub_tg_item_tv("朋友圈");
        list.add(bean2);

        JD_TuiguangBean bean3=new JD_TuiguangBean();
        bean3.setPub_tg_item_iv(R.mipmap.main_kj);
        bean3.setPub_tg_item_tv("QQ空间");
        list.add(bean3);

        JD_TuiguangBean bean4=new JD_TuiguangBean();
        bean4.setPub_tg_item_iv(R.mipmap.main_wb);
        bean4.setPub_tg_item_tv("微博");
        list.add(bean4);

        JD_TuiguangBean bean5=new JD_TuiguangBean();
        bean5.setPub_tg_item_iv(R.mipmap.main_tb);
        bean5.setPub_tg_item_tv("贴吧");
        list.add(bean5);

        JD_TuiguangBean bean6=new JD_TuiguangBean();
        bean6.setPub_tg_item_iv(R.mipmap.main_tantan);
        bean6.setPub_tg_item_tv("探探");
        list.add(bean6);

        JD_TuiguangBean bean7=new JD_TuiguangBean();
        bean7.setPub_tg_item_iv(R.mipmap.main_momo);
        bean7.setPub_tg_item_tv("陌陌");
        list.add(bean7);


        JD_TuiguangBean bean8=new JD_TuiguangBean();
        bean8.setPub_tg_item_iv(R.mipmap.main_paipai);
        bean8.setPub_tg_item_tv("派派");
        list.add(bean8);

        JD_TuiguangBean bean9=new JD_TuiguangBean();
        bean9.setPub_tg_item_iv(R.mipmap.main_qinxin);
        bean9.setPub_tg_item_tv("亲信");
        list.add(bean9);

        adapter.init(list);
        listview.setAdapter(adapter);
        ListViewSize.SetListViewSize(listview);

    }

    private void InitEvent() {
        listview.setOnItemClickListener(this);
    }

    private void InitView() {
        listview = (ListView) findViewById(R.id.pub_tg_lv);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(this,JD_Main.class);
        startActivity(intent);
    }
}
