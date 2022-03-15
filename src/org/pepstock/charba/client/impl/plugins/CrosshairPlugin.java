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
package org.pepstock.charba.client.impl.plugins;

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.ChartNode;
import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Helpers;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.ScaleType;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.callbacks.CrosshairFormatterCallback;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.dom.BaseEventTypes;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Context2dItem;
import org.pepstock.charba.client.dom.elements.TextMetricsItem;
import org.pepstock.charba.client.dom.enums.TextBaseline;
import org.pepstock.charba.client.enums.AxisPosition;
import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.events.ChartEventContext;
import org.pepstock.charba.client.items.ChartAreaNode;
import org.pepstock.charba.client.items.PluginEventArgument;
import org.pepstock.charba.client.items.PluginUpdateArgument;
import org.pepstock.charba.client.items.ScaleItem;
import org.pepstock.charba.client.items.ScaleValueItem;
import org.pepstock.charba.client.items.ScalesNode;
import org.pepstock.charba.client.options.IsImmutableFont;
import org.pepstock.charba.client.options.Scale;
import org.pepstock.charba.client.options.Scales;

/**
 * This plugin is drawing horizontal and vertical crosshair on the chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class CrosshairPlugin extends CharbaPlugin<CrosshairOptions> {

	// states map
	private final Map<String, State> states = new HashMap<>();
	// contexts map
	private final Map<String, ChartEventContext> contexts = new HashMap<>();

	/**
	 * To avoid any instantiation
	 */
	CrosshairPlugin() {
		super(Crosshair.ID, Crosshair.FACTORY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.plugins.CharbaPlugin#createDefaultOptionInstance()
	 */
	@Override
	CrosshairOptions createDefaultOptionInstance() {
		return new CrosshairOptions(CrosshairDefaultOptions.INSTANCE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeUpdate(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.items.PluginUpdateArgument)
	 */
	@Override
	public boolean onBeforeUpdate(IsChart chart, PluginUpdateArgument argument) {
		// checks if chart is consistent
		if (mustBeActivated(chart, false)) {
			// loads options
			CrosshairOptions pOptions = loadOptions(chart);
			// checks if the plugin is enabled
			if (pOptions.isEnabled()) {
				// creates a stores the state
				State state = states.computeIfAbsent(chart.getId(), mapKey -> new State());
				// gets chart node
				ChartNode node = chart.getNode();
				// gets scales
				ScalesNode scales = node.getScales();
				// gets scale items
				Map<String, ScaleItem> items = scales.getItems();
				// gets and stores x scale
				state.setXScale(items.get(pOptions.getXScaleID().value()));
				// gets and stores y scale
				state.setYScale(items.get(pOptions.getYScaleID().value()));
				// loads axis
				Scales axes = node.getOptions().getScales();
				// gets and stores x axis
				state.setXAxis(axes.getAxis(pOptions.getXScaleID()));
				// gets and stores y axis
				state.setYAxis(axes.getAxis(pOptions.getYScaleID()));
			} else {
				// removes the contexts
				contexts.remove(chart.getId());
				// removes the state
				states.remove(chart.getId());
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterDraw(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onAfterDraw(IsChart chart) {
		// checks if chart is consistent
		if (mustBeActivated(chart, true) && contexts.containsKey(chart.getId()) && states.containsKey(chart.getId())) {
			// gets option on the cache
			CrosshairOptions options = getOptions(chart);
			// gets event context
			ChartEventContext context = contexts.get(chart.getId());
			// draws lines
			drawLines(chart, options, context);
			// draws labels
			drawLabels(chart, options, context);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterEvent(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.items.PluginEventArgument)
	 */
	@Override
	public void onAfterEvent(IsChart chart, PluginEventArgument argument) {
		// checks if chart is consistent
		if (mustBeActivated(chart, true)) {
			// gets event context
			ChartEventContext context = argument.getEventContext();
			// checks if event is on chart area
			if (!argument.isInChartArea()) {
				// if not removes the state
				contexts.remove(chart.getId());
				// forces redrawing to remove the crosshair
				argument.setChanged(true);
				return;
			}
			// gets base native event
			BaseNativeEvent event = context.getNativeEvent();
			// checks if is mouse move
			if (BaseEventTypes.MOUSE_MOVE.equalsIgnoreCase(event.getType())) {
				// adds context to the state
				contexts.put(chart.getId(), context);
				// forces redrawing to draw the crosshair
				argument.setChanged(true);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeDestroy(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onBeforeDestroy(IsChart chart) {
		// checks if chart is valid
		if (IsChart.isValid(chart)) {
			// removes the stored options for chart
			removeOptions(chart);
			// if not removes the contexts
			contexts.remove(chart.getId());
			// if not removes the state
			states.remove(chart.getId());
		}
	}

	/**
	 * Draws the lines which are creating the crosshair.
	 * 
	 * @param chart chart instance
	 * @param options plugin options
	 * @param context event context
	 */
	private void drawLines(IsChart chart, CrosshairOptions options, ChartEventContext context) {
		// checks if the line must be drawn
		if (options.getLineWidth() > 0 && IsColor.isVisible(options.getLineColor())) {
			// gets chart area
			ChartAreaNode area = chart.getNode().getChartArea();
			// gets state
			State state = states.get(chart.getId());
			// gets context
			Context2dItem ctx = chart.getCanvas().getContext2d();
			// saves the layer
			ctx.save();
			// applies styles
			ctx.setStrokeColor(options.getLineColor());
			ctx.setLineWidth(options.getLineWidth());
			ctx.setLineDash(options.getLineDash());
			ctx.setLineDashOffset(options.getLineDashOffset());
			// begins path
			ctx.beginPath();
			// checks which type of interaction has been set
			if (inXScaleRange(state.getXScale(), context) && hasY(options)) {
				// draws vertical line
				ctx.moveTo(context.getX(), area.getTop());
				ctx.lineTo(context.getX(), area.getBottom());
			}
			if (inYScaleRange(state.getYScale(), context) && hasX(options)) {
				// draws horizontal line
				ctx.moveTo(area.getLeft(), context.getY());
				ctx.lineTo(area.getRight(), context.getY());
			}
			// strokes lines
			ctx.stroke();
			ctx.restore();
		}
	}

	/**
	 * Draws the labels of the lines which are creating the crosshair.
	 * 
	 * @param chart chart instance
	 * @param options plugin options
	 * @param context event context
	 */
	private void drawLabels(IsChart chart, CrosshairOptions options, ChartEventContext context) {
		// checks if there is the state
		if (states.containsKey(chart.getId())) {
			// gets state
			State state = states.get(chart.getId());
			// gets x label options
			CrosshairLabel xLabel = options.getXLabel();
			// checks if x scale is consistent
			if (inXScaleRange(state.getXScale(), context) && state.getXAxis() != null && hasY(options) && xLabel.isDisplay()) {
				// draws the X label
				drawLabel(chart, state.getXScale(), state.getXAxis(), xLabel, context.getX());
			}
			// gets y label options
			CrosshairLabel yLabel = options.getYLabel();
			// checks if y scale is consistent
			if (inYScaleRange(state.getYScale(), context) && state.getYAxis() != null && hasX(options) && xLabel.isDisplay()) {
				// draws the Y label
				drawLabel(chart, state.getYScale(), state.getYAxis(), yLabel, context.getY());
			}
		}
	}

	private void drawLabel(IsChart chart, ScaleItem scale, Scale axis, CrosshairLabel label, double pixel) {
		// checks if the position is supported
		if (!isSupportedPosition(scale)) {
			// if not supported, do nothing
			return;
		}
		ScaleValueItem item = scale.getValueAtPixel(pixel);
		// stored the value
		String labelText = item.getLabel();
		// gets formatter
		CrosshairFormatterCallback formatter = label.getFormatter();
		// checks if there is a formatter callback
		if (formatter != null) {
			// invokes callbacks
			labelText = formatter.format(chart, scale, item);
			// if the result is not consistent
			// the label is skipped
			if (labelText == null || labelText.trim().length() == 0) {
				// skips it
				return;
			}
		}
		// gets chart area
		ChartAreaNode area = chart.getNode().getChartArea();
		// gets canvas
		Canvas canvas = chart.getCanvas();
		// gets context
		Context2dItem ctx = canvas.getContext2d();
		// saves the layer
		ctx.save();
		// gets font
		IsDefaultFont font = label.getFont();
		// sets font
		ctx.setFont(Helpers.get().toFontString(font));
		TextMetricsItem metrics = ctx.measureText(labelText);
		// gets immutable font
		IsImmutableFont immutableValueFont = Helpers.get().toFont(font);
		// calculates positions and sizes for the label drawing
		final int padding = label.getPadding();
		final int margin = axis.getGrid().getTickLength() + axis.getTicks().getPadding();
		final double textWidth = metrics.getWidth();
		final double halfTextWidth = textWidth / 2;
		final double maxWidth = canvas.getOffsetWidth() - (double) padding;
		final double textHeight = immutableValueFont.getLineHeight();
		final double halfTextHeight = textHeight / 2;
		final double maxHeight = canvas.getOffsetHeight() - (double) padding;
		double labelX = 0;
		double labelY = 0;
		// gets scale position
		AxisPosition position = scale.getPosition();
		// calculates the coordinates
		// based on the scale position
		switch (position) {
		case TOP:
			labelX = checkAndGetLabelCoordinate(pixel - halfTextWidth, padding, maxWidth - textWidth);
			labelY = checkAndGetLabelCoordinate(area.getTop() - margin - halfTextHeight, padding, maxHeight - halfTextHeight);
			break;
		case BOTTOM:
			labelX = checkAndGetLabelCoordinate(pixel - halfTextWidth, padding, maxWidth - textWidth);
			labelY = checkAndGetLabelCoordinate(area.getBottom() + margin + halfTextHeight, padding, maxHeight - halfTextHeight);
			break;
		case RIGHT:
			labelX = checkAndGetLabelCoordinate(area.getRight() + margin, padding, maxWidth - textWidth);
			labelY = checkAndGetLabelCoordinate(pixel, padding, maxHeight - halfTextHeight);
			break;
		default:
			labelX = checkAndGetLabelCoordinate(area.getLeft() - margin - textWidth, padding, maxWidth - textWidth);
			labelY = checkAndGetLabelCoordinate(pixel, padding, maxHeight - halfTextHeight);
		}
		// calculates the box position
		double boxX = labelX - padding;
		double boxY = labelY - halfTextHeight - padding;
		// ---------
		// DRAWS BOX
		// ---------
		// begins path
		ctx.beginPath();
		// sets box background color
		ctx.setFillColor(label.getBackgroundColorAsString());
		// draws the rounded rectangle
		ctx.addRoundedRectPath(boxX, boxY, textWidth + padding * 2, textHeight + padding * 2, label.getBarBordeRadius());
		// fills box
		ctx.fill();
		// ---------
		// DRAWS TEXT
		// ---------
		// begins path
		ctx.beginPath();
		// sets font color
		ctx.setFillColor(label.getColorAsString());
		ctx.setTextBaseline(TextBaseline.MIDDLE);
		// draws label
		ctx.fillText(labelText, labelX, labelY);
		ctx.stroke();
		ctx.restore();
	}

	/**
	 * Returns <code>true</code> if the chart is consistent and the scale type is {@link ScaleType#MULTI}.
	 * 
	 * @param chart chart instance to check
	 * @param options if <code>true</code>, the method checks also the consistency o fthe options
	 * @return <code>true</code> if the chart is consistent and the scale type is {@link ScaleType#MULTI}
	 */
	private boolean mustBeActivated(IsChart chart, boolean options) {
		// checks consistent and the chart are multiple scales
		boolean mustBeActivated = IsChart.isConsistent(chart) && checkChartBaseTypes(chart);
		// if the first check is true and options must be checked...
		if (mustBeActivated && options) {
			// sets if the options are loaded
			mustBeActivated = hasOptions(chart);
			// checks if the plugin is enabled
			if (mustBeActivated) {
				// gets option on the cache
				CrosshairOptions pOptions = getOptions(chart);
				// checks if enabled
				mustBeActivated = pOptions.isEnabled();
			}
		}
		return mustBeActivated;
	}

	/**
	 * Returns <code>true</code> if the chart is manageable by this plugin.
	 * 
	 * @param chart chart instance to check
	 * @return <code>true</code> if the chart is manageable by this plugin
	 */
	private boolean checkChartBaseTypes(IsChart chart) {
		// gets base type
		Type type = chart.getBaseType();
		// scans all out of the box chart types
		for (ChartType chartType : ChartType.values()) {
			// checks if multi scale and equals
			if (chartType.equals(type) && ScaleType.MULTI.equals(chartType.scaleType())) {
				// found therefore can be managed
				return true;
			}
		}
		// if here, the chart is not manageable by this plugin
		return false;
	}

	/**
	 * Returns <code>true</code> if the event is on X scale range. Needed for stacked scales.
	 * 
	 * @param scale scale instance
	 * @param context event context to check
	 * @return <code>true</code> if the event is on X scale range. Needed for stacked scales.
	 */
	private boolean inXScaleRange(ScaleItem scale, ChartEventContext context) {
		// checks if scale is consistent
		if (scale != null && scale.isHorizontal()) {
			return scale.getLeft() <= context.getX() && scale.getRight() >= context.getX();

		}
		// is here, the scale is not consistent or not horizontal
		return false;
	}

	/**
	 * Returns <code>true</code> if the event is on Y scale range. Needed for stacked scales.
	 * 
	 * @param scale scale instance
	 * @param context event context to check
	 * @return <code>true</code> if the event is on Y scale range. Needed for stacked scales.
	 */
	private boolean inYScaleRange(ScaleItem scale, ChartEventContext context) {
		// checks if scale is consistent
		if (scale != null && !scale.isHorizontal()) {
			return scale.getTop() <= context.getY() && scale.getBottom() >= context.getY();
		}
		// is here, the scale is not consistent or horizontal
		return false;
	}

	/**
	 * Returns <code>true</code> if the user mode is X oriented.
	 * 
	 * @param options plugin option to get the mode
	 * @return <code>true</code> if the user mode is X oriented
	 */
	private boolean hasX(CrosshairOptions options) {
		return InteractionAxis.X.equals(options.getMode()) || InteractionAxis.XY.equals(options.getMode());
	}

	/**
	 * Returns <code>true</code> if the user mode is Y oriented.
	 * 
	 * @param options plugin option to get the mode
	 * @return <code>true</code> if the user mode is Y oriented
	 */
	private boolean hasY(CrosshairOptions options) {
		return InteractionAxis.Y.equals(options.getMode()) || InteractionAxis.XY.equals(options.getMode());
	}

	/**
	 * Checks if the coordinate is out of range and adjusts it accordingly.
	 * 
	 * @param value value to check
	 * @param min minimum value of the coordinate
	 * @param max maximum value of the coordinate
	 * @return the value checked against the size
	 */
	private double checkAndGetLabelCoordinate(double value, double min, double max) {
		// if value is less than minimum
		if (value <= min) {
			// returns the minimum
			return min;
		}
		// if value is greater than minimum
		if (value >= max) {
			// returns the maximum
			return max;
		}
		// if here the value is in the range
		// then returns the value itself
		return value;
	}

	/**
	 * Returns <code>true</code> if the position of the scale is supported.
	 * 
	 * @param scale the scale item to check
	 * @return <code>true</code> if the position of the scale is supported
	 */
	private boolean isSupportedPosition(ScaleItem scale) {
		// gets scale position
		AxisPosition position = scale.getPosition();
		// checks supported position
		return AxisPosition.LEFT.equals(position) || AxisPosition.RIGHT.equals(position) || AxisPosition.TOP.equals(position) || AxisPosition.BOTTOM.equals(position);
	}

	/**
	 * Internal object to maintain the instances needed to draw the crosshair by every chart instance.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class State {

		private Scale xAxis = null;

		private Scale yAxis = null;

		private ScaleItem xScale = null;

		private ScaleItem yScale = null;

		/**
		 * Returns the X scale instance
		 * 
		 * @return the X scale instance
		 */
		private ScaleItem getXScale() {
			return xScale;
		}

		/**
		 * Sets the X scale instance
		 * 
		 * @param xScale the X scale instance
		 */
		private void setXScale(ScaleItem xScale) {
			this.xScale = xScale;
		}

		/**
		 * Returns the Y scale instance
		 * 
		 * @return the Y scale instance
		 */
		private ScaleItem getYScale() {
			return yScale;
		}

		/**
		 * Sets the Y scale instance
		 * 
		 * @param yScale the Y scale instance
		 */
		private void setYScale(ScaleItem yScale) {
			this.yScale = yScale;
		}

		/**
		 * Returns the X axis instance
		 * 
		 * @return the X axis instance
		 */
		private Scale getXAxis() {
			return xAxis;
		}

		/**
		 * Sets the X axis instance
		 * 
		 * @param xAxis the X axis instance
		 */
		private void setXAxis(Scale xAxis) {
			this.xAxis = xAxis;
		}

		/**
		 * Returns the Y axis instance
		 * 
		 * @return the Y axis instance
		 */
		private Scale getYAxis() {
			return yAxis;
		}

		/**
		 * Sets the Y axis instance
		 * 
		 * @param yAxis the Y axis instance
		 */
		private void setYAxis(Scale yAxis) {
			this.yAxis = yAxis;
		}

	}
}
