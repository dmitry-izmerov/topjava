package ru.javawebinar.topjava.util;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * GKislin
 * 07.01.2015.
 */
public class TimeUtil
{
	public static final DateTimeFormatter DATE_TME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	public static final DateTimeFormatter DATE_FORMATTER_PSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static final LocalDate MIN_DATE = LocalDate.of(0, 1, 1);
	public static final LocalDate MAX_DATE = LocalDate.of(3000, 1, 1);

	public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime)
	{
		return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
	}

	public static boolean isBetween(LocalDateTime ldt, LocalDateTime startDateTime, LocalDateTime endDateTime)
	{
		return ldt.compareTo(startDateTime) >= 0 && ldt.compareTo(endDateTime) <= 0;
	}

	public static LocalDate parseLocalDate(String str, LocalDate def)
	{
		return StringUtils.isEmpty(str) ? def : LocalDate.parse(str);
	}

	public static LocalTime parseLocalTime(String str, LocalTime def)
	{
		return StringUtils.isEmpty(str) ? def : LocalTime.parse(str);
	}

	public static String toString(LocalDateTime ldt)
	{
		return ldt == null ? "" : ldt.format(DATE_TME_FORMATTER);
	}

	public static String toPSQLFormat(LocalDateTime ldt)
	{
		return ldt == null ? "" : ldt.format(DATE_FORMATTER_PSQL);
	}

	public static LocalDateTime parsePSQLTimestamp(String s)
	{
		int pointInd = s.indexOf(".");
		if (pointInd != -1) {
			s = s.substring(0, pointInd);
		}
		return LocalDateTime.parse(s, DATE_FORMATTER_PSQL);
	}
}