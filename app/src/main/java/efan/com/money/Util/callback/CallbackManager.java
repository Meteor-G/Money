package efan.com.money.Util.callback;

import java.util.WeakHashMap;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/26.
 */

public class CallbackManager {
    private static final WeakHashMap<Object, IGlobalCallback> CALLBACKS = new WeakHashMap<>();

    private static class Holder {
        private static final CallbackManager INSTENCE = new CallbackManager();
    }

    public static CallbackManager getInstence() {
        return Holder.INSTENCE;
    }

    public CallbackManager addCallback(Object tag, IGlobalCallback callback) {
        CALLBACKS.put(tag, callback);
        return this;
    }

    public IGlobalCallback getCallback(Object tag) {
        return CALLBACKS.get(tag);
    }
}
