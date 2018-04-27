package efan.com.money.Main.Mine.Indent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import efan.com.money.Main.Mine.Other.Identity;
import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/10.
 */

public class Mai_Fd_Fragment extends Fragment implements View.OnClickListener {
    private View view;
    private RelativeLayout mai_2_fra_rwyl_rl;
    private RelativeLayout mai_2_fra_jxs_rl;
    private RelativeLayout mai_2_fra_dsh_rl;
    private RelativeLayout mai_2_fra_jycg_rl;
    private RelativeLayout mai_2_fra_qbrw_rl;
    private RelativeLayout mai_2_fra_smrz_rl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.mai_2_faragment, container, false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);// 先移除
        }
        InitView();
        InitEvent();
        return view;
    }

    private void InitEvent() {
        mai_2_fra_rwyl_rl.setOnClickListener(this);
        mai_2_fra_jxs_rl.setOnClickListener(this);
        mai_2_fra_dsh_rl.setOnClickListener(this);
        mai_2_fra_jycg_rl.setOnClickListener(this);
        mai_2_fra_qbrw_rl.setOnClickListener(this);
        mai_2_fra_smrz_rl.setOnClickListener(this);
    }

    private void InitView() {
        mai_2_fra_rwyl_rl = (RelativeLayout) view.findViewById(R.id.mai_2_fra_rwyl_rl);
        mai_2_fra_jxs_rl = (RelativeLayout) view.findViewById(R.id.mai_2_fra_jxs_rl);
        mai_2_fra_dsh_rl = (RelativeLayout) view.findViewById(R.id.mai_2_fra_dsh_rl);
        mai_2_fra_jycg_rl = (RelativeLayout) view.findViewById(R.id.mai_2_fra_jycg_rl);
        mai_2_fra_qbrw_rl = (RelativeLayout) view.findViewById(R.id.mai_2_fra_qbrw_rl);
        mai_2_fra_smrz_rl = view.findViewById(R.id.mai_2_fra_smrz_rl);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mai_2_fra_rwyl_rl:
                intent_class(0);
                break;
            case R.id.mai_2_fra_jxs_rl:
                intent_class(1);
                break;
            case R.id.mai_2_fra_dsh_rl:
                intent_class(2);
                break;
            case R.id.mai_2_fra_jycg_rl:
                intent_class(3);
                break;
            case R.id.mai_2_fra_qbrw_rl:
                intent_class(4);
                break;
            case R.id.mai_2_fra_smrz_rl:
                Intent intent = new Intent(getActivity(), Identity.class);
                getActivity().startActivity(intent);
                break;
        }

    }

    private void intent_class(int i) {
        String s = String.valueOf(i);
        Intent intent = new Intent(getActivity(), Mai_Fd_Dingdan.class);
        Bundle bundle = new Bundle();
        bundle.putString("mai2", s);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
