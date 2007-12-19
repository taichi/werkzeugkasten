package twowaysqleditor;

import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.swt.graphics.RGB;

public class Constants {

	public static final String ID_PLUGIN = "TwoWaySqlEditor";
	/* ---------------------------------------------- */
	public static final RGB BLACK = new RGB(0, 0, 0);

	public static final RGB WHITE = new RGB(255, 255, 255);

	public static final RGB KEYWORD = new RGB(140, 0, 0);

	public static final RGB GREEN = new RGB(0, 140, 0);

	/* ---------------------------------------------- */
	public static final String DEFID_FORMAT = "twowaysqleditor.format";

	/* ---------------------------------------------- */
	public static final ICompletionProposal[] EMPTY_PROPOSAL = new CompletionProposal[0];
}
