package efan.com.money.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Bean.NetTuiGuangBean;
import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/12.
 */

public class FD_LeixingAdapter extends BaseAdapter {

    private Context context;
    private List<NetTuiGuangBean> list = new ArrayList<NetTuiGuangBean>();
    private LayoutInflater layoutInflater;

    public FD_LeixingAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void init(List<NetTuiGuangBean> list) {
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
            view = View.inflate(context, R.layout.fd_leixing_item, null);
            holder.publeixing_item_tv = (TextView) view.findViewById(R.id.publeixing_item_tv);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.publeixing_item_tv.setText(list.get(position).getTg_leixing());
        return view;
    }

    class ViewHolder {
        private TextView publeixing_item_tv;
    }
}
