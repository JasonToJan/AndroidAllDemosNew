package jason.jan.weatherdemo.di.module;



import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import jason.jan.arms.mvvm.ViewModelFactory;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
@Module(includes = AbstractViewModelModule.class)
public abstract class AbstractViewModelFactoryModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);
}
