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
package org.pepstock.charba.client.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.Helpers;
import org.pepstock.charba.client.annotation.AnnotationPlugin;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayUtil;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.dom.DOMBuilder;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Context2dItem;
import org.pepstock.charba.client.dom.enums.TextBaseline;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.options.IsImmutableFont;

/**
 * Utility to build a canvas, to add to a chart, which contains the content where fonts and colors can be applied for each row.<br>
 * Usually helpful to use in the {@link AnnotationPlugin}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class MultilineTextAnnotationBuilder {

	// default colors
	private static final List<IsColor> DEFAULT_COLORS = Collections.unmodifiableList(Arrays.asList(HtmlColor.BLACK));
	// content list instance
	private List<String> content = null;
	// fonts list instance
	private List<IsDefaultFont> fonts = null;
	// colors list instance
	private List<IsColor> colors = DEFAULT_COLORS;
	// text align option
	private TextAlign textAlign = TextAlign.CENTER;

	/**
	 * To avoid any instantiation
	 * 
	 * @param content content of the label
	 */
	private MultilineTextAnnotationBuilder(List<String> content) {
		this.content = content;
	}

	/**
	 * Creates the builder.
	 * 
	 * @param content content of the label
	 * @return builder instance
	 */
	public static MultilineTextAnnotationBuilder create(String... content) {
		// checks fonts consistency
		Checker.assertCheck(ArrayUtil.isNotEmpty(content), "Content is not consistent");
		// creates builder
		return create(Arrays.asList(content));
	}

	/**
	 * Creates the builder.
	 * 
	 * @param content content of the label
	 * @return builder instance
	 */
	public static MultilineTextAnnotationBuilder create(List<String> content) {
		return new MultilineTextAnnotationBuilder(content);
	}

	/**
	 * Sets the text alignment in the canvas.
	 * 
	 * @param align the text alignment in the canvas
	 * @return builder instance
	 */
	public MultilineTextAnnotationBuilder setTextAlign(TextAlign align) {
		// checks fonts consistency
		if (Key.isValid(align)) {
			// stores fonts
			this.textAlign = align;
		}
		// returns builder
		return this;
	}

	/**
	 * Sets the fonts to apply to each row of the content.<br>
	 * If the fonts are less than the content row count, the last font is used for the rest of the content.
	 * 
	 * @param fonts the fonts to apply to each row of the content
	 * @return builder instance
	 */
	public MultilineTextAnnotationBuilder setFonts(IsDefaultFont... fonts) {
		// checks fonts consistency
		if (ArrayUtil.isNotEmpty(fonts)) {
			// stores fonts
			this.fonts = Arrays.asList(fonts);
		}
		// returns builder
		return this;
	}

	/**
	 * Sets the fonts to apply to each row of the content.<br>
	 * If the fonts are less than the content row count, the last font is used for the rest of the content.
	 * 
	 * @param fonts the fonts to apply to each row of the content
	 * @return builder instance
	 */
	public MultilineTextAnnotationBuilder setFonts(List<IsDefaultFont> fonts) {
		// stores fonts
		this.fonts = fonts;
		// returns builder
		return this;
	}

	/**
	 * Sets the colors to apply to each row of the content.<br>
	 * If the colors are less than the content row count, the last color is used for the rest of the content.
	 * 
	 * @param colors the colors to apply to each row of the content
	 * @return builder instance
	 */
	public MultilineTextAnnotationBuilder setColors(String... colors) {
		// checks fonts consistency
		if (ArrayUtil.isNotEmpty(colors)) {
			// stores fonts
			this.colors = ColorBuilder.parse(Arrays.asList(colors));
		}
		// returns builder
		return this;
	}

	/**
	 * Sets the colors to apply to each row of the content.<br>
	 * If the colors are less than the content row count, the last color is used for the rest of the content.
	 * 
	 * @param colors the colors to apply to each row of the content
	 * @return builder instance
	 */
	public MultilineTextAnnotationBuilder setColors(IsColor... colors) {
		// checks fonts consistency
		if (ArrayUtil.isNotEmpty(colors)) {
			// stores fonts
			this.colors = Arrays.asList(colors);
		}
		// returns builder
		return this;
	}

	/**
	 * Sets the colors to apply to each row of the content.<br>
	 * If the colors are less than the content row count, the last color is used for the rest of the content.
	 * 
	 * @param colors the colors to apply to each row of the content
	 * @return builder instance
	 */
	public MultilineTextAnnotationBuilder setColors(List<IsColor> colors) {
		// checks colors consistency
		if (ArrayListHelper.isConsistent(colors)) {
			// stores colors
			this.colors = colors;
		}
		// returns builder
		return this;
	}

	/**
	 * Creates and returns the {@link Canvas} which contains the content which can be added to a chart.
	 * 
	 * @return the {@link Canvas} which contains the content
	 */
	public Canvas build() {
		// checks content consistency
		Checker.assertCheck(ArrayListHelper.isConsistent(content), "Content is not consistent");
		// checks fonts consistency
		Checker.assertCheck(ArrayListHelper.isConsistent(fonts), "Fonts are not consistent");
		// cache with the immutable font
		final List<IsImmutableFont> immutableFonts = new LinkedList<>();
		// creates the canvas to return
		final Canvas canvas = DOMBuilder.get().createCanvasElement();
		final Context2dItem ctx = canvas.getContext2d();
		// --------------------------
		// calculates the canvas size
		// --------------------------
		final int count = content.size();
		double width = 0;
		double height = 0;
		// scans the content and the fonts
		// to calculate the canvas size
		for (int i = 0; i < count; i++) {
			// gets immutable font and add to the cache
			IsImmutableFont font = Helpers.get().toFont(checkAndGet(i, fonts));
			immutableFonts.add(font);
			// gets text row
			String text = content.get(i);
			ctx.setFont(font.toCSSString());
			// calculates width and height
			width = Math.max(width, ctx.measureText(text).getWidth());
			height += font.getLineHeight();
		}
		// sets dimension to canvas
		canvas.setWidth((int) Math.ceil(width));
		canvas.setHeight((int) Math.ceil(height));
		// --------------------------
		// draw text on the canvas
		// --------------------------
		ctx.save();
		// common canvas option
		ctx.setTextBaseline(TextBaseline.MIDDLE);
		ctx.setTextAlign(textAlign);
		// calculates starting points
		double x = calculateTextAlignment(width);
		double y = 0;
		// scans the content, fonts and colors
		// to draw the text on canvas
		for (int i = 0; i < count; i++) {
			// gets font, text and color to apply
			final IsImmutableFont font = Helpers.get().toFont(immutableFonts.get(i));
			final String text = content.get(i);
			final IsColor color = checkAndGet(i, colors);
			// gets half of line height to apply before and after text drawing
			final double halfLH = font.getLineHeight() / 2;
			y += halfLH;
			// sets canvas properties
			ctx.setFont(font.toCSSString());
			ctx.setFillColor(color);
			// apply text
			ctx.fillText(text, x, y);
			// positioning to next starting row
			y += halfLH;
		}
		ctx.restore();
		return canvas;
	}

	// --------------------
	// INTERNALS
	// --------------------

	/**
	 * Checks if the index if consistent against the passed list, otherwise it will returns the last element of the list.
	 * 
	 * @param index index to check
	 * @param items list of items
	 * @param <T> type of the element of the list
	 * @return the element of the list
	 */
	private <T> T checkAndGet(int index, List<T> items) {
		// gets index
		int i = Checker.betweenOrMaximum(index, 0, items.size() - 1);
		// returns the element at index
		return items.get(i);
	}

	/**
	 * Calculates the X starting position of the text row of the content to draw on the canvas, based on {@link TextAlign}.
	 * 
	 * @param width width of the canvas
	 * @return the X starting position of the text row of the content to draw on the canvas
	 */
	private double calculateTextAlignment(double width) {
		// checks if the alignment is center
		if (TextAlign.CENTER.equals(textAlign)) {
			return width / 2;
		} else if (TextAlign.END.equals(textAlign) || TextAlign.RIGHT.equals(textAlign)) {
			// checks if the alignment is right
			return width;
		}
		// if here, alignment is left
		return 0;
	}
}