package jason.jan.weatherdemo.mvvm.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import jason.jan.arms.mvvm.BaseViewModel;
import jason.jan.repository.http.Resource;
import jason.jan.repository.http.Status;
import jason.jan.weatherdemo.mvvm.model.WeatherModel;
import timber.log.Timber;

/**
 * @author xiaobailong24
 * @date 2017/7/31
 * MVVM WeatherViewModel
 */
public class WeatherViewModel extends BaseViewModel<WeatherModel> {
    private final String comma = ",";

    private final MediatorLiveData<List<String>> mLocationPaths = new MediatorLiveData<>();
    private MutableLiveData<Resource<List<String>>> mLocationsResource;
    private boolean mFirst = true;

    /**
     * 可以与 Fragment 共享此数据
     */
    private MutableLiveData<String> mLocation;

    @Inject
    public WeatherViewModel(Application application, WeatherModel model) {
        super(application, model);
        loadLocations();
    }

    public void loadLocations() {
        mLocationsResource = mModel.getAllLocations();
        mLocationPaths.addSource(mLocationsResource, observer -> {
            mLocationPaths.removeSource(mLocationsResource);
            mLocationPaths.addSource(mLocationsResource, newResource -> {
                if (newResource == null) {
                    newResource = Resource.error("", null);
                }
                Timber.d("Load history locations: %s", newResource.status);
                if (newResource.status == Status.LOADING) {
                    STATUS.set(Status.LOADING);
                } else if (newResource.status == Status.SUCCESS) {
                    mLocationPaths.postValue(newResource.data);
                    String location = newResource.data.get(0);
                    //如果位置是全路径，则截取城市名
                    if (location.contains(comma)) {
                        location = location.substring(0, location.indexOf(comma));
                    }
                    if (mFirst) {
                        //只有第一次时获取历史地址
                        mFirst = false;
                        mLocation.postValue(location);
                    }
                    STATUS.set(Status.SUCCESS);
                } else if (newResource.status == Status.ERROR) {
                    STATUS.set(Status.ERROR);
                }
            });
        });
    }

    /**
     * 获取储存的位置记录
     *
     * @return 历史位置记录列表
     */
    public LiveData<List<String>> getHistoryLocations() {
        return mLocationPaths;
    }

    public MutableLiveData<String> getLocation() {
        if (mLocation == null) {
            mLocation = new MutableLiveData<>();
        }
        return mLocation;
    }

    @Override
    public void onCleared() {
        super.onCleared();
        this.mLocation = null;
    }

}
