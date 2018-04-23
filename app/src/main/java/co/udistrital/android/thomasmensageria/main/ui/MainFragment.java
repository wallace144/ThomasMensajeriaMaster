package co.udistrital.android.thomasmensageria.main.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.udistrital.android.thomasmensageria.R;
import co.udistrital.android.thomasmensageria.close_route.CloseRouteFragment;
import co.udistrital.android.thomasmensageria.get_route.ui.GetRouteFragment;
import co.udistrital.android.thomasmensageria.services_route.ServicesRouteFragment;
import co.udistrital.android.thomasmensageria.summary_route.SummaryRouteFragment;
import co.udistrital.android.thomasmensageria.validate_route.ValidateRouteFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    Unbinder unbinder;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.linearObtenerRuta, R.id.linearValidarRuta, R.id.linearRutaServicios, R.id.linearResumenRuta, R.id.linearCierreRuta})
    public void onViewClicked(View view) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        switch (view.getId()) {
            case R.id.linearObtenerRuta:
                fragmentManager.beginTransaction().replace(R.id.content, new GetRouteFragment()).commit();
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.menu_icon_get_route);
                break;
            case R.id.linearValidarRuta:
                fragmentManager.beginTransaction().replace(R.id.content, new ValidateRouteFragment()).commit();
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.menu_icon_validate_route);
                break;
            case R.id.linearRutaServicios:
                fragmentManager.beginTransaction().replace(R.id.content, new ServicesRouteFragment()).commit();
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.menu_icon_services_route);
                break;
            case R.id.linearResumenRuta:
                fragmentManager.beginTransaction().replace(R.id.content, new SummaryRouteFragment()).commit();
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.menu_icon_summary_route);
                break;
            case R.id.linearCierreRuta:
                fragmentManager.beginTransaction().replace(R.id.content, new CloseRouteFragment()).commit();
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.menu_icon_close_route);
                break;
        }
    }
}
