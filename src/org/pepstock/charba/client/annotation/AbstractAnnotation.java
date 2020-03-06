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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.annotation.callbacks.ClickCallback;
import org.pepstock.charba.client.annotation.callbacks.ContextMenuCallback;
import org.pepstock.charba.client.annotation.callbacks.DoubleClickCallback;
import org.pepstock.charba.client.annotation.callbacks.MouseDownCallback;
import org.pepstock.charba.client.annotation.callbacks.MouseEnterCallback;
import org.pepstock.charba.client.annotation.callbacks.MouseLeaveCallback;
import org.pepstock.charba.client.annotation.callbacks.MouseMoveCallback;
import org.pepstock.charba.client.annotation.callbacks.MouseOutCallback;
import org.pepstock.charba.client.annotation.callbacks.MouseOverCallback;
import org.pepstock.charba.client.annotation.callbacks.MouseUpCallback;
import org.pepstock.charba.client.annotation.callbacks.WheelCallback;
import org.pepstock.charba.client.annotation.enums.AnnotationType;
import org.pepstock.charba.client.annotation.enums.DrawTime;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;
import org.pepstock.charba.client.utils.Window;

import jsinterop.annotations.JsFunction;

/**
 * Base class to define an annotation into {@link AnnotationPlugin#ID} plugin.<br>
 * It contains all commons properties to define an annotation ({@link BoxAnnotation} or {@link LineAnnotation}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractAnnotation extends AbstractPluginOptions implements IsDefaultsAnnotation {

	/**
	 * Java script FUNCTION callback called to provide events generated by annotations.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyHandlerCallback {

		/**
		 * Method of function to be called to provide events generated by annotations.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function
		 * @param event native event generated by annotation
		 */
		void call(NativeObject context, BaseNativeEvent event);
	}

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		TYPE("type"),
		ID("id"),
		BORDER_COLOR("borderColor"),
		BORDER_WIDTH("borderWidth"),
		ON_MOUSE_ENTER("onMouseenter"),
		ON_MOUSE_OVER("onMouseover"),
		ON_MOUSE_LEAVE("onMouseleave"),
		ON_MOUSE_OUT("onMouseout"),
		ON_MOUSE_MOVE("onMousemove"),
		ON_MOUSE_DOWN("onMousedown"),
		ON_MOUSE_UP("onMouseup"),
		ON_CLICK("onClick"),
		ON_DOUBLE_CLICK("onDblclick"),
		ON_CONTEXT_MENU("onContextmenu"),
		ON_WHEEL("onWheel"),
		// internal property to set name instead of id
		// because the plugin does not reload the options if they have got an id
		// therefore another property must be set to identify better the annotation and
		// the id must be reset every reconfiguration
		CHARBA_NAME("_charbaName");

		// name value of property
		private final String value;
		//

		/**
		 * Creates with the property value to use into native object.
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

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the MOUSEENTER function
	private final CallbackProxy<ProxyHandlerCallback> mouseenterCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the MOUSEOVER function
	private final CallbackProxy<ProxyHandlerCallback> mouseoverCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the MOUSELEAVE function
	private final CallbackProxy<ProxyHandlerCallback> mouseleaveCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the MOUSEOUT function
	private final CallbackProxy<ProxyHandlerCallback> mouseoutCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the MOUSEMOVE function
	private final CallbackProxy<ProxyHandlerCallback> mousemoveCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the MOUSEDOWN function
	private final CallbackProxy<ProxyHandlerCallback> mousedownCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the MOUSEUP function
	private final CallbackProxy<ProxyHandlerCallback> mouseupCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the CLICK function
	private final CallbackProxy<ProxyHandlerCallback> clickCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the DBLCLICK function
	private final CallbackProxy<ProxyHandlerCallback> dblclickCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the CONTEXTMENU function
	private final CallbackProxy<ProxyHandlerCallback> contextmenuCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the WHEEL function
	private final CallbackProxy<ProxyHandlerCallback> wheelCallbackProxy = JsHelper.get().newCallbackProxy();
	// type of annotation
	private final AnnotationType type;
	// callback instance to handle mouseenter event
	private MouseEnterCallback mouseenterCallback = null;
	// callback instance to handle mouseover event
	private MouseOverCallback mouseoverCallback = null;
	// callback instance to handle mouseleave event
	private MouseLeaveCallback mouseleaveCallback = null;
	// callback instance to handle mouseout event
	private MouseOutCallback mouseoutCallback = null;
	// callback instance to handle mousemove event
	private MouseMoveCallback mousemoveCallback = null;
	// callback instance to handle mousedown event
	private MouseDownCallback mousedownCallback = null;
	// callback instance to handle mouseup event
	private MouseUpCallback mouseupCallback = null;
	// callback instance to handle click event
	private ClickCallback clickCallback = null;
	// callback instance to handle dblclick event
	private DoubleClickCallback dblclickCallback = null;
	// callback instance to handle contextmenu event
	private ContextMenuCallback contextmenuCallback = null;
	// callback instance to handle wheel event
	private WheelCallback wheelCallback = null;

	private final DefaultsOptions defaultsOptions;

	/**
	 * Creates the object with the type of annotation to handle.
	 * 
	 * @param type annotation type
	 * @param defaultsOptions default options stored into defaults global
	 */
	AbstractAnnotation(AnnotationType type, DefaultsOptions defaultsOptions) {
		super(AnnotationPlugin.ID);
		// checks if type is consistent
		Key.checkIfValid(type);
		// stores type
		this.type = type;
		setValue(Property.TYPE, type);
		// checks if defaults options are consistent
		if (defaultsOptions == null) {
			// reads the default default global options
			this.defaultsOptions = loadGlobalsPluginOptions(AnnotationPlugin.DEFAULTS_FACTORY);
		} else {
			// stores default options
			this.defaultsOptions = defaultsOptions;
		}
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets proxy handler to callback proxy to invoke the MOUSEENTER function
		mouseenterCallbackProxy.setCallback(this::onMouseenter);
		// sets proxy handler to callback proxy to invoke the MOUSEOVER function
		mouseoverCallbackProxy.setCallback(this::onMouseover);
		// sets proxy handler to callback proxy to invoke the MOUSELEAVE function
		mouseleaveCallbackProxy.setCallback(this::onMouseleave);
		// sets proxy handler to callback proxy to invoke the MOUSEOUT function
		mouseoutCallbackProxy.setCallback(this::onMouseout);
		// sets proxy handler to callback proxy to invoke the MOUSEMOVE function
		mousemoveCallbackProxy.setCallback(this::onMousemove);
		// sets proxy handler to callback proxy to invoke the MOUSEDOWN function
		mousedownCallbackProxy.setCallback(this::onMousedown);
		// sets proxy handler to callback proxy to invoke the MOUSEUP function
		mouseupCallbackProxy.setCallback(this::onMouseup);
		// sets proxy handler to callback proxy to invoke the CLICK function
		clickCallbackProxy.setCallback(this::onClick);
		// sets proxy handler to callback proxy to invoke the DBLCLICK function
		dblclickCallbackProxy.setCallback(this::onDblclick);
		// sets proxy handler to callback proxy to invoke the CONTEXTMENU function
		contextmenuCallbackProxy.setCallback(this::onContextmenu);
		// sets proxy handler to callback proxy to invoke the WHEEL function
		wheelCallbackProxy.setCallback(this::onWheel);
	}

	/**
	 * Removes the ID property form annotation in order that the options can be reloaded by plugin when the chart will be reconfigured, by {@link IsChart#reconfigure()}.
	 */
	void resetAnnotationId() {
		removeIfExists(Property.ID);
	}

	/**
	 * Returns the type of annotation.
	 * 
	 * @return the type of annotation
	 */
	public final AnnotationType getType() {
		return type;
	}

	/**
	 * Sets the name of annotation.
	 * 
	 * @param name the name of annotation
	 */
	public final void setName(String name) {
		setValue(Property.CHARBA_NAME, name);
	}

	/**
	 * Returns the name of annotation.
	 * 
	 * @return the name of annotation
	 */
	public final String getName() {
		return getValue(Property.CHARBA_NAME, UndefinedValues.STRING);
	}

	/**
	 * Sets the draw time which defines when the annotations are drawn.
	 * 
	 * @param drawTime the draw time which defines when the annotations are drawn
	 */
	public final void setDrawTime(DrawTime drawTime) {
		setValue(AnnotationOptions.Property.DRAW_TIME, drawTime);
	}

	/**
	 * Returns the draw time which defines when the annotations are drawn.
	 * 
	 * @return the draw time which defines when the annotations are drawn
	 */
	public final DrawTime getDrawTime() {
		return getValue(AnnotationOptions.Property.DRAW_TIME, DrawTime.values(), defaultsOptions.getDrawTime());
	}

	/**
	 * Sets the color of the border of annotation.
	 * 
	 * @param borderColor the color of the border of annotation
	 */
	public final void setBorderColor(IsColor borderColor) {
		setBorderColor(checkValue(borderColor));
	}

	/**
	 * Sets the color of the border of annotation.
	 * 
	 * @param borderColor the color of the border of annotation
	 */
	public final void setBorderColor(String borderColor) {
		setValue(Property.BORDER_COLOR, borderColor);
	}

	/**
	 * Returns the color of the border of annotation.
	 * 
	 * @return the color of the border of annotation
	 */
	public final IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Sets the width of the border in pixels.
	 * 
	 * @param borderWidth the width of the border in pixels.
	 */
	public final void setBorderWidth(int borderWidth) {
		setValue(Property.BORDER_WIDTH, borderWidth);
	}

	/**
	 * Returns the callback called when a "mouseenter" event is occurring.
	 * 
	 * @return the callback called when a "mouseenter" event is occurring
	 */
	public final MouseEnterCallback getMouseEnterCallback() {
		return mouseenterCallback;
	}

	/**
	 * Sets the callback called when a "mouseenter" event is occurring.
	 * 
	 * @param mouseenterCallback the callback called when a "mouseenter" event is occurring
	 */
	public final void setMouseEnterCallback(MouseEnterCallback mouseenterCallback) {
		// sets mouseenter callback
		this.mouseenterCallback = mouseenterCallback;
		// checks if callback is consistent
		if (mouseenterCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.ON_MOUSE_ENTER, mouseenterCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.ON_MOUSE_ENTER);
		}
	}

	/**
	 * Returns the callback called when a "mouseover" event is occurring.
	 * 
	 * @return the callback called when a "mouseover" event is occurring
	 */
	public final MouseOverCallback getMouseOverCallback() {
		return mouseoverCallback;
	}

	/**
	 * Sets the callback called when a "mouseover" event is occurring.
	 * 
	 * @param mouseoverCallback the callback called when a "mouseover" event is occurring
	 */
	public final void setMouseOverCallback(MouseOverCallback mouseoverCallback) {
		// sets mouseover callback
		this.mouseoverCallback = mouseoverCallback;
		// checks if callback is consistent
		if (mouseoverCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.ON_MOUSE_OVER, mouseoverCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.ON_MOUSE_OVER);
		}
	}

	/**
	 * Returns the callback called when a "mouseleave" event is occurring.
	 * 
	 * @return the callback called when a "mouseleave" event is occurring
	 */
	public final MouseLeaveCallback getMouseLeaveCallback() {
		return mouseleaveCallback;
	}

	/**
	 * Sets the callback called when a "mouseleave" event is occurring.
	 * 
	 * @param mouseleaveCallback the callback called when a "mouseleave" event is occurring
	 */
	public final void setMouseLeaveCallback(MouseLeaveCallback mouseleaveCallback) {
		// sets mouseleave callback
		this.mouseleaveCallback = mouseleaveCallback;
		// checks if callback is consistent
		if (mouseleaveCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.ON_MOUSE_LEAVE, mouseleaveCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.ON_MOUSE_LEAVE);
		}
	}

	/**
	 * Returns the callback called when a "mouseout" event is occurring.
	 * 
	 * @return the callback called when a "mouseout" event is occurring
	 */
	public final MouseOutCallback getMouseOutCallback() {
		return mouseoutCallback;
	}

	/**
	 * Sets the callback called when a "mouseout" event is occurring.
	 * 
	 * @param mouseoutCallback the callback called when a "mouseout" event is occurring
	 */
	public final void setMouseOutCallback(MouseOutCallback mouseoutCallback) {
		// sets mouseout callback
		this.mouseoutCallback = mouseoutCallback;
		// checks if callback is consistent
		if (mouseoutCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.ON_MOUSE_OUT, mouseoutCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.ON_MOUSE_OUT);
		}
	}

	/**
	 * Returns the callback called when a "mousemove" event is occurring.
	 * 
	 * @return the callback called when a "mousemove" event is occurring
	 */
	public final MouseMoveCallback getMouseMoveCallback() {
		return mousemoveCallback;
	}

	/**
	 * Sets the callback called when a "mousemove" event is occurring.
	 * 
	 * @param mousemoveCallback the callback called when a "mousemove" event is occurring
	 */
	public final void setMouseMoveCallback(MouseMoveCallback mousemoveCallback) {
		// sets mousemove callback
		this.mousemoveCallback = mousemoveCallback;
		// checks if callback is consistent
		if (mousemoveCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.ON_MOUSE_MOVE, mousemoveCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.ON_MOUSE_MOVE);
		}
	}

	/**
	 * Returns the callback called when a "mousedown" event is occurring.
	 * 
	 * @return the callback called when a "mousedown" event is occurring
	 */
	public final MouseDownCallback getMouseDownCallback() {
		return mousedownCallback;
	}

	/**
	 * Sets the callback called when a "mousedown" event is occurring.
	 * 
	 * @param mousedownCallback the callback called when a "mousedown" event is occurring
	 */
	public final void setMouseDownCallback(MouseDownCallback mousedownCallback) {
		// sets mousedown callback
		this.mousedownCallback = mousedownCallback;
		// checks if callback is consistent
		if (mousedownCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.ON_MOUSE_DOWN, mousedownCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.ON_MOUSE_DOWN);
		}
	}

	/**
	 * Returns the callback called when a "mouseup" event is occurring.
	 * 
	 * @return the callback called when a "mouseup" event is occurring
	 */
	public final MouseUpCallback getMouseUpCallback() {
		return mouseupCallback;
	}

	/**
	 * Sets the callback called when a "mouseup" event is occurring.
	 * 
	 * @param mouseupCallback the callback called when a "mouseup" event is occurring
	 */
	public final void setMouseUpCallback(MouseUpCallback mouseupCallback) {
		// sets mouseup callback
		this.mouseupCallback = mouseupCallback;
		// checks if callback is consistent
		if (mouseupCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.ON_MOUSE_UP, mouseupCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.ON_MOUSE_UP);
		}
	}

	/**
	 * Returns the callback called when a "click" event is occurring.
	 * 
	 * @return the callback called when a "click" event is occurring
	 */
	public final ClickCallback getClickCallback() {
		return clickCallback;
	}

	/**
	 * Sets the callback called when a "click" event is occurring.
	 * 
	 * @param clickCallback the callback called when a "click" event is occurring
	 */
	public final void setClickCallback(ClickCallback clickCallback) {
		// sets click callback
		this.clickCallback = clickCallback;
		// checks if callback is consistent
		if (clickCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.ON_CLICK, clickCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.ON_CLICK);
		}
	}

	/**
	 * Returns the callback called when a "dblclick" event is occurring.
	 * 
	 * @return the callback called when a "dblclick" event is occurring
	 */
	public final DoubleClickCallback getDoubleClickCallback() {
		return dblclickCallback;
	}

	/**
	 * Sets the callback called when a "dblclick" event is occurring.
	 * 
	 * @param dblclickCallback the callback called when a "dblclick" event is occurring
	 */
	public final void setDoubleClickCallback(DoubleClickCallback dblclickCallback) {
		// sets dblclick callback
		this.dblclickCallback = dblclickCallback;
		// checks if callback is consistent
		if (dblclickCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.ON_DOUBLE_CLICK, dblclickCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.ON_DOUBLE_CLICK);
		}
	}

	/**
	 * Returns the callback called when a "contextmenu" event is occurring.
	 * 
	 * @return the callback called when a "contextmenu" event is occurring
	 */
	public final ContextMenuCallback getContextMenuCallback() {
		return contextmenuCallback;
	}

	/**
	 * Sets the callback called when a "contextmenu" event is occurring.
	 * 
	 * @param contextmenuCallback the callback called when a "contextmenu" event is occurring
	 */
	public final void setContextMenuCallback(ContextMenuCallback contextmenuCallback) {
		// sets contextmenu callback
		this.contextmenuCallback = contextmenuCallback;
		// checks if callback is consistent
		if (contextmenuCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.ON_CONTEXT_MENU, contextmenuCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.ON_CONTEXT_MENU);
		}
	}

	/**
	 * Returns the callback called when a "wheel" event is occurring.
	 * 
	 * @return the callback called when a "wheel" event is occurring
	 */
	public final WheelCallback getWheelCallback() {
		return wheelCallback;
	}

	/**
	 * Sets the callback called when a "wheel" event is occurring.
	 * 
	 * @param wheelCallback the callback called when a "wheel" event is occurring
	 */
	public final void setWheelCallback(WheelCallback wheelCallback) {
		// sets wheel callback
		this.wheelCallback = wheelCallback;
		// checks if callback is consistent
		if (wheelCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.ON_WHEEL, wheelCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.ON_WHEEL);
		}
	}

	/**
	 * Manages the MOUSEENTER event invoking the callback is exists.
	 * 
	 * @param NativeObject context value of <code>this</code> to the execution context of function
	 * @param event native event generated by annotation
	 */
	private void onMouseenter(NativeObject context, BaseNativeEvent event) {
		// creates a context wrapper
		Context internalContext = new Context(context);
		// gets chart instance from function context
		IsChart chart = internalContext.getChart();
		// checks if chart, event and callback are consistent
		if (IsChart.isValid(chart) && event != null && mouseenterCallback != null) {
			// invokes callback
			mouseenterCallback.onMouseEnter(chart, event, this);
		}
	}

	/**
	 * Manages the MOUSEOVER event firing an annotation event.
	 * 
	 * @param NativeObject context value of <code>this</code> to the execution context of function
	 * @param event native event generated by annotation
	 */
	private void onMouseover(NativeObject context, BaseNativeEvent event) {
		// creates a context wrapper
		Context internalContext = new Context(context);
		// gets chart instance from function context
		IsChart chart = internalContext.getChart();
		// checks if chart is consistent
		if (IsChart.isValid(chart) && event != null && mouseoverCallback != null) {
			// invokes callback
			mouseoverCallback.onMouseOver(chart, event, this);
		}
	}

	/**
	 * Manages the MOUSELEAVE event firing an annotation event.
	 * 
	 * @param NativeObject context value of <code>this</code> to the execution context of function
	 * @param event native event generated by annotation
	 */
	private void onMouseleave(NativeObject context, BaseNativeEvent event) {
		// creates a context wrapper
		Context internalContext = new Context(context);
		// gets chart instance from function context
		IsChart chart = internalContext.getChart();
		// checks if chart is consistent
		if (IsChart.isValid(chart) && event != null && mouseleaveCallback != null) {
			// invokes callback
			mouseleaveCallback.onMouseLeave(chart, event, this);
		}
	}

	/**
	 * Manages the MOUSEOUT event firing an annotation event.
	 * 
	 * @param NativeObject context value of <code>this</code> to the execution context of function
	 * @param event native event generated by annotation
	 */
	private void onMouseout(NativeObject context, BaseNativeEvent event) {
		// creates a context wrapper
		Context internalContext = new Context(context);
		// gets chart instance from function context
		IsChart chart = internalContext.getChart();
		// checks if chart is consistent
		if (IsChart.isValid(chart) && event != null && mouseoutCallback != null) {
			// invokes callback
			mouseoutCallback.onMouseOut(chart, event, this);
		}
	}

	/**
	 * Manages the MOUSEMOVE event firing an annotation event.
	 * 
	 * @param NativeObject context value of <code>this</code> to the execution context of function
	 * @param event native event generated by annotation
	 */
	private void onMousemove(NativeObject context, BaseNativeEvent event) {
		// creates a context wrapper
		Context internalContext = new Context(context);
		// gets chart instance from function context
		IsChart chart = internalContext.getChart();
		// checks if chart is consistent
		if (IsChart.isValid(chart) && event != null && mousemoveCallback != null) {
			// invokes callback
			mousemoveCallback.onMouseMove(chart, event, this);
		}
	}

	/**
	 * Manages the MOUSEDOWN event firing an annotation event.
	 * 
	 * @param NativeObject context value of <code>this</code> to the execution context of function
	 * @param event native event generated by annotation
	 */
	private void onMousedown(NativeObject context, BaseNativeEvent event) {
		// creates a context wrapper
		Context internalContext = new Context(context);
		// gets chart instance from function context
		IsChart chart = internalContext.getChart();
		// checks if chart is consistent
		if (IsChart.isValid(chart) && event != null && mousedownCallback != null) {
			// invokes callback
			mousedownCallback.onMouseDown(chart, event, this);
		}
	}

	/**
	 * Manages the MOUSEUP event firing an annotation event.
	 * 
	 * @param NativeObject context value of <code>this</code> to the execution context of function
	 * @param event native event generated by annotation
	 */
	private void onMouseup(NativeObject context, BaseNativeEvent event) {
		// creates a context wrapper
		Context internalContext = new Context(context);
		// gets chart instance from function context
		IsChart chart = internalContext.getChart();
		// checks if chart is consistent
		if (IsChart.isValid(chart) && event != null && mouseupCallback != null) {
			// invokes callback
			mouseupCallback.onMouseUp(chart, event, this);
		}
	}

	/**
	 * Manages the CLICK event firing an annotation event.
	 * 
	 * @param NativeObject context value of <code>this</code> to the execution context of function
	 * @param event native event generated by annotation
	 */
	private void onClick(NativeObject context, BaseNativeEvent event) {
		// FIXME
		Window.getConsole().log(context);
		Window.getConsole().log(event);

		// creates a context wrapper
		Context internalContext = new Context(context);
		// gets chart instance from function context
		IsChart chart = internalContext.getChart();
		// checks if chart is consistent
		if (IsChart.isValid(chart) && event != null && clickCallback != null) {
			// invokes callback
			clickCallback.onClick(chart, event, this);
		}
	}

	/**
	 * Manages the DBLCLICK event firing an annotation event.
	 * 
	 * @param NativeObject context value of <code>this</code> to the execution context of function
	 * @param event native event generated by annotation
	 */
	private void onDblclick(NativeObject context, BaseNativeEvent event) {
		// creates a context wrapper
		Context internalContext = new Context(context);
		// gets chart instance from function context
		IsChart chart = internalContext.getChart();
		// checks if chart is consistent
		if (IsChart.isValid(chart) && event != null && dblclickCallback != null) {
			// invokes callback
			dblclickCallback.onDoubleClick(chart, event, this);
		}
	}

	/**
	 * Manages the CONTEXTMENU event firing an annotation event.
	 * 
	 * @param NativeObject context value of <code>this</code> to the execution context of function
	 * @param event native event generated by annotation
	 */
	private void onContextmenu(NativeObject context, BaseNativeEvent event) {
		// creates a context wrapper
		Context internalContext = new Context(context);
		// gets chart instance from function context
		IsChart chart = internalContext.getChart();
		// checks if chart is consistent
		if (IsChart.isValid(chart) && event != null && contextmenuCallback != null) {
			// invokes callback
			contextmenuCallback.onContextMenu(chart, event, this);
		}
	}

	/**
	 * Manages the WHEEL event firing an annotation event.
	 * 
	 * @param NativeObject context value of <code>this</code> to the execution context of function
	 * @param event native event generated by annotation
	 */
	private void onWheel(NativeObject context, BaseNativeEvent event) {
		// creates a context wrapper
		Context internalContext = new Context(context);
		// gets chart instance from function context
		IsChart chart = internalContext.getChart();
		// checks if chart is consistent
		if (IsChart.isValid(chart) && event != null && wheelCallback != null) {
			// invokes callback
			wheelCallback.onWheel(chart, event, this);
		}
	}

}
