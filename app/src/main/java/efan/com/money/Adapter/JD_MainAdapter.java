package efan.com.money.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Bean.JD_MainBean;
import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/12.
 */

public class JD_MainAdapter extends BaseAdapter {
    private Context context;
    private List<JD_MainBean> list = new ArrayList<JD_MainBean>();
    private LayoutInflater layoutInflater;

    public JD_MainAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void init(List<JD_MainBean> list) {
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
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.jd_main_item, null);
            holder.jd_main_item_iv = (ImageView) view.findViewById(R.id.jd_main_item_iv);
            holder.jd_main_item_lx_tv = (TextView) view.findViewById(R.id.jd_main_item_lx_tv);
            holder.jd_main_item_title_tv = (TextView) view.findViewById(R.id.jd_main_item_title_tv);
            holder.jd_main_item_mjxy_tv = (TextView) view.findViewById(R.id.jd_main_item_mjxy_tv);
            holder.jd_main_item_cj_tv = (TextView) view.findViewById(R.id.jd_main_item_cj_tv);
            holder.jd_main_item_qian = (TextView) view.findViewById(R.id.jd_main_item_qian);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.jd_main_item_iv.setBackgroundResource(list.get(position).jd_main_item_iv);
        holder.jd_main_item_lx_tv.setText(list.get(position).jd_main_item_lx_tv);
        holder.jd_main_item_title_tv.setText(list.get(position).jd_main_item_title_tv);
        holder.jd_main_item_mjxy_tv.setText(list.get(position).jd_main_item_mjxy_tv);
        holder.jd_main_item_cj_tv.setText(list.get(position).jd_main_item_cj_tv);
        holder.jd_main_item_qian.setText(list.get(position).jd_main_item_qian);
        return view;
    }

    class ViewHolder {
        private ImageView jd_main_item_iv;
        private TextView jd_main_item_lx_tv, jd_main_item_title_tv, jd_main_item_mjxy_tv, jd_main_item_cj_tv, jd_main_item_qian;
    }
}
