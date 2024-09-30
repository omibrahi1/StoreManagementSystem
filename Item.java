import java.io.Serializable;

class Item implements Serializable {

    // Serial version UID to support object serialization
    private static final long serialVersionUID = 1L;

    // Instance variables to store item properties
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private ItemType type;


    public Item(int id, String name, String description, double price, int quantity, ItemType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public ItemType getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "Item{"
                + "id=" + id
                + "name='" + name + '\''
                + ", description='" + description + '\''
                + ", price=" + price
                + ", quantity=" + quantity
                + ", type=" + type
                + '}';
    }
}

