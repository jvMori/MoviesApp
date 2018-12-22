package com.example.jvmori.moviesapp.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class BitmapConvert {

    public static byte[] ConvertToByte(Context context, int imageResId){
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), imageResId);
        // convert bitmap to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    public static Bitmap ConvertToBitmap(byte[] outImage){
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        return BitmapFactory.decodeStream(imageStream);
    }
}
