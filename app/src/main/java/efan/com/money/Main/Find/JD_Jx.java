package efan.com.money.Main.Find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/12.
 */

public class JD_Jx extends AppCompatActivity implements View.OnClickListener {
    private ImageView jd_jinxing_fanhui;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jd_jx);
        InitView();
        InitEvent();
    }

    private void InitEvent() {
    }

    private void InitView() {
        jd_jinxing_fanhui = (ImageView) findViewById(R.id.jd_jinxing_fanhui);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jd_jinxing_fanhui:
                finish();
                break;
        }
    }
}
