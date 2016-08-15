package io.github.sanjeet291196.sqlitesample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText dataText;
    ListView dataList;

    ArrayAdapter<DataStructure> adapter;
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Data");
    int NewDataId = 1;
    private ArrayList<DataStructure> dataItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getExtras().getString("GID") != null) {
            //noinspection ConstantConditions
            myRef = FirebaseDatabase.getInstance().getReference(getIntent().getExtras().getString("GID"));
        }

        dataText = (EditText) findViewById(R.id.data_text_view);
        dataList = (ListView) findViewById(R.id.data_list);

        dataItem = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataItem);
        dataList.setAdapter(adapter);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DataStructure addedDataStructure = dataSnapshot.getValue(DataStructure.class);

                if (addedDataStructure != null) {
                    dataItem.add(0, addedDataStructure);
                    NewDataId = addedDataStructure.DataId + 1;
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                DataStructure deletedDataStructure = dataSnapshot.getValue(DataStructure.class);


                if (deletedDataStructure != null) {
                    int index = -1;
                    for (int i = 0; i < dataItem.size(); i++) {
                        if (deletedDataStructure.DataId == dataItem.get(i).DataId) {
                            index = i;
                            break;
                        }
                    }
                    dataItem.remove(index);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Firebase", "Failed to read value.", databaseError.toException());
            }
        });
    }


    public void addToDatabase(View view) {
        if (!dataText.getText().toString().equals("")) {
            DataStructure newDataStructure = new DataStructure(NewDataId, dataText.getText().toString());
            myRef.child(String.valueOf(NewDataId)).setValue(newDataStructure);
            dataText.setText("");
        }
    }

}
