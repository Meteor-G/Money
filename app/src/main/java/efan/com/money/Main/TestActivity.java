package efan.com.money.Main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSONObject;

import efan.com.money.R;
import efan.com.money.Util.net.rx.BaseSubscriber;
import efan.com.money.Util.net.rx.RxRestClient;
import efan.com.money.Util.storage.MainPreference;
import efan.com.money.staticfunction.StaticUrl;
import efan.com.money.staticfunction.StaticValue;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/25.
 */

public class TestActivity extends BasePermissionActivity {

    JSONObject object = new JSONObject();
    private static int ticketCount = 100;
    private Button a_text_btn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_test);
        a_text_btn = findViewById(R.id.a_text_btn);
        a_text_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Start();
            }
        });
    }

    private void Start() {
        // 定义全局变量

        TicketSell sell = new TicketSell(ticketCount);
        for (int i = 0; i < 10; i++) {
            new Thread(sell).start();
        }
    }

    class TicketSell implements Runnable {
        private int count;

        public TicketSell(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (this) {// synchronized和if条件的位置要注意了，如果if条件在外层，那么可能会出现多个线程在请求最后一张票，然后出现负数的我情况
                    if (count > 0) {
                        count--;
                        Log.i("TestActivity", count + "");
                        RxRestClient.builder()
                                .url(StaticUrl.GET_DING_DAN)
                                .params("fd_id", "")
                                .params("jd_id", MainPreference.getCustomAppProfile(StaticValue.USER_ID))
                                .params("page", 0)
                                .params("zhuangtai", count)
                                .build()
                                .get()
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new BaseSubscriber<String>(TestActivity.this) {
                                    @Override
                                    public void onNext(String s) {
                                        if (object.parseObject(s).getString("success").equals("true")) {

                                        }
                                    }
                                });
                    }
//                    if (count > 0) {
//                        count--;
//                        System.out.println(Thread.currentThread().getName() + "剩余票：" + count);
//                    }
                }

            }
        }

    }
}
