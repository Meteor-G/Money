package efan.com.money.Util.UI.camera;

import android.net.Uri;

import efan.com.money.Main.BasePermissionActivity;
import efan.com.money.Util.file.FileUtil;

/**
 * Created by 傅令杰
 * 照相机调用类
 */

public class LatteCamera {

    public static Uri createCropFile() {
        return Uri.parse
                (FileUtil.createFile("Money",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(BasePermissionActivity delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
