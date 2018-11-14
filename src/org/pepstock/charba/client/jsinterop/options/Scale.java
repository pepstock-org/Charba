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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.enums.ScaleBounds;
import org.pepstock.charba.client.enums.ScaleDistribution;
import org.pepstock.charba.client.jsinterop.callbacks.AxisBuildTicksCallback;
import org.pepstock.charba.client.jsinterop.callbacks.AxisCalculateTickRotationCallback;
import org.pepstock.charba.client.jsinterop.callbacks.AxisDataLimitsCallback;
import org.pepstock.charba.client.jsinterop.callbacks.AxisDimensionsCallback;
import org.pepstock.charba.client.jsinterop.callbacks.AxisFitCallback;
import org.pepstock.charba.client.jsinterop.callbacks.AxisTickToLabelConversionCallback;
import org.pepstock.charba.client.jsinterop.callbacks.AxisUpdateCallback;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisBuildTicksHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisCalculateTickRotationHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisDataLimitsHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisDimensionsHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisFitHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisTickToLabelConversionHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.AxisUpdateHandler;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.Enumer;
import org.pepstock.charba.client.jsinterop.commons.JsHelper;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale;
import org.pepstock.charba.client.jsinterop.items.AxisItem;
import org.pepstock.charba.client.jsinterop.items.UndefinedValues;

import jsinterop.annotations.JsFunction;

/**
 * Axes are an integral part of a chart. They are used to determine how data maps to a pixel value on the chart. <br>
 * In a cartesian chart, there is 1 or more X axis and 1 or more Y axis to map points onto the 2 dimensional canvas. These axes are know as 'cartesian axes'.<br>
 * Axes that follow a cartesian grid are known as 'Cartesian Axes'. Cartesian axes are used for line, bar, and bubble charts. Four cartesian axes are included by default.<br>
 * <ul>
 * <li>linear
 * <li>logarithmic
 * <li>category
 * <li>time (not implemented yet)
 * </ul>
 * <br>
 * It maps the CHART.JS object of default, <code>chart.defaults.scale</code>.<br>
 * @author Andrea "Stock" Stocchero
 *
 */
public class Scale extends BaseModel<BaseOptions<?,?>, IsDefaultScale, NativeScale>{
	
	@JsFunction
	interface ProxyBeforeUpdateCallback {
		void call(Object context, AxisItem item);
	}

	@JsFunction
	interface ProxyBeforeSetDimensionsCallback {
		void call(Object context, AxisItem item);
	}

	@JsFunction
	interface ProxyAfterSetDimensionsCallback {
		void call(Object context, AxisItem item);
	}

	@JsFunction
	interface ProxyBeforeDataLimitsCallback {
		void call(Object context, AxisItem item);
	}

	@JsFunction
	interface ProxyAfterDataLimitsCallback {
		void call(Object context, AxisItem item);
	}

	@JsFunction
	interface ProxyBeforeBuildTicksCallback {
		void call(Object context, AxisItem item);
	}

	@JsFunction
	interface ProxyAfterBuildTicksCallback {
		void call(Object context, AxisItem item);
	}

	@JsFunction
	interface ProxyBeforeTickToLabelConversionCallback {
		void call(Object context, AxisItem item);
	}

	@JsFunction
	interface ProxyAfterTickToLabelConversionCallback {
		void call(Object context, AxisItem item);
	}

	@JsFunction
	interface ProxyBeforeCalculateTickRotationCallback {
		void call(Object context, AxisItem item);
	}

	@JsFunction
	interface ProxyAfterCalculateTickRotationCallback {
		void call(Object context, AxisItem item);
	}

	@JsFunction
	interface ProxyBeforeFitCallback {
		void call(Object context, AxisItem item);
	}

	@JsFunction
	interface ProxyAfterFitCallback {
		void call(Object context, AxisItem item);
	}

	@JsFunction
	interface ProxyAfterUpdateCallback {
		void call(Object context, AxisItem item);
	}

