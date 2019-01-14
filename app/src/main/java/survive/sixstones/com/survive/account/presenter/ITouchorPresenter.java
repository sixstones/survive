package survive.sixstones.com.survive.account.presenter;

import android.view.MotionEvent;

public interface ITouchorPresenter {

    boolean addAccount(float amount);

    void moveTouchor(MotionEvent motionEvent);

    void swichtTouch();
}
