package com.example.api;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MoveListView extends AppCompatActivity {

    ListView lista1;
    ListView lista2;
    TextView textoAdd;

    ArrayList<String> list1 = new ArrayList<String>();
    ArrayList<String> list2 = new ArrayList<String>();;

    ArrayAdapter<String> adaptadorLista1;
    ArrayAdapter<String> adaptadorLista2;

    int indice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_list_view);

        textoAdd = findViewById(R.id.text_input);
        lista1 = findViewById(R.id.list1);
        lista2 = findViewById(R.id.list2);

        adaptadorLista1 = new ArrayAdapter<String>(this, R.layout.list_i,list1);
        lista1.setAdapter(adaptadorLista1);
        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                indice = i;
            }
        });

        lista1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int po = i;
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(MoveListView.this);
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿Elimina este elemento?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        list1.remove(po);
                        adaptadorLista1.notifyDataSetChanged();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialogo1.show();



                return false;
            }
        });

        adaptadorLista2 = new ArrayAdapter<String>(this, R.layout.list_i,list2);
        lista2.setAdapter(adaptadorLista2);
        lista2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        list1.add("Hola");
        list2.add("Bruh");




    }

    public void agregarItem(View view) {
        list1.add(textoAdd.getText().toString());
        adaptadorLista1.notifyDataSetChanged();
        textoAdd.setText("");
        Toast.makeText(this, "" + indice, Toast.LENGTH_SHORT).show();
    }

    public void moverItem(View view) {
        String mover;
        mover = list1.get(indice);
        list2.add(mover);
        adaptadorLista1.notifyDataSetChanged();
        adaptadorLista2.notifyDataSetChanged();
    }
}
