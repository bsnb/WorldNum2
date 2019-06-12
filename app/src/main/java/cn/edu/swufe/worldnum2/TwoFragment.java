package cn.edu.swufe.worldnum2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class TwoFragment  extends Fragment {
    public static TwoFragment newInstance() {
        return new TwoFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout=new LinearLayout(getActivity());
        layout.setBackgroundResource(R.color.colorAccent);
        return layout;
    }
}
