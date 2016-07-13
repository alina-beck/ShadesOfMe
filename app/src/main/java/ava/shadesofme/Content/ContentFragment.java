package ava.shadesofme.Content;

import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

public class ContentFragment extends Fragment {

    protected LinearLayout.LayoutParams calculateFragmentSize() {

        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        int displayWidth = metrics.widthPixels;
        int displayHeight = metrics.heightPixels;

        int fragmentWidth = displayWidth * 85 / 100;
        int fragmentHeight = displayHeight * 75 / 100;

        return new LinearLayout.LayoutParams(fragmentWidth, fragmentHeight);
    }
}
