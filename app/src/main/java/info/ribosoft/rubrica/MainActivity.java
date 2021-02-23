package info.ribosoft.rubrica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private HttpConn myHttpConn;
    private Object object;
    private Button buttonInsert;
    private ListView listViewAddressBook;
    private ArrayList<String> conctactId = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();
        String URLWEB = context.getString(R.string.URLWEB);

        buttonInsert = (Button) findViewById(R.id.buttonInsert);
        listViewAddressBook = (ListView) findViewById(R.id.listViewAddressBook);

        // enter a new contact
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getApplicationContext return the context of the single,
                // global Application object of the current process
                Intent intent = new Intent(getApplicationContext(), InsertActivity.class);
                // pass parameters between two activities
                // starts an instance specified by intent
                startActivity(intent);
            }
        });

        // view contact
        listViewAddressBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posClick,
                long idClick) {
                // getApplicationContext return the context of the single,
                // global Application object of the current process
                Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
                // pass parameters between two activities
                intent.putExtra("URLWEB",URLWEB);
                intent.putExtra("id_contact", conctactId.get(posClick));
                // starts an instance specified by intent
                startActivity(intent);
            }
        });

        myHttpConn = new HttpConn();
        // specifies a url and receives a string
        myHttpConn.postStringRequest(getApplicationContext(), URLWEB, "0",
            new HttpConn.vollCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    // creates a new JSONArray with values from the JSON string
                    JSONArray jsonArray = new JSONArray(response);
                    // size the array
                    String[] record = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        // returns the value mapped by name if it exists and is a JSONObject
                        JSONObject object = jsonArray.getJSONObject(i);
                        conctactId.add(object.getString("id_contatto"));
                        record[i] = object.getString("cognome") + " " +
                                object.getString("nome");
                    }
                    // sets the data behind this ListView
                    ArrayAdapter<String> arrayContact =  new
                            ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_list_item_1, record);
                    listViewAddressBook.setAdapter(arrayContact);
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), "Errore: " + ex.toString(),
                        Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplicationContext(), "Errore: " + error.toString(),
                    Toast.LENGTH_SHORT).show();
            }
        });
    }
}