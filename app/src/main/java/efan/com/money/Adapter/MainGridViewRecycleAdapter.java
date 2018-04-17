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

import efan.com.money.Bean.NetTuiGuangBean;
import efan.com.money.R;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/17.
 */

public class MainGridViewRecycleAdapter extends RecyclerView.Adapter<MainGridViewRecycleAdapter.ViewHolder> {

    private Context mContext;
    private List<NetTuiGuangBean> mList;
    private OnItemClickListener mItemClickListener;

    public MainGridViewRecycleAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void initData(List<NetTuiGuangBean> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.main_gv_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Picasso.with(mContext)
                .load(mList.get(position).getTg_leixing_iv())
                .error(R.mipmap.ic_launcher)
                .into(holder.main_grid_item_iv);
        holder.main_grid_item_tv.setText(mList.get(position).getTg_leixing());
        holder.main_frame_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(holder.main_frame_rl, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView main_grid_item_iv;
        private TextView main_grid_item_tv;
        private RelativeLayout main_frame_rl;

        public ViewHolder(View itemView) {
            super(itemView);
            main_grid_item_iv = (ImageView) itemView.findViewById(R.id.main_gv_item_iv);
            main_grid_item_tv = (TextView) itemView.findViewById(R.id.main_gv_item_tv);
            main_frame_rl = (RelativeLayout) itemView.findViewById(R.id.main_frame_rl);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }
}
