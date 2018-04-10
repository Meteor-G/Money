package efan.com.money.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import efan.com.money.Bean.JD_MainBean;
import efan.com.money.R;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/10.
 */

public class JD_Main_Adapter extends RecyclerView.Adapter<JD_Main_Adapter.ViewHolder> {
    private Context mContext;
    private List<JD_MainBean> mList;
    private OnItemClickListener mItemClickListener;
    LayoutInflater inflater;

    public JD_Main_Adapter(Context mContext) {
        this.mContext = mContext;
        inflater.from(mContext);

    }

    public void initData(List<JD_MainBean> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.jd_main_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.jd_main_item_iv.setBackgroundResource(mList.get(position).getJd_main_item_iv());
        holder.jd_main_item_lx_tv.setText(mList.get(position).getJd_main_item_lx_tv());
        holder.jd_main_item_title_tv.setText(mList.get(position).getJd_main_item_title_tv());
        holder.jd_main_item_mjxy_tv.setText(mList.get(position).getJd_main_item_mjxy_tv());
        holder.jd_main_item_cj_tv.setText(mList.get(position).getJd_main_item_cj_tv());
        holder.jd_main_item_qian.setText(mList.get(position).getJd_main_item_qian());
        if (mItemClickListener != null) {
            holder.jd_main_item_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView jd_main_item_iv;
        private TextView jd_main_item_lx_tv, jd_main_item_title_tv, jd_main_item_mjxy_tv,
                jd_main_item_cj_tv, jd_main_item_qian;
        private LinearLayout jd_main_item_ll;

        public ViewHolder(View itemView) {
            super(itemView);
            jd_main_item_iv = (ImageView) itemView.findViewById(R.id.jd_main_item_iv);
            jd_main_item_lx_tv = (TextView) itemView.findViewById(R.id.jd_main_item_lx_tv);
            jd_main_item_title_tv = (TextView) itemView.findViewById(R.id.jd_main_item_title_tv);
            jd_main_item_mjxy_tv = (TextView) itemView.findViewById(R.id.jd_main_item_mjxy_tv);
            jd_main_item_cj_tv = (TextView) itemView.findViewById(R.id.jd_main_item_cj_tv);
            jd_main_item_qian = (TextView) itemView.findViewById(R.id.jd_main_item_qian);
            jd_main_item_ll= (LinearLayout) itemView.findViewById(R.id.jd_main_item_ll);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }
}
