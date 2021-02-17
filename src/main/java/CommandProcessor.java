import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import com.google.gson.Gson;

public class CommandProcessor {
    private final List<ComponentGroup> groups;

    public CommandProcessor() {
        this.groups = buildGroups();
    }

    public void ProcessCommand(String[] command) {
        switch (command[0]) {
            case "show current configuration":
                ShowCurrent();
                break;
            case "show groups":
                ShowGroups();
                break;
            case "show components":
                ShowComponents(command);
                break;
            case "show missing configuration":
                ShowMissing();
                break;
            case "set quantity for component":
                SetQuantity(command);
                break;
            case "build":
                Build();
                break;
        }
    }

    // Stuff for doing sth. with the GroupList aka executing the Commands /////////////////////////

    private void ShowCurrent() {
        groups.forEach((g) -> {
            g.getComponents().forEach((c) -> {
                System.out.println(String.format("%s | %s | %d", g.getName(), c.getName(), c.getQuantity()));
            });
        });
    }

    private void ShowGroups() {


        groups.forEach((g) -> {
            System.out.println(g.getName());
        });
    }

    private void ShowComponents(String[] arguments) {

        // show Components Like
        if (arguments[1].equals("like")) {
            Map<String, String> out = new TreeMap<>();
            groups.forEach((g) -> {
                g.getComponents().forEach((c) -> {
                    if (c.getName().contains(arguments[2])) {
                        out.put(c.getName(), String.format("%s | %%s | %d", g.getName(), c.getQuantity()));
                    }
                });
            });

            out.forEach((k, v) -> {
                System.out.println(String.format(v, k));
            });

            // show Components in Group
        } else {

            boolean found = false;

            for (ComponentGroup group: groups) {
                if (group.getName().equals(arguments[2])){
                    found = true;
                    group.getComponents().forEach((c) -> {
                        System.out.println(String.format("%s | %s | %d", c.getComponentGroup().getName(), c.getName(), c.getQuantity()));
                    });
                }
            }

            if (!found) System.out.println(String.format("Group %s does not exist!", arguments[2]));
        }
    }

    private void ShowMissing() {
        List<Component> missing = GetMissing();


        missing.forEach((c) -> {
            System.out.println(String.format("%s | %s | %d", c.getComponentGroup().getName(), c.getName(), c.getQuantity()));
        });
    }

    private void SetQuantity(String[] arguments) {
        groups.forEach((g) -> {
            g.getComponents().forEach((c) -> {
                if (c.getName().equals(arguments[1])) {
                    c.setQuantity(Integer.parseInt(arguments[2]));
                }
            });
        });

    }

    private void Build() {

        List<Component> missing = GetMissing();

        if (!missing.isEmpty()){
            System.out.print("build failed– incomplete configuration for components ");
            for (Component component : missing) {
                System.out.print(component.getName()+ ", ");
            }
            System.out.println();
        } else {
            List<ComponentJSONEntry> entries = new ArrayList<>();

            for (Component component: GetComponents()) {
                entries.add(new ComponentJSONEntry(component.getName(), component.getQuantity()));
            }

            Gson gson = new Gson();
            String configuration =gson.toJson(entries);
            try (PrintWriter out = new PrintWriter("configuration.json")) {
                out.println(configuration);
            } catch (FileNotFoundException e){
                System.out.println("Problems Writing File");
            }
        }


    }

    private List<Component> GetMissing(){

        List<Component> missing = new ArrayList<>();

        for (ComponentGroup group : groups) {
            for (Component component : group.getComponents()) {
                if (component.getQuantity() == 0){
                    missing.add(component);
                }
            }
        }

        Collections.sort(missing, (c0, c1) -> {
            return c0.getName().compareTo(c1.getName());
        });

        return missing;
    }

    private List<Component> GetComponents(){

        List<Component> components = new ArrayList<>();

        for (ComponentGroup group : groups) {
            components.addAll(group.getComponents());
        }

        return components;
    }

    // Stuff for Constructing Group List //////////////////////////////////////////////////////////

    private List<ComponentGroup> buildGroups() {
        Map<String, ComponentGroup> groups = new TreeMap<>();
        List<ComponentGroup> groupsList = new ArrayList<>();

        groupsList.add(buildGroup("apu_engine_gear_pump", new String[]{"apu", "engine", "gear", "hydraulic_pump"}));
        groupsList.add(buildGroup("cabin", new String[]{"air_conditioning", "kitchen"}));
        groupsList.add(buildGroup("cargo_system", new String[]{"cargo_system"}));
        groupsList.add(buildGroup("door", new String[]{"bulk_cargo_door", "emergency_exit_door", "gear_door"}));
        groupsList.add(buildGroup("flight_controls_01", new String[]{"drop_nose", "elevator", "flap", "slat", "left_aileron", "right_aileron", "rudder", "spoiler"}));
        groupsList.add(buildGroup("light", new String[]{"anti_collision_light", "cargo_compartment_light", "landing_light", "left_navigation_light", "logo_light", "right_navigation_light", "tail_navigation_light", "taxi_light"}));
        groupsList.add(buildGroup("management", new String[]{"cost_optimizer", "route_management"}));
        groupsList.add(buildGroup("seat", new String[]{"business_class_seat", "crew_seat", "economy_class_seat", "premium_economy_class_seat"}));
        groupsList.add(buildGroup("sensor_01", new String[]{"fuel_flow_sensor", "fuel_sensor", "ice_detector_probe"}));
        groupsList.add(buildGroup("sensor_02", new String[]{"fire_detector", "oxygen_detector", "shock_sensor", "stalling_sensor", "temperature_sensor"}));
        groupsList.add(buildGroup("sensor_03", new String[]{"airflow_sensor", "pilot_tube", "radar_altimeter", "tcas", "turbulent_airflow_sensor"}));
        groupsList.add(buildGroup("sensor_04", new String[]{"camera", "gps", "radar", "satcom", "vhf"}));
        groupsList.add(buildGroup("tank_bottle", new String[]{"apu_oil_tank", "battery", "deicing_system", "engine_oil_tank", "fuel_tank", "nitrogen_bottle", "oxygen_bottle", "portable_watertank", "wastewater_tank"}));

        Collections.sort(groupsList, (g0, g1) -> {
            return g0.getName().compareTo(g1.getName());
        });

        return groupsList;

    }

    private ComponentGroup buildGroup(String name, String[] componentNames) {
        ComponentGroup group = new ComponentGroup(name);

        for (String componentName : componentNames) {
            group.getComponents().add(new Component(componentName, group));
        }

        return group;
    }
}
