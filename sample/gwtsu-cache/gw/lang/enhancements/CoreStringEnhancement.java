// Compiled from CoreStringEnhancement.gsx
public gw.lang.enhancements.CoreStringEnhancement extends Object{

  public static int getlength(String $that$) {
    return $that$.length();
  }

  public static int getsize(String $that$) {
    return $that$.length();
  }

  public static boolean isHasContent(String $that$) {
    return $that$.length() > 0;
  }

  public static boolean isNotBlank(String $that$) {
    return !(GosuStringUtil.isBlank($that$));
  }

  public static RegExpMatch match(String $that$, String regExp) {
    return GosuStringUtil.match($that$, regExp);
  }

  public static List findDistinctIndicesOf(String $that$, String stringToLookFor) {
    return GosuStringUtil.findDistinctIndexesOf($that$, stringToLookFor);
  }

  public static boolean containsIgnoreCase(String $that$, String searchStr) {
    return GosuStringUtil.containsIgnoreCase($that$, searchStr);
  }

  public static String remove(String $that$, String substring) {
    return GosuStringUtil.remove($that$, substring);
  }

  public static String chomp(String $that$) {
    return GosuStringUtil.chomp($that$);
  }

  public static String chomp(String $that$, String separator) {
    return GosuStringUtil.chomp($that$, separator);
  }

  public static String chop(String $that$) {
    return GosuStringUtil.chop($that$);
  }

  public static String repeat(String $that$, int times) {
    return GosuStringUtil.repeat($that$, times);
  }

  public static String rightPad(String $that$, int newSize) {
    return GosuStringUtil.rightPad($that$, newSize);
  }

  public static String leftPad(String $that$, int newSize) {
    return GosuStringUtil.leftPad($that$, newSize);
  }

  public static String center(String $that$, int newSize) {
    return GosuStringUtil.center($that$, newSize);
  }

  public static String capitalize(String $that$) {
    return GosuStringUtil.capitalize($that$);
  }

  public static String uncapitalize(String $that$) {
    return GosuStringUtil.uncapitalize($that$);
  }

  public static String swapCase(String $that$) {
    return GosuStringUtil.swapCase($that$);
  }

  public static int countMatches(String $that$, String substring) {
    return GosuStringUtil.countMatches($that$, substring);
  }

  public static int countRegexpMatches(String $that$, String regexp) {
    return GosuStringUtil.countRegexpMatches($that$, regexp);
  }

  public static boolean isAlpha(String $that$) {
    return GosuStringUtil.isAlpha($that$);
  }

  public static boolean isAlphaSpace(String $that$) {
    return GosuStringUtil.isAlphaSpace($that$);
  }

  public static boolean isAlphanumeric(String $that$) {
    return GosuStringUtil.isAlphanumeric($that$);
  }

  public static boolean isAlphanumericSpace(String $that$) {
    return GosuStringUtil.isAlphanumericSpace($that$);
  }

  public static boolean isNumeric(String $that$) {
    return GosuStringUtil.isNumeric($that$);
  }

  public static boolean isNumericSpace(String $that$) {
    return GosuStringUtil.isNumericSpace($that$);
  }

  public static boolean isWhitespace(String $that$) {
    return GosuStringUtil.isWhitespace($that$);
  }

  public static String reverse(String $that$) {
    return GosuStringUtil.reverse($that$);
  }

  public static String elide(String $that$, int width) {
    return GosuStringUtil.abbreviate($that$, width);
  }

  public static int getDistanceFrom(String $that$, String otherStr) {
    return GosuStringUtil.getLevenshteinDistance($that$, otherStr);
  }

  public static boolean startsWithIgnoreCase(String $that$, String substring) {
    return GosuStringUtil.startsWithIgnoreCase($that$, substring);
  }

  public static boolean endsWithIgnoreCase(String $that$, String substring) {
    return GosuStringUtil.endsWithIgnoreCase($that$, substring);
  }

  public static String formatMessage(String $that$, Object; args) {
    return MessageFormat.format($that$, args);
  }

  public static Pattern toRegEx(String $that$) {
    return Pattern.compile($that$);
  }

  public static boolean toBoolean(String $that$) {
    return Boolean.parseBoolean($that$);
  }

  public static short toShort(String $that$) {
    return Short.parseShort($that$);
  }

  public static int toInt(String $that$) {
    return Integer.parseInt($that$);
  }

  public static long toLong(String $that$) {
    return Long.parseLong($that$);
  }

  public static float toFloat(String $that$) {
    return Float.parseFloat($that$);
  }

  public static double toDouble(String $that$) {
    return Double.parseDouble($that$);
  }

  public static BigInteger toBigInteger(String $that$) {
    return new BigInteger($that$);
  }

  public static BigDecimal toBigDecimal(String $that$) {
    return new BigDecimal($that$);
  }

  public static Date toDate(String $that$) {
    return new Date($that$);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    builder.startAnnotationInfoForFeature("@length()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.ShortCircuitingProperty.class));
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@size()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.ShortCircuitingProperty.class));
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@HasContent()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "True if this string is not null and has a length > 0");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@NotBlank()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "True if this string is not null and contains at least one non-whitespace character");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("containsIgnoreCase(java.lang.String)");
    builder.addGosuAnnotation(new Param("searchStr", " the String to find, may be null"));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "true if the String contains the search String irrespective of
