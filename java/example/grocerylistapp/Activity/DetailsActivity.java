package example.grocerylistapp.Activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import example.grocerylistapp.Data.DatabseHandler;
import example.grocerylistapp.R;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabseHandler databseHandler;
    private TextView itemNameDetail, itemQuantityDetails, dateAddedDetails;
    private Button editButtonDetails, deleteButtonDetails;

    private AlertDialog alertDialog;
    private AlertDialog.Builder alertDialogBuilder;
    private Button yesButton, noButton;

    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        databseHandler = new DatabseHandler(this);

        itemNameDetail = (TextView) findViewById(R.id.itemNameDetailsID);
        itemQuantityDetails = (TextView) findViewById(R.id.itemQuantityDetailsID);
        dateAddedDetails = (TextView) findViewById(R.id.dateAddedDetailsID);
        editButtonDetails = (Button) findViewById(R.id.editButtonDetailsID);
        deleteButtonDetails = (Button) findViewById(R.id.deleteButtonDetailsID);

        extras =getIntent().getExtras();

        if(extras != null)
        {
            itemNameDetail.setText(extras.getString("name"));
            itemQuantityDetails.setText(extras.getString("quantity"));
            dateAddedDetails.setText(extras.getString("date"));
        }

        deleteButtonDetails.setOnClickListener(this);
        editButtonDetails.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.editButtonDetailsID:

//                Ye nahi ho raha!
//                MainActivity mainActivity = new MainActivity();
//                mainActivity.createPopup();


//                isliye ye karte hain..
                break;

            case R.id.deleteButtonDetailsID:
                createPopup();
                break;

            case R.id.yesButtonID:

                DatabseHandler databseHandler = new DatabseHandler(this);
                extras = getIntent().getExtras();

                if(extras != null)
                {
                    int id = extras.getInt("id");
                    databseHandler.deleteItem(id);
                    startActivity(new Intent(DetailsActivity.this, ListActivity.class));
                    finish();
                }

                break;

            case R.id.noButtonID:
                alertDialog.dismiss();
                break;
        }

    }

    public void createPopup() {

        alertDialogBuilder = new AlertDialog.Builder(this);

        View view = getLayoutInflater().inflate(R.layout.yes_no_popup, null);

        alertDialogBuilder.setView(view);
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        yesButton = (Button) view.findViewById(R.id.yesButtonID);
        noButton = (Button) view.findViewById(R.id.noButtonID);

        yesButton.setOnClickListener(this);
        noButton.setOnClickListener(this);

        }


}
