package jason.jan.arms.base;


import android.os.Bundle;


import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProvider;
import jason.jan.arms.mvvm.IViewModel;
import jason.jan.lifecycle.delegate.IActivity;


/**
 * @author xiaobailong24
 * @date 2017/6/16
 * MVVM BaseActivity
 * 如果只使用 DataBinding, 则 VM 的泛型可以传
 */
public abstract class BaseActivity<DB extends ViewDataBinding, VM extends IViewModel>
        extends AppCompatActivity implements IActivity {
    protected final String TAG = this.getClass().getName();

    /**
     * MVVM ViewModel ViewModelProvider.Factory
     */
    @Inject
    protected ViewModelProvider.Factory mViewModelFactory;

    /**
     * ViewDataBinding
     */
    protected DB mBinding;

    /**
     * instance in subclass; 自动销毁
     */
    protected VM mViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置DataBinding
        mBinding = DataBindingUtil.setContentView(this, initView(savedInstanceState));
        initData(savedInstanceState);
        if (mViewModel != null) {
            getLifecycle().addObserver((LifecycleObserver) mViewModel);
        }
    }

    @Override
    public boolean useEventBus() {
        return true;
    }


    @Override
    public boolean useFragment() {
        return true;
    }

    @Override
    public boolean injectable() {
        return true;
    }

    @SuppressWarnings("all")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        /**
         * 新姿势：通过ViewModel保存数据。
         *  @see <a href="https://developer.android.com/topic/libraries/architecture/viewmodel.html#viewmodel_vs_savedinstancestate">ViewModel vs SavedInstanceState</a>
         */
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mBinding = null;
        this.mViewModelFactory = null;
        //移除LifecycleObserver
        if (mViewModel != null) {
            getLifecycle().removeObserver((LifecycleObserver) mViewModel);
        }
        this.mViewModel = null;
    }
}