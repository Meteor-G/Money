package efan.com.money.staticfunction;

import android.content.Context;
import android.widget.Toast;

public class ShowTips {
    public static void showTips(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

}
