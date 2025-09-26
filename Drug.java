public class Drug {

    private String name;
    private int id;
    private double price;
    private String category;
    private int availableQuantity;

    // Constructor to initialize the drug object
    public Drug(String name, int id, double price, String category, int availableQuantity) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.category = category;
        this.availableQuantity = availableQuantity;
    }

    // Getters for accessing drug information
    // setters

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAvailableQuantity() {
        return this.availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    // Method to display drug information using string formatting
    public String getDrugInfo() {
        return String.format("Drug Name: %s\nID: %d\nPrice: $%.2f\nCategory: %s\nAvailable Quantity: %d", name, id,
                price, category, availableQuantity);
    }

}