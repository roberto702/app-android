package cl.estfel.siac;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;


/**
 * Custom adapter - "View Holder Pattern".
 *
 *
 *
 */

public class CustomArrayAdapter extends ArrayAdapter<Row> implements
        View.OnClickListener {

    private LayoutInflater layoutInflater;

    public CustomArrayAdapter(Context context, List<Row> objects) {
        super(context,0, objects);
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        // holder pattern
        Holder holder;
        if (convertView == null) {
            holder = new Holder();

            convertView = layoutInflater.inflate(R.layout.listview_row, parent, false);
            holder.setTextViewTitle((TextView) convertView
                    .findViewById(R.id.textViewTitle));
            holder.setTextViewSubtitle((TextView) convertView
                    .findViewById(R.id.textViewSubtitle));
            holder.setCheckBox((CheckBox) convertView
                    .findViewById(R.id.checkBox));
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        final Row row = getItem(position);
        assert row != null;
        holder.getTextViewTitle().setText(row.getTitle());
        holder.getTextViewSubtitle().setText(row.getSubtitle());
        holder.getCheckBox().setTag(position);
        holder.getCheckBox().setChecked(row.isChecked());
        holder.getCheckBox().setOnClickListener(this);

        changeBackground(getContext(), holder.getCheckBox());

        return convertView;
    }


    @Override
    public void onClick(View v) {

        CheckBox checkBox = (CheckBox) v;
        int position = (Integer) v.getTag();
        getItem(position).setChecked(checkBox.isChecked());

        changeBackground(CustomArrayAdapter.this.getContext(), checkBox);
    }


    /**
     * Set the background of a row based on the value of its checkbox value.
     * Checkbox has its own style.
     */
    @SuppressWarnings("deprecation")
    private void changeBackground(Context context, CheckBox checkBox) {
        View row = (View) checkBox.getParent();
        Drawable drawable;
        if (checkBox.isChecked()) {
            drawable = context.getResources().getDrawable(
                    R.drawable.listview_selector_checked);
        } else {
            drawable = context.getResources().getDrawable(
                    R.drawable.listview_selector);
        }
        row.setBackgroundDrawable(drawable);
    }

    private static class Holder {
        TextView textViewTitle;
        TextView textViewSubtitle;
        CheckBox checkBox;

        public TextView getTextViewTitle() {
            return textViewTitle;
        }

        public void setTextViewTitle(TextView textViewTitle) {
            this.textViewTitle = textViewTitle;
        }

        public TextView getTextViewSubtitle() {
            return textViewSubtitle;
        }

        public void setTextViewSubtitle(TextView textViewSubtitle) {
            this.textViewSubtitle = textViewSubtitle;
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }

        public void setCheckBox(CheckBox checkBox) {
            this.checkBox = checkBox;
        }

    }

}