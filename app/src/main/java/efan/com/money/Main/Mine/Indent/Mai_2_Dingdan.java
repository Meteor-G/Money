package efan.com.money.Main.Mine.Indent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Adapter.ViewPagerAdapter;
import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/14.
 */

public class Mai_2_Dingdan extends FragmentActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private List<Fragment> mDatas;
    private ViewPagerAdapter adapter;

    private RelativeLayout mai_2_dd_rwyl;
    private RelativeLayout mai_2_dd_jxs;
    private RelativeLayout mai_2_dd_dsh;
    private RelativeLayout mai_2_dd_jycg;
    private RelativeLayout mai_2_dd_qbrw;

    private TextView mai_2_dd_rwyl_tv;
    private TextView mai_2_dd_jxs_tv;
    private TextView mai_2_dd_dsh_tv;
    private TextView mai_2_dd_jycg_tv;
    private TextView mai_2_dd_qbrw_tv;

    private ImageView mai_2_dd_fanhui;
    private String bundledata;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mai_2_dingdan);
        InitView();
        InitEvent();
        GetBundleData();
        setSelect(Integer.valueOf(bundledata));
    }

    private void GetBundleData() {
        Bundle bundle = this.getIntent().getExtras();
        bundledata = bundle.getString("mai2");
    }

    private void InitEvent() {
        mai_2_dd_fanhui.setOnClickListener(this);
        mai_2_dd_rwyl.setOnClickListener(this);
        mai_2_dd_jxs.setOnClickListener(this);
        mai_2_dd_dsh.setOnClickListener(this);
        mai_2_dd_jycg.setOnClickListener(this);
        mai_2_dd_qbrw.setOnClickListener(this);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentItem = mViewPager.getCurrentItem();
                setTab(currentItem);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void InitView() {
        mai_2_dd_rwyl = (RelativeLayout) findViewById(R.id.mai_2_dd_rwyl);
        mai_2_dd_jxs = (RelativeLayout) findViewById(R.id.mai_2_dd_jxs);
        mai_2_dd_dsh = (RelativeLayout) findViewById(R.id.mai_2_dd_dsh);
        mai_2_dd_jycg = (RelativeLayout) findViewById(R.id.mai_2_dd_jycg);
        mai_2_dd_qbrw = (RelativeLayout) findViewById(R.id.mai_2_dd_qbrw);
        mai_2_dd_rwyl_tv = (TextView) findViewById(R.id.mai_2_dd_rwyl_tv);
        mai_2_dd_jxs_tv = (TextView) findViewById(R.id.mai_2_dd_jxs_tv);
        mai_2_dd_dsh_tv = (TextView) findViewById(R.id.mai_2_dd_dsh_tv);
        mai_2_dd_jycg_tv = (TextView) findViewById(R.id.mai_2_dd_jycg_tv);
        mai_2_dd_qbrw_tv = (TextView) findViewById(R.id.mai_2_dd_qbrw_tv);
        mai_2_dd_fanhui = (ImageView) findViewById(R.id.mai_2_dd_fanhui);
        mViewPager = (ViewPager) findViewById(R.id.mai_2_dd_viewpager);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mDatas = new ArrayList<Fragment>();
        Fragment tab0 = new Mai_2_Dd_Rwyl();
        Fragment tab1 = new Mai_2_Dd_Jxs();
        Fragment tab2 = new Mai_2_Dd_Dsh();
        Fragment tab3 = new Mai_2_Dd_Jycg();
        Fragment tab4 = new Mai_2_Dd_Qbrw();

        mDatas.add(tab0);
        mDatas.add(tab1);
        mDatas.add(tab2);
        mDatas.add(tab3);
        mDatas.add(tab4);

        adapter.ViewPagerAdapter(mDatas);
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mai_2_dd_fanhui:
                finish();
                break;
            case R.id.mai_2_dd_rwyl:
                setSelect(0);
                break;
            case R.id.mai_2_dd_jxs_tv:
                setSelect(1);
                break;
            case R.id.mai_2_dd_dsh_tv:
                setSelect(2);
                break;
            case R.id.mai_2_dd_jycg_tv:
                setSelect(3);
                break;
            case R.id.mai_2_dd_qbrw_tv:
                setSelect(4);
                break;
        }
    }

    private void setSelect(int i) {
        setTab(i);
        mViewPager.setCurrentItem(i);
    }

    private void setTab(int i) {
        resetTextView();
        switch (i) {
            case 0:
                mai_2_dd_rwyl_tv.setTextColor(0xffFFA500);
                break;
            case 1:
                mai_2_dd_jxs_tv.setTextColor(0xffFFA500);
                break;
            case 2:
                mai_2_dd_dsh_tv.setTextColor(0xffFFA500);
                break;
            case 3:
                mai_2_dd_jycg_tv.setTextColor(0xffFFA500);
                break;
            case 4:
                mai_2_dd_qbrw_tv.setTextColor(0xffFFA500);
                break;

        }
    }

    private void resetTextView() {
        mai_2_dd_rwyl_tv.setTextColor(0xffcccccc);
        mai_2_dd_jxs_tv.setTextColor(0xffcccccc);
        mai_2_dd_dsh_tv.setTextColor(0xffcccccc);
        mai_2_dd_jycg_tv.setTextColor(0xffcccccc);
        mai_2_dd_qbrw_tv.setTextColor(0xffcccccc);
    }
}
