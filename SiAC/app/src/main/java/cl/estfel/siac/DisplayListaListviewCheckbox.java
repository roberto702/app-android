package cl.estfel.siac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
import android.widget.ListView;
//import android.widget.Toast;
//import android.widget.AdapterView.OnItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import cl.estfel.siac.Asistencia;
//import cl.estfel.siac.CustomArrayAdapter;
//import cl.estfel.siac.R;
//import cl.estfel.siac.Row;

public class DisplayListaListviewCheckbox extends AppCompatActivity {
    Activity me;
    List<Row> rows;
    ArrayList<Asistencia> asistencias;
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    String id_clase;
    CustomArrayAdapter adapter;

    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        listView = (ListView) findViewById(android.R.id.list);
        id_clase = getIntent().getStringExtra("id_clase");


        rows = new ArrayList<Row>();


        Log.d("rows", "len" + rows.size());

        adapter = new CustomArrayAdapter(this,rows);
        listView.setAdapter(adapter);


        new ListarAsistenciaTask().execute();

    }

    class ListarAsistenciaTask extends AsyncTask<Void, Void, String> {
        String json_url;

        @Override
        protected  void onPreExecute(){
            adapter.clear();
            json_url = "http://robertoadvance.dreamhosters.com/Connections/Android/obtener_claseporid.php?id_clase="+ id_clase;

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
            Data.JSONAsistencia =  result;
            try{
                jsonObject = new JSONObject(Data.JSONAsistencia);
                jsonArray = jsonObject.getJSONArray("prueba");

                int count = 0;

                Row row = null;
                Log.d("CheckBox" ,"length"+ jsonArray.length());

                asistencias = new ArrayList<>();

                while (count < jsonArray.length()){
                    JSONObject JO = jsonArray.getJSONObject(count);
                    Asistencia asistencia = new Asistencia();
                    asistencia.setNombreAlumno_json(JO.getString("nombre"));
                    asistencia.setApellidoAlumno_json(JO.getString("apellidos"));


                    row = new Row();
                    row.setTitle(asistencia.getNombreAlumno_json());
                    row.setSubtitle(asistencia.getApellidoAlumno_json());
                    row.setChecked(false);

                    rows.add(row);


                    asistencias.add(asistencia);



                    count++;
                }






            }catch (JSONException ex){
                ex.printStackTrace();

            }

            adapter.notifyDataSetChanged();




        }
    }

}
