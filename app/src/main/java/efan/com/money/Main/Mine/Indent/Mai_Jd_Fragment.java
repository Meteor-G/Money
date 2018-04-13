package efan.com.money.Main.Mine.Indent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/10.
 */

public class Mai_Jd_Fragment extends Fragment implements View.OnClickListener {
    private View view;
    private RelativeLayout mai_1_fra_jyz_rl;
    private RelativeLayout mai_1_fra_shz_rl;
    private RelativeLayout mai_1_fra_jycg_rl;
    private RelativeLayout mai_1_fra_qbrw_rl;
    private RelativeLayout mai_1_fra_zhbd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.mai_1_fragment, container, false);
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
        mai_1_fra_jyz_rl.setOnClickListener(this);
        mai_1_fra_shz_rl.setOnClickListener(this);
        mai_1_fra_jycg_rl.setOnClickListener(this);
        mai_1_fra_qbrw_rl.setOnClickListener(this);
        mai_1_fra_zhbd.setOnClickListener(this);
    }

    private void InitView() {
        mai_1_fra_jyz_rl = (RelativeLayout) view.findViewById(R.id.mai_1_fra_jyz_rl);
        mai_1_fra_shz_rl = (RelativeLayout) view.findViewById(R.id.mai_1_fra_shz_rl);
        mai_1_fra_jycg_rl = (RelativeLayout) view.findViewById(R.id.mai_1_fra_jycg_rl);
        mai_1_fra_qbrw_rl = (RelativeLayout) view.findViewById(R.id.mai_1_fra_qbrw_rl);
        mai_1_fra_zhbd = (RelativeLayout) view.findViewById(R.id.mai_1_fra_zhbd);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mai_1_fra_jyz_rl:
                intent_class(0);
                break;
            case R.id.mai_1_fra_shz_rl:
                intent_class(1);
                break;
            case R.id.mai_1_fra_jycg_rl:
                intent_class(2);
                break;
            case R.id.mai_1_fra_qbrw_rl:
                intent_class(3);
                break;
            case R.id.mai_1_fra_zhbd:
                Intent intent = new Intent(getActivity(), Mai_Jd_Zhbd.class);
                startActivity(intent);
                break;
        }

    }

    private void intent_class(int i) {
        String s = String.valueOf(i);
        Intent intent = new Intent(getActivity(), Mai_Jd_Digdan.class);
        Bundle bundle = new Bundle();
        bundle.putString("mai1", s);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
