package sdgp.azure.routeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button seekBtn;
    EditText destText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBtn = (Button) findViewById(R.id.seekButton);
        destText = (EditText) findViewById(R.id.destText);
        seekBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("location", destText.getText().toString());
                startActivity(intent);
            }
        });
    }
}
