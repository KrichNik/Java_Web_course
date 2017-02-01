package summarytask3.util;

import summarytask3.entity.Computer;
import summarytask3.entity.Device;

import java.util.Collections;
import java.util.Comparator;

/**
 * Contains static methods for sorting.
 *
 */
public class Sorter {

	private static final Comparator<Device> SORT_DEVICES_BY_PRICE = new Comparator<Device>() {
		@Override
		public int compare(Device o1, Device o2) {
			return Integer.valueOf(o1.getPrice()).compareTo(o2.getPrice());
		}
	};

	private static final Comparator<Device> SORT_DEVICES_BY_DEVICE_NAME = new Comparator<Device>() {
		@Override
		public int compare(Device o1, Device o2) {
			return o1.getName().compareTo(o2.getName());
		}
	};

	private static final Comparator<Device> SORT_DEVICES_BY_DEVICE_ORIGIN = new Comparator<Device>() {
		@Override
		public int compare(Device o1, Device o2) {
			return o1.getOrigin().compareTo(o2.getOrigin());
		}
	};

	public static final void sortDevicesByPrice(Computer computer) {
		Collections.sort(computer.getDeviceList(), SORT_DEVICES_BY_PRICE);
	}

	public static final void sortDevicesByDeviceName(Computer computer) {
		Collections.sort(computer.getDeviceList(), SORT_DEVICES_BY_DEVICE_NAME);
	}

	public static final void sortDevicesByDeviceOrigin(Computer computer) {
		Collections.sort(computer.getDeviceList(), SORT_DEVICES_BY_DEVICE_ORIGIN);
	}
}
