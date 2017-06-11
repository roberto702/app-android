package cl.estfel.siac;

/**
 * Created by robma on 13/05/2017.
 */

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.ImageButton;
import android.widget.Toast;
import android.text.Editable;
//import android.text.TextUtils;
import android.text.TextWatcher;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;

public class InsertActivity extends AppCompatActivity {

    //private Toolbar toolbar;

    private EditText insertar_nombre;
    private EditText insertar_apellidos;
    private EditText insertar_usuario;
    private EditText insertar_fecha_registro;
    private EditText insertar_estatus;
    private EditText insertar_tipo;
    private EditText insertar_password;

    private TextInputLayout inputLayoutName;
    private TextInputLayout inputLayoutApellidos;
    private TextInputLayout inputLayoutUsuario;
    private TextInputLayout inputLayoutFechaIngreso;
    private TextInputLayout inputLayoutEstatus;
    private TextInputLayout inputLayoutTipo;
    private TextInputLayout inputLayoutPassword;


    private Button insertar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        setContentView(R.layout.insertar_activity);
        //toolbar =(Toolbar) findViewById(R.id.toolbar);

        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutApellidos = (TextInputLayout) findViewById(R.id.input_layout_apellidos);
        inputLayoutUsuario = (TextInputLayout) findViewById(R.id.input_layout_usuarios);
        inputLayoutFechaIngreso = (TextInputLayout) findViewById(R.id.input_layout_fecha_ingreso);
        inputLayoutEstatus = (TextInputLayout) findViewById(R.id.input_layout_estatus);
        inputLayoutTipo = (TextInputLayout) findViewById(R.id.input_layout_tipo);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);

        insertar_nombre = (EditText) findViewById(R.id.input_name);
        insertar_apellidos = (EditText) findViewById(R.id.input_apellidos);
        insertar_usuario = (EditText) findViewById(R.id.input_usuarios);
        insertar_fecha_registro = (EditText) findViewById(R.id.input_fecha_ingreso);
        insertar_estatus = (EditText) findViewById(R.id.input_estatus);
        insertar_tipo = (EditText) findViewById(R.id.input_tipo);
        insertar_password = (EditText) findViewById(R.id.input_password);


        insertar = (Button) findViewById(R.id.btn_insertar);

        //Insertamos los datos de la persona.

        insertar_nombre.addTextChangedListener(new MyTextWatcher(insertar_nombre));
        insertar_apellidos.addTextChangedListener(new MyTextWatcher(insertar_apellidos));
        insertar_usuario.addTextChangedListener(new MyTextWatcher(insertar_usuario));
        insertar_fecha_registro.addTextChangedListener(new MyTextWatcher(insertar_fecha_registro));
        insertar_estatus.addTextChangedListener(new MyTextWatcher(insertar_estatus));
        insertar_tipo.addTextChangedListener(new MyTextWatcher(insertar_tipo));
        insertar_password.addTextChangedListener(new MyTextWatcher(insertar_password));

        insertar.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                submitForm();
                if (!insertar_nombre.getText().toString().trim().equalsIgnoreCase("") ||
                       !insertar_apellidos.getText().toString().trim().equalsIgnoreCase("") ||
                       !insertar_usuario.getText().toString().trim().equalsIgnoreCase("") ||
                       !insertar_fecha_registro.getText().toString().trim().equalsIgnoreCase("") ||
                       !insertar_estatus.getText().toString().trim().equalsIgnoreCase("") ||
                       !insertar_tipo.getText().toString().trim().equalsIgnoreCase("") ||
                       !insertar_password.getText().toString().trim().equalsIgnoreCase(""))

                       new Insertar(InsertActivity.this).execute();

                else
                    Toast.makeText(InsertActivity.this, "Existe información por ingresar", Toast.LENGTH_LONG).show();



            }

        });



    }

    //Validando formulario

    private void submitForm(){
        if (!validateNombre()){
            return;
        }

        if (!validateApellidos()){
            return;
        }

        if (!validateUsuario()){
            return;
        }

        if (!validateFechaIngreso()){
            return;
        }

        if (!validateEstatus()){
            return;
        }

        if (!validateTipo()){
            return;
        }

        if (!validatePassword()){
            return;
        }

        Toast.makeText(getApplicationContext(), "Muchas Gracias!", Toast.LENGTH_SHORT).show();

    }


    private boolean validateNombre(){
        if (insertar_nombre.getText().toString().trim().isEmpty()){
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(insertar_nombre);
            return false;
        } else{
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateApellidos(){
        if (insertar_apellidos.getText().toString().trim().isEmpty()){
            inputLayoutApellidos.setError(getString(R.string.err_msg_apellido));
            requestFocus(insertar_apellidos);
            return false;
        }else{
            inputLayoutApellidos.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateUsuario(){
        if (insertar_usuario.getText().toString().trim().isEmpty()){
            inputLayoutUsuario.setError(getString(R.string.err_msg_usuario));
            requestFocus(insertar_usuario);
            return false;
        }else{
            inputLayoutUsuario.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateFechaIngreso(){
        if (insertar_fecha_registro.getText().toString().trim().isEmpty()){
            inputLayoutFechaIngreso.setError(getString(R.string.err_msg_fechaingreso));
            requestFocus(insertar_fecha_registro);
            return false;
        }else{
            inputLayoutFechaIngreso.setErrorEnabled(false);
        }

        return true;

    }

    private boolean validateEstatus(){
        if (insertar_estatus.getText().toString().trim().isEmpty()){
            inputLayoutEstatus.setError(getString(R.string.err_msg_estatus));
            requestFocus(insertar_estatus);
            return false;
        }else{
            inputLayoutEstatus.setErrorEnabled(false);
        }

        return true;

    }

    private boolean validateTipo(){
        if (insertar_tipo.getText().toString().trim().isEmpty()){
            inputLayoutTipo.setError(getString(R.string.err_msg_tipo));
            requestFocus(insertar_tipo);
            return false;
        }else{
            inputLayoutTipo.setErrorEnabled(false);
        }

        return true;

    }

    private boolean validatePassword() {
        if (insertar_password.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(insertar_password);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }




    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateNombre();
                    break;
                case R.id.input_apellidos:
                    validateApellidos();
                    break;
                case R.id.input_usuarios:
                    validateUsuario();
                    break;
                case R.id.input_fecha_ingreso:
                    validateFechaIngreso();
                    break;
                case R.id.input_estatus:
                    validateEstatus();
                    break;
                case R.id.input_tipo:
                    validateTipo();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
            }
        }
    }

    //Inserta los datos de las Personas en el servidor.
    private boolean insertar(){

        OutputStream os = null;
        InputStream input = null;
        HttpURLConnection conn = null;
        try {
            //constants
            URL url = new URL("http://robertoadvance.dreamhosters.com/Connections/Android/insertar_usuario.php");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("nombre",insertar_nombre.getText().toString().trim());
            jsonObject.put("apellidos",insertar_apellidos.getText().toString().trim());
            jsonObject.put("nom_usuario",insertar_usuario.getText().toString().trim());
            jsonObject.put("fecha_registro",insertar_fecha_registro.getText().toString().trim());
            jsonObject.put("estatus",insertar_estatus.getText().toString().trim());
            jsonObject.put("tipo", insertar_tipo.getText().toString().trim());
            jsonObject.put("password", insertar_password.getText().toString().trim());
            String message = jsonObject.toString();

            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout( 10000 /*milliseconds*/ );
            conn.setConnectTimeout( 15000 /* milliseconds */ );
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setFixedLengthStreamingMode(message.getBytes().length);

            //make some HTTP header nicety
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");

            //open
            conn.connect();

            //setup send
            os = new BufferedOutputStream(conn.getOutputStream());
            os.write(message.getBytes());
            //clean up
            os.flush();

            //do somehting with response
            //String contentAsString = readIt(is,len);
            int response_code = conn.getResponseCode();

            //check if successful connection made
            if (response_code == HttpURLConnection.HTTP_OK) {

                //read data sent from server
                input = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);

                }

                //Analizar respuesta antes de decir true o false
                //Pass data to onPostExecute method
                return true;
            } else {
                return false;
            }



        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            //clean up
            try {
                os.close();
                input.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            conn.disconnect();
        }

        //Prueba
 /*       try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            httpclient.execute(httppost);
            return true; //No se está verificando respuesta!!!
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        } catch (ClientProtocolException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }*/
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
                        InsertActivity.this.finish();
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