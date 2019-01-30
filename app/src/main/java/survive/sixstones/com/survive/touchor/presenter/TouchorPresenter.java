package survive.sixstones.com.survive.touchor.presenter;

import android.view.MotionEvent;

import survive.sixstones.com.survive.touchor.Touchor;
import survive.sixstones.com.survive.touchor.model.AccountModel;

public class TouchorPresenter implements Touchor.Presenter {

    private Touchor.View mTouchorView;

    public TouchorPresenter(Touchor.View mTouchorView) {
        this.mTouchorView = mTouchorView;
        this.mTouchorView.setPresenter(this);
    }

    @Override
    public boolean addAccount(float amount) {
        return false;
    }

    @Override
    public void moveTouchor(MotionEvent motionEvent) {
        mTouchorView.onTouchorMove(motionEvent);
    }

    @Override
    public void swichtTouch() {
        mTouchorView.onTouchorSwitch();
    }


}
