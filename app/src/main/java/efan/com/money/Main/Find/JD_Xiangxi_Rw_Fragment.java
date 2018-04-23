package efan.com.money.Main.Find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import efan.com.money.Bean.NetFaDanBean;
import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/12.
 */

public class JD_Xiangxi_Rw_Fragment extends Fragment {
    private View view;
    private TextView jd_xx_rw_fra_sxsj;
    private TextView jd_xx_rw_fra_rwlx;
    private TextView jd_xx_rw_fra_rwnr;
    private TextView jd_xx_rw_fra_rwbz;
    private NetFaDanBean netFaDanBean;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.jd_xx_rw_fra, container, false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);// 先移除
        }
        initView();
        SetValue();
        return view;
    }

    private void SetValue() {
        if (getArguments() != null) {
            netFaDanBean = (NetFaDanBean) getArguments().getSerializable("bean");
            jd_xx_rw_fra_sxsj.setText(netFaDanBean.getZhangHao());
            jd_xx_rw_fra_rwlx.setText(netFaDanBean.getTuiGuang());
            jd_xx_rw_fra_rwnr.setText(netFaDanBean.getFd_NeiRong());
            jd_xx_rw_fra_rwbz.setText(netFaDanBean.getFd_BeiZhu());
        }
    }

    public static JD_Xiangxi_Rw_Fragment newInstence(NetFaDanBean netFaDanBean) {
        JD_Xiangxi_Rw_Fragment fragment = new JD_Xiangxi_Rw_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("bean", netFaDanBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void initView() {
        jd_xx_rw_fra_sxsj = (TextView) view.findViewById(R.id.jd_xx_rw_fra_sxsj);
        jd_xx_rw_fra_rwlx = (TextView) view.findViewById(R.id.jd_xx_rw_fra_rwlx);
        jd_xx_rw_fra_rwnr = (TextView) view.findViewById(R.id.jd_xx_rw_fra_rwnr);
        jd_xx_rw_fra_rwbz = (TextView) view.findViewById(R.id.jd_xx_rw_fra_rwbz);
    }
}
