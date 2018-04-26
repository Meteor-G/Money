package efan.com.money.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Bean.NetDingDanBean;
import efan.com.money.R;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/13.
 */

public class Mai_Jd_Dd_Jyz_Adapter extends RecyclerView.Adapter<Mai_Jd_Dd_Jyz_Adapter.ViewHolder> {
    private Context context;
    private List<NetDingDanBean> list = new ArrayList<>();
    private OnItemClickListener mItemClickListener;

    public Mai_Jd_Dd_Jyz_Adapter(Context context) {
        this.context = context;

    }

    public void initData(List<NetDingDanBean> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mai_jd_dd_jyz, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mai_1_dd_jyz_item_time.setText(list.get(position).getDd_Time());
        holder.mai_1_dd_jyz_item_lx.setText(list.get(position).getTuiGuang());
        holder.mai_1_dd_jyz_item_rwm.setText(list.get(position).getFd_MingCheng());
        holder.mai_1_dd_jyz_item_yhm.setText(list.get(position).getFd_name());
        holder.mai_1_dd_jyz_item_zt.setText(list.get(position).getDd_ZhuangTai());
        Picasso.with(context)
                .load(list.get(position).getHead())
                .into(holder.mai_1_dd_jyz_item_tupian);
//        holder.mai_1_dd_jyz_item_tupian.setBackgroundResource(list.get(position).getHead());
        holder.mai_jyz_ll.setOnClickListener(new View.OnClickListener() {
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
        private TextView mai_1_dd_jyz_item_time;
        private ImageView mai_1_dd_jyz_item_tupian;
        private TextView mai_1_dd_jyz_item_lx;
        private TextView mai_1_dd_jyz_item_rwm;
        private TextView mai_1_dd_jyz_item_yhm;
        private TextView mai_1_dd_jyz_item_zt;
        private LinearLayout mai_jyz_ll;

        public ViewHolder(View itemView) {
            super(itemView);
            mai_1_dd_jyz_item_time = (TextView) itemView.findViewById(R.id.mai_1_dd_jyz_item_time);
            mai_1_dd_jyz_item_lx = (TextView) itemView.findViewById(R.id.mai_1_dd_jyz_item_lx);
            mai_1_dd_jyz_item_rwm = (TextView) itemView.findViewById(R.id.mai_1_dd_jyz_item_rwm);
            mai_1_dd_jyz_item_yhm = (TextView) itemView.findViewById(R.id.mai_1_dd_jyz_item_yhm);
            mai_1_dd_jyz_item_zt = (TextView) itemView.findViewById(R.id.mai_1_dd_jyz_item_zt);
            mai_1_dd_jyz_item_tupian = (ImageView) itemView.findViewById(R.id.mai_1_dd_jyz_item_tupian);
            mai_jyz_ll = (LinearLayout) itemView.findViewById(R.id.mai_jyz_ll);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }
}
