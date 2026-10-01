package com.example.geometricstyler.util;

import org.junit.jupiter.api.Test;

import java.util.OptionalDouble;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the rules that stop invalid user input from reaching the model.
 */
class ValidationUtilTest {

    @Test
    void clampKeepsValuesInsideTheRange() {
        assertEquals(5, ValidationUtil.clamp(5, 0, 10));
        assertEquals(0, ValidationUtil.clamp(-40, 0, 10));
        assertEquals(10, ValidationUtil.clamp(99, 0, 10));
    }

    @Test
    void clampReplacesUnusableNumbers() {
        assertEquals(1, ValidationUtil.clamp(Double.NaN, 1, 10));
        assertEquals(1, ValidationUtil.clamp(Double.POSITIVE_INFINITY, 1, 10));
    }

    @Test
    void parseNumberRejectsTextThatIsNotANumber() {
        assertTrue(ValidationUtil.parseNumber("abc").isEmpty());
        assertTrue(ValidationUtil.parseNumber("").isEmpty());
        assertTrue(ValidationUtil.parseNumber(null).isEmpty());
    }

    @Test
    void parseNumberReadsPlainAndNegativeNumbers() {
        OptionalDouble parsed = ValidationUtil.parseNumber(" 42.5 ");
        assertTrue(parsed.isPresent());
        assertEquals(42.5, parsed.getAsDouble());
        assertEquals(-12, ValidationUtil.parseNumber("-12").getAsDouble());
    }

    @Test
    void negativeSizesAreNeverAccepted() {
        assertEquals(Defaults.MIN_SIZE, ValidationUtil.sanitizeSize(-200));
        assertEquals(Defaults.MAX_SIZE, ValidationUtil.sanitizeSize(9999));
    }

    @Test
    void scaleIsNeverZeroOrNegative() {
        assertEquals(Defaults.MIN_SCALE, ValidationUtil.sanitizeScale(0));
        assertEquals(2.0, ValidationUtil.sanitizeScale(-2.0));
    }

    @Test
    void opacityStaysBetweenZeroAndOne() {
        assertEquals(1.0, ValidationUtil.sanitizeOpacity(4.2));
        assertEquals(0.0, ValidationUtil.sanitizeOpacity(-0.5));
    }

    @Test
    void rotationIsNormalisedIntoZeroToThreeSixty() {
        assertEquals(10, ValidationUtil.sanitizeRotation(370));
        assertEquals(350, ValidationUtil.sanitizeRotation(-10));
    }

    @Test
    void rangeCheckRejectsUnusableNumbers() {
        assertFalse(ValidationUtil.isInRange(Double.NaN, 0, 10));
        assertTrue(ValidationUtil.isInRange(10, 0, 10));
    }
}
