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
public final class LuxonAdapterResource extends AbstractInjectableResource {

	// encoded javascript content of chartjs-adapter-luxon.min.js
	private static final String[] CONTENT = {
		"/*!",
		" * chartjs-adapter-luxon v0.2.1",
		" * https://www.chartjs.org",
		" * (c) 2020 Chart.js Contributors",
		" * Released under the MIT license",
		" */",
		"!function(e,t){\"object\"==typeof exports&&\"undefined\"!=typeof module?t(require(\"chart.js\"),require(\"luxon\")):\"function\"==typeof define&&define.amd?define([\"chart.js\",\"luxon\"],t):t((e=e||self).Chart,e.luxon)}(this,(function(e,t){\"use strict\";e=e&&Object.prototype.hasOwnProperty.call(e,\"default\")?e.default:e;const r={datetime:t.DateTime.DATETIME_MED_WITH_SECONDS,millisecond:\"h:mm:ss.SSS a\",second:t.DateTime.TIME_WITH_SECONDS,minute:t.DateTime.TIME_SIMPLE,hour:{hour:\"numeric\"},day:{day:\"numeric\",month:\"short\"},week:\"DD\",month:{month:\"short\",year:\"numeric\"},quarter:\"'Q'q - yyyy\",year:{year:\"numeric\"}};e._adapters._date.override({_id:\"luxon\",_create:function(e){return t.DateTime.fromMillis(e,this.options)},formats:function(){return r},parse:function(r,n){const i=this.options;if(e.helpers.isNullOrUndef(r))return null;const o=typeof r;return\"number\"===o?r=this._create(r):\"string\"===o?r=\"string\"==typeof n?t.DateTime.fromFormat(r,n,i):t.DateTime.fromISO",
		"(r,i):\"object\"!==o||r instanceof t.DateTime?r instanceof Date&&(r=t.DateTime.fromJSDate(r,i)):r=t.DateTime.fromObject(r),r.isValid?r.valueOf():null},format:function(e,t){const r=this._create(e);return\"string\"==typeof t?r.toFormat(t,this.options):r.toLocaleString(t)},add:function(e,t,r){const n={};return n[r]=t,this._create(e).plus(n).valueOf()},diff:function(e,t,r){return this._create(e).diff(this._create(t)).as(r).valueOf()},startOf:function(e,t,r){return\"isoWeek\"===t?this._create(e).isoWeekday(r).valueOf():t?this._create(e).startOf(t).valueOf():e},endOf:function(e,t){return this._create(e).endOf(t).valueOf()}})}));"
	};
	
	/**
	 * Creates the injectable resource with <code>chartjs-adapter-luxon.min.js</code> content.
	 */
	LuxonAdapterResource() {
		super(ResourceName.DATE_TIME_ADAPTER, CONTENT);
	}

}
