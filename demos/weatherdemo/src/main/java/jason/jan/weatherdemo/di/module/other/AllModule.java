package jason.jan.weatherdemo.di.module.other;


import dagger.Module;


/**
 * @author xiaobailong24
 * @date 2017/7/22
 * Dagger AppModule
 */
@Module(
        includes = {
                WeatherDailyModule.class,
                WeatherNowModule.class,
                WeatherModule.class,
        })
public class AllModule {

}
