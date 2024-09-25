package com.example.loginsample;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loginsample.databinding.ActivityMainBinding;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        EditText edtUsername = binding.edtUsername;
        EditText edtPassword = binding.edtPassword;
        Button btnLogin = binding.btnLogin;
        Button btnAddAccount = binding.btnAddAccount;
        btnLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(edtUsername.getText().toString().equals("admin") &&
                edtPassword.getText().toString().equals("admin")){
                    Toast.makeText(getApplicationContext(),"Bienvenido a mi App",Toast.LENGTH_SHORT).show();
                    Log.d(TAG,"Bienvenido a mi App");
                }
                else{
                    Toast.makeText(getApplicationContext(),"Error en la autenticacion",Toast.LENGTH_SHORT).show();
                    Log.d(TAG,"Error en la autenticacion");
                }
            }
        });
        btnAddAccount.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(),AccountActivity.class);
            activityResultLauncher.launch(intent);


        });
    }
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    Integer resultCode=o.getResultCode();
                    if(resultCode==AccountActivity.ACCOUNT_ACEPTAR){
                        Intent data=o.getData();
                        String account_record = data.getStringExtra(AccountActivity.ACCOUNT_RECORD);
                        Gson gson= new Gson();
                        AccountEntity accountEntity = gson.fromJson(account_record, AccountEntity.class);
                        String firstName = accountEntity.getFirstname();
                        Toast.makeText(getApplicationContext(),"Nombre" + firstName,Toast.LENGTH_LONG).show();
                        Log.d("LoginActivity", "Nombre" + firstName);
                    }
                    else if(resultCode.equals(AccountActivity.ACCOUNT_CANCELAR)){
                        Toast.makeText(getApplicationContext(),"Cancelado",Toast.LENGTH_LONG).show();
                        Log.d("LoginActivity", "Cancelado" );
                    }
                }
            }
    );
}