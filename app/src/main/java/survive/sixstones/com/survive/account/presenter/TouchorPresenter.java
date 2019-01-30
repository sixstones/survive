package survive.sixstones.com.survive.account.presenter;

import android.view.MotionEvent;

import survive.sixstones.com.survive.account.model.AccountModel;
import survive.sixstones.com.survive.account.view.ITouchorView;

public class TouchorPresenter implements ITouchorPresenter {

    private ITouchorView iTouchorView;

    private AccountModel accountModel;

    @Override
    public boolean addAccount(float amount) {
        return false;
    }

    @Override
    public void moveTouchor(MotionEvent motionEvent) {
        iTouchorView.onTouchorMove(motionEvent);
    }

    @Override
    public void swichtTouch() {
        iTouchorView.onTouchorSwitch();
    }

    public TouchorPresenter(ITouchorView iTouchorView) {
        this.iTouchorView = iTouchorView;
        this.accountModel = new AccountModel(this.iTouchorView.)
    }
}
