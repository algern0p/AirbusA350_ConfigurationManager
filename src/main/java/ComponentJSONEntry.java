public class ComponentJSONEntry {
    private String Name;
    private int Quantity;

    public ComponentJSONEntry(String name, int quantity){
        this.Name = name;
        this.Quantity = quantity;
    }

    public String getName(){
        return Name;
    }

    public int getQuantity(){
        return Quantity;
    }
}
