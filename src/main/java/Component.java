public class Component {
    private final String Name;
    private int Quantity;

    public Component(String name) {
        this.Name = name;
        this.Quantity = 0;
    }

    public String getName() {
        return Name;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
