package efan.com.money.Main.Find;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Adapter.JD_Main_Adapter;
import efan.com.money.Adapter.OnItemClickListener;
import efan.com.money.Bean.JD_MainBean;
import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/12.
 */

public class JD_Main extends AppCompatActivity implements View.OnClickListener, OnItemClickListener {
    private ImageView jd_main_fanhui;
    private SuperSwipeRefreshLayout swipe_refresh;
    private RecyclerView recycle;

    // Header View
    private ProgressBar progressBar;
    private TextView textView;
    private ImageView imageView;

    // Footer View
    private ProgressBar footerProgressBar;
    private TextView footerTextView;
    private ImageView footerImageView;

    private JD_Main_Adapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jd_main);
        InitView();
        InitEvent();
        Refresh();
        lv();
    }

    private void Refresh() {
        swipe_refresh.setHeaderView(createHeaderView());// add headerView
        swipe_refresh.setFooterView(createFooterView());
        //设置下拉时，被包含的View是否随手指的移动而移动   true跟随   false不跟随
        swipe_refresh.setTargetScrollWithLayout(true);
        //设置默认圆形的阴影颜色
        swipe_refresh.setDefaultCircleShadowColor(R.color.colorPrimaryDark);
        //设置默认圆形背景色
        swipe_refresh.setDefaultCircleBackgroundColor(R.color.white);
        //设置默认圆形进度条颜色
        swipe_refresh.setDefaultCircleProgressColor(R.color.colorAccent);
        swipe_refresh.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {
            @Override
            public void onRefresh() {

                textView.setText("正在刷新");
                imageView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
//                        RecycleBean bean = new RecycleBean();
//                        bean.setTv("159");
////                        mAdapter.index(1, bean);
//                        mList.add(1, bean);
////                        mAdapter.notifyItemInserted(1);
//                        mAdapter.notifyDataSetChanged();
                        swipe_refresh.setRefreshing(false);
                        progressBar.setVisibility(View.GONE);
                    }
                }, 1000);
            }

            @Override
            public void onPullDistance(int i) {

            }

            @Override
            public void onPullEnable(boolean enable) {
                textView.setText(enable ? "松开刷新" : "下拉刷新");
                imageView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                imageView.setRotation(enable ? 180 : 0);
            }
        });
        /**
         * 上拉加载更多
         */
        swipe_refresh.setOnPushLoadMoreListener(new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {
            @Override
            public void onLoadMore() {
                footerTextView.setText("正在加载...");
                footerImageView.setVisibility(View.GONE);
                footerProgressBar.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
//                        RecycleBean bean = new RecycleBean();
//                        bean.setTv("159");
//                        mList.add(bean);
//                        mAdapter.notifyDataSetChanged();
                        swipe_refresh.setLoadMore(false);
                        progressBar.setVisibility(View.GONE);
//                        super_recycle.scrollToPosition(mAdapter.getItemCount() - 1);
                    }
                }, 1000);
            }

            @Override
            public void onPushDistance(int i) {

            }

            @Override
            public void onPushEnable(boolean enable) {
                footerTextView.setText(enable ? "松开加载" : "上拉加载");
                footerImageView.setVisibility(View.VISIBLE);
                footerProgressBar.setVisibility(View.GONE);
                footerImageView.setRotation(enable ? 0 : 180);
            }
        });
    }

    private void lv() {
        adapter = new JD_Main_Adapter(this);

        List<JD_MainBean> list = new ArrayList<JD_MainBean>();

        JD_MainBean bean = new JD_MainBean();
        bean.setJd_main_item_iv(R.mipmap.main_tuiguang);
        bean.setJd_main_item_lx_tv("推广");
        bean.setJd_main_item_title_tv("商品推销，朋友圈保留一天。");
        bean.setJd_main_item_mjxy_tv("卖家信用：新卖家");
        bean.setJd_main_item_cj_tv("近7天成交率：80%");
        bean.setJd_main_item_qian("￥15");
        list.add(bean);

        JD_MainBean bean1 = new JD_MainBean();
        bean1.setJd_main_item_iv(R.mipmap.mian_dianjiliang);
        bean1.setJd_main_item_lx_tv("点击量");
        bean1.setJd_main_item_title_tv("谢谢大家参观");
        bean1.setJd_main_item_mjxy_tv("卖家信用：新卖家");
        bean1.setJd_main_item_cj_tv("近7天成交率：80%");
        bean1.setJd_main_item_qian("￥1");
        list.add(bean1);

        JD_MainBean bean2 = new JD_MainBean();
        bean2.setJd_main_item_iv(R.mipmap.main_jizan);
        bean2.setJd_main_item_lx_tv("集赞");
        bean2.setJd_main_item_title_tv("拜托大家了，集赞集赞");
        bean2.setJd_main_item_mjxy_tv("卖家信用：新卖家");
        bean2.setJd_main_item_cj_tv("近7天成交率：50%");
        bean2.setJd_main_item_qian("￥2");
        list.add(bean2);

        JD_MainBean bean3 = new JD_MainBean();
        bean3.setJd_main_item_iv(R.mipmap.main_toupiao);
        bean3.setJd_main_item_lx_tv("投票");
        bean3.setJd_main_item_title_tv("拉选票");
        bean3.setJd_main_item_mjxy_tv("卖家信用：新卖家");
        bean3.setJd_main_item_cj_tv("近7天成交率：100%");
        bean3.setJd_main_item_qian("￥1");
        list.add(bean3);
        JD_MainBean bean4 = new JD_MainBean();
        bean4.setJd_main_item_iv(R.mipmap.main_toupiao);
        bean4.setJd_main_item_lx_tv("投票");
        bean4.setJd_main_item_title_tv("拉选票");
        bean4.setJd_main_item_mjxy_tv("卖家信用：新卖家");
        bean4.setJd_main_item_cj_tv("近7天成交率：100%");
        bean4.setJd_main_item_qian("￥1");
        list.add(bean4);
        JD_MainBean bean5 = new JD_MainBean();
        bean5.setJd_main_item_iv(R.mipmap.main_toupiao);
        bean5.setJd_main_item_lx_tv("投票");
        bean5.setJd_main_item_title_tv("拉选票");
        bean5.setJd_main_item_mjxy_tv("卖家信用：新卖家");
        bean5.setJd_main_item_cj_tv("近7天成交率：100%");
        bean5.setJd_main_item_qian("￥1");
        list.add(bean5);
        adapter.initData(list);
        recycle.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    private void InitEvent() {
        jd_main_fanhui.setOnClickListener(this);

    }

    private void InitView() {
        jd_main_fanhui = (ImageView) findViewById(R.id.jd_main_fanhui);
        swipe_refresh = (SuperSwipeRefreshLayout) findViewById(R.id.jd_main_swipe_refresh);
        recycle = (RecyclerView) findViewById(R.id.recycle);
        LinearLayoutManager manager = new LinearLayoutManager(JD_Main.this, LinearLayoutManager.VERTICAL, false);
        recycle.setLayoutManager(manager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jd_main_fanhui:
                finish();
                break;
        }
    }

    private View createFooterView() {
        View footerView = LayoutInflater.from(JD_Main.this)
                .inflate(R.layout.layout_footer, null);
        footerProgressBar = (ProgressBar) footerView
                .findViewById(R.id.footer_pb_view);
        footerImageView = (ImageView) footerView
                .findViewById(R.id.footer_image_view);
        footerTextView = (TextView) footerView
                .findViewById(R.id.footer_text_view);
        footerProgressBar.setVisibility(View.GONE);
        footerImageView.setVisibility(View.VISIBLE);
        footerImageView.setImageResource(R.mipmap.down_arrow);
        footerTextView.setText("上拉加载更多...");
        return footerView;
    }

    private View createHeaderView() {
        View headerView = LayoutInflater.from(JD_Main.this)
                .inflate(R.layout.layout_head, null);
        progressBar = (ProgressBar) headerView.findViewById(R.id.pb_view);
        textView = (TextView) headerView.findViewById(R.id.text_view);
        textView.setText("下拉刷新");
        imageView = (ImageView) headerView.findViewById(R.id.image_view);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(R.mipmap.down_arrow);
        progressBar.setVisibility(View.GONE);
        return headerView;
    }

    @Override
    public void onItemClick(View view, int Position) {
        Toast.makeText(this, "点击了" + Position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, JD_Xiangxi.class);
        intent.putExtra("id", Position);
        startActivity(intent);
    }
}
