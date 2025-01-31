/**
 * Copyright 2011 Joao Miguel Pereira
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package eu.jpereira.trainings.designpatterns.creational.builder.xml;

import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBody;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SaleEntry;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SoldItem;

import java.util.Iterator;

/**
 * This class is for demonstration purpose only!!
 * 
 * @author jpereira
 * 
 */
public class XMLReportBody implements ReportBody {

	private StringBuilder stringBuilder = new StringBuilder();

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.jpereira.trainings.designpatterns.creational.builder.ReportBody#
	 * getAsString()
	 */
	@Override
	public Object getAsString() {
		return this.stringBuilder.toString();
	}

	public void putContent(Object content) {
		this.stringBuilder.append(content);
	}

	@Override
	public void assembleReport(SaleEntry saleEntry) {
		this.putContent("<sale><customer><name>");
		this.putContent(saleEntry.getCustomer().getName());
		this.putContent("</name><phone>");
		this.putContent(saleEntry.getCustomer().getPhone());
		this.putContent("</phone></customer>");

		this.putContent("<items>");

		Iterator<SoldItem> it = saleEntry.getSoldItems().iterator();
		while ( it.hasNext() ) {
			SoldItem soldEntry= it.next();
			this.putContent("<item><name>");
			this.putContent(soldEntry.getName());
			this.putContent("</name><quantity>");
			this.putContent(soldEntry.getQuantity());
			this.putContent("</quantity><price>");
			this.putContent(soldEntry.getUnitPrice());
			this.putContent("</price></item>");
		}
		this.putContent("</items></sale>");
	}

}
