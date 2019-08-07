package com.example.android.camera;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.List;

public class showCamera extends SurfaceView implements SurfaceHolder.Callback {

    Camera camera;
    SurfaceHolder holder;
    public showCamera(Context context, Camera camera) {
        super(context);
        this.camera = camera;
        holder = getHolder(); // this will hold our view
        holder.addCallback(this);


    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    Camera.Parameters params = camera.getParameters();
    //we can set parameters through the above params by using .set method

        // get the resolution of the camera hardware of phone like use 8MP camera or 13MP camera, whichever the phone has

        List<Camera.Size> sizes  = params.getSupportedPictureSizes();
        Camera.Size mSize  = null;

        for(Camera.Size size : sizes) // enhanced for loop
        {
            mSize = size;
        }

    // now we have to change the orientation of the camera
        if(this.getResources().getConfiguration().orientation!=Configuration.ORIENTATION_LANDSCAPE)
        {
            params.set("orientation", "portrait");
            camera.setDisplayOrientation(90);
            params.setRotation(90);
        }

        else
        {
            params.set("orientation", "landscape");
            camera.setDisplayOrientation(0);
            params.setRotation(0);
        }
        params.setPictureSize(mSize.width, mSize.height);
    camera.setParameters(params);
        try{
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }





    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    camera.stopPreview();
    camera.release();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
