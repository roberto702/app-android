package cl.estfel.siac;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import android.widget.AdapterView;
import android.widget.ListView;


//import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static cl.estfel.siac.MenuSuperior.current;
import static cl.estfel.siac.R.id.listview;


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
        listView = (ListView)findViewById(listview);

        usuariosAdapter = new UsuariosAdapter(this,R.layout.row_layout);
        listView.setAdapter(usuariosAdapter);

        json_string = Data.JSONUsuarios;

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("USER");

            int count = 0;
            String nombre, apellidos, usuarios_listview;

            while (count < jsonArray.length()){
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

        FloatingActionButton botonflotante = (FloatingActionButton) findViewById(R.id.botonflotante);
        botonflotante.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(),"Agregar Nuevo Usuario",Toast.LENGTH_LONG).show();
                //Iniciar actividad de ingreso de usuarios
                Intent intent = new Intent(current,InsertActivity.class);
                current.startActivity(intent);
                current.finish();

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String item = ((TextView)view).getText().toString();
                //Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"Presionaste: " + position,Toast.LENGTH_LONG).show();
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
