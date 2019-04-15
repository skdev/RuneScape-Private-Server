package net.scapeemulator.game.util;

public class Utils {
	public static final char[] VALID_CHARS = { '_', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	public static final long stringToLong(String s) {
		int i = s.length();
		long l = 0L;
		for (int j = 0; ~i < ~j; j++) {
			l *= 37L;
			char c = s.charAt(j);
			if (c >= 'A' && c <= 'Z')
				l += (1 + c) - 65;
			else if (c < 'a' || c > 'z') {
				if (c >= '0' && c <= '9')
					l += -48 + (c + 27);
			} else {
				l += -97 + c + 1;
			}
			if (l >= 0x27817226572713dL)
				break;
		}
		for (; (l % 37L) == 0L && l != 0L; l /= 37L)
			;
		return l;
	}
	
	public static final String longToString(long l) {
		if (l <= 0L || -6582952005840035282L >= (l ^ 0xffffffffffffffffL)) {
			return null;
		}
		if (l % 37L == 0L) {
			return null;
		}
		int i = 0;
		for (long l_0_ = l; 0L != l_0_; l_0_ /= 37L) {
			i++;
		}
		final StringBuffer stringbuffer = new StringBuffer(i);
		while (l != 0L) {
			final long l_1_ = l;
			l /= 37L;
			char c = VALID_CHARS[(int) (-(l * 37L) + l_1_)];
			if (c == '_') {
				c = '\u00a0';
				final int i_2_ = stringbuffer.length() - 1;
				stringbuffer.setCharAt(i_2_, Character.toUpperCase(stringbuffer.charAt(i_2_)));
			}
			stringbuffer.append(c);
		}
		stringbuffer.reverse();
		stringbuffer.setCharAt(0, Character.toUpperCase(stringbuffer.charAt(0)));
		return stringbuffer.toString();
	}

}
