package survive.sixstones.com.survive;

import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import survive.sixstones.com.survive.touchor.service.TouchorService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Settings.canDrawOverlays(MainActivity.this))
        {
            Intent intent = new Intent(MainActivity.this,TouchorService.class);
            Toast.makeText(MainActivity.this,"已开启Toucher", Toast.LENGTH_SHORT).show();
            startService(intent);
            finish();
        }else
        {
            //若没有权限，提示获取.
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            Toast.makeText(MainActivity.this,"需要取得权限以使用悬浮窗",Toast.LENGTH_SHORT).show();
            startActivityForResult(intent,1);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(Build.VERSION.SDK_INT>=23) {
                if (!Settings.canDrawOverlays(this)) {
                    Toast.makeText(MainActivity.this, "权限授予失败，无法开启悬浮窗", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "权限授予成功！", Toast.LENGTH_SHORT).show();
                    //创建悬浮窗
                    Intent intent = new Intent(MainActivity.this, TouchorService.class);
                    startService(intent);
                }
            }
        }
    }
}
