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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.callbacks.StartAngleCallback;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.ChartAxisType;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.options.ScaleId;

/**
 * Radial axes are used specifically for the radar and polar area chart types.<br>
 * These axes overlay the chart area, rather than being positioned on one of the edges.<br>
 * The linear scale is use to chart numerical data.<br>
 * As the name suggests, linear interpolation is used to determine where a value lies in relation the center of the axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class RadialAxis extends Axis implements IsLinearAxis {

	// --------------------------------------------
	// -- CALLBACKS PROXIES FOR AXIS PROPERTIES ---
	// --------------------------------------------
	// callback proxy to invoke the start angle function
	private final CallbackProxy<ProxyDoubleCallback> startAngleCallbackProxy = JsHelper.get().newCallbackProxy();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		START_ANGLE("startAngle");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	// ----------------------------------------
	// -- USERS AXIS CALLBACKS x PROPERTIES ---
	// ----------------------------------------
	// user callback implementation for start angle
	private StartAngleCallback startAngleCallback = null;

	// sub elements of axis
	private final Grid grid;

	private final RadialLinearTick ticks;

	private final RadialAngleLines angleLines;

	private final RadialPointLabels pointLabels;
	// min max handler
	private final MinMaxCallbacksHandler<Double> minMaxHandler;
	// begin at zero handler
	private final BegiAtZeroCallbackHandler beginAtZeroHandler;

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	public RadialAxis(IsChart chart) {
		this(chart, DefaultScaleId.R, ChartAxisType.RADIAL_LINEAR, AxisKind.R);
	}

	/**
	 * Builds the object storing the chart instance and cartesian axis type.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param type axis type
	 * @param kind axis kind
	 */
	protected RadialAxis(IsChart chart, ScaleId id, AxisType type, AxisKind kind) {
		super(chart, id, type, kind);
		// initialize sub elements
		this.pointLabels = new RadialPointLabels(this);
		this.grid = new Grid(this);
		this.ticks = new RadialLinearTick(this);
		this.angleLines = new RadialAngleLines(this);
		// creates internal handlers
		this.minMaxHandler = new MinMaxCallbacksHandler<>(this);
		this.beginAtZeroHandler = new BegiAtZeroCallbackHandler(this);
		// -------------------------------------------------
		// -- SET CALLBACKS to PROXIES x AXIS PROPERTIES ---
		// -------------------------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.startAngleCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsNumber(createContext(context), getStartAngleCallback(), getDefaultValues().getStartAngle()).doubleValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.IsLinearAxis#getAxisElement()
	 */
	@Override
	public Axis getAxisElement() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.HasMinMaxHandler#getMinMaxHandler()
	 */
	@Override
	public MinMaxCallbacksHandler<Double> getMinMaxCallbacksHandler() {
		return minMaxHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.HasBeginAtZeroCallbackHandler#getBegiAtZeroCallbackHandler()
	 */
	@Override
	public BegiAtZeroCallbackHandler getBegiAtZeroCallbackHandler() {
		return beginAtZeroHandler;
	}

	/**
	 * Returns the ticks element.
	 * 
	 * @return the ticks
	 */
	public RadialLinearTick getTicks() {
		return ticks;
	}

	/**
	 * Returns the grid element.
	 * 
	 * @return the grid
	 */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * Returns the angle lines element.
	 * 
	 * @return the angleLines
	 */
	public RadialAngleLines getAngleLines() {
		return angleLines;
	}

	/**
	 * Returns the point labels element.
	 * 
	 * @return the pointLabels
	 */
	public RadialPointLabels getPointLabels() {
		return pointLabels;
	}

	/**
	 * Sets whether to animate scaling the chart from the center.
	 * 
	 * @param animate whether to animate scaling the chart from the center.
	 */
	public void setAnimate(boolean animate) {
		getConfiguration().setAnimate(animate);
	}

	/**
	 * Returns whether to animate scaling the chart from the center.
	 * 
	 * @return whether to animate scaling the chart from the center.
	 */
	public boolean isAnimate() {
		return getConfiguration().isAnimate();
	}

	/**
	 * Sets the starting angle to draw arcs for the first item in a data set.
	 * 
	 * @param startAngle starting angle to draw arcs for the first item in a data set.
	 */
	public void setStartAngle(double startAngle) {
		// resets callback
		setStartAngle((StartAngleCallback) null);
		// stores value
		getConfiguration().setStartAngle(startAngle);
	}

	/**
	 * Returns the starting angle to draw arcs for the first item in a data set.
	 * 
	 * @return starting angle to draw arcs for the first item in a data set.
	 */
	public double getStartAngle() {
		return getConfiguration().getStartAngle();
	}

	// -----------------------------
	// AXIS PROPERTIES CALLBACKS
	// -----------------------------

	/**
	 * Returns the user callback that sets the starting angle to draw arcs for the first item in a data set.
	 * 
	 * @return the user callback that sets the starting angle to draw arcs for the first item in a data set.
	 */
	public StartAngleCallback getStartAngleCallback() {
		return startAngleCallback;
	}

	/**
	 * Sets the user callback that sets the starting angle to draw arcs for the first item in a data set.
	 * 
	 * @param startAngleCallback the user callback that sets the starting angle to draw arcs for the first item in a data set.
	 */
	public void setStartAngle(StartAngleCallback startAngleCallback) {
		// sets the callback
		this.startAngleCallback = startAngleCallback;
		// stores and manages callback
		setCallback(getConfiguration(), Property.START_ANGLE, startAngleCallback, startAngleCallbackProxy);
	}

	/**
	 * Sets the user callback that sets the starting angle to draw arcs for the first item in a data set.
	 * 
	 * @param startAngleCallback that sets the starting angle to draw arcs for the first item in a data set.
	 */
	public void setStartAngle(NativeCallback startAngleCallback) {
		// resets callback
		setStartAngle((StartAngleCallback) null);
		// stores and manages callback
		setCallback(getConfiguration(), Property.START_ANGLE, startAngleCallback);
	}
}