package summarytask1.soundrecording.equipment;

/**
 *
 * Class describes a single track. A track has a name, duration(Min), style and
 * size(Mb). Name, style and duration set in constructor. Size is calculated
 * using duration.
 *
 * @author Nikita Datsenko
 *
 */
public class Track {

	private String name;

	private String style;

	private int duration;

	private int size;

	public Track(String name, String style, int duration) {
		if (duration <= 0) {
			throw new IllegalArgumentException("Invalid duration!");
		}
		this.name = name;
		this.style = style;
		this.duration = duration;
		size = calculateSize(duration);
	}

	public String getStyle() {
		return style;
	}

	public int getDuration() {
		return duration;
	}

	public int getSize() {
		return size;
	}

	private static int calculateSize(int duration) {
		return duration + 1;
	}

	@Override
	public String toString() {
		return name + ": " + "style - " + style + ", duration - " + duration + " min, size - " + size + " Mb";
	}

}
