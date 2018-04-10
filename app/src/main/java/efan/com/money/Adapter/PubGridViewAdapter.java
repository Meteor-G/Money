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

import efan.com.money.Bean.PubGridViewBean;
import efan.com.money.R;

/**
 * Created by Administrator on 2017/10/27.
 */

public class PubGridViewAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<PubGridViewBean> list = new ArrayList<>();

    public PubGridViewAdapter(Context context) {
        this.context = context;
        layoutInflater = layoutInflater.from(context);
    }

    public void init(List<PubGridViewBean> list) {
        this.list = list;
    }

    ;

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
            convertView = layoutInflater.inflate(R.layout.pub_gv_item, null);
            holder.pub_grid_item_iv = (ImageView) convertView.findViewById(R.id.pub_gv_item_iv);
            holder.pub_grid_item_tv = (TextView) convertView.findViewById(R.id.pub_gv_item_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    class ViewHolder {
        private ImageView pub_grid_item_iv;
        private TextView pub_grid_item_tv;
    }
}
