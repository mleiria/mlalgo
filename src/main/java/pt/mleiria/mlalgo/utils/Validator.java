package pt.mleiria.mlalgo.utils;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * This Class is a utility class. Do not instantiate this class. Contain auxiliary methods.
 */
public final class Validator {

    private static final Logger LOGGER = Logger.getLogger(Validator.class.getName());

    /**
     * Verifies if both Object elements are not null.
     */
    public static final BiPredicate<Object, Object> NOT_NULL = (x, y) -> x != null && y != null;
    private static final Pattern REGEX_THREE_DIGITS_PATTERN = Pattern.compile("\\d{3}");
    public static final Pattern TIME_ZONE_PATTERN = Pattern.compile("[-+0-9]{1}[0-9]{0,4}");

    private static final Predicate<String> IS_NULL = Objects::isNull;

    private static final Predicate<String> IS_EMPTY = String::isEmpty;

    /**
     * Function that performs logical OR operation between 'x' and 'y'
     */
    public static final Function<Boolean, Function<Boolean, Boolean>> isMatchOr = x -> y -> x || y;

    /**
     * Constructs a new BaseValidator. This Class this not mean to instantiate. Returns IllegalStateException.
     */
    private Validator() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Tests if a string is not null and is not empty
     *
     * @return a Predicate
     */
    public static Predicate<String> isPresent() {
        return IS_NULL.negate().and(IS_EMPTY.negate());
    }

    /**
     * Test if the String is not null and not empty
     *
     * @param str the string to test if is not null and not empty
     * @return an Optional if the string is present or empty otherwise
     */
    public static Optional<String> validate(final String str) {
        return isPresent().test(str) ? Optional.of(str) : Optional.empty();
    }

    /**
     * Tests if the object is not null
     *
     * @param t   object to be validated
     * @param <T> the generic param
     * @return Optional of t if t is no null, empty otherwise
     */
    public static <T> Optional<T> validate(final T t) {
        return isNotNull(t) ? Optional.of(t) : Optional.empty();
    }

    /**
     * Tests if a string is null or is empty
     *
     * @return a Predicate
     */
    public static Predicate<String> isNotPresent() {
        return IS_NULL.or(IS_EMPTY);
    }

    /**
     * Receives a list of predicates and apply the and operator
     *
     * @param predicates a list of predicates to test the and operator
     * @param <T>        the type parameter
     * @return a Predicate
     */
    public static <T> Predicate<T> andComparator(final List<Predicate<T>> predicates) {
        return predicates.stream().reduce(s -> true, Predicate::and);
    }

    /**
     * Receives a list of predicates and apply the or operator
     *
     * @param predicates a list of predicates to test the or operator
     * @param <T>        the type parameter
     * @return a Predicate to test
     */
    public static <T> Predicate<T> orComparator(final List<Predicate<T>> predicates) {
        return predicates.stream().reduce(s -> false, Predicate::or);
    }

    /**
     * Test if two elements match (are equals using == operator)
     *
     * @param <U> the type parameter
     * @return a Predicate
     */
    public static <U> BiPredicate<U, U> isEquals() {
        return (u, o) -> u == o;
    }


    /**
     * Tests if the element received passes the Predicate equals ( == )
     *
     * @param t   the type to be compared
     * @param <T> the type
     * @return a Predicate that compares the type received: x -> x == t
     */
    public static <T extends Enum<T>> Predicate<T> isEquals(T t) {
        return x -> x == t;
    }

    /**
     * Tests if the element received passes the Predicate not equals ( != )
     *
     * @param t   the type to be compared
     * @param <T> the type
     * @return a Predicate that compares the type received: x -> x != t
     */
    public static <T extends Enum<T>> Predicate<T> isNotEquals(final T t) {
        return x -> x != t;
    }

    /**
     * Tests if the element received passes the Predicate not equals. Both types are converted
     * to a string representation given in the argument func because the comparison is made using
     * the equals method
     *
     * @param t    the type to be compared
     * @param func a function that transforms the type to an appropriate String representation
     * @param <T>  the type
     * @return a Predicate that compares the type received: x -> x.equals(t).
     */
    public static <T> Predicate<T> isNotEquals(final T t, final Function<T, String> func) {
        if (t == null) {
            return x -> true;
        }
        return x -> x == null || !func.apply(x).equals(func.apply(t));
    }


    /**
     * Checks if the String make match with regex received as parameter.
     *
     * @param input   the String to execute the match with {@code regex}
     * @param pattern to apply the match condition
     * @return true if the String make match with regex received as parameter
     */
    public static boolean isMatch(final String input, final Pattern pattern) {
        return pattern.matcher(input).matches();
    }

    /**
     * Checks if the String does not make match with regex received as parameter.
     *
     * @param input   the String to execute the match with {@code regex}
     * @param pattern to apply the match condition
     * @return true if the String does not make match with regex received as parameter
     */
    public static boolean isNotMatch(final String input, final Pattern pattern) {
        return !isMatch(input, pattern);
    }


    /**
     * Checks if the String does not make match with three digits received.
     *
     * @param input the String to execute the match with {@code regex}
     * @return true if the String does not make match with regex three digits
     */
    public static boolean isNotMatchThreeDigits(final String input) {
        return !REGEX_THREE_DIGITS_PATTERN.matcher(input).matches();
    }

    /**
     * Checks if the element received as parameter is null or missing.
     *
     * @param elem the element to check nullability or missing
     * @return true if the element is <code>null</code> or <code>elem.isEmpty()</code>
     */
    public static boolean isElementNullOrEmpty(final String elem) {
        return IS_NULL.or(IS_EMPTY).test(elem);
    }

