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
package org.pepstock.charba.client.jsinterop.configuration;

import java.util.List;

import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.CartesianAxisType;
import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.ChartOptions;
import org.pepstock.charba.client.jsinterop.Defaults;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisBuildTicksHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisCalculateTickRotationHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisDataLimitsHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisDimensionsHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisFitHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisTickToLabelConversionHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisUpdateHandler;
import org.pepstock.charba.client.jsinterop.defaults.chart.DefaultChartScale;
import org.pepstock.charba.client.jsinterop.items.AxisItem;
import org.pepstock.charba.client.jsinterop.options.Scale;
import org.pepstock.charba.client.jsinterop.utils.Window;

/**
 * Axes are an integral part of a chart.<br>
 * They are used to determine how data maps to a pixel value on the chart.<br>
 * It contains a number of config callbacks that can be used to change parameters in the scale at different points in the update
 * process.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class Axis extends ConfigurationContainer<Scale> implements AxisBuildTicksHandler, AxisCalculateTickRotationHandler, AxisDataLimitsHandler, AxisDimensionsHandler, AxisFitHandler, AxisTickToLabelConversionHandler, AxisUpdateHandler {

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	protected Axis(AbstractChart<?, ?> chart) {
		super(chart);
		setConfiguration(new Scale(new DefaultChartScale(getDefaultScale())));
		if (hasGlobalBuildTicksCallback()) {
			chart.getOptions().getConfiguration().setAxisBuildTicksHandler(getScale(), this);
		}
		if (hasGlobalCalculateTickRotationCallback()) {
			chart.getOptions().getConfiguration().setAxisCalculateTickRotationHandler(getScale(), this);
		}
		if (hasGlobalDataLimitsCallback()) {
			chart.getOptions().getConfiguration().setAxisDataLimitsHandler(getScale(), this);
		}
		if (hasGlobalDimensionsCallback()) {
			chart.getOptions().getConfiguration().setAxisDimensionsHandler(getScale(), this);
		}
		if (hasGlobalFitCallback()) {
			chart.getOptions().getConfiguration().setAxisFitHandler(getScale(), this);
		}
		if (hasGlobalTickToLabelConversionCallback()) {
			chart.getOptions().getConfiguration().setAxisTickToLabelConversionHandler(getScale(), this);
		}
		if (hasGlobalUpdateCallback()) {
			chart.getOptions().getConfiguration().setAxisUpdateHandler(getScale(), this);
		}
	}

	private boolean hasGlobalBuildTicksCallback() {
		return getDefaultScale().getAxisBuildTicksCallback() != null;  
	}
	
	private boolean hasGlobalCalculateTickRotationCallback() {
		return getDefaultScale().getAxisCalculateTickRotationCallback() != null;  
	}

	private boolean hasGlobalDataLimitsCallback() {
		return getDefaultScale().getAxisDataLimitsCallback() != null;  
	}

	private boolean hasGlobalDimensionsCallback() {
		return getDefaultScale().getAxisDimensionsCallback() != null;  
	}

	private boolean hasGlobalFitCallback() {
		return getDefaultScale().getAxisFitCallback() != null;  
	}

	private boolean hasGlobalTickToLabelConversionCallback() {
		return getDefaultScale().getAxisTickToLabelConversionCallback() != null;  
	}

	private boolean hasGlobalUpdateCallback() {
		return getDefaultScale().getAxisUpdateCallback() != null;  
	}
	
	/**
	 * Type of scale being employed.
	 * 
	 * @param type type of axis
	 * @see org.pepstock.charba.client.enums.AxisType
	 */
	public void setType(AxisType type) {
		getScale().setType(type);
	}

	/**
	 * Returns the type of axis.
	 * 
	 * @return the type of axis
	 * @see org.pepstock.charba.client.enums.AxisType
	 */
	public AxisType getType() {
		return getScale().getType();
	}

	/**
	 * If set to false the axis is hidden from view. Overrides gridLines.display, scaleLabel.display, and ticks.display.
	 * 
	 * @param display If set to false the axis is hidden from view. Overrides gridLines.display, scaleLabel.display, and
	 *            ticks.display.
	 */
	public void setDisplay(boolean display) {
		getScale().setDisplay(display);
	}

	/**
	 * Returns if axis is hidden.
	 * 
	 * @return <code>false</code> if axis is hidden, otherwise <code>true</code>.
	 */
	public boolean isDisplay() {
		return getScale().isDisplay();
	}

	/**
	 * The weight used to sort the axis. Higher weights are further away from the chart area.
	 * 
	 * @param weight weight of axis
	 */
	public void setWeight(int weight) {
		getScale().setWeight(weight);
	}

	/**
	 * The weight used to sort the axis. Higher weights are further away from the chart area.
	 * 
	 * @return weight of axis
	 */
	public int getWeight() {
		return getScale().getWeight();
	}

	/**
	 * @return the options
	 */
	final Scale getScale() {
		return super.getConfiguration();
	}

	/**
	 * Returns the global options for this chart.
	 * 
	 * @return the global options for this chart.
	 */
	private Scale getDefaultScale() {
		// gets the gloabl option for the chart.
		ChartOptions options = Defaults.options(getChart().getType());
		// if is a multi scale chart
		if (getChart().getOptions() instanceof MultiScalesOptions) {
			CartesianAxisType type = null;
			// checks if is cartesian axis
			// only cartesian axis has got the mutli scale
			if (this instanceof CartesianAxis<?>) {
				CartesianAxis<?> cAxis = (CartesianAxis<?>) this;
				// gets cartesian type
				type = cAxis.getCartesianType();
			}
			// if type is consistent
			if (type != null) {
				// returns the option for x or y scale.
				return getCartesianScale(CartesianAxisType.x.equals(type) ? options.getScales().getXAxes() : options.getScales().getYAxes());
			}
		} else if (getChart().getOptions() instanceof SingleScaleOptions) {
			// being a single escale
			// returns scale otpion
			return options.getScale();
		}
		// returns default scale
		return Defaults.getScale();
	}

	/**
	 * Returns the scale option for multi scale chart using the axis type.
	 * 
	 * @param scales X or Y scale option.
	 * @return a scale object with axis configuration
	 */
	private Scale getCartesianScale(List<Scale> scales) {
		// scan all configuration axes
		for (Scale axis : scales) {
			// if configuration type equals to this axis
			if (getType().equals(axis.getType())) {
				// returns scale config
				return axis;
			}
		}
		// returns default scale
		return Defaults.getScale();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisUpdateHandler#onBeforeUpdate(org.pepstock.charba.client.jsinterop.utils.Window, org.pepstock.charba.client.jsinterop.items.AxisItem)
	 */
	@Override
	public void onBeforeUpdate(Window window, AxisItem item) {
		if (getConfiguration().getAxisUpdateCallback() != null) {
			getConfiguration().getAxisUpdateCallback().onBeforeUpdate(getChart(), item);
		} else if (hasGlobalUpdateCallback()) {
			getDefaultScale().getAxisUpdateCallback().onBeforeUpdate(getChart(), item);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisUpdateHandler#onAfterUpdate(org.pepstock.charba.client.jsinterop.utils.Window, org.pepstock.charba.client.jsinterop.items.AxisItem)
	 */
	@Override
	public void onAfterUpdate(Window window, AxisItem item) {
		if (getConfiguration().getAxisUpdateCallback() != null) {
			getConfiguration().getAxisUpdateCallback().onAfterUpdate(getChart(), item);
		} else if (hasGlobalUpdateCallback()) {
			getDefaultScale().getAxisUpdateCallback().onAfterUpdate(getChart(), item);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisTickToLabelConversionHandler#onBeforeTickToLabelConversion(org.pepstock.charba.client.jsinterop.utils.Window, org.pepstock.charba.client.jsinterop.items.AxisItem)
	 */
	@Override
	public void onBeforeTickToLabelConversion(Window window, AxisItem item) {
		if (getConfiguration().getAxisTickToLabelConversionCallback() != null) {
			getConfiguration().getAxisTickToLabelConversionCallback().onBeforeTickToLabelConversion(getChart(), item);
		} else if (hasGlobalTickToLabelConversionCallback()) {
			getDefaultScale().getAxisTickToLabelConversionCallback().onBeforeTickToLabelConversion(getChart(), item);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisTickToLabelConversionHandler#onAfterTickToLabelConversion(org.pepstock.charba.client.jsinterop.utils.Window, org.pepstock.charba.client.jsinterop.items.AxisItem)
	 */
	@Override
	public void onAfterTickToLabelConversion(Window window, AxisItem item) {
		if (getConfiguration().getAxisTickToLabelConversionCallback() != null) {
			getConfiguration().getAxisTickToLabelConversionCallback().onAfterTickToLabelConversion(getChart(), item);
		} else if (hasGlobalTickToLabelConversionCallback()) {
			getDefaultScale().getAxisTickToLabelConversionCallback().onAfterTickToLabelConversion(getChart(), item);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisFitHandler#onBeforeFit(org.pepstock.charba.client.jsinterop.utils.Window, org.pepstock.charba.client.jsinterop.items.AxisItem)
	 */
	@Override
	public void onBeforeFit(Window window, AxisItem item) {
		if (getConfiguration().getAxisFitCallback() != null) {
			getConfiguration().getAxisFitCallback().onBeforeFit(getChart(), item);
		} else if (hasGlobalFitCallback()) {
			getDefaultScale().getAxisFitCallback().onBeforeFit(getChart(), item);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisFitHandler#onAfterFit(org.pepstock.charba.client.jsinterop.utils.Window, org.pepstock.charba.client.jsinterop.items.AxisItem)
	 */
	@Override
	public void onAfterFit(Window window, AxisItem item) {
		if (getConfiguration().getAxisFitCallback() != null) {
			getConfiguration().getAxisFitCallback().onAfterFit(getChart(), item);
		} else if (hasGlobalFitCallback()) {
			getDefaultScale().getAxisFitCallback().onAfterFit(getChart(), item);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisDimensionsHandler#onBeforeSetDimensions(org.pepstock.charba.client.jsinterop.utils.Window, org.pepstock.charba.client.jsinterop.items.AxisItem)
	 */
	@Override
	public void onBeforeSetDimensions(Window window, AxisItem item) {
		if (getConfiguration().getAxisDimensionsCallback() != null) {
			getConfiguration().getAxisDimensionsCallback().onBeforeSetDimensions(getChart(), item);
		} else if (hasGlobalDimensionsCallback()) {
			getDefaultScale().getAxisDimensionsCallback().onBeforeSetDimensions(getChart(), item);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisDimensionsHandler#onAfterSetDimensions(org.pepstock.charba.client.jsinterop.utils.Window, org.pepstock.charba.client.jsinterop.items.AxisItem)
	 */
	@Override
	public void onAfterSetDimensions(Window window, AxisItem item) {
		if (getConfiguration().getAxisDimensionsCallback() != null) {
			getConfiguration().getAxisDimensionsCallback().onAfterSetDimensions(getChart(), item);
		} else if (hasGlobalDimensionsCallback()) {
			getDefaultScale().getAxisDimensionsCallback().onAfterSetDimensions(getChart(), item);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisDataLimitsHandler#onBeforeDataLimits(org.pepstock.charba.client.jsinterop.utils.Window, org.pepstock.charba.client.jsinterop.items.AxisItem)
	 */
	@Override
	public void onBeforeDataLimits(Window window, AxisItem item) {
		if (getConfiguration().getAxisDataLimitsCallback() != null) {
			getConfiguration().getAxisDataLimitsCallback().onBeforeDataLimits(getChart(), item);
		} else if (hasGlobalDataLimitsCallback()) {
			getDefaultScale().getAxisDataLimitsCallback().onBeforeDataLimits(getChart(), item);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisDataLimitsHandler#onAfterDataLimits(org.pepstock.charba.client.jsinterop.utils.Window, org.pepstock.charba.client.jsinterop.items.AxisItem)
	 */
	@Override
	public void onAfterDataLimits(Window window, AxisItem item) {
		if (getConfiguration().getAxisDataLimitsCallback() != null) {
			getConfiguration().getAxisDataLimitsCallback().onAfterDataLimits(getChart(), item);
		} else if (hasGlobalDataLimitsCallback()) {
			getDefaultScale().getAxisDataLimitsCallback().onAfterDataLimits(getChart(), item);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisCalculateTickRotationHandler#onBeforeCalculateTickRotation(org.pepstock.charba.client.jsinterop.utils.Window, org.pepstock.charba.client.jsinterop.items.AxisItem)
	 */
	@Override
	public void onBeforeCalculateTickRotation(Window window, AxisItem item) {
		if (getConfiguration().getAxisCalculateTickRotationCallback() != null) {
			getConfiguration().getAxisCalculateTickRotationCallback().onBeforeCalculateTickRotation(getChart(), item);
		} else if (hasGlobalCalculateTickRotationCallback()) {
			getDefaultScale().getAxisCalculateTickRotationCallback().onBeforeCalculateTickRotation(getChart(), item);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisCalculateTickRotationHandler#onAfterCalculateTickRotation(org.pepstock.charba.client.jsinterop.utils.Window, org.pepstock.charba.client.jsinterop.items.AxisItem)
	 */
	@Override
	public void onAfterCalculateTickRotation(Window window, AxisItem item) {
		if (getConfiguration().getAxisCalculateTickRotationCallback() != null) {
			getConfiguration().getAxisCalculateTickRotationCallback().onAfterCalculateTickRotation(getChart(), item);
		} else if (hasGlobalCalculateTickRotationCallback()) {
			getDefaultScale().getAxisCalculateTickRotationCallback().onAfterCalculateTickRotation(getChart(), item);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisBuildTicksHandler#onBeforeBuildTicks(org.pepstock.charba.client.jsinterop.utils.Window, org.pepstock.charba.client.jsinterop.items.AxisItem)
	 */
	@Override
	public void onBeforeBuildTicks(Window window, AxisItem item) {
		if (getConfiguration().getAxisBuildTicksCallback() != null) {
			getConfiguration().getAxisBuildTicksCallback().onBeforeBuildTicks(getChart(), item);
		} else if (hasGlobalBuildTicksCallback()) {
			getDefaultScale().getAxisBuildTicksCallback().onBeforeBuildTicks(getChart(), item);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisBuildTicksHandler#onAfterBuildTicks(org.pepstock.charba.client.jsinterop.utils.Window, org.pepstock.charba.client.jsinterop.items.AxisItem)
	 */
	@Override
	public void onAfterBuildTicks(Window window, AxisItem item) {
		if (getConfiguration().getAxisBuildTicksCallback() != null) {
			getConfiguration().getAxisBuildTicksCallback().onAfterBuildTicks(getChart(), item);
		} else if (hasGlobalBuildTicksCallback()) {
			getDefaultScale().getAxisBuildTicksCallback().onAfterBuildTicks(getChart(), item);
		}
	}
}