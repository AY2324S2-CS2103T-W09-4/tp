package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a policy associated with a person.
 * Guarantees: immutable; policyName is valid as declared in {@link #isValidField(String)}
 */
public class Policy {
    public final String policyName;
    public final String policyNumber;
    public final String premiumTerm;
    public final String premium;
    public final String benefit;

    public static final String MESSAGE_CONSTRAINTS = "Policy Name must be alphanumeric";

    public static final String STRING_VALIDATION_REGEX = "[^\\s].*";
    public static final String NUMBER_VALIDATION_REGEX = "\\d{3,}";

    public Policy(String policyName, String policyNumber, String premiumTerm, String premium, String benefit) {
        requireAllNonNull(policyName);
        checkArgument(isValidField(policyName), MESSAGE_CONSTRAINTS);
        this.policyName = policyName;
        this.policyNumber = policyNumber;
        this.premiumTerm = premiumTerm;
        this.premium = premium;
        this.benefit = benefit;
    }

    /**
     * Returns true if a given string is a valid policy name.
     *
     * @param field The policy name to validate.
     * @return True if the policy name is valid, false otherwise.
     */
    public static boolean isValidField(String field) {
        return field.matches(STRING_VALIDATION_REGEX);
    }

    public static boolean isValidNumber(String field) {
        return field.matches(NUMBER_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return " Policy Name:" +
                policyName +
                " Policy ID: " +
                policyNumber +
                " Premium Term: " +
                premiumTerm +
                " Premium: " +
                premium +
                " Benefit: " +
                benefit;
    }

//    @Override
//    public boolean equals(Object other) {
//        return other == this // short circuit if same object
//                || (other instanceof PolicyName // instanceof handles nulls
//                && value.equals(((PolicyName) other).value)); // state check
//    }
//
//    @Override
//    public int hashCode() {
//        return value.hashCode();
//    }
}
