package efan.com.money.Util.Pay;

import efan.com.money.Main.BasePermissionActivity;

/**
 * 作者： ZlyjD.
 * 时间：2018/5/3.
 */

public class MainPay {

    public static void startPay(BasePermissionActivity delegate, int consume, String name, String describe) {
        new PayHandler(delegate, consume, name, describe).beginPayDialog();

    }
}
