package androidassignment.wordpress.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView username,password,id,name,age,city,company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username = (TextView)findViewById(R.id.username);
        password = (TextView)findViewById(R.id.password);
        id = (TextView)findViewById(R.id.id);
        name = (TextView)findViewById(R.id.name);
        age = (TextView)findViewById(R.id.age);
        city = (TextView)findViewById(R.id.city);
        company = (TextView)findViewById(R.id.company);

        username.setText(getIntent().getStringExtra("username"));
        password.setText(getIntent().getStringExtra("password"));
        id.setText(getIntent().getStringExtra("id"));
        name.setText(getIntent().getStringExtra("name"));
        age.setText(getIntent().getStringExtra("age"));
        city.setText(getIntent().getStringExtra("city"));
        company.setText(getIntent().getStringExtra("company"));

    }
}
