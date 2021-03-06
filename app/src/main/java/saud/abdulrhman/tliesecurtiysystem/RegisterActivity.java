package saud.abdulrhman.tliesecurtiysystem;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final Button   btButton  = (Button)  findViewById(R.id.btRegistr);

        btButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name =etName.getText().toString();
                final String username =etUsername.getText().toString();
                final String password =etPassword.getText().toString();
                final String email = etEmail.getText().toString();


                Response.Listener<String> ResponseListener = new Response.Listener<String>(){


                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                Intent intent = new Intent(RegisterActivity.this,loginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }else{

                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };


                RegiserRequest regiserRequest = new RegiserRequest(name,username,email,password,ResponseListener);
                RequestQueue queue =Volley.newRequestQueue(RegisterActivity.this);
                queue.add(regiserRequest);
            }
        });
    }
}
