/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author jasper
 */
@WebService(serviceName = "HugeInteger")
public class HugeInteger {

    private final static int MAXIMUM = 100; // maximum number of digits
    public int[] number = new int[MAXIMUM]; // stores the huge integer

    // creates a HugeInteger from a String
    private static HugeInteger parseHugeInteger(String s) {
        HugeInteger temp = new HugeInteger();
        int size = s.length();

        for (int i = 0; i < size; i++) {
            temp.number[i] = s.charAt(size - i - 1) - '0';
        }

        return temp;
    } // end method parseHugeInteger

    // returns a String representation of a HugeInteger
    @Override
    public String toString() {
        String value = "";
        // convert HugeInteger to a String
        for (int digit : number) {
            value = digit + value; // places next digit at beginning of value
        }
        // locate position of first non-zero digit
        int length = value.length();
        int position = -1;
        for (int i = 0; i < length; i++) {
            if (value.charAt(i) != '0') {
                position = i; // first non-zero digit
                break;
            }
        } // end for
        return (position != -1 ? value.substring(position) : "0");
    } // end method toString

    /**
     * Web service operation
     */
    @WebMethod(operationName = "add")
    public String add(@WebParam(name = "first") String first,
            @WebParam(name = "second") String second) {
        int carry = 0; // the value to be carried
        HugeInteger operand1 = HugeInteger.parseHugeInteger(first);
        HugeInteger operand2 = HugeInteger.parseHugeInteger(second);
        HugeInteger result = new HugeInteger(); // stores addition result

        // perform addition on each digit
        for (int i = 0; i < MAXIMUM; i++) {
            // add corresponding digits in each number and the carried value;
            // store result in the corresponding column of HugeInteger result
            result.number[i]
                    = (operand1.number[i] + operand2.number[i] + carry) % 10;

            // set carry for next column
            carry
                    = (operand1.number[i] + operand2.number[i] + carry) / 10;
        } // end for

        return result.toString();

    }

    // WebMethod that subtracts integers represented by String arguments
    @WebMethod(operationName = "subtract")
    public String subtract(@WebParam(name = "first") String first,
            @WebParam(name = "second") String second) {
        HugeInteger operand1 = HugeInteger.parseHugeInteger(first);
        HugeInteger operand2 = HugeInteger.parseHugeInteger(second);
        HugeInteger result = new HugeInteger(); // stores difference

        // subtract bottom digit from top digit
        for (int i = 0; i < MAXIMUM; i++) {
            // if the digit in operand1 is smaller than the corresponding
            // digit in operand2, borrow from the next digit
            if (operand1.number[i] < operand2.number[i]) {
                operand1.borrow(i);
            }

            // subtract digits
            result.number[i] = operand1.number[i] - operand2.number[i];
        } // end for

        return result.toString();
    } // end WebMethod subtract

    // borrow 1 from next digit
    private void borrow(int place) {
        if (place >= MAXIMUM) {
            throw new IndexOutOfBoundsException();
        } else if (number[place + 1] == 0) // if next digit is zero
        {
            borrow(place + 1); // borrow from next digit
        }
        number[place] += 10; // add 10 to the borrowing digit
        --number[place + 1]; // subtract one from the digit to the left

    } // end method borrow

    // WebMethod that returns true if first integer is greater than second
    @WebMethod(operationName = "bigger")
    public boolean bigger(@WebParam(name = "first") String first,
            @WebParam(name = "second") String second) {
        try // try subtracting first from second
        {
            String difference = subtract(first, second);
            return !difference.matches("^[0]+$");
        } // end try
        catch (IndexOutOfBoundsException e) // first is less than //second
        {
            return false;
        } // end catch
    } // end WebMethod bigger

    // WebMethod that returns true if the first integer is less than second
    @WebMethod(operationName = "smaller")
    public boolean smaller(@WebParam(name = "first") String first, @WebParam(name = "second") String second) {
        return bigger(second, first);
    } // end WebMethod smaller

    // WebMethod that returns true if the first integer equals the second
    @WebMethod(operationName = "equals")
    public boolean equals(@WebParam(name = "first") String first, @WebParam(name = "second") String second) {
        return !(bigger(first, second) || smaller(first, second));
    } // end WebMethod equals
}