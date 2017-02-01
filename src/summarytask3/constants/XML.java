package summarytask3.constants;

/**
 * Holds entities declared in XSD document.
 *
 */
public enum XML {
	// elements names
	COMPUTER("Computer"), DEVICE("Device"), NAME("Name"), ORIGIN("Origin"), TYPE("Type"),

	ENERGY_CONCUMPTION("EnergyConsumption"), GROUP("Group"), PORT("Port"),

	// attribute names
	PRICE("price"), CRITICAL("critical"), IS_PERIPHERAL("isPeripheral"), HAS_COOLER("hasCooler");

	private String value;

	XML(String value) {
		this.value = value;
	}

	public boolean equalsTo(String name) {
		return value.equals(name);
	}

	public String value() {
		return value;
	}
}
