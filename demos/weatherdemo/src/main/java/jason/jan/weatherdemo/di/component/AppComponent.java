package jason.jan.weatherdemo.di.component;

import dagger.Component;
import jason.jan.arms.di.component.ArmsComponent;
import jason.jan.arms.di.scope.AppScope;
import jason.jan.weatherdemo.MainApp;
import jason.jan.weatherdemo.di.module.AppModule;


/**
 * @author xiaobailong24
 * @date 2017/7/15
 * Dagger AppComponent
 */
@AppScope
@Component(

        dependencies = ArmsComponent.class,
        modules      = AppModule.class

)
public interface AppComponent {
    /**
     * Dagger 注入
     *
     * @param mainApp MainApp
     */
    void inject(MainApp mainApp);
}
