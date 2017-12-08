package zksiot.example.com.pingjia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

/**
 * Created by zcl on 2017/12/8.
 */

public class FragmentUtils {
    private Fragment hidefragment = null;
    private boolean isa = false;
    private Bundle bundle;

    public void hsfrgment(FragmentManager fragmentManager, Fragment show, int Rid, Bundle savedInstanceState) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(savedInstanceState!=null&&isa){
            bundle=null;
        }else {
            bundle=savedInstanceState;
        }
        if (bundle == null) {
            if (hidefragment == null) {
            } else {
                fragmentTransaction.hide(hidefragment);
            }
            if (show == null) {
            } else {
                if (show.isAdded()) {
                    fragmentTransaction.show(show);
                } else {
                    //没添加过我们应该添加
                    fragmentTransaction.add(Rid, show);
                }
            }
        } else {
            List<Fragment> fragments = fragmentManager.getFragments();
            for (int i = 0; i < fragments.size(); i++) {
                if (fragments.get(i) != null && fragments.get(i).isAdded()) {
                    fragmentTransaction.hide(fragments.get(i));
                }
            }
            if (show != null && show.isAdded()) {
                fragmentTransaction.show(show);
            }else {
                fragmentTransaction.add(Rid,show);
            }

        }
        hidefragment = show;
        isa = true;
        fragmentTransaction.commit();
    }

    public void setIssavdintance(boolean a) {
    }


}
