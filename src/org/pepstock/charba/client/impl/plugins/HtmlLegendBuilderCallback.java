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
import org.pepstock.charba.client.enums.LegendAlign;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.items.LegendLabelItem;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.utils.Utilities;
import org.pepstock.charba.client.utils.Window;

import com.google.gwt.core.client.GWT;
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
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
final class HtmlLegendBuilderCallback implements LegendCallback {

	public interface LegendLabelColumnIdTemplate extends SafeHtmlTemplates {

		@Template("{0}_{1}_label")
		SafeHtml id(String chartId, int index);
	}

	public interface LegendColorColumnIdTemplate extends SafeHtmlTemplates {

		@Template("{0}_{1}_color")
		SafeHtml id(String chartId, int index);
	}

	// instance of template
	private static final LegendLabelColumnIdTemplate LABEL_COLUMN_ID_TEMPLATE = GWT.create(LegendLabelColumnIdTemplate.class);
	// instance of template
	private static final LegendColorColumnIdTemplate COLOR_COLUMN_ID_TEMPLATE = GWT.create(LegendColorColumnIdTemplate.class);
	// string format to trim blanks
	private static final String REGEXP_ID_PATTERN = "(\\S+)_(\\d+)_(\\S+)";
	// regexp instance to trim blanks
	private static final RegExp REGEXP_ID = RegExp.compile(REGEXP_ID_PATTERN);
	
	// internal comparator to sort legend item by own index
	private static final Comparator<LegendLabelItem> COMPARATOR = (LegendLabelItem o1, LegendLabelItem o2) -> Double.compare(o1.getDatasetIndex(), o2.getDatasetIndex()) + Double.compare(o1.getIndex(), o2.getIndex());
	// internal comparator to sort legend item by own index
	private static final Comparator<LegendLabelItem> REVERSE_COMPARATOR = (LegendLabelItem o1, LegendLabelItem o2) -> Double.compare(o2.getDatasetIndex(), o1.getDatasetIndex()) + Double.compare(o2.getIndex(), o1.getIndex());

