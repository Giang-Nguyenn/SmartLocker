package com.example.smartlocker;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class User extends AppCompatActivity {
    GridView gridView;
    TextView txtuser;
    TextView txtsotu;
    EditText edttimtu;
    Button btntimtu;
    ArrayList<Employees> ArrayEmployees=new ArrayList<Employees>();
    ArrayList<String> arrayList=new ArrayList<>();
    LockerAdapter adapter;
    String url="http://192.168.1.102/Android/hi.php";
    Integer count=0;

    ArrayList<Integer> A=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        gridView=(GridView) findViewById(R.id.gr_view);
        txtuser=(TextView)findViewById(R.id.txt_user);
        txtsotu=(TextView)findViewById(R.id.txt_sotu);
        edttimtu=(EditText) findViewById(R.id.edt_timtu);
        btntimtu=(Button) findViewById(R.id.btn_timtu);
        Intent intent=getIntent();
        String a=intent.getStringExtra("Name");
        txtuser.setText(a);
        Getdata(url);
        //Toast.makeText(User.this,ArrayEmployees.get(1).getLabel().toString(),Toast.LENGTH_LONG).show();
        //final int size = ArrayEmployees.size();
        //txtsotu.setText("Bạn có "+ count +" tủ");
        adapter=new LockerAdapter(this, R.layout.locker, ArrayEmployees);
        gridView.setAdapter(adapter);
        btntimtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=edttimtu.getText().toString();
                for(int i=0;i<ArrayEmployees.size();i++){
                    String s2=ArrayEmployees.get(i).getLabel().toString();
                    if(s1.equals(s2)){
                        Employees e=ArrayEmployees.get(i);
                        ArrayEmployees.clear();
                        ArrayEmployees.add(e);
                        break;
                    }

                }
                adapter.notifyDataSetChanged();

                }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Employees e=ArrayEmployees.get(position);
                InfLocker(e.getLabel().toString(),e.getPosition().toString(),e.getNumber().toString());
            }
        });

    }
    public void Getdata(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0;i<response.length();i++){
                    txtsotu.setText("Có "+response.length()+" tủ");
                    try {
                        JSONObject object=response.getJSONObject(i);
                        ArrayEmployees.add(new Employees(
                                object.getString("Label"),
                                object.getString("LockerId"),
                                object.getString("Number"),
                                object.getString("Position"),
                                object.getString("Position"),
                                object.getString("Position")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(User.this,"response.toString()",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    private void InfLocker(String sotu,String vitri,String number){
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_custom);
        TextView editsotu=(TextView) dialog.findViewById(R.id.txt_sotu);
        TextView editnumber=(TextView) dialog.findViewById(R.id.txt_number);
        TextView editvitri=(TextView) dialog.findViewById(R.id.txt_vitri);
        editsotu.setText("Tủ số: "+sotu);
        editnumber.setText("Number: "+number);
        editvitri.setText("Vị trí: "+vitri);
        dialog.show();


    }
}
