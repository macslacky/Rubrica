package info.ribosoft.rubrica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {
    private Intent intentAddressBook;
    private HttpConn myHttpConn;
    private Object object;
    private ListView listViewAddressBook;
    private ArrayList<String> conctactId = new ArrayList<String>();
    private String idContact;
    private HttpConn.recContact myRecContact;

    EditText editUpdateFirstName, editUpdateSurName, editUpdatePhone;
    Button buttonAddressBook, buttonUpdate, buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Context context = getApplicationContext();
        String URLWEB = context.getString(R.string.URLWEB);

        editUpdateFirstName = findViewById(R.id.editUpdateFirstName);
        editUpdateSurName = findViewById(R.id.editUpdateSurname);
        editUpdatePhone = findViewById(R.id.editUpdatePhone);
        buttonAddressBook = findViewById(R.id.buttonAddressBook);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);

        buttonAddressBook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // getApplicationContext return the context of the single,
                // global Application object of the current process
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                // pass parameters between two activities
                // starts an instance specified by intent
                startActivity(intent);
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                myHttpConn = new HttpConn();
                // specifies a url and receives a string
                myHttpConn.postUpdateRequest(getApplicationContext(), URLWEB, "2",
                    idContact, editUpdateFirstName.getText().toString(),
                    editUpdateSurName.getText().toString(), editUpdatePhone.getText().toString(),
                    new HttpConn.vollCallback() {

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
                        Toast.makeText(getApplicationContext(), "Errore: " +
                            error,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                myHttpConn = new HttpConn();
                // specifies a url and receives a string
                myHttpConn.postDeleteRequest(getApplicationContext(), URLWEB, "3",
                    idContact, new HttpConn.vollCallback() {

                    @Override
                    public void onSuccess(String response) {
                        // getApplicationContext return the context of the single,
                        // global Application object of the current process
                        Intent intent = new Intent(getApplicationContext(),
                        MainActivity.class);
                        // pass parameters between two activities
                        // starts an instance specified by intent
                        startActivity(intent);
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(getApplicationContext(), "Errore: " +
                            error,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        intentAddressBook = getIntent();
        idContact = intentAddressBook.getStringExtra("id_contact");

        myHttpConn = new HttpConn();
        myHttpConn.postStringRequest(getApplicationContext(), URLWEB, "1",
            idContact, new HttpConn.vollCallback() {

            @Override
            public void onSuccess(String response) {
                try {
                    // creates a new JSONArray with values from the JSON string
                    JSONArray jsonArray = new JSONArray(response);
                    // returns the value mapped by name if it exists and is a JSONObject
                    JSONObject object = jsonArray.getJSONObject(0);
                    editUpdateFirstName.setText(object.getString("nome"));
                    editUpdateSurName.setText(object.getString("cognome"));
                    editUpdatePhone.setText(object.getString("telefono"));
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), "Errore: " + ex,
                        Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplicationContext(), "Errore: " +
                    error,Toast.LENGTH_SHORT).show();
            }
        });
    }
}