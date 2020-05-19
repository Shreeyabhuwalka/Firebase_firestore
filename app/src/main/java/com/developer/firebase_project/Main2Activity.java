package com.developer.firebase_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {
    private Button logout;
    private Button add;
    private EditText input;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        add = (Button) findViewById(R.id.add);
        input = (EditText) findViewById(R.id.editText);
        logout = (Button) findViewById(R.id.log_out);
        listView = (ListView) findViewById(R.id.lists);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
            }
        });
//        FirebaseDatabase.getInstance().getReference().child("ProgrammingKnowledge")
//                .child("Name").setValue("Shreeya");
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("Name","Shreeya");
//        map.put("Email","shreeyabhuwalka@gmail.com");
//
//        FirebaseDatabase.getInstance().getReference()
//                .child("ProgrammingKnowledge")
//                .child("Multiple values")
//                .updateChildren(map);
        final int[] count = {0};
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = input.getText().toString();
                if(text.isEmpty())
                {
                    Toast.makeText(Main2Activity.this, "Enter Name", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    count[0]++;
                    FirebaseDatabase.getInstance().getReference().child("language").child(text+ count[0]).setValue(text);
                }
            }
        });
        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter(this,R.layout.list_item,list);
        listView.setAdapter(adapter);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("language");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    list.add(snapshot.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
