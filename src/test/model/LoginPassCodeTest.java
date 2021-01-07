package model;

import exceptions.InvalidPassCodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginPassCodeTest {

    private LoginPassCode testPassCode;

    @BeforeEach
    void runBefore(){
        testPassCode = new LoginPassCode();
    }

    @Test
    void getPassCodeTest() {
        assertEquals(testPassCode.passCode, testPassCode.getPassCode());
    }

    @Test
    void changePassCodeValidInputTest() {
        try {
            testPassCode.changePassCode("1234");
        } catch (InvalidPassCodeException e) {
            fail("Exception should not be caught.");
        }
        assertEquals("1234", testPassCode.getPassCode());
    }

    @Test
    void changePassCodeInvalidInputTest() {
        try {
            testPassCode.changePassCode("password");
            fail("Exception not caught.");
        } catch (InvalidPassCodeException e) {
            System.out.println("InvalidPasscodeException Caught");
        }
        assertEquals("0000", testPassCode.getPassCode());
    }

    @Test
    void validPassCodeTest() {
        assertFalse(testPassCode.validPassCode("123456"));
        assertFalse(testPassCode.validPassCode("password"));
        assertFalse(testPassCode.validPassCode("1234hi"));
        assertFalse(testPassCode.validPassCode("hi1234"));
        assertFalse(testPassCode.validPassCode("000"));
        assertFalse(testPassCode.validPassCode("00"));
        assertTrue(testPassCode.validPassCode("0000"));
        assertTrue(testPassCode.validPassCode("9999"));
        assertTrue(testPassCode.validPassCode("0055"));
    }
}
