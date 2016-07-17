package ava.shadesofme.Content;

import android.databinding.BaseObservable;
import android.os.Parcelable;

import java.util.Observer;

public abstract class ContentViewModel extends BaseObservable implements Parcelable, Observer {

    public abstract String getTitle();
    public abstract String getNavButtonText();

}
