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

import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.dom.enums.BorderStyle;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.utils.toast.enums.DefaultProgressBarType;
import org.pepstock.charba.client.utils.toast.enums.DefaultToastType;

/**
 * Wraps the immutable default options of the toast.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class ImmutableToastOptions extends AbstractReadOnlyToastOptions {

	// Default of toast type
	static final DefaultToastType DEFAULT_TYPE = DefaultToastType.DEFAULT;
	// Default of toast progress bar type
	static final DefaultProgressBarType DEFAULT_PROGRESS_BAR_TYPE = DefaultProgressBarType.DEFAULT;
	// Default of toast progress bar height
	static final int DEFAULT_PROGRESS_BAR_HEIGHT = 3;
	// Default of auto hide of the toast
	static final boolean DEFAULT_AUTO_HIDE = true;
	// Default to hide the progress bar toast
	static final boolean DEFAULT_HIDE_PROGRESS_BAR = false;
	// Default to hide the shadow of toast
	static final boolean DEFAULT_HIDE_SHADOW = false;
	// Default of timeout of the toast
	static final int DEFAULT_TIMEOUT = 4000;
	// Default of border radius of the toast
	static final int DEFAULT_BORDER_RADIUS = 8;
	// default font family
	static final String DEFAULT_FONT_FAMILY = "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif";
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

		private final DefaultActionsValues actions = new DefaultActionsValues(label);

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

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.utils.toast.IsDefaultToastOptions#getActions()
		 */
		@Override
		public IsDefaultAction getAction() {
			return actions;
		}

	}

	/**
	 * Class which implements the defaults for title
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class DefaultTitleValues implements IsDefaultContentElement {

		private final ImmutableFont font;

		/**
		 * To avoid any instantiation
		 */
		private DefaultTitleValues() {
			this.font = new ImmutableFont(15, FontStyle.NORMAL, DEFAULT_FONT_FAMILY, Weight.BOLD, Undefined.STRING);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.options.HasFont#getFont()
		 */
		@Override
		public IsDefaultFont getFont() {
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

		private final ImmutableFont font;

		/**
		 * To avoid any instantiation
		 */
		private DefaultLabelValues() {
			this.font = new ImmutableFont(14, FontStyle.NORMAL, DEFAULT_FONT_FAMILY, Weight.NORMAL, "20px");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.options.HasFont#getFont()
		 */
		@Override
		public IsDefaultFont getFont() {
			return font;
		}
	}

	/**
	 * Class which implements the defaults for actions
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class DefaultActionsValues implements IsDefaultAction {

		private static final String TRANSPARENT = HtmlColor.TRANSPARENT.toRGBA();

		private static final BorderStyle DEFAULT_BORDER_STYLE = BorderStyle.NONE;

		private final DefaultLabelValues label;

		/**
		 * To avoid any instantiation
		 * 
		 * @param label uses the default of labels as based
		 */
		private DefaultActionsValues(DefaultLabelValues label) {
			this.label = label;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.options.HasFont#getFont()
		 */
		@Override
		public IsDefaultFont getFont() {
			return label.getFont();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.utils.toast.IsDefaultAction#getBackgroundColorAsString()
		 */
		@Override
		public String getBackgroundColorAsString() {
			return TRANSPARENT;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.utils.toast.IsDefaultAction#getBorderWidth()
		 */
		@Override
		public int getBorderWidth() {
			return 0;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.utils.toast.IsDefaultAction#getBorderColorAsString()
		 */
		@Override
		public String getBorderColorAsString() {
			return getBackgroundColorAsString();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.utils.toast.IsDefaultAction#getBorderRadius()
		 */
		@Override
		public int getBorderRadius() {
			return 0;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.utils.toast.IsDefaultAction#getBorderStyle()
		 */
		@Override
		public BorderStyle getBorderStyle() {
			return DEFAULT_BORDER_STYLE;
		}

	}

}