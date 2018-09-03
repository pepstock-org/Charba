package org.pepstock.charba.client.controllers;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Controller;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;

import com.google.gwt.core.client.JavaScriptObject;

public abstract class AbstractController implements Controller {
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.controllers.AbstractController#addElements(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public void addElements(Context jsThis, AbstractChart<?, ?> chart) {
		if (getChartType() != null) {
			invokeAddElements(getChartType().name(), jsThis.getObject());
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.controllers.AbstractController#addElementAndReset(org.pepstock.charba.client.AbstractChart, int)
	 */
	@Override
	public void addElementAndReset(Context jsThis, AbstractChart<?, ?> chart, int index) {
		if (getChartType() != null) {
			invokeAddElementAndReset(getChartType().name(), jsThis.getObject(), index);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.controllers.AbstractController#draw(org.pepstock.charba.client.AbstractChart, double)
	 */
	@Override
	public void draw(Context jsThis, AbstractChart<?, ?> chart, double ease) {
		if (getChartType() != null) {
			invokeDraw(getChartType().name(), jsThis.getObject(), ease);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.controllers.AbstractController#removeHoverStyle(org.pepstock.charba.client.AbstractChart, com.google.gwt.core.client.JavaScriptObject)
	 */
	@Override
	public void removeHoverStyle(Context jsThis, AbstractChart<?, ?> chart, StyleElement element) {
		if (getChartType() != null) {
			invokeRemoveHoverStyle(getChartType().name(), jsThis.getObject(), element.getObject());
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.controllers.AbstractController#setHoverStyle(org.pepstock.charba.client.AbstractChart, com.google.gwt.core.client.JavaScriptObject)
	 */
	@Override
	public void setHoverStyle(Context jsThis, AbstractChart<?, ?> chart, StyleElement element) {
		if (getChartType() != null) {
			invokeSetHoverStyle(getChartType().name(), jsThis.getObject(), element.getObject());
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.controllers.AbstractController#update(org.pepstock.charba.client.AbstractChart, boolean)
	 */
	@Override
	public void update(Context jsThis, AbstractChart<?, ?> chart, boolean reset) {
		if (getChartType() != null) {
			invokeUpdate(getChartType().name(), jsThis.getObject(), reset);
		}
	}
	
	private native void invokeAddElements(String controllerType, GenericJavaScriptObject jsThis)/*-{
		$wnd.Chart.controllers[controllerType].prototype.addElements.call(jsThis);
	}-*/;
	
	private native void invokeAddElementAndReset(String controllerType, GenericJavaScriptObject jsThis, int index)/*-{
		$wnd.Chart.controllers[controllerType].prototype.addElementAndReset.call(jsThis, index);
	}-*/;

	private native void invokeDraw(String controllerType, GenericJavaScriptObject jsThis, double ease)/*-{
		$wnd.Chart.controllers[controllerType].prototype.draw.call(jsThis, ease);
	}-*/;

	private native void invokeRemoveHoverStyle(String controllerType, GenericJavaScriptObject jsThis, JavaScriptObject element)/*-{
		$wnd.Chart.controllers[controllerType].prototype.removeHoverStyle.call(jsThis, element);
	}-*/;

	private native void invokeSetHoverStyle(String controllerType, GenericJavaScriptObject jsThis, JavaScriptObject element)/*-{
		$wnd.Chart.controllers[controllerType].prototype.setHoverStyle.call(jsThis, element);
	}-*/;

	private native void invokeUpdate(String controllerType, GenericJavaScriptObject jsThis, boolean reset)/*-{
		$wnd.Chart.controllers[controllerType].prototype.update.call(jsThis, reset);
	}-*/;

}
