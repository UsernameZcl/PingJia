package zksiot.example.com.pingjia;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by 李梦月 on 2017/11/17.
 */

public class AddandsubtractView extends View {

    private Paint paint;
    private int number = 0;
    private Bitmap bitmap;
    private Bitmap bitmap1;
    private float pxImagePadding = BubbleUtils.dipToDimension( 5,getContext());// 图片间的间距
    private float strokewidth =BubbleUtils.dipToDimension(0.5f,getContext());// 图片间的间距
    private Rect addrect;
    private Rect subtractrect;
    private boolean contains;
    private boolean contains1;

    public AddandsubtractView(Context context) {
        this(context, null);
    }

    public AddandsubtractView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddandsubtractView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
//   paint.setStrokeWidth(strokewidth);
        paint.setColor(Color.parseColor("#333333"));
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.main_bottom_attention_normal);
        bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.main_bottom_attention_press);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setTextSize(getHeight() / 2);
        int width = getWidth();
        RectF rect = new RectF(0, 0, getWidth(), getHeight());
        canvas.drawRoundRect(rect, pxImagePadding, pxImagePadding, paint);
        int i = (int) (width / 4f);
        //计算两边留白
        int i1 = i / 4;
        //计算图片与控件的比例
        float bitmapmesuare = bitmapmesuare(bitmap, i - i1 * 2, bitmap.getHeight());
        float bitmapmesuare1 = bitmapmesuare(bitmap1, i - i1 * 2, bitmap1.getHeight());

        //
        float v = getHeight() - bitmapmesuare;
        float v1 = getHeight() - bitmapmesuare1;
        //LogUtils.E(v + "V" + v1 + "v1");

        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(i1, (int) (v / 2), i - i1, (int) (getHeight() - v / 2)), null);
        canvas.drawBitmap(bitmap1, new Rect(0, 0, bitmap1.getWidth(), bitmap1.getHeight()), new Rect(getWidth() - i + i1, (int) (v1 / 2), width - i1, (int) (getHeight() - v1 / 2)), null);
        canvas.drawLine(i, paint.getStrokeWidth(), i, getHeight() - paint.getStrokeWidth(), paint);
        canvas.drawLine(getWidth() - i, paint.getStrokeWidth(), getWidth() - i, getHeight() - paint.getStrokeWidth(), paint);
        String s = String.valueOf(number);
        canvas.drawText(s, getWidth() / 2 - paint.measureText(s) / 2, getHeight() / 2 + ((paint.getFontMetrics().bottom - paint.getFontMetrics().top) / 2 - paint.getFontMetrics().descent), paint);
        addrect = new Rect(0, 0, i, getHeight());
        subtractrect = new Rect(getWidth() - i, 0, getWidth(), getHeight());
    }

    public float bitmapmesuare(Bitmap bitmap, float width, float height) {
        int width1 = bitmap.getWidth();
        float i = width / width1;
        float v = height * i;
        return v;
    }

    public int getNumber() {
        return number;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                contains = addrect.contains((int) x, (int) y);
                contains1 = subtractrect.contains((int) x, (int) y);
                break;
            case MotionEvent.ACTION_UP:
                if (number > 0) {
                    if (contains) {
                        number -= 1;
                        postInvalidate();
                        if (addandsubtranListener != null) {
                            addandsubtranListener.subtran(number);
                        }
                    }
                }

                if (contains1) {
                    number += 1;
                    postInvalidate();
                    if (addandsubtranListener != null) {
                        addandsubtranListener.add(number);
                    }
                }
                break;
        }
        return true;
    }

    public void setNumber(int number) {
        this.number = number;
        postInvalidate();
    }
    public interface AddandsubtranListener {
        void add(int number);

        void subtran(int number);
    }

    private AddandsubtranListener addandsubtranListener;

    public void setAddandsubtranListener(AddandsubtranListener addandsubtranListener) {
        this.addandsubtranListener = addandsubtranListener;
    }
}
