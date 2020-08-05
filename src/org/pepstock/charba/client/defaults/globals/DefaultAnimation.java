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
package org.pepstock.charba.client.defaults.globals;

import java.util.Locale;

import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.defaults.IsDefaultAnimationCollection;
import org.pepstock.charba.client.defaults.IsDefaultAnimationMode;
import org.pepstock.charba.client.defaults.IsDefaultAnimationProperty;
import org.pepstock.charba.client.enums.DefaultAnimationCollectionKey;
import org.pepstock.charba.client.enums.DefaultAnimationModeKey;
import org.pepstock.charba.client.enums.DefaultAnimationPropertyKey;
import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.options.IsAnimationCollectionKey;
import org.pepstock.charba.client.options.IsAnimationModeKey;
import org.pepstock.charba.client.options.IsAnimationPropertyKey;

/**
 * CHART.JS default values for ANIMATION element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultAnimation extends DefaultAnimationMode implements IsDefaultAnimation {

	/**
	 * Default animation mode element.
	 */
	public static final IsDefaultAnimationMode DEFAULT_ANIMATION_MODE = new DefaultAnimationMode();

	private static final boolean DEFAULT_ANIMATE_ROTATE = true;

	private static final boolean DEFAULT_ANIMATE_SCALE = false;

	private static final IsDefaultAnimationCollection DEFAULT_COLLECTION_NUMBERS = new DefaultAnimationCollection(DefaultAnimationCollectionKey.NUMBERS);

	private static final IsDefaultAnimationCollection DEFAULT_COLLECTION_COLORS = new DefaultAnimationCollection(DefaultAnimationCollectionKey.COLORS);

	private static final IsDefaultAnimationMode ACTIVE_DEFAULT_ANIMATION_MODE = new ActiveDefaultAnimationMode();

	private static final IsDefaultAnimationMode RESIZE_DEFAULT_ANIMATION_MODE = new ResizeDefaultAnimationMode();

	private static final IsDefaultAnimationMode SHOW_DEFAULT_ANIMATION_MODE = new ShowAndHideDefaultAnimationMode(true);

	private static final IsDefaultAnimationMode HIDE_DEFAULT_ANIMATION_MODE = new ShowAndHideDefaultAnimationMode(false);

	/**
	 * To avoid any instantiation
	 */

	DefaultAnimation() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.animation.IsDefaultAnimation#isAnimateRotate()
	 */
	@Override
	public boolean isAnimateRotate() {
		return DEFAULT_ANIMATE_ROTATE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.animation.IsDefaultAnimation#isAnimateScale()
	 */
	@Override
	public boolean isAnimateScale() {
		return DEFAULT_ANIMATE_SCALE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.globals.DefaultAnimationMode#getCollection(org.pepstock.charba.client.options.IsAnimationCollectionKey)
	 */
	@Override
	public IsDefaultAnimationCollection getCollection(IsAnimationCollectionKey collection) {
		// checks if collection is valid and is default one
		if (IsAnimationCollectionKey.isValid(collection) && DefaultAnimationCollectionKey.is(collection)) {
			// checks if is color
			if (Key.equals(collection, DefaultAnimationCollectionKey.COLORS)) {
				return DEFAULT_COLLECTION_COLORS;
			} else {
				// if here, is numbers
				return DEFAULT_COLLECTION_NUMBERS;
			}
		}
		// if here, collection not valid or not a default
		return super.getCollection(collection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#getMode(org.pepstock.charba.client.options.IsAnimationMode)
	 */
	@Override
	public IsDefaultAnimationMode getMode(IsAnimationModeKey mode) {
		// checks if mode is valid and is default one
		if (IsAnimationModeKey.isValid(mode) && DefaultAnimationModeKey.is(mode)) {
			// checks which default mode is
			if (Key.equals(mode, DefaultAnimationModeKey.ACTIVE)) {
				// if here, is ACTIVE
				return ACTIVE_DEFAULT_ANIMATION_MODE;
			} else if (Key.equals(mode, DefaultAnimationModeKey.RESIZE)) {
				// if here, is RESIZE
				return RESIZE_DEFAULT_ANIMATION_MODE;
			} else if (Key.equals(mode, DefaultAnimationModeKey.SHOW)) {
				// if here, is SHOW
				return SHOW_DEFAULT_ANIMATION_MODE;
			} else if (Key.equals(mode, DefaultAnimationModeKey.HIDE)) {
				// if here, is HIDE
				return HIDE_DEFAULT_ANIMATION_MODE;
			}
		}
		// if here, mode not valid or not a default
		return DEFAULT_ANIMATION_MODE;
	}

	/**
	 * Default animation for animation mode {@link DefaultAnimationModeKey#ACTIVE}.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class ActiveDefaultAnimationMode extends DefaultAnimationMode {

		private static final int DEFAULT_DURATION = 400;

		/**
		 * Creates a default animation mode wrapping the {@link DefaultAnimationModeKey#ACTIVE}.
		 */
		ActiveDefaultAnimationMode() {
			super(DefaultAnimationModeKey.ACTIVE);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.globals.AbstractDefaultAnimation#getDuration()
		 */
		@Override
		public int getDuration() {
			return DEFAULT_DURATION;
		}

	}

	/**
	 * Default animation for animation mode {@link DefaultAnimationModeKey#RESIZE}.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class ResizeDefaultAnimationMode extends DefaultAnimationMode {

		private static final int DEFAULT_DURATION = 0;

		/**
		 * Creates a default animation mode wrapping the {@link DefaultAnimationModeKey#RESIZE}.
		 */
		ResizeDefaultAnimationMode() {
			super(DefaultAnimationModeKey.RESIZE);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.globals.AbstractDefaultAnimation#getDuration()
		 */
		@Override
		public int getDuration() {
			return DEFAULT_DURATION;
		}

	}

	/**
	 * Default animation for {@link DefaultAnimationModeKey#SHOW} and {@link DefaultAnimationModeKey#HIDE} animation mode.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class ShowAndHideDefaultAnimationMode extends DefaultAnimationMode {

		private static final IsDefaultAnimationCollection DEFAULT_COLORS_COLLECTION = new ShowAndHideDefaultAnimationCollection();

		private final IsDefaultAnimationProperty visible;

		/**
		 * Creates a default animation mode wrapping the {@link DefaultAnimationModeKey#SHOW} or {@link DefaultAnimationModeKey#HIDE}.
		 * 
		 * @param show if <code>true</code>, initializes teh object for {@link DefaultAnimationModeKey#SHOW} mode, otherwise for {@link DefaultAnimationModeKey#HIDE}
		 */
		ShowAndHideDefaultAnimationMode(boolean show) {
			super(show ? DefaultAnimationModeKey.SHOW : DefaultAnimationModeKey.HIDE);
			// stores the visible property
			this.visible = new ShowAndHideDefaultAnimationProperty(show);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.globals.DefaultAnimationMode#getProperty(org.pepstock.charba.client.options.IsAnimationPropertyKey)
		 */
		@Override
		public IsDefaultAnimationProperty getProperty(IsAnimationPropertyKey property) {
			// checks if property is valid and is default visible one
			if (IsAnimationPropertyKey.isValid(property) && Key.equals(property, DefaultAnimationCollectionKey.COLORS)) {
				return visible;
			}
			// if here, property not valid or not a default
			return super.getProperty(property);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.globals.DefaultAnimationMode#getCollection(org.pepstock.charba.client.options.IsAnimationCollectionKey)
		 */
		@Override
		public IsDefaultAnimationCollection getCollection(IsAnimationCollectionKey collection) {
			// checks if collection is valid and is default color collection
			if (IsAnimationCollectionKey.isValid(collection) && Key.equals(collection, DefaultAnimationCollectionKey.COLORS)) {
				return DEFAULT_COLORS_COLLECTION;
			}
			// if here, collection not valid or not a default
			return super.getCollection(collection);
		}

	}

	/**
	 * {@link DefaultAnimationCollectionKey#COLORS} default animation collection for {@link DefaultAnimationModeKey#SHOW} and {@link DefaultAnimationModeKey#HIDE} animation mode.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class ShowAndHideDefaultAnimationCollection extends DefaultAnimationCollection {

		private static final String DEFAULT_FROM = HtmlColor.TRANSPARENT.name().toLowerCase(Locale.getDefault());

		/**
		 * Creates a default animation collection wrapping the {@link DefaultAnimationCollectionKey#COLORS}.
		 */
		ShowAndHideDefaultAnimationCollection() {
			super(DefaultAnimationCollectionKey.COLORS);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.globals.DefaultAnimationProperty#getFromAsString()
		 */
		@Override
		public String getFromAsString() {
			return DEFAULT_FROM;
		}

	}

	/**
	 * {@link DefaultAnimationPropertyKey#VISIBLE} default animation property for {@link DefaultAnimationModeKey#SHOW} and {@link DefaultAnimationModeKey#HIDE} animation mode.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class ShowAndHideDefaultAnimationProperty extends DefaultAnimationProperty {

		private static final int DEFAULT_DURATION = 0;

		private final boolean show;

		/**
		 * Creates a default animation property wrapping the {@link DefaultAnimationPropertyKey#VISIBLE}.
		 * 
		 * @param show if <code>true</code>, initializes teh object for {@link DefaultAnimationModeKey#SHOW} mode, otherwise for {@link DefaultAnimationModeKey#HIDE}
		 */
		ShowAndHideDefaultAnimationProperty(boolean show) {
			super(DefaultAnimationPropertyKey.VISIBLE);
			// stores show
			this.show = show;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.globals.AbstractDefaultAnimation#getEasing()
		 */
		@Override
		public Easing getEasing() {
			return show ? super.getEasing() : Easing.EASE_IN_EXPO;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.globals.AbstractDefaultAnimation#getDuration()
		 */
		@Override
		public int getDuration() {
			return show ? DEFAULT_DURATION : super.getDuration();
		}
	}
}
