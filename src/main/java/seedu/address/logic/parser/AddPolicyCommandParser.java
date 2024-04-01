package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BENEFIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREMIUM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREMIUM_TERM;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddPolicyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddPolicyCommand object.
 */
public class AddPolicyCommandParser implements Parser<AddPolicyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPolicyCommand
     * and returns a AddPolicyCommand object for execution.
     *
     * @param args The arguments provided by the user.
     * @return A parsed AddPolicyCommand object representing the user's command.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public AddPolicyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_POLICY_NAME, PREFIX_POLICY_TYPE, PREFIX_POLICY_NUMBER, PREFIX_PREMIUM_TERM, PREFIX_PREMIUM,
                PREFIX_BENEFIT);

        if (!arePrefixesPresent(argMultimap, PREFIX_POLICY_NAME, PREFIX_POLICY_TYPE, PREFIX_POLICY_NUMBER,
                PREFIX_PREMIUM_TERM, PREFIX_PREMIUM, PREFIX_BENEFIT) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPolicyCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_POLICY_NAME, PREFIX_POLICY_TYPE, PREFIX_POLICY_NUMBER,
                PREFIX_PREMIUM_TERM, PREFIX_PREMIUM, PREFIX_BENEFIT);

        Index index;
        String policyName = argMultimap.getValue(PREFIX_POLICY_NAME).orElse(null);
        String policyType = argMultimap.getValue(PREFIX_POLICY_TYPE).orElse(null);
        String policyNumber = argMultimap.getValue(PREFIX_POLICY_NUMBER).orElse(null);
        String premiumTerm = argMultimap.getValue(PREFIX_PREMIUM_TERM).orElse(null).toUpperCase();
        String premium = argMultimap.getValue(PREFIX_PREMIUM).orElse(null);
        String benefit = argMultimap.getValue(PREFIX_BENEFIT).orElse(null);

        index = ParserUtil.parseIndex(argMultimap.getPreamble());
        String newPolicyName = ParserUtil.parsePolicyName(policyName);
        String newPolicyType = ParserUtil.parsePolicyType(policyType);
        String newPolicyNumber = ParserUtil.parsePolicyNumber(policyNumber);
        String newPremiumTerm = ParserUtil.parsePremiumTerm(premiumTerm);
        String newPremium = ParserUtil.parsePremium(premium);
        String newBenefit = ParserUtil.parseBenefit(benefit);

        return new AddPolicyCommand(index, newPolicyName, newPolicyType, newPolicyNumber, newPremiumTerm,
                newPremium, newBenefit);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
