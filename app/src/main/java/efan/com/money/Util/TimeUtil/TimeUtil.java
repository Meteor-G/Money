package efan.com.money.Util.TimeUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/23.
 */

public class TimeUtil {


    public static String Long2Time(Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(time));
    }

    public static String Long2Date(Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        return sdf.format(new Date(time));
    }

    public static String Long2Hour(Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh-mm");
        return sdf.format(new Date(time));
    }

}
