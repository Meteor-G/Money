package efan.com.money.Util.net;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import efan.com.money.Util.UI.loader.LoaderStyle;
import efan.com.money.Util.net.callback.IError;
import efan.com.money.Util.net.callback.IFailure;
import efan.com.money.Util.net.callback.IRequest;
import efan.com.money.Util.net.callback.ISuccess;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 作者： ZlyjD.
 * 时间：2018/3/24.
 */

public class RestClientBuilder {

    private String mUrl = null;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;
    private IError mIError = null;
    private IFailure mIFailure = null;
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private RequestBody mBody = null;
    private Context mContext = null;
    private File mFile = null;
    private LoaderStyle mLoaderStyle = null;

    public RestClientBuilder() {
    }

    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder onRequset(IRequest iRequset) {
        this.mIRequest = iRequset;
        return this;
    }

    public final RestClientBuilder load(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RestClientBuilder load(Context context) {
//        this.mContext = context;
//        this.mLoaderStyle = LoaderStyle.BallSpinFadeLoaderIndicator;
        load(context, LoaderStyle.PacmanIndicator);
        return this;
    }


    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mIError, mIFailure,
                mIRequest, mISuccess, mContext, mBody, mFile, mDownloadDir, mExtension, mName, mLoaderStyle);
    }
}



