package io.github.sanjeet291196.sqlitesample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        EditText groupId = (EditText) findViewById(R.id.view_group_id);
        if (!groupId.getText().toString().equals("")) {
            Bundle gId = new Bundle();
            gId.putString("GID", groupId.getText().toString());
            Intent i = new Intent(this, MainActivity.class);
            i.putExtras(gId);
            startActivity(i);
        }
    }
}
