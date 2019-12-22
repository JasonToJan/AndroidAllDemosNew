package jason.jan.lifecycle.delegate;


import jason.jan.lifecycle.di.component.LifecycleComponent;
import jason.jan.lifecycle.di.module.LifecycleModule;

/**
 * @author xiaobailong24
 * @date 2017/6/16
 * Application 继承该接口，就可以具有 LifecycleComponent 提供的方法。
 */
public interface ILifecycle {
    /**
     * 获得全局 LifecycleComponent
     *
     * @return LifecycleComponent
     */
    LifecycleComponent getLifecycleComponent();


    /**
     * 获得全局 LifecycleModule 重用
     *
     * @return LifecycleModule
     */
    LifecycleModule getLifecycleModule();
}
