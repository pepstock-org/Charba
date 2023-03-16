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
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.defaults.IsDefaultAnimationCollection;
import org.pepstock.charba.client.defaults.IsDefaultAnimations;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.defaults.IsDefaultTooltips;
import org.pepstock.charba.client.defaults.IsDefaultTransitions;
import org.pepstock.charba.client.enums.AnimationType;
import org.pepstock.charba.client.enums.DefaultAnimationCollectionKey;
import org.pepstock.charba.client.enums.DefaultAnimationPropertyKey;
import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.IsInteractionMode;
import org.pepstock.charba.client.enums.IsTooltipPosition;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.enums.TextDirection;
import org.pepstock.charba.client.enums.TooltipAlign;
import org.pepstock.charba.client.enums.TooltipPosition;
import org.pepstock.charba.client.enums.Weight;
import org.pepstock.charba.client.options.IsEvent;

/**
 * CHART.JS default values for TOOLTIPS element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultTooltips implements IsDefaultTooltips {

	private static final boolean DEFAULT_ENABLED = true;

	private static final String DEFAULT_BACKGROUND_COLOR = "rgba(0,0,0,0.8)";

	private static final String DEFAULT_TITLE_FONT_COLOR = "#fff";

	private static final int DEFAULT_TITLE_SPACING = 2;

	private static final int DEFAULT_TITLE_MARGIN_BOTTOM = 6;

	private static final String DEFAULT_BODY_FONT_COLOR = "#fff";

	private static final int DEFAULT_BODY_SPACING = 2;

	private static final String DEFAULT_FOOTER_FONT_COLOR = "#fff";

	private static final int DEFAULT_FOOTER_SPACING = 2;

	private static final int DEFAULT_FOOTER_MARGIN_TOP = 6;

	private static final int DEFAULT_CARET_PADDING = 2;

	private static final int DEFAULT_CARET_SIZE = 5;

	private static final int DEFAULT_CORNER_RADIUS = 6;

	private static final String DEFAULT_MULTI_KEY_BACKGROUND = "#fff";

	private static final boolean DEFAULT_DISPLAY_COLORS = true;

	private static final String DEFAULT_BORDER_COLOR = "rgba(0,0,0,0)";

	private static final int DEFAULT_BORDER_WIDTH = 0;

	private static final boolean DEFAULT_RTL = false;

	private static final boolean DEFAULT_USE_POINT_STYLE = false;

	private static final int DEFAULT_PADDING = 6;

	private static final int DEFAULT_BOX_PADDING = 1;

	private final IsDefaultAnimation animation = new DefaultAnimation();

	private final IsDefaultTransitions transitions = new DefaultTransitions();

	private final IsDefaultAnimations animations = new InternalAnimations();

	private final DefaultPadding padding = new DefaultPadding(DEFAULT_PADDING);

	private final DefaultRoutedFont titleFont = new InternalTitleFont();

	private final DefaultRoutedFont bodyFont = new InternalBodyFont();

	private final DefaultRoutedFont footerFont = new InternalFooterFont();

	/**
	 * To avoid any instantiation
	 */
	DefaultTooltips() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationContainer#getTransitions()
	 */
	@Override
	public IsDefaultTransitions getTransitions() {
		return transitions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationTransition#getAnimation()
	 */
	@Override
	public IsDefaultAnimation getAnimation() {
		return animation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationTransition#getAnimations()
	 */
	@Override
	public IsDefaultAnimations getAnimations() {
		return animations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getPadding()
	 */
	@Override
	public IsDefaultPadding getPadding() {
		return padding;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return DEFAULT_ENABLED;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getEvents()
	 */
	@Override
	public Set<IsEvent> getEvents() {
		return Collections.unmodifiableSet(Defaults.get().getGlobal().getEvents());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getMode()
	 */
	@Override
	public IsInteractionMode getMode() {
		return Defaults.get().getGlobal().getInteraction().getMode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#isIntersect()
	 */
	@Override
	public boolean isIntersect() {
		return Defaults.get().getGlobal().getInteraction().isIntersect();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getPosition()
	 */
	@Override
	public IsTooltipPosition getPosition() {
		return TooltipPosition.AVERAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getBackgroundColorAsString()
	 */
	@Override
	public String getBackgroundColorAsString() {
		return DEFAULT_BACKGROUND_COLOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getTitleFont()
	 */
	@Override
	public IsDefaultFont getTitleFont() {
		return titleFont;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getTitleColorAsString()
	 */
	@Override
	public String getTitleColorAsString() {
		return DEFAULT_TITLE_FONT_COLOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getTitleAlign()
	 */
	@Override
	public TextAlign getTitleAlign() {
		return TextAlign.LEFT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getTitleSpacing()
	 */
	@Override
	public int getTitleSpacing() {
		return DEFAULT_TITLE_SPACING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getTitleMarginBottom()
	 */
	@Override
	public int getTitleMarginBottom() {
		return DEFAULT_TITLE_MARGIN_BOTTOM;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getBodyFont()
	 */
	@Override
	public IsDefaultFont getBodyFont() {
		return bodyFont;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getBodyColorAsString()
	 */
	@Override
	public String getBodyColorAsString() {
		return DEFAULT_BODY_FONT_COLOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getBodyAlign()
	 */
	@Override
	public TextAlign getBodyAlign() {
		return TextAlign.LEFT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getBodySpacing()
	 */
	@Override
	public int getBodySpacing() {
		return DEFAULT_BODY_SPACING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getFooterFont()
	 */
	@Override
	public IsDefaultFont getFooterFont() {
		return footerFont;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getFooterColorAsString()
	 */
	@Override
	public String getFooterColorAsString() {
		return DEFAULT_FOOTER_FONT_COLOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getFooterAlign()
	 */
	@Override
	public TextAlign getFooterAlign() {
		return TextAlign.LEFT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getFooterSpacing()
	 */
	@Override
	public int getFooterSpacing() {
		return DEFAULT_FOOTER_SPACING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getFooterMarginTop()
	 */
	@Override
	public int getFooterMarginTop() {
		return DEFAULT_FOOTER_MARGIN_TOP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getCaretPadding()
	 */
	@Override
	public int getCaretPadding() {
		return DEFAULT_CARET_PADDING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getCaretSize()
	 */
	@Override
	public int getCaretSize() {
		return DEFAULT_CARET_SIZE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getCornerRadius()
	 */
	@Override
	public int getCornerRadius() {
		return DEFAULT_CORNER_RADIUS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getMultiKeyBackgroundAsString()
	 */
	@Override
	public String getMultiKeyBackgroundAsString() {
		return DEFAULT_MULTI_KEY_BACKGROUND;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#isDisplayColors()
	 */
	@Override
	public boolean isDisplayColors() {
		return DEFAULT_DISPLAY_COLORS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getBorderColorAsString()
	 */
	@Override
	public String getBorderColorAsString() {
		return DEFAULT_BORDER_COLOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return DEFAULT_BORDER_WIDTH;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#isRtl()
	 */
	@Override
	public boolean isRtl() {
		return DEFAULT_RTL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getTextDirection()
	 */
	@Override
	public TextDirection getTextDirection() {
		return TextDirection.LEFT_TO_RIGHT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getBoxWidth()
	 */
	@Override
	public int getBoxWidth() {
		return bodyFont.getSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getBoxHeight()
	 */
	@Override
	public int getBoxHeight() {
		return bodyFont.getSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#isUsePointStyle()
	 */
	@Override
	public boolean isUsePointStyle() {
		return DEFAULT_USE_POINT_STYLE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getXAlign()
	 */
	@Override
	public TooltipAlign getXAlign() {
		return TooltipAlign.AUTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getYAlign()
	 */
	@Override
	public TooltipAlign getYAlign() {
		return TooltipAlign.AUTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getBoxPadding()
	 */
	@Override
	public int getBoxPadding() {
		return DEFAULT_BOX_PADDING;
	}

	/**
	 * Internal class extending {@link DefaultRoutedFont} to override some defaults for title.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InternalTitleFont extends DefaultRoutedFont {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.globals.DefaultRoutedFont#getWeight()
		 */
		@Override
		public Weight getWeight() {
			return Weight.BOLD;
		}

	}

	/**
	 * Internal class extending {@link DefaultRoutedFont} to override some defaults for body.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InternalBodyFont extends DefaultRoutedFont {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.globals.DefaultFont#getStyle()
		 */
		@Override
		public FontStyle getStyle() {
			return FontStyle.NORMAL;
		}

	}

	/**
	 * Internal class extending {@link DefaultRoutedFont} to override some defaults for footer.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InternalFooterFont extends DefaultRoutedFont {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.globals.DefaultRoutedFont#getWeight()
		 */
		@Override
		public Weight getWeight() {
			return Weight.BOLD;
		}

	}

	/**
	 * Specific default animations for tooltips.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InternalAnimations extends DefaultAnimations {

		private static final Key OPACITY = Key.create("opacity");

		private static final List<Key> DEFAULT_ANIMATION_COLLECTION_KEYS = Arrays.asList(DefaultAnimationCollectionKey.NUMBERS, OPACITY);

		private static final DefaultAnimationCollection NUMBERS_COLLECTION = new InternalNumbersAnimationCollection();

		private static final DefaultAnimationCollection OPACITY_COLLECTION = new InternalOpacityAnimationCollection();

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultAnimations#contains(org.pepstock.charba.client.commons.Key)
		 */
		@Override
		public boolean contains(Key collection) {
			// checks if collection is valid
			if (Key.isValid(collection)) {
				// scans all defaults
				for (Key defaultCollection : DEFAULT_ANIMATION_COLLECTION_KEYS) {
					// checks if equals
					if (Key.equals(defaultCollection, collection)) {
						// equals then exist
						return true;
					}
				}
			}
			// if here, collection not valid or not a default
			return false;
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
				// checks if is numbers or opacity
				if (Key.equals(DefaultAnimationCollectionKey.NUMBERS, collection)) {
					// equals then returns the default colors
					return NUMBERS_COLLECTION;
				} else if (Key.equals(OPACITY, collection)) {
					// equals then returns the default opacity
					return OPACITY_COLLECTION;
				}
			}
			// if here, collection not valid or not a default
			return null;
		}
	}

	/**
	 * Default NUMBERS animation element properties for tooltip.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InternalNumbersAnimationCollection extends DefaultAnimationCollection {

		private static final List<Key> DEFAULT_ANIMATION_PROPERTIES_KEYS = Collections
				.unmodifiableList(Arrays.asList(DefaultAnimationPropertyKey.X, DefaultAnimationPropertyKey.Y, Key.create("width"), Key.create("height"), Key.create("caretX"), Key.create("caretY")));

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.globals.DefaultAnimationCollection#getProperties()
		 */
		@Override
		public List<Key> getProperties() {
			return DEFAULT_ANIMATION_PROPERTIES_KEYS;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.globals.DefaultAnimationCollection#getType()
		 */
		@Override
		public AnimationType getType() {
			return AnimationType.NUMBER;
		}

	}

	/**
	 * Default OPACITY animation element properties for tooltip.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InternalOpacityAnimationCollection extends DefaultAnimationCollection {

		private static final int DEFAULT_DURATION = 200;

		private List<Key> properties = null;

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.globals.DefaultAnimationCollection#getProperties()
		 */
		@Override
		public List<Key> getProperties() {
			// if the list is null
			if (properties == null) {
				// creates and loads it
				properties = Collections.unmodifiableList(Arrays.asList(InternalAnimations.OPACITY));
			}
			return properties;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.globals.DefaultAnimationCollection#getType()
		 */
		@Override
		public AnimationType getType() {
			return AnimationType.NUMBER;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.globals.AbstractDefaultAnimation#getEasing()
		 */
		@Override
		public Easing getEasing() {
			return Easing.LINEAR;
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

}