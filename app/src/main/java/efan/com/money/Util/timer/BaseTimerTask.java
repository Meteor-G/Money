package efan.com.money.Util.timer;

import java.util.TimerTask;

/**
 * 作者： ZlyjD.
 * 时间：2018/3/31.
 */

public class BaseTimerTask extends TimerTask {
    private ITimerListener mITimerListener;

    public BaseTimerTask(ITimerListener mITimerListener) {
        this.mITimerListener = mITimerListener;
    }

    @Override
    public void run() {
        mITimerListener.onTimer();
    }
}
