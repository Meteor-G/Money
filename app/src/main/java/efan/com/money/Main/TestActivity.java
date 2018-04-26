package efan.com.money.Main;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import efan.com.money.R;
import efan.com.money.Util.callback.CallbackManager;
import efan.com.money.Util.callback.CallbackType;
import efan.com.money.Util.callback.IGlobalCallback;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/25.
 */

public class TestActivity extends BasePermissionActivity {
    private Button a_text_btn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_test);
        a_text_btn = (Button) findViewById(R.id.a_text_btn);
        a_text_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallbackManager.getInstence()
                        .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {

                            @Override
                            public void executeCallback(@Nullable Uri args) {
                                Toast.makeText(TestActivity.this, args + "", Toast.LENGTH_LONG).show();
                            }
                        });
                startCameraWithCheck();
            }
        });
    }
}
