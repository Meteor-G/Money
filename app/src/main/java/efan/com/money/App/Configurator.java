package efan.com.money.App;

import android.util.Log;

import com.blankj.utilcode.util.Utils;
import com.eagle.pay66.Pay66;
import com.mob.MobSDK;

import java.util.HashMap;

import efan.com.money.staticfunction.StaticValue;

/**
 * 作者： ZlyjD.
 * 时间：2018/3/27.
 */

public class Configurator {
    private static final HashMap<Object, Object> MAIN_CONFIGS = new HashMap<>();

    /**
     * 单例
     */
    private static class Holder {
        private static final Configurator INSTENCE = new Configurator();
    }

    public static Configurator getInstencs() {
        return Holder.INSTENCE;
    }

    /**
     * 返回HashMap
     *
     * @return
     */

    public final HashMap<Object, Object> getLatteConfigs() {
        return MAIN_CONFIGS;
    }

    private Configurator() {
        Log.i("运行", "false");
        MAIN_CONFIGS.put(ConfigKey.CONFIG_READY.name(), false);
    }

    public final Configurator withApiHost(String host) {
        MAIN_CONFIGS.put(ConfigKey.API_HOSE.name(), host);
        return this;
    }

    public void checkConfiguratr() {
        final boolean isReady = (boolean) MAIN_CONFIGS.get(ConfigKey.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    public final void configue() {
        MAIN_CONFIGS.put(ConfigKey.CONFIG_READY.name(), true);
        Log.i("运行", "初始化完成获取数据" + MAIN_CONFIGS.get(ConfigKey.API_HOSE.name()));
        Utils.init(Main.getApplication());
        Pay66.init(StaticValue.PAY66, Main.getApplication());
        MobSDK.init(Main.getApplication());
    }

    final <T> T getConfiguration(Object key) {
        checkConfiguratr();
        final Object value = MAIN_CONFIGS.get(key);
        Log.i("运行", "获取数据" + MAIN_CONFIGS.get(ConfigKey.API_HOSE.name()));
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) MAIN_CONFIGS.get(key);
    }

}
