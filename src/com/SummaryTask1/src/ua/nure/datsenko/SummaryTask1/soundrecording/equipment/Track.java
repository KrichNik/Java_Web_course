package com.SummaryTask1.src.ua.nure.datsenko.SummaryTask1.soundrecording.equipment;

/**
 *
 * Class describes a single track. A track has a name, duration(Min), style and size(Mb).
 * Name, style and duration set in constructor. Size is calculated using
 * duration.
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		if (duration <= 0) {
			throw new IllegalArgumentException("Invalid duration!");
		}
		this.duration = duration;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException("Invalid size!");
		}
		this.size = size;
	}

	private static int calculateSize(int duration) {
		return duration + 1;
	}
}
