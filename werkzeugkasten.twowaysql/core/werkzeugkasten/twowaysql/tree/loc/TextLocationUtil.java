package werkzeugkasten.twowaysql.tree.loc;

public class TextLocationUtil {

	public static String substring(String allOfTxt, Locatable location) {
		return substring(allOfTxt, location.getLocation());
	}

	public static String substring(String allOfTxt, TextLocation location) {
		return allOfTxt.substring(location.startIndex(),
				location.endIndex() + 1);
	}
}
