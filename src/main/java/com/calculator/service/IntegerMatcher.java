package com.calculator.service;

import com.calculator.exception.InvalidExpressionException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntegerMatcher {

    private static final String INTEGER_PATTERN = "^\\d+";
    private static final Pattern PATTERN = Pattern.compile(INTEGER_PATTERN);

    /**
     * Will match the given input against an Integer pattern and return the matched subsequence.
     * The input string must start with an Integer and can contain any other characters after that.
     *
     * @param expression the expression in which to search for a Integer pattern
     * @return the subsequence representing an Integer
     */
    public static String getMatch(String expression) {

        Matcher matcher = PATTERN.matcher(expression);
        if (!matcher.find()) {
            throw new InvalidExpressionException("Failed to parse integer from expression = " + expression);
        }

        return matcher.group();
    }
}
