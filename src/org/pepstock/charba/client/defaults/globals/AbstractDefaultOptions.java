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
import org.pepstock.charba.client.defaults.IsDefaultDatasets;
import org.pepstock.charba.client.defaults.IsDefaultElements;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.defaults.IsDefaultInteraction;
import org.pepstock.charba.client.defaults.IsDefaultLayout;
import org.pepstock.charba.client.defaults.IsDefaultLegend;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.defaults.IsDefaultPlugins;
import org.pepstock.charba.client.defaults.IsDefaultTitle;
import org.pepstock.charba.client.defaults.IsDefaultTooltips;
import org.pepstock.charba.client.defaults.chart.DefaultChartAnimation;
import org.pepstock.charba.client.defaults.chart.DefaultChartDatasets;
import org.pepstock.charba.client.defaults.chart.DefaultChartElements;
import org.pepstock.charba.client.defaults.chart.DefaultChartFont;
import org.pepstock.charba.client.defaults.chart.DefaultChartHover;
import org.pepstock.charba.client.defaults.chart.DefaultChartInteraction;
import org.pepstock.charba.client.defaults.chart.DefaultChartLayout;
import org.pepstock.charba.client.defaults.chart.DefaultChartLegend;
import org.pepstock.charba.client.defaults.chart.DefaultChartPlugins;
import org.pepstock.charba.client.defaults.chart.DefaultChartTitle;
import org.pepstock.charba.client.defaults.chart.DefaultChartTooltips;

/**
 * Abstract CHART.JS default values for OPTIONS element with all inner elements.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class AbstractDefaultOptions implements IsDefaultOptions {

	private final IsDefaultAnimation animation;

	private final IsDefaultInteraction hover;

	private final IsDefaultInteraction interaction;

	private final IsDefaultElements elements;

	private final IsDefaultLayout layout;

	private final IsDefaultTitle title;

	private final IsDefaultLegend legend;

	private final IsDefaultTooltips tooltips;

	private final IsDefaultPlugins plugins;

	private final IsDefaultDatasets datasets;

	private final IsDefaultFont font;

	/**
	 * Creates the object using the defaults inner elements of chart options.
	 */
	protected AbstractDefaultOptions() {
		// stores all inner elements
		// using defaults
		this.animation = new DefaultAnimation();
		this.hover = new DefaultHover();
		this.interaction = new DefaultInteraction();
		this.elements = new DefaultElements();
		this.layout = new DefaultLayout();
		this.title = new DefaultTitle();
		this.legend = new DefaultLegend();
		this.tooltips = new DefaultTooltips();
		this.plugins = new DefaultPlugins();
		this.datasets = new DefaultDatasets();
		this.font = new DefaultFont();
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
		this.animation = new DefaultChartAnimation(options.getAnimation());
		this.hover = new DefaultChartHover(options.getHover());
		this.interaction = new DefaultChartInteraction(options.getInteraction());
		this.elements = new DefaultChartElements(options.getElements());
		this.layout = new DefaultChartLayout(options.getLayout());
		this.title = new DefaultChartTitle(options.getTitle());
		this.legend = new DefaultChartLegend(options.getLegend());
		this.tooltips = new DefaultChartTooltips(options.getTooltips());
		this.plugins = new DefaultChartPlugins(options.getPlugins());
		this.datasets = new DefaultChartDatasets(options.getDatasets());
		this.font = new DefaultChartFont(new DefaultRoutedFont());
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
	public final IsDefaultInteraction getHover() {
		return hover;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getInteraction()
	 */
	@Override
	public IsDefaultInteraction getInteraction() {
		return interaction;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getDatasets()
	 */
	@Override
	public IsDefaultDatasets getDatasets() {
		return datasets;
	}

}
