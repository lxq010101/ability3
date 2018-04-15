package api.amap.com.ad;

import android.app.Activity;
import android.os.Bundle;

import com.amap.api.loc.MoblickAgent;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MoblickAgent.init(this);
    }
}
