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
package org.pepstock.charba.client.geo;

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.Labels;
import org.pepstock.charba.client.geo.callbacks.FeatureFilterCallback;
import org.pepstock.charba.client.geo.callbacks.FeatureFindCallback;
import org.pepstock.charba.client.geo.callbacks.FeatureLabelCallback;
import org.pepstock.charba.client.resources.AbstractInjectableResource;
import org.pepstock.charba.client.utils.JSON;

/**
 * Utility to manage <a href="https://github.com/topojson/topojson">TopoJson</a> definitions.<br>
 * <b>TopoJson</b> is an extension of <b>GeoJSON</b> that encodes topology.<br>
 * Rather than representing geometries discretely, geometries in <b>TopoJson</b> files are stitched together from shared line segments called arc.<br>
 * <b>PAY ATTENTION</b> CHARBA doesn't include any <b>TopoJSON</b> files itself.<br>
 * Some useful resources I found so far:<br>
 * <ul>
 * <li>US map: <a href="https://www.npmjs.com/package/us-atlas">https://www.npmjs.com/package/us-atlas</a>
 * <li>World map: <a href="https://www.npmjs.com/package/world-atlas">https://www.npmjs.com/package/world-atlas</a>
 * <li>TopoJson collection: <a href="https://github.com/deldersveld/topojson">https://github.com/deldersveld/topojson</a>
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class GeoUtils {

	/**
	 * To avoid any instantiation
	 */
	private GeoUtils() {
		// do nothing
	}
	
	// ---------------------------
	// TOPOJSON
	// ---------------------------

	/**
	 * Creates the {@link TopoJson} object definition from a topoJson text definition.
	 * 
	 * @param topojson topoJson definition.
	 * @return the {@link TopoJson} object definition
	 */
	public static TopoJson createTopoJson(AbstractInjectableResource topojson) {
		// checks if topojson is consistent
		if (topojson != null) {
			return createTopoJson(topojson.getContent());
		}
		// if here, the arguments or the feature parsing are not consistent
		// then returns an empty object
		return new TopoJson(null);
	}

	/**
	 * Creates the {@link TopoJson} object definition from a topoJson text definition.
	 * 
	 * @param topojson topoJson definition.
	 * @return the {@link TopoJson} object definition
	 */
	public static TopoJson createTopoJson(String topojson) {
		// checks if topojson text is consistent
		if (isConsistent(topojson) ) {
			return new TopoJson(JSON.parse(topojson));
		}
		// if here, the arguments or the feature parsing are not consistent
		// then returns an empty object
		return new TopoJson(null);
	}

	// ---------------------------
	// FEATURES
	// ---------------------------

	/**
	 * Reads the topoJson definition and creates a list of features to enable regions drawing.
	 * 
	 * @param topojson topoJson definition.
	 * @param featureProperty property in the <code>objects</code> node of topoJson definition where all regions are defined.
	 * @return a list of features to enable regions drawing
	 */
	public static List<Feature> features(AbstractInjectableResource topojson, String featureProperty) {
		return features(topojson, featureProperty, null);
	}

	/**
	 * Reads the topoJson definition and creates a list of features to enable regions drawing.
	 * 
	 * @param topojson topoJson definition.
	 * @param featureProperty property in the <code>objects</code> node of topoJson definition where all regions are defined.
	 * @return a list of features to enable regions drawing
	 */
	public static List<Feature> features(AbstractInjectableResource topojson, Key featureProperty) {
		return features(topojson, featureProperty, null);
	}

	/**
	 * Reads the topoJson definition and creates a list of features to enable regions drawing.
	 * 
	 * @param topojson topoJson definition.
	 * @param featureProperty property in the <code>objects</code> node of topoJson definition where all regions are defined
	 * @param filterCallback callback instance to filter the features to draw
	 * @return a list of features to enable regions drawing
	 */
	public static List<Feature> features(AbstractInjectableResource topojson, String featureProperty, FeatureFilterCallback filterCallback) {
		// checks if topojson is consistent
		if (topojson != null) {
			return features(topojson.getContent(), featureProperty, filterCallback);
		}
		// if here, the arguments or the feature parsing are not consistent
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Reads the topoJson definition and creates a list of features to enable regions drawing.
	 * 
	 * @param topojson topoJson definition.
	 * @param featureProperty property in the <code>objects</code> node of topoJson definition where all regions are defined.
	 * @param filterCallback callback instance to filter the features to draw
	 * @return a list of features to enable regions drawing
	 */
	public static List<Feature> features(AbstractInjectableResource topojson, Key featureProperty, FeatureFilterCallback filterCallback) {
		// checks if arguments are consistent
		if (topojson != null && Key.isValid(featureProperty)) {
			return features(topojson.getContent(), featureProperty.value(), filterCallback);
		}
		// if here, the arguments or the feature parsing are not consistent
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Reads the topoJson definition and creates a list of features to enable regions drawing.
	 * 
	 * @param topojson topoJson definition.
	 * @param featureProperty property in the <code>objects</code> node of topoJson definition where all regions are defined.
	 * @return a list of features to enable regions drawing
	 */
	public static List<Feature> features(String topojson, Key featureProperty) {
		return features(topojson, featureProperty, null);
	}

	/**
	 * Reads the topoJson definition and creates a list of features to enable regions drawing.
	 * 
	 * @param topojson topoJson definition.
	 * @param featureProperty property in the <code>objects</code> node of topoJson definition where all regions are defined.
	 * @return a list of features to enable regions drawing
	 */
	public static List<Feature> features(String topojson, String featureProperty) {
		return features(topojson, featureProperty, null);
	}

	/**
	 * Reads the topoJson definition and creates a list of features to enable regions drawing.
	 * 
	 * @param topojson topoJson definition.
	 * @param featureProperty property in the <code>objects</code> node of topoJson definition where all regions are defined.
	 * @param filterCallback callback instance to filter the features to draw
	 * @return a list of features to enable regions drawing
	 */
	public static List<Feature> features(String topojson, Key featureProperty, FeatureFilterCallback filterCallback) {
		// checks if property is consistent
		if (Key.isValid(featureProperty)) {
			return features(topojson, featureProperty.value(), filterCallback);
		}
		// if here, the arguments or the feature parsing are not consistent
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Reads the topoJson definition and creates a list of features to enable regions drawing.
	 * 
	 * @param topojson topoJson definition.
	 * @param featureProperty property in the <code>objects</code> node of topoJson definition where all regions are defined.
	 * @param filterCallback callback instance to filter the features to draw
	 * @return a list of features to enable regions drawing
	 */
	public static List<Feature> features(String topojson, String featureProperty, FeatureFilterCallback filterCallback) {
		// checks if arguments are consistent
		if (isConsistent(topojson)) {
			return features(new TopoJson(JSON.parse(topojson)), featureProperty, filterCallback);
		}
		// if here, the arguments or the feature parsing are not consistent
		// then returns an empty list
		return Collections.emptyList();
	}
	
	/**
	 * Reads the topoJson definition and creates a list of features to enable regions drawing.
	 * 
	 * @param topojson topoJson definition.
	 * @param featureProperty property in the <code>objects</code> node of topoJson definition where all regions are defined.
	 * @return a list of features to enable regions drawing
	 */
	public static List<Feature> features(TopoJson topojson, Key featureProperty) {
		return features(topojson, featureProperty, null);
	}

	/**
	 * Reads the topoJson definition and creates a list of features to enable regions drawing.
	 * 
	 * @param topojson topoJson definition.
	 * @param featureProperty property in the <code>objects</code> node of topoJson definition where all regions are defined.
	 * @return a list of features to enable regions drawing
	 */
	public static List<Feature> features(TopoJson topojson, String featureProperty) {
		return features(topojson, featureProperty, null);
	}

	/**
	 * Reads the topoJson definition and creates a list of features to enable regions drawing.
	 * 
	 * @param topojson topoJson definition.
	 * @param featureProperty property in the <code>objects</code> node of topoJson definition where all regions are defined.
	 * @param filterCallback callback instance to filter the features to draw
	 * @return a list of features to enable regions drawing
	 */
	public static List<Feature> features(TopoJson topojson, Key featureProperty, FeatureFilterCallback filterCallback) {
		// checks if property is consistent
		if (Key.isValid(featureProperty)) {
			return features(topojson, featureProperty.value(), filterCallback);
		}
		// if here, the arguments or the feature parsing are not consistent
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Reads the topoJson definition and creates a list of features to enable regions drawing.
	 * 
	 * @param topojson topoJson definition.
	 * @param featureProperty property in the <code>objects</code> node of topoJson definition where all regions are defined.
	 * @param filterCallback callback instance to filter the features to draw
	 * @return a list of features to enable regions drawing
	 */
	public static List<Feature> features(TopoJson topojson, String featureProperty, FeatureFilterCallback filterCallback) {
		// checks if arguments are consistent
		if (topojson!= null && isConsistent(featureProperty)) {
			// gets array of features
			ArrayObject array = JsGeoHelper.get().features(topojson, featureProperty);
			// checks if result is consistent
			if (array != null) {
				// checks if callback must be applied
				if (filterCallback != null) {
					return ArrayListHelper.unmodifiableList(array.filter((element, index) -> filterCallback.filter(new Feature(element), index)), Feature.FACTORY);
				} else {
					return ArrayListHelper.unmodifiableList(array, Feature.FACTORY);
				}
			}
		}
		// if here, the arguments or the feature parsing are not consistent
		// then returns an empty list
		return Collections.emptyList();
	}


	// ---------------------------
	// FEATURE
	// ---------------------------

	/**
	 * Reads the topoJson definition and find a specific feature to enable outline.
	 * 
	 * @param topojson topoJson definition.
	 * @param featureProperty property in the <code>objects</code> node of topoJson definition where all regions are defined
	 * @param findCallback callback instance to find the features to outline
	 * @return a features instance to enable outline or <code>null</code>
	 */
	public static Feature feature(AbstractInjectableResource topojson, String featureProperty, FeatureFindCallback findCallback) {
		// checks if topojson is consistent
		if (topojson != null) {
			return feature(topojson.getContent(), featureProperty, findCallback);
		}
		// if here, the arguments or the feature parsing are not consistent
		// then returns null
		return null;
	}

	/**
	 * Reads the topoJson definition and find a specific feature to enable outline.
	 * 
	 * @param topojson topoJson definition.
	 * @param featureProperty property in the <code>objects</code> node of topoJson definition where all regions are defined
	 * @param findCallback callback instance to find the features to outline
	 * @return a features instance to enable outline or <code>null</code>
	 */
	public static Feature feature(AbstractInjectableResource topojson, Key featureProperty, FeatureFindCallback findCallback) {
		// checks if arguments are consistent
		if (topojson != null && Key.isValid(featureProperty)) {
			return feature(topojson.getContent(), featureProperty.value(), findCallback);
		}
		// if here, the arguments or the feature parsing are not consistent
		// then returns null
		return null;
	}

	/**
	 * Reads the topoJson definition and find a specific feature to enable outline.
	 * 
	 * @param topojson topoJson definition.
	 * @param featureProperty property in the <code>objects</code> node of topoJson definition where all regions are defined
	 * @param findCallback callback instance to find the features to outline
	 * @return a features instance to enable outline or <code>null</code>
	 */
	public static Feature feature(String topojson, Key featureProperty, FeatureFindCallback findCallback) {
		// checks if property is consistent
		if (Key.isValid(featureProperty)) {
			return feature(topojson, featureProperty.value(), findCallback);
		}
		// if here, the arguments or the feature parsing are not consistent
		// then returns null
		return null;
	}

	/**
	 * Reads the topoJson definition and find a specific feature to enable outline.
	 * 
	 * @param topojson topoJson definition.
	 * @param featureProperty property in the <code>objects</code> node of topoJson definition where all regions are defined
	 * @param findCallback callback instance to find the features to outline
	 * @return a features instance to enable outline or <code>null</code>
	 */
	public static Feature feature(String topojson, String featureProperty, FeatureFindCallback findCallback) {
		// checks if topojson text is consistent
		if (isConsistent(topojson) ) {
			return feature(new TopoJson(JSON.parse(topojson)), featureProperty, findCallback);
		}
		// if here, the arguments or the feature parsing are not consistent
		// then returns null
		return null;
	}

	/**
	 * Reads the topoJson definition and find a specific feature to enable outline.
	 * 
	 * @param topojson topoJson definition.
	 * @param featureProperty property in the <code>objects</code> node of topoJson definition where all regions are defined
	 * @param findCallback callback instance to find the features to outline
	 * @return a features instance to enable outline or <code>null</code>
	 */
	public static Feature feature(TopoJson topojson, Key featureProperty, FeatureFindCallback findCallback) {
		// checks if property is consistent
		if (Key.isValid(featureProperty)) {
			return feature(topojson, featureProperty.value(), findCallback);
		}
		// if here, the arguments or the feature parsing are not consistent
		// then returns null
		return null;
	}

	/**
	 * Reads the topoJson definition and find a specific feature to enable outline.
	 * 
	 * @param topojson topoJson definition.
	 * @param featureProperty property in the <code>objects</code> node of topoJson definition where all regions are defined
	 * @param findCallback callback instance to find the features to outline
	 * @return a features instance to enable outline or <code>null</code>
	 */
	public static Feature feature(TopoJson topojson, String featureProperty, FeatureFindCallback findCallback) {
		// checks if arguments are consistent
		if (topojson != null && isConsistent(featureProperty) && findCallback != null) {
			// gets array of features
			ArrayObject array = JsGeoHelper.get().features(topojson, featureProperty);
			// finds the feature
			NativeObject foundObject = array.find((element, index) -> findCallback.find(new Feature(element), index));
			// checks if the feature has been found
			if (foundObject != null) {
				return new Feature(foundObject);
			}
		}
		// if here, the arguments or the feature parsing are not consistent
		// then returns null
		return null;
	}
	// ---------------------------
	// LABELS
	// ---------------------------

	/**
	 * Creates and loads a {@link Labels} object with the label of feature object by the property.
	 * 
	 * @param features list of features to draw
	 * @param property property in the {@link Feature} to identify it
	 * @return a {@link Labels} loaded with all features labels
	 */
	public static Labels loadLabels(List<Feature> features, Key property) {
		return loadLabels(features, null, property);
	}

	/**
	 * Creates and loads a {@link Labels} object with the label of feature object by the property.
	 * 
	 * @param features list of features to draw
	 * @param property property in the {@link Feature} to identify it
	 * @return a {@link Labels} loaded with all features labels
	 */
	public static Labels loadLabels(List<Feature> features, String property) {
		// checks if property is consistent
		if (isConsistent(property)) {
			return loadLabels(features, null, Key.create(property));
		}
		// if here, the property is not consistent
		// then returns an empty labels
		return Labels.build();
	}

	/**
	 * Creates and loads a {@link Labels} object with the label of feature object by the callback.
	 * 
	 * @param features list of features to draw
	 * @param labelCallback callback invoked in order to get the label of the feature
	 * @return a {@link Labels} loaded with all features labels
	 */
	public static Labels loadLabels(List<Feature> features, FeatureLabelCallback labelCallback) {
		return loadLabels(features, labelCallback, null);
	}

	/**
	 * This utility method is invoked by GEO axis to check if the chart, passed as argument, is consistent.
	 * 
	 * @param chart chart instance to be checked
	 * @return the controller type of the GEO chart
	 */
	static final ControllerType checkAndGetControllerType(IsChart chart) {
		// checks if the chart is consistent with the scale
		if (chart.getType() instanceof ControllerType) {
			// casts to controller type
			ControllerType chartType = (ControllerType) chart.getType();
			// checks if is a god chart type
			if (BubbleMapChart.CONTROLLER_TYPE.equals(chart.getType()) || ChoroplethChart.CONTROLLER_TYPE.equals(chartType)) {
				// returns controller
				return chartType;
			} else {
				// if here, the chart instance is not a GEO one
				// the exception
				throw new IllegalArgumentException("Chart argument is not an instance of " + BaseGeoChart.class.getName() + " but "+chart.getClass().getName());
			}
		} else {
			// if here, the chart instance is not a GEO one
			// the exception
			throw new IllegalArgumentException("Chart argument is not a controller.");
		}

	}

	/**
	 * Creates and loads a {@link Labels} object with the label of feature object by the property or by the callback.
	 * 
	 * @param features list of features to draw
	 * @param labelCallback callback invoked in order to get the label of the feature
	 * @param property property in the {@link Feature} to identify it
	 * @return a {@link Labels} loaded with all features labels
	 */
	private static Labels loadLabels(List<Feature> features, FeatureLabelCallback labelCallback, Key property) {
		// creates labels object
		Labels labels = Labels.build();
		// checks if list of feature is consistent
		if (features != null && !features.isEmpty()) {
			// scans all features
			for (Feature feature : features) {
				String result;
				// checks if callback is consistent
				if (labelCallback != null) {
					// invokes callback
					result = labelCallback.label(feature);
				} else if (Key.isValid(property)) {
					// gets the value of feature property
					result = feature.getStringProperty(property);
				} else {
					// if here, both callback and property are not consistent
					// then sets to null
					result = null;
				}
				// the result has been checked in the labels
				// if it is consistent
				labels.add(result);
			}
		}
		// returns the built labels object
		return labels;
	}

	// ---------------------------
	// INTERNALS
	// ---------------------------

	/**
	 * Returns <code>true</code> if the string argument is not null and length greater than 0.
	 * 
	 * @param value string value to check
	 * @return <code>true</code> if the string argument is not null and length greater than 0
	 */
	private static boolean isConsistent(String value) {
		return value != null && value.trim().length() > 0;
	}

}
