package com.minkiapps.newspaper.test.utils;

import ohos.agp.window.service.DisplayManager;
import ohos.app.Context;

public class UIUtils {

    public static int vpTpPixel(final Context context, final int vp) {
        return (int) (DisplayManager.getInstance()
                .getDefaultDisplay(context)
                .get()
                .getAttributes().densityPixels * vp);
    }
}
