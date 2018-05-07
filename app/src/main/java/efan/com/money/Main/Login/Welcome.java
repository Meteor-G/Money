package efan.com.money.Main.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import efan.com.money.Main.BaseActivity;
import efan.com.money.Main.MainActivity;
import efan.com.money.R;
import efan.com.money.Util.storage.MainPreference;
import efan.com.money.Util.timer.BaseTimerTask;
import efan.com.money.Util.timer.ITimerListener;

/**
 * Created by Administrator on 2017/9/14.
 */

public class Welcome extends BaseActivity implements ITimerListener {
    @BindView(R.id.welcome_tv)
    TextView welcome_tv;
    private int mCount = 3;
    private Timer mTimer = null;

    @OnClick(R.id.welcome_tv)
    public void onClick(View v) {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        ButterKnife.bind(this);
        initTime();
    }

    private void initTime() {
        mTimer = new Timer();
        BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public void onTimer() {
        Welcome.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (welcome_tv != null) {
                    welcome_tv.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }

    private void checkIsShowScroll() {
        if (MainPreference.getAppFlag("check")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Welcome.this.finish();
        } else {
            Intent intent1 = new Intent(this, Login.class);
            startActivity(intent1);
            Welcome.this.finish();
        }
    }

}
