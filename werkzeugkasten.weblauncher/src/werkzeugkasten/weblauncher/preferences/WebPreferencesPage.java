package werkzeugkasten.weblauncher.preferences;

import static werkzeugkasten.weblauncher.Constants.*;
import static werkzeugkasten.weblauncher.nls.Strings.*;

import java.util.Set;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.CoreException;
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
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import werkzeugkasten.common.resource.ProjectUtil;
import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.common.ui.WorkbenchUtil;
import werkzeugkasten.common.wiget.ResourceTreeSelectionDialog;
import werkzeugkasten.launcher.ConfigurationFacet;
import werkzeugkasten.launcher.ConfigurationFacetRegistry;
import werkzeugkasten.launcher.LibraryConfigurator;
import werkzeugkasten.weblauncher.Activator;
import werkzeugkasten.weblauncher.preferences.impl.WebPreferencesImpl;

/**
 * @author taichi
 * 
 */
public class WebPreferencesPage extends PropertyPage {

	private Pattern numeric = Pattern.compile("\\d*");

	private Button useWebLauncher;

	private Button addLibraryToBuildPath;

	private Combo libType;

	private Combo webType;

	private Label webDesc;

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
		WorkbenchUtil.setHelp(parent, CTX_HELP_PREF);
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		GridData data = new GridData(GridData.FILL);
		data.grabExcessHorizontalSpace = true;
		composite.setLayoutData(data);

		this.useWebLauncher = createCheckBox(composite, LABEL_USE_WEB_LAUNCHER);

		this.libType = createPartOfLibType(composite);

		this.webType = createPartOfDbType(composite, LABEL_SERVER_TYPE);

		this.contextName = createPart(composite, LABEL_CONTEXT_NAME);
		this.baseDir = createBaseDir(composite);
		this.webPortNo = createPart(composite, LABEL_WEB_PORTNO);
		NumberVerifier nv = new NumberVerifier();
		this.webPortNo.addModifyListener(nv);
		this.config = createConfig(composite);

		this.isCheckServer = createCheckBox(composite, LABEL_CHECK_SERVER);
		this.isCheckServer.setToolTipText(TOOLTIP_CHECK_SERVER);
		this.isDebug = createCheckBox(composite, LABEL_IS_DEBUG);
		this.useInternalWebBrowser = createCheckBox(composite,
				LABEL_USE_INTERNAL_WEBBROWSER);

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

	private Button createCheckBox(Composite composite, String label) {
		Button b = new Button(createDefaultComposite(composite), SWT.CHECK);
		b.setText(label);
		return b;
	}

	protected void setLibraryExists(LibraryConfigurator facet) {
		this.addLibraryToBuildPath.setSelection(hasLibrary(facet));
	}

	protected LibraryConfigurator getCurrentLibrary() {
		ConfigurationFacet facet = Activator.getLibraryRegistry().find(
				this.libType.getText());
		if (facet instanceof LibraryConfigurator) {
			return (LibraryConfigurator) facet;
		}
		return null;
	}

	protected ConfigurationFacet getCurrentFacet() {
		ConfigurationFacet facet = Activator.getLaunchRegistry().find(
				this.webType.getText());
		return facet;
	}

	protected boolean hasLibrary(LibraryConfigurator lib) {
		boolean result = false;
		try {
			IJavaProject p = getJavaProject();
			if (lib != null) {
				result = lib.hasLibrary(p);
			}
		} catch (CoreException e) {
			Activator.log(e);
		}
		return result;
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

		int index = this.libType.indexOf(wp.getLibraryType());
		this.libType.select(-1 < index ? index : 0);
		LibraryConfigurator lib = getCurrentLibrary();
		if (lib != null) {
			setLibraryExists(lib);
		}

		index = this.webType.indexOf(wp.getWebServerType());
		this.webType.select(-1 < index ? index : 0);
		ConfigurationFacet facet = getCurrentFacet();
		if (facet != null) {
			this.webDesc.setText(facet.getDescription());
		}

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
		this.libType.select(0);
		this.webType.select(0);
		this.addLibraryToBuildPath.setSelection(false);
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

				store.setValue(PREF_WEB_SERVER_TYPE, this.webType.getText());
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

				processLibraryConfig(store);

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

	private void processLibraryConfig(IPreferenceStore store)
			throws CoreException {
		String oldone = store.getString(PREF_LIBRARY_TYPE);
		String newone = this.libType.getText();

		if (this.addLibraryToBuildPath.getSelection()) {
			if (oldone.equals(newone) == false) {
				processLibraryConfig(oldone, false);
				processLibraryConfig(newone, true);
				store.setValue(PREF_LIBRARY_TYPE, newone);
			}
		} else {
			processLibraryConfig(oldone, false);
		}
	}

	private void processLibraryConfig(String type, boolean add)
			throws CoreException {
		ConfigurationFacetRegistry registry = Activator.getLibraryRegistry();
		ConfigurationFacet facet = registry.find(type);
		if (facet instanceof LibraryConfigurator) {
			LibraryConfigurator lc = (LibraryConfigurator) facet;
			IJavaProject jp = getJavaProject();
			if (add) {
				lc.addLibrary(jp);
			} else {
				lc.removeLibrary(jp);
			}
		}
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

	private Combo createPartOfLibType(Composite parent) {

		Composite composite = new Composite(parent, SWT.NONE);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		data.horizontalSpan = 2;
		composite.setLayoutData(data);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		composite.setLayout(layout);

		Combo c = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
		c.setLayoutData(new GridData(GridData.BEGINNING));
		Set<String> keys = Activator.getLibraryRegistry().keys();
		c.setItems(keys.toArray(new String[keys.size()]));
		c.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			public void widgetSelected(SelectionEvent e) {
				LibraryConfigurator lib = getCurrentLibrary();
				if (lib != null) {
					setLibraryExists(lib);
				}
			}
		});

		data = new GridData();
		data.horizontalSpan = 0;
		data.horizontalIndent = 0;
		data.verticalIndent = 0;

		this.addLibraryToBuildPath = new Button(composite, SWT.CHECK);
		this.addLibraryToBuildPath.setText(LABEL_ADD_LIBRARY_TO_BUILDPATH);
		this.addLibraryToBuildPath.setLayoutData(data);

		return c;
	}

	private Combo createPartOfDbType(Composite parent, String label) {
		Label l = new Label(parent, SWT.NONE);
		l.setText(label);

		Composite composite = new Composite(parent, SWT.NONE);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		data.horizontalSpan = 0;
		composite.setLayoutData(data);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		composite.setLayout(layout);

		Combo c = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
		c.setLayoutData(new GridData(GridData.BEGINNING));
		Set<String> keys = Activator.getLaunchRegistry().keys();
		c.setItems(keys.toArray(new String[keys.size()]));
		this.webDesc = new Label(composite, SWT.NONE);
		c.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			public void widgetSelected(SelectionEvent e) {
				ConfigurationFacet facet = getCurrentFacet();
				if (facet != null) {
					webDesc.setText(facet.getDescription());
					webDesc.getParent().layout();
				}
			}
		});
		data = new GridData();
		data.horizontalSpan = 0;
		data.horizontalIndent = 0;
		data.verticalIndent = 0;
		webDesc.setLayoutData(data);
		return c;
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
