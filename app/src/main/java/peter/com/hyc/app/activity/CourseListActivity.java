package peter.com.hyc.app.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import peter.com.hyc.R;
import peter.com.hyc.app.adaptor.CourseAdaptor;
import peter.com.hyc.app.model.CourseModel;

public class CourseListActivity extends AppCompatActivity {
    ListView list_item;
    Database database;
    ArrayList<String> ar = new ArrayList<String>();
    TextView txt_delete;
    private CourseAdaptor adpt;
    ArrayList<CourseModel> modelList;
ImageView imgEdit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_list_layout);
        Button btnEnterData = findViewById(R.id.btnEnterData);
        imgEdit=findViewById(R.id.imgEdit);
        imgEdit.setVisibility(View.GONE);
        list_item = findViewById(R.id.list_item);
        txt_delete = findViewById(R.id.txt_delete);
        btnEnterData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CourseListActivity.this, AddCourseActivity.class));

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        database = new Database(this);
        getListviewItem();
    }

    private void getListviewItem() {
        modelList = new ArrayList<>();
        Cursor cursor = database.fetchData();

        if (!(cursor.moveToFirst()) || cursor.getCount() == 0) {
            Toast.makeText(CourseListActivity.this, "empty database", Toast.LENGTH_LONG).show();
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
            } catch (Exception e) {
            }

        } while (cursor.moveToNext());

        adpt = new CourseAdaptor(this, modelList);
        list_item.setAdapter(adpt);
    }

}
