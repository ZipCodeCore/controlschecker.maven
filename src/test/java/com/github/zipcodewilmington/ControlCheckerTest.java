package com.github.zipcodewilmington;


import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author git-leon
 * @version 1.0.0
 * @date 5/19/21 11:02 AM
 */
public class ControlCheckerTest {

    /**
     * algorithm for testing implementation of `ControlChecker.getFireplaceControls`
     * @param insideTemperature temperature of inside climate during this test case
     * @param outsideTemperature temperature of outside climate during this test case
     * @param expected expected output from this test case
     */
    private void testFireplaceControls(int insideTemperature, int outsideTemperature, String expected) {
        // given
        ControlChecker controlChecker = new ControlChecker();

        // when
        String actual = controlChecker.getFireplaceControls(insideTemperature, outsideTemperature);

        // then
        Assert.assertEquals(expected, actual);
    }

    /**
     * algorithm for testing implementation of `ControlChecker.getFuelControls`
     * @param fuelLevel fuel level during this test case
     * @param expected expected output from this test case
     */
    private void testFuelControls(Double fuelLevel, String expected) {
        // given
        ControlChecker controlChecker = new ControlChecker();

        // when
        String actual = controlChecker.getFuelControls(fuelLevel);

        // then
        Assert.assertEquals(expected, actual);
    }

    /**
     * algorithm for testing implementation of `ControlChecker.getThermostatControls`
     * @param insideTemperature temperature of inside climate during this test case
     * @param expected expected output from this test case
     */
    private void testThermostatControls(Integer insideTemperature, String expected) {
        // given
        ControlChecker controlChecker = new ControlChecker();

        // when
        String actual = controlChecker.getThermostatControls(insideTemperature);

        // then
        Assert.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {71, 90, 5000, Integer.MAX_VALUE})
    public void testThermostatControlsLessHeat(Integer insideTemperature) {
        testThermostatControls(insideTemperature, "less heat!");
    }

    @ParameterizedTest
    @ValueSource(ints = {69, 50, -5000, Integer.MIN_VALUE})
    public void testThermostatControlsMoreHeat(Integer insideTemperature) {
        testThermostatControls(insideTemperature, "more heat!");
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.79, 0.5, 0.1, Double.MAX_VALUE})
    public void testFuelControlsBurnFuel(Double fuelLevel) {
        testFuelControls(fuelLevel, "refuel");
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.079, 0.05, 0.001, Double.MIN_VALUE})
    public void testFuelControlsRefuel(Double fuelLevel) {
        testFuelControls(fuelLevel, "refuel");
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, 20, 40, 49, Integer.MIN_VALUE})
    public void testFirePlaceStartFire(Integer outsideTemperature) {
        testFireplaceControls(outsideTemperature, outsideTemperature, "start fireplace!");
    }

    @ParameterizedTest
    @ValueSource(ints = {60, 100, 300, Integer.MAX_VALUE})
    public void testFirePlaceStopFire(Integer insideTemperature) {
        testFireplaceControls(insideTemperature, insideTemperature, "stop fireplace!");
    }
}