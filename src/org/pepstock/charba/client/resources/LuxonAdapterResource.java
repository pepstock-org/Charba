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
 
/**
 * Contains the content of <code>chartjs-adapter-luxon.min.js</code> to inject.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class LuxonAdapterResource extends AbstractInjectableResource {

	// encoded javascript content of chartjs-adapter-luxon.min.js
	private static final String[] CONTENT = {
		"/*!",
		" * chartjs-adapter-luxon v1.2.0",
		" * https://www.chartjs.org",
		" * (c) 2022 chartjs-adapter-luxon Contributors",
		" * Released under the MIT license",
		" */",
		"!function(e,t){\"object\"==typeof exports&&\"undefined\"!=typeof module?t(require(\"chart.js\"),require(\"luxon\")):\"function\"==typeof define&&define.amd?define([\"chart.js\",\"luxon\"],t):t((e=\"undefined\"!=typeof globalThis?globalThis:e||self).Chart,e.luxon)}(this,(function(e,t){\"use strict\";const n={datetime:t.DateTime.DATETIME_MED_WITH_SECONDS,millisecond:\"h:mm:ss.SSS a\",second:t.DateTime.TIME_WITH_SECONDS,minute:t.DateTime.TIME_SIMPLE,hour:{hour:\"numeric\"},day:{day:\"numeric\",month:\"short\"},week:\"DD\",month:{month:\"short\",year:\"numeric\"},quarter:\"'Q'q - yyyy\",year:{year:\"numeric\"}};e._adapters._date.override({_id:\"luxon\",_create:function(e){return t.DateTime.fromMillis(e,this.options)},init(e){this.options.locale||(this.options.locale=e.locale)},formats:function(){return n},parse:function(e,n){const i=this.options,r=typeof e;return null===e||\"undefined\"===r?null:(\"number\"===r?e=this._create(e):\"string\"===r?e=\"string\"==typeof n?t.DateTime.fromFormat(e,",
		"n,i):t.DateTime.fromISO(e,i):e instanceof Date?e=t.DateTime.fromJSDate(e,i):\"object\"!==r||e instanceof t.DateTime||(e=t.DateTime.fromObject(e,i)),e.isValid?e.valueOf():null)},format:function(e,t){const n=this._create(e);return\"string\"==typeof t?n.toFormat(t):n.toLocaleString(t)},add:function(e,t,n){const i={};return i[n]=t,this._create(e).plus(i).valueOf()},diff:function(e,t,n){return this._create(e).diff(this._create(t)).as(n).valueOf()},startOf:function(e,t,n){if(\"isoWeek\"===t){n=Math.trunc(Math.min(Math.max(0,n),6));const t=this._create(e);return t.minus({days:(t.weekday-n+7)%7}).startOf(\"day\").valueOf()}return t?this._create(e).startOf(t).valueOf():e},endOf:function(e,t){return this._create(e).endOf(t).valueOf()}})}));"
	};
	
	/**
	 * Creates the injectable resource with <code>chartjs-adapter-luxon.min.js</code> content.
	 */
	LuxonAdapterResource() {
		super(ResourceName.DATE_TIME_ADAPTER, CONTENT);
	}

}
