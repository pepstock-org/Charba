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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.enums.IndexAxis;
import org.pepstock.charba.client.intl.CLocale;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.utils.Window;

/**
 * CHART.JS default values for OPTIONS element. THIS IS THE ROOT OF ALL ELEMENTS DEFAULTS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DefaultOptions extends AbstractDefaultOptions {

	private static final String DEFAULT_BORDER_COLOR = "rgba(0,0,0,0.1)";

	private static final String DEFAULT_BACKGROUND_COLOR = "rgba(0,0,0,0.1)";

	private static final String DEFAULT_COLOR = "#666";

	private static final boolean DEFAULT_RESPONSIVE = true;

	private static final boolean DEFAULT_MAINTAIN_ASPECT_RATIO = true;

	private static final double DEFAULT_ASPECT_RATIO = 2D;

	private static final double DEFAULT_RESIZE_DELAY = 0D;

	private static final boolean DEFAULT_SHOW_LINE = true;

	private static final boolean DEFAULT_SKIP_NULL = false;

	private static final boolean DEFAULT_SPAN_GAPS = false;

	private static final double DEFAULT_CUTOUT = Undefined.DOUBLE;

	private static final String DEFAULT_CUTOUT_PERCENTAGE = Undefined.STRING;

	private static final double DEFAULT_RADIUS = Undefined.DOUBLE;

	private static final String DEFAULT_RADIUS_PERCENTAGE = "100%";

	private static final double DEFAULT_ROTATION = 0D;

	private static final double DEFAULT_CIRCUMFERENCE = 360D;

	private static final boolean DEFAULT_DRAW_ON_ATTACH = true;

	private static final boolean DEFAULT_DESTROY_ON_DETACH = true;

	private static final List<Event> DEFAULT_EVENTS = Collections.unmodifiableList(Arrays.asList(Event.MOUSE_MOVE, Event.MOUSE_OUT, Event.CLICK, Event.TOUCH_MOVE, Event.TOUCH_START));

	/**
	 * To avoid any instantiation
	 */
	DefaultOptions() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getEvents()
	 */
	@Override
	public List<Event> getEvents() {
		return DEFAULT_EVENTS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getLocale()
	 */
	@Override
	public CLocale getLocale() {
		return CLocale.getDefault();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.DefaultOptions#isResponsive()
	 */
	@Override
	public boolean isResponsive() {
		return DEFAULT_RESPONSIVE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.DefaultOptions#isMaintainAspectRatio()
	 */
	@Override
	public boolean isMaintainAspectRatio() {
		return DEFAULT_MAINTAIN_ASPECT_RATIO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getAspectRatio()
	 */
	@Override
	public double getAspectRatio() {
		return DEFAULT_ASPECT_RATIO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getResizeDelay()
	 */
	@Override
	public double getResizeDelay() {
		return DEFAULT_RESIZE_DELAY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.DefaultOptions#getDevicePixelRatio()
	 */
	@Override
	public double getDevicePixelRatio() {
		return Window.getDevicePixelRatio();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.DefaultOptions#getColorAsString()
	 */
	@Override
	public String getColorAsString() {
		return DEFAULT_COLOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getBackgroundColorAsString()
	 */
	@Override
	public String getBackgroundColorAsString() {
		return DEFAULT_BACKGROUND_COLOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getBorderColorAsString()
	 */
	@Override
	public String getBorderColorAsString() {
		return DEFAULT_BORDER_COLOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.DefaultOptions#isShowLine()
	 */
	@Override
	public boolean isShowLine() {
		return DEFAULT_SHOW_LINE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.DefaultOptions#isSpanGaps()
	 */
	@Override
	public boolean isSpanGaps() {
		return DEFAULT_SPAN_GAPS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getCutout()
	 */
	@Override
	public double getCutout() {
		return DEFAULT_CUTOUT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getCutoutPercentage()
	 */
	@Override
	public String getCutoutPercentage() {
		return DEFAULT_CUTOUT_PERCENTAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getRadius()
	 */
	@Override
	public double getRadius() {
		return DEFAULT_RADIUS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getRadiusPercentage()
	 */
	@Override
	public String getRadiusPercentage() {
		return DEFAULT_RADIUS_PERCENTAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.DefaultOptions#getRotation()
	 */
	@Override
	public double getRotation() {
		return DEFAULT_ROTATION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.DefaultOptions#getCircumference()
	 */
	@Override
	public double getCircumference() {
		return DEFAULT_CIRCUMFERENCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#isDrawOnAttach()
	 */
	@Override
	public boolean isDrawOnAttach() {
		return DEFAULT_DRAW_ON_ATTACH;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#isDestroyOnDetach()
	 */
	@Override
	public boolean isDestroyOnDetach() {
		return DEFAULT_DESTROY_ON_DETACH;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#isSkipNull()
	 */
	@Override
	public boolean isSkipNull() {
		return DEFAULT_SKIP_NULL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getIndexAxis()
	 */
	@Override
	public IndexAxis getIndexAxis() {
		return IndexAxis.X;
	}

}
