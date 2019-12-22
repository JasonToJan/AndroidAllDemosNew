package jason.jan.arms.base;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProvider;
import jason.jan.arms.mvvm.IViewModel;
import jason.jan.lifecycle.delegate.IFragment;


/**
 * @author xiaobailong24
 * @date 2017/6/16
 * MVVM BaseFragment
 * 如果只使用 DataBinding, 则 VM 的泛型可以传
 */
public abstract class BaseFragment<DB extends ViewDataBinding, VM extends IViewModel>
        extends Fragment implements IFragment {
    protected final String TAG = this.getClass().getName();
    /**
     * 是否可见，用于懒加载
     */
    protected boolean mVisible = false;
    /**
     * 是否第一次加载，用于懒加载
     */
    protected boolean mFirst = true;
    private View mRootView;

    /**
     * ViewDataBinding
     */
    protected DB mBinding;

    /**
     * instance in subclass; 自动销毁
     */
    protected VM mViewModel;

    /**
     * MVVM ViewModel ViewModelProvider.Factory
     */
    @Inject
    protected ViewModelProvider.Factory mViewModelFactory;


    public BaseFragment() {
        //必须确保在 Fragment 实例化时 setArguments()
        setArguments(new Bundle());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = initView(inflater, container, savedInstanceState);
        if (mViewModel != null) {
            getLifecycle().addObserver((LifecycleObserver) mViewModel);
        }
        //可见，并且是首次加载
        if (mVisible && mFirst) {
            onFragmentVisibleChange(true);
        }
        return mRootView;
    }


    @Override
    public boolean injectable() {
        //默认进行依赖注入
        return true;
    }


    @Override
    public boolean useEventBus() {
        return true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mVisible = isVisibleToUser;
        if (mRootView == null) {
            return;//第一次会返回
        }
        //可见，并且首次加载时才调用  可见并且是第一次执行
        onFragmentVisibleChange(mVisible & mFirst); //& 不管前面的条件是否正确，后面都执行
    }

    /**
     * 当前 Fragment 可见状态发生变化时会回调该方法。
     * 如果当前 Fragment 是第一次加载，等待 onCreateView 后才会回调该方法，
     * 其它情况回调时机跟 {@link #setUserVisibleHint(boolean)}一致
     * 在该回调方法中你可以做一些加载数据操作，甚至是控件的操作.
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected void onFragmentVisibleChange(boolean isVisible) {
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mBinding = null;
        this.mRootView = null;
        this.mViewModelFactory = null;
        //移除LifecycleObserver
        if (mViewModel != null) {
            getLifecycle().removeObserver((LifecycleObserver) mViewModel);
        }
        this.mViewModel = null;
    }
}
