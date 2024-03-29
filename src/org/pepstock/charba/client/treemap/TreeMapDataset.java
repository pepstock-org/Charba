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
package org.pepstock.charba.client.treemap;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.callbacks.BorderRadiusCallback;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyNativeObjectCallback;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayDoubleList;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayObjectContainerList;
import org.pepstock.charba.client.commons.ArraySetHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.KeyFactory;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.BarBorderRadius;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.data.HoverFlexDataset;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.items.Undefined;

/**
 * The treemap data set allows to specify the values for displaying hierarchical data using nested rectangles.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class TreeMapDataset extends HoverFlexDataset {

	/**
	 * Factory to create {@link TreeMapDataPoint}s.
	 */
	public static final DataPointFactory DATAPOINTS_FACTORY = new DataPointFactory();
	/**
	 * Default border width, <b>{@value}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 0;
	/**
	 * Default spacing, <b>{@value}</b>.
	 */
	public static final double DEFAULT_SPACING = 0.5;
	/**
	 * Default border radius, <b>{@value}</b>.
	 */
	public static final int DEFAULT_BORDER_RADIUS = 0;
	/**
	 * Default right-to-left, <b>{@value}</b>.
	 */
	public static final boolean DEFAULT_RTL = false;
	/**
	 * Default tree leaf node key, <b>{@value}</b>.
	 */
	public static final String DEFAULT_TREE_LEAF_KEY = "_leaf";

	// exception string message for setting data
	private static final String INVALID_SET_DATA_CALL = "'setData' method is not invokable by a treemap chart. Use 'setTree' or 'setTreeObjects' methods";
	// exception string message for getting data
	private static final String INVALID_GET_DATA_CALL = "'getData' method is not invokable by a treemap chart. Use 'getTree' or 'getTreeObjects' methods";
	// factory to create keys
	private static final InternalKeyFactory KEY_FACTORY = new InternalKeyFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		TREE("tree"),
		TREE_LEAF_KEY("treeLeafKey"),
		KEY("key"),
		SUM_KEYS("sumKeys"),
		GROUPS("groups"),
		SPACING("spacing"),
		RTL("rtl"),
		BORDER_RADIUS("borderRadius"),
		// inner elements
		DIVIDERS("dividers"),
		CAPTIONS("captions"),
		LABELS("labels"),
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

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the border radius function
	private final CallbackProxy<ProxyNativeObjectCallback> borderRadiusCallbackProxy = JsHelper.get().newCallbackProxy();

	// ---------------------------
	// -- USERS CALLBACKS ---
	// ---------------------------
	// border radius callback instance
	private BorderRadiusCallback<DatasetContext> borderRadiusCallback = null;

	// dividers instance
	private final Dividers dividers;
	// captions instance
	private final Captions captions;
	// labels instance
	private final Labels labels;

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
		// gets the inner element
		this.dividers = new Dividers(this, Property.DIVIDERS, getValue(Property.DIVIDERS));
		this.captions = new Captions(this, Property.CAPTIONS, getDefaultValues(), getValue(Property.CAPTIONS));
		this.labels = new Labels(this, Property.LABELS, getDefaultValues(), getValue(Property.LABELS));
		// sets function to proxy callback in order to invoke the java interface
		this.borderRadiusCallbackProxy.setCallback(context -> BorderRadiusCallback.toObject(createContext(context), getBorderRadiusCallback(), DEFAULT_BORDER_RADIUS).nativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultBorderWidth()
	 */
	@Override
	protected int getDefaultBorderWidth() {
		return DEFAULT_BORDER_WIDTH;
	}

	/**
	 * Returns the dividers object.
	 * 
	 * @return the dividers object.
	 */
	public Dividers getDividers() {
		return dividers;
	}

	/**
	 * Returns the captions object.
	 * 
	 * @return the captions object.
	 */
	public Captions getCaptions() {
		return captions;
	}

	/**
	 * Returns the labels object.
	 * 
	 * @return the labels object.
	 */
	public Labels getLabels() {
		return labels;
	}

	/**
	 * Returns the treemap data property of a dataset for a chart is specified as an array of treemap data points
	 * 
	 * @return a list of treemap data points or an empty list of data points.
	 */
	public List<TreeMapDataPoint> getDataPoints() {
		// gets array
		ArrayObject array = getArrayValue(CommonProperty.DATA);
		// returns points
		return ArrayListHelper.list(array, DATAPOINTS_FACTORY);
	}

	/**
	 * Returns the type of tree property.
	 * 
	 * @return the type of tree property
	 */
	TreeType getTreeType() {
		return getValue(Property.CHARBA_TREE_TYPE, TreeType.values(), TreeType.UNKNOWN);
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
		if (TreeType.NUMBERS.equals(getTreeType())) {
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
	 * @param <T> type of tree objects
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
	 * @param <T> type of tree objects
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
	 * @param <T> type of tree objects
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
	 * @param <T> type of tree objects
	 * @return list of objects
	 */
	public <T extends NativeObjectContainer> List<T> getTreeObjects(NativeObjectContainerFactory<T> factory, boolean binding) {
		// checks if the type is not an number and factory is consistent
		if (TreeType.OBJECTS.equals(getTreeType()) && factory != null) {
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
	 * Sets the tree data property of a data set for a chart is specified as an object.
	 * 
	 * @param value the object instance
	 */
	public void setTreeNativeObject(NativeObject value) {
		// set value. If null, removes key and then.
		setValue(Property.TREE, value);
		// sets data type checking if the key exists
		setValue(Property.CHARBA_TREE_TYPE, has(Property.TREE) ? TreeType.TREE : TreeType.UNKNOWN);
	}

	/**
	 * Returns he tree data property of a data set for a chart is specified as an object.
	 * 
	 * @return the object instance.
	 */
	public NativeObject getTreeNativeObject() {
		// checks if the type is a tree and factory is consistent
		if (TreeType.TREE.equals(getTreeType())) {
			// returns object
			return getValue(Property.TREE);
		}
		// returns a null object
		return null;
	}

	/**
	 * Sets the tree data property of a data set for a chart is specified as an object.
	 * 
	 * @param value the object instance
	 * @param <T> type of tree object
	 */
	public <T extends NativeObjectContainer> void setTreeObject(T value) {
		// set value. If null, removes key and then.
		setValue(Property.TREE, value);
		// sets data type checking if the key exists
		setValue(Property.CHARBA_TREE_TYPE, has(Property.TREE) ? TreeType.TREE : TreeType.UNKNOWN);
	}

	/**
	 * Returns he tree data property of a data set for a chart is specified as an object.
	 * 
	 * @param factory instance of factory to create the native object
	 * @param <T> type of tree object
	 * @return the object instance.
	 */
	public <T extends NativeObjectContainer> T getTreeObject(NativeObjectContainerFactory<T> factory) {
		// checks if the type is a tree and factory is consistent
		if (TreeType.TREE.equals(getTreeType()) && factory != null) {
			// returns object
			return factory.create(getValue(Property.TREE));
		}
		// returns a null object
		return null;
	}

	/**
	 * Sets the key of the object to use to get data value from a tree object.<br>
	 * It's needed ONLY when tree data is set by a list of objects.
	 * 
	 * @param key the key of the object to use to get data value from a tree object
	 */
	public void setKey(String key) {
		setValue(Property.KEY, key);
	}

	/**
	 * Sets the key of the object to use to get data value from a tree object.<br>
	 * It's needed ONLY when tree data is set by a list of objects.
	 * 
	 * @param key the key of the object to use to get data value from a tree object
	 */
	public void setKey(Key key) {
		setValue(Property.KEY, key);
	}

	/**
	 * Returns the key of the object to use to get data value from a tree object.<br>
	 * It's needed ONLY when tree data is set by a list of objects.
	 * 
	 * @return the key of the object to use to get data value from a tree object
	 */
	public String getKeyAsString() {
		return getValue(Property.KEY, Undefined.STRING);
	}

	/**
	 * Returns the key of the object to use to get data value from a tree object.<br>
	 * It's needed ONLY when tree data is set by a list of objects.
	 * 
	 * @return the key of the object to use to get data value from a tree object
	 */
	public Key getKey() {
		// gets string value
		String value = getKeyAsString();
		// returns creating the key
		return value != null ? Key.create(value) : null;
	}

	/**
	 * Sets the name of the key where the object key of leaf node of tree object is stored.<br>
	 * Used only when `tree` is an `object`, as hierarchical data.
	 * 
	 * @param key the name of the key where the object key of leaf node of tree object is stored.
	 */
	public void setTreeLeafKey(String key) {
		setValue(Property.TREE_LEAF_KEY, key);
	}

	/**
	 * Sets the name of the key where the object key of leaf node of tree object is stored.<br>
	 * Used only when `tree` is an `object`, as hierarchical data.
	 * 
	 * @param key the name of the key where the object key of leaf node of tree object is stored.
	 */
	public void setTreeLeafKey(Key key) {
		setValue(Property.TREE_LEAF_KEY, key);
	}

	/**
	 * Returns the name of the key where the object key of leaf node of tree object is stored.<br>
	 * Used only when `tree` is an `object`, as hierarchical data.
	 * 
	 * @return the name of the key where the object key of leaf node of tree object is stored.
	 */
	public String getTreeLeafKeyAsString() {
		return getValue(Property.TREE_LEAF_KEY, DEFAULT_TREE_LEAF_KEY);
	}

	/**
	 * Returns the name of the key where the object key of leaf node of tree object is stored.<br>
	 * Used only when `tree` is an `object`, as hierarchical data.
	 * 
	 * @return the name of the key where the object key of leaf node of tree object is stored.
	 */
	public Key getTreeLeafKey() {
		// gets string value
		String value = getTreeLeafKeyAsString();
		// returns creating the key
		return value != null ? Key.create(value) : null;
	}

	/**
	 * Sets multiple keys to add additional sums, on top of the `key` one, for scriptable options use.<br>
	 * It's needed ONLY when tree data is set by a list of objects.
	 * 
	 * @param sumKeys multiple keys to add additional sums, on top of the `key` one, for scriptable options use.
	 */
	public void setSumKeys(String... sumKeys) {
		setArrayValue(Property.SUM_KEYS, ArrayString.fromOrNull(sumKeys));
	}

	/**
	 * Sets multiple keys to add additional sums, on top of the `key` one, for scriptable options use.<br>
	 * It's needed ONLY when tree data is set by a list of objects.
	 * 
	 * @param sumKeys multiple keys to add additional sums, on top of the `key` one, for scriptable options use.
	 */
	public void setSumKeys(Key... sumKeys) {
		setArrayValue(Property.SUM_KEYS, ArrayString.fromOrNull(sumKeys));
	}

	/**
	 * Sets multiple keys to add additional sums, on top of the `key` one, for scriptable options use.<br>
	 * It's needed ONLY when tree data is set by a list of objects.
	 * 
	 * @param sumKeys multiple keys to add additional sums, on top of the `key` one, for scriptable options use.
	 */
	public void setSumKeys(Set<Key> sumKeys) {
		setArrayValue(Property.SUM_KEYS, sumKeys != null ? ArrayString.fromOrNull(sumKeys) : null);
	}

	/**
	 * Returns multiple keys to add additional sums, on top of the `key` one, for scriptable options use.<br>
	 * It's needed ONLY when tree data is set by a list of objects.
	 * 
	 * @return multiple keys to add additional sums, on top of the `key` one, for scriptable options use.
	 */
	public Set<String> getSumKeysAsString() {
		// retrieves the array
		ArrayString array = getArrayValue(Property.SUM_KEYS);
		// if the array is not consistent returns an empty list
		return array != null ? ArraySetHelper.set(array) : Collections.emptySet();
	}

	/**
	 * Returns multiple keys to add additional sums, on top of the `key` one, for scriptable options use.<br>
	 * It's needed ONLY when tree data is set by a list of objects.
	 * 
	 * @return multiple keys to add additional sums, on top of the `key` one, for scriptable options use.
	 */
	public Set<Key> getSumKeys() {
		// retrieves the array
		ArrayString array = getArrayValue(Property.SUM_KEYS);
		// if the array is not consistent returns an empty list
		return array != null ? ArraySetHelper.set(array, KEY_FACTORY) : Collections.emptySet();
	}

	/**
	 * Sets the keys of the object to use to group the values from a tree object.<br>
	 * It's needed ONLY when tree data is set by a list of objects.
	 * 
	 * @param groups the keys of the object to use to group the values from a tree object
	 */
	public void setGroups(String... groups) {
		setArrayValue(Property.GROUPS, ArrayString.fromOrNull(groups));
	}

	/**
	 * Sets the keys of the object to use to group the values from a tree object.<br>
	 * It's needed ONLY when tree data is set by a list of objects.
	 * 
	 * @param groups the keys of the object to use to group the values from a tree object
	 */
	public void setGroups(Key... groups) {
		setArrayValue(Property.GROUPS, ArrayString.fromOrNull(groups));
	}

	/**
	 * Sets the keys of the object to use to group the values from a tree object.<br>
	 * It's needed ONLY when tree data is set by a list of objects.
	 * 
	 * @param groups the keys of the object to use to group the values from a tree object
	 */
	public void setGroups(Set<Key> groups) {
		setArrayValue(Property.GROUPS, groups != null ? ArrayString.fromOrNull(groups) : null);
	}

	/**
	 * Returns the keys of the object to use to group the values from a tree object.<br>
	 * It's needed ONLY when tree data is set by a list of objects.
	 * 
	 * @return the keys of the object to use to group the values from a tree object
	 */
	public Set<String> getGroupsAsString() {
		// retrieves the array
		ArrayString array = getArrayValue(Property.GROUPS);
		// if the array is not consistent returns an empty list
		return array != null ? ArraySetHelper.set(array) : Collections.emptySet();
	}

	/**
	 * Returns the keys of the object to use to group the values from a tree object.<br>
	 * It's needed ONLY when tree data is set by a list of objects.
	 * 
	 * @return the keys of the object to use to group the values from a tree object
	 */
	public Set<Key> getGroups() {
		// retrieves the array
		ArrayString array = getArrayValue(Property.GROUPS);
		// if the array is not consistent returns an empty list
		return array != null ? ArraySetHelper.set(array, KEY_FACTORY) : Collections.emptySet();
	}

	/**
	 * Sets the fixed spacing among rectangles.
	 * 
	 * @param spacing the fixed spacing among rectangles
	 */
	public void setSpacing(double spacing) {
		setValue(Property.SPACING, spacing);
	}

	/**
	 * Returns the fixed spacing among rectangles.
	 * 
	 * @return the fixed spacing among rectangles
	 */
	public double getSpacing() {
		return getValue(Property.SPACING, DEFAULT_SPACING);
	}

	/**
	 * Sets <code>true</code> for rendering the rectangles from right to left.
	 * 
	 * @param rtl <code>true</code> for rendering the rectangles from right to left
	 */
	public void setRtl(boolean rtl) {
		setValue(Property.RTL, rtl);
	}

	/**
	 * Returns <code>true</code> for rendering the rectangles from right to left.
	 * 
	 * @return <code>true</code> for rendering the rectangles from right to left.
	 */
	public boolean isRtl() {
		return getValue(Property.RTL, DEFAULT_RTL);
	}

	/**
	 * Sets the border radius.
	 * 
	 * @param radius the border radius.
	 */
	public void setBorderRadius(int radius) {
		setValueAndAddToParent(Property.BORDER_RADIUS, Checker.positiveOrZero(radius));
	}

	/**
	 * Sets the border radius (in pixels).
	 * 
	 * @param borderRadius the border radius (in pixels).
	 */
	public void setBorderRadius(BarBorderRadius borderRadius) {
		setValueAndAddToParent(Property.BORDER_RADIUS, borderRadius);
	}

	/**
	 * Returns the border radius (in pixels).
	 * 
	 * @return the border radius (in pixels).
	 */
	public BarBorderRadius getBorderRadiusAsObject() {
		// checks if was stored as object
		if (isType(Property.BORDER_RADIUS, ObjectType.OBJECT)) {
			return BarBorderRadius.FACTORY.create(getValue(Property.BORDER_RADIUS));
		} else if (isType(Property.BORDER_RADIUS, ObjectType.NUMBER)) {
			// if here, the property is a number
			// then returns new border radius object
			return new BarBorderRadius(getBorderRadius());
		}
		// if here, the property is missing
		// then returns null
		return null;
	}

	/**
	 * Returns the border radius (in pixels).
	 * 
	 * @return the border radius (in pixels).
	 */
	public int getBorderRadius() {
		// checks if was stored as number
		if (isType(Property.BORDER_RADIUS, ObjectType.NUMBER)) {
			return getValue(Property.BORDER_RADIUS, Undefined.INTEGER);
		} else if (isType(Property.BORDER_RADIUS, ObjectType.OBJECT)) {
			// if here, the property is a object
			BarBorderRadius object = getBorderRadiusAsObject();
			// checks if there is the same value
			if (object != null && object.areValuesEquals()) {
				// the returns the same value
				// in whatever property
				return object.getTopLeft();
			}
		}
		// if here, the property is missing
		// then returns default
		return DEFAULT_BORDER_RADIUS;
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
		 * The tree property is set by {@link NativeObject}.
		 */
		TREE("tree"),
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

	// ---------------------------
	// CALLBACKS METHODS
	// ---------------------------

	/**
	 * Returns the border radius callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border radius callback, if set, otherwise <code>null</code>.
	 */
	public BorderRadiusCallback<DatasetContext> getBorderRadiusCallback() {
		return borderRadiusCallback;
	}

	/**
	 * Sets the border radius callback.
	 * 
	 * @param borderRadiusCallback the border radius callback.
	 */
	public void setBorderRadius(BorderRadiusCallback<DatasetContext> borderRadiusCallback) {
		// sets the callback
		this.borderRadiusCallback = borderRadiusCallback;
		// checks if callback is consistent
		if (borderRadiusCallback != null) {
			// adds the callback proxy function to java script object
			setValueAndAddToParent(Property.BORDER_RADIUS, borderRadiusCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.BORDER_RADIUS);
		}
	}

	/**
	 * Sets the border radius callback.
	 * 
	 * @param borderRadiusCallback the border radius callback.
	 */
	public void setBorderRadius(NativeCallback borderRadiusCallback) {
		// resets callback
		setBorderRadius((BorderRadiusCallback<DatasetContext>) null);
		// stores value
		setValueAndAddToParent(Property.BORDER_RADIUS, borderRadiusCallback);
	}

	/**
	 * Factory to create a keys from a native array, used for array lists.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InternalKeyFactory implements KeyFactory<Key> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.KeyFactory#create(java.lang.String)
		 */
		@Override
		public Key create(String value) {
			return Key.create(value);
		}

	}

	/**
	 * Factory to create a tree map data point from a native object, used for array container lists.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class DataPointFactory implements NativeObjectContainerFactory<TreeMapDataPoint> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client. commons.NativeObject)
		 */
		@Override
		public TreeMapDataPoint create(NativeObject nativeObject) {
			return new TreeMapDataPoint(nativeObject);
		}

	}

}