package efan.com.money.Main.Mine.Indent.Particular;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import efan.com.money.R;
import efan.com.money.staticfunction.StaticValue;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/12.
 */

public class Jd_Dd_Jyz extends AppCompatActivity {
    @BindView(R.id.indent_title_bar)
    TextView indent_title_bar;
    @BindView(R.id.indent_back_iv)
    ImageView indent_back_iv;
    @BindView(R.id.indent_dingdan_iv)
    ImageView indent_dingdan_iv;
    @BindView(R.id.indent_dingdan_lx_tv)
    TextView indent_dingdan_lx_tv;
    @BindView(R.id.indent_dingdan_title_tv)
    TextView indent_dingdan_title_tv;
    @BindView(R.id.indent_dingdan_qian)
    TextView indent_dingdan_qian;
    @BindView(R.id.indent_dingdan_zhuangtai_tv)
    TextView indent_dingdan_zhuangtai_tv;
    @BindView(R.id.indent_dingdan_rulx_tv)
    TextView indent_dingdan_rulx_tv;
    @BindView(R.id.indent_dingdan_zhyq_tv)
    TextView indent_dingdan_zhyq_tv;
    @BindView(R.id.indent_dingdan_scjt_tv)
    TextView indent_dingdan_scjt_tv;
    @BindView(R.id.indent_dingdan_rwnr_tv)
    TextView indent_dingdan_rwnr_tv;
    @BindView(R.id.indent_dingdan_rwbz_tv)
    TextView indent_dingdan_rwbz_tv;
    @BindView(R.id.indent_dingdan_sc1_tv)
    TextView indent_dingdan_sc1_tv;
    @BindView(R.id.indent_dingdan_sc2_tv)
    TextView indent_dingdan_sc2_tv;
    @BindView(R.id.indent_dingdan_tj_tv)
    TextView indent_dingdan_tj_tv;
    @BindView(R.id.indent_dingdan_tj_rl)
    RelativeLayout indent_dingdan_tj_rl;
    @BindView(R.id.indent_dingdan_fdr_tv)
    TextView indent_dingdan_fdr_tv;

    @OnClick({R.id.indent_back_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.indent_back_iv:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.indent);
        ButterKnife.bind(this);
        getData();
        initData();
    }

    private void initData() {
    }

    private void getData() {
        String id = getIntent().getStringExtra("id");
        int type = getIntent().getIntExtra("type", 0);
        Toast.makeText(this, "传过来的值为" + id + " 传输类型" + type, Toast.LENGTH_SHORT).show();
        initTitle(type);
    }

    private void initTitle(int type) {
        if (type == StaticValue.JYCG_TO_INDENT) {
            indent_title_bar.setText("交易成功");
            indent_dingdan_zhuangtai_tv.setText("已完成");
            indent_dingdan_tj_rl.setVisibility(View.GONE);
            indent_dingdan_sc1_tv.setVisibility(View.GONE);
            indent_dingdan_sc2_tv.setVisibility(View.GONE);
        } else if (type == StaticValue.JYZ_TO_INDENT) {
            indent_title_bar.setText("交易中");
            indent_dingdan_zhuangtai_tv.setText("已完成");
        } else if (type == StaticValue.SHZ_TO_INDENT) {
            indent_title_bar.setText("审核中");
            indent_dingdan_tj_tv.setText("取消审核");
            indent_dingdan_zhuangtai_tv.setText("已完成");
            indent_dingdan_tj_tv.setBackgroundResource(R.drawable.jd_jx_sc_rj);
            indent_dingdan_sc1_tv.setVisibility(View.GONE);
            indent_dingdan_sc2_tv.setVisibility(View.GONE);
        }
    }
}
