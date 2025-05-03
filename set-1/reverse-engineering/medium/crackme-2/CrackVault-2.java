import java.util.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
class CrackVault {
	public static void main(String args[]) {
		Scanner b = new Scanner(System.in);
		System.out.print("Enter vault password: ");
		String c = b.next();
		String f = c.substring("mobile_mafia{".length(),c.length()-1);
		CrackVault a = new CrackVault();
		if (a.checkPassword(f))
		{
			System.out.println("Access granted.");
		}
		else
		{
			System.out.println("Access denied!");
		}
	}

	public char[] scramble(String password){
		/* Scramble a password by transposing pairs of bits. */
		char[] a = password.toCharArray();
		for (int b=0; b<a.length; b++)
		{
			char c = a[b];
			c = switchBits(c,1,2);
			c = switchBits(c,0,3);
			
			c = switchBits(c,5,6);
			c = switchBits(c,4,7);
			c = switchBits(c,0,1);
			
			c = switchBits(c,3,4);
			c = switchBits(c,2,5);
			c = switchBits(c,6,7);
			a[b] = c;
		}
		return a;
	}
	public char switchBits(char c, int p1, int p2){
		/*
		 Move the bit in position p1 to position p2, and move the bit
		 that was in position p2 to position p1. Precondition: p1 < p2
		*/
		char mask1 = (char)(1 << p1);
		char mask2 = (char)(1 << p2);

		char bit1 = (char)(c & mask1);
		char bit2 = (char)(c & mask2);

		char rest = (char)(c & ~(mask1 | mask2));
		char shift = (char)(p2 - p1);
		char result = (char)((bit1<<shift) | (bit2>>shift) | rest);
		return result;
	}
	public boolean checkPassword(String password){
		char[] scrambled = scramble(password);
		char[] expected = { 0xA4, 0xD0, 0xC5, 0x77, 0xA5, 0x87, 0xD0, 0xC4, 0xC4, 0xF0, 0xE4, 0x77, 0xE4, 0xF0, 0xE5, 0xF0, 0xE4, 0xD1, 0xF0, 0x77, 0xC5, 0xC0, 0x77, 0xB5, 0xF0, 0xC5, 0x77, 0xA5, 0x87, 0xC1, 0xB5, 0x77, 0xD2, 0xD1 };
		return Arrays.equals(scrambled, expected);
	}
}
