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

import efan.com.money.Bean.Mai_1_Dd_Jyz_Bean;
import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/14.
 */

public class Mai_1_Dd_Jyz_Adapter extends BaseAdapter {
    private Context context;
    private List<Mai_1_Dd_Jyz_Bean> list = new ArrayList<Mai_1_Dd_Jyz_Bean>();
    private LayoutInflater layoutInflater;

    public Mai_1_Dd_Jyz_Adapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void init(List<Mai_1_Dd_Jyz_Bean> list) {
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
            view = View.inflate(context, R.layout.mai_1_dd_jyz_item, null);
            holder.mai_1_dd_jyz_item_time = (TextView) view.findViewById(R.id.mai_1_dd_jyz_item_time);
            holder.mai_1_dd_jyz_item_lx = (TextView) view.findViewById(R.id.mai_1_dd_jyz_item_lx);
            holder.mai_1_dd_jyz_item_rwm = (TextView) view.findViewById(R.id.mai_1_dd_jyz_item_rwm);
            holder.mai_1_dd_jyz_item_yhm = (TextView) view.findViewById(R.id.mai_1_dd_jyz_item_yhm);
            holder.mai_1_dd_jyz_item_zt = (TextView) view.findViewById(R.id.mai_1_dd_jyz_item_zt);
            holder.mai_1_dd_jyz_item_tupian = (ImageView) view.findViewById(R.id.mai_1_dd_jyz_item_tupian);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.mai_1_dd_jyz_item_time.setText(list.get(position).getMai_1_dd_jyz_item_time());
        holder.mai_1_dd_jyz_item_lx.setText(list.get(position).getMai_1_dd_jyz_item_lx());
        holder.mai_1_dd_jyz_item_rwm.setText(list.get(position).getMai_1_dd_jyz_item_rwm());
        holder.mai_1_dd_jyz_item_yhm.setText(list.get(position).getMai_1_dd_jyz_item_yhm());
        holder.mai_1_dd_jyz_item_zt.setText(list.get(position).getMai_1_dd_jyz_item_zt());
        holder.mai_1_dd_jyz_item_tupian.setBackgroundResource(list.get(position).getMai_1_dd_jyz_item_tupian());
        return view;
    }

    class ViewHolder {
        private TextView mai_1_dd_jyz_item_time;
        private ImageView mai_1_dd_jyz_item_tupian;
        private TextView mai_1_dd_jyz_item_lx;
        private TextView mai_1_dd_jyz_item_rwm;
        private TextView mai_1_dd_jyz_item_yhm;
        private TextView mai_1_dd_jyz_item_zt;
    }
}
