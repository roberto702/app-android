package cl.estfel.siac;

import android.content.Context;
//import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class UsuariosAdapter extends ArrayAdapter {

    List list = new ArrayList();


    public UsuariosAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(Usuarios object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Object getItem(int position){

        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View row;
        row = convertView;
        UsuariosHolder usuariosHolder;

        if(row == null)
        {

            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            usuariosHolder = new UsuariosHolder();
            usuariosHolder.txnombre = (TextView) row.findViewById(R.id.tx_nombre);
            usuariosHolder.txapellidos = (TextView) row.findViewById(R.id.tx_apellidos);
            usuariosHolder.txusuarios = (TextView) row.findViewById(R.id.tx_usuario);
            row.setTag(usuariosHolder);

        }

        else
            {
                usuariosHolder = (UsuariosHolder) row.getTag();

            }

        Usuarios usuarios = (Usuarios) this.getItem(position);
        usuariosHolder.txnombre.setText(usuarios.getNombre_json());
        usuariosHolder.txapellidos.setText(usuarios.getApellidos_json());
        usuariosHolder.txusuarios.setText(usuarios.getUsuario_json());

        return row;
    }

    static class UsuariosHolder{

        TextView txnombre, txapellidos,txusuarios;
    }
}
