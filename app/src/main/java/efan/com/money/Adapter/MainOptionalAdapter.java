package efan.com.money.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import efan.com.money.Bean.NetFaDanBean;
import efan.com.money.R;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/14.
 */

public class MainOptionalAdapter extends RecyclerView.Adapter<MainOptionalAdapter.ViewHolder> {
    private Context mContext;
    private List<NetFaDanBean> mList;
    private OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public MainOptionalAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void initData(List<NetFaDanBean> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main_optional, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.item_main_optional_money.setText("￥" + mList.get(position).getFd_JiaGe());
        holder.item_main_optional_title.setText(mList.get(position).getFd_MingCheng());
        holder.item_main_optional_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(holder.item_main_optional_rl, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView item_main_optional_title;
        private TextView item_main_optional_money;
        private RelativeLayout item_main_optional_rl;

        public ViewHolder(View itemView) {
            super(itemView);
            item_main_optional_title = (TextView) itemView.findViewById(R.id.item_main_optional_title);
            item_main_optional_money = (TextView) itemView.findViewById(R.id.item_main_optional_money);
            item_main_optional_rl = (RelativeLayout) itemView.findViewById(R.id.item_main_optional_rl);
        }
    }
}