    /**
     * Checks if elem contains elemToFindInString
     *
     * @param elem               the element to check if contains elemToFindInString
     * @param elemToFindInString the element to check if it's in the elem
     * @return if elem contains elemToFindInString, else otherwise
     */
    public static boolean contains(final String elem, final String elemToFindInString) {
        if (IS_NULL.negate().and(IS_EMPTY.negate()).test(elem)) {
            return elem.contains(elemToFindInString);
        }
        return false;
    }

    /**
     * Checks if elem contains one of the elements in the list elemToFindInString
     *
     * @param elem                the element to check
     * @param elemsToFindInString the elements to check if it's in the elem
     * @return if elem contains elemToFindInString, else otherwise
     */
    public static boolean containsOneOfTheList(final String elem, final List<String> elemsToFindInString) {
        if (isElementNullOrEmpty(elem)) return false;
        return elemsToFindInString.stream().anyMatch(elem::contains);

    }

    /**
     * Checks if the element received as parameter is not null and not missing.
     *
     * @param elem the element to check nullability or missing
     * @return true if the element is not <code>null</code> or not <code>elem.isEmpty()</code>
     */
    public static boolean isElementNotNullAndNotEmpty(final String elem) {
        return IS_NULL.negate().and(IS_EMPTY.negate()).test(elem);
    }


    /**
     * Note: uses reflection!
     *
     * @param elem Check if the receiver is empty
     * @return true if the receiver is empty.
     * @throws IllegalAccessException if an error occurs in the reflection process
     */

    public static boolean isObjectEmpty(final Object elem) {
        for (final Field f : elem.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (Validator.isNotNull(f.get(elem))) {
                    if (f.get(elem) instanceof Map<?, ?>) {
                        if (!((Map<?, ?>) f.get(elem)).isEmpty()) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            } catch (IllegalAccessException e) {
                LOGGER.severe(e.getMessage());
                return true;
            }
        }
        return true;
    }

    /**
     * Checks if the enum is null or empty.
     *
     * @param elem the element to check nullability/missing
     * @return true if the element is null or missing
     */
    public static boolean isElementNullOrEmpty(final Enum<?> elem) {
        return isNull(elem) || elem.name().isEmpty();
    }

    /**
     * Checks if the element exists but it's empty.
     *
     * @param elem the string to test if it's empty
     * @return true if element exists and is empty
     */
    public static boolean isElementExistsButIsEmpty(final String elem) {
        return isNotNull(elem) && elem.isEmpty();
    }

    /**
     * Checks if the list it's empty.
     * A null list is an empty list
     *
     * @param list the list to test if it's empty
     * @return true if the lost is null or empty
     */
    public static boolean isEmptyList(final List<?> list) {
        return list == null || list.isEmpty();
    }

    /**
     * Checks if the list it's not null and not empty.
     *
     * @param list the list to test
     * @return true if the list is not null and not empty
     */
    public static boolean isNotEmptyList(final List<?> list) {
        return isNotNull(list) && !isEmptyList(list);
    }

    /**
     * Checks if the object is null.
     *
     * @param obj the object to check if it's null
     * @return true if the object is null
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * Returns the object t if it's not null, otherwise returns an object
     * given by the supplier f
     *
     * @param t   the object to check if it's null
     * @param f   supplier that provides a default object to return if t is null
     * @param <T> the generic object type
     * @return the defaultValue object if t is null otherwise t
     */
    public static <T> T isNull(T t, Supplier<T> f) {
        return isNull(t) ? f.get() : t;
    }

    /**
     * Checks if the object is not null.
     *
     * @param obj the object check for nullability
     * @return true if the object is not null
     */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * Negates the object returns the boolean result.
     *
     * @param obj the boolean object
     * @return true, if the object negation is true, false, otherwise
     */
    public static boolean isNot(final boolean obj) {
        return !obj;
    }

    /**
     * A generic test using a bi predicate passed as parameter.
     *
     * @param p    the predicate to evaluate
     * @param argX first argument of the predicate
     * @param argY second argument of the predicate
     * @param <X>  generic type for the first argument
     * @param <Y>  generic type for the second argument
     * @return predicate test (argX, argY)
     */
    public static <X, Y> boolean isValid(final BiPredicate<X, Y> p, final X argX, final Y argY) {
        return p.test(argX, argY);
    }

    /**
     * If the boolean value b is true, the condition Consumer will accept the acceptValue
     *
     * @param b           a boolean that if true the condition will accept the acceptValue
     * @param acceptValue the value to be inserted in the condition
     * @param condition   a Consumer that accepts the acceptValue if the boolean b is true
     * @param <T>         the generic param
     */
    public static <T> void ifTrue(final boolean b, T acceptValue, Consumer<T> condition) {
        if (b) {
            condition.accept(acceptValue);
        }
    }

    /**
     * Binary operator to test to boolean conditions one against the other.
     *
     * @param b1   First boolean condition to be tested
     * @param b2   Second boolean condition to be tested
     * @param func lambda expression to test the b1 and b2 params (might be an OR, AND, NOR,...)
     * @return the boolean result of the test
     */
    public static boolean binaryOperator(final boolean b1, final boolean b2, final BinaryOperator<Boolean> func) {
        return func.apply(b1, b2);
    }

    /**
     * Checks if the element is inside the list
     *
     * @param lst a list with elements
     * @param val an element to check if it's inside the list
     * @param <T> the generic object type
     * @return true if the element belongs to the list, false otherwise
     */
    public static <T> boolean anyMatch(List<T> lst, T val) {
        return lst.stream().anyMatch(elem -> elem.equals(val));

    }

}
