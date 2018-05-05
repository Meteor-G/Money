package efan.com.money.Adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Bean.NetShouCang;
import efan.com.money.R;
import efan.com.money.Util.net.rx.BaseSubscriber;
import efan.com.money.Util.net.rx.RxRestClient;
import efan.com.money.staticfunction.StaticUrl;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者： ZlyjD.
 * 时间：2018/5/3.
 */

public class MineOtherCollectAdapter extends BaseAdapter {
    private Context context;
    private List<NetShouCang> list = new ArrayList<NetShouCang>();
    private LayoutInflater layoutInflater;
    private PopupWindow poPupWindow;
    private TextView ppw_fin_indent_get_qd;
    private TextView ppw_fin_indent_get_qx;
    private TextView main_xxwh_xx_ppw_nr;
    private JSONObject object = new JSONObject();

    public MineOtherCollectAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void init(List<NetShouCang> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.item_mai_jd_zhbd, null);
            holder.mai_1_zhbd_itme_lx = (TextView) view.findViewById(R.id.mai_1_zhbd_itme_lx);
            holder.mai_1_zhbd_itme_zh = (TextView) view.findViewById(R.id.mai_1_zhbd_itme_zh);
            holder.zhbd_zh = (TextView) view.findViewById(R.id.zhbd_zh);
            holder.mai_1_zhbd_item_sc = (RelativeLayout) view.findViewById(R.id.mai_1_zhbd_item_sc);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.mai_1_zhbd_itme_lx.setText("[" + list.get(position).getZhanghao() + "]");
        holder.mai_1_zhbd_itme_zh.setText(list.get(position).getFd_mingcheng());
        holder.zhbd_zh.setText("任务名称：");
        holder.mai_1_zhbd_item_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showpopupWindow(holder.mai_1_zhbd_item_sc, position);
            }
        });
        return view;
    }

    private void showpopupWindow(View parent, int position) {
        if (poPupWindow == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.ppw_find_indent_get, null);
            poPupWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, true);
            initPop(view, position);

            view.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View arg0, MotionEvent arg1) {
                    if (poPupWindow != null && poPupWindow.isShowing()) {
                        poPupWindow.dismiss();
                        poPupWindow = null;
                    }
                    return true;
                }
            });
        }
        poPupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        poPupWindow.setFocusable(true);
        poPupWindow.setOutsideTouchable(true);
        poPupWindow.setBackgroundDrawable(new BitmapDrawable());
        poPupWindow
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        poPupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
    }

    private void initPop(View view, final int position) {
        main_xxwh_xx_ppw_nr = (TextView) view.findViewById(R.id.main_xxwh_xx_ppw_nr);
        ppw_fin_indent_get_qd = (TextView) view.findViewById(R.id.ppw_fin_indent_get_qd);
        ppw_fin_indent_get_qx = (TextView) view.findViewById(R.id.ppw_fin_indent_get_qd);
        main_xxwh_xx_ppw_nr.setText("确定删除\n该收藏吗？");
        ppw_fin_indent_get_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poPupWindow.dismiss();
                poPupWindow = null;
            }
        });
        ppw_fin_indent_get_qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poPupWindow.dismiss();
                poPupWindow = null;
                deleteShouCang(position);
                list.remove(position);
                notifyDataSetChanged();

            }
        });
    }

    private void deleteShouCang(int position) {
        RxRestClient.builder()
                .load(context)
                .url(StaticUrl.DELETE_SHOU_CANG)
                .params("scid", list.get(position).getScid())
                .build()
                .post()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>(context) {
                    @Override
                    public void onNext(String s) {
                        if (object.parseObject(s).getBoolean("success")) {
                            Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    class ViewHolder {
        private TextView mai_1_zhbd_itme_lx;
        private TextView mai_1_zhbd_itme_zh;
        private TextView zhbd_zh;
        private RelativeLayout mai_1_zhbd_item_sc;
    }
}

