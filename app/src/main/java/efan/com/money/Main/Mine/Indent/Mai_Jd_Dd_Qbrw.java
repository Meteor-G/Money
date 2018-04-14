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

import efan.com.money.Adapter.Mai_Jd_Dd_Qbrw_Adapter;
import efan.com.money.Bean.Mai_1_Dd_Qbrw_Bean;
import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/14.
 */

public class Mai_Jd_Dd_Qbrw extends Fragment {
    private View view;

    private ProgressBar progressBar;
    private TextView textView;
    private ImageView imageView;
    // Footer View
    private ProgressBar footerProgressBar;
    private TextView footerTextView;
    private ImageView footerImageView;

    private SuperSwipeRefreshLayout mai_jd_dd_qbrw_refresh;
    private RecyclerView mai_jd_dd_qbrw_recycle;
    private Mai_Jd_Dd_Qbrw_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.mai_1_dd_qbrw, container, false);
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
        mai_jd_dd_qbrw_refresh.setHeaderView(createHeaderView());// add headerView
        mai_jd_dd_qbrw_refresh.setFooterView(createFooterView());
        //设置下拉时，被包含的View是否随手指的移动而移动   true跟随   false不跟随
        mai_jd_dd_qbrw_refresh.setTargetScrollWithLayout(true);
        //设置默认圆形的阴影颜色
        mai_jd_dd_qbrw_refresh.setDefaultCircleShadowColor(R.color.colorPrimaryDark);
        //设置默认圆形背景色
        mai_jd_dd_qbrw_refresh.setDefaultCircleBackgroundColor(R.color.white);
        //设置默认圆形进度条颜色
        mai_jd_dd_qbrw_refresh.setDefaultCircleProgressColor(R.color.colorAccent);
        mai_jd_dd_qbrw_refresh.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {
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
                        mai_jd_dd_qbrw_refresh.setRefreshing(false);
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
        mai_jd_dd_qbrw_refresh.setOnPushLoadMoreListener(new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {
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
                        mai_jd_dd_qbrw_refresh.setLoadMore(false);
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
        List<Mai_1_Dd_Qbrw_Bean> list = new ArrayList<Mai_1_Dd_Qbrw_Bean>();

        Mai_1_Dd_Qbrw_Bean bean = new Mai_1_Dd_Qbrw_Bean();
        bean.setMai_1_dd_qbrw_item_lx("[微信]");
        bean.setMai_1_dd_qbrw_item_rwm("商品推销，朋友圈保留一天。");
        bean.setMai_1_dd_qbrw_item_time("2017-09-12");
        bean.setMai_1_dd_qbrw_item_yhm("那朵云");
        bean.setMai_1_dd_qbrw_item_zt("已完成");
        bean.setMai_1_dd_qbrw_item_tupian(R.mipmap.touxiang_1);
        list.add(bean);

        Mai_1_Dd_Qbrw_Bean bean1 = new Mai_1_Dd_Qbrw_Bean();
        bean1.setMai_1_dd_qbrw_item_lx("[QQ]");
        bean1.setMai_1_dd_qbrw_item_rwm("快点拿钱投票啦！");
        bean1.setMai_1_dd_qbrw_item_time("2017-09-8");
        bean1.setMai_1_dd_qbrw_item_yhm("是你");
        bean1.setMai_1_dd_qbrw_item_zt("已完成");
        bean1.setMai_1_dd_qbrw_item_tupian(R.mipmap.touxiang_8);
        list.add(bean1);
        Mai_1_Dd_Qbrw_Bean bean2 = new Mai_1_Dd_Qbrw_Bean();
        bean2.setMai_1_dd_qbrw_item_lx("[微博]");
        bean2.setMai_1_dd_qbrw_item_rwm("兴趣好友");
        bean2.setMai_1_dd_qbrw_item_time("2017-09-6");
        bean2.setMai_1_dd_qbrw_item_yhm("Edge");
        bean2.setMai_1_dd_qbrw_item_zt("已完成");
        bean2.setMai_1_dd_qbrw_item_tupian(R.mipmap.touxiang_6);
        list.add(bean2);

        Mai_1_Dd_Qbrw_Bean bean3 = new Mai_1_Dd_Qbrw_Bean();
        bean3.setMai_1_dd_qbrw_item_lx("[朋友圈]");
        bean3.setMai_1_dd_qbrw_item_rwm("商品大甩卖啦");
        bean3.setMai_1_dd_qbrw_item_time("2017-09-2");
        bean3.setMai_1_dd_qbrw_item_yhm("遗");
        bean3.setMai_1_dd_qbrw_item_zt("已完成");
        bean3.setMai_1_dd_qbrw_item_tupian(R.mipmap.touxiang_4);
        list.add(bean3);
        adapter = new Mai_Jd_Dd_Qbrw_Adapter(getActivity(), list);
        mai_jd_dd_qbrw_recycle.setAdapter(adapter);
    }

    private void InitView() {
        mai_jd_dd_qbrw_refresh = (SuperSwipeRefreshLayout) view.findViewById(R.id.mai_jd_dd_qbrw_refresh);
        mai_jd_dd_qbrw_recycle = (RecyclerView) view.findViewById(R.id.mai_jd_dd_qbrw_recycle);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mai_jd_dd_qbrw_recycle.setLayoutManager(manager);
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
