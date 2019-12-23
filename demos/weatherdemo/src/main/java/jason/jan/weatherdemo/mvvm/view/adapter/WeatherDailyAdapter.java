package jason.jan.weatherdemo.mvvm.view.adapter;


import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.allen.library.SuperTextView;
import com.bumptech.glide.load.resource.bitmap.FitCenter;

import java.util.List;
import java.util.Locale;

import jason.jan.arms.http.imageloader.glide.ImageConfigImpl;
import jason.jan.arms.mvvm.binding.BaseBindAdapter;
import jason.jan.arms.mvvm.binding.BaseBindHolder;
import jason.jan.arms.utils.ArmsUtils;
import jason.jan.weatherdemo.BR;
import jason.jan.weatherdemo.R;
import jason.jan.weatherdemo.mvvm.model.api.Api;
import jason.jan.weatherdemo.mvvm.model.entry.WeatherDailyResponse;


/**
 * @author xiaobailong24
 * @date 2017/8/15
 * RecyclerView DataBinding Adapter
 */
public class WeatherDailyAdapter extends BaseBindAdapter<WeatherDailyResponse.DailyResult.Daily> {

    public WeatherDailyAdapter(@LayoutRes int layoutResId, @Nullable List<WeatherDailyResponse.DailyResult.Daily> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseBindHolder helper, WeatherDailyResponse.DailyResult.Daily item) {
        ViewDataBinding binding = helper.getBinding();
        binding.setVariable(jason.jan.weatherdemo.BR.daily, item);
        binding.executePendingBindings();

        SuperTextView superTextView = helper.getView(R.id.super_item_daily);
        ArmsUtils.INSTANCE.obtainArmsComponent(mContext).imageLoader()
                .loadImage(mContext,
                        ImageConfigImpl.builder()
                                .url(String.format(Locale.CHINESE, Api.API_WEATHER_ICON_URL, item.getCodeDay()))
                                .placeholder(R.mipmap.ic_placeholder)
                                .errorPic(R.mipmap.weather_unknown)
                                .transformation(new FitCenter())
                                .imageView(superTextView.getRightIconIV())
                                .build());
    }

}
