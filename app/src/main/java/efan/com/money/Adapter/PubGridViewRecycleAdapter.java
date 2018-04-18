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
import efan.com.money.staticfunction.StaticUrl;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/18.
 */

public class PubGridViewRecycleAdapter extends RecyclerView.Adapter<PubGridViewRecycleAdapter.ViewHolder> {

    private Context mContext;
    private List<NetZhangHao> mList;
    private OnItemClickListener mItemClickListener;

    public PubGridViewRecycleAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void initData(List<NetZhangHao> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pub_gv_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Picasso.with(mContext)
                .load(StaticUrl.BASE_URL + mList.get(position).getZh_leixing_iv())
                .error(R.mipmap.ic_launcher)
                .into(holder.pub_grid_item_iv);
        holder.pub_grid_item_tv.setText(mList.get(position).getZh_leixing());
        holder.pubfra_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(holder.pubfra_rl, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView pub_grid_item_iv;
        private TextView pub_grid_item_tv;
        private RelativeLayout pubfra_rl;

        public ViewHolder(View itemView) {
            super(itemView);
            pub_grid_item_iv = (ImageView) itemView.findViewById(R.id.pub_gv_item_iv);
            pub_grid_item_tv = (TextView) itemView.findViewById(R.id.pub_gv_item_tv);
            pubfra_rl = (RelativeLayout) itemView.findViewById(R.id.pubfra_rl);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }
}
