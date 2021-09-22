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

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;
import org.pepstock.charba.client.items.FontItem;
import org.pepstock.charba.client.options.FontContainer;
import org.pepstock.charba.client.options.HasFont;
import org.pepstock.charba.client.options.IsFont;
import org.pepstock.charba.client.utils.toast.handlers.ClickEventHandler;
import org.pepstock.charba.client.utils.toast.handlers.CloseHandler;
import org.pepstock.charba.client.utils.toast.handlers.OpenHandler;

/**
 * Wrapper of {@link ToastOptions}, used by {@link ToastItem}, which exposes all "get" methods in order to be immutable.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ToastItemOptions implements IsDefaultToastOptions {

	// toast options instance to wrap
	private final ToastOptions delegated;
	// title instance by a wrapper
	private final WrapperContentElement title;
	// label instance by a wrapper
	private final WrapperContentElement label;

	/**
	 * Creates the object which wraps the passed argument.
	 * 
	 * @param delegated source object to be wrapper
	 */
	ToastItemOptions(ToastOptions delegated) {
		this.delegated = delegated;
		this.title = delegated != null ? new WrapperContentElement(delegated.getTitle(), delegated.getDefaultValues().getTitle())
				: new WrapperContentElement(Toaster.get().getDefaults().getTitle(), Toaster.get().getDefaults().getDefaultValues().getTitle());
		this.label = delegated != null ? new WrapperContentElement(delegated.getLabel(), delegated.getDefaultValues().getLabel())
				: new WrapperContentElement(Toaster.get().getDefaults().getLabel(), Toaster.get().getDefaults().getDefaultValues().getLabel());
	}

	/**
	 * Returns the original wrapped options
	 * 
	 * @return the original wrapped options
	 */
	ToastOptions getDelegated() {
		return delegated;
	}

	/**
	 * Returns the CLICK event hander, if set, otherwise <code>null</code>.
	 * 
	 * @return the CLICK event hander, if set, otherwise <code>null</code>.
	 */
	public ClickEventHandler getClickEventHandler() {
		return delegated != null ? delegated.getClickEventHandler() : null;
	}

	/**
	 * Returns the OPEN hander, if set, otherwise <code>null</code>.
	 * 
	 * @return the OPEN hander, if set, otherwise <code>null</code>.
	 */
	public OpenHandler getOpenHandler() {
		return delegated != null ? delegated.getOpenHandler() : null;
	}

	/**
	 * Returns the CLOSE hander, if set, otherwise <code>null</code>.
	 * 
	 * @return the CLOSE hander, if set, otherwise <code>null</code>.
	 */
	public CloseHandler getCloseHandler() {
		return delegated != null ? delegated.getCloseHandler() : null;
	}

	/**
	 * Returns the title of the toast.
	 * 
	 * @return the title of the toast
	 */
	@Override
	public IsDefaultContentElement getTitle() {
		return title;
	}

	/**
	 * Returns the label of the toast.
	 * 
	 * @return the label of the toast
	 */
	@Override
	public IsDefaultContentElement getLabel() {
		return label;
	}

	/**
	 * Returns the type of the toast.
	 * 
	 * @return the type of the toast
	 */
	@Override
	public final IsToastType getType() {
		return delegated != null ? delegated.getType() : Toaster.get().getDefaults().getType();
	}

	/**
	 * Returns the type of the toast progress bar.
	 * 
	 * @return the type of the toast progress bar
	 */
	@Override
	public final IsProgressBarType getProgressBarType() {
		return delegated != null ? delegated.getProgressBarType() : Toaster.get().getDefaults().getProgressBarType();
	}

	/**
	 * Returns <code>true</code> whether to hide the progress bar.
	 * 
	 * @return <code>true</code> whether to hide the progress bar
	 */
	@Override
	public final boolean isHideProgressBar() {
		return delegated != null ? delegated.isHideProgressBar() : Toaster.get().getDefaults().isHideProgressBar();
	}

	/**
	 * Returns <code>true</code> whether to hide the shadow of toast.
	 * 
	 * @return <code>true</code> whether to hide the shadow of toast
	 */
	@Override
	public final boolean isHideShadow() {
		return delegated != null ? delegated.isHideShadow() : Toaster.get().getDefaults().isHideShadow();
	}

	/**
	 * Returns whether to make the toast notification sticky, which means that the toast notification will never auto dismiss until clicked.
	 * 
	 * @return whether to make the toast notification sticky, which means that the toast notification will never auto dismiss until clicked
	 */
	@Override
	public final boolean isAutoHide() {
		return delegated != null ? delegated.isAutoHide() : Toaster.get().getDefaults().isAutoHide();
	}

	/**
	 * Returns how long the toast notification should last.
	 * 
	 * @return how long the toast notification should last
	 */
	@Override
	public final int getTimeout() {
		return delegated != null ? delegated.getTimeout() : Toaster.get().getDefaults().getTimeout();
	}

	/**
	 * Returns the icon image set for toast.
	 * 
	 * @return the icon image set for toast
	 */
	@Override
	public final Img getIcon() {
		return delegated != null ? delegated.getIcon() : Toaster.get().getDefaults().getIcon();
	}

	/**
	 * Returns the border radius (in pixels).
	 * 
	 * @return the border radius (in pixels).
	 */
	@Override
	public int getBorderRadius() {
		return delegated != null ? delegated.getBorderRadius() : Toaster.get().getDefaults().getBorderRadius();
	}

	/**
	 * Is a wrapper of {@link Title} or {@link Label} in order to exposes only "get" methods.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class WrapperContentElement implements IsDefaultContentElement {

		private final IsDefaultContentElement elementDelegated;

		private final WrapperFont font;

		/**
		 * Creates the object wrapping the passed content element
		 * 
		 * @param elementDelegated content element to wrap
		 * @param defaultValues the default values of element
		 */
		private WrapperContentElement(IsDefaultContentElement elementDelegated, IsDefaultContentElement defaultValues) {
			this.elementDelegated = elementDelegated;
			this.font = new WrapperFont(elementDelegated.getFont(), defaultValues.getFont());
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

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.options.HasFont#getColor()
		 */
		@Override
		public IsColor getColor() {
			return elementDelegated.getColor();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.utils.toast.IsDefaultContentElement#getColorAsString()
		 */
		@Override
		public String getColorAsString() {
			return elementDelegated.getColorAsString();
		}

	}

	/**
	 * Is a wrapper of {@link IsFont} in order to exposes only "get" methods.<br>
	 * The "set" methods are still here, because the {@link HasFont} interface needs a {@link IsFont} instance and not {@link IsDefaultFont} but they don't do anything.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class WrapperFont implements IsFont {

		// font instance to be wrap
		private final IsFont fontDelegated;
		// default font
		private final IsDefaultFont defaultValues;

		/**
		 * Creates the object wrapping the passed font
		 * 
		 * @param fontDelegated font to wrap
		 * @param defaultValues default values to apply in case of creation of {@link FontItem}.
		 */
		private WrapperFont(IsFont fontDelegated, IsDefaultFont defaultValues) {
			this.fontDelegated = fontDelegated;
			this.defaultValues = defaultValues;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getSize()
		 */
		@Override
		public int getSize() {
			return fontDelegated.getSize();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getStyle()
		 */
		@Override
		public FontStyle getStyle() {
			return fontDelegated.getStyle();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getFamily()
		 */
		@Override
		public String getFamily() {
			return fontDelegated.getFamily();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getWeight()
		 */
		@Override
		public Weight getWeight() {
			return fontDelegated.getWeight();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getLineHeight()
		 */
		@Override
		public double getLineHeight() {
			return fontDelegated.getLineHeight();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getLineHeightAsString()
		 */
		@Override
		public String getLineHeightAsString() {
			return fontDelegated.getLineHeightAsString();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultFont#create()
		 */
		@Override
		public FontItem create() {
			return IsFont.super.create(defaultValues);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.options.IsFont#setSize(int)
		 */
		@Override
		public void setSize(int size) {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.options.IsFont#setStyle(org.pepstock.charba.client.enums.FontStyle)
		 */
		@Override
		public void setStyle(FontStyle style) {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.options.IsFont#setFamily(java.lang.String)
		 */
		@Override
		public void setFamily(String family) {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.options.IsFont#setWeight(org.pepstock.charba.client.enums.Weight)
		 */
		@Override
		public void setWeight(Weight weight) {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.options.IsFont#setLineHeight(double)
		 */
		@Override
		public void setLineHeight(double lineHeight) {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.options.IsFont#setLineHeight(java.lang.String)
		 */
		@Override
		public void setLineHeight(String lineHeight) {
			// do nothing
		}

	}

}