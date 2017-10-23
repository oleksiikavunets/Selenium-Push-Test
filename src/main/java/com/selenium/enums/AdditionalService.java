package com.selenium.enums;


import java.util.ArrayList;
import java.util.List;

public enum AdditionalService implements GravitecService  {


	TAGS,
	REST_API,
	ALIASES,
	ADVANCED_SEGMENTS,
	CUSTOM_USER_SEGMENTS,
	SDK,
	STANDARD_SEGMENTS,
	UNLIMITED_WEBSITES,
	UNLIMITED_NOTIFICATIONS,
	UNLIMITED_CAMPAIGN,
	OS,
	GEOLOCATION,
	BROWSERS;



	private static List<GravitecService> standardSegmentation = new ArrayList<>();


	private static List<GravitecService> primarySegments = new ArrayList<>();


	private static List<GravitecService> personalNotifications = new ArrayList<>();

	static {
		primarySegments.add(TAGS);
	}

	static {
		standardSegmentation.add(GEOLOCATION);
		standardSegmentation.add(BROWSERS);
	}

	static {
		personalNotifications.add(ALIASES);
	}

	public static List<GravitecService> getStandardSegmentation() {
		return standardSegmentation;
	}

	public static List<GravitecService> getPrimarySegments() {
		return primarySegments;
	}

	public static List<GravitecService> getPersonalNotifications() {
		return personalNotifications;
	}
}
