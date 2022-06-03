package sg.edu.rp.c346.id20026389.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etElement;
    Spinner spnAddRemove;
    Button btnAdd;
    Button btnRemove;
    Button btnClear;
    ListView lvTask;
    ArrayList<String> alTask;
    ArrayAdapter<String> aaTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etElement=findViewById(R.id.editTextInput);
        btnAdd=findViewById(R.id.buttonAddItem);
        btnRemove=findViewById(R.id.buttonRemoveItem);
        btnClear=findViewById(R.id.buttonClear);
        spnAddRemove=findViewById(R.id.spinner);
        lvTask=findViewById(R.id.listViewTask);
        alTask=new ArrayList<>();
        aaTask=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,alTask);
        lvTask.setAdapter(aaTask);
        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        btnRemove.setEnabled(false);
                        btnAdd.setEnabled(true);
                        etElement.setInputType(InputType.TYPE_CLASS_TEXT);
                        etElement.setHint("Enter new task");
                        Toast.makeText(MainActivity.this, "Add a new task selected", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnRemove.setEnabled(true);
                        etElement.setInputType(InputType.TYPE_CLASS_NUMBER);
                        etElement.setHint("Enter index of task");
                        Toast.makeText(MainActivity.this, "Remove a task selected", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String task=alTask.get(position);
                Toast.makeText(MainActivity.this, task, Toast.LENGTH_SHORT).show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etElement.getText().toString().length()>0){
                    alTask.add(etElement.getText().toString());
                    aaTask.notifyDataSetChanged();
                    etElement.setText(null);
                    Toast.makeText(MainActivity.this, "Successfully added new task", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Please enter all the values", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(alTask.size()>0){
                    if(etElement.getText().toString().length()>0){
                        if(Integer.parseInt(etElement.getText().toString())<alTask.size()){
                            alTask.remove(Integer.parseInt(etElement.getText().toString()));
                            aaTask.notifyDataSetChanged();
                            etElement.setText(null);
                            Toast.makeText(MainActivity.this, "Successfully removed task",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Index chosen is invalid", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Please provide index of task", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "There is no task to remove", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(alTask.size()>0){
                    alTask.clear();
                    aaTask.notifyDataSetChanged();
                    etElement.setText(null);
                    Toast.makeText(MainActivity.this, "All task removed", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "There is no task to remove", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}