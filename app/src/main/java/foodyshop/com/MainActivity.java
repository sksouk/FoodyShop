package foodyshop.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private Button add1;
    private FirebaseDatabase fbdata=FirebaseDatabase.getInstance();
    private DatabaseReference mRef=fbdata.getReference().child("users").child(currentFirebaseUser.getUid());
    String curUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(curUid).child("order");
    private DatabaseReference testef;
    private TextView tx;
    private DatabaseReference mDatabase;
    List<recyclerview_class> lstRecyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  tx = (TextView) findViewById(R.id.textView2);
       // add1= (Button) findViewById(R.id.button);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        if (user != null)
        {
            lstRecyclerview = new ArrayList<>();
            lstRecyclerview.add(new recyclerview_class("Title","message 1","message 2","message 3"));
            lstRecyclerview.add(new recyclerview_class("Title","message 1","message 2","message 3"));
            lstRecyclerview.add(new recyclerview_class("Title","message 1","message 2","message 3"));

            RecyclerView myrv = findViewById(R.id.recyclerview_id);
            RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,lstRecyclerview);
            myrv.setLayoutManager(new GridLayoutManager(this,1));
            myrv.setAdapter(myAdapter);
         //   add1.setOnClickListener(new View.OnClickListener() {
           //     @Override
             //   public void onClick(View v) {
               //     mDatabase.child("users").child(curUid).child("diachi").setValue("mon:test,diachi:b3-511");
                //}
            //});
           // tx.setText(curUid);
            startService(new Intent(this, NotificationService.class));

        }
        else
        {
            Toast.makeText(this,"not yet login",Toast.LENGTH_LONG).show();
        }
    }
}
