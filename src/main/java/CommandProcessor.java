import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CommandProcessor {
    private Map<String, ComponentGroup> groups;

    public CommandProcessor() {
        this.groups = buildGroupMap();
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
                break;
        }
    }

    // Stuff for doing sth. with the GroupList aka executing the Commands /////////////////////////

    private void ShowCurrent() {
        groups.forEach((k, v) -> {
            v.getComponents().forEach((c) -> {
                if (c.getQuantity() == 0) {
                    System.out.println(String.format("%s | %s | %d", v.getName(), c.getName(), c.getQuantity()));
                }
            });
        });
    }

    private void ShowGroups() {


        groups.forEach((k, v) -> {
            System.out.println(k);
        });
    }

    private void ShowComponents(String[] arguments) {

        // show Components Like
        if (arguments[1].equals("like")) {
            Map<String, String> out = new TreeMap<>();
            groups.forEach((k, v) -> {
                v.getComponents().forEach((c) -> {
                    if (c.getName().contains(arguments[2])) {
                        out.put(c.getName(), String.format("%s | %%s | %d", v.getName(), c.getQuantity()));
                    }
                });
            });

            out.forEach((k, v) -> {
                System.out.println(String.format(v, k));
            });

            // show Components in Group
        } else {
            groups.get(arguments[2]).getComponents().forEach((c) -> {
                System.out.println(String.format("%s | %s | %d", arguments[2], c.getName(), c.getQuantity()));
            });
        }
    }

    private void ShowMissing() {
        Map<String, String> out = new TreeMap<>();
        groups.forEach((k, v) -> {
            v.getComponents().forEach((c) -> {
                if (c.getQuantity() == 0) {
                    out.put(c.getName(), String.format("%s | %%s | %d", v.getName(), c.getQuantity()));
                }
            });
        });

        out.forEach((k, v) -> {
            System.out.println(String.format(v, k));
        });
    }

    private void SetQuantity(String[] arguments) {
        groups.forEach((k, v) -> {
            v.getComponents().forEach((c) -> {
                if (c.getName().equals(arguments[1])) {
                    c.setQuantity(Integer.parseInt(arguments[2]));
                }
            });
        });

    }

    private void Build() {

    }

    // Stuff for Constructing Group List //////////////////////////////////////////////////////////

    private Map<String, ComponentGroup> buildGroupMap() {
        Map<String, ComponentGroup> groups = new TreeMap<String, ComponentGroup>();
        List<ComponentGroup> groupsList = new ArrayList<>();

        groupsList.add(buildGroup("apu_engine_gear_pump", new String[]{"apu", "engine", "gear", "hydraulic_pump"}));
        groupsList.add(buildGroup("cabin", new String[]{"air_conditioning", "kitchen"}));
        groupsList.add(buildGroup("cargo_system", new String[]{"cargo_system"}));
        groupsList.add(buildGroup("door", new String[]{"bulk_cargo_door", "emergency_exit_door", "gear_door"}));
        groupsList.add(buildGroup("flight_controls_01", new String[]{"drop_nose", "elevaor", "flap", "slat", "left_aileron", "right_aileron", "rudder", "spoiler"}));
        groupsList.add(buildGroup("light", new String[]{"anti_collision_light", "cargo_compartment_light", "landing_ligth", "left_navigation_light", "logo_light", "right_navigation_light", "tail_navigation_light", "taxi_light"}));
        groupsList.add(buildGroup("management", new String[]{"cost_optimizer", "route_management"}));
        groupsList.add(buildGroup("seat", new String[]{"business_class_seat", "crew_seat", "economy_class_seat", "premium_economy_class_seat"}));
        groupsList.add(buildGroup("sensor_01", new String[]{"fuel_flow_sensor", "fuel_sensor", "ice_detector_probe"}));
        groupsList.add(buildGroup("sensor_02", new String[]{"fire_detector", "oxygen_detector", "shock_sensor", "stalling_sensor", "temperature_sensor"}));
        groupsList.add(buildGroup("sensor_03", new String[]{"airflow_sensor", "pilot_tube", "radar_altimeter", "tcas", "turbulent_airflow_sensor"}));
        groupsList.add(buildGroup("sensor_04", new String[]{"camera", "gps", "radar", "satcom", "vhf"}));
        groupsList.add(buildGroup("tank_bottle", new String[]{"apu_oil_tank", "battery", "deicing_system", "engine_oil_tank", "fuel_tank", "nitrogen_bottle", "oxygen_bottle", "portable_watertank", "wastewater_tank"}));

        for (ComponentGroup componentGroup : groupsList) {
            groups.put(componentGroup.getName(), componentGroup);
        }

        return groups;

    }

    private ComponentGroup buildGroup(String name, String[] componentNames) {
        ComponentGroup group = new ComponentGroup(name);

        for (String componentName : componentNames) {
            group.getComponents().add(new Component(componentName));
        }

        return group;
    }
}
