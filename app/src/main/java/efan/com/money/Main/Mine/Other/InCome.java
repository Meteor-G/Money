package efan.com.money.Main.Mine.Other;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import efan.com.money.Main.BaseActivity;
import efan.com.money.R;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/27.
 */

public class InCome extends BaseActivity {
    @BindView(R.id.other_income_fanhui)
    ImageView other_income_fanhui;

    @OnClick({R.id.other_income_fanhui})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.other_income_fanhui:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_income);
        ButterKnife.bind(this);
    }
}
