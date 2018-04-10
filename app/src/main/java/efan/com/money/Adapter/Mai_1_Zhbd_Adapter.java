package efan.com.money.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Bean.Mai_1_Zhbd_Item_Bean;
import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/19.
 */

public class Mai_1_Zhbd_Adapter extends BaseAdapter {
    private Context context;
    private List<Mai_1_Zhbd_Item_Bean> list = new ArrayList<Mai_1_Zhbd_Item_Bean>();
    private LayoutInflater layoutInflater;

    public Mai_1_Zhbd_Adapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void init(List<Mai_1_Zhbd_Item_Bean> list) {
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
            view = View.inflate(context, R.layout.mai_1_zhbd_item, null);
            holder.mai_1_zhbd_itme_lx = (TextView) view.findViewById(R.id.mai_1_zhbd_itme_lx);
            holder.mai_1_zhbd_itme_zh = (TextView) view.findViewById(R.id.mai_1_zhbd_itme_zh);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.mai_1_zhbd_itme_lx.setText(list.get(position).getMai_1_zhbd_itme_lx());
        holder.mai_1_zhbd_itme_zh.setText(list.get(position).getMai_1_zhbd_itme_zh());

        return view;
    }

    class ViewHolder {
        private TextView mai_1_zhbd_itme_lx;
        private TextView mai_1_zhbd_itme_zh;
    }
}
