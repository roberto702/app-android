package cl.estfel.siac;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ClasesAdapter extends ArrayAdapter{

    List list = new ArrayList();
    public ClasesAdapter(Context context, int resource) {

        super(context, resource);
    }

    public void add(Clases object){

        super.add(object);
        list.add(object);

    }

    @Override
    public int getCount(){
        return super.getCount();
    }

    @Override
    public Object getItem(int position){

        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row = convertView;
        ClasesAdapter.ClasesHolder clasesHolder;

        if (row == null) {

            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row = layoutInflater.inflate(R.layout.row_clases, parent, false);
            clasesHolder = new ClasesAdapter.ClasesHolder();
            clasesHolder.nombre_de_la_clase = (TextView) row.findViewById(R.id.tx_nombre_de_la_clase);
            clasesHolder.fecha_creacion_clase = (TextView) row.findViewById(R.id.tx_fecha_creacion_clase);
            row.setTag(clasesHolder);

        } else {

            clasesHolder = (ClasesAdapter.ClasesHolder) row.getTag();

        }

        Clases clases = (Clases) this.getItem(position);
        clasesHolder.nombre_de_la_clase.setText(clases.getNombredelaClase_json());
        clasesHolder.fecha_creacion_clase.setText(clases.getFechacreacionClase_json());

        return row;
    }

    static class ClasesHolder{

        TextView nombre_de_la_clase, fecha_creacion_clase;
    }


}
