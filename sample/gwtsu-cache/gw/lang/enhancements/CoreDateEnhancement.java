// Compiled from CoreDateEnhancement.gsx
public gw.lang.enhancements.CoreDateEnhancement extends Object{

  public static Date create(int year, Month month, int day, int hour, int minute, int second, int millisecond, TimeZone timeZone) {
    calendar = (timeZone != null ? Calendar.getInstance(timeZone) : Calendar.getInstance());
    [[
      *temp0 = calendar;
      *temp1 = year;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreCalendarEnhancement.setCalendarYear(*temp0, *temp1)
    ]];
    [[
      *temp2 = calendar;
      *temp3 = (month != null ? month : Month.JANUARY);
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreCalendarEnhancement.setCalendarMonthEnum(*temp2, *temp3)
    ]];
    [[
      *temp4 = calendar;
      *temp5 = day;
      if (*temp4 == null) {
        throw new NullPointerException();
      }
      CoreCalendarEnhancement.setCalendarDay(*temp4, *temp5)
    ]];
    [[
      *temp6 = calendar;
      *temp7 = hour;
      if (*temp6 == null) {
        throw new NullPointerException();
      }
      CoreCalendarEnhancement.setCalendarHourOfDay(*temp6, *temp7)
    ]];
    [[
      *temp8 = calendar;
      *temp9 = minute;
      if (*temp8 == null) {
        throw new NullPointerException();
      }
      CoreCalendarEnhancement.setCalendarMinute(*temp8, *temp9)
    ]];
    [[
      *temp10 = calendar;
      *temp11 = second;
      if (*temp10 == null) {
        throw new NullPointerException();
      }
      CoreCalendarEnhancement.setCalendarSecond(*temp10, *temp11)
    ]];
    [[
      *temp12 = calendar;
      *temp13 = millisecond;
      if (*temp12 == null) {
        throw new NullPointerException();
      }
      CoreCalendarEnhancement.setCalendarMillisecond(*temp12, *temp13)
    ]];
    return calendar.getTime();
  }

  public static Date getNow() {
    return CommonServices.getEntityAccess().getCurrentTime();
  }

  public static Date getToday() {
    cal = [[
      *temp0 = CoreDateEnhancement.getNow();
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreDateEnhancement.toCalendar(*temp0)
    ]];
    [[
      *temp1 = cal;
      *temp2 = 0;
      if (*temp1 == null) {
        throw new NullPointerException();
      }
      CoreCalendarEnhancement.setCalendarMillisecond(*temp1, *temp2)
    ]];
    [[
      *temp3 = cal;
      *temp4 = 0;
      if (*temp3 == null) {
        throw new NullPointerException();
      }
      CoreCalendarEnhancement.setCalendarSecond(*temp3, *temp4)
    ]];
    [[
      *temp5 = cal;
      *temp6 = 0;
      if (*temp5 == null) {
        throw new NullPointerException();
      }
      CoreCalendarEnhancement.setCalendarMinute(*temp5, *temp6)
    ]];
    [[
      *temp7 = cal;
      *temp8 = 0;
      if (*temp7 == null) {
        throw new NullPointerException();
      }
      CoreCalendarEnhancement.setCalendarHourOfDay(*temp7, *temp8)
    ]];
    return cal.getTime();
  }

  public static Date getYesterday() {
    return [[
      *temp0 = CoreDateEnhancement.getToday();
      *temp1 = -1;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreDateEnhancement.addDays(*temp0, *temp1)
    ]];
  }

  public static Date getTomorrow() {
    return [[
      *temp0 = CoreDateEnhancement.getToday();
      *temp1 = 1;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreDateEnhancement.addDays(*temp0, *temp1)
    ]];
  }

  public static int getMillisecondInSecond(Date $that$) {
    return [[
      *temp1 = [[
        *temp0 = $that$;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreDateEnhancement.toCalendar(*temp0)
      ]];
      if (*temp1 == null) {
        throw new NullPointerException();
      }
      CoreCalendarEnhancement.getCalendarMillisecond(*temp1)
    ]];
  }

  public static int getSecond(Date $that$) {
    return GosuDateUtil.getSecond($that$);
  }

  public static int getMinute(Date $that$) {
    return GosuDateUtil.getMinute($that$);
  }

  public static int getHour(Date $that$) {
    return GosuDateUtil.getHour($that$);
  }

  public static boolean isAM(Date $that$) {
    return GosuDateUtil.isAM($that$);
  }

  public static boolean isPM(Date $that$) {
    return GosuDateUtil.isPM($that$);
  }

  public static int getHourOfDay(Date $that$) {
    return GosuDateUtil.getHourOfDay($that$);
  }

  public static int getDayOfWeek(Date $that$) {
    return GosuDateUtil.getDayOfWeek($that$);
  }

  public static DayOfWeek getDayOfWeekEnum(Date $that$) {
    return [[
      *temp1 = [[
        *temp0 = $that$;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreDateEnhancement.toCalendar(*temp0)
      ]];
      if (*temp1 == null) {
        throw new NullPointerException();
      }
      CoreCalendarEnhancement.getCalendarDayOfWeekEnum(*temp1)
    ]];
  }

  public static int getDayOfMonth(Date $that$) {
    return GosuDateUtil.getDayOfMonth($that$);
  }

  public static int getDayOfYear(Date $that$) {
    return GosuDateUtil.getDayOfYear($that$);
  }

  public static int getWeekOfMonth(Date $that$) {
    return GosuDateUtil.getWeekOfMonth($that$);
  }

  public static int getWeekOfYear(Date $that$) {
    return GosuDateUtil.getWeekOfYear($that$);
  }

  public static int getMonthOfYear(Date $that$) {
    return GosuDateUtil.getMonth($that$);
  }

  public static Month getMonthOfYearEnum(Date $that$) {
    return [[
      *temp1 = [[
        *temp0 = $that$;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreDateEnhancement.toCalendar(*temp0)
      ]];
      if (*temp1 == null) {
        throw new NullPointerException();
      }
      CoreCalendarEnhancement.getCalendarMonthEnum(*temp1)
    ]];
  }

  public static Calendar toCalendar(Date $that$) {
    cal = Calendar.getInstance();
    cal.setTime($that$);
    return cal;
  }

  public static Calendar toCalendar(Date $that$, TimeZone tz) {
    cal = Calendar.getInstance(tz);
    cal.setTime($that$);
    return cal;
  }

  public static Calendar toCalendar(Date $that$, Locale locale) {
    cal = Calendar.getInstance(locale);
    cal.setTime($that$);
    return cal;
  }

  public static Calendar toCalendar(Date $that$, TimeZone tz, Locale locale) {
    cal = Calendar.getInstance(tz, locale);
    cal.setTime($that$);
    return cal;
  }

  public static Date addSeconds(Date $that$, int iSeconds) {
    return GosuDateUtil.addSeconds($that$, iSeconds);
  }

  public static Date addMinutes(Date $that$, int iMinutes) {
    return GosuDateUtil.addMinutes($that$, iMinutes);
  }

  public static Date addHours(Date $that$, int iHours) {
    return GosuDateUtil.addHours($that$, iHours);
  }

  public static Date addDays(Date $that$, int iDays) {
    return GosuDateUtil.addDays($that$, iDays);
  }

  public static Date addWeeks(Date $that$, int iWeeks) {
    return GosuDateUtil.addWeeks($that$, iWeeks);
  }

  public static Date addMonths(Date $that$, int iMonths) {
    return GosuDateUtil.addMonths($that$, iMonths);
  }

  public static Date addYears(Date $that$, int iYears) {
    return GosuDateUtil.addYears($that$, iYears);
  }

  public static String toStringWithFormat(Date $that$, String format, TimeZone timeZone, Locale locale) {
    formatter = (locale != null ? new SimpleDateFormat(format, locale) : new SimpleDateFormat(format));
    if (timeZone != null) {
      formatter.setTimeZone(timeZone);
    }
    return formatter.format($that$);
  }

  public static Date toSQLDate(Date $that$) {
    return new Date($that$.getTime());
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    builder.startAnnotationInfoForFeature("@MillisecondInSecond()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "the millisecond, in the range 0-999");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@Second()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "the second, in the range 0-59.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@Minute()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "the minute, in the range 0-59.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@Hour()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The hour of the time. Based on a 12-hour clock, in the range 0-11");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@AM()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "true if the time is AM, false otherwise");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@PM()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "true if the time is PM, false otherwise");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@HourOfDay()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The hour of the time. Based on a 24-hour clock, in the range 0-23");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@DayOfWeek()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The day of the week. Sunday = 1, Monday = 2, ..., Saturday = 7.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@DayOfWeekEnum()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The day of the week. Sunday, Monday, Tuesday, etc.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@DayOfMonth()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The day of the month. The first day = 1.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@DayOfYear()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The day number of the year. The first day = 1.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@WeekOfMonth()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The week of the month. The first week = 1.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@WeekOfYear()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The week of the year. The first week = 1.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@MonthOfYear()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The month of the year, in the range 1-12.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@MonthOfYearEnum()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The month of the year, January, February etc.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("addSeconds(int)");
    builder.addGosuAnnotation(new Param("iSeconds", "The amount of seconds to add."));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "A new date with the seconds added.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("addMinutes(int)");
    builder.addGosuAnnotation(new Param("iMinutes", "The amount of minutes to add."));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "A new date with the minutes added.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("addHours(int)");
    builder.addGosuAnnotation(new Param("iHours", "The amount of hours to add."));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "A new date with the hours added.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("addDays(int)");
    builder.addGosuAnnotation(new Param("iDays", "The amount of days to add."));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "A new date with the days added.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("addWeeks(int)");
    builder.addGosuAnnotation(new Param("iWeeks", "The amount of weeks to add."));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "A new date with the weeks added.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("addMonths(int)");
    builder.addGosuAnnotation(new Param("iMonths", "The amount of months to add."));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "A new date with the months added.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("addYears(int)");
    builder.addGosuAnnotation(new Param("iYears", "The amount of years to add."));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "A new date with the years added.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("toStringWithFormat(java.lang.String, java.util.TimeZone, java.util.Locale)");
    builder.addGosuAnnotation(new Param("format", "a SimpleDataFormat format string, must not be null"));
    builder.addGosuAnnotation(new Param("timeZone", "optional time zone; if omitted or null the default time zone is used"));
    builder.addGosuAnnotation(new Param("locale", "optional locale; if omitted or null the default locale is used"));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "formatted date string");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("MillisecondInSecond");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "the millisecond, in the range 0-999");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("Second");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "the second, in the range 0-59.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("Minute");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "the minute, in the range 0-59.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("Hour");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The hour of the time. Based on a 12-hour clock, in the range 0-11");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("AM");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "true if the time is AM, false otherwise");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("PM");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "true if the time is PM, false otherwise");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("HourOfDay");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The hour of the time. Based on a 24-hour clock, in the range 0-23");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("DayOfWeek");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The day of the week. Sunday = 1, Monday = 2, ..., Saturday = 7.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("DayOfWeekEnum");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The day of the week. Sunday, Monday, Tuesday, etc.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("DayOfMonth");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The day of the month. The first day = 1.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("DayOfYear");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The day number of the year. The first day = 1.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("WeekOfMonth");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The week of the month. The first week = 1.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("WeekOfYear");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The week of the year. The first week = 1.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("MonthOfYear");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The month of the year, in the range 1-12.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("MonthOfYearEnum");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The month of the year, January, February etc.");
    builder.finishJavaAnnotation();
    return builder.getAnnotations();
  }

}