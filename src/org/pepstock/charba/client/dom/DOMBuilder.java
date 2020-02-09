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
package org.pepstock.charba.client.dom;

import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.dom.elements.LineBreak;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Div;
import org.pepstock.charba.client.dom.elements.Heading;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.dom.elements.Script;
import org.pepstock.charba.client.dom.elements.Span;
import org.pepstock.charba.client.dom.elements.Style;
import org.pepstock.charba.client.dom.elements.TableCell;
import org.pepstock.charba.client.dom.elements.Table;
import org.pepstock.charba.client.dom.elements.TableRow;
import org.pepstock.charba.client.dom.elements.TextNode;

/**
 * FIXME
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DOMBuilder {

	private static final DOMBuilder INSTANCE = new DOMBuilder();

	private static final String CHART_PREFIX_ID = "charba-chart-";

	private final AtomicInteger counter = new AtomicInteger();

	private final boolean canvasSupported;

	private DOMBuilder() {
		Canvas canvas = createCanvasElement();
		this.canvasSupported = canvas.isSupported();
	}

	public static DOMBuilder get() {
		return INSTANCE;
	}

	public String createUniqueId() {
		return CHART_PREFIX_ID + counter.getAndIncrement();
	}

	/**
	 * @return the canvasSupported
	 */
	public boolean isCanvasSupported() {
		return canvasSupported;
	}

	/**
	 * Creates a &lt;canvas&gt; element.
	 * 
	 * @return the newly created element
	 */
	public Canvas createCanvasElement() {
		return DOM.getDocument().createElement(Canvas.TAG);
	}

	/**
	 * Creates a &lt;div&gt; element.
	 * 
	 * @return the newly created element
	 */
	public Div createDivElement() {
		return DOM.getDocument().createElement(Div.TAG);
	}

	/**
	 * Creates a &lt;script&gt; element.
	 * 
	 * @return the newly created element
	 */
	public Script createScriptElement() {
		return DOM.getDocument().createElement(Script.TAG);
	}

	/**
	 * Creates a &lt;script&gt; element.
	 * 
	 * @return the newly created element
	 */
	public Style createStyleElement() {
		return DOM.getDocument().createElement(Style.TAG);
	}

	/**
	 * Creates a &lt;br&gt; element.
	 * 
	 * @return the newly created element
	 */
	public LineBreak createBreakElement() {
		return DOM.getDocument().createElement(LineBreak.TAG);
	}

	/**
	 * Creates a &lt;span&gt; element.
	 * 
	 * @return the newly created element
	 */
	public Span createSpanElement() {
		return DOM.getDocument().createElement(Span.TAG);
	}

	/**
	 * Creates a &lt;img&gt; element.
	 * 
	 * @return the newly created element
	 */
	public Img createImageElement() {
		return DOM.getDocument().createElement(Img.TAG);
	}

	/**
	 * Creates a &lt;table&gt; element.
	 * 
	 * @return the newly created element
	 */
	public Table createTableElement() {
		return DOM.getDocument().createElement(Table.TAG);
	}

	/**
	 * Creates a &lt;tr&gt; element.
	 * 
	 * @return the newly created element
	 */
	public TableRow createTableRowElement() {
		return DOM.getDocument().createElement(TableRow.TAG);
	}

	/**
	 * Creates a &lt;td&gt; element.
	 * 
	 * @return the newly created element
	 */
	public TableCell createTableCellElement() {
		return DOM.getDocument().createElement(TableCell.TAG);
	}
	
	/**
	 * Creates a heading element element.
	 * 
	 * @return the newly created element
	 */
	public Heading createHeadingElement() {
		return DOM.getDocument().createElement(Heading.TAG);
	}
	
	/**
	 * Creates a text node.
	 * 
	 * @param data the text node's initial text
	 * @return the newly created text node
	 */
	public TextNode createTextNode(String data) {
		return DOM.getDocument().createTextNode(data);
	}

	/**
	 * FIXME
	 * 
	 * @return the newly created text node
	 */
	public BaseNativeEvent createChangeEvent() {
		BaseNativeEvent event = DOM.getDocument().createEvent(BaseEventTypes.EVENT_MOUSE);
		event.initEvent(BaseEventTypes.CONTEXT_MENU);
		return event;
	}

}
