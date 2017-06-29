package cl.estfel.siac;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by robma on 05/05/2017.
 */

public class MenuSuperior {
    static Activity current;
    static MenuSuperior instancia = new MenuSuperior();

    public boolean onCreateOptionsMenu(Menu menu, Activity current){
        current.getMenuInflater().inflate(R.menu.androidstudiofaqs, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item, Activity current) {
        MenuSuperior.current = current;
        switch (item.getItemId()) {

            case R.id.MenuOpcion1:
                Toast.makeText(current, "Asistencia", Toast.LENGTH_SHORT).show();
                new AsistenciaTask().execute();
                return true;

            case R.id.MenuOpcion2:
                Toast.makeText(current, "Usuarios", Toast.LENGTH_SHORT).show();
                new UsuariosTask().execute();

                return true;

            case R.id.MenuOpcion3:
                Toast.makeText(current, "Cerrando la Aplicación", Toast.LENGTH_SHORT).show();
                current.finish();
                return true;
        }
        return false;
    }

    public static void Menu1Success(){
        Intent intent = new Intent(current, DisplayClasesListView.class);
        intent.putExtra("id_alumno","1");
        current.startActivity(intent);
        current.finish();
    }

    public static void Menu2Success(){
        Intent intent = new Intent(current,DisplayUsuariosListView.class);
        current.startActivity(intent);
        current.finish();
    }

    private class UsuariosTask extends AsyncTask<Void, Void, String> {
        String json_url;

        @Override
        protected  void onPreExecute(){
            json_url = "http://robertoadvance.dreamhosters.com/Connections/Android/obtener_usuarios.php";

        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);

                }

                //Pass data to onPostExecute method
                return (result.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onProgressUpdate(Void... values){

            super.onProgressUpdate(values);
        }

        @Override
        protected  void onPostExecute (String result) {
            //Se procesa el jason y se va agregando a Alumnos
            //Data.Alumnos.add("Juan");
            //Data.Alumnos.add("Pedro");
            //Data.Alumnos.add("Diego");
            Data.JSONUsuarios =  result;
            Menu2Success();
        }
        //      String aVoid;
        //     super.onPostExecute(aVoid);
        //    TextView textView = (TextView) findViewById(R.id.textView);
        //   textView.setText(result);

        //}
    }

    private class AsistenciaTask extends AsyncTask<Void, Void, String> {
        String json_url;

        @Override
        protected  void onPreExecute(){
            json_url = "http://robertoadvance.dreamhosters.com/Connections/Android/obtener_listadodeclases.php";

        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);

                }

                //Pass data to onPostExecute method
                return (result.toString());

            } catch (IOException e) {
                e.printStackTrace();
                //Toast.makeText(current, "Error en la conexión", Toast.LENGTH_SHORT).show();
            }
            return "";
        }

        @Override
        protected void onProgressUpdate(Void... values){

            super.onProgressUpdate(values);
        }

        @Override
        protected  void onPostExecute (String result) {
            Data.JSONClases =  result;
            Menu1Success();
        }
    }

}
