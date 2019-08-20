package ttokk.com.swproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ttokk.com.swproject.model.sqliteDB.Alcohol;
import ttokk.com.swproject.R;

public class AlcoholAdapter extends BaseAdapter {

    Context context;
    ArrayList<Alcohol> wl;
    int layout;
    LayoutInflater inf;

    public AlcoholAdapter(Context context, ArrayList<Alcohol> wl, int layout) {
        this.context = context;
        this.wl = wl;
        this.layout = layout;
        inf = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return wl.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return wl.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inf.inflate(layout, null);
        }
        TextView output_alcochol = (TextView) convertView.findViewById(R.id.output_alcohol);
        TextView alcohol_date = (TextView) convertView.findViewById(R.id.alcohol_date);

        Alcohol b = wl.get(position);
        output_alcochol.setText(b.alcohol_data + "");
        alcohol_date.setText(b.date);

        return convertView;
    }
}
