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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.ScaleType;
import org.pepstock.charba.client.annotation.AnnotationOptionsFactory.AnnotationDefaultsOptionsFactory;
import org.pepstock.charba.client.annotation.enums.DrawTime;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.plugins.AbstractPlugin;

/**
 * The plugin draws lines and boxes on the chart area.<br>
 * It works with line, bar, scatter and bubble charts that use linear, logarithmic, time, or category scales.<br>
 * It will not work on any chart that does not have exactly two axes, including pie, radar, and polar area charts.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Annotation extends AbstractPlugin {

	/**
	 * Plugin ID <b>{@value ID}</b>.
	 */
	public static final String ID = "charbaannotation";
	/**
	 * The factory to read options for plugin
	 */
	public static final AnnotationOptionsFactory FACTORY = new AnnotationOptionsFactory();
	// factory instance to read the options from default global
	static final AnnotationDefaultsOptionsFactory DEFAULTS_FACTORY = new AnnotationDefaultsOptionsFactory();
	// singleton instance
	private static final Annotation INSTANCE = new Annotation();
	// cache to store options in order do not load every time the options
	private final Map<String, AnnotationOptions> pluginOptions = new HashMap<>();
	// map to maintain the annotation elements for every chart
	private final Map<String, List<AbstractAnnotationElement<?>>> annotationElements = new HashMap<>();
	// map to maintain all annotation instances, acts as a cache
	private final Map<Integer, AbstractAnnotation> annotationInstancesCache = new HashMap<>();
	// pattern factory
	private final Pattern.PatternFactory patternFactory = new Pattern.PatternFactory();
	// gradient factory
	private final Gradient.GradientFactory gradientFactory = new Gradient.GradientFactory();

	/**
	 * To avoid any instantiation
	 */
	private Annotation() {
		// do nothing
	}

	/**
	 * Returns the singleton instance of plugin.
	 * 
	 * @return the singleton instance of plugin
	 */
	public static Annotation get() {
		return INSTANCE;
	}

	/**
	 * Returns the pattern factory instance of plugin.
	 * 
	 * @return the pattern factory instance of plugin
	 */
	Pattern.PatternFactory getPatternFactory() {
		return patternFactory;
	}

	/**
	 * Returns the gradient factory instance of plugin.
	 * 
	 * @return the gradient factory instance of plugin
	 */
	Gradient.GradientFactory getGradientFactory() {
		return gradientFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#getId()
	 */
	@Override
	public String getId() {
		return ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onConfigure(org.pepstock.charba.client. AbstractChart)
	 */
	@Override
	public void onConfigure(IsChart chart) {
		// checks if chart is consistent and the plugin has been invoked for an accepted chart
		if (mustBeActivated(chart)) {
			// options instance
			AnnotationOptions pOptions = null;
			// loads chart options for the chart
			IsDefaultScaledOptions options = chart.getWholeOptions();
			// checks if the plugin has been configured
			if (options.getPlugins().hasOptions(ID)) {
				// if here, is configured and loads the configuration
				pOptions = options.getPlugins().getOptions(ID, FACTORY);
			} else {
				// if here, there is not any configuration
				// then it uses the default
				pOptions = new AnnotationOptions(AnnotationDefaultsOptions.DEFAULTS_INSTANCE);
			}
			// stores the options into cache
			pluginOptions.put(chart.getId(), pOptions);
			// loads creating all annotation elements
			loadAnnotationElements(chart, pOptions);
		}
	}

	/**
	 * Creates and loads the annotation elements reading the configuration from annotation options.
	 * 
	 * @param chart chart instance to use for creating elements
	 * @param pOptions annotation options retrieved from chart options.
	 */
	private void loadAnnotationElements(IsChart chart, AnnotationOptions pOptions) {
		// cleans the elements previously loaded
		cleanAnnotationElements(chart);
		// creates new container for element
		List<AbstractAnnotationElement<?>> listOfElements = new LinkedList<>();
		// stores the containers
		annotationElements.put(chart.getId(), listOfElements);
		// checks if the root of options has got draw time property
		final DrawTime defaultDrawTime = pOptions.hasDrawTime() ? pOptions.getDrawTime() : null;
		// scans loading the draw time
		for (AbstractAnnotation annotation : pOptions.getAnnotations()) {
			// checks if annotation is enabled
			if (annotation.isEnabled()) {
				// sets default draw time
				annotation.setDefaultDrawTime(defaultDrawTime);
				// checks and loads annotation elements
				if (annotation instanceof BoxAnnotation) {
					// casts to box annotation
					BoxAnnotation boxAnnotation = (BoxAnnotation) annotation;
					// adds new box annotation element
					listOfElements.add(new BoxAnnotationElement(chart, boxAnnotation));
				} else if (annotation instanceof LineAnnotation) {
					// casts to line annotation
					LineAnnotation lineAnnotation = (LineAnnotation) annotation;
					// adds new line annotation element
					listOfElements.add(new LineAnnotationElement(chart, lineAnnotation));
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onAfterLayout(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onAfterLayout(IsChart chart) {
		// the configuration of annotation elements must be performed after the scales are configured into CHARTJS.
		// This is the reason why the annotation elements are configured into after layout methods of plugin.
		// checks if chart is consistent and the plugin has been invoked for an accepted chart
		// and the plugin configuration and annotation elements are already loaded
		if (mustBeActivated(chart) && pluginOptions.containsKey(chart.getId()) && annotationElements.containsKey(chart.getId())) {
			// gets the annotation elements
			List<AbstractAnnotationElement<?>> listOfElements = annotationElements.get(chart.getId());
			// scans all elements
			for (AbstractAnnotationElement<?> element : listOfElements) {
				// configures all elements
				element.configure();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onBeforeDatasetsDraw(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public boolean onBeforeDatasetsDraw(IsChart chart) {
		draw(chart, DrawTime.BEFORE_DATASETS_DRAW);
		// must be always true to continue drawing
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onAfterDraw(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onAfterDraw(IsChart chart) {
		draw(chart, DrawTime.AFTER_DRAW);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onAfterDatasetsDraw(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onAfterDatasetsDraw(IsChart chart) {
		draw(chart, DrawTime.AFTER_DATASETS_DRAW);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onDestroy(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onDestroy(IsChart chart) {
		// checks if chart is consistent and the plugin has been invoked for an accepted chart
		if (mustBeActivated(chart)) {
			// removes options
			pluginOptions.remove(chart.getId());
			// removes all annotation elements
			cleanAnnotationElements(chart);
		}
	}

	/**
	 * Draws all annotation elements on the canvas.
	 * 
	 * @param chart chart instance
	 * @param drawTime the draw time is used to get all annotation elements configured to be drawn in that draw time
	 */
	private void draw(IsChart chart, DrawTime drawTime) {
		// checks if chart is consistent and the plugin has been invoked for an accepted chart
		// and the plugin configuration and annotation elements are already loaded
		if (mustBeActivated(chart) && pluginOptions.containsKey(chart.getId()) && annotationElements.containsKey(chart.getId())) {
			// gets all annotation elements
			List<AbstractAnnotationElement<?>> listOfElements = annotationElements.get(chart.getId());
			// scans all elements
			for (AbstractAnnotationElement<?> element : listOfElements) {
				// gets the configuration of element
				AbstractAnnotation configuration = element.getConfiguration();
				// checks if the annotation element is well configure and ready to be drawn and
				// the the draw time of the configuration is the same of the plugin.
				if (element.isConsistent() && drawTime.equals(configuration.getDrawTime())) {
					// draws annotation element
					element.draw();
				}
			}
		}
	}

	/**
	 * Returns <code>true</code> if the chart is consistent and the scale type of chart is {@link ScaleType#MULTI}.
	 * 
	 * @param chart chart instance to check
	 * @return <code>true</code> if the chart is consistent and the scale type of chart is {@link ScaleType#MULTI}
	 */
	private boolean mustBeActivated(IsChart chart) {
		// checks consistent and with multiple scales
		return IsChart.isConsistent(chart) && ScaleType.MULTI.equals(chart.getType().scaleType());
	}

	/**
	 * Removes all annotation elements stored for the passed chart.
	 * 
	 * @param chart chart instance to clean the annotation element
	 */
	private void cleanAnnotationElements(IsChart chart) {
		// checks if annotation elements are stored
		if (annotationElements.containsKey(chart.getId())) {
			// removes options
			List<AbstractAnnotationElement<?>> oldListOfElements = annotationElements.remove(chart.getId());
			// scans all old elements to destroy them
			oldListOfElements.forEach(AbstractAnnotationElement::destroy);
		}
	}

	/**
	 * Adds an annotation configuration into the cache.
	 * 
	 * @param annotation annotation configuration instance to store into the cache
	 */
	void addAnnotation(AbstractAnnotation annotation) {
		// checks if annotation argument is consistent
		if (annotation != null && annotation.getAnnotationId() != UndefinedValues.INTEGER) {
			// stores the annotation configuration
			annotationInstancesCache.put(annotation.getAnnotationId(), annotation);
		}
	}

	/**
	 * Retrieves a cached annotation configuration item, previously stored.
	 * 
	 * @param annotationId annotation id to use to get the annotation configuration item
	 * @return a cached annotation configuration item or <code>null</code> if not exist
	 */
	AbstractAnnotation getAnnotation(int annotationId) {
		return annotationInstancesCache.get(annotationId);
	}

	/**
	 * Retrieves the annotation configuration instance from chart, by an annotation id, in order to get it as default of another annotation object.
	 * 
	 * @param type annotation type of the default annotation object
	 * @param id annotation id to use to get the annotation object
	 * @param chart chart instance to search the annotation object
	 * @return the annotation configuration related to the id passed as argument
	 */
	IsDefaultsAnnotation getDefaultsAnnotationOptionsByChart(AnnotationType type, IsAnnotationId id, IsChart chart) {
		// checks annotation type
		// if not exception
		Key.checkIfValid(type);
		// checks if annotation type is consistent
		if (IsChart.isConsistent(chart) && IsAnnotationId.isValid(id)) {
			// gets result, inspecting the chart options
			return inspectChartToGetAnnotation(type, id, chart.getDefaultChartOptions().getPlugins().getOptions(Annotation.ID, Annotation.FACTORY));
		}
		// ig here, the chart is not consistent
		// then returns the default for the annotation type
		return type.getDefaultsValues();
	}

	/**
	 * Retrieves the annotation configuration instance from globals, by an annotation id, in order to get it as default of another annotation object.
	 * 
	 * @param type annotation type of the default annotation object
	 * @param id annotation id to use to get the annotation object
	 * @return the annotation configuration related to the id passed as argument
	 */
	IsDefaultsAnnotation getDefaultsAnnotationOptionsByGlobal(AnnotationType type, IsAnnotationId id) {
		// checks annotation type
		// if not exception
		Key.checkIfValid(type);
		// gets result, inspecting the global options
		return inspectChartToGetAnnotation(type, id, Defaults.get().getGlobal().getPlugins().getOptions(Annotation.ID, Annotation.FACTORY));
	}

	/**
	 * Retrieves the annotation configuration instance, inspecting an annotation options, in order to get it as default of another annotation object.
	 * 
	 * @param type annotation type of the default annotation object
	 * @param id annotation id to use to get the annotation object
	 * @param options annotation options instance to inspect in order to retrieve the annotation object
	 * @return the annotation configuration related to the id passed as argument
	 */
	private IsDefaultsAnnotation inspectChartToGetAnnotation(AnnotationType type, IsAnnotationId id, AnnotationOptions options) {
		// checks if annotation id and options are consistent
		if (IsAnnotationId.isValid(id) && options != null && options.hasAnnotation(id)) {
			// stores the result
			IsDefaultsAnnotation result = options.getAnnotation(id);
			// checks if the result type and type passed as argument
			// must be the same otherwise the default will be return
			if (type.equals(result.getType())) {
				return result;
			}
		}
		// if here, something is not consistent
		// then returns the defaults
		return type.getDefaultsValues();
	}
}