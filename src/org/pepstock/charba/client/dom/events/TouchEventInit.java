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
package org.pepstock.charba.client.dom.events;

import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayTouch;
import org.pepstock.charba.client.commons.Key;

/**
 * Initialization object for a {@link NativeTouchEvent}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TouchEventInit extends UIEventInit implements IsModifiersHandler {

	// default instance
	static final TouchEventInit DEFAULT_INSTANCE = new TouchEventInit();

	// instance of modifier handler
	private final ModifiersHandler handler;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CHANGED_TOUCHES("changedTouches"),
		TARGET_TOUCHES("targetTouches "),
		TOUCHES("touches");

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

	/**
	 * Creates an empty object
	 */
	public TouchEventInit() {
		super();
		// stores handler
		this.handler = new ModifiersHandler(getNativeObject());
	}

	/**
	 * Creates an object with an array of objects for every point of contact currently touching the surface.
	 * 
	 * @param touches an array of objects for every point of contact currently touching the surface
	 */
	public TouchEventInit(Touch... touches) {
		this();
		// stores touches
		setTouches(touches);
	}

	/**
	 * Creates an object with an array of objects for every point of contact currently touching the surface.
	 * 
	 * @param touches an array of objects for every point of contact currently touching the surface
	 */
	public TouchEventInit(List<Touch> touches) {
		this();
		// stores touches
		setTouches(touches);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.dom.events.IsModifiersHandler#getModifiersHandler()
	 */
	@Override
	public ModifiersHandler getModifiersHandler() {
		return handler;
	}

	/**
	 * Sets an array of objects for every point of contact which contributed to the event.
	 * 
	 * @param touches an array of objects for every point of contact which contributed to the event
	 */
	public void setChangedTouches(Touch... touches) {
		setArrayValue(Property.CHANGED_TOUCHES, ArrayTouch.fromOrEmpty(touches));
	}

	/**
	 * Sets a list of objects for every point of contact which contributed to the event.
	 * 
	 * @param touches a list of objects for every point of contact which contributed to the event
	 */
	public void setChangedTouches(List<Touch> touches) {
		setArrayValue(Property.CHANGED_TOUCHES, ArrayTouch.fromOrEmpty(touches));
	}

	/**
	 * Returns a list of objects for every point of contact which contributed to the event.
	 * 
	 * @return a list of objects for every point of contact which contributed to the event.
	 */
	public List<Touch> getChangedTouches() {
		// gets array
		ArrayTouch array = getArrayValue(Property.CHANGED_TOUCHES);
		// returns as list
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets an array of objects for every point of contact that is touching the surface and started on the element that is the target of the current event.
	 * 
	 * @param touches an array of objects for every point of contact that is touching the surface and started on the element that is the target of the current event
	 */
	public void setTargetTouches(Touch... touches) {
		setArrayValue(Property.TARGET_TOUCHES, ArrayTouch.fromOrEmpty(touches));
	}

	/**
	 * Sets a list of objects for every point of contact that is touching the surface and started on the element that is the target of the current event
	 * 
	 * @param touches a list of objects for every point of contact that is touching the surface and started on the element that is the target of the current event
	 */
	public void setTargetTouches(List<Touch> touches) {
		setArrayValue(Property.TARGET_TOUCHES, ArrayTouch.fromOrEmpty(touches));
	}

	/**
	 * Returns a list of objects for every point of contact that is touching the surface and started on the element that is the target of the current event.
	 * 
	 * @return a list of objects for every point of contact that is touching the surface and started on the element that is the target of the current event
	 */
	public List<Touch> getTargetTouches() {
		// gets array
		ArrayTouch array = getArrayValue(Property.TARGET_TOUCHES);
		// returns as list
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets an array of objects for every point of contact currently touching the surface.
	 * 
	 * @param touches an array of objects for every point of contact currently touching the surface
	 */
	public void setTouches(Touch... touches) {
		setArrayValue(Property.TOUCHES, ArrayTouch.fromOrEmpty(touches));
	}

	/**
	 * Sets an array of objects for every point of contact currently touching the surface.
	 * 
	 * @param touches an array of objects for every point of contact currently touching the surface
	 */
	public void setTouches(List<Touch> touches) {
		setArrayValue(Property.TOUCHES, ArrayTouch.fromOrEmpty(touches));
	}

	/**
	 * Returns a list of objects for every point of contact currently touching the surface.
	 * 
	 * @return a list of objects for every point of contact currently touching the surface.
	 */
	public List<Touch> getTouches() {
		// gets array
		ArrayTouch array = getArrayValue(Property.TOUCHES);
		// returns as list
		return ArrayListHelper.list(array);
	}

}