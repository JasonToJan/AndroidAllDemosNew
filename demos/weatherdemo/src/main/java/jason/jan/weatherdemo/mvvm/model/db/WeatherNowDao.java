package jason.jan.weatherdemo.mvvm.model.db;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import jason.jan.weatherdemo.mvvm.model.entry.Location;


/**
 * @author xiaobailong24
 * @date 2017/7/29
 * Room Database DAO
 * @see <a href="http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2017/0726/8268.html">在Room中使用RxJava</a>
 */
@Dao
public interface WeatherNowDao {


    /**
     * 插入
     *
     * @param locations 地址信息
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Location... locations);

    /**
     * 查询
     *
     * @return 所有地址列表
     */
    @Query("SELECT * FROM location")
    Flowable<List<Location>> getAll();

    /**
     * 查询指定地址
     *
     * @param name 地址名称
     * @return 地址信息
     */
    @Query("SELECT * FROM location WHERE name = :name")
    Flowable<List<Location>> getLocationByName(String name);

    /**
     * 更新地址信息
     *
     * @param locations 要更新的地址列表
     */
    @Update
    void updateAll(Location... locations);

    /**
     * 删除地址
     *
     * @param locations 要删除的地址列表
     */
    @Delete
    void deleteLocation(Location... locations);
}
