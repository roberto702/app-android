package cl.estfel.siac;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static cl.estfel.siac.MenuSuperior.current;
import static cl.estfel.siac.R.id.listView1;


public class DisplayClasesListView extends AppCompatActivity {
    Activity cla = this;
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ClasesAdapter clasesAdapter;
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_clases);
        listView = (ListView)findViewById(listView1);

        clasesAdapter = new ClasesAdapter(this,R.layout.row_clases);
        listView.setAdapter(clasesAdapter);

        json_string = Data.JSONClases;

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("nombre_clase");

            int count = 0;
            String nombreClaseClase;
            String idClase;

            while (count < jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                nombreClaseClase = JO.getString("nombre_clase");

                idClase = JO.getString("id_clase");
                //fechaCreacionClase = JO.getString("fecha_clase");
                Clases clases = new Clases(nombreClaseClase,idClase);
                clasesAdapter.add(clases);
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();

        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(cla,"String:" + json_string,Toast.LENGTH_LONG).show();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(),"Presionaste: " + position,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(current, DisplayAsistenciaListviewCheckbox.class);
                Clases oClases = (Clases) clasesAdapter.getItem(position);
                intent.putExtra("id_clase",oClases.getIdClase_json());
                current.startActivity(intent);


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        return MenuSuperior.instancia.onCreateOptionsMenu(menu, this);

    }
    @Override
    public void onResume(){
        super.onResume();

        //Intentar llamar OnOptionsItemSelected con MenuOption2
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return MenuSuperior.instancia.onOptionsItemSelected(item, this);
    }



}
