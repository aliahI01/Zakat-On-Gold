package com.example.zakatgold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    ImageView logo;
    TextView tvAbout, tvName, tvGroup, tvID, tvURL, copyright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        logo = (ImageView) findViewById(R.id.logo);
        tvAbout = (TextView) findViewById(R.id.tvAbout);
        tvName = (TextView) findViewById(R.id.tvName);
        tvGroup = (TextView) findViewById(R.id.tvGroup);
        tvID = (TextView) findViewById(R.id.tvID);
        tvURL = (TextView) findViewById(R.id.tvURL);
        copyright = (TextView) findViewById(R.id.copyright);

        tvURL.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String urlGit = "https://github.com/aliahI01/Zakat-on-Gold.git/";
                Intent in = new Intent(Intent.ACTION_VIEW);
                in.setData(Uri.parse(urlGit));
                startActivity(in);
            }
        });
    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.calc :
//                Toast.makeText(this, "This is calculation", Toast.LENGTH_LONG).show();

                Intent in = new Intent(this, MainActivity.class);
                startActivity(in);

                break;

            case R.id.about :
//                Toast.makeText(this, "This is about", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}