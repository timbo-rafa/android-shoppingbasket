package com.rafaeltimbo.shoppingbasket;

import android.content.Context;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class ImageDownloader {
    private ImageLoader imageLoader;

    ImageDownloader(Context context) {
        this.imageLoader = ImageLoader.getInstance();
        this.imageLoader.init(ImageLoaderConfiguration.createDefault(context));
    }

    public void download(ImageView view, String imageUrl) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()//
            .showImageOnFail(R.drawable.shopping_basket_greyed_out)
            //.showImageOnLoading(R.drawable.image_placeholder)
            .showImageForEmptyUri(R.drawable.shopping_basket_greyed_out)
            .cacheInMemory(true)
            .cacheOnDisk(false)
            .build();
        this.imageLoader.displayImage(imageUrl, view, options);
    }
}
