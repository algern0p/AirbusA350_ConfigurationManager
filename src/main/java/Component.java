public class Component {
    private final String Name;
    private int Quantity;
    private final ComponentGroup _ComponentGroup;

    public Component(String name, ComponentGroup componentGroup) {
        this.Name = name;
        this._ComponentGroup = componentGroup;
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

    public ComponentGroup getComponentGroup(){ return _ComponentGroup; }
}
