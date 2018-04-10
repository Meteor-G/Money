package efan.com.money.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments = new ArrayList<Fragment>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void ViewPagerAdapter(List<Fragment> mData) {
        this.mFragments = mData;
    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return mFragments.get(arg0);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mFragments.size();
    }
}