case or false if not or <code>null</code> string input");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("remove(java.lang.String)");
    builder.addGosuAnnotation(new Param("substring", " the String to search for and remove, may be null"));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "the substring with the string removed if found,
<code>null</code> if null String input");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("chomp()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "String without newline");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("chop()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "String without last character");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("repeat(int)");
    builder.addGosuAnnotation(new Param("times", " number of times to repeat str, negative treated as zero"));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "a new String consisting of the original String repeated");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("rightPad(int)");
    builder.addGosuAnnotation(new Param("size", " the size to pad to"));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "right padded String or original String if no padding is necessary");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("leftPad(int)");
    builder.addGosuAnnotation(new Param("size", " the size to pad to"));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "left padded String or original String if no padding is necessary");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("center(int)");
    builder.addGosuAnnotation(new Param("size", " the int size of new String, negative treated as zero"));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "centered String");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("capitalize()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "the capitalized String");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("uncapitalize()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "the uncapitalized String");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("swapCase()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "the changed String");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("countMatches(java.lang.String)");
    builder.addGosuAnnotation(new Param("sub", " the substring to count, may be null"));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "the number of occurrences, 0 if <code>sub</code> is <code>null</code>");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("countRegexpMatches(java.lang.String)");
    builder.addGosuAnnotation(new Param("regexp", " the regexp to count"));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "the number of occurrences");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@Alpha()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "<code>true</code> if only contains letters, and is non-null");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@AlphaSpace()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "<code>true</code> if only contains letters and space,
and is non-null");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@Alphanumeric()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "<code>true</code> if only contains letters or digits,
and is non-null");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@AlphanumericSpace()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "<code>true</code> if only contains letters, digits or space,
and is non-null");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@Numeric()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "<code>true</code> if only contains digits, and is non-null");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@NumericSpace()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "<code>true</code> if only contains digits or space,
and is non-null");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("@Whitespace()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "<code>true</code> if only contains whitespace, and is non-null");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("reverse()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "the reversed String");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("elide(int)");
    builder.addGosuAnnotation(new Param("width", " maximum length of result String, must be at least 4"));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "abbreviated String");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("getDistanceFrom(java.lang.String)");
    builder.addGosuAnnotation(new Param("otherStr", " the second String, must not be null"));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "result distance");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("startsWithIgnoreCase(java.lang.String)");
    builder.addGosuAnnotation(new Param("prefix", "the prefix to find, may be null"));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "<code>true</code> if the String starts with the prefix, case insensitive");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("endsWithIgnoreCase(java.lang.String)");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "<code>true</code> if the String ends with the suffix, case insensitive");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("length");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.ShortCircuitingProperty.class));
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("size");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.ShortCircuitingProperty.class));
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("HasContent");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "True if this string is not null and has a length > 0");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("NotBlank");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "True if this string is not null and contains at least one non-whitespace character");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("Alpha");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "<code>true</code> if only contains letters, and is non-null");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("AlphaSpace");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "<code>true</code> if only contains letters and space,
and is non-null");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("Alphanumeric");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "<code>true</code> if only contains letters or digits,
and is non-null");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("AlphanumericSpace");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "<code>true</code> if only contains letters, digits or space,
and is non-null");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("Numeric");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "<code>true</code> if only contains digits, and is non-null");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("NumericSpace");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "<code>true</code> if only contains digits or space,
and is non-null");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("Whitespace");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "<code>true</code> if only contains whitespace, and is non-null");
    builder.finishJavaAnnotation();
    return builder.getAnnotations();
  }

}