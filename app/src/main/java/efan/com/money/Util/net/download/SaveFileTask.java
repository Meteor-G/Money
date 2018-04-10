package efan.com.money.Util.net.download;


import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.File;
import java.io.InputStream;

import efan.com.money.Util.file.FileUtil;
import efan.com.money.Util.net.callback.IRequest;
import efan.com.money.Util.net.callback.ISuccess;
import okhttp3.ResponseBody;

/**
 * 作者： ZlyjD.
 * 时间：2018/3/26.
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }


    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        final String name = (String) params[3];

        final InputStream is = body.byteStream();
        if (downloadDir == null || downloadDir.equals("")) {
            downloadDir = "down_loads";
        }
        if (extension == null || extension.equals("")) {
            extension = "";
        }
        if (name == null || name.equals("")) {
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, downloadDir, name);
        }
    }

    protected void onoPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null) {
            SUCCESS.onsuccess(file.getPath());
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        autoInstallApk(file);
    }

    //如果是apk直接安装
    private void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            //新建一个栈
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//            Latte.getApplication().startActivity(install);
        }

    }
}
