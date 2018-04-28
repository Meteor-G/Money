package efan.com.money.Main.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import efan.com.money.Main.BaseActivity;
import efan.com.money.R;
import efan.com.money.Util.net.rx.BaseSubscriber;
import efan.com.money.Util.net.rx.RxRestClient;
import efan.com.money.staticfunction.StaticUrl;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/28.
 */

public class SignUp extends BaseActivity {
    @BindView(R.id.up_name)
    EditText up_name;
    @BindView(R.id.up_password)
    EditText up_password;
    @BindView(R.id.up_password_2)
    EditText up_password_2;
    @BindView(R.id.up_dl_bt)
    TextView up_dl_bt;
    private JSONObject object = new JSONObject();

    @OnClick(R.id.up_dl_bt)
    public void onClick(View view) {
        check();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        ButterKnife.bind(SignUp.this);
    }

    private void check() {
        if (TextUtils.isEmpty(up_name.getText().toString().trim())) {
            Toast.makeText(this, "电话号不能为空", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(up_password.getText().toString().trim())) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(up_password_2.getText().toString().trim())) {
            Toast.makeText(this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
        }
        if (!TextUtils.isEmpty(up_name.getText().toString().trim()) && up_password.getText().toString().trim().equals(up_password_2.getText().toString().trim())) {
            SignUpData(up_name.getText().toString().trim(), up_password.getText().toString().trim());
        } else {
            Toast.makeText(this, "两次密码不相同", Toast.LENGTH_SHORT).show();
        }


    }

    private void SignUpData(String name, String password) {
        RxRestClient.builder()
                .load(this)
                .url(StaticUrl.SIGN_UP)
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
                            Toast.makeText(SignUp.this, "注册成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp.this, Login.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignUp.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
