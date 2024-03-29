package org.bank;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;



public class BankAccountTest {
    BankAccount account;

    @BeforeEach
    void setup(){account = new BankAccount(0);}

    @Test
    void Deposit_PositiveAmount_ReturnsNewBalance(){
        //Arrange
        int amount = 10;
        int startingBalance = account.getBalance();
        //Act
        account.deposit(amount);
        //Assert
        assertEquals(account.getBalance(), startingBalance+amount);
    }

    @Test
    void Deposit_NegativeAmount_ThrowsIllegalArgumentException(){
        //Arrange
        int amount = -10;
        //Act
        //Assert
        assertThrows(IllegalArgumentException.class, ()->{account.deposit(amount);});
    }

    @Test
    void Withdraw_ExistingAmount_DecreasesBalanceAndReturnsTrue(){
        //Arrange
        int withdrawal = 5;
        int deposit = 10;
        //Act
        account.deposit(deposit);
        Boolean success = account.withdraw(withdrawal);
        //Assert
        assertTrue(success);
        assertEquals(account.getBalance(), deposit-withdrawal);
    }
    @Test
    void Withdraw_NonexistingAmount_ReturnsFalse(){
        //Arrange
        int withdrawal = 5;
        //Act
        Boolean success = account.withdraw(withdrawal);
        //Assert
        assertFalse(success);
    }

    @Test
    void Payment_ReturnsCorrectAmount(){
        //Arrange
        double total_amount = 10000;
        double interest = 0.001;
        int npayments = 12;
        double expected = 838.7599255697181;
        //Act
        double actual = account.payment(total_amount,interest,npayments);
        //Assert
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "-10000, 0.001, 12",
            "10000, -0.001, 12",
            "10000, 0.001, 0",
            "10000, 0.001, -12"
    })
    void Payment_NegativeInputs_ThrowsIllegalArgumentException(double amount, double inte, int npayments){
        assertThrows(IllegalArgumentException.class, ()->{
            account.payment(amount, inte, npayments);
        });
    }

    @Test
    void Pending_MothZero_ReturnsAmount(){
        //Arrange
        double total_amount = 10000;
        double interest = 0.001;
        int npayments = 12;
        double expected = total_amount;
        //Act
        double actual = account.pending(total_amount,interest,npayments,0);
        //Assert
        assertEquals(expected, actual);
    }
    @Test
    void Pending_NonZeroMonth_ReturnsCorrectAmount(){
        //Arrange
        double total_amount = 10000;
        double interest = 0.001;
        int npayments = 12;
        int month = 2;
        double expected = 8341.651388934994;
        //Act
        double actual = account.pending(total_amount,interest,npayments,month);
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void Pending_MonthGreaterThanNumberOfPayments_ThrowsIllegalArgumentException(){
        //Arrange
        double amount = 10000;
        double interest = 0.001;
        int npayments = 12;
        int month = 13;
        //Act
        //Assert
        assertThrows(IllegalArgumentException.class, ()->{
            account.pending(amount, interest, npayments, month);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "-10000, 0.001, 12, 2",
            "10000, -0.001, 12, 2",
            "10000, 0.001, 0, 2",
            "10000, 0.001, -12, 2",
            "10000, 0.001, 12, -2"
    })
    void Pending_NegativeInputs_ThrowsIllegalArgumentException(double amount, double inte, int npayments, int month){
        assertThrows(IllegalArgumentException.class, ()->{
            account.pending(amount, inte, npayments, month);
        });
    }
}
