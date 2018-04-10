package efan.com.money.Main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import efan.com.money.Main.Find.MainFragment;
import efan.com.money.Main.Mine.MeFragment;
import efan.com.money.Main.Publish.PubFragment;
import efan.com.money.R;

/**
 * Created by Administrator on 2017/9/10.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout mMain;
    private LinearLayout mFub;
    private LinearLayout mMe;

    private ImageButton mImgMain;
    private ImageButton mImgFub;
    private ImageButton mImgMe;

    private Fragment mMainF;
    private Fragment mPubF;
    private Fragment mMeF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntView();
        IntEvent();
        setSelect(0);
    }

    private void IntView() {
        mMain=(LinearLayout)findViewById(R.id.id_tab_main);
        mFub=(LinearLayout)findViewById(R.id.id_tab_fub);
        mMe=(LinearLayout)findViewById(R.id.id_tab_me);
        mImgMain= (ImageButton) findViewById(R.id.id_tab_main_img);
        mImgFub= (ImageButton) findViewById(R.id.id_tab_fub_img);
        mImgMe= (ImageButton) findViewById(R.id.id_tab_me_img);


    }
    private void IntEvent() {
        mMain.setOnClickListener(this);
        mFub.setOnClickListener(this);
        mMe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        resetImgs();
        switch (v.getId()) {
            case R.id.id_tab_main:
                setSelect(0);
                break;
            case R.id.id_tab_fub:
                setSelect(1);
                break;
            case R.id.id_tab_me:
                setSelect(2);
                break;
        }
    }
    private void setSelect(int i) {
        // 获取Fragement管理者
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        // 隐藏所有fragment
        hideFragment(transaction);
        // 把图片变亮
        // 设置内容区域
        switch (i) {
            case 0:
                if (mMainF == null) {
                    mMainF = new MainFragment();
                    transaction.add(R.id.id_content, mMainF);
                } else {
                    transaction.show(mMainF);
                }
                mImgMain.setBackgroundResource(R.mipmap.bt_home_press);
                break;
            case 1:
                if (mPubF == null) {
                    mPubF = new PubFragment();
                    transaction.add(R.id.id_content, mPubF);
                } else {
                    transaction.show(mPubF);
                }
                mImgFub.setBackgroundResource(R.mipmap.bt_sell_press);
                break;
            case 2:
                if (mMeF == null) {
                    mMeF = new MeFragment();
                    transaction.add(R.id.id_content, mMeF);
                } else {
                    transaction.show(mMeF);
                }
                mImgMe.setBackgroundResource(R.mipmap.bt_user_press);
                break;
            default:
                break;
        }
        transaction.commit();

    }

    private void hideFragment(FragmentTransaction transaction) {
        // TODO Auto-generated method stub
        if (mMainF != null) {
            transaction.hide(mMainF);
        }
        if (mPubF != null) {
            transaction.hide(mPubF);
        }
        if (mMeF != null) {
            transaction.hide(mMeF);
        }

    }

    private void resetImgs() {
        mImgMain.setBackgroundResource(R.mipmap.bt_home);
        mImgFub.setBackgroundResource(R.mipmap.bt_sell);
        mImgMe.setBackgroundResource(R.mipmap.bt_user);

    }
}
