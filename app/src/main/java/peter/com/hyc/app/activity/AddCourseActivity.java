package peter.com.hyc.app.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import peter.com.hyc.R;
import peter.com.hyc.app.adaptor.CourseAdaptor;
import peter.com.hyc.app.model.CourseModel;

public class AddCourseActivity extends AppCompatActivity {
    Spinner spnColors;
    EditText edt_subject, edt_code;
    Button btn_plus, btn_minus, btnAdd;
    String course = "", code = "";
    Database database;
    ArrayList<CourseModel> listModel;
    private CourseAdaptor adpt;
    EditText edt_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listModel = new ArrayList<>();
        setContentView(R.layout.add_course_activity);
        edt_code = findViewById(R.id.edt_code);
        edt_subject = findViewById(R.id.edt_subject);
        spnColors = findViewById(R.id.spnColors);
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        edt_result = findViewById(R.id.edt_result);
        btnAdd = findViewById(R.id.btnAdd);
        String colorList[] = {"Black", "Red", "Blue", "Green"};
        database = new Database(this);
        spnColors.setAdapter(new ArrayAdapter(this, R.layout.spn_textview_item, R.id.spn_txt_item, colorList));
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = edt_subject.getText().toString();
                code = edt_code.getText() + "";
                String text = spnColors.getSelectedItem() + "";
                if (text.equalsIgnoreCase("Black")) {
                    final CourseModel model = new CourseModel();
                    model.setName(subject);
                    model.setCode(code);
                    model.setColor("Black");
                    listModel.add(model);
                    showCourses();
                } else if (text.equalsIgnoreCase("Red")) {
                    final CourseModel model = new CourseModel();
                    model.setName(subject);
                    model.setCode(code);
                    model.setColor("Red");
                    listModel.add(model);
                    showCourses();
                } else if (text.equalsIgnoreCase("Blue")) {
                    final CourseModel model = new CourseModel();
                    model.setName(subject);
                    model.setCode(code);
                    model.setColor("Blue");
                    listModel.add(model);

                    showCourses();
                } else if (text.equalsIgnoreCase("Green")) {
                    final CourseModel model = new CourseModel();
                    model.setName(subject);
                    model.setCode(code);
                    model.setColor("Green");
                    listModel.add(model);

                    showCourses();
                }
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listModel.size()>0) {
                    listModel.remove(listModel.size() - 1);
                    showCourses();
                }

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (CourseModel model : listModel) {
                    database.saveData(model.getName(), model.getCode(),model.getColor());
                }
                Toast.makeText(AddCourseActivity.this, "saved", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void showCourses()
    {
        edt_result.setText("");
        course="";
        for (CourseModel model : listModel) {

            switch (model.getColor())
            {
                case "Red":
                    course += " <font color=#E52727>" + model.getName() + "</font>";
                    break;
                case "Green":
                    course += " <font color=#27E538>" + model.getName() + "</font>";
                    break;
                case "Black":
                    course += " <font color=#111010>" + model.getName() + "</font>";
                    break;
                case "Blue":
                    course += " <font color=#2761E5>" + model.getName() + "</font>";
                    break;

            }
        }

       edt_result.setText(Html.fromHtml(course));
    }

   /* public void AddData(String newEntry){
        boolean insertData=database.addData(newEntry);
        if (insertData){
Toast.makeText(this,"data inserted",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"data not inserted",Toast.LENGTH_SHORT).show();
        }
    }*/
}
