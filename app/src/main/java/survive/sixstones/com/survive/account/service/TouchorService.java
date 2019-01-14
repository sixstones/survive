package survive.sixstones.com.survive.account.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import survive.sixstones.com.survive.account.presenter.ITouchorPresenter;
import survive.sixstones.com.survive.account.presenter.TouchorPresenter;
import survive.sixstones.com.survive.account.view.ITouchorView;
import survive.sixstones.com.survive.account.view.TouchorType;
import survive.sixstones.com.survive.account.view.TouchorView;

public class TouchorService extends Service {

    private static final String TAG = "TouchorService";

    private ITouchorView touchorView;

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
