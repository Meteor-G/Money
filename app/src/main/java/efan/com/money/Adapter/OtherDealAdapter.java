package efan.com.money.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import efan.com.money.Bean.NetDingDanBean;
import efan.com.money.R;
import efan.com.money.Util.TimeUtil.TimeUtil;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/28.
 */

public class OtherDealAdapter extends RecyclerView.Adapter<OtherDealAdapter.ViewHolder> {

    private Context mContext;
    private List<NetDingDanBean> mList;

    public OtherDealAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void initData(List<NetDingDanBean> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_other_deal, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.item_other_deal_money.setText("+" + mList.get(position).getFd_JiaGe());
        holder.item_other_deal_name.setText(mList.get(position).getFd_MingCheng());
        holder.item_other_deal_time1.setText(TimeUtil.Long2Date(Long.valueOf(mList.get(position).getDd_Time())));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_other_deal_money;
        TextView item_other_deal_name;
        TextView item_other_deal_time1;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(mContext, itemView);
            item_other_deal_money = (TextView) itemView.findViewById(R.id.item_other_deal_money);
            item_other_deal_name = (TextView) itemView.findViewById(R.id.item_other_deal_name);
            item_other_deal_time1 = (TextView) itemView.findViewById(R.id.item_other_deal_time1);
        }
    }
}
