package cl.estfel.siac;

/**
 * Created by robma on 13/05/2017.
 */

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class InsertActivity extends AppCompatActivity {

    private EditText insertar_nombre;
    private EditText insertar_apellidos;
    private EditText insertar_usuario;
    private EditText insertar_fecha_registro;
    private EditText insertar_estatus;
    private EditText insertar_tipo;
    private EditText insertar_password;
    private Button mostrar;
    private ImageButton mas;
    private ImageButton menos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        //Toast.makeText(InsertActivity.this, "estoy aquí", Toast.LENGTH_LONG).show();

        setContentView(R.layout.insertar_activity);

        insertar_nombre = (EditText) findViewById(R.id.tx_nombre);
        insertar_apellidos = (EditText) findViewById(R.id.tx_apellidos);
        insertar_usuario = (EditText) findViewById(R.id.tx_usuario);
        insertar_fecha_registro = (EditText) findViewById(R.id.tx_fecha_registro);
        insertar_estatus = (EditText) findViewById(R.id.tx_estatus);
        insertar_tipo = (EditText) findViewById(R.id.tx_tipo);
        insertar_password = (EditText) findViewById(R.id.tx_password);
        //Insertamos los datos de la persona.
        Button insertar = (Button) findViewById(R.id.insertar);
        insertar.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {


                if (!insertar_nombre.getText().toString().trim().equalsIgnoreCase("") ||
                        !insertar_apellidos.getText().toString().trim().equalsIgnoreCase("") ||
                        !insertar_usuario.getText().toString().trim().equalsIgnoreCase("") ||
                        !insertar_fecha_registro.getText().toString().trim().equalsIgnoreCase("") ||
                        !insertar_estatus.getText().toString().trim().equalsIgnoreCase("") ||
                        !insertar_tipo.getText().toString().trim().equalsIgnoreCase("") ||
                        !insertar_password.getText().toString().trim().equalsIgnoreCase(""))

                    new Insertar(InsertActivity.this).execute();

                else
                    Toast.makeText(InsertActivity.this, "Hay información por ingresar", Toast.LENGTH_LONG).show();
            }

        });

        //Mostramos los datos de la persona por pantalla.
        mostrar = (Button) findViewById(R.id.mostrar);
        //Se mueve por nuestro ArrayList mostrando el objeto posterior.
        mas = (ImageButton) findViewById(R.id.mas);
        //Se mueve por nuestro ArrayList mostrando el objeto anterior
        menos = (ImageButton) findViewById(R.id.menos);

    }

    //Inserta los datos de las Personas en el servidor.
    private boolean insertar(){
        HttpClient httpclient;
        List<NameValuePair> nameValuePairs;
        HttpPost httppost;
        httpclient=new DefaultHttpClient();
        httppost= new HttpPost("http://robertoadvance.dreamhosters.com/Connections/Android/insertar_usuarios.php"); // Url del Servidor

        //Añadimos nuestros datos
        nameValuePairs = new ArrayList<>(7);
        nameValuePairs.add(new BasicNameValuePair("nombre",insertar_nombre.getText().toString().trim()));
        nameValuePairs.add(new BasicNameValuePair("apellidos",insertar_apellidos.getText().toString().trim()));
        nameValuePairs.add(new BasicNameValuePair("nom_usuario",insertar_usuario.getText().toString().trim()));
        nameValuePairs.add(new BasicNameValuePair("fecha_registro",insertar_fecha_registro.getText().toString().trim()));
        nameValuePairs.add(new BasicNameValuePair("estatus",insertar_estatus.getText().toString().trim()));
        nameValuePairs.add(new BasicNameValuePair("tipo", insertar_tipo.getText().toString().trim()));
        nameValuePairs.add(new BasicNameValuePair("password", insertar_password.getText().toString().trim()));


        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            httpclient.execute(httppost);
            return true;
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        } catch (ClientProtocolException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return false;
    }
    //AsyncTask para insertar Personas
    class Insertar extends AsyncTask<String,String,String>{

        private Activity context;

        Insertar(Activity context){
            this.context=context;
        }
        @Override
        protected String doInBackground(String... params) {

            if(insertar())
                context.runOnUiThread(new Runnable(){
                    @Override
                    public void run() {

                        Toast.makeText(context, "Usuario ingresado con éxito", Toast.LENGTH_LONG).show();
                        insertar_nombre.setText("");
                        insertar_apellidos.setText("");
                        insertar_usuario.setText("");
                        insertar_fecha_registro.setText("");
                        insertar_estatus.setText("");
                        insertar_tipo.setText("");
                        insertar_password.setText("");
                    }
                });
            else
                context.runOnUiThread(new Runnable(){
                    @Override
                    public void run() {

                        Toast.makeText(context, "Usuario no se pudo grabar, consulte con Administrador", Toast.LENGTH_LONG).show();
                    }
                });
            return null;
        }
    }
}