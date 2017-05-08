package cl.estfel.siac;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DisplayListView extends AppCompatActivity {


    Activity me = this;
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    UsuariosAdapter usuariosAdapter;
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);
        listView = (ListView)findViewById(R.id.listview);

        usuariosAdapter = new UsuariosAdapter(this,R.layout.row_layout);
        listView.setAdapter(usuariosAdapter);


        json_string = Data.JSONUsuarios;

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("USER");

            int count = 0;
            String nombre, apellidos, usuarios_listview;

            while (count<jsonObject.length()){

            JSONObject JO = jsonArray.getJSONObject(count);
                nombre = JO.getString("NOMBRE");
                apellidos = JO.getString("APELLIDOS");
                usuarios_listview =JO.getString("USER");
                Usuarios usuarios = new Usuarios(nombre, apellidos,usuarios_listview);
                usuariosAdapter.add(usuarios);
                count++;


            }

        } catch (JSONException e) {
            e.printStackTrace();

        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(me,"String:" + json_string,Toast.LENGTH_LONG).show();
        }
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
