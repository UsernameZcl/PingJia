package zksiot.example.com.pingjia;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;

/**
 * Created by zcl on 2017/12/8.
 */

/**
 * （包含获取状态栏、标题栏）
 */
public class ActiivtyOne extends AppCompatActivity {
    Handler handler = new Handler();
    private int height;
    //    View root = new View(MainActivity.this);
    private Object activityContentHeight;
    private int actionBarHeight;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.iv);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Bitmap rsblur = rsblur(bitmap, this);
        imageView.setImageBitmap(rsblur);
        //获取状态栏
        int statusBarHeight = getStatusBarHeight();
        //获取标题栏
        TypedValue tv = new TypedValue();
        if (this.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, this.getResources().getDisplayMetrics());
        }
        //statusBarHeight == 25dp   actionBarHeight==56

        //获取屏幕分辨率
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;

        Log.e("TAG", "statusBarHeight-----" + statusBarHeight + "-----ActionBar-----" + actionBarHeight + "-----widthPixels-----" + widthPixels + "-----heightPixels-----" + heightPixels);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public Bitmap rsblur(Bitmap bitmap, Context context) {

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //先优化bitmap使他像素点降低
        int nw = Math.round(width * 1 / 8);
        int nh = Math.round(height * 1 / 8);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, nw, nh, false);
        RenderScript renderScript = RenderScript.create(context);
        Allocation fromBitmap = Allocation.createFromBitmap(renderScript, scaledBitmap);
        Allocation out = Allocation.createTyped(renderScript, fromBitmap.getType());
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        scriptIntrinsicBlur.setInput(fromBitmap);
//0-25  0最小  25最大
        scriptIntrinsicBlur.setRadius(1);
        scriptIntrinsicBlur.forEach(out);
        out.copyTo(scaledBitmap);
        renderScript.destroy();
        return scaledBitmap;

    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public int getActivityContentHeight() {
        int actionBarHeight = getActionBar().getHeight();
        return actionBarHeight;
    }
}