	private final GridLines gridLines;

	private final Ticks ticks;

	private final ScaleLabel scaleLabel;
	
	private final AngleLines angleLines;
	
	private final PointLabels pointLabels;
	
	private final Time time;
	
	/**
	 * Name of fields of JavaScript object.
	 */
	enum Property implements Key
	{
		beforeUpdate,
		beforeSetDimensions,
		afterSetDimensions,
		beforeDataLimits,
		afterDataLimits,
		beforeBuildTicks,
		afterBuildTicks,
		beforeTickToLabelConversion,
		afterTickToLabelConversion,
		beforeCalculateTickRotation,
		afterCalculateTickRotation,
		beforeFit,
		afterFit,
		afterUpdate
	}

	private final CallbackProxy<ProxyBeforeUpdateCallback> beforeUpdateCallbackProxy = JsHelper.newCallbackProxy();

	private final CallbackProxy<ProxyBeforeSetDimensionsCallback> beforeSetDimensionsCallbackProxy = JsHelper.newCallbackProxy();

	private final CallbackProxy<ProxyAfterSetDimensionsCallback> afterSetDimensionsCallbackProxy = JsHelper.newCallbackProxy();

	private final CallbackProxy<ProxyBeforeDataLimitsCallback> beforeDataLimitsCallbackProxy = JsHelper.newCallbackProxy();

	private final CallbackProxy<ProxyAfterDataLimitsCallback> afterDataLimitsCallbackProxy = JsHelper.newCallbackProxy();

	private final CallbackProxy<ProxyBeforeBuildTicksCallback> beforeBuildTicksCallbackProxy = JsHelper.newCallbackProxy();

	private final CallbackProxy<ProxyAfterBuildTicksCallback> afterBuildTicksCallbackProxy = JsHelper.newCallbackProxy();

	private final CallbackProxy<ProxyBeforeTickToLabelConversionCallback> beforeTickToLabelConversionCallbackProxy = JsHelper.newCallbackProxy();

	private final CallbackProxy<ProxyAfterTickToLabelConversionCallback> afterTickToLabelConversionCallbackProxy = JsHelper.newCallbackProxy();

	private final CallbackProxy<ProxyBeforeCalculateTickRotationCallback> beforeCalculateTickRotationCallbackProxy = JsHelper.newCallbackProxy();

	private final CallbackProxy<ProxyAfterCalculateTickRotationCallback> afterCalculateTickRotationCallbackProxy = JsHelper.newCallbackProxy();

	private final CallbackProxy<ProxyBeforeFitCallback> beforeFitCallbackProxy = JsHelper.newCallbackProxy();

	private final CallbackProxy<ProxyAfterFitCallback> afterFitCallbackProxy = JsHelper.newCallbackProxy();

	private final CallbackProxy<ProxyAfterUpdateCallback> afterUpdateCallbackProxy = JsHelper.newCallbackProxy();
	
	private AxisBuildTicksCallback axisBuildTicksCallback = null;
	
	private AxisCalculateTickRotationCallback axisCalculateTickRotationCallback = null;
	
	private AxisDataLimitsCallback axisDataLimitsCallback = null;
	
	private AxisDimensionsCallback axisDimensionsCallback = null;
	
	private AxisFitCallback axisFitCallback = null;
	
	private AxisTickToLabelConversionCallback axisTickToLabelConversionCallback = null;
	
	private AxisUpdateCallback axisUpdateCallback = null;
	
	private AxisBuildTicksHandler axisBuildTicksHandler = null;
	
	private AxisCalculateTickRotationHandler axisCalculateTickRotationHandler = null;
	
	private AxisDataLimitsHandler axisDataLimitsHandler = null;
	
	private AxisDimensionsHandler axisDimensionsHandler = null;
	
	private AxisFitHandler axisFitHandler = null;
	
	private AxisTickToLabelConversionHandler axisTickToLabelConversionHandler = null;
	
