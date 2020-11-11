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
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * This is the {@link Annotation#ID} plugin options where to set all configuration items needed to the plugin.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AnnotationOptions extends AbstractPluginOptions implements IsDefaultsAnnotationOptions {

	/**
	 * Default draw time, <b>{@link DrawTime#AFTER_DATASETS_DRAW}</b>.
	 */
	public static final DrawTime DEFAULT_DRAW_TIME = DrawTime.AFTER_DATASETS_DRAW;

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
	private final IsDefaultsAnnotationOptions defaultsOptions;
	// annotations object, stored by key
	private final AnnotationMap annotationsMap;

	/**
	 * Creates new {@link Annotation#ID} plugin options.
	 */
	public AnnotationOptions() {
		this(null, null);
	}

	/**
	 * Creates new {@link Annotation#ID} plugin options, relating to chart instance for default.
	 * 
	 * @param chart chart instance related to the plugin options
	 */
	public AnnotationOptions(IsChart chart) {
		this(IsChart.isConsistent(chart) ? chart.getDefaultChartOptions().getPlugins().getOptions(Annotation.ID, Annotation.FACTORY) : null);
	}

	/**
	 * Creates new {@link Annotation#ID} plugin options.
	 * 
	 * @param defaultsOptions default options stored into defaults global
	 */
	AnnotationOptions(IsDefaultsAnnotationOptions defaultsOptions) {
		// creates an empty native object
		this(defaultsOptions, null);
	}

	/**
	 * Creates new {@link Annotation#ID} plugin options.<br>
	 * <b>PAY ATTENTION</b>: this method is invoked from plugin before starting drawing and NOT for configuration.
	 * 
	 * @param defaultsOptions default options stored into defaults global
	 * @param nativeObject native object loaded from configuration
	 */
	AnnotationOptions(IsDefaultsAnnotationOptions defaultsOptions, NativeObject nativeObject) {
		super(Annotation.ID, nativeObject);
		// checks if defaults options are consistent
		if (defaultsOptions == null) {
			// reads the default default global options
			this.defaultsOptions = loadGlobalsPluginOptions(Annotation.DEFAULTS_FACTORY);
		} else {
			// stores default options
			this.defaultsOptions = defaultsOptions;
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
			// stores into java script object as well
			setValue(Property.ANNOTATIONS, annotationsMap);
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
	@Override
	public DrawTime getDrawTime() {
		return getValue(Property.DRAW_TIME, DrawTime.values(), defaultsOptions.getDrawTime());
	}

	/**
	 * Returns <code>true</code> if the annotation with the id passed as argument exists.
	 * 
	 * @param id annotation id to check
	 * @return <code>true</code> if the annotation with the id passed as argument exists
	 */
	public boolean hasAnnotation(String id) {
		return hasAnnotation(IsAnnotationId.create(id));
	}

	/**
	 * Returns <code>true</code> if the annotation with the id passed as argument exists.
	 * 
	 * @param id annotation id to check
	 * @return <code>true</code> if the annotation with the id passed as argument exists
	 */
	@Override
	public boolean hasAnnotation(IsAnnotationId id) {
		return annotationsMap.hasAnnotation(id) || defaultsOptions.hasAnnotation(id);
	}

	/**
	 * Removes the annotation by the id passed as argument, if exists.
	 * 
	 * @param id annotation id to check
	 */
	public void removeAnnotation(String id) {
		removeAnnotation(IsAnnotationId.create(id));
	}

	/**
	 * Removes the annotation by the id passed as argument, if exists.
	 * 
	 * @param id annotation id to check
	 */
	public void removeAnnotation(IsAnnotationId id) {
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
		return getAnnotation(IsAnnotationId.create(id));
	}

	/**
	 * Returns the annotation with the id passed as argument or <code>null</code> if not exist.
	 * 
	 * @param id annotation id to check
	 * @return the annotation with the id passed as argument or <code>null</code> if not exist
	 */
	@Override
	public AbstractAnnotation getAnnotation(IsAnnotationId id) {
		// gets annotation instance
		AbstractAnnotation result = annotationsMap.getAnnotation(id);
		// if annotation is consistent
		if (result == null) {
			// not present in this object
			// searches on the default
			return defaultsOptions.getAnnotation(id);

		}
		// if here, the annotation was found
		// in this object then returns it
		return result;
	}

}
