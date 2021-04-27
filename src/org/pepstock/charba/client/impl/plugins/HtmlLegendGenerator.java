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
package org.pepstock.charba.client.impl.plugins;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.HtmlLegendItemCallback;
import org.pepstock.charba.client.callbacks.HtmlLegendTitleCallback;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.colors.tiles.TilesFactory;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.configuration.Legend;
import org.pepstock.charba.client.configuration.LegendLabels;
import org.pepstock.charba.client.configuration.LegendTitle;
import org.pepstock.charba.client.dom.DOMBuilder;
import org.pepstock.charba.client.dom.elements.Div;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.dom.elements.Span;
import org.pepstock.charba.client.dom.elements.Table;
import org.pepstock.charba.client.dom.elements.TableCell;
import org.pepstock.charba.client.dom.elements.TableRow;
import org.pepstock.charba.client.dom.enums.BorderStyle;
import org.pepstock.charba.client.dom.enums.Display;
import org.pepstock.charba.client.dom.enums.Repetition;
import org.pepstock.charba.client.dom.enums.TextBaseline;
import org.pepstock.charba.client.dom.enums.TextDecoration;
import org.pepstock.charba.client.dom.enums.Unit;
import org.pepstock.charba.client.dom.safehtml.SafeHtml;
import org.pepstock.charba.client.dom.safehtml.SafeHtmlBuilder;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.enums.TextDirection;
import org.pepstock.charba.client.items.DatasetElement;
import org.pepstock.charba.client.items.DatasetElementOptions;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.LegendItem;
import org.pepstock.charba.client.items.LegendLabelItem;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Legend callback implementation to override the standard HTML format of CHART.JS legend.<br>
 * This HTML is usable only by {@link HtmlLegend} plugin.<br>
 * This is HTML structure of the legend:<br>
 * <br>
 * <code>
 * &lt;TABLE&gt;
 *   &lt;TR&gt;
 *     &lt;TD&gt;
 *       &lt;TABLE&gt;
 *         &lt;TR&gt;
 *           &lt;TD&gt;
 *             &lt;DIV legend title&gt;
 *           &lt;/TD&gt;
 *         &lt;/TR&gt;
 *       &lt;/TABLE&gt;
 *     &lt;/TD&gt;
 *   &lt;/TR&gt;
 *   &lt;TR&gt;
 *     &lt;TD&gt;
 *       &lt;TABLE&gt;
 *         &lt;TR&gt;
 *           &lt;TD&gt;
 *             &lt;DIV color for legend item&gt;
 *             &lt;DIV label for legend item&gt;
 *           &lt;/TD&gt;
 *           &lt;TD&gt;
 *             &lt;DIV color for legend item&gt;
 *             &lt;DIV label for legend item&gt;
 *           &lt;/TD&gt;
 *         &lt;/TR&gt;
 *       &lt;/TABLE&gt;
 *     &lt;/TD&gt;
 *   &lt;/TR&gt;
 * &lt;/TABLE&gt;
 * </code> <br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class HtmlLegendGenerator {

	// internal comparator to sort legend item by own index
	private static final Comparator<LegendLabelItem> COMPARATOR = (LegendLabelItem o1, LegendLabelItem o2) -> Double.compare(o1.getDatasetIndex(), o2.getDatasetIndex()) + Double.compare(o1.getIndex(), o2.getIndex());
	// internal comparator to sort legend item by reverse own index
	private static final Comparator<LegendLabelItem> REVERSE_COMPARATOR = (LegendLabelItem o1, LegendLabelItem o2) -> Double.compare(o2.getDatasetIndex(), o1.getDatasetIndex()) + Double.compare(o2.getIndex(), o1.getIndex());
	// default radius value
	private static final double DEFAULT_RADIUS = Defaults.get().getGlobal().getElements().getPoint().getRadius();
	// instance text generator for legend items
	private final HtmlLegendTextGenerator<LegendLabelItem, HtmlLegendItemCallback> legendItemTextGenerator = new HtmlLegendTextGenerator<>();
	// instance text generator for legend title
	private final HtmlLegendTextGenerator<LegendTitle, HtmlLegendTitleCallback> legendTitleTextGenerator = new HtmlLegendTextGenerator<>();

	/**
	 * Creates HTML representation of legend.
	 * 
	 * @param chart chart instance
	 * @return HTML legend representation as SafeHTML
	 */
	SafeHtml generateLegend(IsChart chart) {
		// checks if chart is consistent
		IsChart.checkIfValid(chart);
		// creates a HTML element
		// as container of result
		// needed to get innerHTML property
		Span container = DOMBuilder.get().createSpanElement();
		// invokes the creation of legend
		container.appendChild(buildLegend(chart));
		// appends to safe html builder the innerHTML
		// of container
		// returns as safe html
		return SafeHtmlBuilder.create().appendHtmlConstant(container.getInnerHTML()).toSafeHtml();
	}

	/**
	 * Builds the legend for the passed chart using {@link Table} as model.
	 * 
	 * @param chart chart instance related to legend to build
	 * @return the table element instance with the complete legend
	 */
	private Table buildLegend(IsChart chart) {
		HtmlLegendOptions options = HtmlLegend.get().getPluginOptions().get(chart.getId());
		// gets max columns for legend
		int maxColumns = Math.max(1, options.getMaximumLegendColumns());
		// gets legend
		final Legend legend = chart.getOptions().getLegend();
		// gets legend and legend labels instances
		final LegendLabels legendLabels = legend.getLabels();
		// creates table as result
		final Table mainTable = DOMBuilder.get().createTableElement();
		// resets padding and spacing
		mainTable.setCellPadding(String.valueOf(0));
		mainTable.setCellSpacing(String.valueOf(0));
		mainTable.getStyle().setBorderSpacing(Unit.PX.format(0));
		// sets horizontal alignment
		mainTable.setAlign(legend.getAlign().getHorizontalAlignmentValue());
		// its has got only 1 colum and 2 row
		// title - table
		// legendItem - table
		// manages finally the legend title
		final Table titleTable = buildTitleCell(chart, legend, options.getLegendTitleCallback());
		// checks if title is consistent
		if (titleTable != null) {
			// title row for title
			TableRow titleRow = DOMBuilder.get().createTableRowElement();
			// adds to title row
			mainTable.appendChild(titleRow);
			// title column
			TableCell titleCell = DOMBuilder.get().createTableCellElement();
			// adds to title
			titleRow.appendChild(titleCell);
			// adds title table column
			titleCell.appendChild(titleTable);
		}
		// creates table as result
		final Table table = DOMBuilder.get().createTableElement();
		// resets padding and spacing
		table.setCellPadding(String.valueOf(0));
		table.setCellSpacing(String.valueOf(0));
		table.getStyle().setBorderSpacing(Unit.PX.format(0));
		// sets horizontal alignment
		table.setAlign(legend.getAlign().getHorizontalAlignmentValue());
		// retrieves the list of legend items
		final List<LegendLabelItem> legendItems = extractLegendItems(chart);
		// checks if there is any items
		if (!legendItems.isEmpty()) {
			// checks if legend must be created in reverse mode
			if (legend.isReverse()) {
				Collections.sort(legendItems, REVERSE_COMPARATOR);
			} else if (legendLabels.getItemSortCallback() == null) {
				// invokes the standard sort if ONLY there is not any sort callback
				Collections.sort(legendItems, COMPARATOR);
			}
			// sets index to check when to have more lines
			int index = 0;
			// instance of current row
			// where adds new columns for each legend
			TableRow current = null;
			// gets html legend item wrapper
			final HtmlLegendItem htmlLegendItem = new HtmlLegendItem(chart);
			// scans all legend items
			for (LegendLabelItem item : legendItems) {
				// checks if new row must be created
				// checking max legend items per row
				if (index % maxColumns == 0) {
					// new row
					TableRow newRow = DOMBuilder.get().createTableRowElement();
					// appends to table
					table.appendChild(newRow);
					// sets the current one
					current = newRow;
				}
				// creates the element id for legend item
				HtmlLegendId legendId = HtmlLegendId.get(chart, item);
				// set legend item to html legend id
				htmlLegendItem.setLegendItem(item);
				// creates and adds the cell part
				// related to color/box to design in the legend
				current.appendChild(buildColorCell(chart, legendId, htmlLegendItem));
				// creates and adds the cell part
				// related to label with legend item text
				current.appendChild(buildLabelCell(chart, legendId, item, options.getLegendItemCallback()));
				// increments the amount of legend item index
				index++;
			}
		}
		// checks if to return the table with title or not
		if (titleTable != null) {
			// legend items row 
			TableRow legendItemsRow = DOMBuilder.get().createTableRowElement();
			// adds to legend items
			mainTable.appendChild(legendItemsRow);
			// legend items column
			TableCell legendItemsCell = DOMBuilder.get().createTableCellElement();
			// adds to tile
			legendItemsRow.appendChild(legendItemsCell);
			// adds legends items
			legendItemsCell.appendChild(table);
			// returns the legend
			return mainTable;
		}
		// if here, the title is not present
		// then returns ONLY the table of legend items
		return table;
	}

	/**
	 * Builds a {@link TableCell} which should contains and represents the title of legend.
	 * 
	 * @param chart chart instance related to legend to build
	 * @param legend configuration legend instance
	 * @param callback callback instance which can be implemented to change the text of legend's title, as HTML
	 * @return the table element which contains the title or <code>null</code> if the title is not displayed
	 */
	private Table buildTitleCell(IsChart chart, Legend legend, HtmlLegendTitleCallback callback) {
		// checks if the title must be shown
		if (legend.getTitle().isDisplay()) {
			// creates table as result
			Table tableTitle = DOMBuilder.get().createTableElement();
			// legend title
			LegendTitle title = legend.getTitle();
			// instance of title row
			// new row
			TableRow titleRow = DOMBuilder.get().createTableRowElement();
			// appends to table
			tableTitle.appendChild(titleRow);
			// result title cell
			TableCell titleCell = DOMBuilder.get().createTableCellElement();
			titleRow.appendChild(titleCell);
			titleCell.setAlign(TextAlign.CENTER.value());
			titleCell.setVAlign(TextBaseline.MIDDLE.value());
			titleCell.getStyle().setPaddingTop(Unit.PX.format(title.getPadding().getTop()));
			titleCell.getStyle().setPaddingBottom(Unit.PX.format(title.getPadding().getBottom()));
			// creates inner HTML element
			// where to apply the title
			Div titleText = legendTitleTextGenerator.createTextElement(chart, title, callback);
			titleCell.appendChild(titleText);
			// styling the cell with mandatory values
			titleText.getStyle().setFont(Utilities.toCSSFontProperty(title.getFont()));
			titleText.getStyle().setColor(title.getColor().toRGBA());
			// checks text direction
			if (legend.isRtl() || TextDirection.RIGHT_TO_LEFT.equals(legend.getTextDirection())) {
				titleText.getStyle().setDirection(TextDirection.RIGHT_TO_LEFT.value());
			} else {
				titleText.getStyle().setDirection(TextDirection.LEFT_TO_RIGHT.value());
			}
			// returns the table of the title
			return tableTitle;
		}
		// title is not displayed
		// the returns null
		return null;
	}

	/**
	 * Extracts all legend items which must be rendered in the legend.
	 * 
	 * @param chart chart instance related to legend to build
	 * @return list of legend items to render
	 */
	private List<LegendLabelItem> extractLegendItems(IsChart chart) {
		// checks if chart is consistent
		final IsChart internalChart = IsChart.checkAndGetIfConsistent(chart);
		// result list
		final List<LegendLabelItem> result = new LinkedList<>();
		// gets legend and legend labels instances
		final Legend legend = chart.getOptions().getLegend();
		final LegendLabels legendLabels = legend.getLabels();
		// temporary list of legend items
		List<LegendLabelItem> legendItems;
		// default list of legend items
		List<LegendLabelItem> defaultLegendItems = Defaults.get().generateLabels(chart);
		// ---------------
		// Labels callback
		// ---------------
		// checks if there is a configured legend labels callback
		if (legendLabels.getLabelsCallback() != null) {
			// if here, there is a generate legend labels callback
			// and then it will be called
			legendItems = legendLabels.getLabelsCallback().generateLegendLabels(chart, defaultLegendItems);
		} else {
			// no callback then it will use the default ones
			legendItems = defaultLegendItems;
		}
		// stores the legend items
		HtmlLegend.get().getPluginLegendLabelsItems().put(chart.getId(), legendItems);
		// ---------------
		// Filter callback
		// ---------------
		// checks if there is a filter legend callback
		if (legendLabels.getFilterCallback() != null) {
			// if here, the filter callback is invoked
			// scanning all legend items
			for (LegendLabelItem item : legendItems) {
				// invokes the filter callback
				if (legendLabels.getFilterCallback().onFilter(chart, item)) {
					// if here, the filter accept the legend item
					result.add(item);
				}
			}
		} else {
			// if here, there is not any filter callback
			// and adds all legend items retrieved previously
			result.addAll(legendItems);
		}
		// ---------------
		// Sort callback
		// ---------------
		// checks if there is a sort legend callback
		if (legendLabels.getItemSortCallback() != null) {
			Comparator<LegendItem> sortComparator = (LegendItem o1, LegendItem o2) -> legendLabels.getItemSortCallback().onItemSort(internalChart, o1, o2);
			// sorts the labels using the legend configuration
			Collections.sort(result, sortComparator);
		}
		// then returns the sorted result
		return result;
	}

	/**
	 * Builds a {@link TableCell} which should contains and represents the color of dataset.
	 * 
	 * @param chart chart instance related to legend to build
	 * @param legendId element id for {@link TableCell}
	 * @param htmlLegendItem legend item to show in the color cell element
	 * @return a {@link TableCell} which should contains and represents the color of dataset
	 */
	private TableCell buildColorCell(IsChart chart, HtmlLegendId legendId, HtmlLegendItem htmlLegendItem) {
		// gets legend label item
		LegendLabelItem item = htmlLegendItem.getLegendItem();
		// result color cell
		final TableCell colorCell = DOMBuilder.get().createTableCellElement();
		// gets legend and legend labels instances
		Legend legend = chart.getOptions().getLegend();
		LegendLabels legendLabels = legend.getLabels();
		// styling the cell
		colorCell.setId(legendId.getIdForColor());
		colorCell.setVAlign(TextBaseline.TOP.value());
		colorCell.getStyle().setMarginRight(Unit.PX.format((int)(legendLabels.getPadding() / 2D)));
		colorCell.getStyle().setMarginBottom(Unit.PX.format(legendLabels.getPadding()));
		colorCell.getStyle().setDisplay(Display.BLOCK);
		colorCell.getStyle().setBorderSpacing(Unit.PX.format(0));
		// creates inner HTML element
		// where to apply the color
		Div color = DOMBuilder.get().createDivElement();
		colorCell.appendChild(color);
		// calculates the border width to remove to size of color
		// because the size of border will be added to element
		// applying the border afterwards
		int borderWidthToRemove = Math.max(0, item.getLineWidth());
		int width = legendLabels.getBoxWidth() - borderWidthToRemove;
		int height = legendLabels.getBoxHeight() - borderWidthToRemove;
		// styling the cell with mandatory values
		color.getStyle().setDisplay(Display.BLOCK);
		color.getStyle().setWidth(Unit.PX.format(width));
		color.getStyle().setHeight(Unit.PX.format(height));
		// checks if must apply point style
		if (!legendLabels.isUsePointStyle()) {
			// applies border radius
			colorCell.getStyle().setBorderRadius(Utilities.toCSSBorderRadiusProperty(item.getBorderRadius()));
			// applies the background color
			applyBackgroundColor(chart, item, color, width, height);
			// applies the border width
			boolean applyBorderColor = applyBorderWidth(item, colorCell);
			// checks if must be applied the color
			if (applyBorderColor) {
				// applies the border color
				applyBorderColor(chart, item, colorCell, width, height);
			}
		} else {
			// if here, it must show the legend point styles
			applyPointStyle(chart, htmlLegendItem, color, width, height);
		}
		return colorCell;
	}

	/**
	 * Builds a {@link TableCell} which should contains and represents the label of dataset.
	 * 
	 * @param chart chart instance related to legend to build
	 * @param legendId element id for {@link TableCell}
	 * @param item legend item to show in the color cell element
	 * @param callback callback instance which can be implemented to change the text of legend for a specific item, as HTML
	 * @return a {@link TableCell} which should contains and represents the label of dataset
	 */
	private TableCell buildLabelCell(IsChart chart, HtmlLegendId legendId, LegendLabelItem item, HtmlLegendItemCallback callback) {
		// result label cell
		final TableCell labelCell = DOMBuilder.get().createTableCellElement();
		// gets legend and legend labels instances
		Legend legend = chart.getOptions().getLegend();
		LegendLabels legendLabels = legend.getLabels();
		// styling the cell
		labelCell.setId(legendId.getIdForLabel());
		labelCell.setVAlign(TextBaseline.MIDDLE.value());
		labelCell.getStyle().setPaddingRight(Unit.PX.format(legendLabels.getPadding()));
		labelCell.getStyle().setPaddingBottom(Unit.PX.format(legendLabels.getPadding()));
		// creates inner HTML element
		// where to apply the label
		Div label = legendItemTextGenerator.createTextElement(chart, item, callback);
		labelCell.appendChild(label);
		// styling the cell with mandatory values
		label.getStyle().setFont(Utilities.toCSSFontProperty(legendLabels.getFont()));
		label.getStyle().setTextAlign(legendLabels.getTextAlign().value());
		// gets color
		IsColor fontColor = item.getFontColor();
		// stores font color
		label.getStyle().setColor(fontColor != null ? fontColor.toRGBA() : legendLabels.getColor().toRGBA());
		// checks text direction
		if (legend.isRtl() || TextDirection.RIGHT_TO_LEFT.equals(legend.getTextDirection())) {
			label.getStyle().setDirection(TextDirection.RIGHT_TO_LEFT.value());
		} else {
			label.getStyle().setDirection(TextDirection.LEFT_TO_RIGHT.value());
		}
		// checks if item is hidden
		if (item.isHidden(chart)) {
			// if hidden, apply a specific text decoration
			label.getStyle().setTextDecoration(TextDecoration.LINE_THROUGH);
		}
		return labelCell;
	}

	/**
	 * Applies the background color to the color element.
	 * 
	 * @param chart chart instance related to legend to build
	 * @param htmlLegendItem legend item to map in the background color
	 * @param color DIV element where to apply the background color
	 * @param width width to use to apply background color
	 * @param height height to use to apply background color
	 */
	private void applyPointStyle(IsChart chart, HtmlLegendItem htmlLegendItem, Div color, int width, int height) {
		// gets legend label item
		LegendLabelItem item = htmlLegendItem.getLegendItem();
		// checks if point style is an image
		if (item.isPointStyleAsImage()) {
			// gets point style image
			Img image = item.getPointStyleAsImage();
			// if here, apply the point style as image
			String imageAsCss = Utilities.toCSSBackgroundProperty(image);
			// applies the point style as background to color element
			color.getStyle().setBackground(imageAsCss);
			// sets the image size to color element
			color.getStyle().setWidth(Unit.PX.format(image.getWidth()));
			color.getStyle().setHeight(Unit.PX.format(image.getHeight()));
		} else {
			// calculated size which is ALWAYS a square for point styles
			int size = Math.min(width, height);
			// calculated a radius, predefined by size
			double radius = (size - 2) / 2D;
			// here is searching for radius set to data set level
			DatasetElementOptions datasetViewItem = lookForDatasetMetaItem(chart, item);
			// checks if data set item is consistent
			if (datasetViewItem != null) {
				// if data set item is found
				// gets the radius, taking the MAX value
				// otherwise it could be small
				radius = Math.max(datasetViewItem.getRadius(), radius);
			}
			// checks with default radius
			// if consistent
			if (Checker.isNegative(radius) || Undefined.is(radius)) {
				radius = DEFAULT_RADIUS;
			}
			// recalculate size
			size = (int) (radius * 2) + 2;
			// update the HTML legend item setting the size and calculated radius
			htmlLegendItem.setSize(size);
			htmlLegendItem.setRadius(radius);
			// invokes tiles factory to get a pattern as string
			// of point style
			String pattern = TilesFactory.createHtmlLegendItem(htmlLegendItem);
			// checks if the result of tile factory is consistent
			if (pattern != null && pattern.trim().length() > 0) {
				// transforms pattern in the CSS property and
				String patternAsCss = Utilities.toCSSBackgroundProperty(pattern, Repetition.NO_REPEAT);
				// applies the point style as background to color element
				color.getStyle().setBackground(patternAsCss);
				// sets the size to color element
				color.getStyle().setWidth(Unit.PX.format(size));
				color.getStyle().setHeight(Unit.PX.format(size));
			} else {
				// if here, inconsistent point style representation
				// applies the background color
				applyBackgroundColor(chart, item, color, width, height);
			}
		}
	}

	/**
	 * Returns a data set element options instance using the legend item locator (data set or data index) or <code>null</code> if not found.
	 * 
	 * @param chart chart instance
	 * @param item legend item to use as data set locator
	 * @return a data set element options instance using the legend item locator (data set or data index) or <code>null</code> if not found.
	 */
	private DatasetElementOptions lookForDatasetMetaItem(IsChart chart, LegendItem item) {
		// prepares the data set item instance
		DatasetItem datasetItem = null;
		// item index set to 0 for data set index locator
		int itemIndex = 0;
		// based on the legend item location
		if (Undefined.isNot(item.getDatasetIndex())) {
			// retrieves the data set set item by data set index
			datasetItem = chart.getDatasetItem(item.getDatasetIndex());
		} else if (Undefined.isNot(item.getIndex())) {
			// if here is looking for data index then it uses
			// the first data set
			datasetItem = chart.getDatasetItem(0);
			// and gets the data set index to use
			itemIndex = item.getIndex();
		}
		// checks if the searching of data set item is consistent
		// with the locator
		if (datasetItem != null && datasetItem.getElements().size() > itemIndex) {
			// gets data set element by calculated index
			DatasetElement datasetElement = datasetItem.getElements().get(itemIndex);
			// checks if consistent
			if (datasetElement != null) {
				// returns the options of element
				return datasetElement.getOptions();
			}
		}
		// if here, the locator is not able to get the right data set item
		// then returns null
		return null;
	}

	/**
	 * Applies the background color to the color element.
	 * 
	 * @param chart chart instance related to legend to build
	 * @param item legend item to map in the background color
	 * @param color DIV element where to apply the background color
	 * @param width width to use to apply background color
	 * @param height height to use to apply background color
	 */
	private void applyBackgroundColor(IsChart chart, LegendLabelItem item, Div color, int width, int height) {
		// checks if fill color has been set
		if (item.isFillStyleAsColor()) {
			// if here, apply the fill color as background color
			color.getStyle().setBackgroundColor(item.getFillStyle().toRGBA());
		} else if (item.isFillStyleAsPattern()) {
			// gets the pattern by legend item
			Pattern pattern = item.getFillStyleAsPattern();
			// checks if not consistent
			if (pattern == null) {
				// retrieve loaded pattern by dataset
				pattern = chart.getData().retrieveFillStyleAsPattern(item);
			}
			// checks if pattern consistent
			if (pattern != null) {
				// transforms pattern in the CSS property and
				String patternAsCss = Utilities.toCSSBackgroundProperty(pattern, width, height);
				color.getStyle().setBackground(patternAsCss);
			} else {
				// if here, apply the fill color as background color
				color.getStyle().setBackgroundColor(Defaults.get().getGlobal().getBackgroundColorAsString());
			}
		} else if (item.isFillStyleAsGradient()) {
			// gets the gradient by legend item
			Gradient gradient = item.getFillStyleAsGradient();
			// checks if not consistent
			if (gradient == null) {
				// retrieve loaded gradient by dataset
				gradient = chart.getData().retrieveFillStyleAsGradient(item);
			}
			// checks if gradient consistent
			if (gradient != null) {
				// reference to CCS gradient
				String gradientAsCss = Utilities.toCSSBackgroundProperty(gradient);
				// sets gradient as image
				color.getStyle().setBackgroundImage(gradientAsCss);
				// gets background size
				String size = Unit.PX.format(width) + Constants.BLANK + Unit.PX.format(height);
				// sets background size property
				color.getStyle().setBackgroundSize(size);
			} else {
				// if here, apply the fill color as background color
				color.getStyle().setBackgroundColor(Defaults.get().getGlobal().getBackgroundColorAsString());
			}
		}
	}

	/**
	 * Applies the border style to the color element and returns <code>true</code> if the border color must be applied.
	 * 
	 * @param item legend item to map in the border style
	 * @param color TD element where to apply the border style
	 * @return <code>true</code> if the border has been applied and than color is missing
	 */
	private boolean applyBorderWidth(LegendLabelItem item, TableCell color) {
		// gets a correct border width
		int borderWidth = Math.max(0, item.getLineWidth());
		// applies the border
		color.getStyle().setBorderWidth(Unit.PX.format(borderWidth));
		// checks if a border must be applied
		// having a border width more then 0
		if (borderWidth > 0) {
			// checking the line has been configured as dashed
			if (item.getLineDash().isEmpty()) {
				// if here, solid border, no dashed line
				color.getStyle().setBorderStyle(BorderStyle.SOLID);
			} else {
				// if here, dashed line
				color.getStyle().setBorderStyle(BorderStyle.DASHED);
			}
			// returns true
			// to apply color
			return true;
		}
		// returns false
		// to apply color
		return false;
	}

	/**
	 * Applies the border color to the color element.
	 * 
	 * @param chart chart instance related to legend to build
	 * @param item legend item to map in the border color
	 * @param color TD element where to apply the border color
	 * @param width width to use to apply border color
	 * @param height height to use to apply border color
	 */
	private void applyBorderColor(IsChart chart, LegendLabelItem item, TableCell color, int width, int height) {
		// checks if there is stroke color
		// to apply to the border
		if (item.isStrokeStyleAsColor()) {
			color.getStyle().setBorderColor(item.getStrokeStyle().toRGBA());
		} else if (item.isStrokeStyleAsPattern()) {
			// gets the pattern by legend item
			Pattern pattern = item.getStrokeStyleAsPattern();
			// checks if not consistent
			if (pattern == null) {
				// retrieve loaded pattern by dataset
				pattern = chart.getData().retrieveStrokeStyleAsPattern(item);
			}
			// checks if pattern consistent
			if (pattern != null) {
				// transforms pattern in the CSS property and
				String patternAsCss = Utilities.toCSSBackgroundProperty(pattern, width, height) + " 1";
				color.getStyle().setBorderImage(patternAsCss);
			} else {
				// if here, remove border
				color.getStyle().setBorderWidth(Unit.PX.format(0));
			}
		} else if (item.isStrokeStyleAsGradient()) {
			// gets the gradient by legend item
			Gradient gradient = item.getStrokeStyleAsGradient();
			// checks if not consistent
			if (gradient == null) {
				// retrieve loaded gradient by dataset
				gradient = chart.getData().retrieveStrokeStyleAsGradient(item);
			}
			// checks if gradient consistent
			if (gradient != null) {
				// reference to CCS gradient
				String gradientAsCss = Utilities.toCSSBackgroundProperty(gradient) + " 1";
				// sets border imaged property
				color.getStyle().setBorderImage(gradientAsCss);
			} else {
				// if here, remove border
				color.getStyle().setBorderWidth(Unit.PX.format(0));
			}
		}
	}
}
