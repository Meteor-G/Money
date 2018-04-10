package efan.com.money.Main.Mine.Indent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/14.
 */

public class Mai_2_Dd_Qbrw extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.mai_2_dd_qbrw, container, false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        InitView();
        InitEvent();
        return view;
    }

    private void InitView() {
    }

    private void InitEvent() {
    }

}

