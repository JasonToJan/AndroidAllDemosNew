package jason.jan.weatherdemo.di.module;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import java.util.Map;

import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.Multibinds;

/**
 * Description:  统一管理所有的Activity
 * *
 * Creator: Wang
 * Date: 2019/11/28 20:39
 */
@Module(includes = AbstractActivityModule.class)
public abstract class AbstractActivityFactoryModule {

    @Multibinds
    abstract Map<Class<? extends Activity>, AndroidInjector.Factory<? extends Activity>>
    activityInjectorFactories();

    @Multibinds
    abstract Map<Class<? extends Fragment>, AndroidInjector.Factory<? extends Fragment>>
    fragmentInjectorFactories();
}
