/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.utils.toast;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.utils.toast.enums.ProgressBarType;
import org.pepstock.charba.client.utils.toast.enums.ToastType;

/**
 * Wraps the immutable default options of the toast.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class ImmutableToastOptions extends AbstractReadOnlyToastOptions {

	/**
	 * Default of toast type, {@link ToastType#DEFAULT}.
	 */
	static final ToastType DEFAULT_TYPE = ToastType.DEFAULT;
	/**
	 * Default of toast progress bar type, {@link ProgressBarType#DEFAULT}.
	 */
	static final ProgressBarType DEFAULT_PROGRESS_BAR_TYPE = ProgressBarType.DEFAULT;
	/**
	 * Default of auto hide of the toast, <b>{@value DEFAULT_AUTO_HIDE}</b>.
	 */
	static final boolean DEFAULT_AUTO_HIDE = true;
	/**
	 * Default to hide the progress bar toast, <b>{@value DEFAULT_HIDE_PROGRESS_BAR}</b>.
	 */
	static final boolean DEFAULT_HIDE_PROGRESS_BAR = false;
	/**
	 * Default of timeout of the toast, <b>{@value DEFAULT_TIMEOUT}</b>.
	 */
	static final int DEFAULT_TIMEOUT = 4000;
	// static instance of default options
	private static final IsDefaultToastOptions DEFAULT_VALUES = new DefaultValues();

	/**
	 * Creates the configuration with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	ImmutableToastOptions(NativeObject nativeObject) {
		super(nativeObject, DEFAULT_VALUES);
	}

	/**
	 * Class which implements the defaults.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class DefaultValues implements IsDefaultToastOptions {

		/**
		 * To avoid any instantiation
		 */
		private DefaultValues() {
			// do nothing
		}

	}

}