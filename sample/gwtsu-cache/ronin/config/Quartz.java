// Compiled from Quartz.gs
public ronin.config.Quartz extends Object implements IGosuClassObject {
  internal final static Map TASKS;
  internal static Scheduler _SCHEDULER;

  internal static void <clinit>() {
    Quartz.TASKS = Collections.synchronizedMap(new HashMap());

    return;
  }

  public void <init>() {
    this.<init>();
    return;
  }

  public static Scheduler getScheduler() {
    return Quartz._SCHEDULER;
  }

  public static void setScheduler(Scheduler __value_) {
    Quartz._SCHEDULER = __value_;
    return;
  }

  public static void schedule(IFunction0 task, Integer atSecond, Iterable atSeconds, Integer atMinute, Iterable atMinutes, Integer atHour, Iterable atHours, Integer onDayOfMonth, Iterable onDaysOfMonth, Day onDay, Iterable onDays, Month inMonth, Iterable inMonths, String cronString, String jobName) {
    if (cronString == null) {
      explicitSeconds = false;
      secondString = "0";
      if (atSeconds != null) {
        secondString = [[
          *temp0 = atSeconds;
          *temp1 = TypeSystem.get(java.lang.Integer.class);
          *temp2 = ",";
          if (*temp0 == null) {
            throw new NullPointerException();
          }
          CoreIterableEnhancement.join(*temp0, *temp1, *temp2)
        ]];
        explicitSeconds = true;
      } else {
        if (atSecond != null) {
          secondString = atSecond.toString();
          explicitSeconds = true;
        }
      }
      explicitMinutes = false;
      minuteString = (explicitSeconds ? "*" : "0");
      if (atMinutes != null) {
        minuteString = [[
          *temp3 = atMinutes;
          *temp4 = TypeSystem.get(java.lang.Integer.class);
          *temp5 = ",";
          if (*temp3 == null) {
            throw new NullPointerException();
          }
          CoreIterableEnhancement.join(*temp3, *temp4, *temp5)
        ]];
      } else {
        if (atMinute != null) {
          minuteString = atMinute.toString();
        }
      }
      hourString = ((explicitMinutes || explicitSeconds) ? "*" : "0");
      if (atHours != null) {
        hourString = [[
          *temp6 = atHours;
          *temp7 = TypeSystem.get(java.lang.Integer.class);
          *temp8 = ",";
          if (*temp6 == null) {
            throw new NullPointerException();
          }
          CoreIterableEnhancement.join(*temp6, *temp7, *temp8)
        ]];
      } else {
        if (atHour != null) {
          hourString = atHour.toString();
        }
      }
      dayOfMonthString = "?";
      if (onDaysOfMonth != null) {
        dayOfMonthString = [[
          *temp9 = onDaysOfMonth;
          *temp10 = TypeSystem.get(java.lang.Integer.class);
          *temp11 = ",";
          if (*temp9 == null) {
            throw new NullPointerException();
          }
          CoreIterableEnhancement.join(*temp9, *temp10, *temp11)
        ]];
      } else {
        if (onDayOfMonth != null) {
          dayOfMonthString = onDayOfMonth.toString();
        }
      }
      dayOfWeekString = "?";
      if (EqualityExpressionTransformer.evaluate(dayOfMonthString, TypeSystem.get(java.lang.String.class), true, "?", TypeSystem.get(java.lang.String.class))) {
        if (onDays != null) {
          dayOfWeekString = [[
            *temp17 = [[
              *temp13 = onDays;
              *temp14 = TypeSystem.getByFullName("ronin.config.Quartz.Day", "_default_");
              *temp15 = TypeSystem.get(java.lang.String.class);
              *temp16 = [[
                *temp12 = new block_0_();
                *temp12._returnType = TypeSystem.get(java.lang.String.class);
                *temp12
              ]];
              if (*temp13 == null) {
                throw new NullPointerException();
              }
              CoreIterableEnhancement.map(*temp13, *temp14, *temp15, *temp16)
            ]];
            *temp18 = TypeSystem.get(java.lang.String.class);
            *temp19 = ",";
            if (*temp17 == null) {
              throw new NullPointerException();
            }
            CoreIterableEnhancement.join(*temp17, *temp18, *temp19)
          ]];
        } else {
          if (onDay != null) {
            dayOfWeekString = onDay.getDayCode().toString();
          }
        }
      }
      if ((EqualityExpressionTransformer.evaluate(dayOfWeekString, TypeSystem.get(java.lang.String.class), true, "?", TypeSystem.get(java.lang.String.class)) && EqualityExpressionTransformer.evaluate(dayOfMonthString, TypeSystem.get(java.lang.String.class), true, "?", TypeSystem.get(java.lang.String.class)))) {
        dayOfMonthString = "*";
      }
      monthString = "*";
      if (inMonths != null) {
        monthString = [[
          *temp25 = [[
            *temp21 = inMonths;
            *temp22 = TypeSystem.getByFullName("ronin.config.Quartz.Month", "_default_");
            *temp23 = TypeSystem.get(java.lang.String.class);
            *temp24 = [[
              *temp20 = new block_1_();
              *temp20._returnType = TypeSystem.get(java.lang.String.class);
              *temp20
            ]];
            if (*temp21 == null) {
              throw new NullPointerException();
            }
            CoreIterableEnhancement.map(*temp21, *temp22, *temp23, *temp24)
          ]];
          *temp26 = TypeSystem.get(java.lang.String.class);
          *temp27 = ",";
          if (*temp25 == null) {
            throw new NullPointerException();
          }
          CoreIterableEnhancement.join(*temp25, *temp26, *temp27)
        ]];
      } else {
        if (inMonth != null) {
          monthString = inMonth.getMonthCode().toString();
        }
      }
      cronString = ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), secondString, " ", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), minuteString, TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), " ", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), hourString, TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), " ", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), dayOfMonthString, TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), " ", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), monthString, TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), " ", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), dayOfWeekString, TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false));
    }
    Quartz.scheduleImpl(cronString, jobName, ((Runnable) TypeAsTransformer.coerceValue(task, TypeSystem.get(java.lang.Runnable.class), FunctionToInterfaceCoercer.instance())));
    return;
  }

  public static void maybeStart() {
    if (Quartz.TASKS.size() > 0) {
      Quartz.getScheduler().start();
    }
    return;
  }

  internal static void scheduleImpl(String cronExpression, String jobName, Runnable task) {
    *temp0 = Quartz.TASKS;
    synchronized( *temp0 )    try {
      taskId = Quartz.TASKS.size();
      if (jobName == null) {
        jobName = ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), "RoninJob", Integer.valueOf(taskId), TypeSystem.get(java.lang.String.class), TypeSystem.get(int.class), true, false, false));
      }
      job = new JobDetail(jobName, "RoninGroup", ((Class) TypeAsTransformer.coerceValue(TypeSystem.getByFullName("ronin.config.Quartz.RoninJobClass", "_default_"), TypeSystem.getByFullName("java.lang.Class", "_default_").getParameterizedType([[
        *temp1 = new IType[1];
        *temp1[0] = TypeSystem.getByFullName("ronin.config.Quartz.RoninJobClass", "_default_");
        *temp1
      ]]), MetaTypeToClassCoercer.instance())));
      trigger = new CronTrigger(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), "CronTrigger", Integer.valueOf(taskId), TypeSystem.get(java.lang.String.class), TypeSystem.get(int.class), true, false, false)), "RoninGroup", jobName, "RoninGroup", cronExpression);
      Quartz.TASKS.put(jobName, task);
      if (Quartz._SCHEDULER == null) {
        Quartz._SCHEDULER = new StdSchedulerFactory().getScheduler();
      }
      Quartz._SCHEDULER.scheduleJob(job, trigger);
    }
    finally {
    }
    return;
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}