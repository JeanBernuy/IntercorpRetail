package com.example.jeanbernuycharvet.intercorpretail.activities;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jeanbernuycharvet.intercorpretail.R;
import com.example.jeanbernuycharvet.intercorpretail.model.Customer;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateCustomerActivity extends AppCompatActivity {

    private EditText nombre;
    private EditText apellido;
    private EditText edad;
    private EditText fechaNacimiento;
    private Button btnRegistrar;
    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;
    DatabaseReference databaseCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_customer);

        initUI();
    }

    private void initUI() {

        nombre = findViewById(R.id.edtNombre);
        apellido = findViewById(R.id.edtApellido);
        edad = findViewById(R.id.edtEdad);
        fechaNacimiento = findViewById(R.id.edtFecha);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        fechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(CreateCustomerActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                actualizarInput();
            }

        };

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCustomer();
            }
        });

        initFireBaseDB();

    }

    private void initFireBaseDB(){
        databaseCustomer = FirebaseDatabase.getInstance().getReference("customer");
    }
    private void actualizarInput() {
        String formatoDeFecha = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);
        fechaNacimiento.setText(sdf.format(myCalendar.getTime()));
    }

    private void addCustomer(){
        String name = nombre.getText().toString();
        String ape = apellido.getText().toString();
        String ed = edad.getText().toString();
        String fecha = fechaNacimiento.getText().toString();

        if (!name.equals("") && !ape.equals("") && !ed.equals("") && !fecha.equals("")){
            String id = databaseCustomer.push().getKey();
            Customer customer = new Customer(name,ape,ed,fecha);
            databaseCustomer.child(id).setValue(customer);
            Toast.makeText(CreateCustomerActivity.this,"Cliente Agregado",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(CreateCustomerActivity.this,"Debe completar todos los campos",Toast.LENGTH_SHORT).show();
        }
    }
}
