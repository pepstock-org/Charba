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

import org.pepstock.charba.client.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.defaults.IsDefaultElements;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.defaults.IsDefaultHover;
import org.pepstock.charba.client.defaults.IsDefaultLayout;
import org.pepstock.charba.client.defaults.IsDefaultLegend;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.defaults.IsDefaultPlugins;
import org.pepstock.charba.client.defaults.IsDefaultTitle;
import org.pepstock.charba.client.defaults.IsDefaultTooltips;

/**
 * Abstract CHART.JS default values for OPTIONS element with all inner elements.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class AbstractDefaultOptions implements IsDefaultOptions {

	private final IsDefaultAnimation animation;

	private final IsDefaultHover hover;

	private final IsDefaultElements elements;

	private final IsDefaultLayout layout;

	private final IsDefaultTitle title;

	private final IsDefaultLegend legend;

	private final IsDefaultTooltips tooltips;

	private final IsDefaultPlugins plugins;

	private final IsDefaultFont font = new DefaultFont();

	/**
	 * Creates the object using the defaults inner elements of chart options.
	 */
	protected AbstractDefaultOptions() {
		// stores all inner elements
		// using defaults
		this.animation = new DefaultAnimation();
		this.hover = new DefaultHover();
		this.elements = new DefaultElements();
		this.layout = new DefaultLayout();
		this.title = new DefaultTitle();
		this.legend = new DefaultLegend();
		this.tooltips = new DefaultTooltips();
		this.plugins = new DefaultPlugins();
	}

	/**
	 * Creates the object using the argument options instance as default of chart options.
	 * 
	 * @param options default options instance
	 */
	protected AbstractDefaultOptions(IsDefaultOptions options) {
		// checks if options is consistent
		if (options == null) {
			throw new IllegalArgumentException("Default options argument is null");
		}
		// stores all inner elements
		this.animation = options.getAnimation();
		this.hover = options.getHover();
		this.elements = options.getElements();
		this.layout = options.getLayout();
		this.title = options.getTitle();
		this.legend = options.getLegend();
		this.tooltips = options.getTooltips();
		this.plugins = options.getPlugins();
	}

	/**
	 * Returns the font element.<br>
	 * It contains the global defaults for font.
	 * 
	 * @return the font
	 */
	public final IsDefaultFont getDefaultsFont() {
		return font;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsDefaultOptions#getAnimation()
	 */
	@Override
	public final IsDefaultAnimation getAnimation() {
		return animation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getLayout()
	 */
	@Override
	public final IsDefaultLayout getLayout() {
		return layout;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getElements()
	 */
	@Override
	public final IsDefaultElements getElements() {
		return elements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsDefaultOptions#getHover()
	 */
	@Override
	public final IsDefaultHover getHover() {
		return hover;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsDefaultOptions#getTitle()
	 */
	@Override
	public final IsDefaultTitle getTitle() {
		return title;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsDefaultOptions#getLegend()
	 */
	@Override
	public final IsDefaultLegend getLegend() {
		return legend;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsDefaultOptions#getTooltips()
	 */
	@Override
	public final IsDefaultTooltips getTooltips() {
		return tooltips;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getPlugins()
	 */
	@Override
	public IsDefaultPlugins getPlugins() {
		return plugins;
	}

}
