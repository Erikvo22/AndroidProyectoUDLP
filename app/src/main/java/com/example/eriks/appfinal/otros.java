package com.example.eriks.appfinal;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class otros extends Activity implements View.OnClickListener{

    Bundle bundle;
    String img, img2, img3;
    CheckBox c1,c2,c3;
    boolean ch1 = false, ch2 = false, ch3 = false;
    String [] eleccion1 = {""};
    String s1 ="",s2="",s3="";
    String precio = "",precio2 = "",precio3 = "";
    int und1 = 0, und2 = 0, und3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otros);
        bundle = getIntent().getExtras();
        if (bundle == null){
            bundle = new Bundle();
        }

        Button volver = (Button)findViewById(R.id.volver);
        Button facturar = (Button)findViewById(R.id.cesta);
        ImageButton borrar = (ImageButton) findViewById(R.id.borrar);
        borrar.setOnClickListener(this);
        volver.setOnClickListener(this);
        facturar.setOnClickListener(this);

        borrar.setImageResource(R.drawable.x);


        //BD
        BD acceso = new BD(this);
        SQLiteDatabase BD = acceso.getWritableDatabase();
        ContentValues DatosInsertar = new ContentValues();

        //Insertamos1
        DatosInsertar.put("COD", 3);
        DatosInsertar.put("NOMBRE","BALÓN");
        DatosInsertar.put("PRECIO",25);
        DatosInsertar.put("IMG","R.drawable.balon");

       long err = BD.insert("PRODUCTOS", null, DatosInsertar);
        if (err==-1){
            //  Toast.makeText(this, "Ya estaba creada la fila", Toast.LENGTH_LONG).show();
        }

        //Insertamos2
        DatosInsertar.put("COD",6);
        DatosInsertar.put("NOMBRE","GORRA");
        DatosInsertar.put("PRECIO",20);
        DatosInsertar.put("IMG","R.drawable.gorra");

        err = BD.insert("PRODUCTOS", null, DatosInsertar);
        if (err==-1){
            // Toast.makeText(this, "Ya estaba creada la fila", Toast.LENGTH_LONG).show();
        }

        //Insertamos3
        DatosInsertar.put("COD",9);
        DatosInsertar.put("NOMBRE","CHANCLAS");
        DatosInsertar.put("PRECIO",20);
        DatosInsertar.put("IMG","R.drawable.chanclas");

        err = BD.insert("PRODUCTOS", null, DatosInsertar);
        if (err==-1){
            // Toast.makeText(this, "Ya estaba creada la fila", Toast.LENGTH_LONG).show();
        }

        //SELECCIONO LAS RUTAS DE LAS IMAGENES
        String campos [] = new String [] {"IMG"};
        Cursor cr5 = BD.query("PRODUCTOS", campos, "(COD == 3)", null, null, null, null);
        cr5.moveToFirst();
        img = cr5.getString(cr5.getColumnIndex("IMG")).substring(11,cr5.getString(cr5.getColumnIndex("IMG")).length());

        Cursor cr6 = BD.query("PRODUCTOS", campos, "(COD == 6)", null, null, null, null);
        cr6.moveToFirst();
        img2 = cr6.getString(cr6.getColumnIndex("IMG")).substring(11,cr6.getString(cr6.getColumnIndex("IMG")).length());

        Cursor cr = BD.query("PRODUCTOS", campos, "(COD == 9)", null, null, null, null);
        cr.moveToFirst();
        img3 = cr.getString(cr.getColumnIndex("IMG")).substring(11,cr.getString(cr.getColumnIndex("IMG")).length());


        //SELECCIONO DE LA BD, LOS NOMBRES Y PRECIOS DE LOS PRODUCTOS
        String nombres [] = new String [] {"NOMBRE"};
        String precios [] = new String [] {"PRECIO"};
        //IMG1
        Cursor c = BD.query("PRODUCTOS", nombres, "(COD == 3)", null, null, null, null);
        c.moveToFirst();
        String name = c.getString(c.getColumnIndex("NOMBRE"));

        c = BD.query("PRODUCTOS", precios, "(COD == 3)", null, null, null, null);
        c.moveToFirst();
        precio = c.getString(c.getColumnIndex("PRECIO"));

        //IMG2
        c = BD.query("PRODUCTOS", nombres, "(COD == 6)", null, null, null, null);
        c.moveToFirst();
        String name2 = c.getString(c.getColumnIndex("NOMBRE"));

        c = BD.query("PRODUCTOS", precios, "(COD == 6)", null, null, null, null);
        c.moveToFirst();
        precio2 = c.getString(c.getColumnIndex("PRECIO"));

        //IMG3
        c = BD.query("PRODUCTOS", nombres, "(COD == 9)", null, null, null, null);
        c.moveToFirst();
        String name3 = c.getString(c.getColumnIndex("NOMBRE"));

        c = BD.query("PRODUCTOS", precios, "(COD == 9)", null, null, null, null);
        c.moveToFirst();
        precio3 = c.getString(c.getColumnIndex("PRECIO"));



        //--------------CHECKBOX--------------
        c1 = (CheckBox)findViewById(R.id.c1);
        c2 = (CheckBox)findViewById(R.id.c2);
        c3 = (CheckBox)findViewById(R.id.c3);
        ImageView i = (ImageView) findViewById(R.id.img1);
        ImageView i2 = (ImageView) findViewById(R.id.img2);
        ImageView i3 = (ImageView) findViewById(R.id.img3);
        i.setOnClickListener(this);
        i2.setOnClickListener(this);
        i3.setOnClickListener(this);


        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){

                if(c1.isChecked()){
                    //Toast.makeText(getApplicationContext(), "Has pulsado 1", Toast.LENGTH_LONG).show();
                    ch1 = true;
                    c2.setChecked(false);
                    c3.setChecked(false);
                }else{
                    // Toast.makeText(getApplicationContext(), "Has Despulsado 1", Toast.LENGTH_LONG).show();
                    ch1 = false;
                }

            }
        });


        c2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){

                if(c2.isChecked()){
                    // Toast.makeText(getApplicationContext(), "Has pulsado 2", Toast.LENGTH_LONG).show();
                    ch2 = true;
                    c1.setChecked(false);
                    c3.setChecked(false);
                }else{
                    //  Toast.makeText(getApplicationContext(), "Has Despulsado 2", Toast.LENGTH_LONG).show();
                    ch2 = false;
                }

            }
        });


        c3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){

                if(c3.isChecked()){
                    //Toast.makeText(getApplicationContext(), "Has pulsado 3", Toast.LENGTH_LONG).show();
                    ch3 = true;
                    c1.setChecked(false);
                    c2.setChecked(false);
                }else{
                    //Toast.makeText(getApplicationContext(), "Has Despulsado 3", Toast.LENGTH_LONG).show();
                    ch3 = false;
                }

            }
        });


        c1.setText(name + " " + precio + "€");
        c2.setText(name2 + " " + precio2 + "€");
        c3.setText(name3 + " " + precio3 + "€");

        //COLOCO LAS IMAGENES
        int ide=getResources().getIdentifier(img,"drawable",getPackageName());
        i.setImageResource(ide);
        ide=getResources().getIdentifier(img2,"drawable",getPackageName());
        i2.setImageResource(ide);
        ide=getResources().getIdentifier(img3,"drawable",getPackageName());
        i3.setImageResource(ide);


        //TALLAS DE LOS PRODUCTOS
        final String [] tallas = {"45","44","43","42","41","40"};
        final String [] cantidad = {"1", "2", "3", "4", "5"};
        Spinner spin = (Spinner) findViewById(R.id.spin);
        Spinner cant1 = (Spinner) findViewById(R.id.cant1);
        Spinner cant2 = (Spinner) findViewById(R.id.cant2);
        Spinner cant3 = (Spinner) findViewById(R.id.cant3);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tallas);
        spin.setAdapter(adaptador);

        ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cantidad);
        cant1.setAdapter(adaptador2);
        cant2.setAdapter(adaptador2);
        cant3.setAdapter(adaptador2);

        //SPIN 1
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

                if (position == 0) {
                    eleccion1[0] = tallas[0];
                }
                if (position == 1){
                    eleccion1[0] = tallas[1];
                }
                if (position == 2){
                    eleccion1[0] = tallas[2];
                }
                if (position == 3){
                    eleccion1[0] = tallas[3];
                }
                if (position == 4){
                    eleccion1[0] = tallas[4];
                }
                if (position == 5){
                    eleccion1[0] = tallas[5];
                }

                // Toast.makeText(getApplicationContext(), "Ha pulsado el item " + position, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        cant1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

                if (position == 0) {
                    und1 = 1;
                }
                if (position == 1){
                    und1 = 2;
                }
                if (position == 2){
                    und1 = 3;
                }
                if (position == 3){
                    und1 = 4;
                }
                if (position == 4){
                    und1 = 5;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cant2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

                if (position == 0) {
                    und2 = 1;
                }
                if (position == 1){
                    und2 = 2;
                }
                if (position == 2){
                    und2 = 3;
                }
                if (position == 3){
                    und2 = 4;
                }
                if (position == 4){
                    und2 = 5;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        cant3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

                if (position == 0) {
                    und3 = 1;
                }
                if (position == 1){
                    und3 = 2;
                }
                if (position == 2){
                    und3 = 3;
                }
                if (position == 3){
                    und3 = 4;
                }
                if (position == 4){
                    und3 = 5;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //BOTONES DE AÑADIR PRODUCTO

        Button b = (Button)findViewById(R.id.b1);
        Button b2 = (Button)findViewById(R.id.b2);
        Button b3 = (Button)findViewById(R.id.b3);
        b.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent paso = new Intent (this, productos.class);
        Intent paso2 = new Intent (this, comprar.class);

        switch (v.getId()){
            case R.id.volver:
                paso.putExtras(bundle);
                startActivity(paso);
                finish();
                break;
            case R.id.cesta:
                paso2.putExtras(bundle);
                startActivity(paso2);
                finish();
                break;
            case R.id.b2:
                if(ch1 == true) {
                    s1 = String.valueOf(und1) + " " + (String) c1.getText();
                    Toast.makeText(getApplicationContext(), "Has añadido a la cesta: " + s1, Toast.LENGTH_LONG).show();
                    bundle.putString("otros1",s1);
                    bundle.putInt("po1",Integer.parseInt(precio)*und1);
                }
                break;
            case R.id.b1:
                if(ch2 == true) {
                    s2 = String.valueOf(und2) + " " + (String) c2.getText();
                    Toast.makeText(getApplicationContext(), "Has añadido a la cesta: " + s2, Toast.LENGTH_LONG).show();
                    bundle.putString("otros2",s2);
                    bundle.putInt("po2",Integer.parseInt(precio2)*und2);
                }
                break;
            case R.id.b3:
                if(ch3 == true) {
                    s3 = String.valueOf(und3) + " " + c3.getText() + " Talla:" + eleccion1[0];
                    Toast.makeText(getApplicationContext(), "Has añadido a la cesta: " + s3, Toast.LENGTH_LONG).show();
                    bundle.putString("otros3",s3);
                    bundle.putInt("po3",Integer.parseInt(precio3)*und3);
                }
                break;
            case R.id.borrar:
                bundle.putString("otros1",null);
                bundle.putString("otros2",null);
                bundle.putString("otros3",null);
                bundle.putInt("po1", 0);
                bundle.putInt("po2", 0);
                bundle.putInt("po3", 0);
                Toast.makeText(getApplicationContext(),"Has deseleccionado los complementos", Toast.LENGTH_LONG).show();
                break;
        }

    }
    public void onBackPressed() {
    }
}
