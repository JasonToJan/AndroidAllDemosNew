package jason.jan.lifecycle;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jason.jan.lifecycle.delegate.ActivityLifecycle;
import jason.jan.lifecycle.delegate.AppLifecycles;
import jason.jan.lifecycle.delegate.ILifecycle;
import jason.jan.lifecycle.di.component.DaggerLifecycleComponent;
import jason.jan.lifecycle.di.component.LifecycleComponent;
import jason.jan.lifecycle.di.module.LifecycleModule;
import jason.jan.lifecycle.utils.ManifestLifecycleParser;
import jason.jan.lifecycle.utils.Preconditions;


/**
 * @author xiaobailong24
 * @date 2017/9/30
 * LifecycleInjector，需要在 Application 初始化，注入 LifecycleComponent
 */
public class LifecycleInjector implements ILifecycle, AppLifecycles {

    @Inject
    protected ActivityLifecycle mActivityLifecycle;

    private Application mApplication;
    private LifecycleComponent mLifecycleComponent;
    private LifecycleModule mLifecycleModule;
    private List<ConfigLifecycle> mConfigLifecycles;
    private List<AppLifecycles> mAppLifecycles = new ArrayList<>();//全局的App
    private List<Application.ActivityLifecycleCallbacks> mActivityLifecycles = new ArrayList<>();//Activity的


    public LifecycleInjector(Context context) {
        mConfigLifecycles = new ManifestLifecycleParser(context).parse();
        for (ConfigLifecycle lifecycle : mConfigLifecycles) {
            lifecycle.injectAppLifecycle(context, mAppLifecycles);//相当于把用户自己的生命周期逻辑 add进来了 如某一个 AppLifecyclesImpl
            lifecycle.injectActivityLifecycle(context, mActivityLifecycles);//相当于把用户自己对于Activity的回调类加进来 如某一个ActivityLifecycleCallbacksImpl
        }
    }

    @Override
    public void attachBaseContext(Context context) {
        for (AppLifecycles lifecycle : mAppLifecycles) {
            lifecycle.attachBaseContext(context);
        }
    }

    @Override
    public void onCreate(Application application) {
        this.mApplication = application;

        if (mLifecycleModule == null) {
            mLifecycleModule = new LifecycleModule(mApplication);
        }
        mLifecycleComponent = DaggerLifecycleComponent.builder()
                .lifecycleModule(mLifecycleModule)
                .build();
        mLifecycleComponent.inject(this);

        //这里注射一个全局 的  从反射中拿到的ConfigLifecycles 之后会在Fragment里拿这个
        mLifecycleComponent.extras().put(ConfigLifecycle.class.getName(), mConfigLifecycles);

        mApplication.registerActivityLifecycleCallbacks(mActivityLifecycle);//这里是dagger注入的

        this.mConfigLifecycles = null;

        for (Application.ActivityLifecycleCallbacks lifecycle : mActivityLifecycles) {
            mApplication.registerActivityLifecycleCallbacks(lifecycle);//Activity级别的回调
        }

        for (AppLifecycles lifecycle : mAppLifecycles) {//App级别的 生命周期回调
            lifecycle.onCreate(mApplication); //是通过重写onCreate，attachBaseContext,onTerminate方法来的
        }
    }

    @Override
    public void onTerminate(Application application) {
        if (mActivityLifecycle != null) {
            mApplication.unregisterActivityLifecycleCallbacks(mActivityLifecycle);
        }

        if (mActivityLifecycles != null && mActivityLifecycles.size() > 0) {
            for (Application.ActivityLifecycleCallbacks lifecycle : mActivityLifecycles) {
                mApplication.unregisterActivityLifecycleCallbacks(lifecycle);
            }
        }

        if (mAppLifecycles != null && mAppLifecycles.size() > 0) {
            for (AppLifecycles lifecycle : mAppLifecycles) {
                lifecycle.onTerminate(mApplication);//app的生命周期回调
            }
        }

        this.mLifecycleModule = null;
        this.mLifecycleComponent = null;
        this.mActivityLifecycle = null;
        this.mActivityLifecycles = null;
        this.mAppLifecycles = null;
        this.mApplication = null;
    }


    @Override
    public LifecycleComponent getLifecycleComponent() {
        Preconditions.checkNotNull(mLifecycleComponent,
                "%s cannot be null,first call %s#onCreate(Application) in %s#onCreate()",
                LifecycleComponent.class.getName(), getClass().getName(), mApplication.getClass().getName());
        return this.mLifecycleComponent;
    }

    @Override
    public LifecycleModule getLifecycleModule() {
        Preconditions.checkNotNull(mLifecycleModule,
                "%s cannot be null,first call %s#onCreate(Application) in %s#onCreate()",
                LifecycleModule.class.getName(), getClass().getName(), mApplication.getClass().getName());
        return this.mLifecycleModule;
    }

}
