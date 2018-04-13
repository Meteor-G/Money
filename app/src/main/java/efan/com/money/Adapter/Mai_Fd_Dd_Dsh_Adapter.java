package efan.com.money.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import efan.com.money.Bean.Mai_Fd_Dd_Dsh_Bean;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/13.
 */

public class Mai_Fd_Dd_Dsh_Adapter extends RecyclerView.Adapter<Mai_Fd_Dd_Dsh_Adapter.ViewHolder> {
    private Context context;
    private List<Mai_Fd_Dd_Dsh_Bean> list = new ArrayList<>();
    private OnItemClickListener mItemClickListener;

    public Mai_Fd_Dd_Dsh_Adapter(Context context, List<Mai_Fd_Dd_Dsh_Bean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_, null);
//        ViewHolder holder = new ViewHolder(view);
//        return holder;
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.mai_fd_dd_dsh_item_time.setText(list.get(position).getMai_1_dd_shz_item_time());
//        holder.mai_fd_dd_dsh_item_lx.setText(list.get(position).getMai_1_dd_shz_item_lx());
//        holder.mai_fd_dd_dsh_item_rwm.setText(list.get(position).getMai_1_dd_shz_item_rwm());
//        holder.mai_fd_dd_dsh_item_yhm.setText(list.get(position).getMai_1_dd_shz_item_yhm());
//        holder.mai_fd_dd_dsh_item_zt.setText(list.get(position).getMai_1_dd_shz_item_zt());
//        holder.mai_fd_dd_dsh_jiage.setText(list.get(position).getMai_1_dd_shz_jiage());
//        holder.mai_fd_dd_dsh_iv_1.setBackgroundResource(list.get(position).getMai_1_dd_shz_iv_1());
//        holder.mai_fd_dd_dsh_iv_2.setBackgroundResource(list.get(position).getMai_1_dd_shz_iv_2());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mai_fd_dd_dsh_item_time;
        private ImageView mai_fd_dd_dsh_iv_1, mai_fd_dd_dsh_iv_2;
        private TextView mai_fd_dd_dsh_item_lx;
        private TextView mai_fd_dd_dsh_item_rwm;
        private TextView mai_fd_dd_dsh_item_yhm;
        private TextView mai_fd_dd_dsh_item_zt;
        private TextView mai_fd_dd_dsh_jiage;

        public ViewHolder(View itemView) {
            super(itemView);
//            mai_fd_dd_dsh_item_time = (TextView) itemView.findViewById(R.id.mai_fd_dd_dsh_item_time);
//            mai_fd_dd_dsh_item_lx = (TextView) itemView.findViewById(R.id.mai_fd_dd_dsh_item_lx);
//            mai_fd_dd_dsh_item_rwm = (TextView) itemView.findViewById(R.id.mai_fd_dd_dsh_item_rwm);
//            mai_fd_dd_dsh_item_yhm = (TextView) itemView.findViewById(R.id.mai_fd_dd_dsh_item_yhm);
//            mai_fd_dd_dsh_item_zt = (TextView) itemView.findViewById(R.id.mai_fd_dd_dsh_item_zt);
//            mai_fd_dd_dsh_jiage = (TextView) itemView.findViewById(R.id.mai_fd_dd_dsh_jiage);
//            mai_fd_dd_dsh_iv_1 = (ImageView) itemView.findViewById(R.id.mai_fd_dd_dsh_iv_1);
//            mai_fd_dd_dsh_iv_2 = (ImageView) itemView.findViewById(R.id.mai_fd_dd_dsh_iv_2);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }
}