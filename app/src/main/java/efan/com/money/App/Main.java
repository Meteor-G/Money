package efan.com.money.App;

import android.content.Context;
import android.util.Log;

/**
 * 作者： ZlyjD.
 * 时间：2018/3/27.
 */

public class Main {
    public static Configurator init(Context context) {
        Configurator.getInstencs()
                .getLatteConfigs()
                .put(ConfigKey.APPLICATION_CONTEXT.name(), context);
        Log.i("运行", "context");
        return Configurator.getInstencs();
    }

    public static Configurator getConfigurators() {
        return Configurator.getInstencs();
    }

    public static <T> T getConfigurations(Object key) {
        return getConfigurators().getConfiguration(key);
    }

    public static Context getApplication() {
        return getConfigurations(ConfigKey.APPLICATION_CONTEXT.name());
    }
}
