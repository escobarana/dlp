package parser;

public class LexerHelper {

	public static int lexemeToInt(String str) {
		try {
			return Integer.parseInt(str);
		}
		catch(NumberFormatException e) {
			System.out.println(e);
		}
		return -1;
	}

	public static char lexemeToChar(String str) {
		// First - Clean the lexeme to have a good input to convert to integer - instead of '1' -> 1
		str = str.replace("'", "");
		if(str.contains("\\")) { // \\n, \\t ...
			str = str.replace("\\", "");
			try { // Convert to an integer and return its value in a char format
				Integer a = Integer.parseInt(str);
				return (char) a.intValue();
			}
			catch(Exception e) {}
			if(str.contains("n"))
				return '\n';
			if(str.contains("t"))
				return '\t';
		}
		return str.charAt(0);
	}

	public static double lexemeToReal(String str) {
		try {
			if(str.toLowerCase().contains("e")) { // Checking
				return Double.parseDouble(str.toLowerCase().split("e")[0]) * Math.pow(10, Double.parseDouble(
						str.toLowerCase().split("e")[1]));
			}
			else {
				return Double.parseDouble(str.toLowerCase());
			}
		}
		catch(NumberFormatException e) {
			System.out.println(e);
		}
		return -1;
	}

}
