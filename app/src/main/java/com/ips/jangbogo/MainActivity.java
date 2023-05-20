package com.ips.jangbogo;

import static android.content.ContentValues.TAG;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.nio.channels.AsynchronousChannelGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    private Button button;

    //DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    //DatabaseReference conditionRef = mRootRef.child("data");
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
    }

    @Override
    protected void onStart() {
        super.onStart();

        /*conditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String text = dataSnapshot.getValue(String.class);
                //textView.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyAsyncTask().execute();
            }


        });

    }

    private class MyAsyncTask extends AsyncTask<Void, Void, String>{
        @Override
        protected String doInBackground(Void... voids) {
            int status = NetworkStatus.getConnectivityStatus(getApplicationContext());
            if (status == NetworkStatus.TYPE_MOBILE || status == NetworkStatus.TYPE_WIFI) {
                /*// get
                String url = "https://jangbogo-shop-default-rtdb.firebaseio.com/product.json";
                return OkhttpUtils.get(url);*/

                // get - firestore
                //String url = "https://firestore.googleapis.com/v1/projects/jangbogo-shop/databases/(default)/documents/cart/OhEvXwkAPYHLe8fJ9Q34";
                //return OkhttpUtils.get(url);

                //Query query = db.collection("cart").whereEqualTo("memberID", "sdfasdfa");
                //final List<Object> list = new ArrayList<>();
                //final String[] str = {""};

                db.collection("cart")
                        .whereEqualTo("memberID", "a;lssdf")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                        //Map<String, Object> res = new HashMap<>();
                                        //res.put(document.getId(), document.getData());
                                    }
                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });
                //return res.get(str).toString();
                return "굿한번해~";

                /*// post
                String url = "https://jangbogo-shop-default-rtdb.firebaseio.com/appLog.json";
                Map<String, String> body = new HashMap<>();
                body.put("author", "alanisawesome22");
                body.put("title", "The Turing Machine33");
                return OkhttpUtils.post(url, body, MediaType.parse("application/json; charset=utf-8"));*/

                /*//patch
                String url = "https://jangbogo-shop-default-rtdb.firebaseio.com/appLog/-NVok6cm2jzQPajashy7.json";
                Map<String, String> body = new HashMap<>();
                body.put("author", "update!");
                return OkhttpUtils.patch(url, body, MediaType.parse("application/json; charset=utf-8"));
*/

                /*//delete
                String url = "https://jangbogo-shop-default-rtdb.firebaseio.com/data.json";
                return OkhttpUtils.delete(url);*/


            } else {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                textView.setText(result);
            } else {
                Toast.makeText(getApplicationContext(), "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}