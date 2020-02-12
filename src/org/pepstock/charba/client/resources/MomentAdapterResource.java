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
package org.pepstock.charba.client.resources;

import org.pepstock.charba.client.resources.InjectableResource;

/**
 * Contains the content of <code>chartjs-adapter-moment.min.js</code> to inject.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class MomentAdapterResource extends InjectableResource {

	// encoded javascript content of chartjs-adapter-moment.min.js
	private static final String[] CONTENT = {
		"/*!",
		" * chartjs-adapter-moment v0.1.1",
		" * https://www.chartjs.org",
		" * (c) 2020 Chart.js Contributors",
		" * Released under the MIT license",
		" */",
		"!function(e,t){\"object\"==typeof exports&&\"undefined\"!=typeof module?t(require(\"moment\"),require(\"chart.js\")):\"function\"==typeof define&&define.amd?define([\"moment\",\"chart.js\"],t):t((e=e||self).moment,e.Chart)}(this,(function(e,t){\"use strict\";e=e&&e.hasOwnProperty(\"default\")?e.default:e;const n={datetime:\"MMM D, YYYY, h:mm:ss a\",millisecond:\"h:mm:ss.SSS a\",second:\"h:mm:ss a\",minute:\"h:mm a\",hour:\"hA\",day:\"MMM D\",week:\"ll\",month:\"MMM YYYY\",quarter:\"[Q]Q - YYYY\",year:\"YYYY\"};t._adapters._date.override(\"function\"==typeof e?{_id:\"moment\",formats:function(){return n},parse:function(t,n){return\"string\"==typeof t&&\"string\"==typeof n?t=e(t,n):t instanceof e||(t=e(t)),t.isValid()?t.valueOf():null},format:function(t,n){return e(t).format(n)},add:function(t,n,f){return e(t).add(n,f).valueOf()},diff:function(t,n,f){return e(t).diff(e(n),f)},startOf:function(t,n,f){return t=e(t),\"isoWeek\"===n?t.isoWeekday(f).valueOf():t.startOf(n).valueOf()},endOf:func",
		"tion(t,n){return e(t).endOf(n).valueOf()}}:{})}));"
	};
	
	/**
	 * Creates the injectable resource with <code>chartjs-adapter-moment.min.js</code> content.
	 */
	MomentAdapterResource() {
		super(ResourceNames.DATE_TIME_ADAPTER, CONTENT);
	}

}