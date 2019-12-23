package jason.jan.weatherdemo.mvvm.model.db;

import androidx.room.TypeConverter;
import androidx.room.util.StringUtil;

import java.util.Collections;
import java.util.List;

/**
 * @author xiaobailong24
 * @date 2017/7/29
 * Room Database Converter
 */
public class WeatherNowConverter {

    @TypeConverter
    public static List<Integer> stringToIntList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }
        return StringUtil.splitToIntList(data);
    }

    @TypeConverter
    public static String intListToString(List<Integer> ints) {
        return StringUtil.joinIntoString(ints);
    }

}
