package jason.jan.arms.mvvm.binding;


import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import jason.jan.arms.R;

/**
 * @author xiaobailong24
 * @date 2017/6/30
 * DataBinding BaseBindAdapter
 */
public abstract class BaseBindAdapter<T> extends BaseQuickAdapter<T, BaseBindHolder> {

    public BaseBindAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }

}
