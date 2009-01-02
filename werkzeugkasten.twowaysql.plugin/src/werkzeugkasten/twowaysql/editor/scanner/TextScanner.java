package werkzeugkasten.twowaysql.editor.scanner;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.BufferedRuleBasedScanner;
import org.eclipse.jface.text.rules.IRule;

import werkzeugkasten.common.util.Initializable;
import werkzeugkasten.twowaysql.Constants.COLORING;
import werkzeugkasten.twowaysql.editor.conf.ColorDefineFactory;

public class TextScanner extends BufferedRuleBasedScanner implements
		Initializable {

	protected ColorDefineFactory colors;

	public TextScanner(ColorDefineFactory cdf) {
		this.colors = cdf;
	}

	@Override
	public void initialize() {
		setDefaultReturnToken(colors.getToken(COLORING.TXT));
		List<IRule> list = new ArrayList<IRule>();

		setRules(list.toArray(new IRule[list.size()]));
	}

}
