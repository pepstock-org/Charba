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

import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.annotation.enums.DrawTime;
import org.pepstock.charba.client.commons.ArrayObjectContainerList;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.plugins.AbstractPluginCachedOptions;

/**
 * This is the {@link Annotation#ID} plugin options where to set all configuration items needed to the plugin.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AnnotationOptions extends AbstractPluginCachedOptions {

	/**
	 * Default draw time, <b>{@link DrawTime#AFTER_DATASETS_DRAW}</b>.
	 */
	public static final DrawTime DEFAULT_DRAW_TIME = DrawTime.AFTER_DATASETS_DRAW;

	// maintains the list of annotations because needs to preserve the annotation type
	private final ArrayObjectContainerList<AbstractAnnotation> currentAnnotations = new ArrayObjectContainerList<>();

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		DRAW_TIME("drawTime"),
		ANNOTATIONS("annotations");

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

	// defaults global options instance
	private DefaultsOptions defaultsOptions;

	/**
	 * Creates new {@link Annotation#ID} plugin options.
	 */
	public AnnotationOptions() {
		this((DefaultsOptions) null);
	}

	/**
	 * Creates new {@link Annotation#ID} plugin options, relating to chart instance for default.
	 * 
	 * @param chart chart instance related to the plugin options
	 */
	public AnnotationOptions(IsChart chart) {
		this(IsChart.isConsistent(chart) ? chart.getDefaultChartOptions().getPlugins().getOptions(Annotation.ID, Annotation.DEFAULTS_FACTORY) : null);
	}

	/**
	 * Creates new {@link Annotation#ID} plugin options.
	 * 
	 * @param defaultsOptions default options stored into defaults global
	 */
	AnnotationOptions(DefaultsOptions defaultsOptions) {
		// creates an empty native object
		super(Annotation.ID, Annotation.FACTORY, false);
		// checks if defaults options are consistent
		if (defaultsOptions == null) {
			// reads the default default global options
			this.defaultsOptions = loadGlobalsPluginOptions(Annotation.DEFAULTS_FACTORY);
		} else {
			// stores default options
			this.defaultsOptions = defaultsOptions;
		}
	}

	/**
	 * Sets the draw time which defines when the annotations are drawn.
	 * 
	 * @param drawTime the draw time which defines when the annotations are drawn
	 */
	public void setDrawTime(DrawTime drawTime) {
		setValue(Property.DRAW_TIME, drawTime);
	}

	/**
	 * Returns <code>true</code> if the draw time has previously set.
	 * 
	 * @return <code>true</code> if the draw time has previously set
	 */
	boolean hasDrawTime() {
		return has(Property.DRAW_TIME);
	}

	/**
	 * Returns the draw time which defines when the annotations are drawn.
	 * 
	 * @return the draw time which defines when the annotations are drawn
	 */
	public DrawTime getDrawTime() {
		return getValue(Property.DRAW_TIME, DrawTime.values(), defaultsOptions.getDrawTime());
	}

	/**
	 * Sets a set of annotations for plugin. If argument is <code>null</code>, removes all annotations.
	 * 
	 * @param annotations set of annotations. If <code>null</code>, removes all annotations
	 */
	public void setAnnotations(AbstractAnnotation... annotations) {
		// clear buffer
		this.currentAnnotations.clear();
		// checks if arguments is consistent
		if (annotations != null) {
			// adds all annotations
			this.currentAnnotations.addAll(annotations);
			// sets annotations to native object
			setArrayValue(Property.ANNOTATIONS, this.currentAnnotations);
		} else {
			// removes the existing property
			removeIfExists(Property.ANNOTATIONS);
		}
	}

	/**
	 * Returns the list of annotations.
	 * 
	 * @return the list of annotations
	 */
	public List<AbstractAnnotation> getAnnotations() {
		return getAnnotations(false);
	}

	/**
	 * Returns the list of annotations.
	 * 
	 * @param binding if <code>true</code> binds the new array list into container
	 * @return the list of annotations
	 */
	public List<AbstractAnnotation> getAnnotations(boolean binding) {
		// there is not the property and the binding is requested
		// then adds array to container
		if (!has(Property.ANNOTATIONS) && binding) {
			// sets annotations to native object
			setArrayValue(Property.ANNOTATIONS, this.currentAnnotations);
		}
		// returns annotations
		return this.currentAnnotations;
	}

}
