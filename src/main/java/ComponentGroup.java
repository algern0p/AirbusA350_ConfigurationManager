import java.util.ArrayList;
import java.util.List;

public class ComponentGroup {
    private String Name;
    private List<Component> components;

    public ComponentGroup(String name) {
        this.Name = name;
        this.components = new ArrayList<Component>();
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public List<Component> getComponents() {
        return components;
    }

    public String getName() {
        return Name;
    }
}
