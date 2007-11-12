package werkzeugkasten.weblauncher.preferences;

import static werkzeugkasten.weblauncher.Constants.*;
import static werkzeugkasten.weblauncher.nls.Strings.*;

import java.util.regex.Pattern;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import werkzeugkasten.common.resource.ProjectUtil;
import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.common.wiget.ResourceTreeSelectionDialog;
import werkzeugkasten.weblauncher.Activator;
import werkzeugkasten.weblauncher.preferences.impl.WebPreferencesImpl;

/**
 * @author taichi
 * 
 */
public class WebPreferencesPage extends PropertyPage {

	private Pattern numeric = Pattern.compile("\\d*");

	private Button useWebLauncher;

	private Text contextName;

	private Text baseDir;

	private Text webPortNo;

	private Text config;

	private Button isCheckServer;

	private Button isDebug;

	private Button useInternalWebBrowser;

	protected IProject project;
	protected IJavaProject javaP;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		GridData data = new GridData(GridData.FILL);
		data.grabExcessHorizontalSpace = true;
		composite.setLayoutData(data);

		this.useWebLauncher = new Button(createDefaultComposite(composite),
				SWT.CHECK);
		this.useWebLauncher.setText(LABEL_USE_WEB_LAUNCHER);

		this.contextName = createPart(composite, LABEL_CONTEXT_NAME);
		this.baseDir = createBaseDir(composite);
		this.webPortNo = createPart(composite, LABEL_WEB_PORTNO);
		NumberVerifier nv = new NumberVerifier();
		this.webPortNo.addModifyListener(nv);
		this.config = createConfig(composite);

		this.isCheckServer = new Button(createDefaultComposite(composite),
				SWT.CHECK);
		this.isCheckServer.setText(LABEL_CHECK_SERVER);
		this.isCheckServer.setToolTipText(TOOLTIP_CHECK_SERVER);
		this.isDebug = new Button(createDefaultComposite(composite), SWT.CHECK);
		this.isDebug.setText(LABEL_IS_DEBUG);
		this.useInternalWebBrowser = new Button(
				createDefaultComposite(composite), SWT.CHECK);
		this.useInternalWebBrowser.setText(LABEL_USE_INTERNAL_WEBBROWSER);

