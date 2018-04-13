package efan.com.money.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Bean.Mai_1_Dd_Qbrw_Bean;
import efan.com.money.R;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/13.
 */

public class Mai_Jd_Dd_Qbrw_Adapter extends RecyclerView.Adapter<Mai_Jd_Dd_Qbrw_Adapter.ViewHolder> {
    private Context context;
    private List<Mai_1_Dd_Qbrw_Bean> list = new ArrayList<>();
    private OnItemClickListener mItemClickListener;

    public Mai_Jd_Dd_Qbrw_Adapter(Context context, List<Mai_1_Dd_Qbrw_Bean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mai_1_dd_qbrw_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mai_1_dd_qbrw_item_time.setText(list.get(position).getMai_1_dd_qbrw_item_time());
        holder.mai_1_dd_qbrw_item_lx.setText(list.get(position).getMai_1_dd_qbrw_item_lx());
        holder.mai_1_dd_qbrw_item_rwm.setText(list.get(position).getMai_1_dd_qbrw_item_rwm());
        holder.mai_1_dd_qbrw_item_yhm.setText(list.get(position).getMai_1_dd_qbrw_item_yhm());
        holder.mai_1_dd_qbrw_item_zt.setText(list.get(position).getMai_1_dd_qbrw_item_zt());
        holder.mai_1_dd_qbrw_item_tupian.setBackgroundResource(list.get(position).getMai_1_dd_qbrw_item_tupian());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mai_1_dd_qbrw_item_time;
        private ImageView mai_1_dd_qbrw_item_tupian;
        private TextView mai_1_dd_qbrw_item_lx;
        private TextView mai_1_dd_qbrw_item_rwm;
        private TextView mai_1_dd_qbrw_item_yhm;
        private TextView mai_1_dd_qbrw_item_zt;

        public ViewHolder(View itemView) {
            super(itemView);
            mai_1_dd_qbrw_item_time = (TextView) itemView.findViewById(R.id.mai_1_dd_qbrw_item_time);
            mai_1_dd_qbrw_item_lx = (TextView) itemView.findViewById(R.id.mai_1_dd_qbrw_item_lx);
            mai_1_dd_qbrw_item_rwm = (TextView) itemView.findViewById(R.id.mai_1_dd_qbrw_item_rwm);
            mai_1_dd_qbrw_item_yhm = (TextView) itemView.findViewById(R.id.mai_1_dd_qbrw_item_yhm);
            mai_1_dd_qbrw_item_zt = (TextView) itemView.findViewById(R.id.mai_1_dd_qbrw_item_zt);
            mai_1_dd_qbrw_item_tupian = (ImageView) itemView.findViewById(R.id.mai_1_dd_qbrw_item_tupian);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }
}

