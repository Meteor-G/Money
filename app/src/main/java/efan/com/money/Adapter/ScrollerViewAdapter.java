package efan.com.money.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Bean.PubScrollerBean;
import efan.com.money.R;
import efan.com.money.UIView.LimitScrollerView;

import static android.R.attr.data;

/**
 * 作者： ZlyjD.
 * 时间：2018/3/26.
 */

public class ScrollerViewAdapter implements LimitScrollerView.LimitScrollAdapter {
    private Context mContext;
    private List<PubScrollerBean> mList = new ArrayList<>();
    private LayoutInflater inflater;

    public ScrollerViewAdapter(Context context) {
        this.mContext = context;

        inflater.from(mContext);
    }

    public void setdata(List<PubScrollerBean> mlist) {
        this.mList = mlist;
    }


    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public View getView(int index) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pub_fra_scroller_item, null, false);
        TextView Fub_scroller_money_tv = (TextView) view.findViewById(R.id.Fub_scroller_money_tv);
        ImageView Fub_scroller_iv = (ImageView) view.findViewById(R.id.Fub_scroller_iv);
        TextView Fub_scroller_name_tv = (TextView) view.findViewById(R.id.Fub_scroller_name_tv);
        TextView Fub_scroller_time_tv = (TextView) view.findViewById(R.id.Fub_scroller_time_tv);
        TextView Fub_scroller_title_tv = (TextView) view.findViewById(R.id.Fub_scroller_title_tv);

        String money_data = mList.get(index).getFub_scroller_money_tv();
        int iv = mList.get(index).getFub_scroller_iv();
        String name_data = mList.get(index).getFub_scroller_name_tv();
        String time_data = mList.get(index).getFub_scroller_time_tv();
        String title_data = mList.get(index).getFub_scroller_title_tv();

        Fub_scroller_money_tv.setText(money_data);
        Fub_scroller_iv.setBackgroundResource(iv);
        Fub_scroller_name_tv.setText(name_data);
        Fub_scroller_time_tv.setText(time_data);
        Fub_scroller_title_tv.setText(title_data);
        view.setTag(data);
        return view;
    }
}
