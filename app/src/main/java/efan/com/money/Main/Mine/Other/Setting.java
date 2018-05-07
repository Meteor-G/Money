package efan.com.money.Main.Mine.Other;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import efan.com.money.Main.Login.Login;
import efan.com.money.R;
import efan.com.money.Util.callback.CallbackManager;
import efan.com.money.Util.callback.CallbackType;
import efan.com.money.Util.storage.MainPreference;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/18.
 */

public class Setting extends AppCompatActivity {
    @BindView(R.id.me_sz_tcdl)
    TextView me_sz_tcdl;
    @BindView(R.id.other_setting_fanhui)
    ImageView other_setting_fanhui;
    @BindView(R.id.other_setting_switch)
    SwitchCompat other_setting_switch;

    @OnClick({R.id.me_sz_tcdl, R.id.other_setting_fanhui})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.me_sz_tcdl:
                clearData();
                break;
            case R.id.other_setting_fanhui:
                finish();
                break;
        }
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
        other_setting_switch.setChecked(true);
        other_setting_switch.setOnCheckedChangeListener(new switchLinster());
    }

    class switchLinster implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if (isChecked) {
                CallbackManager.getInstence().getCallback(CallbackType.TAG_OPEN_PUSH).executeCallback(null);
                Toast.makeText(Setting.this, "打开推送", Toast.LENGTH_SHORT).show();
            } else {
                CallbackManager.getInstence().getCallback(CallbackType.TAG_STOP_PUSH).executeCallback(null);
                Toast.makeText(Setting.this, "关闭推送", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
