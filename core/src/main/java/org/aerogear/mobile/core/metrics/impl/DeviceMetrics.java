package org.aerogear.mobile.core.metrics.impl;

import android.content.Context;
import android.os.Build;

import org.aerogear.mobile.core.metrics.Metrics;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Collects device metrics
 */
public class DeviceMetrics implements Metrics {

    private final String platform;
    private final String platformVersion;

    public DeviceMetrics(final Context context) {
        this.platform = "android";
        this.platformVersion = String.valueOf(Build.VERSION.SDK_INT);
    }

    @Override
    public String identifier() {
        return "device";
    }

    @Override
    public JSONObject data() {
        JSONObject data = new JSONObject();
        try {
            data.put("platform", platform);
            data.put("platformVersion", platformVersion);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }



}
