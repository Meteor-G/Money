package efan.com.money.Main.Mine.Other;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import efan.com.money.Main.Login.Login;
import efan.com.money.R;
import efan.com.money.Util.storage.MainPreference;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/18.
 */

public class Setting extends AppCompatActivity {
    @BindView(R.id.me_sz_tcdl)
    TextView me_sz_tcdl;

    @OnClick(R.id.me_sz_tcdl)
    public void onClick(View view) {
        clearData();
    }

    private void clearData() {
        MainPreference.clearAppPreferences();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        Setting.this.finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_setting);
        ButterKnife.bind(this);
    }
}
