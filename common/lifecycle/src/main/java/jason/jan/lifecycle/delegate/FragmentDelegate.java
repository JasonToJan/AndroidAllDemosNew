package jason.jan.lifecycle.delegate;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

/**
 * @author xiaobailong24
 * @date 2017/6/16
 * Fragment 生命周期代理接口
 */
public interface FragmentDelegate extends Parcelable {
    String FRAGMENT_DELEGATE = "fragment_delegate";


    /**
     * 代理 {@link }
     *
     * @param context Context
     */
    void onAttach(Context context);

    /**
     * 代理 {@link }
     *
     * @param savedInstanceState 数据恢复
     */
    void onCreate(Bundle savedInstanceState);

    /**
     * 代理 {@link }
     *
     * @param view               View
     * @param savedInstanceState 数据恢复
     */
    void onCreateView(View view, Bundle savedInstanceState);

    /**
     * 代理 {@link }
     *
     * @param savedInstanceState 数据恢复
     */
    void onActivityCreate(Bundle savedInstanceState);

    /**
     * 代理 {@link }
     */
    void onStart();

    /**
     * 代理 {@link }
     */
    void onResume();

    /**
     * 代理 {@link }
     */
    void onPause();

    /**
     * 代理 {@link }
     */
    void onStop();

    /**
     * 代理 {@link }
     *
     * @param outState 数据保存
     */
    void onSaveInstanceState(Bundle outState);

    /**
     * 代理 {@link }
     */
    void onDestroyView();

    /**
     * 代理 {}
     */
    void onDestroy();

    /**
     * 代理 {}
     */
    void onDetach();

    /**
     * Fragment 是否添加到 Activity
     *
     * @return true if the fragment is currently added to its activity.
     */
    boolean isAdded();
}
