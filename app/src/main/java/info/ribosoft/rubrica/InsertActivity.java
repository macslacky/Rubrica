package info.ribosoft.rubrica;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    private HttpConn myHttpConn;
    private EditText editInsertFirstName, editInsertSurname, editInsertPhone;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        Context context = getApplicationContext();
        String URLWEB = context.getString(R.string.URLWEB);

        editInsertFirstName = (EditText) findViewById(R.id.editInsertFirstName);
        editInsertSurname = (EditText) findViewById(R.id.editInsertSurname);
        editInsertPhone = (EditText) findViewById(R.id.editInsertPhone);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                myHttpConn = new HttpConn();
                // specifies a url and receives a string
                myHttpConn.postInsertRequest(getApplicationContext(), URLWEB, "4",
                    editInsertFirstName.getText().toString(),
                    editInsertSurname.getText().toString(),
                    editInsertPhone.getText().toString(), new HttpConn.vollCallback() {

                    @Override
                    public void onSuccess(String response) {
                        // getApplicationContext return the context of the single,
                        // global Application object of the current process
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        // pass parameters between two activities
                        // starts an instance specified by intent
                        startActivity(intent);
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(getApplicationContext(),
                    "Errore: " + error,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}