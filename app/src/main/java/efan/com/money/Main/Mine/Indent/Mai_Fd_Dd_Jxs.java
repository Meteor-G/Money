package efan.com.money.Main.Mine.Indent;

import android.content.Intent;
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

import efan.com.money.Adapter.Mai_Fd_Dd_Jxs_Adapter;
import efan.com.money.Adapter.OnItemClickListener;
import efan.com.money.Bean.Mai_Fd_Dd_Jxs_Bean;
import efan.com.money.Main.Mine.Indent.Particular.Fd_Dd_Indent;
import efan.com.money.R;
import efan.com.money.staticfunction.StaticValue;

/**
 * Created by Administrator on 2017/9/14.
 */

public class Mai_Fd_Dd_Jxs extends Fragment implements OnItemClickListener {
    private View view;
    // Header View
    private ProgressBar progressBar;
    private TextView textView;
    private ImageView imageView;
    // Footer View
    private ProgressBar footerProgressBar;
    private TextView footerTextView;
    private ImageView footerImageView;

    private SuperSwipeRefreshLayout mai_fd_dd_jxs_refresh;
    private RecyclerView mai_fd_dd_jxs_recycle;
    private Mai_Fd_Dd_Jxs_Adapter adapter;

    private List<Mai_Fd_Dd_Jxs_Bean> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.mai_2_dd_jxs, container, false);
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
        mai_fd_dd_jxs_refresh.setHeaderView(createHeaderView());// add headerView
        mai_fd_dd_jxs_refresh.setFooterView(createFooterView());
        //设置下拉时，被包含的View是否随手指的移动而移动   true跟随   false不跟随
        mai_fd_dd_jxs_refresh.setTargetScrollWithLayout(true);
        //设置默认圆形的阴影颜色
        mai_fd_dd_jxs_refresh.setDefaultCircleShadowColor(R.color.colorPrimaryDark);
        //设置默认圆形背景色
        mai_fd_dd_jxs_refresh.setDefaultCircleBackgroundColor(R.color.white);
        //设置默认圆形进度条颜色
        mai_fd_dd_jxs_refresh.setDefaultCircleProgressColor(R.color.colorAccent);
        mai_fd_dd_jxs_refresh.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {
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
                        mai_fd_dd_jxs_refresh.setRefreshing(false);
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
        mai_fd_dd_jxs_refresh.setOnPushLoadMoreListener(new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {
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
                        mai_fd_dd_jxs_refresh.setLoadMore(false);
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
        list = new ArrayList<Mai_Fd_Dd_Jxs_Bean>();

        Mai_Fd_Dd_Jxs_Bean bean = new Mai_Fd_Dd_Jxs_Bean();
        bean.setMai_fd_dd_jxs_item_lx("[微信]");
        bean.setMai_fd_dd_jxs_item_rwm("商品推销");
        bean.setMai_fd_dd_jxs_item_time("2017-09-12");
        bean.setMai_fd_dd_jxs_item_yhm("那天");
        bean.setMai_fd_dd_jxs_item_zt("未上传截图");
        bean.setMai_fd_dd_jxs_item_tupian(R.mipmap.touxiang_6);
        list.add(bean);

        Mai_Fd_Dd_Jxs_Bean bean1 = new Mai_Fd_Dd_Jxs_Bean();
        bean1.setMai_fd_dd_jxs_item_lx("[QQ]");
        bean1.setMai_fd_dd_jxs_item_rwm("投票选财主了");
        bean1.setMai_fd_dd_jxs_item_time("2017-09-11");
        bean1.setMai_fd_dd_jxs_item_yhm("遗忘");
        bean1.setMai_fd_dd_jxs_item_zt("未上传截图");
        bean1.setMai_fd_dd_jxs_item_tupian(R.mipmap.touxiang_5);
        list.add(bean1);

        Mai_Fd_Dd_Jxs_Bean bean2 = new Mai_Fd_Dd_Jxs_Bean();
        bean2.setMai_fd_dd_jxs_item_lx("[微博]");
        bean2.setMai_fd_dd_jxs_item_rwm("就是你了，快来拿钱");
        bean2.setMai_fd_dd_jxs_item_time("2017-09-13");
        bean2.setMai_fd_dd_jxs_item_yhm("Dreamer");
        bean2.setMai_fd_dd_jxs_item_zt("未上传截图");
        bean2.setMai_fd_dd_jxs_item_tupian(R.mipmap.touxiang_1);
        list.add(bean2);

        Mai_Fd_Dd_Jxs_Bean bean3 = new Mai_Fd_Dd_Jxs_Bean();
        bean3.setMai_fd_dd_jxs_item_lx("[朋友圈]");
        bean3.setMai_fd_dd_jxs_item_rwm("鞋子降价了");
        bean3.setMai_fd_dd_jxs_item_time("2017-09-15");
        bean3.setMai_fd_dd_jxs_item_yhm("忘掉");
        bean3.setMai_fd_dd_jxs_item_zt("未上传截图");
        bean3.setMai_fd_dd_jxs_item_tupian(R.mipmap.touxiang_8);
        list.add(bean3);
        adapter = new Mai_Fd_Dd_Jxs_Adapter(getActivity(), list);
        mai_fd_dd_jxs_recycle.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    private void InitView() {
        mai_fd_dd_jxs_refresh = (SuperSwipeRefreshLayout) view.findViewById(R.id.mai_fd_dd_jxs_refresh);
        mai_fd_dd_jxs_recycle = (RecyclerView) view.findViewById(R.id.mai_fd_dd_jxs_recycle);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mai_fd_dd_jxs_recycle.setLayoutManager(manager);
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

    @Override
    public void onItemClick(View view, int Position) {
        Intent intent = new Intent(getActivity(), Fd_Dd_Indent.class);
        intent.putExtra("id", list.get(Position).getMai_fd_dd_jxs_item_lx());
        intent.putExtra("type", StaticValue.FD_JXS_TO_INDENT);
        startActivity(intent);
    }
}
