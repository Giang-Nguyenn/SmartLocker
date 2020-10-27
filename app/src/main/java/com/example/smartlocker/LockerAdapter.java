package com.example.smartlocker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LockerAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Employees> ArrayEmployees;

    public LockerAdapter(Context context, int layout, ArrayList<Employees> arrayEmployees) {
        this.context = context;
        this.layout = layout;
        ArrayEmployees = arrayEmployees;
    }

    @Override
    public int getCount() {
        return ArrayEmployees.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);
        TextView txtsotu=(TextView) convertView.findViewById(R.id.txt_sotu);
        TextView txtvitri=(TextView) convertView.findViewById(R.id.txt_vitri);
        Employees employees=ArrayEmployees.get(position);
        txtsotu.setText(employees.getLabel().toString());
        txtvitri.setText("Vị trí: "+employees.getPosition().toString());
        return convertView;
    }
}
