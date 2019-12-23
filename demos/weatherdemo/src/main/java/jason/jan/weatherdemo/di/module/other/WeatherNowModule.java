package jason.jan.weatherdemo.di.module.other;

import dagger.Module;
import dagger.Provides;
import jason.jan.arms.mvvm.IModel;
import jason.jan.weatherdemo.R;
import jason.jan.weatherdemo.mvvm.model.WeatherNowModel;
import jason.jan.weatherdemo.mvvm.view.adapter.TextContentAdapter;


/**
 * @author xiaobailong24
 * @date 2017/7/15
 * Dagger WeatherNowModule
 */
@Module
public class WeatherNowModule {

    @Provides
    IModel provideWeatherNowModel(WeatherNowModel weatherNowModel) {
        return weatherNowModel;
    }

    @Provides
    TextContentAdapter provideAdapter() {
        return new TextContentAdapter(R.layout.super_text_item, null);
    }

}
