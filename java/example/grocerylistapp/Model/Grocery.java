package example.grocerylistapp.Model;

public class Grocery {

    private String name;
    private String quantity;
    private String itemAdditionDate;
    private int id;

    public Grocery() {
    }

    public Grocery(String name, String quantity, String itemAdditionDate, int id) {
        this.name = name;
        this.quantity = quantity;
        this.itemAdditionDate = itemAdditionDate;

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getItemAdditionDate() {
        return itemAdditionDate;
    }

    public void setItemAdditionDate(String itemAdditionDate) {
        this.itemAdditionDate = itemAdditionDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
