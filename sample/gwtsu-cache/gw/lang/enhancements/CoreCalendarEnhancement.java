// Compiled from CoreCalendarEnhancement.gsx
public gw.lang.enhancements.CoreCalendarEnhancement extends Object{

  public static int getCalendarEra(Calendar $that$) {
    return $that$.get(Calendar.ERA);
  }

  public static void setCalendarEra(Calendar $that$, int value) {
    $that$.set(Calendar.ERA, value);
    return;
  }

  public static int getCalendarYear(Calendar $that$) {
    return $that$.get(Calendar.YEAR);
  }

  public static void setCalendarYear(Calendar $that$, int value) {
    $that$.set(Calendar.YEAR, value);
    return;
  }

  public static int getCalendarMonth(Calendar $that$) {
    return $that$.get(Calendar.MONTH);
  }

  public static void setCalendarMonth(Calendar $that$, int value) {
    $that$.set(Calendar.MONTH, value);
    return;
  }

  public static Month getCalendarMonthEnum(Calendar $that$) {
    return Month.fromCalendarMonth([[
      *temp0 = $that$;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreCalendarEnhancement.getCalendarMonth(*temp0)
    ]]);
  }

  public static void setCalendarMonthEnum(Calendar $that$, Month month) {
    [[
      *temp0 = $that$;
      *temp1 = month.toCalendarMonth();
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreCalendarEnhancement.setCalendarMonth(*temp0, *temp1)
    ]];
    return;
  }

  public static int getCalendarDay(Calendar $that$) {
    return $that$.get(Calendar.DAY_OF_MONTH);
  }

  public static void setCalendarDay(Calendar $that$, int value) {
    $that$.set(Calendar.DAY_OF_MONTH, value);
    return;
  }

  public static int getCalendarDayOfWeek(Calendar $that$) {
    return $that$.get(Calendar.DAY_OF_WEEK);
  }

  public static void setCalendarDayOfWeek(Calendar $that$, int value) {
    $that$.set(Calendar.DAY_OF_WEEK, value);
    return;
  }

  public static DayOfWeek getCalendarDayOfWeekEnum(Calendar $that$) {
    return DayOfWeek.fromCalendarDayOfWeek([[
      *temp0 = $that$;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreCalendarEnhancement.getCalendarDayOfWeek(*temp0)
    ]]);
  }

  public static void setCalendarDayOfWeekEnum(Calendar $that$, DayOfWeek dayOfWeek) {
    [[
      *temp0 = $that$;
      *temp1 = dayOfWeek.toCalendarDayOfWeek();
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreCalendarEnhancement.setCalendarDayOfWeek(*temp0, *temp1)
    ]];
    return;
  }

  public static int getCalendarHourOfDay(Calendar $that$) {
    return $that$.get(Calendar.HOUR_OF_DAY);
  }

  public static void setCalendarHourOfDay(Calendar $that$, int value) {
    $that$.set(Calendar.HOUR_OF_DAY, value);
    return;
  }

  public static int getCalendarMinute(Calendar $that$) {
    return $that$.get(Calendar.MINUTE);
  }

  public static void setCalendarMinute(Calendar $that$, int value) {
    $that$.set(Calendar.MINUTE, value);
    return;
  }

  public static int getCalendarSecond(Calendar $that$) {
    return $that$.get(Calendar.SECOND);
  }

  public static void setCalendarSecond(Calendar $that$, int value) {
    $that$.set(Calendar.SECOND, value);
    return;
  }

  public static int getCalendarMillisecond(Calendar $that$) {
    return $that$.get(Calendar.MILLISECOND);
  }

  public static void setCalendarMillisecond(Calendar $that$, int value) {
    $that$.set(Calendar.MILLISECOND, value);
    return;
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}