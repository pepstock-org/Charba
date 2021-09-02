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
package org.pepstock.charba.client.sankey;

import org.pepstock.charba.client.resources.AbstractInjectableResource;
import org.pepstock.charba.client.resources.ResourceName;

/**
 * Contains the content of <code>chartjs-chart-sankey.min.js</code> to inject.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class SankeyControllerResource extends AbstractInjectableResource {

	// encoded javascript content of chartjs-chart-sankey.min.js
	private static final String[] CONTENT = {
		"/*!",
		" * chartjs-chart-sankey v0.8.0",
		" * https://github.com/kurkle/chartjs-chart-sankey#readme",
		" * (c) 2021 Jukka Kurkela",
		" * Released under the MIT license",
		" */",
		"!function(t,e){\"object\"==typeof exports&&\"undefined\"!=typeof module?e(require(\"chart.js\"),require(\"chart.js/helpers\")):\"function\"==typeof define&&define.amd?define([\"chart.js\",\"chart.js/helpers\"],e):e((t=\"undefined\"!=typeof globalThis?globalThis:t||self).Chart,t.Chart.helpers)}(this,(function(t,e){\"use strict\";function o(t,e){const o=t.filter((t=>!e.has(t)));return o.length?o:t.slice(0,1)}const r=t=>void 0!==t,a=(t,e)=>t.x!==e.x?t.x-e.x:t.y-e.y,n=(t,e)=>t.reduce(((t,o)=>t+o.node[e].length+n(o.node[e],e)),0),i=t=>(e,o)=>n(e.node[t],t)-n(o.node[t],t);function s(t,e){return t.from.sort(i(\"from\")).forEach((t=>{const o=t.node;r(o.y)||(o.y=e,e=Math.max(e+o.out,s(o,e)))})),e}function l(t,e){return t.to.sort(i(\"to\")).forEach((t=>{const o=t.node;r(o.y)||(o.y=e,e=Math.max(e+o.in,l(o,e)))})),e}function c(t,e){return r(t.y)?t.y:(t.y=e,e)}function h(t,e){const o=(t=>t.sort(((t,e)=>Math.max(e.in,e.out)-Math.max(t.in,t.out)))[0])(t);o.y=0;const a=s(o,0),n=l(o,0),i=function(t,e){c",
		"onst o=t.filter((t=>0===t.x)),a=t.filter((t=>t.x===e)),n=o.filter((t=>!r(t.y))),i=a.filter((t=>!r(t.y)));let s=o.reduce(((t,e)=>Math.max(t,e.y+e.out||0)),0),h=a.reduce(((t,e)=>Math.max(t,e.y+e.in||0)),0);return s>=h?(n.forEach((t=>{s=c(t,s),s=Math.max(s+t.out,l(t,s))})),i.forEach((t=>{h=c(t,h),h=Math.max(h+t.in,l(t,h))}))):(i.forEach((t=>{h=c(t,h),h=Math.max(h+t.in,l(t,h))})),n.forEach((t=>{s=c(t,s),s=Math.max(s+t.out,l(t,s))}))),Math.max(s,h)}(t,e);return Math.max(a,n,i)}function f(t,e,r,n){const i=[...t.values()],s=function(t,e){const r=new Set(e.map((t=>t.to))),a=new Set(e.map((t=>t.from))),n=new Set([...t.keys()]);let i=0;for(;n.size;){const a=o([...n],r);for(let e=0;e<a.length;e++)t.get(a[e]).x=i,n.delete(a[e]);n.size&&(r.clear(),e.filter((t=>n.has(t.from))).forEach((t=>r.add(t.to))),i++)}return[...t.keys()].filter((t=>!a.has(t))).forEach((e=>{t.get(e).x=i})),i}(t,e),l=r?function(t,e){let o=0;for(let r=0;r<=e;r++){let e=0;const a=t.filter((t=>t.x===r)).sort(((t,e)=>t.priority-e.pri",
		"ority));for(const t of a)t.y=e,e+=Math.max(t.out,t.in);o=Math.max(e,o)}return o}(i,s):h(i,s),c=function(t,e){let o=1,r=0,n=0,i=0;const s=[];return t.sort(a).forEach((t=>{if(t.y){if(0===t.x)s.push(t.y);else{for(r!==t.x&&(r=t.x,n=0),o=n+1;o<s.length&&!(s[o]>t.y);o++);n=o}t.y+=o*e,o++}i=Math.max(i,t.y+Math.max(t.in,t.out))})),i}(i,.03*l);return function(t,e){t.forEach((t=>{const o=Math[e](t.in||t.out,t.out||t.in),r=o<t.in,a=o<t.out;let n=0,i=t.from.length;t.from.sort(((t,e)=>t.node.y+t.node.out/2-(e.node.y+e.node.out/2))).forEach(((t,e)=>{r?t.addY=e*(o-t.flow)/(i-1):(t.addY=n,n+=t.flow)})),n=0,i=t.to.length,t.to.sort(((t,e)=>t.node.y+t.node.in/2-(e.node.y+e.node.in/2))).forEach(((t,e)=>{a?t.addY=e*(o-t.flow)/(i-1):(t.addY=n,n+=t.flow)}))}))}(i,n),{maxX:s,maxY:c}}function d(t,e){for(let o=0;o<t.length;o++)if(t[o].key===e)return t[o].addY;return 0}function u(t){return t&&-1!==[\"min\",\"max\"].indexOf(t)?t:\"max\"}class y extends t.DatasetController{parseObjectData(t,e,o,r){if(0===r)return[]",
		";const a=this,{xScale:n,yScale:i}=t,s=[],l=a._nodes=function(t){const e=new Map;for(let o=0;o<t.length;o++){const{from:r,to:a,flow:n}=t[o];if(e.has(r)){const t=e.get(r);t.out+=n,t.to.push({key:a,flow:n})}else e.set(r,{key:r,in:0,out:n,from:[],to:[{key:a,flow:n}]});if(e.has(a)){const t=e.get(a);t.in+=n,t.from.push({key:r,flow:n})}else e.set(a,{key:a,in:n,out:0,from:[{key:r,flow:n}],to:[]})}const o=(t,e)=>e.flow-t.flow;return[...e.values()].forEach((t=>{t.from=t.from.sort(o),t.from.forEach((t=>{t.node=e.get(t.key)})),t.to=t.to.sort(o),t.to.forEach((t=>{t.node=e.get(t.key)}))})),e}(e),{priority:c,size:h}=a.getDataset();if(c)for(const t of l.values())t.key in c&&(t.priority=c[t.key]);const{maxX:y,maxY:x}=f(l,e,!!c,u(h));a._maxX=y,a._maxY=x;for(let t=0,o=e.length;t<o;++t){const o=e[t],r=l.get(o.from),a=l.get(o.to),c=r.y+d(r.to,o.to),h=a.y+d(a.from,o.from);s.push({x:n.parse(r.x,t),y:i.parse(c,t),_custom:{from:r,to:a,x:n.parse(a.x,t),y:i.parse(h,t),height:i.parse(o.flow,t)}})}return s.slice(o,",
		"o+r)}getMinMax(t){return{min:0,max:t===this._cachedMeta.xScale?this._maxX:this._maxY}}update(t){const e=this._cachedMeta;this.updateElements(e.data,0,e.data.length,t)}updateElements(t,o,r,a){const n=this,{xScale:i,yScale:s}=n._cachedMeta,l=n.resolveDataElementOptions(o,a),c=n.getSharedOptions(a,t[o],l),h=n.getDataset(),f=e.valueOrDefault(h.borderWidth,1)/2+.5,d=e.valueOrDefault(h.nodeWidth,10);for(let e=o;e<o+r;e++){const o=n.getParsed(e),r=o._custom,l=s.getPixelForValue(o.y);n.updateElement(t[e],e,{x:i.getPixelForValue(o.x)+d+f,y:l,x2:i.getPixelForValue(r.x)-f,y2:s.getPixelForValue(r.y),from:r.from,to:r.to,progress:\"reset\"===a?0:1,height:Math.abs(s.getPixelForValue(o.y+r.height)-l),options:n.resolveDataElementOptions(e,a)},a)}n.updateSharedOptions(c,a)}_drawLabels(){const t=this,o=t._ctx,r=t._nodes||new Map,a=t.getDataset(),n=u(a.size),i=e.valueOrDefault(a.borderWidth,1),s=e.valueOrDefault(a.nodeWidth,10),l=a.labels,{xScale:c,yScale:h}=t._cachedMeta;o.save();const f=t.chart.chartArea",
		";for(const t of r.values()){const e=c.getPixelForValue(t.x),r=h.getPixelForValue(t.y),d=Math[n](t.in||t.out,t.out||t.in),u=Math.abs(h.getPixelForValue(t.y+d)-r),y=l&&l[t.key]||t.key;let x=e;o.fillStyle=a.color||\"black\",o.textBaseline=\"middle\",e<f.width/2?(o.textAlign=\"left\",x+=s+i+4):(o.textAlign=\"right\",x-=i+4),this._drawLabel(y,r,u,o,x)}o.restore()}_drawLabel(t,o,r,a,n){const i=this,s=e.toFont(i.options.font,i.chart.options.font),l=e.isNullOrUndef(t)?[]:i.toTextLines(t),c=l.length,h=o+r/2,f=s.lineHeight,d=e.valueOrDefault(i.options.padding,f/2);if(a.font=s.string,c>1){const t=h-f*c/2+d;for(let e=0;e<c;e++)a.fillText(l[e],n,t+e*f)}else a.fillText(t,n,h)}toTextLines(t){let o,r=[];for(t=[].concat(t);t.length;)o=t.pop(),\"string\"==typeof o?r.unshift.apply(r,o.split(\"\\n\")):Array.isArray(o)?t.push.apply(t,o):e.isNullOrUndef(t)||r.unshift(\"\"+o);return r}_drawNodes(){const t=this,o=t._ctx,r=t._nodes||new Map,a=t.getDataset(),n=u(a.size),{xScale:i,yScale:s}=t._cachedMeta,l=e.valu",
		"eOrDefault(a.borderWidth,1),c=e.valueOrDefault(a.nodeWidth,10);o.save(),o.strokeStyle=a.borderColor||\"black\",o.lineWidth=l;for(const t of r.values()){o.fillStyle=t.color;const e=i.getPixelForValue(t.x),r=s.getPixelForValue(t.y),a=Math[n](t.in||t.out,t.out||t.in),h=Math.abs(s.getPixelForValue(t.y+a)-r);l&&o.strokeRect(e,r,c,h),o.fillRect(e,r,c,h)}o.restore()}draw(){const t=this,e=t._ctx,o=t.getMeta().data||[];for(let t=0,e=o.length;t<e;++t){const e=o[t];e.from.color=e.options.colorFrom,e.to.color=e.options.colorTo}t._drawNodes();for(let t=0,r=o.length;t<r;++t)o[t].draw(e);t._drawLabels()}}y.id=\"sankey\",y.defaults={dataElementType:\"flow\",animations:{numbers:{type:\"number\",properties:[\"x\",\"y\",\"x2\",\"y2\",\"height\"]},progress:{easing:\"linear\",duration:t=>\"data\"===t.type?200*(t.parsed._custom.x-t.parsed.x):void 0,delay:t=>\"data\"===t.type?500*t.parsed.x+20*t.dataIndex:void 0},colors:{type:\"color\",properties:[\"colorFrom\",\"colorTo\"]}},transitions:{hide:{animations:{co",
		"lors:{type:\"color\",properties:[\"colorFrom\",\"colorTo\"],to:\"transparent\"}}},show:{animations:{colors:{type:\"color\",properties:[\"colorFrom\",\"colorTo\"],from:\"transparent\"}}}}},y.overrides={interaction:{mode:\"nearest\",intersect:!0},datasets:{color:()=>\"#efefef\",clip:!1,parsing:!0},plugins:{tooltip:{callbacks:{title:()=>\"\",label(t){const e=t.dataset.data[t.dataIndex];return e.from+\" -> \"+e.to+\": \"+e.flow}}},legend:{display:!1}},scales:{x:{type:\"linear\",bounds:\"data\",display:!1,min:0,offset:!1},y:{type:\"linear\",bounds:\"data\",display:!1,min:0,reverse:!0,offset:!1}},layout:{padding:{top:3,left:3,right:13,bottom:3}}};const x=(t,e,o,r)=>t<o?{cp1:{x:t+(o-t)/3*2,y:e},cp2:{x:t+(o-t)/3,y:r}}:{cp1:{x:t-(t-o)/3,y:0},cp2:{x:o+(t-o)/3,y:0}},p=(t,e,o)=>({x:t.x+o*(e.x-t.x),y:t.y+o*(e.y-t.y)});class g extends t.Element{constructor(t){super(),this.options=void 0,this.x=void 0,this.y=void 0,this.x2=void 0,this.y2=void 0,this.height=void 0,t&&Object.assign(this,t)}draw(t){const",
		"{x:o,x2:r,y:a,y2:n,height:i,progress:s}=this,{cp1:l,cp2:c}=x(o,a,r,n),h=this.options;if(0===s)return;let f;t.save(),s<1&&(t.beginPath(),t.rect(o,Math.min(a,n),(r-o)*s+1,Math.abs(n-a)+i+1),t.clip()),\"from\"===h.colorMode?f=e.color(h.colorFrom).alpha(.5).rgbString():\"to\"===h.colorMode?f=e.color(h.colorTo).alpha(.5).rgbString():(f=t.createLinearGradient(o,0,r,0),f.addColorStop(0,e.color(h.colorFrom).alpha(.5).rgbString()),f.addColorStop(1,e.color(h.colorTo).alpha(.5).rgbString())),t.fillStyle=f,t.strokeStyle=f,t.lineWidth=.5,t.beginPath(),t.moveTo(o,a),t.bezierCurveTo(l.x,l.y,c.x,c.y,r,n),t.lineTo(r,n+i),t.bezierCurveTo(c.x,c.y+i,l.x,l.y+i,o,a+i),t.lineTo(o,a),t.stroke(),t.closePath(),t.fill(),t.restore()}inRange(t,e,o){const{x:r,y:a,x2:n,y2:i,height:s}=this.getProps([\"x\",\"y\",\"x2\",\"y2\",\"height\"],o);if(t<r||t>n)return!1;const{cp1:l,cp2:c}=x(r,a,n,i),h=(t-r)/(n-r),f={x:n,y:i},d=p({x:r,y:a},l,h),u=p(l,c,h),y=p(c,f,h),g=p(d,u,h),m=p(u,y,h),M=p(g,m,h).y;return e>=M&&e<=M+s}inXRange",
		"(t,e){const{x:o,x2:r}=this.getProps([\"x\",\"x2\"],e);return t>=o&&t<=r}inYRange(t,e){const{y:o,y2:r,height:a}=this.getProps([\"y\",\"y2\",\"height\"],e),n=Math.min(o,r),i=Math.max(o,r)+a;return t>=n&&t<=i}getCenterPoint(t){const{x:e,y:o,x2:r,y2:a,height:n}=this.getProps([\"x\",\"y\",\"x2\",\"y2\",\"height\"],t);return{x:(e+r)/2,y:(o+a+n)/2}}tooltipPosition(t){return this.getCenterPoint(t)}getRange(t){return\"x\"===t?this.width/2:this.height/2}}g.id=\"flow\",g.defaults={colorFrom:\"red\",colorTo:\"green\",colorMode:\"gradient\"},t.Chart.register(y,g)}));"
	};
	
	/**
	 * Creates the injectable resource with <code>chartjs-chart-sankey.min.js</code> content.
	 */
	SankeyControllerResource() {
		super(ResourceName.SANKEY_CONTROLLER, CONTENT);
	}

}
