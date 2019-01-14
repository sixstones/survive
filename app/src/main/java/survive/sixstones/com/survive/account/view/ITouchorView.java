package survive.sixstones.com.survive.account.view;

import android.view.MotionEvent;

public interface ITouchorView {

    void onAddSuccess();


    void createTouchor(TouchorType touchorType);


    void onTouchorMove(MotionEvent motionEvent);

    void onTouchorSwitch();
}
