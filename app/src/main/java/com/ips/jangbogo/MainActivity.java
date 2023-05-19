package com.ips.jangbogo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    private Button button;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference conditionRef = mRootRef.child("data");

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

        conditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                textView.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //conditionRef.setValue(editText.getText().toString());

//                OkHttpClient client = new OkHttpClient();
//                Request request = new Request.Builder()
//                        .url("https://jangbogo-shop-default-rtdb.firebaseio.com/product.json")
//                        .get()
//                        .build();
//                try (Response response = client.newCall(request).execute()) {
//                    textView.setText(response.body().string());
//                    //return response.body().string();
//                } catch (IOException e) {
//                    //log.error(e.getMessage(), e);
//                    throw new RuntimeException(e);
//                }


                String url = "https://jangbogo-shop-default-rtdb.firebaseio.com/product.json";
                String result = OkhttpUtils.get(url);
//                Assert.assertEquals("1", result);
                textView.setText(result);
            }


        });
    }
}