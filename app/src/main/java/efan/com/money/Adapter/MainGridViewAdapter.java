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

import efan.com.money.Bean.MainGridViewBean;
import efan.com.money.R;

/**
 * Created by Administrator on 2017/10/27.
 */

public class MainGridViewAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<MainGridViewBean> list = new ArrayList<>();

    public MainGridViewAdapter(Context context) {
        this.context = context;
        layoutInflater = layoutInflater.from(context);
    }

    public void init(List<MainGridViewBean> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.main_gv_item, null);
            holder.main_grid_item_iv = (ImageView) convertView.findViewById(R.id.main_gv_item_iv);
            holder.main_grid_item_tv = (TextView) convertView.findViewById(R.id.main_gv_item_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.main_grid_item_iv.setBackgroundResource(list.get(position).getMain_grid_item_iv());
            holder.main_grid_item_tv.setText(list.get(position).getMain_grid_item_tv());
        }
        return convertView;
    }

    class ViewHolder {
        private ImageView main_grid_item_iv;
        private TextView main_grid_item_tv;
    }
}
