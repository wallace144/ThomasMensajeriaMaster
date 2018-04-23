package co.udistrital.android.thomasmensageria.summary_route;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.udistrital.android.thomasmensageria.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryRouteFragment extends Fragment {


    public SummaryRouteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary_route, container, false);
    }

}
