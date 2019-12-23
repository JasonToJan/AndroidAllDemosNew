package jason.jan.weatherdemo.mvvm.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;


import jason.jan.arms.base.BaseFragment;
import jason.jan.weatherdemo.R;
import jason.jan.weatherdemo.databinding.FragmentWeatherDailyBinding;
import jason.jan.weatherdemo.mvvm.model.api.Api;
import jason.jan.weatherdemo.mvvm.view.adapter.WeatherDailyAdapter;
import jason.jan.weatherdemo.mvvm.viewmodel.WeatherDailyViewModel;
import jason.jan.weatherdemo.mvvm.viewmodel.WeatherViewModel;
import timber.log.Timber;

/**
 * @author xiaobailong24
 * @date 2017/8/14
 * MVVM WeatherDailyFragment
 */
public class WeatherDailyFragment extends BaseFragment<FragmentWeatherDailyBinding, WeatherDailyViewModel> {

    @Inject
    WeatherDailyAdapter mAdapter;
    /**
     * 共享 Activity 数据
     */
    WeatherViewModel mWeatherViewModel;
    private String mLocation;

    public static WeatherDailyFragment newInstance(String location) {
        WeatherDailyFragment weatherDailyFragment = new WeatherDailyFragment();
        Bundle args = new Bundle();
        args.putString(Api.API_KEY_LOCATION, location);
        weatherDailyFragment.setArguments(args);
        return weatherDailyFragment;
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //获取 Activity 的ViewModel 来共享数据
        mWeatherViewModel = ViewModelProviders.of(getActivity(), mViewModelFactory).get(WeatherViewModel.class);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(WeatherDailyViewModel.class);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather_daily, container, false);
        //设置ViewModel
        mBinding.setViewModel(mViewModel);
        mBinding.retry.setViewModel(mViewModel);
        mBinding.retry.setRetry(mViewModel);
        mBinding.weatherSource.setViewModel(mViewModel);
        //RecyclerView设置Adapter
        mBinding.recyclerWeatherDaily.setAdapter(mAdapter);
        //设置Refresh
        mBinding.refresh.setColorSchemeColors(
                ContextCompat.getColor(getContext(), R.color.colorPrimary),
                ContextCompat.getColor(getContext(), R.color.colorAccent),
                ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        return mBinding.getRoot();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        //懒加载：onFragmentVisibleChange().
        mWeatherViewModel.getLocation().observe(this, s -> {
            //位置变化时，需要重新加载
            mFirst = true;
            if (mLocation == null || !mLocation.equals(s)) {
                onFragmentVisibleChange(mVisible);
            }
        });
        mViewModel.getWeatherDaily().observe(this, dailies -> {
            mAdapter.replaceData(dailies);
            //加载完成
            if (mLocation.equals(mWeatherViewModel.getLocation().getValue())) {
                mFirst = false;
            }
        });
    }

    @SuppressWarnings("all")
    @Override
    public void setData(Object data) {
        /**
         * 可以在Activity中调用该接口，实现Activity与Fragment通信。建议 data 传递 {@link android.os.Message}；
         * 这样就可以根据 message.what 判断接收消息。
         * 但是：
         * 新姿势：可以通过 Activity 的 ViewModel 共享数据给包含的 Fragment，配合 LiveData 好用到爆。
         *  @see <a href="https://developer.android.com/topic/libraries/architecture/viewmodel.html#sharing_data_between_fragments">Sharing Data Between Fragments</a>
         */
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        //当 Fragment 显示/隐藏变化时执行该方法，根据是否显示 Fragment 加载数据
        if (mViewModel != null && isVisible) {
            //调用ViewModel的方法获取天气
            mLocation = mWeatherViewModel.getLocation().getValue();
            mViewModel.loadWeatherDaily(mLocation);
        }
    }

    @Override
    public void onDestroy() {
        mWeatherViewModel.getLocation().removeObservers(this);
        mViewModel.getWeatherDaily().removeObservers(this);
        super.onDestroy();
        this.mLocation = null;
        this.mAdapter = null;
        this.mWeatherViewModel = null;
    }
}
