package cl.estfel.siac;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {



    //CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds

    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    private EditText etUser;
    private EditText etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get Reference to variables
        etUser = (EditText) findViewById(R.id.User);
        etPassword = (EditText) findViewById(R.id.password);

    }

    // Triggers when LOGIN Button clicked

    public  void checkLogin(View arg0){

        //Get text from email and password field
        final  String user = etUser.getText().toString();
        final  String password = etPassword.getText().toString();

        //Initialize AsyncLogin() class with email and password
        new AsyncLogin().execute(user,password);

    }

    private class AsyncLogin extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tCargando...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {

                //Enter URL addres where your php file resides
                url = new URL("http://robertoadvance.dreamhosters.com/Connections/Android/valida.php");

            } catch (MalformedURLException e) {
                // Too auto-generated catch block
                e.printStackTrace();
                return "exception";
            }

            try {

                //Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");


                //setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                //Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("username", params[0])
                        .appendQueryParameter("password", params[1]);

                String query = builder.build().getEncodedQuery();

                //Open connections for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));

                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();


            } catch (IOException e1) {

                //tood auto-generated catch block
                e1.printStackTrace();
                return "exception";

            }

            try {

                int response_code = conn.getResponseCode();

                //check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    //read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);

                    }

                    //Pass data to onPostExecute method
                    return (result.toString());
                } else {
                    return ("unsuccessful");
                }


            } catch (IOException e) {
                e.printStackTrace();
                return "exception";

            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();

            if (result.equalsIgnoreCase("true")) {

                /*here launching another activity when login successful. If you persist login state
                use sharedPreferences of Android. And logout button to clear sharedPreferences.
                 */
                Intent intent = new Intent(MainActivity.this, SuccessActivity.class);
                startActivity(intent);
                MainActivity.this.finish();

            } else if (result.equalsIgnoreCase("false")) {


                // if username and password does no match display a error message
                Toast.makeText(MainActivity.this, "Usuario o password incorrecto", Toast.LENGTH_LONG).show();

            } else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {
                Toast.makeText(MainActivity.this, "Problema de conexión. Por favor reintente", Toast.LENGTH_LONG).show();
            }
        }
    }

}
















