package jason.jan.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jason.jan.commonlib.base.BaseApplication

class MainActivity : AppCompatActivity() {

    var app : BaseApplication ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
