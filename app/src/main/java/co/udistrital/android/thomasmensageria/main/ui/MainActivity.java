package co.udistrital.android.thomasmensageria.main.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import co.udistrital.android.thomasmensageria.R;
import co.udistrital.android.thomasmensageria.close_route.CloseRouteFragment;
import co.udistrital.android.thomasmensageria.domain.FirebaseHelper;
import co.udistrital.android.thomasmensageria.entities.Messenger;
import co.udistrital.android.thomasmensageria.get_route.ui.GetRouteFragment;
import co.udistrital.android.thomasmensageria.lib.GlideImageLoader;
import co.udistrital.android.thomasmensageria.lib.ImageLoader;
import co.udistrital.android.thomasmensageria.login.ui.LoginActivity;
import co.udistrital.android.thomasmensageria.main.MainPresenter;
import co.udistrital.android.thomasmensageria.main.MainPresenterImpl;
import co.udistrital.android.thomasmensageria.services_route.ServicesRouteFragment;
import co.udistrital.android.thomasmensageria.summary_route.SummaryRouteFragment;
import co.udistrital.android.thomasmensageria.validate_route.ValidateRouteFragment;
import de.hdodenhof.circleimageview.CircleImageView;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView{


    private CircleImageView imageView;
    private TextView tvprofile_name;
    private TextView tvprofile_email;
    private TextView tvprofile_document;
    private TextView tvprofile_position;
    private ProgressBar progressBar;
    private NavigationView navigationView;
    View headerView;

    private Messenger messenger;
    private ImageLoader imageLoader;

    private MainPresenter presenter;



    public MainActivity() {
        this.presenter = new MainPresenterImpl(this);
        presenter.updateProfileShow();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter.onCreate();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        headerView = navigationView.getHeaderView(0);

        imageView = (CircleImageView) headerView.findViewById(R.id.profile_image);
        tvprofile_name = (TextView) headerView.findViewById(R.id.tvprofile_name);
        tvprofile_email = (TextView) headerView.findViewById(R.id.tvprofile_email);
        tvprofile_document = (TextView) headerView.findViewById(R.id.tvprofile_document);
        tvprofile_position = (TextView) headerView.findViewById(R.id.tvprofile_position);
        progressBar = (ProgressBar) headerView.findViewById(R.id.progressBar);

        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, new MainFragment()).commit();
        getSupportActionBar().setTitle(R.string.menu_icon_main);

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {

            presenter.signOff();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        ActionBar actionBar = getSupportActionBar();
        FragmentManager fragmentManager = getSupportFragmentManager();
        int id = item.getItemId();


        if (id == R.id.main) {
           fragmentManager.beginTransaction().replace(R.id.content, new MainFragment()).commit();
           actionBar.setTitle(R.string.menu_icon_main);
        } else if (id == R.id.get_route) {
            fragmentManager.beginTransaction().replace(R.id.content, new GetRouteFragment()).commit();
            actionBar.setTitle(R.string.menu_icon_get_route);
        } else if (id == R.id.validate_route) {
            fragmentManager.beginTransaction().replace(R.id.content, new ValidateRouteFragment()).commit();
            actionBar.setTitle(R.string.menu_icon_validate_route);
        } else if (id == R.id.services_route) {
            fragmentManager.beginTransaction().replace(R.id.content, new ServicesRouteFragment()).commit();
            actionBar.setTitle(R.string.menu_icon_services_route);
        } else if (id == R.id.summary_route) {
            fragmentManager.beginTransaction().replace(R.id.content, new SummaryRouteFragment()).commit();
            actionBar.setTitle(R.string.menu_icon_summary_route);
        } else if (id == R.id.close_route) {
            fragmentManager.beginTransaction().replace(R.id.content, new CloseRouteFragment()).commit();
            actionBar.setTitle(R.string.menu_icon_close_route);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void setUser(Messenger user) {
        this.messenger = user;
        String name = (messenger.getNombre()+" "+ messenger.getApellido()).toUpperCase();
        String document = "C.C "+messenger.getCedula();

        imageLoader = new GlideImageLoader(getApplicationContext());
        imageLoader.load(imageView, messenger.getUrl());

        tvprofile_name.setText(name);
        tvprofile_email.setText(messenger.getEmail());
        tvprofile_document.setText(document);
        tvprofile_position.setText(messenger.getCargo().toUpperCase());

    }

    @Override
    public void onGetUserError(String error) {
        String msgError = String.format(getString(R.string.main_profile_update_notice),error);
        Snackbar.make(navigationView,msgError,Snackbar.LENGTH_SHORT).show();
    }
}
