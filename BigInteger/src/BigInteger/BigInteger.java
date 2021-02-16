package BigInteger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**  CSD 436 - Assignment 1
 *   Iterative Algorithms
 *   Andrea Simental
 *
 *
 */



public class BigInteger {

    private List<Byte> digits;
    private boolean isNegative;

    // default constructor
    public BigInteger() {
        digits = new ArrayList<>();
        digits.add((byte) 0);
        isNegative = false;
    }

    // construct a big integer from a String of digits
    public BigInteger(String strDigits) {
        if (strDigits == null || strDigits.length() == 0) {
            throw new IllegalArgumentException();
        }
        int limit = 0;
        if(strDigits.charAt(0)== '-'){
            limit = strDigits.length() - 1;
            isNegative = true;
        }
        else{
            limit = strDigits.length();
            isNegative = false;
        }
        //Reversing string so we can add it the correct order
        StringBuilder digReversed = new StringBuilder();
        digReversed.append(strDigits);
        digReversed.reverse();
        digits = new ArrayList<>();

        for (int i = 0; i < limit; i++) {
            byte digit = (byte) ((int) digReversed.charAt(i) - '0');
            if (digit < 0 || digit > 9) {
                throw new IllegalArgumentException();
            }
            digits.add(digit);
        }
    }

    // construct a big integer from an integer
    public BigInteger(int nDigits) {
        if (nDigits == 0) {
            digits = new ArrayList<>();
            digits.add((byte) 0);
            return;
        }
        else if(nDigits < 0){
            isNegative = true;
            nDigits *= -1;
        }
        digits = new ArrayList<>();

        while (nDigits > 0) {
            byte d = (byte) (nDigits % 10);
            if (d < 0 || d > 9) {
                throw new IllegalArgumentException();
            }
            digits.add(d);
            nDigits /= 10;
        }
    }

    // construct a big integer from a  big integer
    public BigInteger( BigInteger srcBi ) {
        digits = new ArrayList<>();
        isNegative = srcBi.isNegative;
        digits.addAll(srcBi.digits);
    }

    // construct a big integer from a list of digits
    // with no error checking
    private BigInteger(List<Byte> srcDigits) {
        if (srcDigits == null) {
            throw new IllegalArgumentException();
        }
        this.digits = srcDigits;
    }

    // add two big integers and return a reference to the big integer sum
    public BigInteger add(BigInteger bi) {
        char  sign = '+';
        List<Byte> resultDigits;
        if(isAddition(sign, bi, this)) {
            resultDigits = additions(bi, this);
        }
        else {
            resultDigits = subtractions(bi, this);
        }
        BigInteger result = new BigInteger(resultDigits);
        result.isNegative = setSign(bi, this);
        return result;
    }

    public BigInteger subtract(BigInteger bi) {
        char  sign = '-';
          List<Byte> resultDigits;
        if(!isAddition(sign, bi, this)) {
            resultDigits = subtractions(bi, this);
        }
        else {
            resultDigits = additions(bi, this);
        }
        BigInteger result = new BigInteger(resultDigits);
        result.isNegative = setSign(bi, this);
        return result;
    }

    //Remove zeros at the beginning of the list array when the number is backwards
    public void removeLeadZeros(List<Byte> toRemove){
        Byte lastElement = toRemove.get(toRemove.size()-1);
        if(lastElement == 0){
            toRemove.remove(toRemove.lastIndexOf(lastElement));
        }
    }

    //Method to determine if the numbers need to be added or subtracted
    public Boolean isAddition (char sign,  BigInteger first, BigInteger second){
        BigInteger big;
        BigInteger small;
        if (first.digits.size() > second.digits.size()) {
            big = first;
            small = second;
        } else {
            big = second;
            small = first;
        }
        if(sign == '+'){
            if(!big.isNegative && !small.isNegative ||
                    big.isNegative && small.isNegative){
                return true;
            }
        }
        else if (sign == '-'){
            if(big.isNegative && !small.isNegative){
                return true;
            }
            if(!big.isNegative && small.isNegative){
                return true;
            }
        }
        return false;
    }

    //add any 2 integers
    private List<Byte> additions ( BigInteger first, BigInteger second){
        List<Byte> resultDigits = new ArrayList<>();
        Iterator<Byte> itSmall, itLarge;
        if (first.digits.size() > second.digits.size()) {
            itLarge = first.digits.iterator();
            itSmall = second.digits.iterator();
        } else {
            itLarge = second.digits.iterator();
            itSmall = first.digits.iterator();
        }
        byte carry = 0, rdigit;

        while (itSmall.hasNext()) {
             rdigit = (byte) (itSmall.next() + itLarge.next() + carry);
             if (rdigit > 9) {
                 rdigit -= 10;
                 carry = 1;
             } else {
                 carry = 0;
                }
                resultDigits.add(rdigit);
        }
        while (itLarge.hasNext()) {
            rdigit = (byte) (itLarge.next() + carry);
            if (rdigit > 9) {
                rdigit -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            resultDigits.add(rdigit);
        }
        if (carry == 1) {
           resultDigits.add(carry);
        }
        return resultDigits;
    }

    private List<Byte> subtractions ( BigInteger first, BigInteger second){
        Iterator<Byte> itSmall, itLarge;
        List<Byte> resultDigits = new ArrayList<>();
        if (first.digits.size() > second.digits.size()) {
            itLarge = first.digits.iterator();
            itSmall = second.digits.iterator();
        } else {
            itLarge = second.digits.iterator();
            itSmall = first.digits.iterator();
        }
        byte carry = 0, rdigit = 0, tempSmall, tempBig, temp;

        while (itSmall.hasNext()) {
            tempBig = (byte) (itLarge.next() - carry);
            tempSmall = itSmall.next();
            if (tempBig < tempSmall) {
                carry = 1;
                temp = (byte) (tempBig + 10);
                rdigit = (byte) (temp - tempSmall);
            } else {
                rdigit = (byte) (tempBig - tempSmall);
                carry = 0;
            }
            resultDigits.add(rdigit);
        }

        while (itLarge.hasNext()) {
            rdigit = (byte) (itLarge.next() - carry);
            if (rdigit > 9) {
                rdigit -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            resultDigits.add(rdigit);
        }
        removeLeadZeros(resultDigits);
        return resultDigits;
    }

    //Method to determin if Biginteger needs to be positive or negative
    private Boolean setSign(BigInteger first, BigInteger second){
        BigInteger big;
        if (first.digits.size() > second.digits.size()) {
            big = first;
        } else {
            big = second;
        }
        if(big.isNegative){
            return true;
        }
        return false;

    }

    // output a string representation of the big integer
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (byte d : digits) {
            sb.append(d);
        }
        if(this.isNegative){
            sb.append('-');
        }
        return sb.reverse().toString();
    }

}
