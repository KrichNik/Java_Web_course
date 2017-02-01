package summarytask3.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Class describes device type.
 *
 */
public class Type {

	private int energyConsumption;

	private String group;

	private boolean isPeripheral;

	private boolean hasCooler;

	private List<String> ports = new ArrayList<>();

	public int getEnergyConsumption() {
		return energyConsumption;
	}

	public void setEnergyConsumption(int energyConsumption) {
		this.energyConsumption = energyConsumption;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public boolean isPeripheral() {
		return isPeripheral;
	}

	public void setPeripheral(boolean isPeripheral) {
		this.isPeripheral = isPeripheral;
	}

	public boolean isHasCooler() {
		return hasCooler;
	}

	public void setHasCooler(boolean hasCooler) {
		this.hasCooler = hasCooler;
	}

	public List<String> getPorts() {
		return ports;
	}

	public void setPort(String port) {
		ports.add(port);
	}

	@Override
	public String toString() {
		return "Energy consumption: " + energyConsumption + " W, Group: " + group
				+ (isPeripheral ? ", peripheral" : ", not peripheral") + (hasCooler ? ", cooler." : "") + ", ports: "
				+ ports;
	}
}
