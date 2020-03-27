package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExampleListView extends AppCompatActivity {

    TextView textView;
    ListView listView;

    String nombres [] = {"lalo", "eliu", "arellano", "cesar", "frias", "andrea"};
    String hobbie [] = {"la guitarra", "programar", "el minecraft", "mandarle mensajes al profe", "el gimnasio", "tomar"};

    ArrayList<String> nom;
    ArrayAdapter<String> adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_list_view);

        textView = findViewById(R.id.textView);
        listView = findViewById(R.id.listView);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,R.layout.list_i,nombres);
        listView.setAdapter(adaptador);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                textView.setText("a " + listView.getItemAtPosition(i) + " le gusta " + hobbie[i]);
            }
        });
    }


}
