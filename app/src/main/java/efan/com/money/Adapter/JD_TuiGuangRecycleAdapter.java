package efan.com.money.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import efan.com.money.Bean.NetZhangHao;
import efan.com.money.R;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/17.
 */

public class JD_TuiGuangRecycleAdapter extends RecyclerView.Adapter<JD_TuiGuangRecycleAdapter.ViewHolder> {

    private Context mContext;
    private List<NetZhangHao> mList;
    private OnItemClickListener mItemClickListener;

    public JD_TuiGuangRecycleAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void initData(List<NetZhangHao> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.jd_tg_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Picasso.with(mContext)
                .load(mList.get(position).getZh_leixing_iv())
                .error(R.mipmap.ic_launcher)
                .into(holder.pub_tg_item_iv);
        holder.pub_tg_item_tv.setText(mList.get(position).getZh_leixing());
        holder.jd_tg_item_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(holder.jd_tg_item_rl, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView pub_tg_item_iv;
        private TextView pub_tg_item_tv;
        private RelativeLayout jd_tg_item_rl;

        public ViewHolder(View itemView) {
            super(itemView);
            pub_tg_item_iv = (ImageView) itemView.findViewById(R.id.pub_tg_item_iv);
            pub_tg_item_tv = (TextView) itemView.findViewById(R.id.pub_tg_item_tv);
            jd_tg_item_rl = (RelativeLayout) itemView.findViewById(R.id.jd_tg_item_rl);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }
}
