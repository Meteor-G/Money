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

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Adapter.JD_Main_Adapter;
import efan.com.money.Adapter.OnItemClickListener;
import efan.com.money.Bean.NetFaDanBean;
import efan.com.money.R;
import efan.com.money.Util.net.rx.BaseSubscriber;
import efan.com.money.Util.net.rx.RxRestClient;
import efan.com.money.staticfunction.StaticUrl;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

    private JD_Main_Adapter adapter = new JD_Main_Adapter(this);


    private List<NetFaDanBean> AllList = new ArrayList<>();
    int PAGE = 0;
    int SIZE = 0;//每次返回的个数


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jd_main);
        InitView();
        InitEvent();
        Refresh();
        GetData(0);
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
                PAGE = 0;
                textView.setText("正在刷新");
                imageView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        AllList.clear();
                        GetData(0);
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
                PAGE += 1;
                footerTextView.setText("正在加载...");
                footerImageView.setVisibility(View.GONE);
                footerProgressBar.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        GetData(PAGE);
                        adapter.notifyDataSetChanged();
                        swipe_refresh.setLoadMore(false);
                        progressBar.setVisibility(View.GONE);
                        recycle.scrollToPosition(adapter.getItemCount() - 1);
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

    private void GetData(int page) {
        RxRestClient.builder()
                .url(StaticUrl.GET_FA_DAN)
                .params("fd_id", "0")
                .params("page", page)
                .load(JD_Main.this)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>(this) {
                    @Override
                    public void onNext(String s) {
                        JSONObject object = new JSONObject();
                        if (object.parseObject(s).getString("success").equals("true")) {
                            List<NetFaDanBean> mList = object.parseObject(object.parseObject(s).getString("data"),
                                    new TypeReference<ArrayList<NetFaDanBean>>() {
                                    });
//                            SIZE = mList.size();
                            AllList.addAll(mList);
                            adapter.initData(AllList);
                            recycle.setAdapter(adapter);
                        } else {
                            Toast.makeText(JD_Main.this, "获取数据失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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
        adapter.setOnItemClickListener(this);
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
        intent.putExtra("id", AllList.get(Position).getFdid());
        startActivity(intent);
    }
}
