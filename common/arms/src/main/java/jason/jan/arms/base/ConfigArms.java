package jason.jan.arms.base;

import android.content.Context;

import jason.jan.arms.di.module.ArmsConfigModule;


/**
 * @author xiaobailong24
 * @date 2017/6/16
 * 框架配置接口
 */
public interface ConfigArms {
    /**
     * 使用{@link ArmsConfigModule.Builder}给框架配置一些配置参数
     *
     * @param context: Context
     * @param builder: ArmsConfigModule.Builder
     */
    void applyOptions(Context context, ArmsConfigModule.Builder builder);
}
