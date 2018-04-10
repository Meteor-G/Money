package efan.com.money.Main.Mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import efan.com.money.Main.Mine.Indent.Mai_1_Fragment;
import efan.com.money.Main.Mine.Indent.Mai_2_Fragment;
import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/10.
 */

public class MeFragment extends Fragment implements View.OnClickListener{
    private RelativeLayout main_mj1_rl;
    private RelativeLayout main_mj2_rl;

    private TextView main_mj1_tv;
    private TextView main_mj2_tv;

    private Fragment Mai_1_Fragment;
    private Fragment Mai_2_Fragment;

    private ImageView main_mj1_iv;
    private ImageView main_mj2_iv;

    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.mefragment, container, false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);// 先移除
        }
        InitView();
        InitEvent();
        setSelect(0);
        return view;
    }

    private void InitEvent() {
        main_mj1_rl.setOnClickListener(this);
        main_mj2_rl.setOnClickListener(this);
    }
    private void InitView() {
        main_mj1_rl=(RelativeLayout)view.findViewById(R.id.main_mj1_rl);
        main_mj2_rl=(RelativeLayout)view.findViewById(R.id.main_mj2_rl);
        main_mj1_tv=(TextView)view.findViewById(R.id.main_mj1_tv);
        main_mj2_tv=(TextView)view.findViewById(R.id.main_mj2_tv);
        main_mj1_iv=(ImageView) view.findViewById(R.id.main_mj1_iv);
        main_mj2_iv=(ImageView) view.findViewById(R.id.main_mj2_iv);
    }

    @Override
    public void onClick(View v) {
        resetImgs();
        switch (v.getId()) {
            case R.id.main_mj1_rl:
                setSelect(0);
                break;
            case R.id.main_mj2_rl:
                setSelect(1);
                break;
        }
    }
    private void setSelect(int i) {
        // 获取Fragement管理者
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        // 隐藏所有fragment
        hideFragment(transaction);
        // 把图片变亮
        // 设置内容区域
        switch (i) {
            case 0:
                if (Mai_1_Fragment == null) {
                    Mai_1_Fragment = new Mai_1_Fragment();
                    transaction.add(R.id.main_frame, Mai_1_Fragment);
                } else {
                    transaction.show(Mai_1_Fragment);
                }
                main_mj1_tv.setTextColor(0xff6699ff);
                main_mj1_iv.setVisibility(View.VISIBLE);
                break;
            case 1:
                if (Mai_2_Fragment == null) {
                    Mai_2_Fragment = new Mai_2_Fragment();
                    transaction.add(R.id.main_frame, Mai_2_Fragment);
                } else {
                    transaction.show(Mai_2_Fragment);
                }
                main_mj2_tv.setTextColor(0xff6699ff);
               main_mj2_iv.setVisibility(View.VISIBLE);
                break;
        }
        transaction.commit();

    }

    private void hideFragment(FragmentTransaction transaction) {
        // TODO Auto-generated method stub
        if (Mai_1_Fragment != null) {
            transaction.hide(Mai_1_Fragment);
        }
        if (Mai_2_Fragment != null) {
            transaction.hide(Mai_2_Fragment);
        }

    }

    private void resetImgs() {
        main_mj1_tv.setTextColor(0xffffffff);
        main_mj2_tv.setTextColor(0xffffffff);
        main_mj1_iv.setVisibility(View.GONE);
        main_mj2_iv.setVisibility(View.GONE);
    }
}
