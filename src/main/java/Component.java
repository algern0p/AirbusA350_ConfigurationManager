public class Component {
    private String Name;
    private int Quantity;

    public  Component(String name){
        this.Name = name;
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
