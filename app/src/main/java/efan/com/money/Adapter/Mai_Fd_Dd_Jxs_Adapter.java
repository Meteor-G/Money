package efan.com.money.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Bean.NetDingDanBean;
import efan.com.money.R;
import efan.com.money.UIView.RoundImageView;
import efan.com.money.Util.TimeUtil.TimeUtil;
import efan.com.money.staticfunction.StaticUrl;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/13.
 */

public class Mai_Fd_Dd_Jxs_Adapter extends RecyclerView.Adapter<Mai_Fd_Dd_Jxs_Adapter.ViewHolder> {
    private Context context;
    private List<NetDingDanBean> list = new ArrayList<>();
    private OnItemClickListener mItemClickListener;

    public Mai_Fd_Dd_Jxs_Adapter(Context context) {
        this.context = context;

    }

    public void initData(List<NetDingDanBean> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mai_fd_dd_jxs, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mai_fd_dd_jxs_item_tupian.setBorderRadius(90);
        holder.mai_fd_dd_jxs_item_time.setText(TimeUtil.Long2Time(Long.valueOf(list.get(position).getDd_Time())));
        holder.mai_fd_dd_jxs_item_lx.setText(list.get(position).getTuiGuang());
        holder.mai_fd_dd_jxs_item_rwm.setText(list.get(position).getFd_MingCheng());
        holder.mai_fd_dd_jxs_item_yhm.setText(list.get(position).getName());
        holder.mai_fd_dd_jxs_item_zt.setText("进行时");
        Picasso.with(context)
                .load(StaticUrl.BASE_URL + list.get(position).getHead())
                .into(holder.mai_fd_dd_jxs_item_tupian);
//        holder.mai_fd_dd_jxs_item_tupian.setBackgroundResource(list.get(position).getMai_fd_dd_jxs_item_tupian());
        holder.item_fd_dd_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(holder.itemView, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mai_fd_dd_jxs_item_time;
        private RoundImageView mai_fd_dd_jxs_item_tupian;
        private TextView mai_fd_dd_jxs_item_lx;
        private TextView mai_fd_dd_jxs_item_rwm;
        private TextView mai_fd_dd_jxs_item_yhm;
        private TextView mai_fd_dd_jxs_item_zt;
        private LinearLayout item_fd_dd_ll;

        public ViewHolder(View itemView) {
            super(itemView);
            mai_fd_dd_jxs_item_time = (TextView) itemView.findViewById(R.id.mai_fd_dd_jxs_item_time);
            mai_fd_dd_jxs_item_lx = (TextView) itemView.findViewById(R.id.mai_fd_dd_jxs_item_lx);
            mai_fd_dd_jxs_item_rwm = (TextView) itemView.findViewById(R.id.mai_fd_dd_jxs_item_rwm);
            mai_fd_dd_jxs_item_yhm = (TextView) itemView.findViewById(R.id.mai_fd_dd_jxs_item_yhm);
            mai_fd_dd_jxs_item_zt = (TextView) itemView.findViewById(R.id.mai_fd_dd_jxs_item_zt);
            mai_fd_dd_jxs_item_tupian = (RoundImageView) itemView.findViewById(R.id.mai_fd_dd_jxs_item_tupian);
            item_fd_dd_ll = (LinearLayout) itemView.findViewById(R.id.item_fd_dd_jxs_ll);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }
}
