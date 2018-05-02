package co.udistrital.android.thomasmensageria.get_route.ui;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.udistrital.android.thomasmensageria.R;
import co.udistrital.android.thomasmensageria.domain.FirebaseHelper;
import co.udistrital.android.thomasmensageria.entities.Route;
import co.udistrital.android.thomasmensageria.get_route.RouteListPresenter;
import co.udistrital.android.thomasmensageria.get_route.RouteListPresenterImpl;
import co.udistrital.android.thomasmensageria.get_route.adapters.OnItemClickListener;
import co.udistrital.android.thomasmensageria.get_route.adapters.RouteListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetRouteFragment extends Fragment implements RouteListView, OnItemClickListener {


    private static final String BS_PACKAGE = "com.google.zxing.client.android";
    private static  final int REQUEST_CODE =24;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerViewRoute)
    RecyclerView recyclerViewRoute;
    Unbinder unbinder;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.routeFragmentId)
    CoordinatorLayout routeFragmentId;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.treference)
    TextView treference;

    private FirebaseHelper helper;

    private RouteListAdapter adapter;
    private RouteListPresenter presenter;

    private int id_guia;

    private List<Route> routes;

    public GetRouteFragment() {
        this.helper = FirebaseHelper.getInstance();
        routes = new ArrayList<Route>();
        presenter = new RouteListPresenterImpl(this);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_get_route, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter.onCreate();
        setupAdapter();
        setupRecyclerView();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        return view;
    }

    private void setupAdapter() {
        adapter = new RouteListAdapter(new ArrayList<Route>(), this);
    }

    private void setupRecyclerView() {
        recyclerViewRoute.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewRoute.setAdapter(adapter);
    }


    @Override
    public void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();

    }

    @Override
    public void showList() {
        recyclerViewRoute.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        recyclerViewRoute.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onRouteAdd(Route route) {
        adapter.add(route);
    }

    @Override
    public void onRouteChanged(Route route) {
        adapter.update(route);
    }

    @Override
    public void onRouteRemoved(Route route) {
        adapter.remove(route);
    }

    @Override
    public void onRouteError(String error) {
        Snackbar.make(routeFragmentId, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void changedState() {
        treference.setText("IGUALES");
    }


    @OnClick(R.id.fab)
    public void addRoute() {
        Toast.makeText(getActivity(), "Hizo click para agregar", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemClick(Route route) {
        scanBar(route.getId_guia());
        Toast.makeText(getActivity().getApplicationContext(), "hizo click", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemLongClick(Route route) {
        Toast.makeText(getActivity().getApplicationContext(), "Hizo click largo", Toast.LENGTH_SHORT).show();
        presenter.removeRoute(String.valueOf(route.getId_guia()));
    }


    private void scanBar(int id_guia) {
        this.id_guia =id_guia;
        try {
            Intent intent = new Intent(BS_PACKAGE + ".SCAN");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

            intent.putExtra("SCAN_WIDTH", 1780);
            intent.putExtra("SCAN_HEIGHT", 1070);

            startActivityForResult(intent, REQUEST_CODE);


        } catch (Exception e) {
            errorMessageScan(e);
        }
    }

    private void errorMessageScan(Exception e) {
        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                "error: " + e.getMessage(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        TextView v = (TextView) toast.getView().findViewById(
                android.R.id.message);
        v.setTextColor(Color.RED);
        toast.show();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_CODE ) {
            if (resultCode == getActivity().RESULT_OK)
                presenter.validateGuia(this.id_guia,intent);
            else if (resultCode == getActivity().RESULT_CANCELED) { }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
