package api.amap.com.ad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amap.api.loc.MoblickAgent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MoblickAgent.init(this);
    }
}
