package persistence;

import exceptions.InputTooLongException;
import exceptions.InvalidPassCodeException;
import model.Account;
import model.LoginPassCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class WriterTest {
    private static final String TEST_FILE = "./data/testAccounts.txt";
    private static final String TEST_PASSCODE = "./data/testPassCode.txt";
    private Writer testWriter;
    private Account testAccount;
    private Account otherAccount;
    private LoginPassCode testPassCode;
    private Writer writer;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException,
            InputTooLongException, InvalidPassCodeException {
        testWriter = new Writer(new File(TEST_FILE));
        testAccount = new Account();
        testAccount.addAccountName("gmail");
        testAccount.addPassword("1234567890");
        otherAccount = new Account();
        otherAccount.addAccountName("lego");
        otherAccount.addUserName("Legoman");
        otherAccount.addEmail("parent@outlook.com");
        otherAccount.addPassword("IamIronMan");

        writer = new Writer(new File(TEST_PASSCODE));
        testPassCode = new LoginPassCode();
        testPassCode.changePassCode("9999");
    }

    @Test
    void testWriteAccounts() {
        // save testAccount and otherAccount accounts to file
        testWriter.write(testAccount);
        testWriter.write(otherAccount);
        testWriter.close();

        // now read them back in and verify that the accounts have the expected values
        try {
            List<Account> accounts = Reader.readAccounts(new File(TEST_FILE));
            Account testAccount = accounts.get(0);
            assertEquals("gmail", testAccount.getAccountName());
            assertEquals("n/a", testAccount.getUserName());
            assertEquals("n/a", testAccount.getEmail());
            assertEquals("1234567890", testAccount.getPassword());

            Account otherAccount = accounts.get(1);
            assertEquals("lego", otherAccount.getAccountName());
            assertEquals("Legoman", otherAccount.getUserName());
            assertEquals("parent@outlook.com", otherAccount.getEmail());
            assertEquals("IamIronMan", otherAccount.getPassword());

        } catch (IOException | InputTooLongException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWritePassCode() {
        writer.write(testPassCode);
        writer.close();

        try {
            String passCode = Reader.readPassCode(new File(TEST_PASSCODE));
            assertEquals("9999", passCode);

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}