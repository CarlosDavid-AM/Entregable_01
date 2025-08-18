package com.example.entregable_e01.Widgets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.entregable_e01.DB.DBTrash;
import com.example.entregable_e01.Entity.Trash;
import com.example.entregable_e01.MainActivity;
import com.example.entregable_e01.R;

import java.time.LocalDateTime;

public class RegistroActivity extends AppCompatActivity {

    Button btnRegisterResidue, btnCancelResidue;
    EditText edtName, edtQuantity;
    RadioGroup rgWasteType;

    private void loadUI() {
        btnRegisterResidue = findViewById(R.id.btnRegisterResidue);
        btnCancelResidue = findViewById(R.id.btnCancelResidue);
        edtName = findViewById(R.id.Name);
        edtQuantity = findViewById(R.id.edtQuantity);
        rgWasteType = findViewById(R.id.rgWasteType);
    }

    private void clearForm() {
        edtName.setText("");
        edtQuantity.setText("");
        rgWasteType.clearCheck();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
           // v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadUI();

        btnRegisterResidue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String workerName = edtName.getText().toString();
                String quantityStr = edtQuantity.getText().toString();

                if (workerName.isEmpty() || quantityStr.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                double quantity = Double.parseDouble(quantityStr);

                int selectedId = rgWasteType.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedId);
                String wasteType = selectedRadioButton.getText().toString();

                Trash newTrash = new Trash();
                newTrash.setWorkerName(workerName);
                newTrash.setWasteType(wasteType);
                newTrash.setQuantity(quantity);
                newTrash.setFecha(LocalDateTime.now());

                DBTrash dbTrash = new DBTrash(getApplicationContext());
                long id = dbTrash.addTrashById(newTrash);

                if (id > 0) {
                    Toast.makeText(getApplicationContext(), "Residuo registrado con éxito.", Toast.LENGTH_SHORT).show();
                    clearForm();
                } else {
                    Toast.makeText(getApplicationContext(), "Error al registrar el residuo.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelResidue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}