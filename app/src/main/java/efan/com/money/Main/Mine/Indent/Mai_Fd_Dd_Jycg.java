package efan.com.money.Main.Mine.Indent;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Adapter.Mai_Fd_Dd_Jycg_Adapter;
import efan.com.money.Bean.Mai_Fd_Dd_Jycg_Bean;
import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/14.
 */

public class Mai_Fd_Dd_Jycg extends Fragment {
    private View view;
    // Header View
    private ProgressBar progressBar;
    private TextView textView;
    private ImageView imageView;
    // Footer View
    private ProgressBar footerProgressBar;
    private TextView footerTextView;
    private ImageView footerImageView;

    private SuperSwipeRefreshLayout mai_fd_dd_jycg_refresh;
    private RecyclerView mai_fd_dd_jycg_recycle;
    private Mai_Fd_Dd_Jycg_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.mai_2_dd_jycg, container, false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        InitView();
        InitEvent();
        Refresh();
        lv();
        return view;
    }

    private void Refresh() {
        mai_fd_dd_jycg_refresh.setHeaderView(createHeaderView());// add headerView
        mai_fd_dd_jycg_refresh.setFooterView(createFooterView());
        //设置下拉时，被包含的View是否随手指的移动而移动   true跟随   false不跟随
        mai_fd_dd_jycg_refresh.setTargetScrollWithLayout(true);
        //设置默认圆形的阴影颜色
        mai_fd_dd_jycg_refresh.setDefaultCircleShadowColor(R.color.colorPrimaryDark);
        //设置默认圆形背景色
        mai_fd_dd_jycg_refresh.setDefaultCircleBackgroundColor(R.color.white);
        //设置默认圆形进度条颜色
        mai_fd_dd_jycg_refresh.setDefaultCircleProgressColor(R.color.colorAccent);
        mai_fd_dd_jycg_refresh.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {
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
                        mai_fd_dd_jycg_refresh.setRefreshing(false);
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
        mai_fd_dd_jycg_refresh.setOnPushLoadMoreListener(new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {
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
                        mai_fd_dd_jycg_refresh.setLoadMore(false);
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
        List<Mai_Fd_Dd_Jycg_Bean> list = new ArrayList<Mai_Fd_Dd_Jycg_Bean>();

        Mai_Fd_Dd_Jycg_Bean bean = new Mai_Fd_Dd_Jycg_Bean();
        bean.setMai_fd_dd_jycg_item_lx("[微信]");
        bean.setMai_fd_dd_jycg_item_rwm("商品推销，朋友圈保留一天。");
        bean.setMai_fd_dd_jycg_item_time("2017-09-12");
        bean.setMai_fd_dd_jycg_item_yhm("遗忘");
        bean.setMai_fd_dd_jycg_item_jg("￥1");
        bean.setMai_fd_dd_jycg_item_tupian(R.mipmap.touxiang_1);
        list.add(bean);

        Mai_Fd_Dd_Jycg_Bean bean1 = new Mai_Fd_Dd_Jycg_Bean();
        bean1.setMai_fd_dd_jycg_item_lx("[QQ]");
        bean1.setMai_fd_dd_jycg_item_rwm("投票，分分钟");
        bean1.setMai_fd_dd_jycg_item_time("2017-09-11");
        bean1.setMai_fd_dd_jycg_item_yhm("edge");
        bean1.setMai_fd_dd_jycg_item_jg("￥3");
        bean1.setMai_fd_dd_jycg_item_tupian(R.mipmap.touxiang_2);
        list.add(bean1);

        Mai_Fd_Dd_Jycg_Bean bean2 = new Mai_Fd_Dd_Jycg_Bean();
        bean2.setMai_fd_dd_jycg_item_lx("[微博]");
        bean2.setMai_fd_dd_jycg_item_rwm("点赞点赞");
        bean2.setMai_fd_dd_jycg_item_time("2017-09-10");
        bean2.setMai_fd_dd_jycg_item_yhm("黑夜");
        bean2.setMai_fd_dd_jycg_item_jg("￥1");
        bean2.setMai_fd_dd_jycg_item_tupian(R.mipmap.touxiang_3);
        list.add(bean2);

        Mai_Fd_Dd_Jycg_Bean bean3 = new Mai_Fd_Dd_Jycg_Bean();
        bean3.setMai_fd_dd_jycg_item_lx("[朋友圈]");
        bean3.setMai_fd_dd_jycg_item_rwm("朋友圈点赞");
        bean3.setMai_fd_dd_jycg_item_time("2017-09-19");
        bean3.setMai_fd_dd_jycg_item_yhm("那个姑娘");
        bean3.setMai_fd_dd_jycg_item_jg("￥1");
        bean3.setMai_fd_dd_jycg_item_tupian(R.mipmap.touxiang_4);
        list.add(bean3);
        Mai_Fd_Dd_Jycg_Bean bean4 = new Mai_Fd_Dd_Jycg_Bean();
        bean4.setMai_fd_dd_jycg_item_lx("[朋友圈]");
        bean4.setMai_fd_dd_jycg_item_rwm("朋友圈点赞");
        bean4.setMai_fd_dd_jycg_item_time("2017-09-19");
        bean4.setMai_fd_dd_jycg_item_yhm("那个姑娘");
        bean4.setMai_fd_dd_jycg_item_jg("￥1");
        bean4.setMai_fd_dd_jycg_item_tupian(R.mipmap.touxiang_4);
        list.add(bean4);
        Mai_Fd_Dd_Jycg_Bean bean5 = new Mai_Fd_Dd_Jycg_Bean();
        bean5.setMai_fd_dd_jycg_item_lx("[朋友圈]");
        bean5.setMai_fd_dd_jycg_item_rwm("朋友圈点赞");
        bean5.setMai_fd_dd_jycg_item_time("2017-09-19");
        bean5.setMai_fd_dd_jycg_item_yhm("那个姑娘");
        bean5.setMai_fd_dd_jycg_item_jg("￥1");
        bean5.setMai_fd_dd_jycg_item_tupian(R.mipmap.touxiang_4);
        list.add(bean5);
        Mai_Fd_Dd_Jycg_Bean bean6 = new Mai_Fd_Dd_Jycg_Bean();
        bean6.setMai_fd_dd_jycg_item_lx("[朋友圈]");
        bean6.setMai_fd_dd_jycg_item_rwm("朋友圈点赞");
        bean6.setMai_fd_dd_jycg_item_time("2017-09-19");
        bean6.setMai_fd_dd_jycg_item_yhm("那个姑娘");
        bean6.setMai_fd_dd_jycg_item_jg("￥1");
        bean6.setMai_fd_dd_jycg_item_tupian(R.mipmap.touxiang_4);
        list.add(bean6);

        adapter = new Mai_Fd_Dd_Jycg_Adapter(getActivity(), list);
        mai_fd_dd_jycg_recycle.setAdapter(adapter);
    }

    private void InitView() {
        mai_fd_dd_jycg_refresh = (SuperSwipeRefreshLayout) view.findViewById(R.id.mai_fd_dd_jycg_refresh);
        mai_fd_dd_jycg_recycle = (RecyclerView) view.findViewById(R.id.mai_fd_dd_jycg_recycle);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mai_fd_dd_jycg_recycle.setLayoutManager(manager);
    }

    private void InitEvent() {
    }

    private View createHeaderView() {
        View headerView = LayoutInflater.from(getActivity())
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

    private View createFooterView() {
        View footerView = LayoutInflater.from(getActivity())
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

}

