package jason.jan.arms.base;


import jason.jan.arms.di.component.ArmsComponent;
import jason.jan.arms.di.module.ArmsModule;

/**
 * @author xiaobailong24
 * @date 2017/9/30
 * Application 继承该接口，就可以具有 ArmsComponent 提供的方法。
 */
public interface IArms {
    /**
     * 获得全局 ArmsComponent
     *
     * @return ArmsComponent
     */
    ArmsComponent getArmsComponent();


    /**
     * 获得全局 ArmsModule 重用
     *
     * @return ArmsModule
     */
    ArmsModule getArmsModule();
}
