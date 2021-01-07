package persistence;

import exceptions.InputTooLongException;
import exceptions.InvalidPassCodeException;
import model.Account;
import model.LoginPassCode;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ReaderTest {

    // Dummy test for reader
    @Test
    void testReader() {
        Reader testReader = new Reader();
    }

    @Test
    void testParseAccountsFile1() {
        try {
            List<Account> accounts = Reader.readAccounts(new File("./data/testAccountFile1.txt"));
            Account account1 = accounts.get(0);
            assertEquals("UBC", account1.getAccountName());
            assertEquals("prof321", account1.getUserName());
            assertEquals("prof@ubc.ca", account1.getEmail());
            assertEquals("testpassword10", account1.getPassword());

            Account account2 = accounts.get(1);
            assertEquals("piazza", account2.getAccountName());
            assertEquals("studentA", account2.getUserName());
            assertEquals("n/a", account2.getEmail());
            assertEquals("password", account2.getPassword());

        } catch (IOException | InputTooLongException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWritePassCode() {
        try {
            String testPassCode = Reader.readPassCode(new File("./data/testPassCodeFile.txt"));
            LoginPassCode passCode = new LoginPassCode();
            passCode.changePassCode(testPassCode);
            assertEquals("1234", passCode.getPassCode());
        } catch (IOException | InvalidPassCodeException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readAccounts(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException | InputTooLongException e) {
            // expected
        }
    }
}
