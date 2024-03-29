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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayUtil;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;

/**
 * Object which stores all annotations by their ID in the {@link AnnotationPlugin#ID} plugin.
 * 
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
class AnnotationMap extends NativeObjectContainer {

	/**
	 * Creates an empty object.
	 */
	AnnotationMap() {
		this(null);
	}

	/**
	 * Creates the object using the instance of native object, passed as argument.
	 * 
	 * @param nativeObject native object loaded from configuration
	 */
	AnnotationMap(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns <code>true</code> if the annotation with the id passed as argument exists.
	 * 
	 * @param id annotation id to check
	 * @return <code>true</code> if the annotation with the id passed as argument exists
	 */
	boolean hasAnnotation(AnnotationId id) {
		// checks if the annotation id is consistent
		AnnotationId.checkIfValid(id);
		// checks if the annotation id exist
		return has(id);
	}

	/**
	 * Removes the annotation by the id passed as argument, if exists.
	 * 
	 * @param id annotation id to check
	 */
	void removeAnnotation(AnnotationId id) {
		// checks if the annotation id is consistent
		AnnotationId.checkIfValid(id);
		// removes from java script object if the annotation id exist
		remove(id);
	}

	/**
	 * Adds an annotations for plugin.
	 * 
	 * @param annotations set of annotations.
	 */
	void addAnnotations(AbstractAnnotation... annotations) {
		// checks if array argument is consistent
		if (ArrayUtil.isNotEmpty(annotations)) {
			// scans all arguments
			for (AbstractAnnotation annotation : annotations) {
				// adds annotation
				AnnotationId id = annotation.getId();
				// stores in the java script object
				setValue(id, annotation);
			}
		}
	}

	/**
	 * Sets a set of annotations for plugin.<br>
	 * If argument is <code>null</code>, removes all annotations.
	 * 
	 * @param annotations set of annotations.<br>
	 *            If <code>null</code>, removes all annotations
	 */
	void setAnnotations(AbstractAnnotation... annotations) {
		// clear existing object
		clear();
		// adds annotation
		addAnnotations(annotations);
	}

	/**
	 * Clears all stored annotations.
	 */
	void clear() {
		// scan all keys
		for (Key key : keys()) {
			// removes existing annotation
			remove(key);
		}
	}

	/**
	 * Returns the unmodifiable list of annotations.
	 * 
	 * @return the unmodifiable list of annotations
	 */
	List<AbstractAnnotation> getAnnotations() {
		// gets an empty result
		List<AbstractAnnotation> result = new ArrayList<>();
		// scan all keys
		for (Key key : keys()) {
			// checks if the type of the stored object
			// is an object
			if (isType(key, ObjectType.OBJECT)) {
				// gets and creates the annotation
				AbstractAnnotation annotation = getAndCreateAnnotation(key);
				// if the annotation reference is consistent
				if (annotation != null) {
					// then adds to result
					result.add(annotation);
				}
			}
		}
		// returns an unmodifiable list with the annotations
		// in order it can not be updated
		return Collections.unmodifiableList(result);
	}

	/**
	 * Returns the annotation with the id passed as argument or <code>null</code> if not exist.
	 * 
	 * @param id annotation id to check
	 * @return the annotation with the id passed as argument or <code>null</code> if not exist
	 */
	AbstractAnnotation getAnnotation(AnnotationId id) {
		// checks if the annotation id is consistent
		AnnotationId.checkIfValid(id);
		// checks if the annotation id exist
		if (hasAnnotation(id) && isType(id, ObjectType.OBJECT)) {
			// gets from the cache
			return getAndCreateAnnotation(id);
		}
		// if here, the annotation id does not exist
		// then returns null
		return null;
	}

	/**
	 * Gets the stored annotation from the native object and then it creates a wrapper on that in order to provide an annotation instance.
	 * 
	 * @param id property of key of the native object where the annotation is stored.
	 * @return an annotation instance or <code>null</code> if the stored object is not consistent
	 */
	AbstractAnnotation getAndCreateAnnotation(Key id) {
		// -----------------------
		// for annotation object
		// -----------------------
		// gets the native object
		NativeObject nativeObject = getValue(id);
		// extracts the type of the annotation as string
		String typeAsString = JsHelper.get().getStringProperty(AbstractAnnotation.Property.TYPE, nativeObject);
		// gets the type as annotation type enumeration
		AnnotationType type = Key.getKeyByValue(AnnotationType.values(), typeAsString);
		// -----------------------
		// for annotation defaults
		// -----------------------
		// extracts the internal annotation id
		int annotationId = JsHelper.get().getIntegerProperty(AbstractAnnotation.Property.CHARBA_ANNOTATION_ID, nativeObject);
		// searches for cached annotation by its internal id
		AbstractAnnotation defaultOptions = AnnotationHelper.get().getAnnotation(annotationId);
		// -----------------------
		// checks which type is in order to create the right annotation instance
		if (AnnotationType.BOX.equals(type) && defaultOptions instanceof BoxAnnotation) {
			return new BoxAnnotation(nativeObject, defaultOptions);
		} else if (AnnotationType.LABEL.equals(type) && defaultOptions instanceof LabelAnnotation) {
			return new LabelAnnotation(nativeObject, defaultOptions);
		} else if (AnnotationType.LINE.equals(type) && defaultOptions instanceof LineAnnotation) {
			return new LineAnnotation(nativeObject, defaultOptions);
		} else if (AnnotationType.ELLIPSE.equals(type) && defaultOptions instanceof EllipseAnnotation) {
			return new EllipseAnnotation(nativeObject, defaultOptions);
		} else if (AnnotationType.POINT.equals(type) && defaultOptions instanceof PointAnnotation) {
			return new PointAnnotation(nativeObject, defaultOptions);
		} else if (AnnotationType.POLYGON.equals(type) && defaultOptions instanceof PolygonAnnotation) {
			return new PolygonAnnotation(nativeObject, defaultOptions);
		} else {
			// if here, the type is not consistent
			// then returns a line annotation being the default in the plugin
			return new LineAnnotation(nativeObject, defaultOptions);
		}
	}
}