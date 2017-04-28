package com.example.rafael.encontrefacildois;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ImageView headerImage;
    TextView headerUsuario;
    TextView headerEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Bundle extras = getIntent().getExtras();

        headerImage = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageView);
        headerUsuario = (TextView) navigationView.getHeaderView(0).findViewById(R.id.txHeaderUsuario);
        headerEmail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.txHeaderEmail);

        headerUsuario.setText(extras.getString("Usuario"));
        headerEmail.setText(extras.getString("Email"));
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.itemHome) {
            Toast.makeText(this, "Você clicou em " + item.getTitle() + "!", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.itemConfiguracoes) {
            Toast.makeText(this, "Você clicou em " + item.getTitle() + "!", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.itemEletronicos) {
            Toast.makeText(this, "Você clicou em " + item.getTitle() + "!", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.itemRoupas) {
            Toast.makeText(this, "Você clicou em " + item.getTitle() + "!", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.itemAcessorios) {
            Toast.makeText(this, "Você clicou em " + item.getTitle() + "!", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.itemEletrodomesticos) {
            Toast.makeText(this, "Você clicou em " + item.getTitle() + "!", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.itemSair) {
            Toast.makeText(this, "Você clicou em " + item.getTitle() + "!", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}