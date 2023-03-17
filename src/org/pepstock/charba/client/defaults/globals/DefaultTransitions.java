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
package org.pepstock.charba.client.defaults.globals;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.defaults.IsDefaultAnimationCollection;
import org.pepstock.charba.client.defaults.IsDefaultAnimationTransition;
import org.pepstock.charba.client.defaults.IsDefaultAnimations;
import org.pepstock.charba.client.defaults.IsDefaultTransitions;
import org.pepstock.charba.client.enums.AnimationType;
import org.pepstock.charba.client.enums.DefaultAnimationCollectionKey;
import org.pepstock.charba.client.enums.DefaultAnimationPropertyKey;
import org.pepstock.charba.client.enums.DefaultTransitionMode;
import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.options.TransitionMode;

/**
 * CHART.JS default values for ANIMATION element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultTransitions implements IsDefaultTransitions {

	/**
	 * Default animation mode element.
	 */
	public static final IsDefaultAnimationTransition DEFAULT_ANIMATION_TRANSITION = new DefaultAnimationTransition();

	private static final IsDefaultAnimationTransition ACTIVE_DEFAULT_ANIMATION_TRANSITION = new ActiveDefaultAnimationTransition();

	private static final IsDefaultAnimationTransition RESIZE_DEFAULT_ANIMATION_TRANSITION = new ResizeDefaultAnimationTransition();

	private static final IsDefaultAnimationTransition SHOW_DEFAULT_ANIMATION_TRANSITION = new ShowAndHideDefaultAnimationTransition(DefaultTransitionMode.SHOW);

	private static final IsDefaultAnimationTransition HIDE_DEFAULT_ANIMATION_TRANSITION = new ShowAndHideDefaultAnimationTransition(DefaultTransitionMode.HIDE);

	private static final List<DefaultTransitionMode> DEFAULT_ANIMATION_MODE_KEYS = Arrays.asList(DefaultTransitionMode.ACTIVE, DefaultTransitionMode.RESIZE, DefaultTransitionMode.SHOW, DefaultTransitionMode.HIDE);

	/**
	 * To avoid any instantiation
	 */

	DefaultTransitions() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTransitions#has(org.pepstock.charba.client.options.TransitionMode)
	 */
	@Override
	public boolean has(TransitionMode transition) {
		// checks if mode is valid and is default one
		if (TransitionMode.isValid(transition)) {
			// scans all defaults
			for (DefaultTransitionMode defaultMode : DEFAULT_ANIMATION_MODE_KEYS) {
				// checks if equals
				if (defaultMode.equals(transition)) {
					// equals then exist
					return true;
				}
			}
		}
		// if here, it's not a default one
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTransitions#get(org.pepstock.charba.client.options.TransitionMode)
	 */
	@Override
	public IsDefaultAnimationTransition get(TransitionMode transition) {
		// checks if mode is valid and is default one
		if (TransitionMode.isValid(transition) && DefaultTransitionMode.is(transition)) {
			// checks which default mode is
			if (Key.equals(transition, DefaultTransitionMode.ACTIVE)) {
				// if here, is ACTIVE
				return ACTIVE_DEFAULT_ANIMATION_TRANSITION;
			} else if (Key.equals(transition, DefaultTransitionMode.RESIZE)) {
				// if here, is RESIZE
				return RESIZE_DEFAULT_ANIMATION_TRANSITION;
			} else if (Key.equals(transition, DefaultTransitionMode.SHOW)) {
				// if here, is SHOW
				return SHOW_DEFAULT_ANIMATION_TRANSITION;
			} else if (Key.equals(transition, DefaultTransitionMode.HIDE)) {
				// if here, is HIDE
				return HIDE_DEFAULT_ANIMATION_TRANSITION;
			}
		}
		// if here, mode not valid or not a default
		return DEFAULT_ANIMATION_TRANSITION;
	}

	/**
	 * Default animation for animation mode {@link DefaultTransitionMode#ACTIVE}.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class ActiveDefaultAnimationTransition extends DefaultAnimationTransition {

		private static final int DEFAULT_DURATION = 400;

		private static final ActiveDefaultAnimation DEFAULT_ANIMATION = new ActiveDefaultAnimation();

		/**
		 * Creates a default animation mode wrapping the {@link DefaultTransitionMode#ACTIVE}.
		 */
		private ActiveDefaultAnimationTransition() {
			super();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.globals.DefaultAnimationTransition#getAnimation()
		 */
		@Override
		public IsDefaultAnimation getAnimation() {
			return DEFAULT_ANIMATION;
		}

		/**
		 * Specific default animation for "active" transition.
		 * 
		 * @author Andrea "Stock" Stocchero
		 *
		 */
		private static class ActiveDefaultAnimation extends DefaultAnimation {

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

	}

	/**
	 * Default animation for animation mode {@link DefaultTransitionMode#RESIZE}.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class ResizeDefaultAnimationTransition extends DefaultAnimationTransition {

		private static final int DEFAULT_DURATION = 0;

		private static final ResizeDefaultAnimation DEFAULT_ANIMATION = new ResizeDefaultAnimation();

		/**
		 * Creates a default animation mode wrapping the {@link DefaultTransitionMode#RESIZE}.
		 */
		private ResizeDefaultAnimationTransition() {
			super();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.globals.DefaultAnimationTransition#getAnimation()
		 */
		@Override
		public IsDefaultAnimation getAnimation() {
			return DEFAULT_ANIMATION;
		}

		/**
		 * Specific default animation for "resize" transition.
		 * 
		 * @author Andrea "Stock" Stocchero
		 *
		 */
		private static class ResizeDefaultAnimation extends DefaultAnimation {

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

	}

	/**
	 * Default animation for {@link DefaultTransitionMode#SHOW} and {@link DefaultTransitionMode#HIDE} animation mode.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class ShowAndHideDefaultAnimationTransition extends DefaultAnimationTransition {

		private final ShowAndHideDefaultAnimations animations;

		/**
		 * Creates a default animation mode wrapping the {@link DefaultTransitionMode#SHOW} or {@link DefaultTransitionMode#HIDE}.
		 * 
		 * @param mode update mode (transition) to set, can be {@link DefaultTransitionMode#SHOW} or {@link DefaultTransitionMode#HIDE}
		 */
		private ShowAndHideDefaultAnimationTransition(TransitionMode mode) {
			this.animations = new ShowAndHideDefaultAnimations(mode);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.globals.DefaultAnimationTransition#getAnimations()
		 */
		@Override
		public IsDefaultAnimations getAnimations() {
			return animations;
		}

		/**
		 * Specific default animations for "show" and/or "hide" transition.
		 * 
		 * @author Andrea "Stock" Stocchero
		 *
		 */
		private static class ShowAndHideDefaultAnimations extends DefaultAnimations {

			private static final IsDefaultAnimationCollection DEFAULT_COLORS_COLLECTION = new ShowAndHideDefaultAnimationCollection();

			private final IsDefaultAnimationCollection visibleCollection;

			/**
			 * Creates a default animations wrapping the {@link DefaultTransitionMode#SHOW} or {@link DefaultTransitionMode#HIDE}.
			 * 
			 * @param mode update mode (transition) to set, can be {@link DefaultTransitionMode#SHOW} or {@link DefaultTransitionMode#HIDE}
			 */
			private ShowAndHideDefaultAnimations(TransitionMode mode) {
				this.visibleCollection = new ShowAndHideDefaultVisibleAnimationCollection(DefaultTransitionMode.SHOW.equals(mode));
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.defaults.IsDefaultAnimations#contains(org.pepstock.charba.client.commons.Key)
			 */
			@Override
			public boolean contains(Key collection) {
				return Key.isValid(collection) && (Key.equals(collection, DefaultAnimationCollectionKey.COLORS) || Key.equals(collection, DefaultAnimationCollectionKey.VISIBLE));
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.defaults.IsDefaultAnimations#get(org.pepstock.charba.client.commons.Key)
			 */
			@Override
			public IsDefaultAnimationCollection get(Key collection) {
				// checks if collection is valid and is default color collection
				if (contains(collection)) {
					// checks if is colors or visible
					if (Key.equals(DefaultAnimationCollectionKey.COLORS, collection)) {
						// equals then exist
						return DEFAULT_COLORS_COLLECTION;
					} else if (Key.equals(DefaultAnimationCollectionKey.VISIBLE, collection)) {
						// equals then exist
						return visibleCollection;
					}
				}
				// if here, collection not valid or not a default
				return null;
			}
		}

		/**
		 * {@link DefaultAnimationCollectionKey#COLORS} default animation collection for {@link DefaultTransitionMode#SHOW} and {@link DefaultTransitionMode#HIDE} animation mode.
		 * 
		 * @author Andrea "Stock" Stocchero
		 *
		 */
		private static class ShowAndHideDefaultAnimationCollection extends DefaultAnimationCollection {

			private static final String DEFAULT_FROM = HtmlColor.TRANSPARENT.name().toLowerCase(Locale.getDefault());

			/**
			 * Creates a default animation collection wrapping the {@link DefaultAnimationCollectionKey#COLORS}.
			 */
			private ShowAndHideDefaultAnimationCollection() {
				super();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.defaults.globals.DefaultAnimationCollection#getType()
			 */
			@Override
			public AnimationType getType() {
				return AnimationType.COLOR;
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
		 * {@link DefaultAnimationPropertyKey#VISIBLE} default animation property for {@link DefaultTransitionMode#SHOW} and {@link DefaultTransitionMode#HIDE} animation mode.
		 * 
		 * @author Andrea "Stock" Stocchero
		 *
		 */
		private static class ShowAndHideDefaultVisibleAnimationCollection extends DefaultAnimationCollection {

			private static final int DEFAULT_DURATION = 0;

			private final boolean show;

			/**
			 * Creates a default animation property wrapping the {@link DefaultAnimationPropertyKey#VISIBLE}.
			 * 
			 * @param show if <code>true</code>, initializes the object for {@link DefaultTransitionMode#SHOW} mode, otherwise for {@link DefaultTransitionMode#HIDE}
			 */
			private ShowAndHideDefaultVisibleAnimationCollection(boolean show) {
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

}