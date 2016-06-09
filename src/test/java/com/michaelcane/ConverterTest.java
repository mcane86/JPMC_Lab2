package com.michaelcane;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterTest {

    private String example1 = "3:45am";
    private String example2 = "12:31p.m.";
    private String example3 = "8:00";
    private String example4 = "21:00";
    private String example5 = "1 (215) 555-1212";

    Converter converter;
    @Before
    public void setUp() throws Exception {
        converter = new Converter();
        converter.populatePhoneNumberMap();
        converter.populateMilitaryTimeMap();
        converter.populateUtilityMap();
        converter.populateTwelveHourMap();
    }

    @Test
    public void testPhoneConstructor() {
        String expected = "One  TwoOneFive  FiveFiveFive OneTwoOneTwo";
        String actual = converter.convertPhoneNumberToText(example5);
        assertEquals(expected, actual);
    }

    @Test
    public void testNumberConverter() {
        String expected = "Three";
        String actual = converter.convertsNumbersToText(example1.charAt(0), converter.populatePhoneNumberMap());
        assertEquals(expected, actual);
    }

    @Test
    public void testInputSplitter() {
        String expected = "12";
        String actual = converter.splitsInputOnDelimiter(example2)[0];
        assertEquals(expected, actual);
    }

    @Test
    public void testFindAMorPM() {
        boolean actual = converter.confirmsIfAnAmOrPmExists(example2);
        assertTrue(actual);
    }

    @Test
    public void testAddAMorPM() {
        String expected = "Post Meridiem";
        String[] newInput = converter.splitsInputOnDelimiter(example2);
        String actual = converter.addTheAMorPMToTheTime(newInput);
        assertEquals(expected, actual);
    }

    @Test
    public void testAddTheZero() {
        String expected = "03";
        String[] newInput = converter.splitsInputOnDelimiter(example1);
        String actual = converter.addsAZeroToTheFirstIndex(newInput)[0];
        assertEquals("Tests to see if a zero is needed to be added to the front of the first element", expected, actual);
    }

    @Test
    public void testTimeConstructor() {
        String expected = "ZeroThree FortyFive Ante Meridiem";
        String actual = converter.convertTimeToText(example1);
        assertEquals(expected, actual);
    }

    @Test
    public void testRealInputTest() {
        boolean actual = converter.testsForARealInput(converter.splitsInputOnDelimiter(example4));
        assertTrue(actual);
    }

    @Test
    public void testMilitaryConstructor() {
        String expected = "ZeroEight ZeroZero Hours";
        String actual = converter.convertTimeToText(example3);
        assertEquals(expected, actual);
    }

}