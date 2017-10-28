package com.example.hotfix;

import android.Manifest;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.taobao.sophix.SophixManager;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 23) {
            requestExternalStoragePermission();
        }
    }

    public void fix(View view) {
        SophixManager.getInstance().queryAndLoadNewPatch();
    }

    public void compute(View view) {
//        Toast.makeText(MainActivity.this, "计算结果：" + 2 / 0, Toast.LENGTH_LONG).show();
        Toast.makeText(MainActivity.this, "计算结果：" + 2 / 1, Toast.LENGTH_LONG).show();
    }

    private void requestExternalStoragePermission() {
        RxPermissions rxPermissions = new RxPermissions(this); // where this is an Activity instance
        rxPermissions
                .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            Log.d(TAG, "被允许了");
                        } else {
                            Log.d(TAG, "被否决了");
                        }
                    }
                });
    }
}
