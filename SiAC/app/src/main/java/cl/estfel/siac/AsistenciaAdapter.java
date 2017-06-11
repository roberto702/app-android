package cl.estfel.siac;

/**
 * Created by robma on 10/06/2017.
 */
//import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
//import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class AsistenciaAdapter extends ArrayAdapter {

    List listAsistencia = new ArrayList();

    public AsistenciaAdapter(Context context, int resource) {

        super(context, resource);
    }

    public void add(Asistencia object){

        super.add(object);
        listAsistencia.add(object);

    }

    @Override
    public int getCount(){
        return super.getCount();
    }

    @Override
    public Object getItem(int position){

        return listAsistencia.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row = convertView;
        AsistenciaAdapter.AsistenciaHolder asistenciaHolder;

        if (row == null) {

            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row = layoutInflater.inflate(R.layout.row_asistencia, parent, false);
            asistenciaHolder = new AsistenciaHolder();
            asistenciaHolder.nomClaseAsistencia = (TextView) row.findViewById(R.id.tx_nomClaseAsistencia);
            asistenciaHolder.nomAsistencia = (TextView) row.findViewById(R.id.tx_nomAsistencia);
            asistenciaHolder.fechaAsistencia = (TextView) row.findViewById(R.id.tx_fechaAsistencia);
            CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
            row.setTag(asistenciaHolder);

        } else {
            asistenciaHolder = (AsistenciaAdapter.AsistenciaHolder) row.getTag();

        }

        Asistencia asistencia = (Asistencia) this.getItem(position);
        asistenciaHolder.nomClaseAsistencia.setText(asistencia.getNombreClaseAsistencia_json());
        asistenciaHolder.nomAsistencia.setText(asistencia.getNombreAlumno_json());
        asistenciaHolder.fechaAsistencia.setText(asistencia.getFechaAsistencia_json());
        //if (asistencia[position].get

        return row;
    }

    static class AsistenciaHolder{

        TextView nomClaseAsistencia, nomAsistencia, fechaAsistencia;
    }

}
