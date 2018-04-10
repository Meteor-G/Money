package efan.com.money.Main;

import android.app.Application;

import efan.com.money.App.Main;

/**
 * 作者： ZlyjD.
 * 时间：2018/3/27.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Main.init(this)
                .withApiHost("https://www.baidu.com/")
                .configue();
    }
}