	private AxisUpdateHandler axisUpdateHandler = null;
	
	// here scale is ROOT
	public Scale(IsDefaultScale defaultValues) {
		this(null, defaultValues, null);
	}

	// here scale is ROOT
	protected Scale(IsDefaultScale defaultValues, NativeScale delegated) {
		this(null, defaultValues, delegated);
	}
	
	Scale(BaseOptions<?,?> options, IsDefaultScale defaultValues, NativeScale delegated) {
		super(options, defaultValues, delegated == null ? new NativeScale(): delegated);
		angleLines = new AngleLines(this, getDefaultValues().getAngleLines(), getDelegated().getAngleLines());
		gridLines = new GridLines(this, getDefaultValues().getGrideLines(), getDelegated().getGridLines());
		pointLabels = new PointLabels(this, getDefaultValues().getPointLabels(), getDelegated().getPointLabels());
		scaleLabel = new ScaleLabel(this, getDefaultValues().getScaleLabel(), getDelegated().getScaleLabel());
		ticks = new Ticks(this, getDefaultValues().getTicks(), getDelegated().getTicks());
		time = new Time(this, getDefaultValues().getTime(), getDelegated().getTime());
		beforeUpdateCallbackProxy.setCallback(new ProxyBeforeUpdateCallback() {

			@Override
			public void call(Object context, AxisItem item) {
				if (axisUpdateHandler != null) {
					axisUpdateHandler.onBeforeUpdate(item);
				}
			}
		});

		beforeSetDimensionsCallbackProxy.setCallback(new ProxyBeforeSetDimensionsCallback() {

			@Override
			public void call(Object context, AxisItem item) {
				if (axisDimensionsHandler != null) {
					axisDimensionsHandler.onBeforeSetDimensions(item);
				}
			}
		});

		afterSetDimensionsCallbackProxy.setCallback(new ProxyAfterSetDimensionsCallback() {

			@Override
			public void call(Object context, AxisItem item) {
				if (axisDimensionsHandler != null) {
					axisDimensionsHandler.onAfterSetDimensions(item);
				}
			}
		});

		beforeDataLimitsCallbackProxy.setCallback(new ProxyBeforeDataLimitsCallback() {

			@Override
			public void call(Object context, AxisItem item) {
				if (axisDataLimitsHandler != null) {
					axisDataLimitsHandler.onBeforeDataLimits(item);
				}
			}
		});

		afterDataLimitsCallbackProxy.setCallback(new ProxyAfterDataLimitsCallback() {

			@Override
			public void call(Object context, AxisItem item) {
				if (axisDataLimitsHandler != null) {
					axisDataLimitsHandler.onAfterDataLimits(item);
				}
			}
		});

		beforeBuildTicksCallbackProxy.setCallback(new ProxyBeforeBuildTicksCallback() {

			@Override
			public void call(Object context, AxisItem item) {
				if (axisBuildTicksHandler != null) {
					axisBuildTicksHandler.onBeforeBuildTicks(item);
				}
			}
		});

		afterBuildTicksCallbackProxy.setCallback(new ProxyAfterBuildTicksCallback() {

			@Override
			public void call(Object context, AxisItem item) {
				if (axisBuildTicksHandler != null) {
					axisBuildTicksHandler.onAfterBuildTicks(item);
				}
			}
		});

		beforeTickToLabelConversionCallbackProxy.setCallback(new ProxyBeforeTickToLabelConversionCallback() {

			@Override
			public void call(Object context, AxisItem item) {
				if (axisTickToLabelConversionHandler != null) {
					axisTickToLabelConversionHandler.onBeforeTickToLabelConversion(item);
				}
			}
		});

		afterTickToLabelConversionCallbackProxy.setCallback(new ProxyAfterTickToLabelConversionCallback() {

			@Override
			public void call(Object context, AxisItem item) {
				if (axisTickToLabelConversionHandler != null) {
					axisTickToLabelConversionHandler.onAfterTickToLabelConversion(item);
				}
			}
		});

		beforeCalculateTickRotationCallbackProxy.setCallback(new ProxyBeforeCalculateTickRotationCallback() {

			@Override
			public void call(Object context, AxisItem item) {
				if (axisCalculateTickRotationHandler != null) {
					axisCalculateTickRotationHandler.onBeforeCalculateTickRotation(item);
				}
			}
		});

		afterCalculateTickRotationCallbackProxy.setCallback(new ProxyAfterCalculateTickRotationCallback() {

			@Override
			public void call(Object context, AxisItem item) {
				if (axisCalculateTickRotationHandler != null) {
					axisCalculateTickRotationHandler.onAfterCalculateTickRotation(item);
				}
			}
		});

		beforeFitCallbackProxy.setCallback(new ProxyBeforeFitCallback() {

			@Override
			public void call(Object context, AxisItem item) {
				if (axisFitHandler != null) {
					axisFitHandler.onBeforeFit(item);
				}
			}
		});

		afterFitCallbackProxy.setCallback(new ProxyAfterFitCallback() {

			@Override
			public void call(Object context, AxisItem item) {
				if (axisFitHandler != null) {
					axisFitHandler.onAfterFit(item);
				}
			}
		});

		afterUpdateCallbackProxy.setCallback(new ProxyAfterUpdateCallback() {

			@Override
			public void call(Object context, AxisItem item) {
				if (axisUpdateHandler != null) {
					axisUpdateHandler.onAfterUpdate(item);
				}
			}
		});
	}

