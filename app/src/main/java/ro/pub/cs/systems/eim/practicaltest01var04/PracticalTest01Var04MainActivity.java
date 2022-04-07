package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Intent;
import android.support.annotation.NonNull;
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
    private Button secondaryActivity;
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
            } else {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04SecondaryActivity.class);
                intent.putExtra(Constants.nameKey, nameEditText.getText().toString());
                intent.putExtra(Constants.groupKey, groupEditText.getText().toString());
                startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
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
        secondaryActivity = findViewById(R.id.launch_secondary_activity);

        displayInformationButton.setOnClickListener(listener);
        secondaryActivity.setOnClickListener(listener);


        if (savedInstanceState != null) {
            firstCheckbox.setChecked(savedInstanceState.getBoolean(Constants.nameCheckbox, false));
            secondCheckbox.setChecked(savedInstanceState.getBoolean(Constants.groupCheckbox, false));
            nameEditText.setText(savedInstanceState.getString(Constants.nameEditText, ""));
            groupEditText.setText(savedInstanceState.getString(Constants.groupEditText, ""));
            informationTextView.setText(savedInstanceState.getString(Constants.infoTextView, ""));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(Constants.nameCheckbox, firstCheckbox.isChecked());
        savedInstanceState.putBoolean(Constants.groupCheckbox, secondCheckbox.isChecked());
        savedInstanceState.putString(Constants.nameEditText, nameEditText.getText().toString());
        savedInstanceState.putString(Constants.groupEditText, groupEditText.getText().toString());
        savedInstanceState.putString(Constants.infoTextView, informationTextView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            firstCheckbox.setChecked(savedInstanceState.getBoolean(Constants.nameCheckbox, false));
            secondCheckbox.setChecked(savedInstanceState.getBoolean(Constants.groupCheckbox, false));
            nameEditText.setText(savedInstanceState.getString(Constants.nameEditText, ""));
            groupEditText.setText(savedInstanceState.getString(Constants.groupEditText, ""));
            informationTextView.setText(savedInstanceState.getString(Constants.infoTextView, ""));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }
}