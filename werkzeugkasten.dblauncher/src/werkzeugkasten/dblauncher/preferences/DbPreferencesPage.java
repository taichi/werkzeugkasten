package werkzeugkasten.dblauncher.preferences;

import static werkzeugkasten.dblauncher.Constants.*;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.dialogs.PropertyPage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.h2.server.TcpServer;

import werkzeugkasten.common.resource.ProjectUtil;
import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.common.ui.WorkbenchUtil;
import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.common.widget.ResourceTreeSelectionDialog;
import werkzeugkasten.dblauncher.Activator;
import werkzeugkasten.dblauncher.nls.Strings;
import werkzeugkasten.dblauncher.preferences.impl.DbPreferencesImpl;
import werkzeugkasten.launcher.ConfigurationFacet;
import werkzeugkasten.launcher.LibraryConfigurator;

/**
 * @author taichi
 * 
 */
public class DbPreferencesPage extends PropertyPage implements
		IWorkbenchPropertyPage {

	private Pattern numeric = Pattern.compile("\\d*");

	private Button useDbLauncher;

	private Button isDebug;

	private Button useInternalWebBrowser;

	private Button addDriverToBuildPath;

	private Combo dbType;

	private Label dbDesc;

	private Text baseDir;

	private Text dbPortNo;

	private Text webPortNo;

	private Text user;

	private Text password;

	/**
	 * 
	 */
	public DbPreferencesPage() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse
	 * .swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		WorkbenchUtil.setHelp(parent, CTX_HELP_PREF);
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		GridData data = new GridData(GridData.FILL);
		data.grabExcessHorizontalSpace = true;
		composite.setLayoutData(data);

		this.useDbLauncher = createCheckBox(composite,
				Strings.LABEL_USE_DBLAUNCHER);

		this.isDebug = createCheckBox(composite, Strings.LABEL_IS_DEBUG);

		this.useInternalWebBrowser = createCheckBox(composite,
				Strings.LABEL_USE_INTERNAL_WEBBROWSER);

		this.addDriverToBuildPath = createCheckBox(composite,
				Strings.LABEL_ADD_DRIVER_TO_BUILDPATH);

		this.dbType = createPartOfDbType(composite, Strings.LABEL_DB_TYPE);

		NumberVerifier nv = new NumberVerifier();
		this.dbPortNo = createPart(composite, Strings.LABEL_DB_PORTNO);
		this.dbPortNo.addModifyListener(nv);
		this.webPortNo = createPart(composite, Strings.LABEL_WEB_PORTNO);
		this.webPortNo.addModifyListener(nv);
		this.user = createPart(composite, Strings.LABEL_USER);
		this.password = createPart(composite, Strings.LABEL_PASSWORD,
				SWT.BORDER | SWT.PASSWORD);
		this.baseDir = createPartOfBaseDir(composite, Strings.LABEL_BASE_DIR);

		setUpStoredValue();
		setUpPortNos();
		return composite;
	}

	private Composite createDefaultComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);

		GridData data = new GridData();
		data.verticalAlignment = GridData.FILL;
		data.horizontalAlignment = GridData.FILL;
		data.horizontalSpan = 2;
		composite.setLayoutData(data);

		return composite;
	}

	private Button createCheckBox(Composite composite, String label) {
		Button b = new Button(createDefaultComposite(composite), SWT.CHECK);
		b.setText(label);
		return b;
	}

	private class NumberVerifier implements ModifyListener {
		public void modifyText(ModifyEvent e) {
			if (e.widget instanceof Text) {
				Text t = (Text) e.widget;
				boolean is = false;
				if (is = numeric.matcher(t.getText()).matches()) {
					setErrorMessage(null);
				} else {
					setErrorMessage(NLS.bind(Strings.ERR_ONLY_NUMERIC,
							Strings.LABEL_DB_PORTNO));
				}
				setValid(is);
			}
		}
	}

	/**
	 * 
	 */
	private void setUpStoredValue() {
		IPreferenceStore store = getPreferenceStore();
		this.useDbLauncher.setSelection(ProjectUtil.hasNature(getProject(),
				ID_NATURE));
		int index = this.dbType.indexOf(store.getString(PREF_DB_TYPE));
		this.dbType.select(-1 < index ? index : 0);
		this.isDebug.setSelection(store.getBoolean(PREF_IS_DEBUG));
		this.useInternalWebBrowser.setSelection(store
				.getBoolean(PREF_USE_INTERNAL_WEBBROWSER));
		ConfigurationFacet facet = getCurrentFacet();
		if (facet instanceof LibraryConfigurator) {
			setDriverExists((LibraryConfigurator) facet);
			this.dbDesc.setText(facet.getDescription());
		}

		this.baseDir.setText(store.getString(PREF_BASE_DIR));
		this.dbPortNo.setText(store.getString(PREF_DB_PORTNO));
		this.webPortNo.setText(store.getString(PREF_WEB_PORTNO));
		this.user.setText(store.getString(PREF_USER));
		this.password.setText(store.getString(PREF_PASSWORD));
	}

	protected void setDriverExists(LibraryConfigurator facet) {
		this.addDriverToBuildPath.setSelection(hasDriver(facet));
	}

	protected ConfigurationFacet getCurrentFacet() {
		ConfigurationFacet facet = Activator.getFacetRegistry().find(
				this.dbType.getText());
		return facet;
	}

	protected boolean hasDriver(LibraryConfigurator facet) {
		boolean result = false;
		try {
			IJavaProject p = getJavaProject();
			if (facet != null) {
				result = facet.hasLibrary(p);
			}
		} catch (CoreException e) {
			Activator.log(e);
		}
		return result;
	}

	/**
	 * @param composite
	 * @param label
	 */
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
		Set<String> keys = Activator.getFacetRegistry().keys();
		c.setItems(keys.toArray(new String[keys.size()]));
		this.dbDesc = new Label(composite, SWT.NONE);
		c.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			public void widgetSelected(SelectionEvent e) {
				ConfigurationFacet facet = getCurrentFacet();
				if (facet instanceof LibraryConfigurator) {
					setDriverExists((LibraryConfigurator) facet);
					dbDesc.setText(facet.getDescription());
					dbDesc.getParent().layout();
				}
			}
		});
		data = new GridData();
		data.horizontalSpan = 0;
		data.horizontalIndent = 0;
		data.verticalIndent = 0;
		dbDesc.setLayoutData(data);
		return c;
	}

	protected Text createPartOfBaseDir(Composite composite, String label) {
		Label l = new Label(composite, SWT.NONE);
		l.setText(label);

		composite = new Composite(composite, SWT.NONE);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		data.horizontalSpan = 0;
		composite.setLayoutData(data);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		composite.setLayout(layout);

		Text txt = new Text(composite, SWT.SINGLE | SWT.BORDER);
		txt.setLayoutData(new GridData(GridData.FILL_BOTH));
		Button outpath = new Button(composite, SWT.PUSH);
		outpath.setText(Strings.LABEL_BROWSE);
		outpath.addSelectionListener(new SelectionAdapter() {
			@Override
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
						baseDir.setText(r.getFullPath().toString());
					}
				}
			}
		});
		data = new GridData();
		data.horizontalAlignment = GridData.END;
		data.horizontalSpan = 0;
		data.horizontalIndent = 0;
		data.verticalIndent = 0;
		outpath.setLayoutData(data);
		return txt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#doGetPreferenceStore()
	 */
	@Override
	protected IPreferenceStore doGetPreferenceStore() {
		IProject project = getProject();
		ScopedPreferenceStore store = null;
		if (project != null) {
			store = new ScopedPreferenceStore(new ProjectScope(project),
					ID_PLUGIN);
			DbPreferencesImpl.setupPreferences(project, store);
			setPreferenceStore(store);
		}
		return store;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		IPreferenceStore store = getPreferenceStore();
		this.useDbLauncher.setSelection(false);
		this.dbType.select(0);
		this.isDebug.setSelection(store.getDefaultBoolean(PREF_IS_DEBUG));
		this.useInternalWebBrowser.setSelection(store
				.getDefaultBoolean(PREF_USE_INTERNAL_WEBBROWSER));
		this.addDriverToBuildPath.setSelection(false);
		this.baseDir.setText(DbPreferencesImpl.getDefaultBaseDir(getProject()));
		this.dbPortNo.setText(store.getDefaultString(PREF_DB_PORTNO));
		this.webPortNo.setText(store.getDefaultString(PREF_WEB_PORTNO));
		super.performDefaults();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	@Override
	public boolean performOk() {
		boolean result = false;
		IProject project = getProject();
		try {
			if (project != null) {
				IPreferenceStore store = getPreferenceStore();
				if (this.useDbLauncher.getSelection()) {
					ProjectUtil.addNature(project, ID_NATURE);
				} else {
					ProjectUtil.removeNature(project, ID_NATURE);
				}

				store.setValue(PREF_DB_TYPE, this.dbType.getText());

				store.setValue(PREF_IS_DEBUG, this.isDebug.getSelection());
				store.setValue(PREF_USE_INTERNAL_WEBBROWSER,
						this.useInternalWebBrowser.getSelection());
				String dir = this.baseDir.getText();
				if (StringUtil.isEmpty(dir) == false) {
					store.setValue(PREF_BASE_DIR, dir);
				}
				String no = this.dbPortNo.getText();
				if (StringUtil.isEmpty(no) == false
						&& numeric.matcher(no).matches()) {
					store.setValue(PREF_DB_PORTNO, no);
				}
				no = this.webPortNo.getText();
				if (StringUtil.isEmpty(no) == false
						&& numeric.matcher(no).matches()) {
					store.setValue(PREF_WEB_PORTNO, no);
				}
				IJavaProject jp = getJavaProject();
				ConfigurationFacet facet = Activator.getFacetRegistry().find(
						this.dbType.getText());
				if (facet instanceof LibraryConfigurator) {
					LibraryConfigurator lc = (LibraryConfigurator) facet;
					if (this.addDriverToBuildPath.getSelection()) {
						lc.addLibrary(jp);
					} else {
						lc.removeLibrary(jp);
					}
				}

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

	protected IProject project;
	protected IJavaProject javaP;

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

	private void setUpPortNos() {
		IProject project = getProject();
		if (ProjectUtil.hasNature(project, ID_NATURE) == false) {
			IWorkspaceRoot root = ProjectUtil.getWorkspaceRoot();
			IProject[] all = root.getProjects();
			Set<String> nos = new HashSet<String>();
			for (int i = 0; i < all.length; i++) {
				if (ProjectUtil.hasNature(all[i], ID_NATURE)) {
					IPreferenceStore other = new ScopedPreferenceStore(
							new ProjectScope(all[i]), ID_PLUGIN);
					nos.add(other.getString(PREF_DB_PORTNO));
					nos.add(other.getString(PREF_WEB_PORTNO));
				}
			}
			int dbPort = TcpServer.DEFAULT_PORT - 1;
			while (nos.contains(String.valueOf(++dbPort))) {
			}
			nos.add(String.valueOf(dbPort));
			int webPort = org.h2.engine.Constants.DEFAULT_HTTP_PORT - 1;
			while (nos.contains(String.valueOf(++webPort))) {
			}
			this.dbPortNo.setText(String.valueOf(dbPort));
			this.webPortNo.setText(String.valueOf(webPort));
		}
	}

}