	private static final int MAX_COLUMNS = 10;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.LegendCallback#generateLegend(org.pepstock.charba.client.IsChart,
	 * com.google.gwt.safehtml.shared.SafeHtmlBuilder)
	 */
	@Override
	public void generateLegend(IsChart chart, SafeHtmlBuilder builder) {
		SpanElement container = Document.get().createSpanElement();
		container.appendChild(createLegend(chart));
		builder.appendHtmlConstant(container.getInnerHTML());
	}

	// FIXME
	String getChartId(String id) {
		if (id != null) {
			// executes regular expression
			MatchResult matcher = REGEXP_ID.exec(id);
			boolean matchFound = matcher != null;
			Window.getConsole().log("Match : "+matchFound);
			Window.getConsole().log("Match : "+matcher.getGroupCount());
			// checks if matches
			if (matchFound && matcher.getGroupCount() == 4) {
				return matcher.getGroup(1);
			}	
		}
		return UndefinedValues.STRING;
	}

	int getIndex(String id) {
		if (id != null) {
			// executes regular expression
			MatchResult matcher = REGEXP_ID.exec(id);
			boolean matchFound = matcher != null;
			Window.getConsole().log("Match : "+matchFound);
			Window.getConsole().log("Match : "+matcher.getGroupCount());
			// checks if matches
			if (matchFound && matcher.getGroupCount() == 4) {
				return Integer.parseInt(matcher.getGroup(2));
			}	
		}
		return UndefinedValues.INTEGER;
	}


	private TableElement createLegend(IsChart chart) {
		TableElement table = Document.get().createTableElement();

		Legend legend = chart.getOptions().getLegend();
		LegendLabels legendLabels = chart.getOptions().getLegend().getLabels();
		List<LegendLabelItem> legendItems;
		if (legendLabels.getLabelsCallback() != null) {
			legendItems = legendLabels.getLabelsCallback().generateLegendLabels(chart,Defaults.get().generateLabels(chart));
		} else {
			legendItems = Defaults.get().generateLabels(chart);
		}		
		List<LegendLabelItem> copiedLegendItems = new LinkedList<>();
		if (legendLabels.getFilterCallback() != null) {
			for (LegendLabelItem item : legendItems) {
				if (legendLabels.getFilterCallback().onFilter(chart, item)) {
					copiedLegendItems.add(item);
				}
			}
		} else {
			copiedLegendItems.addAll(legendItems);
		}
		if (copiedLegendItems.isEmpty()) {
			return table;
		}
		if (legend.isReverse()) {
			Collections.sort(copiedLegendItems, REVERSE_COMPARATOR);
		} else {
			Collections.sort(copiedLegendItems, COMPARATOR);
		}

		table.setCellPadding(0);
		table.setCellSpacing(0);
		if (LegendAlign.CENTER.equals(legend.getAlign())) {
			table.setAttribute("align", "center");
		} else if (LegendAlign.START.equals(legend.getAlign())) {
			table.setAttribute("align", "left");
		} else {
			table.setAttribute("align", "right");
		}
		int index = 0;
		TableRowElement current = null;
		for (LegendLabelItem item : copiedLegendItems) {
			if (index % MAX_COLUMNS == 0) {
				TableRowElement newRow = Document.get().createTRElement();
				table.appendChild(newRow);
				current = newRow;
			}
			item.setText(item.getText()+"\nCiao");
			current.appendChild(createColorCell(legend, legendLabels, item));
			current.appendChild(createLabelCell(legend, legendLabels, item));
			index++;
		}
		return table;
	}

	private TableCellElement createColorCell(Legend legend, LegendLabels legendLabels, LegendLabelItem item) {
		TableCellElement colorColumn = Document.get().createTDElement();
		colorColumn.setId(COLOR_COLUMN_ID_TEMPLATE.id(legend.getChart().getId(), getRightIndex(item)).asString());
		colorColumn.setVAlign(Position.TOP.value());
		colorColumn.getStyle().setPaddingRight(legendLabels.getPadding() / 2, Unit.PX);
		colorColumn.getStyle().setPaddingBottom(legendLabels.getPadding(), Unit.PX);
		DivElement color = Document.get().createDivElement();
		colorColumn.appendChild(color);
		
		int borderWidthToremove = Math.max(0, item.getLineWidth());
		
		color.getStyle().setDisplay(Display.INLINE_BLOCK);
		color.getStyle().setWidth(legendLabels.getBoxWidth() - borderWidthToremove, Unit.PX);
		color.getStyle().setHeight(legendLabels.getFontSize() - borderWidthToremove,  Unit.PX);
		if (item.isFillStyleAsColor()) {
			color.getStyle().setBackgroundColor(item.getFillStyle().toRGBA());
		}
		if (item.getLineWidth() > 0) {
			color.getStyle().setBorderWidth(item.getLineWidth(), Unit.PX);
			if (item.getLineDash().isEmpty()) {
				color.getStyle().setBorderStyle(BorderStyle.SOLID);
			} else {
				color.getStyle().setBorderStyle(BorderStyle.DASHED);
			}
		} else {
			color.getStyle().setBorderStyle(BorderStyle.NONE);
		}
		if (item.isStrokeStyleAsColor()) {
			color.getStyle().setBorderColor(item.getStrokeStyle().toRGBA());
		}
		return colorColumn;
	}

	private TableCellElement createLabelCell(Legend legend, LegendLabels legendLabels, LegendLabelItem item) {
		TableCellElement labelColumn = Document.get().createTDElement();
		labelColumn.setId(LABEL_COLUMN_ID_TEMPLATE.id(legend.getChart().getId(), getRightIndex(item)).asString());
		labelColumn.setVAlign(Position.TOP.value());
		labelColumn.getStyle().setPaddingRight(legendLabels.getPadding(), Unit.PX);
		labelColumn.getStyle().setPaddingBottom(legendLabels.getPadding(), Unit.PX);
		DivElement label = createLabelText(item);
		labelColumn.appendChild(label);
		label.getStyle().setProperty(Utilities.CSS_FONT_PROPERTY, Utilities.toCSSFontProperty(legendLabels.getFontStyle(), legendLabels.getFontSize(), legendLabels.getFontFamily()));
		label.getStyle().setColor(legendLabels.getFontColor().toRGBA());
		if (item.isHidden()) {
			label.getStyle().setTextDecoration(TextDecoration.LINE_THROUGH);
		}
		return labelColumn;
	}
	
	private DivElement createLabelText(LegendLabelItem item) {
		String text = item.getText();
		DivElement element = Document.get().createDivElement();
		if (text != null) {
			if (text.contains("\n")) {
				String[] splittedText = text.split("\n");
				for (String singleText : splittedText) {
					if (element.getChildCount() > 0) {
						element.appendChild(Document.get().createBRElement());
					}
					element.appendChild(Document.get().createTextNode(singleText));
				}
			} else {
				element.setInnerText(text);
			}
		}
		return element;
	}
	
	private int getRightIndex(LegendLabelItem item) {
		return item.getDatasetIndex() == UndefinedValues.INTEGER ? item.getIndex() : item.getDatasetIndex();
	}

}
