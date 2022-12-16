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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.dom.enums.BorderStyle;
import org.pepstock.charba.client.utils.toast.enums.Align;
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
	// actions instance by a wrapper
	private final WrapperAction action;
	// list of actions
	private final List<ToastItemAction> actions = new LinkedList<>();

	/**
	 * Creates the object which wraps the passed argument.
	 * 
	 * @param delegated source object to be wrapper
	 */
	ToastItemOptions(ToastOptions delegated) {
		this.delegated = delegated;
		this.title = delegated != null ? new WrapperContentElement(delegated.getTitle()) : new WrapperContentElement(Toaster.get().getDefaults().getTitle());
		this.label = delegated != null ? new WrapperContentElement(delegated.getLabel()) : new WrapperContentElement(Toaster.get().getDefaults().getLabel());
		this.action = delegated != null ? new WrapperAction(delegated.getAction()) : new WrapperAction(Toaster.get().getDefaults().getAction());
		// checks if delegated is consistent
		if (this.delegated != null) {
			// scans actions
			for (ActionItem actionItem : this.delegated.getActions()) {
				// adds new toast action item
				actions.add(new ToastItemAction(actionItem));
			}
		}
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
	 * Returns the actions of the toast.
	 * 
	 * @return the actions of the toast
	 */
	@Override
	public IsDefaultAction getAction() {
		return action;
	}

	/**
	 * Returns the actions to the toast.
	 * 
	 * @return the actions to the toast.
	 */
	public List<ToastItemAction> getActions() {
		return Collections.unmodifiableList(actions);
	}

	/**
	 * Returns the type of the toast.
	 * 
	 * @return the type of the toast
	 */
	@Override
	public IsToastType getType() {
		return delegated != null ? delegated.getType() : Toaster.get().getDefaults().getType();
	}

	/**
	 * Returns the type of the toast progress bar.
	 * 
	 * @return the type of the toast progress bar
	 */
	@Override
	public IsProgressBarType getProgressBarType() {
		return delegated != null ? delegated.getProgressBarType() : Toaster.get().getDefaults().getProgressBarType();
	}

	/**
	 * Returns the height (in pixels) of the toast progress bar.
	 * 
	 * @return the height (in pixels) of the toast progress bar
	 */
	@Override
	public int getProgressBarHeight() {
		return delegated != null ? delegated.getProgressBarHeight() : Toaster.get().getDefaults().getProgressBarHeight();
	}

	/**
	 * Returns <code>true</code> whether to hide the progress bar.
	 * 
	 * @return <code>true</code> whether to hide the progress bar
	 */
	@Override
	public boolean isHideProgressBar() {
		return delegated != null ? delegated.isHideProgressBar() : Toaster.get().getDefaults().isHideProgressBar();
	}

	/**
	 * Returns <code>true</code> whether to hide the shadow of toast.
	 * 
	 * @return <code>true</code> whether to hide the shadow of toast
	 */
	@Override
	public boolean isHideShadow() {
		return delegated != null ? delegated.isHideShadow() : Toaster.get().getDefaults().isHideShadow();
	}

	/**
	 * Returns whether to make the toast notification sticky, which means that the toast notification will never auto dismiss until clicked.
	 * 
	 * @return whether to make the toast notification sticky, which means that the toast notification will never auto dismiss until clicked
	 */
	@Override
	public boolean isAutoHide() {
		return delegated != null ? delegated.isAutoHide() : Toaster.get().getDefaults().isAutoHide();
	}

	/**
	 * Returns how long the toast notification should last.
	 * 
	 * @return how long the toast notification should last
	 */
	@Override
	public int getTimeout() {
		return delegated != null ? delegated.getTimeout() : Toaster.get().getDefaults().getTimeout();
	}

	/**
	 * Returns the icon image set for toast.
	 * 
	 * @return the icon image set for toast
	 */
	@Override
	public Img getIcon() {
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
	 * Returns the alignment of the toast actions.
	 * 
	 * @return the alignment of the toast actions
	 */
	@Override
	public Align getAlign() {
		return delegated != null ? delegated.getAlign() : Toaster.get().getDefaults().getAlign();
	}

	/**
	 * Is a wrapper of {@link Title} or {@link Label} in order to exposes only "get" methods.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class WrapperContentElement implements IsDefaultContentElement {

		private final IsDefaultContentElement elementDelegated;

		private final ImmutableFont font;

		/**
		 * Creates the object wrapping the passed content element
		 * 
		 * @param elementDelegated content element to wrap
		 */
		private WrapperContentElement(IsDefaultContentElement elementDelegated) {
			this.elementDelegated = elementDelegated;
			this.font = new ImmutableFont(elementDelegated.getFont());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultFontContainer#getFont()
		 */
		@Override
		public IsDefaultFont getFont() {
			return font;
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
	 * Is a wrapper of {@link ActionItem} in order to exposes only "get" methods.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class WrapperAction implements IsDefaultAction {

		private final IsDefaultAction elementDelegated;

		private final ImmutableFont font;

		/**
		 * Creates the object wrapping the passed content element
		 * 
		 * @param elementDelegated content element to wrap
		 */
		private WrapperAction(IsDefaultAction elementDelegated) {
			this.elementDelegated = elementDelegated;
			this.font = new ImmutableFont(elementDelegated.getFont());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultFontContainer#getFont()
		 */
		@Override
		public IsDefaultFont getFont() {
			return font;
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

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.utils.toast.IsDefaultAction#getBackgroundColorAsString()
		 */
		@Override
		public String getBackgroundColorAsString() {
			return elementDelegated.getBackgroundColorAsString();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.utils.toast.IsDefaultAction#getBorderWidth()
		 */
		@Override
		public int getBorderWidth() {
			return elementDelegated.getBorderWidth();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.utils.toast.IsDefaultAction#getBorderColorAsString()
		 */
		@Override
		public String getBorderColorAsString() {
			return elementDelegated.getBorderColorAsString();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.utils.toast.IsDefaultAction#getBorderRadius()
		 */
		@Override
		public int getBorderRadius() {
			return elementDelegated.getBorderRadius();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.utils.toast.IsDefaultAction#getBorderStyle()
		 */
		@Override
		public BorderStyle getBorderStyle() {
			return elementDelegated.getBorderStyle();
		}

	}

}