package efan.com.money.Util.net.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者： ZlyjD.
 * 时间：2018/3/24.
 */

public class RequestCallbacks implements Callback<String> {

    private final IError ERROR;
    private final IFailure FAILURE;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public RequestCallbacks(IError ERROR, IFailure FAILURE, IRequest REQUEST, ISuccess SUCCESS) {
        this.ERROR = ERROR;
        this.FAILURE = FAILURE;
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onsuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
    }
}
