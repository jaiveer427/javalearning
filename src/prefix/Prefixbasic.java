package prefix;

public class Prefixbasic {

	// Input: strs = ["flower","flow","flight"]
	// Output: "fl"
	public static String longestCommonPrefix(String[] strs) {
		if (strs.length == 0 || strs == null) {
			return "";

		}
		String prefix = strs[0];
		if (prefix == null) {
			return "";
		}
		for (int i = 0; i < strs.length; i++) {
			if (strs[i].isBlank() || strs[i].isEmpty()) {
				return "";
			}
			while (strs[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);

				if (prefix == "") {
					return "";
				}
			}

		}
		return prefix;

	}

	public static String longestCommonPrefixVerticalScanning(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		for (String s : strs) {
			if (s == null) {
				return "";
			}
		}
		String prefix = strs[0];
		for (int i = 0; i < prefix.length(); i++) {
			char c = prefix.charAt(i);

			for (int j = 1; j < strs.length; j++) {

				if (i > strs[j].length() || c != strs[j].charAt(i)) {
					return prefix.substring(0, i);
				}
			}

		}

		return strs[0];

	}

	public static void main(String[] args) {
		String[] strs = { "flower", "flow", "flight" };
		System.out.println(longestCommonPrefix(strs));
		System.out.println(longestCommonPrefixVerticalScanning(strs));

	}

}
