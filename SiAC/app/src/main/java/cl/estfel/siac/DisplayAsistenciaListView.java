package cl.estfel.siac;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static cl.estfel.siac.R.id.listview;

public class DisplayAsistenciaListView extends AppCompatActivity{

    Activity asis = this;
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    AsistenciaAdapter asistenciaAdapter;
    ListView listViewAsistencia;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asistencia_main);
        listViewAsistencia = (ListView) findViewById(listview);

        asistenciaAdapter = new AsistenciaAdapter(this,R.layout.row_asistencia);
        listViewAsistencia.setAdapter(asistenciaAdapter);

        json_string = Data.JSONAsistencia;

        try {

            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("nombre_clase");

            int count = 0;
            String nombre_claseAsistencia, nombre_alumnoAsistencia, fecha_claseAsistencia;

            while (count < jsonArray.length()) {
                JSONObject JO = jsonArray.getJSONObject(count);
                nombre_claseAsistencia = JO.getString("nombre_clase");
                nombre_alumnoAsistencia = JO.getString("nombre_alumno");
                fecha_claseAsistencia = JO.getString("fecha_asistencia");
                Asistencia asistencia = new Asistencia(nombre_claseAsistencia, nombre_alumnoAsistencia, fecha_claseAsistencia);
                asistenciaAdapter.add(asistencia);
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();

        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(asis,"String:" + json_string,Toast.LENGTH_LONG).show();
        }
    }

}








