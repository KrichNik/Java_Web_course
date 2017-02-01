package summarytask3.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Root container. Class describes computer devices.
 *
 */
public class Computer {

	private List<Device> deviceList = new ArrayList<>();

	public List<Device> getDeviceList() {
		return deviceList;
	}

	@Override
	public String toString() {
		if (deviceList == null || deviceList.size() == 0) {
			return "Computer has no devices";
		}
		StringBuilder result = new StringBuilder();
		for (Device device : deviceList) {
			result.append(device).append('\n');
		}
		return result.toString();
	}

}
