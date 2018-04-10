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

public class Mai_1_Digdan extends FragmentActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private List<Fragment> mDatas;
    private ViewPagerAdapter adapter;

    private RelativeLayout mai_1_dd_jyz;
    private RelativeLayout mai_1_dd_shz;
    private RelativeLayout mai_1_dd_jycg;
    private RelativeLayout mai_1_dd_qbrw;

    private TextView mai_1_dd_jyz_tv;
    private TextView mai_1_dd_shz_tv;
    private TextView mai_1_dd_jycg_tv;
    private TextView mai_1_dd_qbrw_tv;

    private ImageView mai_1_dd_fanhui;
    private String bundledata;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mai_1_dingdan);
        InitView();
        InitEvent();
        GetBundleData();
        setSelect(Integer.valueOf(bundledata));

    }

    private void GetBundleData() {
        Bundle bundle = this.getIntent().getExtras();
        bundledata = bundle.getString("mai1");
    }

    private void InitEvent() {
        mai_1_dd_fanhui.setOnClickListener(this);
        mai_1_dd_jyz.setOnClickListener(this);
        mai_1_dd_shz.setOnClickListener(this);
        mai_1_dd_jycg.setOnClickListener(this);
        mai_1_dd_qbrw.setOnClickListener(this);
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
        mViewPager = (ViewPager) findViewById(R.id.mai_1_dd_viewpager);
        mai_1_dd_jyz = (RelativeLayout) findViewById(R.id.mai_1_dd_jyz);
        mai_1_dd_shz = (RelativeLayout) findViewById(R.id.mai_1_dd_shz);
        mai_1_dd_jycg = (RelativeLayout) findViewById(R.id.mai_1_dd_jycg);
        mai_1_dd_qbrw = (RelativeLayout) findViewById(R.id.mai_1_dd_qbrw);
        mai_1_dd_jyz_tv = (TextView) findViewById(R.id.mai_1_dd_jyz_tv);
        mai_1_dd_shz_tv = (TextView) findViewById(R.id.mai_1_dd_shz_tv);
        mai_1_dd_jycg_tv = (TextView) findViewById(R.id.mai_1_dd_jycg_tv);
        mai_1_dd_qbrw_tv = (TextView) findViewById(R.id.mai_1_dd_qbrw_tv);
        mai_1_dd_fanhui = (ImageView) findViewById(R.id.mai_1_dd_fanhui);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mDatas = new ArrayList<Fragment>();
        Fragment tab0 = new Mai_1_Dd_Jyz();
        Fragment tab1 = new Mai_1_Dd_Shz();
        Fragment tab2 = new Mai_1_Dd_Jycg();
        Fragment tab3 = new Mai_1_Dd_Qbrw();

        mDatas.add(tab0);
        mDatas.add(tab1);
        mDatas.add(tab2);
        mDatas.add(tab3);

        adapter.ViewPagerAdapter(mDatas);
        mViewPager.setAdapter(adapter);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mai_1_dd_fanhui:
                finish();
                break;
            case R.id.mai_1_dd_jyz:
                setSelect(0);
                break;
            case R.id.mai_1_dd_shz:
                setSelect(1);
                break;
            case R.id.mai_1_dd_jycg:
                setSelect(2);
                break;
            case R.id.mai_1_dd_qbrw:
                setSelect(3);
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
                mai_1_dd_jyz_tv.setTextColor(0xffFFA500);
                break;
            case 1:
                mai_1_dd_shz_tv.setTextColor(0xffFFA500);
                break;
            case 2:
                mai_1_dd_jycg_tv.setTextColor(0xffFFA500);
                break;
            case 3:
                mai_1_dd_qbrw_tv.setTextColor(0xffFFA500);
                break;

        }
    }

    private void resetTextView() {
        mai_1_dd_jyz_tv.setTextColor(0xffcccccc);
        mai_1_dd_shz_tv.setTextColor(0xffcccccc);
        mai_1_dd_jycg_tv.setTextColor(0xffcccccc);
        mai_1_dd_qbrw_tv.setTextColor(0xffcccccc);
    }

}
