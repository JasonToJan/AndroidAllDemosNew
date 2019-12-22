package jason.jan.arms.mvvm;

import android.app.Application;

import jason.jan.repository.IRepositoryManager;
import jason.jan.repository.utils.RepositoryUtils;


/**
 * @author xiaobailong24
 * @date 2017/6/16
 * MVVM BaseModel
 */
public class BaseModel implements IModel {

    protected IRepositoryManager mRepositoryManager;

    /**
     * 在构造函数中，直接通过 application 拿仓库Component
     * 然后通过component 拿数据接口
     * 这样就持有了数据对象
     * @param application
     */
    public BaseModel(Application application) {
        this.mRepositoryManager = RepositoryUtils.INSTANCE
                .obtainRepositoryComponent(application)
                .repositoryManager();
    }

    @Override
    public void onDestroy() {
        this.mRepositoryManager = null;
    }
}
