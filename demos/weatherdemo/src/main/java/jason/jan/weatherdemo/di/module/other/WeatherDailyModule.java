package jason.jan.weatherdemo.di.module.other;

import dagger.Module;
import dagger.Provides;
import jason.jan.arms.mvvm.IModel;
import jason.jan.weatherdemo.R;
import jason.jan.weatherdemo.mvvm.model.WeatherDailyModel;
import jason.jan.weatherdemo.mvvm.view.adapter.WeatherDailyAdapter;



/**
 * @author xiaobailong24
 * @date 2017/8/14
 * Dagger WeatherDailyModule
 */
@Module
public class WeatherDailyModule {

    @Provides
    IModel provideWeatherDailyModel(WeatherDailyModel weatherDailyModel) {
        return weatherDailyModel;
    }

    @Provides
    WeatherDailyAdapter provideAdapter() {
        return new WeatherDailyAdapter(R.layout.super_item_daily, null);
    }
}
