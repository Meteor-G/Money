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

import efan.com.money.Bean.MainListViewBean;
import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/11.
 */

public class MainListViewAdapter extends BaseAdapter {
    private Context context;
    private List<MainListViewBean> mlist = new ArrayList<MainListViewBean>();
    private LayoutInflater inflater;

    public void init(List<MainListViewBean> list) {
        this.mlist = list;
    }

    public MainListViewAdapter(Context context) {
        this.context = context;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mlist.size();
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
            view = inflater.inflate(R.layout.main_lv_item, null);
            holder.main_item_iv = (ImageView) view.findViewById(R.id.main_item_iv);
            holder.main_item_title = (TextView) view.findViewById(R.id.main_item_title);
            holder.main_item_text1 = (TextView) view.findViewById(R.id.main_item_text1);
            holder.main_item_text2 = (TextView) view.findViewById(R.id.main_item_text2);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.main_item_iv.setBackgroundResource(mlist.get(position).main_item_iv);
        holder.main_item_title.setText(mlist.get(position).main_item_title);
        holder.main_item_text1.setText(mlist.get(position).main_item_text1);
        holder.main_item_text2.setText(mlist.get(position).main_item_text2);
        return view;
    }

    class ViewHolder {
        private ImageView main_item_iv;
        private TextView main_item_title;
        private TextView main_item_text1;
        private TextView main_item_text2;
    }
}
