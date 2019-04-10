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
import org.pepstock.charba.client.defaults.IsDefaultHover;
import org.pepstock.charba.client.defaults.IsDefaultLayout;
import org.pepstock.charba.client.defaults.IsDefaultLegend;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
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

	/**
	 * Creates the object using the defaults inner elements of chart options.
	 */
	protected AbstractDefaultOptions() {
		this(new DefaultAnimation(), new DefaultHover(), new DefaultElements(), new DefaultLayout(), new DefaultTitle(), new DefaultLegend(), new DefaultTooltips());
	}

	/**
	 * Creates the object using the arguments as default inner elements of chart options.
	 * 
	 * @param animation animation element instance
	 * @param hover hover element instance
	 * @param elements elements element instance
	 * @param layout layout element instance
	 * @param title title element instance
	 * @param legend legend element instance
	 * @param tooltips tooltips element instance
	 */
	protected AbstractDefaultOptions(IsDefaultAnimation animation, IsDefaultHover hover, IsDefaultElements elements, IsDefaultLayout layout, IsDefaultTitle title, IsDefaultLegend legend, IsDefaultTooltips tooltips) {
		this.animation = animation;
		this.hover = hover;
		this.elements = elements;
		this.layout = layout;
		this.title = title;
		this.legend = legend;
		this.tooltips = tooltips;
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

}
