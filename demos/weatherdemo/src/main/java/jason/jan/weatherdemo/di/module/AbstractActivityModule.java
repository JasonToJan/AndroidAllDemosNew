package jason.jan.weatherdemo.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import jason.jan.weatherdemo.mvvm.view.activity.WeatherActivity;
import jason.jan.weatherdemo.mvvm.view.fragment.WeatherDailyFragment;
import jason.jan.weatherdemo.mvvm.view.fragment.WeatherNowFragment;


/**
 * 这里写所有的Activity
 */
@Module
public abstract class AbstractActivityModule {

    @ContributesAndroidInjector
    abstract WeatherActivity contributesWeatherActivity();

    @ContributesAndroidInjector
    abstract WeatherNowFragment contributeWeatherNowFragment();

    @ContributesAndroidInjector
    abstract WeatherDailyFragment contributeWeatherDailyFragment();

}
