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
package org.pepstock.charba.client.gwt.widgets;

import java.util.List;

import org.pepstock.charba.client.ChartNode;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.UpdateConfiguration;
import org.pepstock.charba.client.data.Data;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Div;
import org.pepstock.charba.client.dom.enums.CursorType;
import org.pepstock.charba.client.events.Event;
import org.pepstock.charba.client.events.EventHandler;
import org.pepstock.charba.client.events.EventType;
import org.pepstock.charba.client.events.HandlerRegistration;
import org.pepstock.charba.client.items.DatasetMetaItem;
import org.pepstock.charba.client.items.DatasetReferenceItem;
import org.pepstock.charba.client.plugins.Plugins;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Base class of all GWT WIDGET charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <C> type for the specific chart
 */
public abstract class AbstractChartWidget<C extends IsChart> extends SimplePanel implements IsChart {

	// chart instance
	private final C chart;

	/**
	 * Creates a chart widget for GWT by chart element instance.
	 * 
	 * @param chart chart instance to wrap by the widget
	 */
	protected AbstractChartWidget(C chart) {
		// creates the simple panel casting the div element of the chart
		// into a GWT element
		super((Element) IsChart.checkAndGetIfConsistent(chart).getChartElement().as());
		// stores the chart
		this.chart = chart;
	}

	/**
	 * Returns the chart instance, wrapped by this GWT widget.
	 * 
	 * @return the chart instance, wrapped by this GWT widget
	 */
	public final C getChart() {
		return chart;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.HasHandlers#fireEvent(org.pepstock.charba.client.events.Event)
	 */
	@Override
	public final void fireEvent(Event event) {
		chart.fireEvent(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#addHandler(org.pepstock.charba.client.events.EventHandler, org.pepstock.charba.client.events.EventType)
	 */
	@Override
	public final HandlerRegistration addHandler(EventHandler handler, EventType type) {
		return chart.addHandler(handler, type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getChartElement()
	 */
	@Override
	public final Div getChartElement() {
		return chart.getChartElement();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getType()
	 */
	@Override
	public final Type getType() {
		return chart.getType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getBaseType()
	 */
	@Override
	public final Type getBaseType() {
		return chart.getBaseType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getId()
	 */
	@Override
	public final String getId() {
		return chart.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getCanvas()
	 */
	@Override
	public final Canvas getCanvas() {
		return chart.getCanvas();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#removeCanvasPreventDefault()
	 */
	@Override
	public final void removeCanvasPreventDefault() {
		chart.removeCanvasPreventDefault();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getInitialCursor()
	 */
	@Override
	public final CursorType getInitialCursor() {
		return chart.getInitialCursor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#isInitialized()
	 */
	@Override
	public final boolean isInitialized() {
		return chart.isInitialized();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getNode()
	 */
	@Override
	public final ChartNode getNode() {
		return chart.getNode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getData()
	 */
	@Override
	public final Data getData() {
		return chart.getData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getPlugins()
	 */
	@Override
	public final Plugins getPlugins() {
		return chart.getPlugins();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getDefaultChartOptions()
	 */
	@Override
	public final IsDefaultScaledOptions getDefaultChartOptions() {
		return chart.getDefaultChartOptions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getWholeOptions()
	 */
	@Override
	public final IsDefaultScaledOptions getWholeOptions() {
		return chart.getWholeOptions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#isDrawOnAttach()
	 */
	@Override
	public final boolean isDrawOnAttach() {
		return chart.isDrawOnAttach();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#setDrawOnAttach(boolean)
	 */
	@Override
	public final void setDrawOnAttach(boolean drawOnAttach) {
		chart.setDrawOnAttach(drawOnAttach);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#isDestroyOnDetach()
	 */
	@Override
	public final boolean isDestroyOnDetach() {
		return chart.isDestroyOnDetach();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#setDestroyOnDetach(boolean)
	 */
	@Override
	public final void setDestroyOnDetach(boolean destroyOnDetach) {
		chart.setDestroyOnDetach(destroyOnDetach);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#destroy()
	 */
	@Override
	public final void destroy() {
		chart.destroy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#stop()
	 */
	@Override
	public final void stop() {
		chart.destroy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#reset()
	 */
	@Override
	public final void reset() {
		chart.reset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#toBase64Image()
	 */
	@Override
	public final String toBase64Image() {
		return chart.toBase64Image();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#resize()
	 */
	@Override
	public final void resize() {
		chart.resize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#update()
	 */
	@Override
	public final void update() {
		chart.update();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#update(org.pepstock.charba.client.UpdateConfiguration)
	 */
	@Override
	public final void update(UpdateConfiguration config) {
		chart.update(config);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#reconfigure()
	 */
	@Override
	public final void reconfigure() {
		chart.reconfigure();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#reconfigure(org.pepstock.charba.client.UpdateConfiguration)
	 */
	@Override
	public final void reconfigure(UpdateConfiguration config) {
		chart.reconfigure(config);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#render()
	 */
	@Override
	public final void render() {
		chart.render();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#render(org.pepstock.charba.client.UpdateConfiguration)
	 */
	@Override
	public final void render(UpdateConfiguration config) {
		chart.render(config);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getDatasetMeta(int)
	 */
	@Override
	public final DatasetMetaItem getDatasetMeta(int index) {
		return chart.getDatasetMeta(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getDatasetAtEvent(org.pepstock.charba.client.dom.BaseNativeEvent)
	 */
	@Override
	public final List<DatasetReferenceItem> getDatasetAtEvent(BaseNativeEvent event) {
		return chart.getDatasetAtEvent(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#isDatasetVisible(int)
	 */
	@Override
	public final boolean isDatasetVisible(int index) {
		return chart.isDatasetVisible(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getVisibleDatasetCount()
	 */
	@Override
	public final int getVisibleDatasetCount() {
		return chart.getVisibleDatasetCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#setDatasetVisibility(int, boolean)
	 */
	@Override
	public void setDatasetVisibility(int datasetIndex, boolean visibility) {
		chart.setDatasetVisibility(datasetIndex, visibility);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#toggleDataVisibility(int)
	 */
	@Override
	public void toggleDataVisibility(int index) {
		chart.toggleDataVisibility(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#isDataVisible(int)
	 */
	@Override
	public boolean isDataVisible(int index) {
		return chart.isDataVisible(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#hide(int)
	 */
	@Override
	public void hide(int datasetIndex) {
		chart.hide(datasetIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#show(int)
	 */
	@Override
	public void show(int datasetIndex) {
		chart.show(datasetIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getElementAtEvent(org.pepstock.charba.client.dom.BaseNativeEvent)
	 */
	@Override
	public final DatasetReferenceItem getElementAtEvent(BaseNativeEvent event) {
		return chart.getElementAtEvent(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getElementsAtEvent(org.pepstock.charba.client.dom.BaseNativeEvent)
	 */
	@Override
	public final List<DatasetReferenceItem> getElementsAtEvent(BaseNativeEvent event) {
		return chart.getElementsAtEvent(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#draw()
	 */
	@Override
	public final void draw() {
		chart.draw();
	}

}
