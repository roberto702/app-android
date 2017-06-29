package cl.estfel.siac;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import cl.estfel.siac.Asistencia;
//import cl.estfel.siac.AsistenciaAdapter;
//import cl.estfel.siac.R;
//import cl.estfel.siac.Row;

public class DisplayAsistenciaListviewCheckbox extends AppCompatActivity {
    Activity me;
    List<Row> rows;
    ArrayList<Asistencia> asistencias;
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    String id_clase;
    AsistenciaAdapter adapter;
    Button guardar;

    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        listView = (ListView) findViewById(android.R.id.list);
        id_clase = getIntent().getStringExtra("id_clase");
        guardar = (Button) findViewById(R.id.button_guardar);


        rows = new ArrayList<Row>();


        Log.d("rows", "len" + rows.size());

        adapter = new AsistenciaAdapter(this,rows);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DisplayAsistenciaListviewCheckbox.this,rows.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });


        new ListarAsistenciaTask().execute();

        //este es codigo agregado en caso de falla se debe borrar

        guardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplication(),"Presionaste boton guardar", Toast.LENGTH_SHORT).show();
                new EnviarDatos(DisplayAsistenciaListviewCheckbox.this).execute();
            }
        });

        //hasta aquí debo borrar en caso de falla

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
                    asistencia.setRutAlumno_json(JO.getString("rut"));
                    asistencia.setIdclaseAlumno_json(JO.getString("id_clase"));


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


    //esta clase se debe borrar en caso que no funcione
    class EnviarDatos extends AsyncTask<String, String, String>{

        //ojo para borrar en caso de
        @Override
        protected  void onPreExecute(){
            //adapter.clear();
        }
        //ojo para borrar en caso de
        private Activity contexto;

        EnviarDatos(Activity contex){
            this.contexto = contex;
        }


        @Override
        protected String doInBackground(String... params) {
            if (datos()){
                contexto.runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        Toast.makeText(contexto, "Asistencia enviada con éxito", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            return null;
        }

        private boolean datos(){

            //ojo borrar a qui 23:08
            OutputStream os = null;
            InputStream input = null;
            HttpURLConnection conn = null;
            //hasta aqui 23:08

            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Santiago"));

            try {
                URL url = new URL("http://robertoadvance.dreamhosters.com/Connections/Android/insertar_asistencia.php");
                JSONObject DataToSendJSon = new JSONObject();
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonAlumno = new JSONObject();
                //for, sacando todos los alumnos de la lista, los rows de la lista
                for (int i = 0; i < jsonAlumno.length() ; i++)
                {
                    jsonAlumno.put("rut_asistencia", asistencias.get(i).getRutAlumno_json());
                    if(asistencias.get(i).asiste)
                        jsonAlumno.put("estado", 1);
                    else
                        jsonAlumno.put("estado", 0);

                    //acá va el estado 0= ausente y 1=presente

                    jsonArray.put(jsonAlumno);
                    //cierra el for
                }
                DataToSendJSon.put("fecha_asistencia", calendar.getTime());
                DataToSendJSon.put("id_curso", asistencias.get(0).getIdclaseAlumno_json());
                //DataToSendJSon.put("estado") No va acá
                DataToSendJSon.put("asistencia", jsonArray);
                String message = DataToSendJSon.toString();

                Log.d("message" ,"length"+ DataToSendJSon.length());

                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout( 10000 /*milliseconds*/);
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
            return false;
        }


    }

}