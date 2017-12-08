package zksiot.example.com.pingjia;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by lifx on 2017/12/8 0008.
 */

public class MyRatingBar extends View {
    //start的间距
    private int numspacing = 30;
    private Bitmap statbit;
    private Bitmap selterstatbit;
    private Object size;

    //星星的图片
//    private
    public MyRatingBar(Context context) {
        this(context, null);
    }


    public MyRatingBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.myrationbar);
        numspacing = (int) typedArray.getDimension(R.styleable.myrationbar_hospacing, 30);
        int statbitId = typedArray.getResourceId(R.styleable.myrationbar_statbit, R.mipmap.main_bottom_attention_normal);
        int selterstatbitId = typedArray.getResourceId(R.styleable.myrationbar_selterstatbit, R.mipmap.main_bottom_attention_press);
        statbit = BitmapFactory.decodeResource(getResources(), statbitId);
        selterstatbit = BitmapFactory.decodeResource(getResources(), selterstatbitId);
        typedArray.recycle();
        init();
    }

    private void init() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthsize = getsize(widthMeasureSpec, 0);
        int heightsize = getsize(heightMeasureSpec, 1);
        setMeasuredDimension(widthsize, heightsize);
    }

    public int getsize(int widthMeasureSpec, int molde) {
        //molde 0是宽1是高
        int size = 0;

        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if (mode == MeasureSpec.AT_MOST) {
            //自己适应,计算高度，或者宽度
            if (molde == 0) {
                int width = statbit.getWidth();
                size = width * 5 + 4 * numspacing;
            } else {
                //高度是图片的高度
                int height = statbit.getHeight();
                size = height;
            }
        } else
            size = MeasureSpec.getSize(widthMeasureSpec);

        return size;
    }

    //被选中的个数s
    private int num = 1;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画选中的星星
        drawstart(canvas, num);
        //画未选中的星星
        drawunstart(canvas);
    }

    private void drawstart(Canvas canvas, int num) {
        for (int i = 0; i < num; i++) {
            //计算起点
            int start = 0;
            start = i * (selterstatbit.getWidth() + numspacing);
            canvas.drawBitmap(selterstatbit, new Rect(0, 0, selterstatbit.getWidth(), selterstatbit.getHeight()), new Rect(start, 0, start + selterstatbit.getWidth(), getHeight()), null);
        }

    }

    private void drawunstart(Canvas canvas) {
        //确定画的起点。该是选中的星星的数量到结尾的
        for (int i = this.num; i < 5; i++) {
            //计算起点
            int start = 0;
            start = i * (statbit.getWidth() + numspacing);
            canvas.drawBitmap(statbit, new Rect(0, 0, statbit.getWidth(), statbit.getHeight()), new Rect(start, 0, start + statbit.getWidth(), getHeight()), null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                //判断x的位置，来设置选中的星星
                indexstra(x);
                break;
        }

        return true;
    }

    private void indexstra(float x) {
        int a = 0;
        for (int i = 0; i < 5; i++) {
            //计算起点
            int start = i * (statbit.getWidth() + numspacing);
            if (x > start) {
                a = i;
            }
        }
        this.num = a + 1;
        postInvalidate();
    }

    public int getNum() {
        return this.num;
    }

    public void setNum() {
        this.num = num;
        postInvalidate();
    }
    /**
     *     <declare-styleable name="myrationbar">
     <attr name="hospacing" format="dimension" />
     <attr name="statbit" format="reference" />
     <attr name="selterstatbit" format="reference" />
     </declare-styleable>
     */
}
