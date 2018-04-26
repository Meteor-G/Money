package efan.com.money.Main.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import efan.com.money.Main.MainActivity;
import efan.com.money.R;
import efan.com.money.Util.net.rx.BaseSubscriber;
import efan.com.money.Util.net.rx.RxRestClient;
import efan.com.money.Util.storage.MainPreference;
import efan.com.money.staticfunction.StaticUrl;
import efan.com.money.staticfunction.StaticValue;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/18.
 */

public class Login extends AppCompatActivity {
    @BindView(R.id.dl_name)
    EditText dl_name;
    @BindView(R.id.dl_password)
    EditText dl_password;
    @BindView(R.id.dl_dl_bt)
    TextView dl_dl_bt;
    private JSONObject object = new JSONObject();
    private long exitTime = 0;

    @OnClick(R.id.dl_dl_bt)
    public void onClick(View view) {
        if (!dl_name.getText().toString().trim().isEmpty() && !dl_password.getText().toString().isEmpty()) {
            SignIn(dl_name.getText().toString().trim(), dl_password.getText().toString());
        } else {
            Toast.makeText(this, "请输入帐号密码", Toast.LENGTH_SHORT).show();
        }
    }

    private void SignIn(String name, String password) {
        RxRestClient.builder()
                .url(StaticUrl.SIGN_IN)
                .params("name", name)
                .params("password", password)
                .build()
                .post()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>(this) {
                    @Override
                    public void onNext(String s) {
                        if (object.parseObject(s).getString("success").equals("true")) {
                            SaveData(s);
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Login.this, "帐号或密码错误！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void SaveData(String s) {
        //保存name
        MainPreference.addCustomAppProfile("name", dl_name.getText().toString().trim());
        Log.i("保存数据name", dl_name.getText().toString().trim());
        //保存密码
        MainPreference.addCustomAppProfile("password", dl_password.getText().toString().trim());
        Log.i("保存数据password", dl_password.getText().toString().trim());
        //保存登录状态
        MainPreference.setAppFlag("check", true);
        //保存用户uid
        MainPreference.addCustomAppProfile(StaticValue.USER_ID, object.parseObject(object.parseObject(s).getString("data")).getString("uid"));
        Log.i("保存数据uid", object.parseObject(object.parseObject(s).getString("data")).getString("uid"));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.park_denglu);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出应用",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return true;
    }
}
