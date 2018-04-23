package co.udistrital.android.thomasmensageria.lib;

import android.widget.ImageView;

/**
 * Created by ASUS on 18/06/2016.
 */
public interface ImageLoader {

    void load(ImageView imgAvatar, String url);
    void setOnFinishedImageLoadingListener(Object listener);
}