	/**
	 * @return the scaleLabel
	 * @see ScaleLabel
	 */
	public ScaleLabel getScaleLabel() {
		return scaleLabel;
	}

	/**
	 * @return the ticks
	 * @see Ticks
	 */
	public Ticks getTicks() {
		return ticks;
	}

	/**
	 * @return the grideLines
	 * @see GridLines
	 */
	public GridLines getGrideLines() {
		return gridLines;
	}
	
	/**
	 * @return the angleLines
	 * @see AngleLines
	 */
	public AngleLines getAngleLines() {
		return angleLines;
	}

	/**
	 * @return the pointLabels
	 * @see PointLabels
	 */
	public PointLabels getPointLabels() {
		return pointLabels;
	}
	
	/**
	 * @return the time
	 */
	public Time getTime() {
		return time;
	}

	/**
	 * The ID is used to link datasets and scale axes together.<br>
	 * This is especially needed if multi-axes charts are used.
	 * 
	 * @param id The ID is used to link datasets and scale axes together
	 */
	public void setId(String id) {
		if (id != null) {
			getDelegated().setId(id);
			// checks if all parents are attached
			checkAndAddToParent();
		}
	}

	/**
	 * The ID is used to link datasets and scale axes together.<br>
	 * This is especially needed if multi-axes charts are used.
	 * 
	 * @return The ID is used to link datasets and scale axes together or <code>null</code> if not set
	 */
	public String getId() {
		return Checker.check(getDelegated().getId(), UndefinedValues.STRING);
	}
	
