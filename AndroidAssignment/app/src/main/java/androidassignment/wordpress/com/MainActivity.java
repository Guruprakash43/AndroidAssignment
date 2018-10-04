package androidassignment.wordpress.com;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button login;
    CheckBox checkbox;

    private ProgressDialog mLoginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginProgress = new ProgressDialog(this);

        username = (EditText)findViewById(R.id.username_edt);
        password = (EditText)findViewById(R.id.password_edt);
        login = (Button)findViewById(R.id.loginbtn);
        checkbox = (CheckBox)findViewById(R.id.checkboxpass);

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){

                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);


                }else{

                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(TextUtils.isEmpty(user)){

                    Toast.makeText(MainActivity.this, "Username is Empty", Toast.LENGTH_LONG).show();
                    return;

                }
                if(TextUtils.isEmpty(pass)){

                    Toast.makeText(MainActivity.this, "Password is Empty", Toast.LENGTH_LONG).show();
                    return;

                }

                mLoginProgress.setTitle("Logging in");
                mLoginProgress.setMessage("Please wait while we checking your details");
                mLoginProgress.setCanceledOnTouchOutside(false);
                mLoginProgress.show();

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                String URL = "http://www.beta.colourdrive.in/apk/user_detail.php?username="+user+"&password="+pass;

                JsonObjectRequest objectRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        URL,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {

                                    mLoginProgress.dismiss();

                                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                                    intent.putExtra("username",response.getString("username"));
                                    intent.putExtra("password",response.getString("password"));
                                    intent.putExtra("id",response.getString("id"));
                                    intent.putExtra("name",response.getString("name"));
                                    intent.putExtra("age",response.getString("age"));
                                    intent.putExtra("city",response.getString("city"));
                                    intent.putExtra("company",response.getString("company"));
                                    startActivity(intent);



                                } catch (JSONException e) {
                                    mLoginProgress.dismiss();
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                mLoginProgress.dismiss();
                            }
                        }

                );
                requestQueue.add(objectRequest);


            }
        });

    }
}
