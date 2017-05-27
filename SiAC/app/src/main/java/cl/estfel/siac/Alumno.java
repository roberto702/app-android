package cl.estfel.siac;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

/**
 * Created by robma on 26/05/2017.
 */

public class Alumno extends ArrayAdapter {


    public Alumno(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }
}
