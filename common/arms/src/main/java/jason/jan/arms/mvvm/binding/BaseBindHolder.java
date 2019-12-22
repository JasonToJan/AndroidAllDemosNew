package jason.jan.arms.mvvm.binding;

import android.view.View;


import com.chad.library.adapter.base.BaseViewHolder;

import androidx.databinding.ViewDataBinding;
import jason.jan.arms.R;


/**
 * @author xiaobailong24
 * @date 2017/6/30
 * DataBinding BaseBindHolder
 */
public class BaseBindHolder extends BaseViewHolder {

    public BaseBindHolder(View view) {
        super(view);
    }

    public ViewDataBinding getBinding() {
        return (ViewDataBinding) itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
    }
}
