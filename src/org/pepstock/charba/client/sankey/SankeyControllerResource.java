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
		" * chartjs-chart-sankey v0.7.1",
		" * https://github.com/kurkle/chartjs-chart-sankey#readme",
		" * (c) 2021 Jukka Kurkela",
		" * Released under the MIT license",
		" */",
		"!function(t,e){\"object\"==typeof exports&&\"undefined\"!=typeof module?e(require(\"chart.js\"),require(\"chart.js/helpers\")):\"function\"==typeof define&&define.amd?define([\"chart.js\",\"chart.js/helpers\"],e):e((t=\"undefined\"!=typeof globalThis?globalThis:t||self).Chart,t.Chart.helpers)}(this,(function(t,e){\"use strict\";function o(t,e){const o=t.filter((t=>!e.has(t)));return o.length?o:t.slice(0,1)}const r=t=>void 0!==t,a=(t,e)=>t.x!==e.x?t.x-e.x:t.y-e.y,n=(t,e)=>t.reduce(((t,o)=>t+o.node[e].length+n(o.node[e],e)),0),s=t=>(e,o)=>n(e.node[t],t)-n(o.node[t],t);function i(t,e){return t.from.sort(s(\"from\")).forEach((t=>{const o=t.node;r(o.y)||(o.y=e,e=Math.max(e+o.out,i(o,e)))})),e}function l(t,e){return t.to.sort(s(\"to\")).forEach((t=>{const o=t.node;r(o.y)||(o.y=e,e=Math.max(e+o.in,l(o,e)))})),e}function c(t,e){return r(t.y)?t.y:(t.y=e,e)}function h(t,e){const o=function(t){return t.sort(((t,e)=>Math.max(e.in,e.out)-Math.max(t.in,t.out)))[0]}(t);o.y=0;const a=i(o,0),n=l(o,0),s=",
		"function(t,e){const o=t.filter((t=>0===t.x)),a=t.filter((t=>t.x===e)),n=o.filter((t=>!r(t.y))),s=a.filter((t=>!r(t.y)));let i=o.reduce(((t,e)=>Math.max(t,e.y+e.out||0)),0),h=a.reduce(((t,e)=>Math.max(t,e.y+e.in||0)),0);return i>=h?(n.forEach((t=>{i=c(t,i),i=Math.max(i+t.out,l(t,i))})),s.forEach((t=>{h=c(t,h),h=Math.max(h+t.in,l(t,h))}))):(s.forEach((t=>{h=c(t,h),h=Math.max(h+t.in,l(t,h))})),n.forEach((t=>{i=c(t,i),i=Math.max(i+t.out,l(t,i))}))),Math.max(i,h)}(t,e);return Math.max(a,n,s)}function f(t,e,r){const n=[...t.values()],s=function(t,e){const r=new Set(e.map((t=>t.to))),a=new Set(e.map((t=>t.from))),n=new Set([...t.keys()]);let s=0;for(;n.size;){const a=o([...n],r);for(let e=0;e<a.length;e++)t.get(a[e]).x=s,n.delete(a[e]);n.size&&(r.clear(),e.filter((t=>n.has(t.from))).forEach((t=>r.add(t.to))),s++)}return[...t.keys()].filter((t=>!a.has(t))).forEach((e=>{t.get(e).x=s})),s}(t,e),i=r?function(t,e){let o=0;for(let r=0;r<=e;r++){let e=0;const a=t.filter((t=>t.x===r)).sort(((t,e)=>t.p",
		"riority-e.priority));for(const t of a)t.y=e,e+=Math.max(t.out,t.in);o=Math.max(e,o)}return o}(n,s):h(n,s),l=function(t,e){let o=1,r=0,n=0,s=0;const i=[];return t.sort(a).forEach((t=>{if(t.y){if(0===t.x)i.push(t.y);else{for(r!==t.x&&(r=t.x,n=0),o=n+1;o<i.length&&!(i[o]>t.y);o++);n=o}t.y+=o*e,o++}s=Math.max(s,t.y+Math.max(t.in,t.out))})),s}(n,.03*i);return function(t){t.forEach((t=>{let e=0;t.from.sort(((t,e)=>t.node.y+t.node.out/2-(e.node.y+e.node.out/2))).forEach((t=>{t.addY=e,e+=t.flow})),e=0,t.to.sort(((t,e)=>t.node.y+t.node.in/2-(e.node.y+e.node.in/2))).forEach((t=>{t.addY=e,e+=t.flow}))}))}(n),{maxX:s,maxY:l}}function d(t,e){for(let o=0;o<t.length;o++)if(t[o].key===e)return t[o].addY;return 0}class u extends t.DatasetController{parseObjectData(t,e,o,r){if(0===r)return[];const a=this,{xScale:n,yScale:s}=t,i=[],l=a._nodes=function(t){const e=new Map;for(let o=0;o<t.length;o++){const{from:r,to:a,flow:n}=t[o];if(e.has(r)){const t=e.get(r);t.out+=n,t.to.push({key:a,flow:n})}else e.set(r,",
		"{key:r,in:0,out:n,from:[],to:[{key:a,flow:n}]});if(e.has(a)){const t=e.get(a);t.in+=n,t.from.push({key:r,flow:n})}else e.set(a,{key:a,in:n,out:0,from:[{key:r,flow:n}],to:[]})}const o=(t,e)=>e.flow-t.flow;return[...e.values()].forEach((t=>{let r=0;t.from=t.from.sort(o),t.from.forEach((t=>{t.node=e.get(t.key),t.addY=r,r+=t.flow})),r=0,t.to=t.to.sort(o),t.to.forEach((t=>{t.node=e.get(t.key),t.addY=r,r+=t.flow}))})),e}(e),c=a.getDataset().priority;if(c)for(const t of l.values())t.key in c&&(t.priority=c[t.key]);const{maxX:h,maxY:u}=f(l,e,!!c);a._maxX=h,a._maxY=u;for(let t=0,o=e.length;t<o;++t){const o=e[t],r=l.get(o.from),a=l.get(o.to),c=r.y+d(r.to,o.to),h=a.y+d(a.from,o.from);i.push({x:n.parse(r.x,t),y:s.parse(c,t),_custom:{from:r,to:a,x:n.parse(a.x,t),y:s.parse(h,t),height:s.parse(o.flow,t)}})}return i.slice(o,o+r)}getMinMax(t){return{min:0,max:t===this._cachedMeta.xScale?this._maxX:this._maxY}}update(t){const e=this._cachedMeta;this.updateElements(e.data,0,e.data.length,t)}updateElements",
		"(t,o,r,a){const n=this,{xScale:s,yScale:i}=n._cachedMeta,l=n.resolveDataElementOptions(o,a),c=n.getSharedOptions(a,t[o],l),h=n.getDataset(),f=e.valueOrDefault(h.borderWidth,1)/2+.5,d=e.valueOrDefault(h.nodeWidth,10);for(let e=o;e<o+r;e++){const o=n.getParsed(e),r=o._custom,l=i.getPixelForValue(o.y);n.updateElement(t[e],e,{x:s.getPixelForValue(o.x)+d+f,y:l,x2:s.getPixelForValue(r.x)-f,y2:i.getPixelForValue(r.y),from:r.from,to:r.to,progress:\"reset\"===a?0:1,height:Math.abs(i.getPixelForValue(o.y+r.height)-l),options:n.resolveDataElementOptions(e,a)},a)}n.updateSharedOptions(c,a)}_drawLabels(){const t=this,o=t._ctx,r=t._nodes||new Map,a=t.getDataset(),n=e.valueOrDefault(a.borderWidth,1),s=e.valueOrDefault(a.nodeWidth,10),i=a.labels,{xScale:l,yScale:c}=t._cachedMeta;o.save();const h=t.chart.chartArea;for(const t of r.values()){const e=l.getPixelForValue(t.x),r=c.getPixelForValue(t.y),f=Math.max(t.in,t.out),d=Math.abs(c.getPixelForValue(t.y+f)-r),u=i&&i[t.key]||t.key;let y=e;o.fillStyle=a.c",
		"olor||\"black\",o.textBaseline=\"middle\",e<h.width/2?(o.textAlign=\"left\",y+=s+n+4):(o.textAlign=\"right\",y-=n+4),this._drawLabel(u,r,d,o,y)}o.restore()}_drawLabel(t,o,r,a,n){const s=this.options.font,i=e.isNullOrUndef(t)?[]:this.toTextLines(t),l=i.length,c=o+r/2,h=s.size;if(a.font=e.toFontString(s),l>1){const t=c-h*l/2+7.5;for(let e=0;e<l;e++)a.fillText(i[e],n,t+e*h)}else a.fillText(t,n,c)}toTextLines(t){let o,r=[];for(t=[].concat(t);t.length;)o=t.pop(),\"string\"==typeof o?r.unshift.apply(r,o.split(\"\\n\")):Array.isArray(o)?t.push.apply(t,o):e.isNullOrUndef(t)||r.unshift(\"\"+o);return r}_drawNodes(){const t=this,o=t._ctx,r=t._nodes||new Map,a=t.getDataset(),{xScale:n,yScale:s}=t._cachedMeta,i=e.valueOrDefault(a.borderWidth,1),l=e.valueOrDefault(a.nodeWidth,10);o.save(),o.strokeStyle=a.borderColor||\"black\",o.lineWidth=i;for(const t of r.values()){o.fillStyle=t.color;const e=n.getPixelForValue(t.x),r=s.getPixelForValue(t.y),a=Math.max(t.in,t.out),c=Math.abs(s.getPixelForValue(t.y",
		"+a)-r);i&&o.strokeRect(e,r,l,c),o.fillRect(e,r,l,c)}o.restore()}draw(){const t=this,e=t._ctx,o=t.getMeta().data||[];for(let t=0,e=o.length;t<e;++t){const e=o[t];e.from.color=e.options.colorFrom,e.to.color=e.options.colorTo}t._drawNodes();for(let t=0,r=o.length;t<r;++t)o[t].draw(e);t._drawLabels()}}u.id=\"sankey\",u.defaults={dataElementType:\"flow\",animations:{numbers:{type:\"number\",properties:[\"x\",\"y\",\"x2\",\"y2\",\"height\"]},progress:{easing:\"linear\",duration:t=>\"data\"===t.type?200*(t.parsed._custom.x-t.parsed.x):void 0,delay:t=>\"data\"===t.type?500*t.parsed.x+20*t.dataIndex:void 0},colors:{type:\"color\",properties:[\"colorFrom\",\"colorTo\"]}},transitions:{hide:{animations:{colors:{type:\"color\",properties:[\"colorFrom\",\"colorTo\"],to:\"transparent\"}}},show:{animations:{colors:{type:\"color\",properties:[\"colorFrom\",\"colorTo\"],from:\"transparent\"}}}}},u.overrides={interaction:{mode:\"nearest\",intersect:!0},datasets:{color:()=>\"#efefef\",clip:!1,parsing:!0},p",
		"lugins:{tooltip:{callbacks:{title:()=>\"\",label(t){const e=t.dataset.data[t.dataIndex];return e.from+\" -> \"+e.to+\": \"+e.flow}}},legend:{display:!1}},scales:{x:{type:\"linear\",bounds:\"data\",display:!1,min:0,offset:!1},y:{type:\"linear\",bounds:\"data\",display:!1,min:0,reverse:!0,offset:!1}},layout:{padding:{top:3,left:3,right:13,bottom:3}}};const y=(t,e,o,r)=>t<o?{cp1:{x:t+(o-t)/3*2,y:e},cp2:{x:t+(o-t)/3,y:r}}:{cp1:{x:t-(t-o)/3,y:0},cp2:{x:o+(t-o)/3,y:0}},x=(t,e,o)=>({x:t.x+o*(e.x-t.x),y:t.y+o*(e.y-t.y)});class p extends t.Element{constructor(t){super(),this.options=void 0,this.x=void 0,this.y=void 0,this.x2=void 0,this.y2=void 0,this.height=void 0,t&&Object.assign(this,t)}draw(t){const{x:o,x2:r,y:a,y2:n,height:s,progress:i}=this,{cp1:l,cp2:c}=y(o,a,r,n),h=this.options;if(0===i)return;let f;t.save(),i<1&&(t.beginPath(),t.rect(o,Math.min(a,n),(r-o)*i+1,Math.abs(n-a)+s+1),t.clip()),\"from\"===h.colorMode?f=e.color(h.colorFrom).alpha(.5).rgbString():\"to\"===h.colorMode?f=e.color(h",
		".colorTo).alpha(.5).rgbString():(f=t.createLinearGradient(o,0,r,0),f.addColorStop(0,e.color(h.colorFrom).alpha(.5).rgbString()),f.addColorStop(1,e.color(h.colorTo).alpha(.5).rgbString())),t.fillStyle=f,t.strokeStyle=f,t.lineWidth=.5,t.beginPath(),t.moveTo(o,a),t.bezierCurveTo(l.x,l.y,c.x,c.y,r,n),t.lineTo(r,n+s),t.bezierCurveTo(c.x,c.y+s,l.x,l.y+s,o,a+s),t.lineTo(o,a),t.stroke(),t.closePath(),t.fill(),t.restore()}inRange(t,e,o){const{x:r,y:a,x2:n,y2:s,height:i}=this.getProps([\"x\",\"y\",\"x2\",\"y2\",\"height\"],o);if(t<r||t>n)return!1;const{cp1:l,cp2:c}=y(r,a,n,s),h=(t-r)/(n-r),f={x:n,y:s},d=x({x:r,y:a},l,h),u=x(l,c,h),p=x(c,f,h),g=x(d,u,h),m=x(u,p,h),M=x(g,m,h).y;return e>=M&&e<=M+i}inXRange(t,e){const{x:o,x2:r}=this.getProps([\"x\",\"x2\"],e);return t>=o&&t<=r}inYRange(t,e){const{y:o,y2:r,height:a}=this.getProps([\"y\",\"y2\",\"height\"],e),n=Math.min(o,r),s=Math.max(o,r)+a;return t>=n&&t<=s}getCenterPoint(t){const{x:e,y:o,x2:r,y2:a,height:n}=this.getProps([\"x\",\"y\",\"x2\",\"y2\"",
		",\"height\"],t);return{x:(e+r)/2,y:(o+a+n)/2}}tooltipPosition(t){return this.getCenterPoint(t)}getRange(t){return\"x\"===t?this.width/2:this.height/2}}p.id=\"flow\",p.defaults={colorFrom:\"red\",colorTo:\"green\",colorMode:\"gradient\"},t.Chart.register(u,p)}));"
	};
	
	/**
	 * Creates the injectable resource with <code>chartjs-chart-sankey.min.js</code> content.
	 */
	SankeyControllerResource() {
		super(ResourceName.SANKEY_CONTROLLER, CONTENT);
	}

}
