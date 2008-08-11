/*
 * Copyright 2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package werkzeugkasten.resource.synchronizer.nls;

import org.eclipse.osgi.util.NLS;

/**
 * 
 * @author taichi
 * 
 */
public class Strings extends NLS {

	public static String MSG_START_SERVER;

	public static String MSG_STOP_SERVER;

	public static String MSG_REFRESH_RESOURCE;

	public static String LABEL_START;

	public static String LABEL_STOP;

	public static String MSG_NEW_DEBUG_JSP;

	public static String TITLE_NEW_DEBUG_JSP;

	public static String MSG_CREATE_CONTENTS;

	public static String LABEL_CONTEXT_ROOT_DIR;

	public static String LABEL_OUT_PUT_DIR;

	public static String LABEL_BROWSE;

	public static String MSG_SELECT_CONTEXT_ROOT_DIR;

	public static String MSG_SELECT_OUTPUT_DIR;

	public static String ERR_SELECT_OUTPUT_DIR;

	public static String ERR_OUTPUT_DIR_HAS_ROOT;

	public static String ERR_OUTPUT_DIR_ALREADY_EXISTS;

	static {
		Class<Strings> clazz = Strings.class;
		NLS.initializeMessages(clazz.getName(), clazz);
	}
}
