package com.example.entregable_e01.Widgets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

import java.util.ArrayList;

public class BuscarActivity extends AppCompatActivity {

    EditText txtFoundId;
    Button btnSearchById, btnDeleteById, btnReturnFromSearch;
    ListView lstListById;

    public void loadUI() {
        txtFoundId = findViewById(R.id.txtFoundId);
        btnSearchById = findViewById(R.id.btnSearchById);
        btnDeleteById = findViewById(R.id.btnDeleteById);
        btnReturnFromSearch = findViewById(R.id.btnReturnFromSearch);
        lstListById = findViewById(R.id.lstListById);
    }

    public void renderData() {
        int id = Integer.parseInt(txtFoundId.getText().toString());

        DBTrash dbTrash = new DBTrash(getApplicationContext());
        Trash data = dbTrash.getTrashById(id);
        ArrayList<String> list = new ArrayList<>();

        if (data != null) {
            list.add(data.getWorkerName() + " - " + data.getWasteType() + " - " + data.getQuantity());

            ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

            lstListById.setAdapter(adapter);
        } else {
            Toast.makeText(this, "El id no existe", Toast.LENGTH_SHORT).show();
        }
    }

    public void deletedData() {
        String idStr = txtFoundId.getText().toString();

        if (idStr.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese un ID", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            DBTrash dbTrash = new DBTrash(getApplicationContext());
            dbTrash.deleted(id);
            Toast.makeText(this, "El registro : " + id + " fue eliminado", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "ID inválido", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buscar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadUI();

        btnSearchById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                renderData();
            }
        });

        btnDeleteById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletedData();
            }
        });

        btnReturnFromSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}