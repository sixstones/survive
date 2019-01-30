package survive.sixstones.com.survive.touchor;

import android.view.MotionEvent;

import survive.sixstones.com.survive.base.BasePresenter;
import survive.sixstones.com.survive.base.BaseView;
import survive.sixstones.com.survive.touchor.view.TouchorType;

public interface Touchor {
    interface Presenter extends BasePresenter{

        void moveTouchor(MotionEvent motionEvent);

        void swichtTouch();
    }

    interface View extends BaseView<Presenter>{

        void createTouchor(TouchorType touchorType);

        void onTouchorMove(MotionEvent motionEvent);

        void onTouchorSwitch();
    }
}
