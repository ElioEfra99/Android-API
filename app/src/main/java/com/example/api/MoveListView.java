package com.example.api;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MoveListView extends AppCompatActivity {

    ListView lista1_view;
    ListView lista2_view;
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
        lista1_view = findViewById(R.id.list1);
        lista2_view = findViewById(R.id.list2);

        adaptadorLista1 = new ArrayAdapter<String>(this, R.layout.list_i,list1);
        lista1_view.setAdapter(adaptadorLista1);
        lista1_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                indice = i;
            }
        });

        lista1_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
        lista2_view.setAdapter(adaptadorLista2);
        lista2_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        lista2_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
                        list2.remove(po);
                        adaptadorLista2.notifyDataSetChanged();
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

        String archivos [] = fileList();

        if (archivoExiste(archivos, "bitacora1.txt")) {
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("bitacora1.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();

                while (linea != null) {
                    list1.add(linea);
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
            } catch (IOException e) {

            }
        }

        if (archivoExiste(archivos, "bitacora2.txt")) {
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("bitacora2.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();

                while (linea != null) {
                    list2.add(linea);
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
            } catch (IOException e) {

            }
        }



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

    private boolean archivoExiste(String archivos[], String nombreArchivo) {
        for (int i = 0; i < archivos.length; i++)
            if (nombreArchivo.equals(archivos[i]))
                return true;
        return false;
    }

    public void GuardarArchivo1(View view) {
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("bitacora1.txt", Activity.MODE_PRIVATE));
            for (int i = 0; i < list1.size(); i ++) {
                archivo.write(list1.get(i));
                archivo.write("\n");
            }
            archivo.flush();
            archivo.close();
        } catch (IOException e) {

        }
        Toast.makeText(this,"Lista 1 guardada con éxito", Toast.LENGTH_LONG).show();
    }

    public void GuardarArchivo2(View view) {
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("bitacora2.txt", Activity.MODE_PRIVATE));
            for (int i = 0; i < list2.size(); i ++) {
                archivo.write(list2.get(i));
                archivo.write("\n");
            }
            archivo.flush();
            archivo.close();
        } catch (IOException e) {

        }
        Toast.makeText(this,"Lista 2 guardada con éxito", Toast.LENGTH_LONG).show();
    }
}