	/**
	 * Sets if the axis are stacked or not.
	 * 
	 * @param stacked if the axis are stacked or not.
	 */
	public void setStacked(boolean stacked) {
		getDelegated().setStacked(stacked);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns if the axis are stacked or not.
	 * 
	 * @return if the axis are stacked or not. Default is <code>false</code>.
	 */
	public boolean isStacked() {
		return Checker.check(getDelegated().isStacked(), getDefaultValues().isStacked());
	}
	
	/**
	 * Type of scale being employed.
	 * 
	 * @param type type of axis
	 * @see org.pepstock.charba.client.enums.AxisType
	 */
	public void setType(AxisType type) {
		getDelegated().setType(type.name());
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the type of axis.
	 * 
	 * @return the type of axis. If not set, the default is {@link org.pepstock.charba.client.enums.AxisType#linear}.
	 * @see org.pepstock.charba.client.enums.AxisType
	 */
	public AxisType getType() {
		return Enumer.deserialize(getDelegated().getType(), getDefaultValues().getType(), AxisType.class, AxisType.linear);
	}

	/**
	 * The weight used to sort the axis. Higher weights are further away from the chart area.
	 * 
	 * @param weight weight of axis
	 */
	public void setWeight(int weight) {
		getDelegated().setWeight(weight);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * The weight used to sort the axis. Higher weights are further away from the chart area.
	 * 
	 * @return weight of axis. Default is 0.
	 */
	public int getWeight() {
		return Checker.check(getDelegated().getWeight(), getDefaultValues().getWeight());
	}

	/**
	 * If true, shows the axis.
	 * 
	 * @param display if true, shows the axes. 
	 */
	public void setDisplay(boolean display) {
		getDelegated().setDisplay(display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, shows the axis.
	 * 
	 * @return if true, shows the axis. Default is true.
	 */
	public boolean isDisplay() {
		return Checker.check(getDelegated().isDisplay(), getDefaultValues().isDisplay());
	}
	/**
	 * If true, extra space is added to the both edges and the axis is scaled to fit into the chart area.
	 * 
	 * @param offset extra space of axis
	 */
	public void setOffset(boolean offset) {
		getDelegated().setOffset(offset);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, extra space is added to the both edges and the axis is scaled to fit into the chart area.
	 * 
	 * @return extra space of axis. Default is <code>false</code>.
	 */
	public boolean isOffset() {
		return Checker.check(getDelegated().isOffset(), getDefaultValues().isOffset());
	}

	/**
	 * Position of the axis in the chart. Possible values are: 'top', 'left', 'bottom', 'right'
	 * 
	 * @param position position of axis
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public void setPosition(Position position) {
		getDelegated().setPosition(position.name());
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Position of the axis in the chart. Possible values are: 'top', 'left', 'bottom', 'right'
	 * 
	 * @return position of axis. Default is {@link org.pepstock.charba.client.enums.Position#top}.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public Position getPosition() {
		return Enumer.deserialize(getDelegated().getPosition(), getDefaultValues().getPosition(), Position.class, Position.top);
	}

	/**
	 * Sets the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole
	 * category width and put the bars right next to each other.
	 * 
	 * @param barPercentage percent (0-1) of the available width each bar should be within the category width. 1.0 will take the
	 *            whole category width and put the bars right next to each other.
	 */
	public void setBarPercentage(double barPercentage) {
		getDelegated().setBarPercentage(barPercentage);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole
	 * category width and put the bars right next to each other.
	 * 
	 * @return percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole
	 *         category width and put the bars right next to each other. Default is 0.9.
	 */
	public double getBarPercentage() {
		return Checker.check(getDelegated().getBarPercentage(), getDefaultValues().getBarPercentage());
	}

	/**
	 * Sets the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @param categoryPercentage percent (0-1) of the available width each category should be within the sample width.
	 */
	public void setCategoryPercentage(double categoryPercentage) {
		getDelegated().setCategoryPercentage(categoryPercentage);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @return the percent (0-1) of the available width each category should be within the sample width. Default is 0.8.
	 */
	public double getCategoryPercentage() {
		return Checker.check(getDelegated().getCategoryPercentage(), getDefaultValues().getCategoryPercentage());
	}

	/**
	 * Sets the width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take
	 * the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @param barThickness width of each bar in pixels. If not set, the base sample widths are calculated automatically so that
	 *            they take the full available widths without overlap. Then, the bars are sized using barPercentage and
	 *            categoryPercentage.
	 */
	public void setBarThickness(int barThickness) {
		getDelegated().setBarThickness(barThickness);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they
	 * take the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @return width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take
	 *         the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 *         Default is 0.
	 */
	public int getBarThickness() {
		return Checker.check(getDelegated().getBarThickness(), getDefaultValues().getBarThickness());
	}

	/**
	 * Sets the maximum bar thickness, to ensure that bars are not sized thicker than this
	 * 
	 * @param maxBarThickness the maximum bar thickness.
	 */
	public void setMaxBarThickness(int maxBarThickness) {
		getDelegated().setMaxBarThickness(maxBarThickness);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the maximum bar thickness.
	 * 
	 * @return the maximum bar thickness. Default is 0.
	 */
	public int getMaxBarThickness() {
		return Checker.check(getDelegated().getMaxBarThickness(), getDefaultValues().getMaxBarThickness());
	}
	
	/**
	 * Sets property controls the data distribution along the scale.
	 * 
	 * @param distribution property controls the data distribution along the scale.
	 * @see org.pepstock.charba.client.enums.ScaleDistribution
	 */
	public void setDistribution(ScaleDistribution distribution) {
		getDelegated().setDistribution(distribution.name());
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the property controls the data distribution along the scale.
	 * 
	 * @return property controls the data distribution along the scale.
	 * @see org.pepstock.charba.client.enums.ScaleDistribution
	 */
	public ScaleDistribution getDistribution() {
		return Enumer.deserialize(getDelegated().getDistribution(), getDefaultValues().getDistribution(), ScaleDistribution.class, ScaleDistribution.linear);
	}
	
	/**
	 * Sets the property controls the scale boundary strategy (bypassed by min/max time options).
	 * 
	 * @param bounds property controls the scale boundary strategy (bypassed by min/max time options).
	 * @see org.pepstock.charba.client.enums.ScaleBounds
	 */
	public void setBounds(ScaleBounds bounds) {
		getDelegated().setBounds(bounds.name());
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the property controls the scale boundary strategy (bypassed by min/max time options).
	 * 
	 * @return property controls the scale boundary strategy (bypassed by min/max time options).
	 * @see org.pepstock.charba.client.enums.ScaleBounds
	 */
	public ScaleBounds getBounds() {
		return Enumer.deserialize(getDelegated().getBounds(), getDefaultValues().getBounds(), ScaleBounds.class, ScaleBounds.data);
	}
	
	/**
	 * @return the axisBuildTicksCallback
	 */
	public AxisBuildTicksCallback getAxisBuildTicksCallback() {
		return axisBuildTicksCallback;
	}

	/**
	 * @param axisBuildTicksCallback the axisBuildTicksCallback to set
	 */
	public void setAxisBuildTicksCallback(AxisBuildTicksCallback axisBuildTicksCallback) {
		this.axisBuildTicksCallback = axisBuildTicksCallback;
	}

	/**
	 * @return the axisCalculateTickRotationCallback
	 */
	public AxisCalculateTickRotationCallback getAxisCalculateTickRotationCallback() {
		return axisCalculateTickRotationCallback;
	}

	/**
	 * @param axisCalculateTickRotationCallback the axisCalculateTickRotationCallback to set
	 */
	public void setAxisCalculateTickRotationCallback(AxisCalculateTickRotationCallback axisCalculateTickRotationCallback) {
		this.axisCalculateTickRotationCallback = axisCalculateTickRotationCallback;
	}

	/**
	 * @return the axisDataLimitsCallback
	 */
	public AxisDataLimitsCallback getAxisDataLimitsCallback() {
		return axisDataLimitsCallback;
	}

	/**
	 * @param axisDataLimitsCallback the axisDataLimitsCallback to set
	 */
	public void setAxisDataLimitsCallback(AxisDataLimitsCallback axisDataLimitsCallback) {
		this.axisDataLimitsCallback = axisDataLimitsCallback;
	}

	/**
	 * @return the axisDimensionsCallback
	 */
	public AxisDimensionsCallback getAxisDimensionsCallback() {
		return axisDimensionsCallback;
	}

	/**
	 * @param axisDimensionsCallback the axisDimensionsCallback to set
	 */
	public void setAxisDimensionsCallback(AxisDimensionsCallback axisDimensionsCallback) {
		this.axisDimensionsCallback = axisDimensionsCallback;
	}

	/**
	 * @return the axisFitCallback
	 */
	public AxisFitCallback getAxisFitCallback() {
		return axisFitCallback;
	}

	/**
	 * @param axisFitCallback the axisFitCallback to set
	 */
	public void setAxisFitCallback(AxisFitCallback axisFitCallback) {
		this.axisFitCallback = axisFitCallback;
	}

	/**
	 * @return the axisTickToLabelConversionCallback
	 */
	public AxisTickToLabelConversionCallback getAxisTickToLabelConversionCallback() {
		return axisTickToLabelConversionCallback;
	}

	/**
	 * @param axisTickToLabelConversionCallback the axisTickToLabelConversionCallback to set
	 */
	public void setAxisTickToLabelConversionCallback(AxisTickToLabelConversionCallback axisTickToLabelConversionCallback) {
		this.axisTickToLabelConversionCallback = axisTickToLabelConversionCallback;
	}

	/**
	 * @return the axisUpdateCallback
	 */
	public AxisUpdateCallback getAxisUpdateCallback() {
		return axisUpdateCallback;
	}

	/**
	 * @param axisUpdateCallback the axisUpdateCallback to set
	 */
	public void setAxisUpdateCallback(AxisUpdateCallback axisUpdateCallback) {
		this.axisUpdateCallback = axisUpdateCallback;
	}

	/**
	 * @return the axisBuildTicksHandler
	 */
	AxisBuildTicksHandler getAxisBuildTicksHandler() {
		return axisBuildTicksHandler;
	}

	/**
	 * @param axisBuildTicksHandler the axisBuildTicksHandler to set
	 */
	void setAxisBuildTicksHandler(AxisBuildTicksHandler axisBuildTicksHandler) {
		if (axisBuildTicksHandler != null) {
			getDelegated().setBeforeBuildTicks(beforeBuildTicksCallbackProxy.getProxy());
			getDelegated().setAfterBuildTicks(afterBuildTicksCallbackProxy.getProxy());
			// checks if the node is already added to parent
			checkAndAddToParent();
		} else {
			remove(Property.beforeBuildTicks);
			remove(Property.afterBuildTicks);
		}
		this.axisBuildTicksHandler = axisBuildTicksHandler;
	}

	/**
	 * @return the axisCalculateTickRotationHandler
	 */
	AxisCalculateTickRotationHandler getAxisCalculateTickRotationHandler() {
		return axisCalculateTickRotationHandler;
	}

	/**
	 * @param axisCalculateTickRotationHandler the axisCalculateTickRotationHandler to set
	 */
	void setAxisCalculateTickRotationHandler(AxisCalculateTickRotationHandler axisCalculateTickRotationHandler) {
		if (axisCalculateTickRotationHandler != null) {
			getDelegated().setBeforeCalculateTickRotation(beforeCalculateTickRotationCallbackProxy.getProxy());
			getDelegated().setAfterCalculateTickRotation(afterCalculateTickRotationCallbackProxy.getProxy());
			// checks if the node is already added to parent
			checkAndAddToParent();
		} else {
			remove(Property.beforeCalculateTickRotation);
			remove(Property.afterCalculateTickRotation);
		}
		this.axisCalculateTickRotationHandler = axisCalculateTickRotationHandler;
	}

	/**
	 * @return the axisDataLimitsHandler
	 */
	AxisDataLimitsHandler getAxisDataLimitsHandler() {
		return axisDataLimitsHandler;
	}

	/**
	 * @param axisDataLimitsHandler the axisDataLimitsHandler to set
	 */
	void setAxisDataLimitsHandler(AxisDataLimitsHandler axisDataLimitsHandler) {
		if (axisDataLimitsHandler != null) {
			getDelegated().setBeforeDataLimits(beforeDataLimitsCallbackProxy.getProxy());
			getDelegated().setAfterDataLimits(afterDataLimitsCallbackProxy.getProxy());
			// checks if the node is already added to parent
			checkAndAddToParent();
		} else {
			remove(Property.beforeDataLimits);
			remove(Property.afterDataLimits);
		}
		this.axisDataLimitsHandler = axisDataLimitsHandler;
	}

	/**
	 * @return the axisDimensionsHandler
	 */
	AxisDimensionsHandler getAxisDimensionsHandler() {
		return axisDimensionsHandler;
	}

	/**
	 * @param axisDimensionsHandler the axisDimensionsHandler to set
	 */
	void setAxisDimensionsHandler(AxisDimensionsHandler axisDimensionsHandler) {
		if (axisDimensionsHandler != null) {
			getDelegated().setBeforeSetDimensions(beforeSetDimensionsCallbackProxy.getProxy());
			getDelegated().setAfterSetDimensions(afterSetDimensionsCallbackProxy.getProxy());
			// checks if the node is already added to parent
			checkAndAddToParent();
		} else {
			remove(Property.beforeSetDimensions);
			remove(Property.afterSetDimensions);
		}
		this.axisDimensionsHandler = axisDimensionsHandler;
	}

	/**
	 * @return the axisFitHandler
	 */
	AxisFitHandler getAxisFitHandler() {
		return axisFitHandler;
	}

	/**
	 * @param axisFitHandler the axisFitHandler to set
	 */
	void setAxisFitHandler(AxisFitHandler axisFitHandler) {
		if (axisFitHandler != null) {
			getDelegated().setBeforeFit(beforeFitCallbackProxy.getProxy());
			getDelegated().setAfterFit(afterFitCallbackProxy.getProxy());
			// checks if the node is already added to parent
			checkAndAddToParent();
		} else {
			remove(Property.beforeFit);
			remove(Property.afterFit);
		}
		this.axisFitHandler = axisFitHandler;
	}

	/**
	 * @return the axisTickToLabelConversionHandler
	 */
	AxisTickToLabelConversionHandler getAxisTickToLabelConversionHandler() {
		return axisTickToLabelConversionHandler;
	}

	/**
	 * @param axisTickToLabelConversionHandler the axisTickToLabelConversionHandler to set
	 */
	void setAxisTickToLabelConversionHandler(AxisTickToLabelConversionHandler axisTickToLabelConversionHandler) {
		if (axisTickToLabelConversionHandler != null) {
			getDelegated().setBeforeTickToLabelConversion(beforeTickToLabelConversionCallbackProxy.getProxy());
			getDelegated().setAfterTickToLabelConversion(afterTickToLabelConversionCallbackProxy.getProxy());
			// checks if the node is already added to parent
			checkAndAddToParent();
		} else {
			remove(Property.beforeTickToLabelConversion);
			remove(Property.afterTickToLabelConversion);
		}
		this.axisTickToLabelConversionHandler = axisTickToLabelConversionHandler;
	}
	
	/**
	 * @return the axisUpdateHandler
	 */
	AxisUpdateHandler getAxisUpdateHandler() {
		return axisUpdateHandler;
	}

	/**
	 * @param axisUpdateHandler the axisUpdateHandler to set
	 */
	void setAxisUpdateHandler(AxisUpdateHandler axisUpdateHandler) {
		if (axisUpdateHandler != null) {
			getDelegated().setBeforeUpdate(beforeUpdateCallbackProxy.getProxy());
			getDelegated().setAfterUpdate(afterUpdateCallbackProxy.getProxy());
			// checks if the node is already added to parent
			checkAndAddToParent();
		} else {
			remove(Property.beforeUpdate);
			remove(Property.afterUpdate);
		}
		this.axisUpdateHandler = axisUpdateHandler;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseModel#addToParent()
	 */
	@Override
	protected void addToParent() {
		if (getParent().getDelegated().getScale() == null) {
			getParent().getDelegated().setScale(getDelegated());
		}
	}
	
}