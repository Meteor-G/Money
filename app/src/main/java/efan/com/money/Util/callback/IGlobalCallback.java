package efan.com.money.Util.callback;

import android.support.annotation.Nullable;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/26.
 */

public interface IGlobalCallback<T> {

    void executeCallback(@Nullable T args);
}
