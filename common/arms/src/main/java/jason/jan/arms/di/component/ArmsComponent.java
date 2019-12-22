package jason.jan.arms.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import jason.jan.arms.base.ArmsInjector;
import jason.jan.arms.di.module.ArmsConfigModule;
import jason.jan.arms.di.module.ArmsModule;
import jason.jan.arms.di.module.ViewModelFactoryModule;
import jason.jan.arms.http.imageloader.ImageLoader;
import jason.jan.arms.utils.ArmsUtils;
import jason.jan.lifecycle.di.module.LifecycleModule;
import jason.jan.repository.di.module.RepositoryModule;


/**
 * @author xiaobailong24
 * @date 2017/7/13
 * Dagger ArmsComponent 向外提供一些方法获取需要的对象，
 * 通过 {@link ArmsUtils} 获取
 */
@Singleton
@Component(modules = {
        ViewModelFactoryModule.class,//提供ViewModel
        RepositoryModule.class,
        LifecycleModule.class,
        ArmsModule.class,
        ArmsConfigModule.class})
public interface ArmsComponent {
    /**
     * 获取 Application
     *
     * @return Application
     */
    Application application();


    /**
     * 图片加载管理器，策略模式，默认使用 Glide
     *
     * @return ImageLoader
     */
    ImageLoader imageLoader();

    /**
     * Dagger 注入
     *
     * @param armsInjector ArmsInjector
     */
    void inject(ArmsInjector armsInjector);
}
