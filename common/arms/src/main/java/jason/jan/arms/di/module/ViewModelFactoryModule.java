package jason.jan.arms.di.module;


import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import jason.jan.arms.mvvm.ViewModelFactory;

/**
 * @author xiaobailong24
 * @date 2017/7/31
 * Dagger ViewModelFactoryModule
 */
@Module
public abstract class ViewModelFactoryModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
