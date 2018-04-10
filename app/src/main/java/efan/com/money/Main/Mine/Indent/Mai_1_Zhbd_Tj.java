package efan.com.money.Main.Mine.Indent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/19.
 */

public class Mai_1_Zhbd_Tj extends AppCompatActivity implements View.OnClickListener {
    private ImageView jmai_1_zhbd_tj_iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mai_1_zhbd_tj);
        InitView();
        InitEvent();
    }

    private void InitEvent() {
        jmai_1_zhbd_tj_iv.setOnClickListener(this);
    }

    private void InitView() {
        jmai_1_zhbd_tj_iv = (ImageView) findViewById(R.id.jmai_1_zhbd_tj_iv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jmai_1_zhbd_tj_iv:
                finish();
                break;
        }
    }
}
