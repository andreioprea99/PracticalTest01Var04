package ro.pub.cs.systems.eim.practicaltest01var04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var04MainActivity extends AppCompatActivity {

    private CheckBox firstCheckbox;
    private CheckBox secondCheckbox;
    private EditText nameEditText;
    private EditText groupEditText;
    private Button displayInformationButton;
    private TextView informationTextView;
    private MyListener listener = new MyListener();


    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.button_display) {
                String name = "";
                String group = "";
                if (firstCheckbox.isChecked()) {
                    name = nameEditText.getText().toString();
                    if (name.equals("")) {
                        displayToast("Name not completed");
                    }

                }
                if (secondCheckbox.isChecked()) {
                    group = groupEditText.getText().toString();
                    if (group.equals("")) {
                        displayToast("Group not completed");
                    }
                }
                informationTextView.setText(name + " " + group);
            }
        }
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_main);
        firstCheckbox = findViewById(R.id.firstCheck);
        secondCheckbox = findViewById(R.id.secondCheck);
        nameEditText = findViewById(R.id.firstEditText);
        groupEditText = findViewById(R.id.secondText);
        displayInformationButton = findViewById(R.id.button_display);
        informationTextView = findViewById(R.id.informationTextView);

        displayInformationButton.setOnClickListener(listener);
    }
}