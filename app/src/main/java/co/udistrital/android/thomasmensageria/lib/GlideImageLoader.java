package co.udistrital.android.thomasmensageria.lib;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;

/**
 * Created by ASUS on 18/06/2016.
 */
public class GlideImageLoader implements ImageLoader {

    private RequestManager glideRequestManager;
    private RequestListener onFinishedLoadingListener;
    private Context context;


    public GlideImageLoader(Context context) {
        this.context = context;
    }


    @Override
    public void load(ImageView imgeView, String URL) {
        Glide.with(context)
                    .load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(imgeView);
    }

    @Override
    public void setOnFinishedImageLoadingListener(Object listener) {
        if (listener instanceof RequestListener)
            this.onFinishedLoadingListener = (RequestListener) listener;
    }
}
