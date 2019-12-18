package jason.jan.commonlib

import android.app.Application

/**
 * Description: 组件化初始化接口，让组件的上下文都使用MainApp的上下文
 * *
 * Creator: Wang
 * Date: 2019/12/18 23:13
 */
interface IAppComponent {

    /**
     * 初始化
     */
    fun initial(application: Application)
}
