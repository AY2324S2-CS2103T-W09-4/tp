package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Client's meeting time in the address book.
 * Guarantees: immutable; is always valid
 */
public class Meeting implements Comparable<Meeting> {
    public static final String MESSAGE_CONSTRAINTS =
            "Meeting MUST be a valid date and time in yyyy-MM-dd HH:mm format";

    public static final String FUTURE_MEETING_MESSAGE_CONSTRAINTS =
            "Meeting MUST be AFTER the current day and time.";
    // The VALIDATION_REGEX for meeting time
    public static final String VALIDATION_REGEX = "^(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2})$";
    public final String value;
    public final LocalDateTime meeting;
    private String name;

    /**
     * Constructs a {@code Meeting}.
     */
    public Meeting(String meeting) {
        requireNonNull(meeting);
        this.value = meeting;
        this.meeting = stringToDateTime(meeting);
    }

    /**
     * Constructs a {@code Meeting} with a name.
     */
    public Meeting(String meeting, String name) {
        this(meeting);
        this.name = name;
    }

    public LocalDateTime getMeeting() {
        return meeting;
    }

    public String getValue() {
        return value;
    }

    /**
     * Returns true if a given string is a valid meeting.
     */
    public static boolean isValidMeeting(String test) {
        try {
            if (!test.matches(VALIDATION_REGEX)) { // Check for YYYY-MM-DD HH:MM format
                return false;
            }
            stringToDateTime(test); // Check for valid date and time
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Returns true if a given string is after LocalDatTime now.
     */
    public static boolean isFutureMeeting(String test) {
        try {
            return stringToDateTime(test).isAfter(LocalDateTime.now());
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * This method converts a string into a LocalDateTime object.
     * The string must be in the format "yyyy-MM-dd HH:mm".
     *
     * @param dateTime The string to be converted into a LocalDateTime object.
     * @return A LocalDateTime object that represents the date and time specified by the input string.
     */
    private static LocalDateTime stringToDateTime(String dateTime) {
        if (dateTime.length() != 16) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        try {
            LocalDate dateCheck = LocalDate.parse(dateTime.substring(0, 10)); // Check that the date is valid.
            LocalTime timeCheck = LocalTime.parse(dateTime.substring(11)); // Check that the time is valid.
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }

    public void setName(String finalName) {
        this.name = finalName;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return meeting.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return this.getMeeting().equals(otherMeeting.getMeeting());
    }

    @Override
    public int hashCode() {
        return meeting.hashCode();
    }

    @Override
    public int compareTo(Meeting o) {
        return this.meeting.compareTo(o.meeting);
    }

    /**
     * Returns true if both Meeting have the same time.
     * This defines a weaker notion of equality between two meetings.
     */
    public boolean isSameMeeting(Meeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null
                && otherMeeting.getMeeting().equals(getMeeting());
    }
}
