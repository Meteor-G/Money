package efan.com.money.Util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import efan.com.money.App.Main;

/**
 * Created by Administrator on 2017/12/2.
 * 测量
 */

public class DimenUtil {
    //得到屏幕的宽度
    public static int getScreenWindth() {
        final Resources resources = Main.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    //得到屏幕的高度
    public static int getScreenHeight() {
        final Resources resources = Main.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
