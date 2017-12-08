package zksiot.example.com.pingjia;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    //implements RadioGroup.OnCheckedChangeListener
    private FragmentUtils fragmentUtils;
    private RadioGroup radioGroup;
    private FragmentManager supportFragmentManager;
    private Bundle bundle1;
    private MyFragment myFragment;
    private MyFragment myFragment1;
    private MyFragment myFragment2;
    private MyFragment myFragment3;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = savedInstanceState;
        setContentView(R.layout.activity_main);
        supportFragmentManager = getSupportFragmentManager();

        fragmentUtils = new FragmentUtils();


        //  radioGroup = (RadioGroup) findViewById(R.id.rgp);
//        radioGroup.setOnCheckedChangeListener(this);
//        myFragment = new MyFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("asdasdasd", "A");
//        myFragment.setArguments(bundle);
//
//        myFragment1 = new MyFragment();
//        bundle1 = new Bundle();
//        bundle1.putString("asdasdasd", "B");
//        myFragment1.setArguments(bundle1);
//
//        myFragment2 = new MyFragment();
//        Bundle bundle2 = new Bundle();
//        bundle2.putString("asdasdasd", "C");
//        myFragment2.setArguments(bundle2);
//
//        myFragment3 = new MyFragment();
//        Bundle bundle3 = new Bundle();
//        bundle3.putString("asdasdasd", "D");
//        myFragment3.setArguments(bundle3);

    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//    }
//
//    @Override
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        int i = group.indexOfChild(radioGroup.findViewById(checkedId));
//        switch (i) {
//            case 0:
//                fragmentUtils.hsfrgment(supportFragmentManager, myFragment, R.id.frag,  bundle);
//                break;
//            case 1:
//                fragmentUtils.hsfrgment(supportFragmentManager, myFragment1, R.id.frag,  bundle);
//                break;
//            case 2:
//                fragmentUtils.hsfrgment(supportFragmentManager, myFragment2, R.id.frag,  bundle);
//                break;
//            case 3:
//                fragmentUtils.hsfrgment(supportFragmentManager, myFragment3, R.id.frag,  bundle);
//                break;
//        }
//    }
}

