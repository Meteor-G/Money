package efan.com.money.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Bean.NetDingDanBean;
import efan.com.money.R;
import efan.com.money.Util.TimeUtil.TimeUtil;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/13.
 */

public class Mai_Fd_Dd_Rwyl_Adapter extends RecyclerView.Adapter<Mai_Fd_Dd_Rwyl_Adapter.ViewHolder> {
    private Context context;
    private List<NetDingDanBean> list = new ArrayList<>();
    private OnItemClickListener mItemClickListener;

    public Mai_Fd_Dd_Rwyl_Adapter(Context context) {
        this.context = context;

    }

    public void initData(List<NetDingDanBean> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mai_fd_dd_rwyl, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mai_fd_dd_rwyl_item_time.setText(TimeUtil.Long2Time(Long.valueOf(list.get(position).getFd_Time())));
        holder.mai_fd_dd_rwyl_item_lx.setText("[" + list.get(position).getTuiGuang() + "]");
        holder.mai_fd_dd_rwyl_item_rwm.setText(list.get(position).getFd_MingCheng());
        holder.item_mai_fd_rwyl_zongshu.setText(list.get(position).getFd_ZongShu() + "单");
        holder.item_mai_fd_rwyl_shengyu.setText("10单");
        holder.item_mai_fd_dd_rwyl_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClickListener.onItemClick(holder.item_mai_fd_dd_rwyl_ll, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mai_fd_dd_rwyl_item_time;
        private TextView mai_fd_dd_rwyl_item_lx;
        private TextView mai_fd_dd_rwyl_item_rwm;
        private TextView item_mai_fd_rwyl_shengyu;
        private TextView item_mai_fd_rwyl_zongshu;
        private LinearLayout item_mai_fd_dd_rwyl_ll;

        public ViewHolder(View itemView) {
            super(itemView);
            mai_fd_dd_rwyl_item_time = (TextView) itemView.findViewById(R.id.mai_fd_dd_rwyl_item_time);
            mai_fd_dd_rwyl_item_lx = (TextView) itemView.findViewById(R.id.mai_fd_dd_rwyl_item_lx);
            mai_fd_dd_rwyl_item_rwm = (TextView) itemView.findViewById(R.id.mai_fd_dd_rwyl_item_rwm);
            item_mai_fd_rwyl_shengyu = (TextView) itemView.findViewById(R.id.item_mai_fd_rwyl_shengyu);
            item_mai_fd_rwyl_zongshu = (TextView) itemView.findViewById(R.id.item_mai_fd_rwyl_zongshu);
            item_mai_fd_dd_rwyl_ll = itemView.findViewById(R.id.item_mai_fd_dd_rwyl_ll);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }
}


