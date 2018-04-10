package efan.com.money.Util.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import efan.com.money.App.Main;


/**
 * 作者： ZlyjD.
 * 时间：2018/3/28.
 */

public class MainPreference {
    /**
     * 提示:
     * Activity.getPreferences(int mode)生成 Activity名.xml 用于Activity内部存储
     * PreferenceManager.getDefaultSharedPreferences(Context)生成 包名_preferences.xml
     * Context.getSharedPreferences(String name,int mode)生成name.xml
     */
    private static final SharedPreferences SHARED_PREFERENCES = PreferenceManager.getDefaultSharedPreferences(Main.getApplication());
    private static final String APP_PREFERENCE_KEY = "profile";

    private static SharedPreferences getAppPreference() {
        return SHARED_PREFERENCES;
    }

    public static void setAppProfile(String str) {
        getAppPreference()
                .edit()
                .putString(APP_PREFERENCE_KEY, str)
                .apply();
    }

    public static String getAppProfile() {
        return getAppPreference().getString(APP_PREFERENCE_KEY, null);
    }

    public static JSONObject getAppProfileJson() {
        final String profile = getAppProfile();
        return JSON.parseObject(profile);
    }

    public static void removeAppProfile() {
        getAppPreference()
                .edit()
                .remove(APP_PREFERENCE_KEY)
                .apply();
    }

    public static void clearAppPreferences() {
        getAppPreference()
                .edit()
                .clear()
                .apply();
    }

    public static void setAppFlag(String key, boolean flag) {
        getAppPreference()
                .edit()
                .putBoolean(key, flag)
                .apply();
    }

    public static boolean getAppFlag(String key) {
        return getAppPreference()
                .getBoolean(key, false);
    }

    public static void addCustomAppProfile(String key, String val) {
        getAppPreference()
                .edit()
                .putString(key, val)
                .apply();
    }

    public static String getCustomAppProfile(String key) {
        return getAppPreference().getString(key, "");
    }
}
