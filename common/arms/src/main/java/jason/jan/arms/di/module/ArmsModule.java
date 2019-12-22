package jason.jan.arms.di.module;

import android.app.Application;
import android.util.ArrayMap;

import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author xiaobailong24
 * @date 2017/7/13
 * Dagger ArmsModule
 */
@Module
public class ArmsModule {
    private Application mApplication;

    public ArmsModule(Application application) {
        this.mApplication = application;
    }


    @Singleton
    @Provides
    Application provideApplication() {
        return this.mApplication;
    }

    @Singleton
    @Provides
    Map<String, Object> provideExtras() {
        return new ArrayMap<>();
    }

}
