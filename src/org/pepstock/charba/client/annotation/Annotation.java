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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.ScaleType;
import org.pepstock.charba.client.annotation.AnnotationOptionsFactory.AnnotationDefaultsOptionsFactory;
import org.pepstock.charba.client.annotation.enums.DrawTime;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
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
				// if here, is configured and loads teh configuration
				pOptions = options.getPlugins().getOptions(ID, FACTORY);
			} else {
				// if here, there is not any configuration
				// then it uses teh default
				pOptions = new AnnotationOptions(DefaultsOptions.DEFAULTS_INSTANCE);
			}
			// stores the options into cache
			pluginOptions.put(chart.getId(), pOptions);
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
}