public class Component {
    private ComponentGroup Group;
    private String Name;
    private int Quantity;

    public  Component(String name, ComponentGroup group){
        this.Group = group;
        this.Name = name;
    }

    public ComponentGroup getGroup() {
        return Group;
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
