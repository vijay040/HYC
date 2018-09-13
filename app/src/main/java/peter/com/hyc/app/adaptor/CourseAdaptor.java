package peter.com.hyc.app.adaptor;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import peter.com.hyc.R;
import peter.com.hyc.app.model.CourseModel;

public class CourseAdaptor extends BaseAdapter {
    public ArrayList<CourseModel> list;
    public Activity context;

    public CourseAdaptor(Activity context, ArrayList<CourseModel> list) {
        this.list = list;
        this.context = context;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void filter(ArrayList<CourseModel> newList1) {
        list = new ArrayList<>();
        list.addAll(newList1);
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.inf_course_item, null);
        }

        TextView txtTitle = view.findViewById(R.id.txtName);
        TextView txtCode = view.findViewById(R.id.txtCode);
       /* txtTitle.setText(list.get(i).getName());
        txtCode.setText(list.get(i).getCode());*/
        showCourses(list.get(i),txtTitle,txtCode);
        return view;
    }

    public void showCourses(CourseModel model, TextView title, TextView code) {
        title.setText("");
        code.setText("");
        String course = "";
        String cod="";
        Log.e("color*****","**********************color"+model.getColor());
        switch (model.getColor()) {
            case "Red":
                course += " <font color=#E52727>" + model.getName() + "</font>";
                cod += " <font color=#E52727>" + model.getCode() + "</font>";
                break;
            case "Green":
                course += " <font color=#27E538>" + model.getName() + "</font>";
                cod += " <font color=#27E538>" + model.getCode() + "</font>";
                break;
            case "Black":
                course += " <font color=#111010>" + model.getName() + "</font>";
                cod += " <font color=#111010>" + model.getCode() + "</font>";
                break;
            case "Blue":
                course += " <font color=#2761E5>" + model.getName() + "</font>";
                cod += " <font color=#2761E5>" + model.getCode() + "</font>";
                break;

        }
        title.setText(Html.fromHtml(course));
        code.setText(Html.fromHtml(cod));
    }

}
