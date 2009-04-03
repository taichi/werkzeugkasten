package werkzeugkasten.twowaysql.editor.reconciler;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.reconciler.DirtyRegion;
import org.eclipse.jface.text.reconciler.IReconcilingStrategy;
import org.eclipse.jface.text.reconciler.IReconcilingStrategyExtension;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;

import werkzeugkasten.twowaysql.editor.QueryProblemAnnotation;
import werkzeugkasten.twowaysql.error.ProblemCoordinator;
import werkzeugkasten.twowaysql.error.QueryProblem;
import werkzeugkasten.twowaysql.error.QueryProblemException;
import werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer;
import werkzeugkasten.twowaysql.grammar.TwoWaySqlParser;
import werkzeugkasten.twowaysql.util.AnnotationUtil;

/**
 * 
 * @author taichi
 * @see org.eclipse.ui.texteditor.MarkerUtilities
 */
public class TwoWaySqlReconcilingStrategy implements IReconcilingStrategy,
		IReconcilingStrategyExtension {

	protected IDocument document;
	protected IProgressMonitor monitor;
	protected ISourceViewer sourceViewer;

	public TwoWaySqlReconcilingStrategy(ISourceViewer sourceViewer) {
		this.sourceViewer = sourceViewer;
	}

	@Override
	public void setProgressMonitor(IProgressMonitor monitor) {
		this.monitor = monitor;
	}

	@Override
	public void setDocument(IDocument document) {
		this.document = document;
	}

	@Override
	public void initialReconcile() {
		reconcile();
	}

	@Override
	public void reconcile(IRegion partition) {
		reconcile();
	}

	@Override
	public void reconcile(DirtyRegion dirtyRegion, IRegion subRegion) {
		reconcile();
	}

	protected void reconcile() {
		IAnnotationModel model = this.sourceViewer.getAnnotationModel();
		AnnotationUtil.removeAllAnnotations(model);

		String string = this.document.get();
		TwoWaySqlParser parser = createParser(string);
		try {
			parser.twowaySQL();
		} catch (RecognitionException e) {
			e.printStackTrace();
		} catch (QueryProblemException e) {
			ProblemCoordinator coordinator = parser.getProblemCoordinator();
			for (QueryProblem qp : coordinator.getAll()) {
				System.out.printf(
						"index:[%s] line:[%s] charPosInline:[%s] token:%s%n",
						qp.getCause().index, qp.getLine(), qp
								.getCharPositionInLine(), qp.getCause().token);
				QueryProblemAnnotation a = new QueryProblemAnnotation(qp);
				model.addAnnotation(a, new Position(qp.getCause().index));
			}
		}
	}

	protected TwoWaySqlParser createParser(String sql) {
		ProblemCoordinator pc = new ProblemCoordinator();
		ANTLRStringStream in = new ANTLRStringStream(sql);
		TwoWaySqlLexer lex = new TwoWaySqlLexer(in) {
			@Override
			public void displayRecognitionError(String[] tokenNames,
					RecognitionException e) {
			}
		};
		CommonTokenStream tokens = new CommonTokenStream(lex);
		TwoWaySqlParser parser = new TwoWaySqlParser(tokens);
		parser.setProblemCoordinator(pc);
		return parser;
	}

}
