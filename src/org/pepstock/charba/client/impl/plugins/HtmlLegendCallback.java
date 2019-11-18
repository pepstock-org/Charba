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
import org.pepstock.charba.client.callbacks.LegendCallback;
import org.pepstock.charba.client.configuration.Legend;
import org.pepstock.charba.client.configuration.LegendLabels;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.items.LegendLabelItem;
import org.pepstock.charba.client.utils.Utilities;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.TextDecoration;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * Legend callback implementation to override the standard HTML format of CHART.JS legend.<br>
 * This HTML is usable only by {@link HtmlLegendBuilder} plugin.<br< This is HTML structure of the legend:<br>
 * <br>
 * <code>
 * <TABLE>
 *   <TR>
 *     <TD>
 *       <DIV color for legend item>
 *       <DIV label for legend item>
 *     </TD>
 *     <TD>
 *       <DIV color for legend item>
 *       <DIV label for legend item>
 *     </TD>
 *   </TR>
 * </TABLE>
 * </code> <br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class HtmlLegendCallback implements LegendCallback {

	// internal comparator to sort legend item by own index
	private static final Comparator<LegendLabelItem> COMPARATOR = (LegendLabelItem o1, LegendLabelItem o2) -> Double.compare(o1.getDatasetIndex(), o2.getDatasetIndex()) + Double.compare(o1.getIndex(), o2.getIndex());
	// internal comparator to sort legend item by reverse own index
	private static final Comparator<LegendLabelItem> REVERSE_COMPARATOR = (LegendLabelItem o1, LegendLabelItem o2) -> Double.compare(o2.getDatasetIndex(), o1.getDatasetIndex()) + Double.compare(o2.getIndex(), o1.getIndex());
	// CSS style property for horizontal alignment
	private static final String STYLE_ALIGN = "align";
	// carriage return to check into text of legend item
	private static final String BREAK = "\n";
	// Maximum amount of column to show
	private static final int MAX_COLUMNS = Integer.MAX_VALUE;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.LegendCallback#generateLegend(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public SafeHtml generateLegend(IsChart chart) {
		// create safe html builder
		SafeHtmlBuilder builder = new SafeHtmlBuilder();
		// creates a HTML element
		// as container of result
		// needed to get innerHTML property
		SpanElement container = Document.get().createSpanElement();
		// invokes the creation of legend
		container.appendChild(buildLegend(chart));
		// appends to safe html builder the innerHTML
		// of container
		builder.appendHtmlConstant(container.getInnerHTML());
		// returns as safe html
		return builder.toSafeHtml();
	}

	/**
	 * Builds the legend for the passed chart using {@link TableElement} as model.
	 * 
	 * @param chart chart instance related to legend to build
	 * @return the table element instance with the complete legend
	 */
	private TableElement buildLegend(IsChart chart) {
		// gets legend
		Legend legend = chart.getOptions().getLegend();
		// creates table as result
		final TableElement table = Document.get().createTableElement();
		// retrieves the list of legend items
		final List<LegendLabelItem> legendItems = extractLegendItems(chart);
		// checks if there is any items
		if (!legendItems.isEmpty()) {
			// checks if legend must be created in reverse mode
			if (legend.isReverse()) {
				Collections.sort(legendItems, REVERSE_COMPARATOR);
			} else {
				Collections.sort(legendItems, COMPARATOR);
			}
			// resets padding and spacing
			table.setCellPadding(0);
			table.setCellSpacing(0);
			// sets horizontal alignment
			table.setAttribute(STYLE_ALIGN, legend.getAlign().getHorizontalAlignmentValue());
			// sets index to check when to have more lines
			int index = 0;
			// instance of current row
			// where adds new columns for each legend
			TableRowElement current = null;
			// scans all legend items
			for (LegendLabelItem item : legendItems) {
				// checks if new row must be created
				// checking max legend items per row
				if (index % MAX_COLUMNS == 0) {
					// new row
					TableRowElement newRow = Document.get().createTRElement();
					// appends to table
					table.appendChild(newRow);
					// sets the current one
					current = newRow;
				}
				// creates the element id for legend item
				HtmlLegendId legendId = HtmlLegendId.get(chart, item);
				// creates and adds the cell part
				// related to color/box to design into legend
				current.appendChild(buildColorCell(chart, legendId, item));
				// creates and adds the cell part
				// related to label with legend item text
				current.appendChild(buildLabelCell(chart, legendId, item));
				// increments the amount of legend item index
				index++;
			}
		}
		// returns the legend
		return table;
	}

	/**
	 * Extracts all legend items which must be rendered into legend.
	 * 
	 * @param chart chart instance related to legend to build
	 * @return list of legend items to render
	 */
	private List<LegendLabelItem> extractLegendItems(IsChart chart) {
		// result list
		final List<LegendLabelItem> result = new LinkedList<>();
		// gets legend and legend labels instances
		Legend legend = chart.getOptions().getLegend();
		LegendLabels legendLabels = legend.getLabels();
		// temporary list of legend items
		List<LegendLabelItem> legendItems;
		// default list of legend items
		List<LegendLabelItem> defaultLegendItems = Defaults.get().generateLabels(chart);
		// checks if there is a configured legend labels callback
		if (legendLabels.getLabelsCallback() != null) {
			// if here, there is a generate legend labels callback
			// and then it will be called
			legendItems = legendLabels.getLabelsCallback().generateLegendLabels(chart, defaultLegendItems);
		} else {
			// no callback then it will use the default ones
			legendItems = defaultLegendItems;
		}
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
		return result;
	}

	/**
	 * Builds a {@link TableCellElement} which should contains and represents the color of dataset.
	 * 
	 * @param chart chart instance related to legend to build
	 * @param legendId element id for {@link TableCellElement}
	 * @param item legend item to show into color cell element
	 * @return a {@link TableCellElement} which should contains and represents the color of dataset
	 */
	private TableCellElement buildColorCell(IsChart chart, HtmlLegendId legendId, LegendLabelItem item) {
		// result color cell
		final TableCellElement colorCell = Document.get().createTDElement();
		// gets legend and legend labels instances
		Legend legend = chart.getOptions().getLegend();
		LegendLabels legendLabels = legend.getLabels();
		// styling the cell
		colorCell.setId(legendId.getIdForColor());
		colorCell.setVAlign(Position.TOP.value());
		colorCell.getStyle().setPaddingRight(legendLabels.getPadding() / 2D, Unit.PX);
		colorCell.getStyle().setPaddingBottom(legendLabels.getPadding(), Unit.PX);
		// creates inner HTML element
		// where to apply the color
		DivElement color = Document.get().createDivElement();
		colorCell.appendChild(color);
		// calculates the border width to remove to size of color
		// because the size of border will be added to element
		// applying the border afterwards
		int borderWidthToremove = Math.max(0, item.getLineWidth());
		int width = legendLabels.getBoxWidth() - borderWidthToremove;
		int height = legendLabels.getFontSize() - borderWidthToremove;
		// styling the cell with mandatory values
		color.getStyle().setDisplay(Display.INLINE_BLOCK);
		color.getStyle().setWidth(width, Unit.PX);
		color.getStyle().setHeight(height, Unit.PX);
		// checks if fill color has been set
		if (item.isFillStyleAsColor()) {
			// if here, apply the fill color as background color
			color.getStyle().setBackgroundColor(item.getFillStyle().toRGBA());
		} else if (item.isFillStyleAsCanvasPattern()) {
			// transforms pattern into CSS property and
			String patternAsCss = Utilities.toCSSBackgroundProperty(item.getFillStyleAsPattern(), width, height);
			color.getStyle().setProperty(Utilities.CSS_BACKGROUND_PROPERTY, patternAsCss);
		} else if (item.isFillStyleAsCanvasGradient()) {
			// reference to CCS gradient
			String gradientAsCss = null;
			if (item.getFillStyleAsGradient() != null) {
				// transforms gradient into CSS property and
				gradientAsCss = Utilities.toCSSBackgroundProperty(item.getFillStyleAsGradient());
			} else {
				// transforms gradient into CSS property and
				gradientAsCss = Utilities.toCSSBackgroundProperty(item.getFillStyleAsCanvasGradient(), width, height);
			}
			color.getStyle().setProperty(Utilities.CSS_BACKGROUND_IMAGE_PROPERTY, gradientAsCss);
		}
		// checks if a border must be applied
		// having a border width more then 0
		if (item.getLineWidth() > 0) {
			// applies the border
			color.getStyle().setBorderWidth(item.getLineWidth(), Unit.PX);
			// checking the line has been configured as dashed
			if (item.getLineDash().isEmpty()) {
				// if here, solid border, no dashed line
				color.getStyle().setBorderStyle(BorderStyle.SOLID);
			} else {
				// if here, dashed line
				color.getStyle().setBorderStyle(BorderStyle.DASHED);
			}
		} else {
			// if here, line width is 0 then
			// configured do not be visible and then
			// no border
			color.getStyle().setBorderStyle(BorderStyle.NONE);
		}
		// checks if there is stroke color
		// to apply to the border
		if (item.isStrokeStyleAsColor()) {
			color.getStyle().setBorderColor(item.getStrokeStyle().toRGBA());
		} else if (item.isStrokeStyleAsCanvasPattern()) {
			// transforms pattern into CSS property and
			String patternAsCss = Utilities.toCSSBackgroundProperty(item.getFillStyleAsCanvasPattern(), width, height) + " 1";
			color.getStyle().setProperty(Utilities.CSS_BORDER_IMAGE_PROPERTY, patternAsCss);
		} else if (item.isStrokeStyleAsCanvasGradient()) {
			// reference to CCS gradient
			String gradientAsCss = null;
			if  (item.getStrokeStyleAsGradient() != null) {
				// transforms gradient into CSS property and
				// adds 1 to the string to show gradient on border by border image property
				gradientAsCss = Utilities.toCSSBackgroundProperty(item.getStrokeStyleAsGradient()) + " 1";
			} else {
				// transforms gradient into CSS property and
				gradientAsCss = Utilities.toCSSBackgroundProperty(item.getStrokeStyleAsCanvasGradient(), width, height) + " 1";
			}
			color.getStyle().setProperty(Utilities.CSS_BORDER_IMAGE_PROPERTY, gradientAsCss);
		}
		return colorCell;
	}

	/**
	 * Builds a {@link TableCellElement} which should contains and represents the label of dataset.
	 * 
	 * @param chart chart instance related to legend to build
	 * @param legendId element id for {@link TableCellElement}
	 * @param item legend item to show into color cell element
	 * @return a {@link TableCellElement} which should contains and represents the label of dataset
	 */
	private TableCellElement buildLabelCell(IsChart chart, HtmlLegendId legendId, LegendLabelItem item) {
		// result label cell
		final TableCellElement labelCell = Document.get().createTDElement();
		// gets legend and legend labels instances
		Legend legend = chart.getOptions().getLegend();
		LegendLabels legendLabels = legend.getLabels();
		// styling the cell
		labelCell.setId(legendId.getIdForLabel());
		labelCell.setVAlign(Position.TOP.value());
		labelCell.getStyle().setPaddingRight(legendLabels.getPadding(), Unit.PX);
		labelCell.getStyle().setPaddingBottom(legendLabels.getPadding(), Unit.PX);
		// creates inner HTML element
		// where to apply the label
		DivElement label = createLabelText(item);
		labelCell.appendChild(label);
		// styling the cell with mandatory values
		label.getStyle().setProperty(Utilities.CSS_FONT_PROPERTY, Utilities.toCSSFontProperty(legendLabels.getFontStyle(), legendLabels.getFontSize(), legendLabels.getFontFamily()));
		label.getStyle().setColor(legendLabels.getFontColor().toRGBA());
		// checks if item is hidden
		if (item.isHidden()) {
			// if hidden, apply a specific text decoration
			label.getStyle().setTextDecoration(TextDecoration.LINE_THROUGH);
		}
		return labelCell;
	}

	/**
	 * Builds a {@link TableCellElement} which should contains and represents the label of dataset, setting the text of the
	 * label to apply.
	 * 
	 * @param item legend item to show into color cell element
	 * @return a {@link TableCellElement} which should contains and represents the label of dataset, setting the text of the
	 *         label to apply
	 */
	private DivElement createLabelText(LegendLabelItem item) {
		// result text label cell
		final DivElement element = Document.get().createDivElement();
		// gets text of legend item
		String text = item.getText();
		// checks if the text of legend item is consistent
		if (text != null && text.length() > 0) {
			// checks if the text has stored as HTML
			if (item.isHtmlText()) {
				// gets html
				SafeHtml html = item.getTextAsHtml();
				// sets html into label cell
				element.setInnerHTML(html.asString());
			} else {
				// FIXME adds callback
				// checks if the text contains a carriage return
				if (text.contains(BREAK)) {
					// splits the text
					String[] splittedText = text.split(BREAK);
					// scans all splitted text
					for (String singleText : splittedText) {
						// if elements has got more than 0 children
						// means that a text node has been already added
						// then BR elemnt will be added
						if (element.getChildCount() > 0) {
							element.appendChild(Document.get().createBRElement());
						}
						// adds the splitted text as text element
						element.appendChild(Document.get().createTextNode(singleText));
					}
				} else {
					// if here, the text has not any HTML element
					// and is not breakable
					// then the text is set as text of HTML element
					element.setInnerText(text);
				}
			}
		}
		return element;
	}

}
