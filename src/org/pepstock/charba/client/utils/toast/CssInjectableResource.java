/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.utils.toast;

import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.resources.AbstractInjectableResource;

/**
 * Enables to inject a custom CSS for new toast type in the DOM.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class CssInjectableResource extends AbstractInjectableResource {

	// prefix of name of CSS injectable
	private static final String NAME_TYPE_PREFIX = "_toastTypeCss";
	// prefix of name of CSS injectable
	private static final String NAME_PROGRESS_PREFIX = "_toastProgressCss";

	/**
	 * Creates new injectable resource for a specific toast type
	 * 
	 * @param type toast type to consumed to inject new CSS
	 * @param progressBar if <code>true</code>, the resource is related to a custom progress bar type
	 * @param content CSS content to inject
	 */
	CssInjectableResource(AbstractStandardType type, boolean progressBar, String... content) {
		super(String.join(Constants.MINUS, progressBar ? NAME_PROGRESS_PREFIX : NAME_TYPE_PREFIX, type.value()), content);
	}

}