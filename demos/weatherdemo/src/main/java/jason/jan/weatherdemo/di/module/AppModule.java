package jason.jan.weatherdemo.di.module;

import android.app.Application;

import dagger.Module;
import jason.jan.weatherdemo.di.module.other.AllModule;

/**
 * @author xiaobailong24
 * @date 2017/7/22
 * Dagger AppModule
 */
@Module(
        includes = {
                AllModule.class,//其他有必要用的
                AbstractActivityFactoryModule.class,//内部依赖了Activity
                AbstractViewModelFactoryModule.class,//里面内部依赖了ViewModel
        })
public class AppModule {

    private Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

}
