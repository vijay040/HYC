package peter.com.hyc.app.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import peter.com.hyc.R;
import peter.com.hyc.app.adaptor.CourseAdaptor;
import peter.com.hyc.app.model.CourseModel;

public class LandingActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ImageView imgEdit;
    Database database;
    ListView list_item;
    Button btnSubmit;
    ArrayList<String> ar = new ArrayList<String>();
    EditText edt_txt_first_name;
  private CourseAdaptor adpt;
  ArrayList<CourseModel> modelList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_layout);
        imgEdit = findViewById(R.id.imgEdit);
        database=new Database(this);
        btnSubmit=findViewById(R.id.btnSubmit);
        list_item=findViewById(R.id.list_item);
        SearchView editTextName=(SearchView) findViewById(R.id.edt_txt_first_name);
        editTextName.setQueryHint("Search By Subject Code");
        editTextName.setOnQueryTextListener(this);



       /* btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getListviewItem();
            }
        });*/
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingActivity.this, CourseListActivity.class));
            }
        });

    }

    private void loadList()
    {
        modelList=new ArrayList<>();
        Cursor cursor = database.fetchData();

        if (!(cursor.moveToFirst()) || cursor.getCount() == 0) {
            Toast.makeText(LandingActivity.this, "empty database", Toast.LENGTH_LONG).show();

        }
        do {
            try {
                String N = cursor.getString(cursor.getColumnIndex("name"));
                String C = cursor.getString(cursor.getColumnIndex("code"));
                String color = cursor.getString(cursor.getColumnIndex("color"));
                CourseModel c = new CourseModel();
                c.setName(N);
                c.setCode(C);
                c.setColor(color);
                modelList.add(c);
            }catch (Exception e){}

        } while (cursor.moveToNext());


        adpt=new CourseAdaptor(this,modelList);
        list_item.setAdapter(adpt);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadList();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText=newText.toLowerCase();
        ArrayList<CourseModel> newlist=new ArrayList<>();
        for(CourseModel filterlist:modelList)
        {
            String purpose=filterlist.getCode().toLowerCase();
            //String address =filterlist.getName().toLowerCase();
            if(purpose.contains(newText)) {
                newlist.add(filterlist);
            }
        }
        adpt.filter(newlist);
        return true;
    }
}
