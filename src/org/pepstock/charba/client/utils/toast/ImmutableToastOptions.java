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
import org.pepstock.charba.client.enums.Weight;
import org.pepstock.charba.client.items.FontItem;
import org.pepstock.charba.client.options.FontContainer;
import org.pepstock.charba.client.options.IsFont;
import org.pepstock.charba.client.utils.toast.enums.ProgressBarType;
import org.pepstock.charba.client.utils.toast.enums.ToastType;

/**
 * Wraps the immutable default options of the toast.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class ImmutableToastOptions extends AbstractReadOnlyToastOptions {

	// Default of toast type
	static final ToastType DEFAULT_TYPE = ToastType.DEFAULT;
	// Default of toast progress bar type
	static final ProgressBarType DEFAULT_PROGRESS_BAR_TYPE = ProgressBarType.DEFAULT;
	// Default of auto hide of the toast
	static final boolean DEFAULT_AUTO_HIDE = true;
	// Default to hide the progress bar toast
	static final boolean DEFAULT_HIDE_PROGRESS_BAR = false;
	// Default of timeout of the toast
	static final int DEFAULT_TIMEOUT = 4000;
	// Default of border radius of the toast
	static final int DEFAULT_BORDER_RADIUS = 8;
	// default font family
	static final String DEFAULT_FONT_FAMILY = "system-ui, -apple-system, \"Segoe UI\", Roboto, Helvetica, Arial, sans-serif, \"Apple Color Emoji\", \"Segoe UI Emoji\"";
	// default font color
	static final String DEFAULT_COLOR = "#616161";

	// static instance of default options
	private static final IsDefaultToastOptions DEFAULT_VALUES = new DefaultValues();
	//

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

		private final DefaultTitleValues title = new DefaultTitleValues();

		private final DefaultLabelValues label = new DefaultLabelValues();

		/**
		 * To avoid any instantiation
		 */
		private DefaultValues() {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.utils.toast.IsDefaultToastOptions#getTitle()
		 */
		@Override
		public IsDefaultContentElement getTitle() {
			return title;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.utils.toast.IsDefaultToastOptions#getLabel()
		 */
		@Override
		public IsDefaultContentElement getLabel() {
			return label;
		}

	}

	/**
	 * Class which implements the defaults for title
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class DefaultTitleValues implements IsDefaultContentElement {

		private final FontItem font = new FontItem();

		/**
		 * To avoid any instantiation
		 */
		private DefaultTitleValues() {
			font.setFamily(DEFAULT_FONT_FAMILY);
			font.setWeight(Weight.BOLD);
			font.setSize(15);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.options.HasFont#getFontContainer()
		 */
		@Override
		public FontContainer getFontContainer() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.options.HasFont#getFont()
		 */
		@Override
		public IsFont getFont() {
			return font;
		}

	}

	/**
	 * Class which implements the defaults for label
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class DefaultLabelValues implements IsDefaultContentElement {

		private final FontItem font = new FontItem();

		/**
		 * To avoid any instantiation
		 */
		private DefaultLabelValues() {
			font.setFamily(DEFAULT_FONT_FAMILY);
			font.setWeight(Weight.NORMAL);
			font.setSize(14);
			font.setLineHeight("20px");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.options.HasFont#getFontContainer()
		 */
		@Override
		public FontContainer getFontContainer() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.options.HasFont#getFont()
		 */
		@Override
		public IsFont getFont() {
			return font;
		}
	}
}