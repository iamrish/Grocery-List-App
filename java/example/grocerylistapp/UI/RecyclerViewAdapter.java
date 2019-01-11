/*

    NOT AN ACTIVITY BRO!

 */



package example.grocerylistapp.UI;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import example.grocerylistapp.Activity.DetailsActivity;
import example.grocerylistapp.Activity.ListActivity;
import example.grocerylistapp.Model.Grocery;
import example.grocerylistapp.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Grocery> groceryItems;

    public RecyclerViewAdapter(Context context, List<Grocery> groceryItems) {
        this.context = context;
        this.groceryItems = groceryItems;
    }

    @NonNull
    @Override

    //View bananeka hai idhar xml se!
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_row, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    //Binding data and View
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int i) {

        Grocery grocery = groceryItems.get(i);

        viewHolder.itemName.setText(grocery.getName());
        viewHolder.itemQuantity.setText(grocery.getQuantity());
        viewHolder.dateAdded.setText(grocery.getItemAdditionDate());

    }

    @Override
    public int getItemCount() {
        return groceryItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Button editButton;
        private Button deleteButton;
        private TextView itemName;
        private TextView itemQuantity;
        private TextView dateAdded;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = (TextView) itemView.findViewById(R.id.itemNameRV);
            itemQuantity = (TextView) itemView.findViewById(R.id.itemQuantityRV);
            dateAdded = (TextView) itemView.findViewById(R.id.dateAddedRV);
            editButton = (Button) itemView.findViewById(R.id.editButtonRV);
            deleteButton = (Button) itemView.findViewById(R.id.deleteButtonRV);

            editButton.setOnClickListener(this);
            deleteButton.setOnClickListener(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    Grocery item = new Grocery();
                    item = groceryItems.get(position);

                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("name", item.getName());
                    intent.putExtra("quantity", item.getQuantity());
                    intent.putExtra("date", item.getItemAdditionDate());
                    intent.putExtra("id", item.getId());

                    context.startActivity(intent);


                }
            });

        }

        @Override
        public void onClick(View v) {



        }
    }
}
