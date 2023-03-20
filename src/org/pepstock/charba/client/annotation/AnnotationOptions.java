/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.annotation;

import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.annotation.enums.DrawTime;
import org.pepstock.charba.client.commons.HasCallbackScope;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.options.IsAnimations;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * This is the {@link AnnotationPlugin#ID} plugin options where to set all configuration items needed to the plugin.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AnnotationOptions extends AbstractPluginOptions implements IsDefaultsAnnotationOptions, HasEventsHandler, HasCallbackScope {

	/**
	 * Default draw time, <b>{@link DrawTime#AFTER_DATASETS_DRAW}</b>.
	 */
	public static final DrawTime DEFAULT_DRAW_TIME = DrawTime.AFTER_DATASETS_DRAW;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		CLIP("clip"),
		DRAW_TIME("drawTime"),
		INTERACTION("interaction"),
		ANIMATIONS("animations"),
		ANNOTATIONS("annotations");

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

	// defaults global options instance
	private final IsDefaultsAnnotationOptions defaultOptions;
	// event callbacks options handler
	private final EventsHandler eventsHandler;
	// annotations object, stored by key
	private final AnnotationMap annotationsMap;
	// interaction object
	private final Interaction interaction;
	// interaction object
	private final Animations animations;

	/**
	 * Creates new {@link AnnotationPlugin#ID} plugin options.
	 */
	public AnnotationOptions() {
		this(null, null);
	}

	/**
	 * Creates new {@link AnnotationPlugin#ID} plugin options, relating to chart instance for default.
	 * 
	 * @param chart chart instance related to the plugin options
	 */
	public AnnotationOptions(IsChart chart) {
		this(IsChart.isConsistent(chart) ? chart.getDefaultChartOptions().getPlugins().getOptions(AnnotationPlugin.ID, AnnotationPlugin.DEFAULTS_FACTORY) : null, null);
	}

	/**
	 * Creates new {@link AnnotationPlugin#ID} plugin options.
	 * 
	 * @param defaultOptions default options stored in the defaults global
	 */
	AnnotationOptions(IsDefaultsAnnotationOptions defaultOptions) {
		// creates an empty native object
		this(defaultOptions, null);
	}

	/**
	 * Creates new {@link AnnotationPlugin#ID} plugin options.
	 * 
	 * @param defaultOptions default options stored in the defaults global
	 * @param nativeObject native object loaded from configuration
	 */
	AnnotationOptions(IsDefaultsAnnotationOptions defaultOptions, NativeObject nativeObject) {
		super(AnnotationPlugin.ID, nativeObject);
		// checks if defaults options are consistent
		if (defaultOptions == null) {
			// reads the default default global options
			this.defaultOptions = loadGlobalsPluginOptions(AnnotationPlugin.DEFAULTS_FACTORY);
		} else {
			// stores default options
			this.defaultOptions = defaultOptions;
		}
		// checks if annotations exists
		if (has(Property.ANNOTATIONS)) {
			// if here, the options has been created from a native object
			// then it must use a NO cached annotations map
			this.annotationsMap = new AnnotationMap(getValue(Property.ANNOTATIONS));
		} else {
			// if here, the options has been created from scratch
			// then it must use a cached annotations map
			this.annotationsMap = new AnnotationCachedMap();
			// stores in the java script object as well
			setValue(Property.ANNOTATIONS, annotationsMap);
		}
		// checks if interaction exists
		if (has(Property.INTERACTION)) {
			// found in the options
			this.interaction = new Interaction(this.defaultOptions.getInteraction(), getValue(Property.INTERACTION));
		} else {
			// not found and add 1 empty
			this.interaction = new Interaction(this.defaultOptions.getInteraction());
			// stores in the java script object as well
			setValue(Property.INTERACTION, interaction);
		}
		// checks if animations exists
		if (has(Property.ANIMATIONS)) {
			// found in the options
			this.animations = new Animations(this, this.defaultOptions.getAnimations(), getValue(Property.ANIMATIONS));
		} else {
			// not found and add 1 empty
			this.animations = new Animations(this, this.defaultOptions.getAnimations());
			// stores in the java script object as well
			setValue(Property.ANIMATIONS, animations);
		}
		// stores incremental ID
		setNewIncrementalId();
		// loads events handler
		this.eventsHandler = new EventsHandler(this, this.defaultOptions, getNativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPluginOptions#applyingDefaults()
	 */
	@Override
	protected void applyingDefaults() {
		AnnotationPlugin.get().mergeDefaults(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasEventsHandler#getEventsHandler()
	 */
	@Override
	public EventsHandler getEventsHandler() {
		return eventsHandler;
	}

	/**
	 * Returns the configuration which events trigger plugin interactions
	 * 
	 * @return the configuration which events trigger plugin interactions
	 */
	@Override
	public Interaction getInteraction() {
		return interaction;
	}

	/**
	 * Returns the configuration to manage the annotation animations
	 * 
	 * @return the configuration to manage the annotation animations
	 */
	@Override
	public IsAnimations getAnimations() {
		return animations;
	}

	/**
	 * Returns the scope of the options, which is the annotation plugin.
	 * 
	 * @return the scope of the options
	 */
	@Override
	public String getScope() {
		return AnnotationPlugin.ID;
	}

	/**
	 * Sets how to clip relative to the chart area.<br>
	 * If <code>false</code> allows overflow, otherwise <code>true</code> clips that many pixels inside the chart area.
	 * 
	 * @param clip If <code>false</code> allows overflow, otherwise <code>true</code> clips that many pixels inside the chart area.
	 */
	public void setClip(boolean clip) {
		setValue(Property.CLIP, clip);
	}

	/**
	 * Returns if clips relative to the chart area.
	 * 
	 * @return <code>true</code> if clips relative to the chart area.
	 */
	@Override
	public boolean isClip() {
		return getValue(Property.CLIP, defaultOptions.isClip());
	}

	/**
	 * Sets the draw time which defines when the annotations are drawn.
	 * 
	 * @param drawTime the draw time which defines when the annotations are drawn
	 */
	public void setDrawTime(DrawTime drawTime) {
		// stores value
		setValue(Property.DRAW_TIME, drawTime);
	}

	/**
	 * Returns the draw time which defines when the annotations are drawn.
	 * 
	 * @return the draw time which defines when the annotations are drawn
	 */
	@Override
	public DrawTime getDrawTime() {
		return getValue(Property.DRAW_TIME, DrawTime.values(), defaultOptions.getDrawTime());
	}

	/**
	 * Returns <code>true</code> if the annotation with the id passed as argument exists.
	 * 
	 * @param id annotation id to check
	 * @return <code>true</code> if the annotation with the id passed as argument exists
	 */
	public boolean hasAnnotation(String id) {
		return hasAnnotation(AnnotationId.create(id));
	}

	/**
	 * Returns <code>true</code> if the annotation with the id passed as argument exists.
	 * 
	 * @param id annotation id to check
	 * @return <code>true</code> if the annotation with the id passed as argument exists
	 */
	@Override
	public boolean hasAnnotation(AnnotationId id) {
		return annotationsMap.hasAnnotation(id) || defaultOptions.hasAnnotation(id);
	}

	/**
	 * Removes the annotation by the id passed as argument, if exists.
	 * 
	 * @param id annotation id to check
	 */
	public void removeAnnotation(String id) {
		removeAnnotation(AnnotationId.create(id));
	}

	/**
	 * Removes the annotation by the id passed as argument, if exists.
	 * 
	 * @param id annotation id to check
	 */
	public void removeAnnotation(AnnotationId id) {
		annotationsMap.removeAnnotation(id);
	}

	/**
	 * Adds an annotations for plugin.
	 * 
	 * @param annotations set of annotations.
	 */
	public void addAnnotations(AbstractAnnotation... annotations) {
		annotationsMap.addAnnotations(annotations);
	}

	/**
	 * Sets a set of annotations for plugin. If argument is <code>null</code>, removes all annotations.
	 * 
	 * @param annotations set of annotations. If <code>null</code>, removes all annotations
	 */
	public void setAnnotations(AbstractAnnotation... annotations) {
		annotationsMap.setAnnotations(annotations);
	}

	/**
	 * Returns the collection of annotations.
	 * 
	 * @return the collection of annotations
	 */
	@Override
	public List<AbstractAnnotation> getAnnotations() {
		return annotationsMap.getAnnotations();
	}

	/**
	 * Returns the annotation with the id passed as argument or <code>null</code> if not exist.
	 * 
	 * @param id annotation id to use to retrieve the annotation
	 * @return the annotation or <code>null</code> if not exist
	 */
	public AbstractAnnotation getAnnotation(String id) {
		return getAnnotation(AnnotationId.create(id));
	}

	/**
	 * Returns the annotation with the id passed as argument or <code>null</code> if not exist.
	 * 
	 * @param id annotation id to check
	 * @return the annotation with the id passed as argument or <code>null</code> if not exist
	 */
	@Override
	public AbstractAnnotation getAnnotation(AnnotationId id) {
		// gets annotation instance
		AbstractAnnotation result = annotationsMap.getAnnotation(id);
		// if annotation is consistent
		if (result == null) {
			// not present in this object
			// searches on the default
			return defaultOptions.getAnnotation(id);

		}
		// if here, the annotation was found
		// in this object then returns it
		return result;
	}
}