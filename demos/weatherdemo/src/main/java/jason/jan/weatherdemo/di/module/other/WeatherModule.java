package jason.jan.weatherdemo.di.module.other;

import dagger.Module;
import dagger.Provides;
import jason.jan.arms.mvvm.IModel;
import jason.jan.weatherdemo.mvvm.model.WeatherModel;


/**
 * @author xiaobailong24
 * @date 2017/7/31
 * Dagger WeatherModule
 */
@Module
public class WeatherModule {

    @Provides
    public IModel provideMainModel(WeatherModel weatherModel) {
        return weatherModel;
    }
}
