package cl.estfel.siac;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
//import android.view.View;
//import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
import java.net.URL;


public class SuccessActivity extends AppCompatActivity {

        //ActionBar actionBar;

        String JSON_STRING;

        @Override
        protected  void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_success);



        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu){
            return MenuSuperior.instancia.onCreateOptionsMenu(menu, this);

        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            return MenuSuperior.instancia.onOptionsItemSelected(item, this);
        }
}

