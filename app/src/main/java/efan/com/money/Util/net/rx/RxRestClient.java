package efan.com.money.Util.net.rx;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import efan.com.money.Util.UI.loader.Loader;
import efan.com.money.Util.UI.loader.LoaderStyle;
import efan.com.money.Util.net.HttpMethod;
import efan.com.money.Util.net.RestCreator;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import static efan.com.money.Util.net.HttpMethod.GET;
import static efan.com.money.Util.net.HttpMethod.POST;

/**
 * 作者： ZlyjD.
 * 时间：2018/3/24.
 */

public class RxRestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final Context CONTEXT;
    private final RequestBody BOBY;
    private final File FILE;
    private final LoaderStyle LOADER_STYLE;

    public RxRestClient(String url,
                        Map<String, Object> params,
                        Context context,
                        RequestBody body,
                        File file,
                        LoaderStyle loaderStyle) {
        this.URL = url;
        PARAMS.putAll(params);
        this.CONTEXT = context;
        this.BOBY = body;
        this.FILE = file;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }

    private Observable<String> request(HttpMethod method) {
        Log.i("RxJava", "开始request");
        final RxRestServer server = RestCreator.getRxRestService();
        Observable<String> observable = null;
        if (LOADER_STYLE != null) {
            Log.i("RxJava", "开始");
            Loader.showLoading(CONTEXT, LOADER_STYLE);
        }
        switch (method) {
            case GET:
                Log.i("RxJava", "开始get");
                observable = server.get(URL, PARAMS);
                break;
            case POST:
                observable = server.post(URL, PARAMS);
                break;
            case POST_RAM:
                observable = server.postRaw(URL, BOBY);
                break;
            case PUT:
                observable = server.put(URL, PARAMS);
                break;
            case PUT_RAM:
                observable = server.putRaw(URL, BOBY);
                break;
            case DELETE:
                observable = server.delete(URL, PARAMS);
                break;
            case UPLOAD:
                break;
            default:
                break;
        }
        return observable;
    }

    public final Observable<String> get() {
        return request(GET);
    }

    public final Observable<String> post() {
        if (BOBY == null) {
            return request(POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            return request(HttpMethod.POST_RAM);
        }
    }

    public final Observable<String> put() {
        if (BOBY == null) {
            return request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            return request(HttpMethod.PUT_RAM);
        }
    }

    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }

    //上传
    public final Observable<String> upload() {
        return request(HttpMethod.UPLOAD);
    }

    //下载
    public final Observable<ResponseBody> download() {
        final Observable<ResponseBody> requestBodyObservable = RestCreator.getRxRestService().download(URL, PARAMS);
        return requestBodyObservable;
    }
}
