package com.zxon.luckyred.views;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.TextView;

import com.zxon.luckyred.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SettingActivity extends AppCompatActivity {

    private static boolean sAccessibilityFlag = false;

    @Bind(R.id.accessibility_state)
    public TextView mAccessibilityState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        sAccessibilityFlag = isAccessibilityEnabled(getPackageName() + "/.MonitorService");
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (sAccessibilityFlag) {
            mAccessibilityState.setText(getText(R.string.enabled));
        } else {
            mAccessibilityState.setText(getText(R.string.disabled));
        }
    }


    private boolean isAccessibilityEnabled(String accessibilityMonitorServiceName) {
        AccessibilityManager manager = (AccessibilityManager) getSystemService(Context.ACCESSIBILITY_SERVICE);

        List<AccessibilityServiceInfo> runningServices = manager.getEnabledAccessibilityServiceList(AccessibilityEvent.TYPES_ALL_MASK);
        for (AccessibilityServiceInfo info : runningServices) {
            if (info.getId().equals(accessibilityMonitorServiceName)) {
                return true;
            }
        }
        return false;
    }

    private boolean isNotificationEnabled() {
        ContentResolver contentResolver = getContentResolver();
        String enabledListeners = Settings.Secure.getString(contentResolver, "enabled_notification_listeners");

        if (!TextUtils.isEmpty(enabledListeners)) {
            return enabledListeners.contains(getPackageName() + "/" + getPackageName() + ".NotificationService");
        } else {
            return false;
        }
    }
}
