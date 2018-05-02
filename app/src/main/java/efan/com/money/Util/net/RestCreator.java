package efan.com.money.Util.net;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import efan.com.money.App.ConfigKey;
import efan.com.money.App.Main;
import efan.com.money.Util.net.rx.RxRestServer;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 作者： ZlyjD.
 * 时间：2018/3/24.
 */

public class RestCreator {


    public static final String BASE_URL = Main.getConfigurations(ConfigKey.API_HOSE.name());

    private static final class RetrofitHolder {
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.BUILDER)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient BUILDER = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();

    }

    private static final class ParamsHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    /**
     * Server接口
     */
    private static final class RestCreatorHolder {
        private static final RestServer REST_SERVER =
                RetrofitHolder.RETROFIT_CLIENT.create(RestServer.class);
    }

    public static RestServer getRestService() {
        return RestCreatorHolder.REST_SERVER;
    }

    /**
     * Server接口
     */
    private static final class RxRestCreatorHolder {
        private static final RxRestServer REST_SERVER =
                RetrofitHolder.RETROFIT_CLIENT.create(RxRestServer.class);
    }

    public static RxRestServer getRxRestService() {
        return RxRestCreatorHolder.REST_SERVER;
    }


}
