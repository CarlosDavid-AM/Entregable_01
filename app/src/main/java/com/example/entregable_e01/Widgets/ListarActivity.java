package com.example.entregable_e01.Widgets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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

public class ListarActivity extends AppCompatActivity {

    ListView lstRegister;
    Button btnReturnFromList;

    public void loadUI() {
        lstRegister = findViewById(R.id.lstRegister);
        btnReturnFromList = findViewById(R.id.btnReturnFromList);
    }

    public void renderData() {
        DBTrash dbTrash = new DBTrash(getApplicationContext());

        ArrayList<Trash> data = dbTrash.getAllTrash();

        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            list.add(data.get(i).getWorkerName() + " - " + data.get(i).getWasteType() + " - " + data.get(i).getQuantity());
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        lstRegister.setAdapter(adapter);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            // v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadUI();
        renderData();

        btnReturnFromList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}