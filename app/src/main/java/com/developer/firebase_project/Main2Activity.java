package com.developer.firebase_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
     Button add;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        add = (Button) findViewById(R.id.add);
        input = (EditText) findViewById(R.id.editText);
        final Button logout = (Button) findViewById(R.id.log_out);
        ListView listView = (ListView) findViewById(R.id.lists);
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
        final ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.list_item,list);
        listView.setAdapter(adapter);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("information");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    information info = snapshot.getValue(information.class);
                    String txt = null;
                    if (info != null) {
                        txt = info.getName() + " : " + info.getEmail();
                    }
                    else
                        txt = "empty";
//                     list.add(snapshot.getValue().toString());
                    list.add(txt);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //--------------??????below command is to add data in cloud firestore-------------

//        Map<String,Object> city =new HashMap<>();
//        city.put("name","Varanasi");
//        city.put("State","UP");
//        city.put("Country","India");
//        db.collection("Cities").document("JRS").set(city).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//           if(task.isSuccessful())
//           {
//               Toast.makeText(Main2Activity.this, "Value Added", Toast.LENGTH_SHORT).show();
//           }
//        }
//        });


        /////----------??????below command is to merge data to firestore data----------

//        Map<String,Object> data = new HashMap<>();
//        data.put("capital",false);
//        db.collection("Cities").document("JRS").set(data, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//           if(task.isSuccessful())
//           {
//               Toast.makeText(Main2Activity.this, "Merge Successful", Toast.LENGTH_SHORT).show();
//           }
//        }
//        });



        /////----------??????below command is to add data with ***unique id*** in firestore data----------
//        Map<String,Object> data = new HashMap<>();
//        data.put("Name","Tokyo");
//        data.put("Capital","Japan");
//        db.collection("Cities").add(data).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentReference> task) {
//                if(task.isSuccessful())
//           {
//               Toast.makeText(Main2Activity.this, "Merge Successful", Toast.LENGTH_SHORT).show();
//           }
//            }
//        });


        /////----------??????below command is to update existing data in firestore data----------
//        DocumentReference ref = FirebaseFirestore.getInstance().collection("Cities").document("JRS");
//        ref.update("capital",true);


        /////----------??????below command is to retrieve data from firestore data----------
        DocumentReference docRef = FirebaseFirestore.getInstance().collection("Cities").document("JRS");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful())
                    {
                        DocumentSnapshot doc = task.getResult();
                        if(doc.exists())
                        {
                            Log.i("Document", doc.getData().toString());
                        }
                        else
                        {
                            Log.i("Document","No Data");
                        }
                    }
                }

        });

    }
}
