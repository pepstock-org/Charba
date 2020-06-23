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

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.enums.InterpolatorType;

/**
 * FIXME It animates charts out of the box. A number of options are provided to configure how the animation looks and how long it takes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Animation extends AbstractModel<Options, IsDefaultAnimation> implements IsDefaultAnimation {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ANIMATE_ROTATE("animateRotate"),
		ANIMATE_SCALE("animateScale"),
		DURATION("duration"),
		EASING("easing"),
		DEBUG("debug"),
		DELAY("delay"),
		LOOP("loop"),
		TYPE("type"),
		FROM("from"),
		ACTIVE("active"),
		RESIZE("resize");

		// name value of property
		private final String value;

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

	private final AnimationActive active;

	private final AnimationResize resize;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Animation(Options options, Key childKey, IsDefaultAnimation defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
		this.active = new AnimationActive(this, Property.ACTIVE, defaultValues.getActive(), getValue(Property.ACTIVE));
		this.resize = new AnimationResize(this, Property.RESIZE, defaultValues.getResize(), getValue(Property.RESIZE));
	}

	/**
	 * Returns the animation element to get the duration in milliseconds it takes to animate hover style changes.
	 * 
	 * @return the animation element to get the duration in milliseconds it takes to animate hover style changes
	 */
	@Override
	public AnimationActive getActive() {
		return active;
	}

	/**
	 * Returns the animation element to get the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @return the animation element to get the duration in milliseconds it takes to animate to new size after a resize event
	 */
	@Override
	public AnimationResize getResize() {
		return resize;
	}

	/**
	 * Sets the animation easing.
	 * 
	 * @param easing animation easing.
	 */
	public void setEasing(Easing easing) {
		setValue(Property.EASING, easing);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the animation easing.
	 * 
	 * @return animation easing.
	 */
	@Override
	public Easing getEasing() {
		return getValue(Property.EASING, Easing.values(), getDefaultValues().getEasing());
	}

	/**
	 * Sets the number of milliseconds an animation takes.
	 * 
	 * @param milliseconds the number of milliseconds an animation takes.
	 */
	public void setDuration(int milliseconds) {
		setValue(Property.DURATION, milliseconds);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the number of milliseconds an animation takes.
	 * 
	 * @return the number of milliseconds an animation takes.
	 */
	@Override
	public int getDuration() {
		return getValue(Property.DURATION, getDefaultValues().getDuration());
	}

	/**
	 * If <code>true</code>, the chart will animate in with a rotation animation.
	 * 
	 * @param animateRotate If <code>true</code>, the chart will animate in with a rotation animation.
	 */
	public void setAnimateRotate(boolean animateRotate) {
		setValue(Property.ANIMATE_ROTATE, animateRotate);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, the chart will animate in with a rotation animation.
	 * 
	 * @return If <code>true</code>, the chart will animate in with a rotation animation.
	 */
	@Override
	public boolean isAnimateRotate() {
		return getValue(Property.ANIMATE_ROTATE, getDefaultValues().isAnimateRotate());
	}

	/**
	 * If <code>true</code>, will animate scaling the chart from the center outwards.
	 * 
	 * @param animateScale If <code>true</code>, will animate scaling the chart from the center outwards.
	 */
	public void setAnimateScale(boolean animateScale) {
		setValue(Property.ANIMATE_SCALE, animateScale);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, will animate scaling the chart from the center outwards.
	 * 
	 * @return If <code>true</code>, will animate scaling the chart from the center outwards.
	 */
	@Override
	public boolean isAnimateScale() {
		return getValue(Property.ANIMATE_SCALE, getDefaultValues().isAnimateScale());
	}

	/**
	 * Sets <code>true</code> if running animation count plus FPS display in upper left corner of the chart.
	 * 
	 * @param debug <code>true</code> if running animation count plus FPS display in upper left corner of the chart
	 */
	public void setDebug(boolean debug) {
		setValue(Property.DEBUG, debug);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns <code>true</code> if running animation count plus FPS display in upper left corner of the chart.
	 * 
	 * @return <code>true</code> if running animation count plus FPS display in upper left corner of the chart
	 */
	@Override
	public boolean isDebug() {
		return getValue(Property.DEBUG, getDefaultValues().isDebug());
	}

	/**
	 * Sets the delay before starting the animations.
	 * 
	 * @param delay the delay before starting the animations
	 */
	public void setDelay(int delay) {
		setValue(Property.DELAY, delay);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the delay before starting the animations.
	 * 
	 * @return the delay before starting the animations
	 */
	@Override
	public int getDelay() {
		return getValue(Property.DELAY, getDefaultValues().getDelay());
	}

	/**
	 * If set to <code>true</code>, loops the animations endlessly.
	 * 
	 * @param loop <code>true</code> if loops the animations endlessly.
	 */
	public void setLoop(boolean loop) {
		setValue(Property.LOOP, loop);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * If set to <code>true</code>, loops the animations endlessly.
	 * 
	 * @return <code>true</code> if loops the animations endlessly.
	 */
	@Override
	public boolean isLoop() {
		return getValue(Property.LOOP, getDefaultValues().isLoop());
	}

	/**
	 * Sets the type of <code>from</code> property and determines the interpolator used.
	 * 
	 * @param type the type of <code>from</code> property and determines the interpolator used.
	 */
	public void setType(InterpolatorType type) {
		// checks if type is consistent and not unknown
		if (type != null && !InterpolatorType.UNKNOWN.equals(type)) {
			// if consistent, stores type
			setValue(Property.TYPE, type);
			// checks if the node is already added to parent
			checkAndAddToParent();
			// checks if the current from property is consistent
			// gets type of property
			ObjectType propertyType = type(Property.FROM);
			// checks if the value is stored as the type
			if (InterpolatorType.NUMBER.equals(type) && !ObjectType.NUMBER.equals(propertyType)) {
				// if here, the new type is not aligned with from
				// then removes from
				removeIfExists(Property.FROM);
			} else if (InterpolatorType.COLOR.equals(type) && !ObjectType.STRING.equals(propertyType)) {
				// if here, the new type is not aligned with from
				// then removes from
				removeIfExists(Property.FROM);
			}
		} else {
			// removes the key because the type is not consistent
			removeIfExists(Property.TYPE);
			// then removes from
			removeIfExists(Property.FROM);
		}
	}

	/**
	 * Returns the type of <code>from</code> property and determines the interpolator used.
	 * 
	 * @return the type of <code>from</code> property and determines the interpolator used.
	 */
	@Override
	public InterpolatorType getType() {
		return getValue(Property.TYPE, InterpolatorType.values(), getDefaultValues().getType());
	}

	/**
	 * Sets the start value for the animation as number.
	 * 
	 * @param from the start value for the animation as number.
	 */
	public void setFrom(double from) {
		setValue(Property.FROM, from);
		// override the type
		setValue(Property.TYPE, InterpolatorType.NUMBER);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets the start value for the animation as color string.
	 * 
	 * @param from the start value for the animation as color string.
	 */
	public void setFrom(String from) {
		setValue(Property.FROM, from);
		// override the type
		setValue(Property.TYPE, InterpolatorType.COLOR);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets the start value for the animation as color.
	 * 
	 * @param from the start value for the animation as color.
	 */
	public void setFrom(IsColor from) {
		setFrom(IsColor.checkAndGetValue(from));
	}

	/**
	 * Returns the start value for the animation as number.
	 * 
	 * @return the start value for the animation as number.
	 */
	@Override
	public double getFrom() {
		// gets the type options
		InterpolatorType storedType = getType();
		// gets type of property
		ObjectType propertyType = type(Property.FROM);
		// checks if the value is stored as the type
		if (InterpolatorType.NUMBER.equals(storedType) && ObjectType.NUMBER.equals(propertyType)) {
			return getValue(Property.FROM, getDefaultValues().getFrom());
		}
		// if here, the type is not consistent
		// then returns the default value
		return getDefaultValues().getFrom();
	}

	/**
	 * Returns the start value for the animation as color string.
	 * 
	 * @return the start value for the animation as color string.
	 */
	@Override
	public String getFromAsString() {
		// gets the type options
		InterpolatorType storedType = getType();
		// gets type of property
		ObjectType propertyType = type(Property.FROM);
		// checks if the value is stored as the type
		if (InterpolatorType.COLOR.equals(storedType) && ObjectType.STRING.equals(propertyType)) {
			return getValue(Property.FROM, getDefaultValues().getFromAsString());
		}
		// if here, the type is not consistent
		// then returns the default value
		return getDefaultValues().getFromAsString();
	}

	/**
	 * Returns the start value for the animation as color.
	 * 
	 * @return the start value for the animation as color.
	 */
	public IsColor getFromAsColor() {
		// gets value as string
		String fromAsString = getFromAsString();
		// checks if consistent
		if (fromAsString != null) {
			// creates and returns the color
			return ColorBuilder.parse(fromAsString);
		}
		// if here
		// no from as string
		// then returns null
		return null;
	}

}