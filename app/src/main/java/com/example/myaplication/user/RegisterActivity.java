package com.example.myaplication.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myaplication.MainActivity;
import com.example.myaplication.R;

public class RegisterActivity extends AppCompatActivity {

    EditText TxEmail, TxUsername, TxPassword;
    Button BtnRegister;
    MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        MainActivity mainactivity = new MainActivity(this);

        TxEmail = (EditText) findViewById(R.id.txEmailReg);
        TxUsername = (EditText) findViewById(R.id.txUsernameReg);
        TxPassword = (EditText) findViewById(R.id.txPasswordReg);
        BtnRegister = (Button) findViewById(R.id.btnRegister);

        TextView tvRegister = (TextView) findViewById(R.id.tvRegister);

        tvRegister.setText(fromHtml("Back to "));
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = TxEmail.getText().toString().trim();
                String username = TxUsername.getText().toString().trim();
                String password = TxPassword.getText().toString().trim();

                ContentValues values = new ContentValues();


                if (email.equals("")){
                    TxEmail.setError("Email Tidak Boleh Kosong!");
                }
                if (username.equals("")) {
                    TxUsername.setError("Nama Tidak Boleh Kosong!");
                }
                if (password.equals("")) {
                    TxPassword.setError("Password Tidak Boleh Kosong!");
                }else if (TxPassword.length() < 6) {
                    TxPassword.setError("Password Harus 6 Karakter atau Lebih!");
                }else {
                    values.put(MainActivity.row_Email, email);
                    values.put(MainActivity.row_username, username);
                    values.put(MainActivity.row_password, password);
                    mainactivity.insertData(values);

                    Toast.makeText(RegisterActivity.this, "Berhasil membuat akun, silahkan Login", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    public static Spanned fromHtml(String html){
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        }else {
            result = Html.fromHtml(html);
        }
        return result;
    }
}