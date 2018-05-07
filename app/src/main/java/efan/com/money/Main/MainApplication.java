package efan.com.money.Main;

import android.app.Application;
import android.support.annotation.Nullable;

import cn.jpush.android.api.JPushInterface;
import efan.com.money.App.Main;
import efan.com.money.Util.callback.CallbackManager;
import efan.com.money.Util.callback.CallbackType;
import efan.com.money.Util.callback.IGlobalCallback;
import efan.com.money.staticfunction.StaticUrl;

/**
 * 作者： ZlyjD.
 * 时间：2018/3/27.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Main.init(this)
                .withApiHost(StaticUrl.BASE_URL)
                .configue();
        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        CallbackManager.getInstence()
                .addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (JPushInterface.isPushStopped(Main.getApplication())) {
                            //开启极光推送
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(Main.getApplication());
//                            Toast.makeText(Main.getApplication(), "开启极光推送", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
//                        Toast.makeText(Main.getApplication(), "关闭极光推送", Toast.LENGTH_SHORT).show();
//                        if (!JPushInterface.isPushStopped(Main.getApplication())) {
//                            JPushInterface.stopPush(Main.getApplication());
//                        }
                    }
                });
    }


}
