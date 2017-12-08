package zksiot.example.com.pingjia;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lifx on 2017/12/8 0008.
 */

public class MyFragment  extends Fragment{

    private String asdasdasd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        Bundle arguments = getArguments();
        asdasdasd = arguments.getString("asdasdasd");
        textView.setText(asdasdasd);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("TAG", "onHiddenChanged: "+hidden+"asdasdasd"+asdasdasd );
    }
}
