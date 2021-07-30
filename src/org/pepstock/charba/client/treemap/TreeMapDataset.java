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
package org.pepstock.charba.client.treemap;

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayDoubleList;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayObjectContainerList;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.DataPoint;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.data.HovingFlexDataset;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.items.Undefined;

/**
 * The choropleth data set allows a region definition (by {@link Feature}) and specific value to be specified.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class TreeMapDataset extends HovingFlexDataset {

	// exception string message for setting data
	private static final String INVALID_SET_DATA_CALL = "'setData' method is not invokable by a treemap chart. Use 'setTree' or 'setTreeObjects' methods";
	// exception string message for getting data
	private static final String INVALID_GET_DATA_CALL = "'getData' method is not invokable by a treemap chart. Use 'getTree' or 'getTreeObjects' methods";

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		TREE("tree"),
		KEY("key"),
		GROUPS("groups"),
		COLOR("color"),
		DIVIDER_CAP_STYLE("dividerCapStyle"),
		DIVIDER_DASH("dividerDash"),
		DIVIDER_DASH_OFFSET("dividerDashOffset"),
		// internal key to store data type
		CHARBA_TREE_TYPE("charbaTreeType");

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
	 * Creates a data set.<br>
	 * It uses the global options has default.
	 */
	public TreeMapDataset() {
		this(Defaults.get().getGlobal());
	}

	/**
	 * Creates a data set setting the defaults value.
	 * 
	 * @param defaultValues default options
	 */
	public TreeMapDataset(IsDefaultOptions defaultValues) {
		this(TreeMapChart.CONTROLLER_TYPE, defaultValues);
	}

	/**
	 * Creates a data set by defaults value and the controller type.
	 * 
	 * @param type controller type related to the data set
	 * @param defaultValues default options
	 */
	TreeMapDataset(ControllerType type, IsDefaultOptions defaultValues) {
		super(type, defaultValues, Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of data points
	 * 
	 * @return a list of data points or an empty list of data points.
	 */
	public List<DataPoint> getDataPoints() {
		// gets array
		ArrayObject array = getArrayValue(CommonProperty.DATA);
		// returns points
		return ArrayListHelper.list(array, Dataset.DATAPOINTS_FACTORY);
	}

	/**
	 * Returns the type of tree property.
	 * 
	 * @return the type of tree property
	 */
	TreeType getTreeType() {
		return getValue(Property.TREE, TreeType.values(), TreeType.UNKNOWN);
	}

	/**
	 * Sets the tree data property of a data set for a chart is specified as an array of numbers.
	 * 
	 * @param values an array of numbers
	 */
	public void setTree(double... values) {
		// set value. If null, removes key and then..
		setArrayValue(Property.TREE, ArrayDouble.fromOrNull(values));
		// sets data type checking if the key exists
		setValue(Property.CHARBA_TREE_TYPE, has(Property.TREE) ? TreeType.NUMBERS : TreeType.UNKNOWN);
	}

	/**
	 * Sets the tree data property of a data set for a chart is specified as a list of numbers.
	 * 
	 * @param values list of numbers.
	 */
	public void setTree(List<Double> values) {
		// set value. If null, removes key and then..
		setArrayValue(Property.TREE, ArrayDouble.fromOrNull(values));
		// sets data type checking if the key exists
		setValue(Property.CHARBA_TREE_TYPE, has(Property.TREE) ? TreeType.NUMBERS : TreeType.UNKNOWN);
	}

	/**
	 * Returns the tree data property of a data set for a chart is specified as a list of numbers.
	 * 
	 * @return list of numbers.
	 */
	public List<Double> getTree() {
		return getTree(false);
	}

	/**
	 * Returns the tree data property of a data set for a chart is specified as an list of numbers.
	 * 
	 * @param binding if <code>true</code> binds the new array list in the container
	 * @return list of numbers
	 */
	public List<Double> getTree(boolean binding) {
		// checks if the type is not an object
		if (!TreeType.OBJECTS.equals(getTreeType())) {
			// checks if is a numbers data type
			if (has(Property.TREE)) {
				// gets numbers
				ArrayDouble array = getArrayValue(Property.TREE);
				// returns array
				return ArrayListHelper.list(array);
			}
			// checks if wants to bind the array
			if (binding) {
				ArrayDoubleList result = new ArrayDoubleList();
				// set value
				setArrayValue(Property.TREE, ArrayDouble.fromOrEmpty(result));
				// returns list
				return result;
			}
		}
		// returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Sets the tree data property of a data set for a chart is specified as an array of objects.
	 * 
	 * @param values an array of objects
	 */
	public <T extends NativeObjectContainer> void setTreeObjects(T[] values) {
		// set value. If null, removes key and then..
		setArrayValue(Property.TREE, ArrayObject.fromOrNull(values));
		// sets data type checking if the key exists
		setValue(Property.CHARBA_TREE_TYPE, has(Property.TREE) ? TreeType.OBJECTS : TreeType.UNKNOWN);
	}

	/**
	 * Sets the tree data property of a data set for a chart is specified as a list of objects.
	 * 
	 * @param values list of objects.
	 */
	public <T extends NativeObjectContainer> void setTreeObjetcs(List<T> values) {
		// set value. If null, removes key and then..
		setArrayValue(Property.TREE, ArrayObject.fromOrNull(values));
		// sets data type checking if the key exists
		setValue(Property.CHARBA_TREE_TYPE, has(Property.TREE) ? TreeType.OBJECTS : TreeType.UNKNOWN);
	}

	/**
	 * Returns the tree data property of a data set for a chart is specified as a list of objects.
	 * 
	 * @param factory instance of factory to create the native object
	 * @return list of objects.
	 */
	public <T extends NativeObjectContainer> List<T> getTreeObjects(NativeObjectContainerFactory<T> factory) {
		return getTreeObjects(factory, false);
	}

	/**
	 * Returns the tree data property of a data set for a chart is specified as an list of objects.
	 * 
	 * @param factory instance of factory to create the native object
	 * @param binding if <code>true</code> binds the new array list in the container
	 * @return list of objects
	 */
	public <T extends NativeObjectContainer> List<T> getTreeObjects(NativeObjectContainerFactory<T> factory, boolean binding) {
		// checks if the type is not an number and factory is consistent
		if (!TreeType.NUMBERS.equals(getTreeType()) && factory != null) {
			// checks if is a numbers data type
			if (has(Property.TREE)) {
				// gets objects
				ArrayObject array = getArrayValue(Property.TREE);
				// returns array
				return ArrayListHelper.list(array, factory);
			}
			// checks if wants to bind the array
			if (binding) {
				ArrayObjectContainerList<T> result = new ArrayObjectContainerList<>();
				// set value
				setArrayValue(Property.TREE, ArrayObject.fromOrEmpty(result));
				// returns list
				return result;
			}
		}
		// returns an empty list
		return Collections.emptyList();
	}
	
	/**
	 * Sets the the key of the object to use to get data value from a tree object.<br>
	 * It's needed ONLY when tree data is set by a list of objects.
	 * 
	 * @param key the the key of the object to use to get data value from a tree object
	 */
	public void setKey(String key) {
		setValue(Property.KEY, key);
	}
	
	/**
	 * Sets the the key of the object to use to get data value from a tree object.<br>
	 * It's needed ONLY when tree data is set by a list of objects.
	 * 
	 * @param key the the key of the object to use to get data value from a tree object
	 */
	public void setKey(Key key) {
		setValue(Property.KEY, key);
	}
	
	/**
	 * Returns the the key of the object to use to get data value from a tree object.<br>
	 * It's needed ONLY when tree data is set by a list of objects.
	 * 
	 * @return the the key of the object to use to get data value from a tree object
	 */
	public String getKeyAsString() {
		return getValue(Property.KEY, Undefined.STRING);
	}

	/**
	 * Returns the the key of the object to use to get data value from a tree object.<br>
	 * It's needed ONLY when tree data is set by a list of objects.
	 * 
	 * @return the the key of the object to use to get data value from a tree object
	 */
	public Key getKey() {
		// gets string value
		String value = getKeyAsString();
		// returns creating the key
		return value != null ? Key.create(value) : null;
	}

	/**
	 * Sets the browser events that the chart should listen to.
	 * 
	 * @param events the browser events that the chart should listen to.
	 */
	public void setGroups(String... groups) {
		setArrayValue(Property.GROUPS, ArrayString.fromOrNull(groups));
	}

	/**
	 * Sets the browser events that the chart should listen to.
	 * 
	 * @param events the browser events that the chart should listen to.
	 */
	public void setGroups(Key... groups) {
		setArrayValue(Property.GROUPS, ArrayString.fromOrNull(groups));
	}

	/**
	 * Sets the browser events that the chart should listen to.
	 * 
	 * @param events the browser events that the chart should listen to.
	 */
	public void setGroups(List<Key> groups) {
		setArrayValue(Property.GROUPS, groups != null ? ArrayString.fromOrNull(groups.toArray(new Key[0])) : null);
	}

	/**
	 * Returns the browser events that the chart should listen to.
	 * 
	 * @return the browser events that the chart should listen to.
	 */
	public List<String> getGroupsAsString() {
		// retrieves the array
		ArrayString array = getArrayValue(Property.GROUPS);
		// if the array is not consistent returns an empty list
		return array != null ? ArrayListHelper.list(array) : Collections.emptyList();
	}

	/**
	 * Returns the browser events that the chart should listen to.
	 * 
	 * @return the browser events that the chart should listen to.
	 */
	public List<Key> getGroups() {
		// retrieves the array
		ArrayString array = getArrayValue(Property.GROUPS);
		// if the array is not consistent returns an empty list
		return array != null ? ArrayListHelper.keys(array) : Collections.emptyList();
	}

	// ---------------------------
	// OVERRIDE METHODS
	// ---------------------------

	/**
	 * Throws an exception because not available.
	 * 
	 * @param values ignored because will throw an exception
	 */
	@Override
	public void setData(double... values) {
		throw new UnsupportedOperationException(INVALID_SET_DATA_CALL);
	}

	/**
	 * Throws an exception because not available.
	 * 
	 * @param values ignored because will throw an exception
	 */
	@Override
	public void setData(List<Double> values) {
		throw new UnsupportedOperationException(INVALID_SET_DATA_CALL);
	}

	/**
	 * Throws an exception because not available.
	 * 
	 * @param binding ignored because will throw an exception
	 * @return nothing because will throw an exception
	 */
	@Override
	public List<Double> getData(boolean binding) {
		throw new UnsupportedOperationException(INVALID_GET_DATA_CALL);
	}

	/**
	 * Enumeration with all possible types of data set in the dataset.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @see Dataset
	 */
	private enum TreeType implements Key
	{
		/**
		 * The tree property is not set yet.
		 */
		UNKNOWN("unknown"),
		/**
		 * The tree property is set as array of doubles.
		 */
		NUMBERS("numbers"),
		/**
		 * The data property is set as array of objects.
		 */
		OBJECTS("objects");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
		 * 
		 * @param value value of property name
		 */
		private TreeType(String value) {
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
}