package main.lesson5;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class CalculatorTest {

    Calculator calc = new Calculator();

    @BeforeClass
    public static void setBefore() throws Exception {

    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAbs() throws Exception {

        assertEquals(5, calc.abs(-5));

    }
}