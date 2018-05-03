package efan.com.money.Util.Pay;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.eagle.pay66.Pay66;
import com.eagle.pay66.listener.CommonListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import efan.com.money.App.Main;
import efan.com.money.Bean.OrderPreMessage;
import efan.com.money.Bean.ResponseParam;
import efan.com.money.Main.BasePermissionActivity;
import efan.com.money.R;
import efan.com.money.Util.callback.CallbackManager;
import efan.com.money.Util.callback.CallbackType;
import efan.com.money.Util.callback.IGlobalCallback;

/**
 * 作者： ZlyjD.
 * 时间：2018/5/3.
 */

public class PayHandler implements View.OnClickListener {
    private final AlertDialog DIALOG;
    private final BasePermissionActivity DELEGATE;
    private final int consume;//价格
    private final String name;//商品名称
    private final String describe;//商品描述


    public PayHandler(BasePermissionActivity delegate, int consume, String name, String describe) {
        this.DELEGATE = delegate;
        DIALOG = new AlertDialog.Builder(delegate).create();
        this.consume = consume;
        this.name = name;
        this.describe = describe;
    }

    final void beginPayDialog() {
        DIALOG.show();
        final Window window = DIALOG.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_pay_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            params.dimAmount = 0.5f;
            window.setAttributes(params);

            window.findViewById(R.id.paydialog_btn_weixin).setOnClickListener(this);
            window.findViewById(R.id.paydialog_btn_zhifubao).setOnClickListener(this);
            window.findViewById(R.id.paydialog_btn_cancel).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.paydialog_btn_weixin:
                createOrder("WxPay");
                DIALOG.cancel();
                break;
            case R.id.paydialog_btn_zhifubao:
                createOrder("AliPay");
                DIALOG.cancel();
                break;
            case R.id.paydialog_btn_cancel:
                DIALOG.cancel();
                break;
        }
    }

    private void createOrder(final String payType) {
        //consume, "商品名称", "商品描述", new CommonListener() {}
        //consume分的整数倍
        Pay66.createOrder(1, name, describe, new CommonListener() {
            @Override
            public void onStart() {
                Log.d("TAG_CREATE_ORDER", "---onStart");
            }

            @Override
            public void onError(int code, String msg) {
                Log.d("TAG_CREATE_ORDER", "---onError");
                Log.d("TAG_CREATE_ORDER", "--onError--code=" + code + ",msg=" + msg);
            }

            @Override
            public void onSuccess(String response) {
                JSONObject object = new JSONObject();
                ResponseParam<OrderPreMessage> responseParam = object.parseObject(response,
                        new TypeReference<ResponseParam<OrderPreMessage>>() {
                        });
                if (responseParam != null && responseParam.getData() != null) {
                    Log.d("TAG_CREATE_ORDER", "---onSuccess--orderId=" + responseParam.getData().getOrderId());
                    pay(responseParam.getData().getOrderId(), responseParam.getData().getConsume(), payType);
                } else {
                    // 不包含订单信息时，处理后台返回异常信息
                    Log.d("TAG_CREATE_ORDER", "不包含订单信息时，处理后台返回异常信息");
                }
            }

            @Override
            public void onCompleted() {
                Log.d("TAG_CREATE_ORDER", "---onCompleted");
            }
        });
    }

    private void pay(String orderId, int consume, String payType) {
        //Pay66.pay(activity, orderId, consume,"支付方式", new CommonListener() {})
        Pay66.pay(DELEGATE, orderId, consume, payType, new CommonListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onError(int code, String reason) {
                Log.d("TAG_CREATE_ORDER", "onError---code=" + code + ",reason=" + reason);
                if (code == 4) { //内嵌APP不存在
                    installPayPlugin("db.db");
                }
                Toast.makeText(DELEGATE, reason, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(String s) {
                Log.d("TAG_CREATE_ORDER", s);
                final IGlobalCallback<String> callback = CallbackManager
                        .getInstence()
                        .getCallback(CallbackType.PAY_SUCCESS);
                if (callback != null) {
                    callback.executeCallback(s);
                }

            }

            @Override
            public void onCompleted() {

            }
        });
    }

    /**
     * 安装assets里的apk文件
     *
     * @param fileName
     */
    void installPayPlugin(String fileName) {
        try {
            InputStream is = DELEGATE.getAssets().open(fileName);
            File file = new File(Environment.getExternalStorageDirectory()
                    + File.separator + fileName + ".apk");
            if (file.exists())
                file.delete();
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri = dealUri_N(Main.getApplication(), intent, file);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            DELEGATE.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 处理安卓版本7.0以上，读取文件的版本
     *
     * @param context context
     * @param intent  intent
     * @param file    待读取的文件
     * @return 格式化后的文件读取路径
     */
    public static Uri dealUri_N(Context context, Intent intent, File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            if (intent != null)
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            //通过FileProvider创建一个content类型的Uri
            return FileProvider.getUriForFile(context, context.getPackageName() + ".fileProvider", file);
        } else {
            return Uri.fromFile(file);
        }
    }
}
