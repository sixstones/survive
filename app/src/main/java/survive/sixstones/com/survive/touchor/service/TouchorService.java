package survive.sixstones.com.survive.touchor.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import survive.sixstones.com.survive.touchor.Touchor;
import survive.sixstones.com.survive.touchor.view.TouchorType;
import survive.sixstones.com.survive.touchor.view.TouchorView;

public class TouchorService extends Service {

    private static final String TAG = "TouchorService";

    private Touchor.View touchorView;

    public TouchorService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"create touchor");
        touchorView = new TouchorView(getApplication());
        touchorView.createTouchor(TouchorType.SMALL);
    }
}
