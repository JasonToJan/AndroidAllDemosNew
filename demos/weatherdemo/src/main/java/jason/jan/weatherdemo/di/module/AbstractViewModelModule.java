package jason.jan.weatherdemo.di.module;


import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import jason.jan.arms.di.scope.ViewModelScope;
import jason.jan.weatherdemo.mvvm.viewmodel.WeatherDailyViewModel;
import jason.jan.weatherdemo.mvvm.viewmodel.WeatherNowViewModel;
import jason.jan.weatherdemo.mvvm.viewmodel.WeatherViewModel;


/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
@Module
public abstract class AbstractViewModelModule {

    @Binds
    @IntoMap
    @ViewModelScope(WeatherDailyViewModel.class)
    abstract ViewModel bindWeatherDailyViewModelModel(WeatherDailyViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelScope(WeatherViewModel.class)
    abstract ViewModel bindWeatherViewModel(WeatherViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelScope(WeatherNowViewModel.class)
    abstract ViewModel bindWeatherNowViewModel(WeatherNowViewModel viewModel);

}
