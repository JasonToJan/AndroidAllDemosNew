package jason.jan.weatherdemo;

import jason.jan.arms.base.BaseApplication;
import jason.jan.weatherdemo.di.component.AppComponent;
import jason.jan.weatherdemo.di.component.DaggerAppComponent;

/**
 * @author xiaobailong24
 * @date 2017/7/13
 * MainApp 配置框架
 * {@link BaseApplication}
 */
public class MainApp extends BaseApplication {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent
                .builder()
                .armsComponent(getArmsComponent())
                .build();
        mAppComponent.inject(this);
    }


    public AppComponent getAppComponent() {
        return this.mAppComponent;
    }

}
