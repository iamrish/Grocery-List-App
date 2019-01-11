package example.grocerylistapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import example.grocerylistapp.Model.Grocery;
import example.grocerylistapp.Util.Constants;

public class DatabseHandler extends SQLiteOpenHelper {

    private Context context;

    public DatabseHandler( @Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_COMM = "CREATE TABLE " + Constants.TABLE_NAME + "(" +
                Constants.KEY_ID + " INTEGER PRIMARY KEY," +
                Constants.KEY_ITEM_NAME + " TEXT," +
                Constants.KEY_ITEM_QUANTITY + " TEXT," +
                Constants.KEY_DATE + " LONG)";

        db.execSQL(CREATE_TABLE_COMM);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }

    //CRUD: Create, Read, Update and Delete Operations

    //Add Items
    public void addItems(Grocery grocery){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.KEY_ITEM_NAME, grocery.getName());
        values.put(Constants.KEY_ITEM_QUANTITY, grocery.getQuantity());
        values.put(Constants.KEY_DATE, java.lang.System.currentTimeMillis());

        database.insert(Constants.TABLE_NAME, null, values);
        database.close();

    }

    //Get an Item
    public Grocery getItems(int id)
    {

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(Constants.TABLE_NAME, new String[] {Constants.KEY_ITEM_NAME,
                Constants.KEY_ITEM_QUANTITY, Constants.KEY_DATE}, Constants.KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null );

        Grocery grocery = new Grocery();
        grocery.setId(Integer.parseInt(cursor.getString(0)));
        grocery.setName(cursor.getString(1));
        grocery.setQuantity(cursor.getString(2));
//        grocery.setItemAdditionDate(cursor.getString(3));

        DateFormat dateFormat = DateFormat.getDateInstance();
        String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE)))
                .getTime());

        grocery.setItemAdditionDate(formattedDate);
        cursor.close();
        database.close();
        return grocery;
    }

    //Get all Items
    public List<Grocery> getAllItems()
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String selectAllQuery = "SELECT * FROM " + Constants.TABLE_NAME;
        List<Grocery> groceryList = new ArrayList<>();
        Cursor cursor = database.rawQuery(selectAllQuery, null);
        if(cursor.moveToFirst())
        {
            do {

                Grocery grocery = new Grocery();
                grocery.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
                grocery.setQuantity(cursor.getString(cursor.getColumnIndex(Constants.KEY_ITEM_QUANTITY)));
                grocery.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_ITEM_NAME)));
                DateFormat dateFormat = DateFormat.getDateInstance();
                String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE)))
                        .getTime());

                grocery.setItemAdditionDate(formattedDate);
                groceryList.add(grocery);


            }while(cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return groceryList;
    }

    //Update Item
    public int updateItem(Grocery grocery)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.KEY_ITEM_QUANTITY, grocery.getQuantity());
        values.put(Constants.KEY_ITEM_NAME, grocery.getName());
        values.put(Constants.KEY_DATE, java.lang.System.currentTimeMillis());

        return database.update(Constants.TABLE_NAME, values, Constants.KEY_ID + "=?",
                new String[] {String.valueOf(grocery.getId())});
    }

    //Delete Items
    public void deleteItem(int id)
    {
        SQLiteDatabase databse = this.getWritableDatabase();
        databse.delete(Constants.TABLE_NAME, Constants.KEY_ID + "=?", new String[] {String.valueOf(id)});
        databse.close();
    }

    //Get Count
    public int getCount()
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String SELECT_ALL_COMM = "SELECT * FROM " + Constants.TABLE_NAME;
        Cursor cursor = database.rawQuery(SELECT_ALL_COMM, null);

        return cursor.getCount();
    }
}
