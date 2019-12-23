package jason.jan.weatherdemo.mvvm.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import jason.jan.arms.mvvm.BaseViewModel;
import jason.jan.repository.http.IRetry;
import jason.jan.repository.http.Resource;
import jason.jan.repository.http.Status;
import jason.jan.weatherdemo.mvvm.model.WeatherNowModel;
import jason.jan.weatherdemo.mvvm.model.api.Api;
import jason.jan.weatherdemo.mvvm.model.entry.WeatherNowResponse;
import jason.jan.weatherdemo.mvvm.model.pojo.TextContent;
import timber.log.Timber;

/**
 * @author xiaobailong24
 * @date 2017/7/21
 * MVVM WeatherNowViewModel
 */
public class WeatherNowViewModel extends BaseViewModel<WeatherNowModel>
        implements IRetry {
    private final MediatorLiveData<List<TextContent>> mContents = new MediatorLiveData<>();
    private MutableLiveData<Resource<WeatherNowResponse>> mNowResponse;
    private String mLocationName = "";

    @Inject
    public WeatherNowViewModel(Application application, WeatherNowModel model) {
        super(application, model);
    }

    public void loadWeatherNow(String locationName) {
        mLocationName = locationName;
        Map<String, String> request = new HashMap<>(4);
        request.put(Api.API_KEY_KEY, Api.API_KEY);
        request.put(Api.API_KEY_TEMP_UNIT, "c");
        request.put(Api.API_KEY_LANGUAGE, "zh-Hans");
        request.put(Api.API_KEY_LOCATION, locationName);

        if (mNowResponse != null) {
            mContents.removeSource(mNowResponse);
        }
        mNowResponse = mModel.getWeatherNow(request);
        mContents.addSource(mNowResponse, observer -> {
            mContents.removeSource(mNowResponse);
            mContents.addSource(mNowResponse, newResource -> {
                if (newResource == null) {
                    newResource = Resource.error("", null);
                }
                Timber.d("Load weather now: %s", newResource.status);
                if (newResource.status == Status.LOADING) {
                    STATUS.set(Status.LOADING);
                } else if (newResource.status == Status.SUCCESS) {
                    List<TextContent> contents = new ArrayList<>(3);
                    WeatherNowResponse.NowResult result = newResource.data.getResults().get(0);
                    contents.add(new TextContent("地点", result.getLocation().getPath()));
                    contents.add(new TextContent("天气", result.getNow().getText()));
                    contents.add(new TextContent("温度", result.getNow().getTemperature() + "º"));
                    mContents.postValue(contents);
                    STATUS.set(Status.SUCCESS);
                } else if (newResource.status == Status.ERROR) {
                    STATUS.set(Status.ERROR);
                }
            });
        });
    }

    public LiveData<List<TextContent>> getWeatherNow() {
        return mContents;
    }

    @Override
    public void retry() {
        if (mLocationName != null) {
            loadWeatherNow(mLocationName);
        }
    }

    @Override
    public void onCleared() {
        super.onCleared();
        this.mNowResponse = null;
        this.mLocationName = null;
    }
}
