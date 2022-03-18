package ru.samsung.itschool.mdev.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class TestSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    private MyThread myThread;
    float x,y,r;
    boolean firstClick = false;

    public TestSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        myThread = new MyThread();
        myThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.x = event.getX();
        this.y = event.getY();
        this.r = 0;
        this.firstClick = true;
        return true;
    }

    class MyThread extends Thread {

        Paint paint;

        MyThread() {
            paint = new Paint();
            paint.setColor(Color.YELLOW);
        }

        @Override
        public void run() {
            Canvas canvas;
            while(true) {
                canvas = getHolder().lockCanvas();
                canvas.drawColor(Color.BLUE);
                r += firstClick ? 5 : 0;
                canvas.drawCircle(x,y,r,paint);
                getHolder().unlockCanvasAndPost(canvas);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}