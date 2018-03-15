package techkids.vn.shortstory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import techkids.vn.shortstory.databases.DatabaseHandle;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: " + DatabaseHandle.getDatabaseHandle(this).getListStoryModel().toString());
    }
}
