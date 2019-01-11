package example.grocerylistapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import example.grocerylistapp.Data.DatabseHandler;
import example.grocerylistapp.Model.Grocery;
import example.grocerylistapp.R;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alertDialog;
    private EditText itemName;
    private EditText itemQuantity;
    private Button saveButton;
    private DatabseHandler databseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        byPass();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        databseHandler = new DatabseHandler(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                createPopup();
            }
        });
    }

    private void byPass() {

        DatabseHandler databseHandler  = new DatabseHandler(this);

        if(databseHandler.getCount() > 0)
        {

            startActivity(new Intent(MainActivity.this, ListActivity.class));
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //To create POPUP
    public void createPopup()
    {
        alertDialogBuilder = new AlertDialog.Builder(this);

        //Popup view ko inflate kara
        View view = getLayoutInflater().inflate(R.layout.popup, null);

        //Saare subviews instantiate kare'
        saveButton = (Button) view.findViewById(R.id.saveButton);
        itemName = (EditText) view.findViewById(R.id.itemName);
        itemQuantity = (EditText) view.findViewById(R.id.itemQuantity);

        //Builder se dialog banaya aur phir use display kar diya
        alertDialogBuilder.setView(view);
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo: Save to DB
                //Todo: Go to next screen
                if(!itemName.getText().toString().isEmpty() &&
                        !itemQuantity.getText().toString().isEmpty()) {
                    saveToDB(v);
                }
            }
        });


    }

    private void saveToDB(View view) {

        Grocery grocery = new Grocery();
        grocery.setName(itemName.getText().toString());
        grocery.setQuantity(itemQuantity.getText().toString());
        Log.d("Name", grocery.getName() + grocery.getQuantity());

        databseHandler.addItems(grocery);

        Snackbar.make(view,"Item Saved!", Snackbar.LENGTH_LONG).show();

//        Log.d("No. of items in DB", String.valueOf(databseHandler.getCount()));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                alertDialog.dismiss();
                startActivity(new Intent(MainActivity.this, ListActivity.class));

            }
        }, 1200);
    }
}
