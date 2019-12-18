package jason.jan.commonlib.base;

import android.app.Application;

import com.alibaba.android.arouter.BuildConfig;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Description: Application基类
 * *
 * Creator: Wang
 * Date: 2019/12/18 23:46
 */
public class BaseApplication extends Application {

    private static  BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        initRouter();
    }

    public static BaseApplication getInstance(){
        return instance;
    }
    private void initRouter(){
        if (BuildConfig.DEBUG) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

}
