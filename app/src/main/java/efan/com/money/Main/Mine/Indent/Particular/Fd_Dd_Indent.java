package efan.com.money.Main.Mine.Indent.Particular;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import efan.com.money.R;
import efan.com.money.staticfunction.StaticValue;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/14.
 */

public class Fd_Dd_Indent extends AppCompatActivity {
    @BindView(R.id.indent_back_iv)
    ImageView indent_back_iv;
    @BindView(R.id.indent_title_bar)
    TextView indent_title_bar;
    @BindView(R.id.fd_indent_dingdan_iv)
    ImageView fd_indent_dingdan_iv;
    @BindView(R.id.fd_indent_dingdan_lx_tv)
    TextView fd_indent_dingdan_lx_tv;
    @BindView(R.id.fd_indent_dingdan_title_tv)
    TextView fd_indent_dingdan_title_tv;
    @BindView(R.id.fd_indent_dingdan_qian)
    TextView fd_indent_dingdan_qian;
    @BindView(R.id.fd_indent_dingdan_zhuangtai_tv)
    TextView fd_indent_dingdan_zhuangtai_tv;
    @BindView(R.id.fd_indent_dingdan_jdr_tv)
    TextView fd_indent_dingdan_jdr_tv;
    @BindView(R.id.fd_indent_dingdan_sxsj_tv)
    TextView fd_indent_dingdan_sxsj_tv;
    @BindView(R.id.fd_indent_dingdan_rulx_tv)
    TextView fd_indent_dingdan_rulx_tv;
    @BindView(R.id.fd_indent_dingdan_zhyq_tv)
    TextView fd_indent_dingdan_zhyq_tv;
    @BindView(R.id.fd_indent_dingdan_rwnr_tv)
    TextView fd_indent_dingdan_rwnr_tv;
    @BindView(R.id.fd_indent_dingdan_rwbz_tv)
    TextView fd_indent_dingdan_rwbz_tv;
    @BindView(R.id.fd_indent_dingdan_jt1_iv)
    ImageView fd_indent_dingdan_jt1_iv;
    @BindView(R.id.fd_indent_dingdan_jt2_iv)
    ImageView fd_indent_dingdan_jt2_iv;
    @BindView(R.id.fd_indent_sh_ll)
    LinearLayout fd_indent_sh_ll;
    @BindView(R.id.fd_indent_btg_rl)
    RelativeLayout fd_indent_btg_rl;
    @BindView(R.id.fd_indent_tg_rl)
    RelativeLayout fd_indent_tg_rl;

    private int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fd_indent);
        ButterKnife.bind(this);
        initData();
        GetData();
    }

    private void GetData() {
        String id = getIntent().getStringExtra("id");
        type = getIntent().getIntExtra("type", 0);
        Toast.makeText(this, "传过来的值为" + id + " 传输类型" + type, Toast.LENGTH_SHORT).show();
        initTitle();
    }

    private void initTitle() {
        if (type == StaticValue.FD_DSH_TO_INDENT) {
            indent_title_bar.setText("待审核");
            fd_indent_dingdan_zhuangtai_tv.setText("待审核");
        } else if (type == StaticValue.FD_JXS_TO_INDENT) {
            indent_title_bar.setText("交易中");
            fd_indent_dingdan_zhuangtai_tv.setText("交易中");
            fd_indent_sh_ll.setVisibility(View.GONE);
        } else if (type == StaticValue.FD_JYCG_TO_INDENT) {
            indent_title_bar.setText("交易成功");
            fd_indent_dingdan_zhuangtai_tv.setText("交易成功");
            fd_indent_sh_ll.setVisibility(View.GONE);
        }
    }

    private void initData() {
    }
}
