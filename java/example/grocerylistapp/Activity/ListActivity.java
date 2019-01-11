package example.grocerylistapp.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import example.grocerylistapp.Data.DatabseHandler;
import example.grocerylistapp.Model.Grocery;
import example.grocerylistapp.R;
import example.grocerylistapp.UI.RecyclerViewAdapter;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private DatabseHandler databseHandler;
    private List<Grocery> groceryList;
    private List<Grocery> groceryItems;

    public ListActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        databseHandler = new DatabseHandler(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        groceryList = new ArrayList<>();
        groceryItems = new ArrayList<>();

        groceryList = databseHandler.getAllItems();

        for(Grocery g: groceryList)
        {
            Grocery grocery = new Grocery();
            grocery.setName(g.getName());
            grocery.setQuantity("Qty:" + g.getQuantity());
            grocery.setId(g.getId());
            grocery.setItemAdditionDate("Added on:" + g.getItemAdditionDate());
            groceryItems.add(grocery);
        }

        adapter = new RecyclerViewAdapter(this, groceryItems);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

}
