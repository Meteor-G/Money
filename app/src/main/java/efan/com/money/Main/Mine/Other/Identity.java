package efan.com.money.Main.Mine.Other;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import efan.com.money.Main.BaseActivity;
import efan.com.money.R;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/27.
 */

public class Identity extends BaseActivity {
    @BindView(R.id.tvNameValue)
    TextView tvNameValue;
    @BindView(R.id.tvIdValue)
    TextView tvIdValue;
    @BindView(R.id.other_identity_fanhui)
    ImageView other_identity_fanhui;

    @OnClick(R.id.other_identity_fanhui)
    public void onClick(View view) {
        finish();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_identity);
        ButterKnife.bind(this);
    }
}
