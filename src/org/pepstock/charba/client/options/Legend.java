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
package org.pepstock.charba.client.options;

import java.util.logging.Logger;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.commons.EventProvider;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.events.ChartClickEvent;
import org.pepstock.charba.client.events.ChartHoverEvent;
import org.pepstock.charba.client.events.ChartNativeEvent;
import org.pepstock.charba.client.events.DatasetSelectionEvent;
import org.pepstock.charba.client.events.LegendClickEvent;
import org.pepstock.charba.client.events.LegendHoverEvent;
import org.pepstock.charba.client.items.DatasetMetaItem;
import org.pepstock.charba.client.items.LegendItem;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;

/**
 * The chart legend displays data about the datasets that area appearing on the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Legend extends EventProvider {

	private final LegendLabels labels;

	// amount of click handlers
	private int onClickHandlers = 0;
	// amount of hover handlers
	private int onHoverHandlers = 0;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		display,
		position,
		fullWidth,
		reverse,
		labels,
		onClick,
		onHover
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	Legend(AbstractChart<?, ?> chart) {
		super(chart);
		labels = new LegendLabels(chart);
		setValue(Property.labels, labels);
	}

	/**
	 * @return the labels
	 */
	public LegendLabels getLabels() {
		return labels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.ChartContainer#addHandler(com.google. gwt.event.shared.GwtEvent.Type)
	 */
	@Override
	protected <H extends EventHandler> void addHandler(Type<H> type) {
		// checks if type of added event handler is dataset selection or click
		if (type.equals(ChartClickEvent.TYPE)) {
			// if java script property is missing
			if (!has(Property.onClick)) {
				// adds the java script function to catch the event
				registerNativeClickHandler(getJavaScriptObject());
			}
			// increments amount of handlers
			onClickHandlers++;
		} else if (type.equals(ChartHoverEvent.TYPE)) {
			// if java script property is missing
			if (!has(Property.onHover)) {
				// adds the java script function to catch the event
				registerNativeHoverHandler(getJavaScriptObject());
			}
			// increments amount of handlers
			onHoverHandlers++;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.ChartContainer#removeHandler(org. pepstock.charba.client.commons.Key)
	 */
	@Override
	protected <H extends EventHandler> void removeHandler(Type<H> type) {
		// checks if type of removed event handler is dataset selection or click
		if (type.equals(DatasetSelectionEvent.TYPE) || type.equals(ChartClickEvent.TYPE)) {
			// decrements the amount of handlers
			onClickHandlers--;
			// if there is not any handler
			if (onClickHandlers == 0) {
				// removes the java script object
				remove(Property.onClick);
			}
		} else if (type.equals(ChartHoverEvent.TYPE)) {
			// decrements the amount of handlers
			onHoverHandlers--;
			// if there is not any handler
			if (onHoverHandlers == 0) {
				// removes the java script object
				remove(Property.onHover);
			}
		}
	}

	/**
	 * Sets if the legend is shown.
	 * 
	 * @param display if the legend is shown.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
	}

	/**
	 * Returns if the legend is shown.
	 * 
	 * @return if the legend is shown. Default is {@link org.pepstock.charba.client.defaults.global.Legend#isDisplay()}.
	 */
	public boolean isDisplay() {
		return getValue(Property.display, getChart().getGlobal().getLegend().isDisplay());
	}

	/**
	 * Marks that this box should take the full width of the canvas (pushing down other boxes).
	 * 
	 * @param fullWidth Marks that this box should take the full width of the canvas (pushing down other boxes)
	 */
	public void setFullWidth(boolean fullWidth) {
		setValue(Property.fullWidth, fullWidth);
	}

	/**
	 * Returns if marks that this box should take the full width of the canvas (pushing down other boxes)
	 * 
	 * @return Marks that this box should take the full width of the canvas (pushing down other boxes). Default is {@link org.pepstock.charba.client.defaults.global.Legend#isFullWidth()}.
	 */
	public boolean isFullWidth() {
		return getValue(Property.fullWidth, getChart().getGlobal().getLegend().isFullWidth());
	}

	/**
	 * Sets the legend will show datasets in reverse order.
	 * 
	 * @param reverse legend will show datasets in reverse order.
	 */
	public void setReverse(boolean reverse) {
		setValue(Property.reverse, reverse);
	}

	/**
	 * Returns if the legend will show datasets in reverse order.
	 * 
	 * @return Legend will show datasets in reverse order. Default is {@link org.pepstock.charba.client.defaults.global.Legend#isReverse()}.
	 */
	public boolean isReverse() {
		return getValue(Property.reverse, getChart().getGlobal().getLegend().isReverse());
	}

	/**
	 * Sets the position of the legend.
	 * 
	 * @param position Position of the legend.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public void setPosition(Position position) {
		setValue(Property.position, position);
	}

	/**
	 * Returns the position of the legend.
	 * 
	 * @return Position of the legend. Default is {@link org.pepstock.charba.client.defaults.global.Legend#getPosition()}.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public Position getPosition() {
		return getValue(Property.position, Position.class, getChart().getGlobal().getLegend().getPosition());
	}

	final Logger log = Logger.getLogger("mio");
	
	/**
	 * A callback that is called when a click event is registered on a label item
	 * 
	 * @param event event generated by chart.
	 * @param item legend item affected by event.
	 */
	protected void onClick(ChartNativeEvent event, LegendItem item) {
		getChart().fireEvent(new LegendClickEvent(event, item));
	}
	
	private void executeInternalOnClickEvent(ChartNativeEvent event, LegendItem item){
		if (getChart().getType().equals(org.pepstock.charba.client.Type.pie) ||
				getChart().getType().equals(org.pepstock.charba.client.Type.polarArea) ||
				getChart().getType().equals(org.pepstock.charba.client.Type.doughnut)){
			
		} else {
			int index = item.getDatasetIndex();
			DatasetMetaItem meta = getChart().getDatasetMeta(index);

			// See controller.isDatasetVisible comment
			meta.setHidden(!meta.isHidden());

			// We hid a dataset ... rerender the chart
			getChart().update();						
		}
	}

	/**
	 * A callback that is called when a 'mousemove' event is registered on top of a label item
	 * 
	 * @param event event generated by chart.
	 * @param item legend item affected by event.
	 */
	protected void onHover(ChartNativeEvent event, LegendItem item) {
		getChart().fireEvent(new LegendHoverEvent(event, item));
	}
    
	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
    private native void registerNativeClickHandler(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.onClick = function(event, legendItem){
	    	self.@org.pepstock.charba.client.options.Legend::onClick(Lorg/pepstock/charba/client/events/ChartNativeEvent;Lorg/pepstock/charba/client/items/LegendItem;)(event, legendItem);
	    }
	}-*/;

	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
    private native void registerNativeHoverHandler(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.onHover = function(event, legendItem){
	    	self.@org.pepstock.charba.client.options.Legend::onHover(Lorg/pepstock/charba/client/events/ChartNativeEvent;Lorg/pepstock/charba/client/items/LegendItem;)(event, legendItem);
	    }
	}-*/;
    
}