		setUpStoredValue();
		return composite;
	}

	private Text createPart(Composite composite, String label) {
		return createPart(composite, label, SWT.SINGLE | SWT.BORDER);
	}

	private Text createPart(Composite composite, String label, int style) {
		Label l = new Label(composite, SWT.NONE);
		l.setText(label);
		Text txt = new Text(composite, style);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		txt.setLayoutData(data);
		return txt;
	}

	private class NumberVerifier implements ModifyListener {
		public void modifyText(ModifyEvent e) {
			if (e.widget instanceof Text) {
				Text t = (Text) e.widget;
				boolean is = false;
				if (is = numeric.matcher(t.getText()).matches()) {
					setErrorMessage(null);
				} else {
					setErrorMessage(NLS.bind(ERR_ONLY_NUMERIC, "Port No"));
				}
				setValid(is);
			}
		}
	}

	private void setUpStoredValue() {
		WebPreferences wp = Activator.getPreferences(getProject());
		this.useWebLauncher.setSelection(ProjectUtil.hasNature(getProject(),
				ID_NATURE));
		this.contextName.setText(wp.getContextName());
		this.baseDir.setText(wp.getBaseDir());
		this.webPortNo.setText(wp.getWebPortNo());
		this.config.setText(wp.getConfig());
		this.isCheckServer.setSelection(wp.checkServerWhenOpen());
		this.isDebug.setSelection(wp.isDebug());
		this.useInternalWebBrowser.setSelection(wp.useInternalWebBrowser());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#doGetPreferenceStore()
	 */
	protected IPreferenceStore doGetPreferenceStore() {
		IProject project = getProject();
		ScopedPreferenceStore store = null;
		if (project != null) {
			store = new ScopedPreferenceStore(new ProjectScope(project),
					ID_PLUGIN);
			WebPreferencesImpl.setupPreferences(project, store);
			setPreferenceStore(store);
		}
		return store;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	protected void performDefaults() {
		IPreferenceStore store = getPreferenceStore();
		this.useWebLauncher.setSelection(false);
		this.contextName.setText(store.getDefaultString(PREF_CONTEXT_NAME));
		this.baseDir.setText(store.getDefaultString(PREF_BASE_DIR));
		this.webPortNo.setText(store.getDefaultString(PREF_WEB_PORTNO));
		this.config.setText(store.getDefaultString(PREF_CONFIG));
		this.isCheckServer.setSelection(store
				.getDefaultBoolean(PREF_CHECK_SERVER));
		this.isDebug.setSelection(store.getDefaultBoolean(PREF_IS_DEBUG));
		this.useInternalWebBrowser.setSelection(store
				.getDefaultBoolean(PREF_USE_INTERNAL_WEBBROWSER));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	public boolean performOk() {
		boolean result = false;
		IProject project = getProject();
		try {
			if (project != null) {
				IPreferenceStore store = getPreferenceStore();
				if (this.useWebLauncher.getSelection()) {
					ProjectUtil.addNature(project, ID_NATURE);
				} else {
					ProjectUtil.removeNature(project, ID_NATURE);
				}

				store.setValue(PREF_CONTEXT_NAME, this.contextName.getText());
				store.setValue(PREF_BASE_DIR, this.baseDir.getText());
				store.setValue(PREF_WEB_PORTNO, this.webPortNo.getText());
				store.setValue(PREF_CONFIG, this.config.getText());

				boolean is = this.isCheckServer.getSelection();
				store.setValue(PREF_CHECK_SERVER, is);
				if (is) {
					project.setPersistentProperty(KEY_CHECK_SERVER, String
							.valueOf(is));
				} else {
					project.setPersistentProperty(KEY_CHECK_SERVER, null);
				}

				store.setValue(PREF_IS_DEBUG, this.isDebug.getSelection());
				store.setValue(PREF_USE_INTERNAL_WEBBROWSER,
						this.useInternalWebBrowser.getSelection());
				if (store instanceof IPersistentPreferenceStore) {
					IPersistentPreferenceStore pps = (IPersistentPreferenceStore) store;
					pps.save();
				}
				result = true;
			}
		} catch (Exception e) {
			Activator.log(e);
		}

		return result;
	}

	private Composite createDefaultComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginWidth = 0;
		composite.setLayout(layout);

		GridData data = new GridData(GridData.FILL);
		data.horizontalSpan = 2;
		composite.setLayoutData(data);

		return composite;
	}

	private Text createBaseDir(Composite parent) {
		Label l = new Label(parent, SWT.NONE);
		l.setText(LABEL_BASE_DIR);

		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 0;
		composite.setLayout(layout);

		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		composite.setLayoutData(data);

		final Text t = new Text(composite, SWT.SINGLE | SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		t.setLayoutData(data);
		Button b = new Button(composite, SWT.PUSH);
		b.setText(LABEL_BROWSE);
		b.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ResourceTreeSelectionDialog dialog = new ResourceTreeSelectionDialog(
						getShell(), getProject().getParent(), IResource.FOLDER
								| IResource.PROJECT);
				dialog.setInitialSelection(getProject());
				dialog.setAllowMultiple(false);
				if (dialog.open() == Dialog.OK) {
					Object[] results = dialog.getResult();
					if (results != null && 0 < results.length) {
						IResource r = (IResource) results[0];
						t.setText(r.getFullPath().toString());
					}
				}
			}
		});

		return t;
	}

	private Text createConfig(Composite parent) {
		Link l = new Link(parent, SWT.NONE);
		l.setText(LABEL_CONFIG);

		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 0;
		composite.setLayout(layout);

		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		composite.setLayoutData(data);

		final Text t = new Text(composite, SWT.SINGLE | SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		t.setLayoutData(data);
		Button b = new Button(composite, SWT.PUSH);
		b.setText(LABEL_BROWSE);
		b.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ResourceTreeSelectionDialog dialog = new ResourceTreeSelectionDialog(
						getShell(), getProject().getParent(), IResource.FOLDER
								| IResource.PROJECT | IResource.FILE);
				dialog.setInitialSelection(getProject());
				dialog.setAllowMultiple(false);
				if (dialog.open() == Dialog.OK) {
					Object[] results = dialog.getResult();
					if (results != null && 0 < results.length) {
						IResource r = (IResource) results[0];
						t.setText(r.getFullPath().toString());
					}
				}
			}
		});

		return t;
	}

	private IProject getProject() {
		if (project == null) {
			project = AdaptableUtil.to(getElement(), IProject.class);
		}
		return project;
	}

	protected IJavaProject getJavaProject() {
		if (javaP == null) {
			javaP = JavaCore.create(getProject());
		}
		return javaP;
	}

}
