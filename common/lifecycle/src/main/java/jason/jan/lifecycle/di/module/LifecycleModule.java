package jason.jan.lifecycle.di.module;

import android.app.Application;
import android.util.ArrayMap;

import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import jason.jan.lifecycle.delegate.ActivityLifecycle;
import jason.jan.lifecycle.delegate.AppManager;


/**
 * @author xiaobailong24
 * @date 2017/9/30
 * Dagger LifecycleModule
 */
@Module(includes = AndroidInjectionModule.class)
public class LifecycleModule {
    private Application mApplication;

    public LifecycleModule(Application application) {
        this.mApplication = application;
    }

    @Singleton
    @Provides
    AppManager provideAppManager() {
        return new AppManager(mApplication);
    }

    @Singleton
    @Provides
    ActivityLifecycle provideActivityLifecycle(AppManager appManager, Map<String, Object> extras) {
        return new ActivityLifecycle(appManager, mApplication, extras);
    }

    @Singleton
    @Provides
    Map<String, Object> provideExtras() {
        return new ArrayMap<>();
    }
}
