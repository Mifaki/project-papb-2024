package com.mobile.petkuy;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

public class ImageLoader {

    private RequestQueue mRequestQueue;

    public ImageLoader(RequestQueue requestQueue) {
        mRequestQueue = requestQueue;
    }

    public void loadImage(String imageUrl, final ImageView imageView) {
        ImageRequest imageRequest = new ImageRequest(imageUrl,
                response -> imageView.setImageBitmap(response),
                0, 0,
                ImageView.ScaleType.CENTER_INSIDE,
                Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors here
                    }
                });

        mRequestQueue.add(imageRequest);
    }
}