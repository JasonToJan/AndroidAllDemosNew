package jason.jan.weatherdemo.mvvm.view.adapter;


import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import java.util.List;

import jason.jan.arms.mvvm.binding.BaseBindAdapter;
import jason.jan.arms.mvvm.binding.BaseBindHolder;
import jason.jan.weatherdemo.mvvm.model.pojo.TextContent;

/**
 * @author xiaobailong24
 * @date 2017/6/28
 * RecyclerView DataBinding Adapter
 */
public class TextContentAdapter extends BaseBindAdapter<TextContent> {

    public TextContentAdapter(@LayoutRes int layoutResId, @Nullable List<TextContent> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseBindHolder helper, TextContent item) {
        ViewDataBinding binding = helper.getBinding();
        binding.setVariable(jason.jan.weatherdemo.BR.content, item);
        binding.executePendingBindings();
    }

}
