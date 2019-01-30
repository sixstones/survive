package survive.sixstones.com.survive.touchor.view;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import survive.sixstones.com.survive.R;
import survive.sixstones.com.survive.touchor.Touchor;

public class TouchorView extends View implements Touchor.View {

    private static final String TAG = "TouchorView";

    private Touchor.Presenter mPresenter;

    private TouchorType touchorType;

    private WindowManager.LayoutParams layoutParams;

    private WindowManager windowManager;

    private Context mContext;

    private LinearLayout touchorLayout;

    private int statusBarHeight;

    private EditText largeTouchEdit;

    private Button cancelButton;


    public TouchorView(Context context) {
        super(context);
        mContext = context;
        statusBarHeight = getStatusBarHeight();
    }

    @Override
    public void setPresenter(Touchor.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void createTouchor(TouchorType touchorType) {
        this.layoutParams = new WindowManager.LayoutParams();
        this.windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            this.layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            this.layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }

        if (touchorType == TouchorType.SMALL) {
            createSmallTouchor();
        } else {
            createLargeTouchor();
        }

        this.layoutParams.format = PixelFormat.RGBA_8888;
        this.layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        this.layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        this.layoutParams.x = 0;
        this.layoutParams.y = 0;

        windowManager.addView(touchorLayout, layoutParams);

    }

    void createSmallTouchor() {
        this.touchorType = TouchorType.SMALL;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        touchorLayout = (LinearLayout) inflater.inflate(R.layout.layout_small_touchor, null);
        ImageButton smallTouch = touchorLayout.findViewById(R.id.smallTouchor);
        touchorLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        this.layoutParams.width = touchorLayout.getMeasuredWidth();
        this.layoutParams.height = touchorLayout.getMeasuredHeight();
        smallTouch.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mPresenter.moveTouchor(event);
                return false;
            }
        });

        smallTouch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "small touch click");
                mPresenter.swichtTouch();
            }
        });

    }

    void createLargeTouchor() {
        this.touchorType = TouchorType.LARGE;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        touchorLayout = (LinearLayout) inflater.inflate(R.layout.layout_large_touchor, null);
        largeTouchEdit = (EditText) touchorLayout.findViewById(R.id.accountAmountEdit);
        Button largeTouchButton = (Button) touchorLayout.findViewById(R.id.addAccountBtn);
        cancelButton = (Button) touchorLayout.findViewById(R.id.cancelBtn);
        largeTouchEdit.setFocusable(true);
        touchorLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        this.layoutParams.width = touchorLayout.getMeasuredWidth();
        this.layoutParams.height = touchorLayout.getMeasuredHeight();
        cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "small touch click");
                mPresenter.swichtTouch();
            }
        });
    }

    @Override
    public void onTouchorMove(MotionEvent motionEvent) {
        layoutParams.x = (int) motionEvent.getRawX() - layoutParams.width/2;
        layoutParams.y = (int) motionEvent.getRawY() - layoutParams.height/2-getStatusBarHeight();
        windowManager.updateViewLayout(touchorLayout, layoutParams);
    }

    @Override
    public void onTouchorSwitch() {
        Log.i(TAG, String.format("on touchor switch %s ", this.touchorType.name()));
        LayoutInflater inflater = LayoutInflater.from(mContext);
        windowManager.removeView(touchorLayout);
        if (this.touchorType == TouchorType.SMALL) {
            Log.i(TAG, "on small touchor,switch to large");
            createLargeTouchor();
            layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
            windowManager.addView(touchorLayout, layoutParams);

        } else {
            Log.i(TAG, "on large touchor,switch to small");
            createSmallTouchor();
            windowManager.addView(touchorLayout, layoutParams);
        }
    }

    private int getStatusBarHeight() {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }


}
