package ddl.dalong.com.meishijie_project.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ddl.dalong.com.meishijie_project.R;

/**
 * 食话Fragment
 */
public class ShihuaFragment extends Fragment {


    private Context mContext;
    public ShihuaFragment() {
        // Required empty public constructor
    }


    public static ShihuaFragment newInstance() {
        ShihuaFragment fragment = new ShihuaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shihua, container, false);
        return view;
    }

}
