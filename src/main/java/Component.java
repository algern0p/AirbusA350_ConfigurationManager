public class Component {
    private final String Name;
    private int Quantity;
    private final String ComponentGroupName;

    public Component(String name, String componentGroup) {
        this.Name = name;
        this.ComponentGroupName = componentGroup;
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

    public String getComponentGroup(){ return ComponentGroupName; }
}
