package com.example.smartlocker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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


public class MainActivity extends AppCompatActivity {
    EditText editTextcodelogic;
    Button buttlogin;
    private ArrayList<Employees> Arrayname=new ArrayList<>();
    private    String url="http://192.168.1.102/Android/getname.php";
    String code;
    ArrayList<String> A=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextcodelogic = (EditText) findViewById(R.id.edt_codelogic);
        buttlogin = (Button) findViewById(R.id.btn_login);
        //code="30363134343132303630";
        buttlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,editTextcodelogic.getText().toString(),Toast.LENGTH_LONG).show();
                Getdata(url);
            }
        });
    }
    private void Getdata(String url){
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject object=response.getJSONObject(i);
                        if(editTextcodelogic.getText().toString().equals(object.getString("CodeLogic")))  {
                            Intent intent=new Intent(MainActivity.this,User.class);
                            intent.putExtra("Name",object.getString("Name").toString());
                            startActivity(intent);

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Lỗi",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

}