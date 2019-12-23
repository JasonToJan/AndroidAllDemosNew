package jason.jan.androidalldemos

import jason.jan.arms.base.BaseApplication

/**
 * 这个是 如果是集成模式下，最根本的Application
 * 为什么要这个，主要是为了处理集成的时候 Application 特殊处理
 * App 层的Application
 */
class AppRootApplication : BaseApplication() {

    override fun onCreate() {
      super.onCreate()
    }

}
