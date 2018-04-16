package efan.com.money.Util.net;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import efan.com.money.Util.UI.loader.Loader;
import efan.com.money.Util.UI.loader.LoaderStyle;
import efan.com.money.Util.net.callback.IError;
import efan.com.money.Util.net.callback.IFailure;
import efan.com.money.Util.net.callback.IRequest;
import efan.com.money.Util.net.callback.ISuccess;
import efan.com.money.Util.net.callback.RequestCallbacks;
import efan.com.money.Util.net.download.DownloadHandler;
import okhttp3.RequestBody;
import retrofit2.Callback;

/**
 * 作者： ZlyjD.
 * 时间：2018/3/24.
 */

public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IError ERROR;
    private final IFailure FAILURE;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final Context CONTEXT;
    private final RequestBody BOBY;
    private final File FILE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final LoaderStyle LOADER_STYLE;

    public RestClient(String url,
                      Map<String, Object> params,
                      IError error,
                      IFailure failure,
                      IRequest request,
                      ISuccess success,
                      Context context,
                      RequestBody body,
                      File file,
                      String downloadDir,
                      String extension,
                      String name,
                      LoaderStyle loaderStyle) {
        this.URL = url;
        PARAMS.putAll(params);
        this.ERROR = error;
        this.FAILURE = failure;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.CONTEXT = context;
        this.BOBY = body;
        this.FILE = file;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestServer server = RestCreator.getRestService();
        retrofit2.Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        if (LOADER_STYLE != null) {
            Loader.showLoading(CONTEXT, LOADER_STYLE);
        }
        switch (method) {
            case GET:
                call = server.get(URL, PARAMS);
                break;
            case POST:
                call = server.post(URL, PARAMS);
                break;
            case POST_RAM:
                call = server.postRaw(URL, BOBY);
                break;
            case PUT:
                call = server.put(URL, PARAMS);
                break;
            case PUT_RAM:
                call = server.putRaw(URL, BOBY);
                break;
            case DELETE:
                call = server.delete(URL, PARAMS);
                break;
            case UPLOAD:
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(ERROR,
                FAILURE,
                REQUEST,
                SUCCESS,
                LOADER_STYLE);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (BOBY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.POST_RAM);
        }
    }

    public final void put() {
        if (BOBY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.PUT_RAM);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    //上传
    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    //下载
    public final void download() {
        new DownloadHandler(URL,
                ERROR,
                DOWNLOAD_DIR,
                EXTENSION,
                NAME,
                FAILURE,
                REQUEST,
                SUCCESS).
                handledownload();
    }
}
