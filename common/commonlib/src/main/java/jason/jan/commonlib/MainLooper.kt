package jason.jan.commonlib

import android.os.Looper

/**
 * Description: 全局的Handler
 *  可以在任意的子线程中调用方法runOnUiThread方法，切换线程到主线程，执行目标Runnable
 * *
 * Creator: Wang
 * Date: 2019/12/18 23:20
 */
class MainLooper protected constructor(looper: Looper): android.os.Handler(looper){

    /**
     * 静态方法和属性
     */
    companion object {

        val instance = MainLooper(Looper.getMainLooper())

        /**
         * 这里是调用了handler的 post方法
         */
        fun runOnUiThread(runnable: Runnable){
            if (Looper.getMainLooper() == Looper.myLooper()) {//kotlin的== 和Java中的Object的equals方法类似
                runnable.run()
            } else {
                instance.post(runnable)
            }
        }
    }
}
