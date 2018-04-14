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

import efan.com.money.Adapter.Mai_Jd_Dd_Shz_Adapter;
import efan.com.money.Adapter.OnItemClickListener;
import efan.com.money.Bean.Mai_1_Dd_Shz_Bean;
import efan.com.money.Main.Mine.Indent.Particular.Jd_Dd_Jyz;
import efan.com.money.R;
import efan.com.money.staticfunction.StaticValue;

/**
 * Created by Administrator on 2017/9/14.
 */

public class Mai_Jd_Dd_Shz extends Fragment implements OnItemClickListener {
    private View view;

    private ProgressBar progressBar;
    private TextView textView;
    private ImageView imageView;
    // Footer View
    private ProgressBar footerProgressBar;
    private TextView footerTextView;
    private ImageView footerImageView;

    private SuperSwipeRefreshLayout mai_jd_dd_shz_refresh;
    private RecyclerView mai_jd_dd_shz_recycle;
    private Mai_Jd_Dd_Shz_Adapter adapter;
    private List<Mai_1_Dd_Shz_Bean> mlist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.mai_1_dd_shz, container, false);
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
        mai_jd_dd_shz_refresh.setHeaderView(createHeaderView());// add headerView
        mai_jd_dd_shz_refresh.setFooterView(createFooterView());
        //设置下拉时，被包含的View是否随手指的移动而移动   true跟随   false不跟随
        mai_jd_dd_shz_refresh.setTargetScrollWithLayout(true);
        //设置默认圆形的阴影颜色
        mai_jd_dd_shz_refresh.setDefaultCircleShadowColor(R.color.colorPrimaryDark);
        //设置默认圆形背景色
        mai_jd_dd_shz_refresh.setDefaultCircleBackgroundColor(R.color.white);
        //设置默认圆形进度条颜色
        mai_jd_dd_shz_refresh.setDefaultCircleProgressColor(R.color.colorAccent);
        mai_jd_dd_shz_refresh.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {
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
                        mai_jd_dd_shz_refresh.setRefreshing(false);
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
        mai_jd_dd_shz_refresh.setOnPushLoadMoreListener(new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {
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
                        mai_jd_dd_shz_refresh.setLoadMore(false);
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
        mlist = new ArrayList<>();

        Mai_1_Dd_Shz_Bean bean = new Mai_1_Dd_Shz_Bean();
        bean.setMai_1_dd_shz_item_lx("[微信]");
        bean.setMai_1_dd_shz_item_rwm("商品推销，朋友圈保留一天。");
        bean.setMai_1_dd_shz_item_time("2017-09-12");
        bean.setMai_1_dd_shz_item_yhm("遗忘");
        bean.setMai_1_dd_shz_item_zt("未上传截图");
        bean.setMai_1_dd_shz_jiage("￥1");
        bean.setMai_1_dd_shz_iv_1(R.mipmap.mai_1_dd_shz_tv);
        bean.setMai_1_dd_shz_iv_2(R.mipmap.mai_1_dd_shz_tv);
        mlist.add(bean);

        Mai_1_Dd_Shz_Bean bean1 = new Mai_1_Dd_Shz_Bean();
        bean1.setMai_1_dd_shz_item_lx("[贴吧]");
        bean1.setMai_1_dd_shz_item_rwm("贴吧推销");
        bean1.setMai_1_dd_shz_item_time("2017-09-12");
        bean1.setMai_1_dd_shz_item_yhm("遗忘");
        bean1.setMai_1_dd_shz_item_zt("未上传截图");
        bean1.setMai_1_dd_shz_jiage("￥3");
        bean1.setMai_1_dd_shz_iv_1(R.mipmap.mai_1_dd_shz_tv);
        bean1.setMai_1_dd_shz_iv_2(R.mipmap.mai_1_dd_shz_tv);
        mlist.add(bean1);


        Mai_1_Dd_Shz_Bean bean2 = new Mai_1_Dd_Shz_Bean();
        bean2.setMai_1_dd_shz_item_lx("[陌陌]");
        bean2.setMai_1_dd_shz_item_rwm("你是我找的人吗");
        bean2.setMai_1_dd_shz_item_time("2017-09-12");
        bean2.setMai_1_dd_shz_item_yhm("遗忘");
        bean2.setMai_1_dd_shz_item_zt("未上传截图");
        bean2.setMai_1_dd_shz_jiage("￥2");
        bean2.setMai_1_dd_shz_iv_1(R.mipmap.mai_1_dd_shz_tv);
        bean2.setMai_1_dd_shz_iv_2(R.mipmap.mai_1_dd_shz_tv);
        mlist.add(bean2);

        Mai_1_Dd_Shz_Bean bean3 = new Mai_1_Dd_Shz_Bean();
        bean3.setMai_1_dd_shz_item_lx("[探探]");
        bean3.setMai_1_dd_shz_item_rwm("寻找另一半");
        bean3.setMai_1_dd_shz_item_time("2017-09-12");
        bean3.setMai_1_dd_shz_item_yhm("遗忘");
        bean3.setMai_1_dd_shz_item_zt("未上传截图");
        bean3.setMai_1_dd_shz_jiage("￥1");
        bean3.setMai_1_dd_shz_iv_1(R.mipmap.mai_1_dd_shz_tv);
        bean3.setMai_1_dd_shz_iv_2(R.mipmap.mai_1_dd_shz_tv);
        mlist.add(bean3);
        adapter = new Mai_Jd_Dd_Shz_Adapter(getActivity(), mlist);
        mai_jd_dd_shz_recycle.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    private void InitView() {
        mai_jd_dd_shz_refresh = (SuperSwipeRefreshLayout) view.findViewById(R.id.mai_jd_dd_shz_refresh);
        mai_jd_dd_shz_recycle = (RecyclerView) view.findViewById(R.id.mai_jd_dd_shz_recycle);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mai_jd_dd_shz_recycle.setLayoutManager(manager);
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
        Intent intent = new Intent(getActivity(), Jd_Dd_Jyz.class);
        intent.putExtra("id", mlist.get(Position).getMai_1_dd_shz_item_zt());
        intent.putExtra("type", StaticValue.SHZ_TO_INDENT);
        startActivity(intent);
    }
}

