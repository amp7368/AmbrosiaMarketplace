package com.ambrosia.markets.util.emerald;

import am.ik.yavi.core.ValueValidator;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EmeraldsParser {

    private static final BigDecimal BIG_STACK = BigDecimal.valueOf(Emeralds.STACK);
    private static final BigDecimal BIG_LIQUID = BigDecimal.valueOf(Emeralds.LIQUID);
    private static final BigDecimal BIG_BLOCK = BigDecimal.valueOf(Emeralds.BLOCK);
    private static final BigDecimal MAX_EMERALDS = BIG_STACK.multiply(BigDecimal.valueOf(256));

    private static final Pattern EMERALDS_PATTERN = Pattern.compile(
        "^\\s*((\\d+(\\.\\d*)?|\\.\\d+)\\s*STX)?\\s*((\\d+(\\.\\d*)?|\\.\\d+)\\s*LE)?\\s*((\\d+(\\.\\d*)?|\\.\\d+)\\s*EB)?\\s*("
            + "(\\d+)\\s*E)?\\s*$", Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE);
    private static final String EMERALDS_FORMAT_MESSAGE = "Use the format \"23 STX 12 LE 8 EB 56 E\" or \"12.75 STX\".";

    public static ValueValidator<? super Object, ?> validator;

    @Nullable
    public static Emeralds tryParse(String amountArg) {
        try {
            return parse(amountArg);
        } catch (EmeraldParserException e) {
            return null;
        }
    }

    @NotNull
    public static Emeralds parse(String amountArg) throws EmeraldParserException {
        verifyHasUnits(amountArg);

        Matcher match = EMERALDS_PATTERN.matcher(amountArg);
        if (!match.find()) {
            throw new EmeraldParserException("'%s' is not a valid amount! %s".formatted(amountArg, EMERALDS_FORMAT_MESSAGE));
        }

        String stxArg = match.group(2);
        String leArg = match.group(5);
        String ebArg = match.group(8);
        String eArg = match.group(11);
        BigDecimal stx = convertUnit(stxArg, BIG_STACK);
        BigDecimal le = convertUnit(leArg, BIG_LIQUID);
        BigDecimal eb = convertUnit(ebArg, BIG_BLOCK);
        BigDecimal e = convertUnit(eArg, BigDecimal.ONE);
        BigDecimal amount = stx.add(le).add(eb).add(e);

        if (amount.compareTo(MAX_EMERALDS) > 0)
            throw new EmeraldParserException("Amount of '%s' is greater than 64stx!".formatted(amountArg));
        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new EmeraldParserException("Amount of '%s' is 0 emeralds. Positive numbers only!'".formatted(amountArg));
        return Emeralds.of(amount);
    }

    public static void verifyHasUnits(String amountArg) throws NumberFormatException {
        BigDecimal amountWithoutUnits;
        try {
            amountWithoutUnits = new BigDecimal(amountArg.trim()).abs();
        } catch (NumberFormatException e) {
            return;
        }

        if (amountWithoutUnits.compareTo(BigDecimal.valueOf(64)) > 0) {
            EmeraldsFormatter formatter = EmeraldsFormatter.of().setTruncateFields(10);
            String emeralds = formatter.format(Emeralds.of(amountWithoutUnits));
            throw new NumberFormatException("Did you mean %s? %s".formatted(emeralds, EMERALDS_FORMAT_MESSAGE));
        }
        throw new NumberFormatException("Did you mean '%s STX'? %s".formatted(amountArg, EMERALDS_FORMAT_MESSAGE));
    }

    @NotNull
    private static BigDecimal convertUnit(@Nullable String arg, @NotNull BigDecimal unit) {
        if (arg == null) return BigDecimal.ZERO;

        StringBuilder amountArg = new StringBuilder();
        char[] chars = arg.toLowerCase(Locale.ROOT).toCharArray();
        for (char ch : chars) {
            if ((ch >= '0' && ch <= '9') || ch == '.')
                amountArg.append(ch);
        }

        BigDecimal amount = new BigDecimal(amountArg.toString());
        return amount.multiply(unit, Emeralds.MATH_CONTEXT);
    }
}
