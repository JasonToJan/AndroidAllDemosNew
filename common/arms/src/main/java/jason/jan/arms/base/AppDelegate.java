package jason.jan.arms.base;

import android.app.Application;
import android.content.Context;

import jason.jan.arms.di.component.ArmsComponent;
import jason.jan.arms.di.module.ArmsModule;
import jason.jan.lifecycle.LifecycleInjector;
import jason.jan.lifecycle.delegate.AppLifecycles;
import jason.jan.lifecycle.delegate.ILifecycle;
import jason.jan.lifecycle.di.component.LifecycleComponent;
import jason.jan.lifecycle.di.module.LifecycleModule;
import jason.jan.repository.IRepository;
import jason.jan.repository.RepositoryInjector;
import jason.jan.repository.di.component.RepositoryComponent;
import jason.jan.repository.di.module.RepositoryModule;


/**
 * @author xiaobailong24
 * @date 2017/6/16
 * Application 生命周期代理接口实现类
 */
public class AppDelegate implements AppLifecycles, ILifecycle, IRepository, IArms {
    private Application mApplication;
    /**
     * {@link RepositoryInjector}
     */
    private RepositoryInjector mRepositoryInjector;
    /**
     * {@link LifecycleInjector}
     */
    private LifecycleInjector mLifecycleInjector;
    /**
     * {@link ArmsInjector}
     */
    private ArmsInjector mArmsInjector;


    public AppDelegate(Context context) {
        if (mRepositoryInjector == null) {
            mRepositoryInjector = new RepositoryInjector(context);
        }
        if (mLifecycleInjector == null) {
            mLifecycleInjector = new LifecycleInjector(context);
        }
        if (mArmsInjector == null) {
            mArmsInjector = new ArmsInjector(context);
        }
    }

    @Override
    public void attachBaseContext(Context context) {
        mLifecycleInjector.attachBaseContext(context);
    }

    @Override
    public void onCreate(Application application) {
        this.mApplication = application;

        //Repository inject
        mRepositoryInjector.onCreate(mApplication);

        //Lifecycle inject
        mLifecycleInjector.onCreate(mApplication);

        //Arms Inject
        mArmsInjector.onCreate(mApplication);

    }

    @Override
    public void onTerminate(Application application) {
        mLifecycleInjector.onTerminate(application);
        this.mLifecycleInjector = null;
        mArmsInjector.onTerminate(application);
        this.mArmsInjector = null;
        mRepositoryInjector.onTerminate(application);
        this.mRepositoryInjector = null;
        this.mApplication = null;
    }


    @Override
    public LifecycleComponent getLifecycleComponent() {
        return mLifecycleInjector.getLifecycleComponent();
    }

    @Override
    public LifecycleModule getLifecycleModule() {
        return mLifecycleInjector.getLifecycleModule();
    }

    @Override
    public RepositoryComponent getRepositoryComponent() {
        return mRepositoryInjector.getRepositoryComponent();
    }

    @Override
    public RepositoryModule getRepositoryModule() {
        return mRepositoryInjector.getRepositoryModule();
    }

    @Override
    public ArmsComponent getArmsComponent() {
        return mArmsInjector.getArmsComponent();
    }

    @Override
    public ArmsModule getArmsModule() {
        return mArmsInjector.getArmsModule();
    }
}