package ru.dimorinny.showcasecard.util;

import android.app.Activity;
import android.os.Build;
import android.view.Gravity;
import android.widget.FrameLayout;

public class NavigationBarUtils {

    public enum NavigationBarPosition {
        BOTTOM,
        LEFT,
        RIGHT,
        UNKNOWN
    }

    public static int navigationBarHeight(Activity activity) {
        int resourceId = activity.getResources().getIdentifier("navigation_bar_height", "dimen", "android");

        if (hasNavigationBar(activity) && resourceId > 0) {
            return activity.getResources().getDimensionPixelSize(resourceId);
        }

        return 0;
    }

    public static NavigationBarPosition navigationBarPosition(final Activity activity) {
        NavigationBarPosition result = NavigationBarPosition.UNKNOWN;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && hasNavigationBar(activity)) {
            int gravity = ((FrameLayout.LayoutParams) activity.findViewById(android.R.id.navigationBarBackground)
                    .getLayoutParams()).gravity;
            return gravityToNavigationBarPosition(gravity);
        }

        return result;
    }

    private static boolean hasNavigationBar(Activity activity) {
        int id = activity.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        return id > 0 && activity.getResources().getBoolean(id);
    }

    private static NavigationBarPosition gravityToNavigationBarPosition(int gravity) {
        switch (gravity) {
            case Gravity.BOTTOM:
                return NavigationBarPosition.BOTTOM;
            case Gravity.LEFT:
                return NavigationBarPosition.LEFT;
            case Gravity.RIGHT:
                return NavigationBarPosition.RIGHT;
            default:
                return NavigationBarPosition.UNKNOWN;
        }
    }
}