package efan.com.money.Util.net.rx;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import efan.com.money.Util.UI.loader.Loader;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/17.
 */

public abstract class BaseSubscriber<String> implements Observer<String> {
    private Context mContext;


    public BaseSubscriber(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        Log.i("RxJava", "onSubscribe");

    }

    @Override
    public void onError(@NonNull Throwable e) {
        Log.i("RxJava", "onError");
        Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        Loader.stopLoading();
    }

    @Override
    public void onComplete() {
        Log.i("RxJava", "onComplete");
        Loader.stopLoading();
    }

    @Override
    public void onNext(String string) {
        Log.i("RxJava", "onNext");
    }
}
