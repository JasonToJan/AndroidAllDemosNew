package jason.jan.commonlib.base

import android.app.Application

import com.alibaba.android.arouter.BuildConfig
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Description: Application基类
 * 加了一个Aouter而已
 * *
 * Creator: Wang
 * Date: 2019/12/18 23:46
 */
 open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        initRouter()
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    private fun initRouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)
    }

    companion object {

        var instance: BaseApplication? = null
            private set
    }

}
