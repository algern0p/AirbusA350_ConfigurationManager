import java.util.ArrayList;
import java.util.List;

public class ComponentGroup {
    private final String Name;
    private final List<Component> components;

    public ComponentGroup(String name) {
        this.Name = name;
        this.components = new ArrayList<>();
    }

    public List<Component> getComponents() {
        return components;
    }

    public String getName() {
        return Name;
    }
}
