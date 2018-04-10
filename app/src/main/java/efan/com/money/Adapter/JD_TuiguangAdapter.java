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

import efan.com.money.Bean.JD_TuiguangBean;
import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/11.
 */

public class JD_TuiguangAdapter extends BaseAdapter {
    private Context context;
    private List<JD_TuiguangBean> list = new ArrayList<JD_TuiguangBean>();
    private LayoutInflater layoutInflater;

    public JD_TuiguangAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void init(List<JD_TuiguangBean> list) {
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
            view = View.inflate(context, R.layout.jd_tg_item, null);
            holder.pub_tg_item_iv = (ImageView) view.findViewById(R.id.pub_tg_item_iv);
            holder.pub_tg_item_tv = (TextView) view.findViewById(R.id.pub_tg_item_tv);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.pub_tg_item_iv.setBackgroundResource(list.get(position).pub_tg_item_iv);
        holder.pub_tg_item_tv.setText(list.get(position).pub_tg_item_tv);
        return view;
    }

    class ViewHolder {
        private ImageView pub_tg_item_iv;
        private TextView pub_tg_item_tv;
    }
}
