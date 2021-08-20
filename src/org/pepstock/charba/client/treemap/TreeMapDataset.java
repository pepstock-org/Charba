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
import java.util.Set;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayDoubleArray;
import org.pepstock.charba.client.commons.ArrayDoubleList;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayObjectContainerList;
import org.pepstock.charba.client.commons.ArraySetHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.KeyFactory;
import org.pepstock.charba.client.commons.NativeArrayContainerFactory;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.data.HoverFlexDataset;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.AbstractFont;
import org.pepstock.charba.client.options.IsFont;

/**
 * The treemap data set allows to specify the values for displaying hierarchical data using nested rectangles.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class TreeMapDataset extends HoverFlexDataset {

	/**
	 * Default border width, <b>{@value}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 0;
	/**
	 * Default color, <b>transparent</b>
	 */
	public static final String DEFAULT_COLOR = HtmlColor.TRANSPARENT.toRGBA();
	/**
	 * Default divider capstyle, <b>butt</b>
	 */
	public static final CapStyle DEFAULT_DIVIDER_CAP_STYLE = CapStyle.BUTT;
	/**
	 * Default divider color, <b>black</b>
	 */
	public static final String DEFAULT_DIVIDER_COLOR = HtmlColor.BLACK.toRGBA();
	/**
	 * Default divider dash offset, <b>{@value}</b>.
	 */
	public static final double DEFAULT_DIVIDER_DASH_OFFSET = 0;
	/**
	 * Default divider width, <b>{@value}</b>.
	 */
	public static final int DEFAULT_DIVIDER_WIDTH = 1;
	/**
	 * Default group dividers, <b>{@value}</b>.
	 */
	public static final boolean DEFAULT_GROUP_DIVIDER = false;
	/**
	 * Default group labels, <b>{@value}</b>.
	 */
	public static final boolean DEFAULT_GROUP_LABELS = true;
	/**
	 * Default spacing, <b>{@value}</b>.
	 */
	public static final double DEFAULT_SPACING = 0.5;
	/**
	 * Default right-to-left, <b>{@value}</b>.
	 */
	public static final boolean DEFAULT_RTL = false;

	// exception string message for setting data
	private static final String INVALID_SET_DATA_CALL = "'setData' method is not invokable by a treemap chart. Use 'setTree' or 'setTreeObjects' methods";
	// exception string message for getting data
	private static final String INVALID_GET_DATA_CALL = "'getData' method is not invokable by a treemap chart. Use 'getTree' or 'getTreeObjects' methods";
	// factory to create data points
	private static final DataPointFactory DATAPOINTS_FACTORY = new DataPointFactory();
	// factory to create dash items
	private static final DashFactory DASH_FACTORY = new DashFactory();
	// factory to create keys
	private static final InternalKeyFactory KEY_FACTORY = new InternalKeyFactory();

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the color function
	private final CallbackProxy<ProxyObjectCallback> colorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover color function
	private final CallbackProxy<ProxyObjectCallback> hoverColorCallbackProxy = JsHelper.get().newCallbackProxy();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		TREE("tree"),
		KEY("key"),
		GROUPS("groups"),
		COLOR("color"),
		HOVER_COLOR("hoverColor"),
		DIVIDER_CAP_STYLE("dividerCapStyle"),
		DIVIDER_COLOR("dividerColor"),
		DIVIDER_DASH("dividerDash"),
		DIVIDER_DASH_OFFSET("dividerDashOffset"),
		DIVIDER_WIDTH("dividerWidth"),
		FONT("font"),
		HOVER_FONT("hoverFont"),
		GROUP_DIVIDERS("groupDividers"),
		GROUP_LABELS("groupLabels"),
		SPACING("spacing"),
		RTL("rtl"),
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

	// color callback instance
	private ColorCallback<DatasetContext> colorCallback = null;
	// hover color callback instance
	private ColorCallback<DatasetContext> hoverColorCallback = null;
	// font instance
	private final Font font;
	// font instance
	private final Font hoverFont;

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
		// gets inner elements
		// FONT
		this.font = new Font(Defaults.get().getGlobal().getFont(), getValue(Property.FONT));
		// checks if already added
		if (!has(Property.FONT)) {
			// sets the font
			setValue(Property.FONT, this.font);
		}
		// HOVER FONT
		this.hoverFont = new Font(Defaults.get().getGlobal().getFont(), getValue(Property.HOVER_FONT));
		// checks if already added
		if (!has(Property.HOVER_FONT)) {
			// sets the font
			setValue(Property.HOVER_FONT, this.hoverFont);
		}
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.colorCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsColor(createContext(context), getColorCallback(), DEFAULT_COLOR, false));
		// sets function to proxy callback in order to invoke the java interface
		this.hoverColorCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsColor(createContext(context), getHoverColorCallback(), DEFAULT_COLOR, false));
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
	 * Returns the the font object.
	 * 
	 * @return the font object.
	 */
	public IsFont getFont() {
		return font;
	}

	/**
	 * Returns the the font object when hovered.
	 * 
	 * @return the font object when hovered
	 */
	public IsFont getHoverFont() {
		return hoverFont;
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
	 * Sets the the keys of the object to use to group the values from a tree object.<br>
	 * It's needed ONLY when tree data is set by a list of objects.
	 * 
	 * @param groups the the keys of the object to use to group the values from a tree object
	 */
	public void setGroups(String... groups) {
		setArrayValue(Property.GROUPS, ArrayString.fromOrNull(groups));
	}

	/**
	 * Sets the the keys of the object to use to group the values from a tree object.<br>
	 * It's needed ONLY when tree data is set by a list of objects.
	 * 
	 * @param groups the the keys of the object to use to group the values from a tree object
	 */
	public void setGroups(Key... groups) {
		setArrayValue(Property.GROUPS, ArrayString.fromOrNull(groups));
	}

	/**
	 * Sets the the keys of the object to use to group the values from a tree object.<br>
	 * It's needed ONLY when tree data is set by a list of objects.
	 * 
	 * @param groups the the keys of the object to use to group the values from a tree object
	 */
	public void setGroups(Set<Key> groups) {
		setArrayValue(Property.GROUPS, groups != null ? ArrayString.fromOrNull(groups) : null);
	}

	/**
	 * Returns the the keys of the object to use to group the values from a tree object.<br>
	 * It's needed ONLY when tree data is set by a list of objects.
	 * 
	 * @return the the keys of the object to use to group the values from a tree object
	 */
	public Set<String> getGroupsAsString() {
		// retrieves the array
		ArrayString array = getArrayValue(Property.GROUPS);
		// if the array is not consistent returns an empty list
		return array != null ? ArraySetHelper.set(array) : Collections.emptySet();
	}

	/**
	 * Returns the the keys of the object to use to group the values from a tree object.<br>
	 * It's needed ONLY when tree data is set by a list of objects.
	 * 
	 * @return the the keys of the object to use to group the values from a tree object
	 */
	public Set<Key> getGroups() {
		// retrieves the array
		ArrayString array = getArrayValue(Property.GROUPS);
		// if the array is not consistent returns an empty list
		return array != null ? ArraySetHelper.set(array, KEY_FACTORY) : Collections.emptySet();
	}

	// ---------------------------
	// STYLE METHODS
	// ---------------------------

	/**
	 * Sets the color of the rectangle.
	 * 
	 * @param color the color of the rectangle
	 */
	public void setColor(IsColor... color) {
		// resets callback
		setColor((ColorCallback<DatasetContext>) null);
		// stores value
		setColors(Property.COLOR, color);
	}

	/**
	 * Sets the color of the rectangle.
	 * 
	 * @param color the color of the rectangle
	 */
	public void setColor(String... color) {
		// resets callback
		setColor((ColorCallback<DatasetContext>) null);
		// stores value
		setColors(Property.COLOR, color);
	}

	/**
	 * Returns the color of the rectangle.
	 * 
	 * @return list of the color of the rectangle
	 */
	public List<String> getColorAsString() {
		// checks if the property is a color
		if (isType(Property.COLOR, ObjectType.STRING) && getColorCallback() == null) {
			ArrayString array = getColors(Property.COLOR, DEFAULT_COLOR);
			return ArrayListHelper.list(array);
		}
		// if here, the property is not a string
		// or the property is missing or a gradient
		// returns empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the color of the rectangle.
	 * 
	 * @return list of the color of the rectangle
	 */
	public List<IsColor> getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the hover color of the rectangle.
	 * 
	 * @param hoverColor the hover color of the rectangle
	 */
	public void setHoverColor(IsColor... hoverColor) {
		// resets callback
		setHoverColor((ColorCallback<DatasetContext>) null);
		// stores value
		setColors(Property.HOVER_COLOR, hoverColor);
	}

	/**
	 * Sets the hover color of the rectangle.
	 * 
	 * @param hoverColor the hover color of the rectangle
	 */
	public void setHoverColor(String... hoverColor) {
		// resets callback
		setHoverColor((ColorCallback<DatasetContext>) null);
		// stores value
		setColors(Property.HOVER_COLOR, hoverColor);
	}

	/**
	 * Returns the hover color of the rectangle.
	 * 
	 * @return list of the hover color of the rectangle
	 */
	public List<String> getHoverColorAsString() {
		// checks if the property is a color
		if (isType(Property.HOVER_COLOR, ObjectType.STRING) && getHoverColorCallback() == null) {
			ArrayString array = getColors(Property.HOVER_COLOR, DEFAULT_COLOR);
			return ArrayListHelper.list(array);
		}
		// if here, the property is not a string
		// or the property is missing or a gradient
		// returns empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the hover color of the rectangle.
	 * 
	 * @return list of the hover color of the rectangle
	 */
	public List<IsColor> getHoverColor() {
		return ColorBuilder.parse(getHoverColorAsString());
	}

	/**
	 * Sets how the end points of every line are drawn.
	 * 
	 * @param dividerCapStyle how the end points of every line are drawn.
	 */
	public void setDividerCapStyle(CapStyle dividerCapStyle) {
		setValue(Property.DIVIDER_CAP_STYLE, dividerCapStyle);
	}

	/**
	 * Returns how the end points of every line are drawn.
	 * 
	 * @return how the end points of every line are drawn.
	 */
	public CapStyle getDividerCapStyle() {
		return getValue(Property.DIVIDER_CAP_STYLE, CapStyle.values(), DEFAULT_DIVIDER_CAP_STYLE);
	}

	/**
	 * Sets the divider color of the rectangle.
	 * 
	 * @param dividerColor the divider color of the rectangle
	 */
	public void setDividerColor(IsColor... dividerColor) {
		setColors(Property.DIVIDER_COLOR, dividerColor);
	}

	/**
	 * Sets the divider color of the rectangle.
	 * 
	 * @param dividerColor the divider color of the rectangle
	 */
	public void setDividerColor(String... dividerColor) {
		setColors(Property.DIVIDER_COLOR, dividerColor);
	}

	/**
	 * Returns the divider color of the rectangle.
	 * 
	 * @return list of the divider color of the rectangle
	 */
	public List<String> getDividerColorAsString() {
		ArrayString array = getColors(Property.COLOR, DEFAULT_COLOR);
		return ArrayListHelper.list(array);
	}

	/**
	 * Returns the divider color of the rectangle.
	 * 
	 * @return list of the divider color of the rectangle
	 */
	public List<IsColor> getDividerColor() {
		return ColorBuilder.parse(getDividerColorAsString());
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param dividerDash the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public void setDividerDash(Dash... dividerDash) {
		setArrayValue(Property.DIVIDER_DASH, ArrayDoubleArray.fromOrNull(dividerDash));
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public List<Dash> getDividerDash() {
		ArrayDoubleArray array = getArrayValue(Property.DIVIDER_DASH);
		// returns dash data
		return ArrayListHelper.list(array, DASH_FACTORY);
	}

	/**
	 * Sets the line dash pattern offset of the divider.
	 * 
	 * @param dividerDashOffset the line dash pattern offset of the divider
	 */
	public void setDividerDashOffset(double dividerDashOffset) {
		setValue(Property.DIVIDER_DASH_OFFSET, dividerDashOffset);
	}

	/**
	 * Returns the line dash pattern offset of the divider.
	 * 
	 * @return the line dash pattern offset of the divider
	 */
	public double getDividerDashOffset() {
		return getValue(Property.DIVIDER_DASH_OFFSET, DEFAULT_DIVIDER_DASH_OFFSET);
	}

	/**
	 * Sets the width of the divider line in pixels.
	 * 
	 * @param dividerWidth the width of the divider line in pixels.
	 */
	public void setDividerWidth(int... dividerWidth) {
		setWidths(Property.DIVIDER_WIDTH, dividerWidth);
	}

	/**
	 * Returns the width of the divider line in pixels.
	 * 
	 * @return the width of the divider line in pixels.
	 */
	public List<Integer> getDividerWidth() {
		ArrayInteger array = getWidths(Property.DIVIDER_WIDTH, DEFAULT_DIVIDER_WIDTH);
		return ArrayListHelper.list(array);
	}

	/**
	 * If <code>false</code>, the divider between groups are not drawn.
	 * 
	 * @param groupDivider if <code>false</code>, the divider between groups are not drawn
	 */
	public void setGroupDividers(boolean groupDivider) {
		setValue(Property.GROUP_DIVIDERS, groupDivider);
	}

	/**
	 * If <code>false</code>, the divider between groups are not drawn.
	 * 
	 * @return if <code>false</code>, the divider between groups are not drawn
	 */
	public boolean isGroupDividers() {
		return getValue(Property.GROUP_DIVIDERS, DEFAULT_GROUP_DIVIDER);
	}

	/**
	 * If <code>false</code>, the labels groups are not drawn.
	 * 
	 * @param groupLabels if <code>false</code>, the labels groups are not drawn
	 */
	public void setGroupLabels(boolean groupLabels) {
		setValue(Property.GROUP_LABELS, groupLabels);
	}

	/**
	 * If <code>false</code>, the labels groups are not drawn.
	 * 
	 * @return if <code>false</code>, the labels groups are not drawn
	 */
	public boolean isGroupLabels() {
		return getValue(Property.GROUP_LABELS, DEFAULT_GROUP_LABELS);
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

	// ---------------------------
	// CALLBACKS METHODS
	// ---------------------------

	/**
	 * Returns the color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the color callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<DatasetContext> getColorCallback() {
		return colorCallback;
	}

	/**
	 * Sets the color callback.
	 * 
	 * @param colorCallback the color callback.
	 */
	public void setColor(ColorCallback<DatasetContext> colorCallback) {
		// sets the callback
		this.colorCallback = colorCallback;
		// checks if callback is consistent
		if (colorCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.COLOR, colorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.COLOR);
		}
	}

	/**
	 * Returns the hover color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the hover color callback, if set, otherwise <code>null</code>.
	 */
	public ColorCallback<DatasetContext> getHoverColorCallback() {
		return hoverColorCallback;
	}

	/**
	 * Sets the hover color callback.
	 * 
	 * @param hoverColorCallback the hover color callback.
	 */
	public void setHoverColor(ColorCallback<DatasetContext> hoverColorCallback) {
		// sets the callback
		this.hoverColorCallback = hoverColorCallback;
		// checks if callback is consistent
		if (hoverColorCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.HOVER_COLOR, hoverColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.HOVER_COLOR);
		}
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

	/**
	 * Object to map font options for treemap dataset configuration.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class Font extends AbstractFont {

		/**
		 * Creates a font to use for plugin.
		 * 
		 * @param defaultValues default provider
		 * @param nativeObject native object to map java script properties
		 */
		Font(IsDefaultFont defaultValues, NativeObject nativeObject) {
			super(defaultValues, nativeObject);
		}

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

	/**
	 * Factory to create a {@link Dash} from a native array, used for array container lists.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class DashFactory implements NativeArrayContainerFactory<ArrayDouble, Dash> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeArrayContainerFactory#create(org.pepstock.charba.client.commons.Array)
		 */
		@Override
		public Dash create(ArrayDouble nativeArray) {
			return new Dash(nativeArray);
		}

	}
}