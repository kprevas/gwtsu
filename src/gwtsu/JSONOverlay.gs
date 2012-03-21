package gwtsu

/**
 * Superclass for JSON overlay objects.
 */
abstract class JSONOverlay {

  /**
   * In order to work client-side, subclasses must define a constructor matching this signature exactly.
   */
  construct(json : String) {}

}