package com.example.loginsample;

import android.accounts.Account;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnAceptar=findViewById(R.id.btnAceptar);
        Button btnCancelar=findViewById(R.id.btnCancelar);
        EditText edtFirstname = findViewById(R.id.edtFirstname);
        EditText edtLastname = findViewById(R.id.edtLastname);
        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtPhone = findViewById(R.id.edtTextPhone);
        EditText edtUsername2 = findViewById(R.id.edtUsername2);

        btnAceptar.setOnClickListener(v->{
            AccountEntity accountEntity=new AccountEntity();
            accountEntity.setFirstname(edtFirstname.getText().toString());
            accountEntity.setLastname(edtLastname.getText().toString());
            
        });
    }
}