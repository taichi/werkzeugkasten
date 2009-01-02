package werkzeugkasten.twowaysql.editor.scanner;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.BufferedRuleBasedScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.WhitespaceRule;

import werkzeugkasten.common.util.Initializable;
import werkzeugkasten.twowaysql.Constants.COLORING;
import werkzeugkasten.twowaysql.editor.conf.ColorDefineFactory;

public class BlockCommentScanner extends BufferedRuleBasedScanner implements
		Initializable {

	protected ColorDefineFactory colors;

	public BlockCommentScanner(ColorDefineFactory cdf) {
		this.colors = cdf;
	}

	@Override
	public void initialize() {
		setDefaultReturnToken(this.colors.getToken(COLORING.COMMENT));
		List<IRule> list = new ArrayList<IRule>();
		list.add(new WhitespaceRule(new WhitespaceDetector()));

		list.add(new KeywordRule(this.colors.getToken(COLORING.COMMENT),
				this.colors.getToken(COLORING.KEYWORD)));

		setRules(list.toArray(new IRule[list.size()]));
	}
}
