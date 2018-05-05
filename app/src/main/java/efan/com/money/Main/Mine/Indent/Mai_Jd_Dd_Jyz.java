package efan.com.money.Main.Mine.Indent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Adapter.Mai_Jd_Dd_Jyz_Adapter;
import efan.com.money.Adapter.OnItemClickListener;
import efan.com.money.Bean.NetDingDanBean;
import efan.com.money.Main.Mine.Indent.Particular.Jd_Dd_Jyz;
import efan.com.money.R;
import efan.com.money.Util.net.rx.BaseSubscriber;
import efan.com.money.Util.net.rx.RxRestClient;
import efan.com.money.Util.storage.MainPreference;
import efan.com.money.staticfunction.StaticUrl;
import efan.com.money.staticfunction.StaticValue;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/9/14.
 */

public class Mai_Jd_Dd_Jyz extends Fragment implements OnItemClickListener {
    private View view;

    // Header View
    private ProgressBar progressBar;
    private TextView textView;
    private ImageView imageView;
    // Footer View
    private ProgressBar footerProgressBar;
    private TextView footerTextView;
    private ImageView footerImageView;

    private SuperSwipeRefreshLayout mai_jd_dd_jyz_refresh;
    private RecyclerView mai_jd_dd_jyz_recycle;
    private Mai_Jd_Dd_Jyz_Adapter adapter;
    private List<NetDingDanBean> Jyz_AllList = new ArrayList<>();
    private RelativeLayout mai_jd_dd_jyz_rl;
    int PAGE = 0;
    private int TAG = 0, TAG_CREATE = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("onCreateView", "Mai_Jd_Dd_Jyz" + System.currentTimeMillis());
        if (view == null) {
            view = inflater.inflate(R.layout.mai_1_dd_jyz, container, false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        InitView();
        Refresh();

            Jyz_AllList.clear();

        GetListData(0);
        InitEvent();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.i("onResume", "Mai_Jd_Dd_Jyz   " + getUserVisibleHint());
        if (TAG > 0 && getUserVisibleHint()) {
            Jyz_AllList.clear();
            GetListData(0);
        }
        TAG += 1;
    }

    private void GetListData(int page) {
        adapter = new Mai_Jd_Dd_Jyz_Adapter(getActivity());
        adapter.setOnItemClickListener(this);
        RxRestClient.builder()
                .url(StaticUrl.GET_DING_DAN)
                .params("fd_id", "")
                .params("jd_id", MainPreference.getCustomAppProfile(StaticValue.USER_ID))
                .params("page", page)
                .params("zhuangtai", StaticValue.INDENT_CENTER)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>(getActivity()) {
                    @Override
                    public void onNext(String s) {
                        JSONObject object = new JSONObject();
                        if (object.parseObject(s).getString("success").equals("true")) {
                            List<NetDingDanBean> jyz_List = object.parseObject(object.parseObject(s).getString("data"),
                                    new TypeReference<ArrayList<NetDingDanBean>>() {
                                    });
                            Jyz_AllList.addAll(jyz_List);
                            if (Jyz_AllList.size() != 0) {
                                adapter.initData(Jyz_AllList);
                                mai_jd_dd_jyz_recycle.setAdapter(adapter);
                                mai_jd_dd_jyz_rl.setVisibility(View.GONE);
                                //如果没有返回数据
                                if (jyz_List.size() == 0) {
                                    Toast.makeText(getActivity(), "无更多数据", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                mai_jd_dd_jyz_rl.setVisibility(View.VISIBLE);
                            }
                            //更新数据后控件变化及更新adapter
                            adapter.notifyDataSetChanged();
                            if (PAGE != 0) {
                                mai_jd_dd_jyz_recycle.scrollToPosition(adapter.getItemCount() - jyz_List.size() - 1);
                            }

                        } else {
                            Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void Refresh() {
        mai_jd_dd_jyz_refresh.setHeaderView(createHeaderView());// add headerView
        mai_jd_dd_jyz_refresh.setFooterView(createFooterView());
        //设置下拉时，被包含的View是否随手指的移动而移动   true跟随   false不跟随
        mai_jd_dd_jyz_refresh.setTargetScrollWithLayout(true);
        //设置默认圆形的阴影颜色
        mai_jd_dd_jyz_refresh.setDefaultCircleShadowColor(R.color.colorPrimaryDark);
        //设置默认圆形背景色
        mai_jd_dd_jyz_refresh.setDefaultCircleBackgroundColor(R.color.white);
        //设置默认圆形进度条颜色
        mai_jd_dd_jyz_refresh.setDefaultCircleProgressColor(R.color.colorAccent);
        mai_jd_dd_jyz_refresh.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {
            @Override
            public void onRefresh() {

                textView.setText("正在刷新");
                imageView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Jyz_AllList.clear();
                        GetListData(0);
                        mai_jd_dd_jyz_refresh.setRefreshing(false);
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
        mai_jd_dd_jyz_refresh.setOnPushLoadMoreListener(new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {
            @Override
            public void onLoadMore() {
                PAGE += 1;
                footerTextView.setText("正在加载...");
                footerImageView.setVisibility(View.GONE);
                footerProgressBar.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        GetListData(PAGE);
                        mai_jd_dd_jyz_refresh.setLoadMore(false);
                        progressBar.setVisibility(View.GONE);
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

    private void InitView() {
        mai_jd_dd_jyz_refresh = (SuperSwipeRefreshLayout) view.findViewById(R.id.mai_jd_dd_jyz_refresh);
        mai_jd_dd_jyz_recycle = (RecyclerView) view.findViewById(R.id.mai_jd_dd_jyz_recycle);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mai_jd_dd_jyz_recycle.setLayoutManager(manager);
        mai_jd_dd_jyz_rl = (RelativeLayout) view.findViewById(R.id.mai_jd_dd_jyz_rl);
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
        intent.putExtra("id", Jyz_AllList.get(Position).getDdid());
        intent.putExtra("type", StaticValue.JYZ_TO_INDENT);
        startActivity(intent);
    }
}

