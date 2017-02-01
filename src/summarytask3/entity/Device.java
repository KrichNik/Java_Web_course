package summarytask3.entity;

/**
 *
 * Class describes computer devices.
 *
 */
public class Device {

	private String name;

	private String origin;

	private Type type;

	private int price;

	private boolean isCritical;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isCritical() {
		return isCritical;
	}

	public void setCritical(boolean isCritical) {
		this.isCritical = isCritical;
	}

	@Override
	public String toString() {
		return "Device: " + name + ", origin: " + origin + ", " + type + ", price: " + price
				+ (isCritical ? ", critical for computer." : ".");
	}

}
