package eu.jpereira.trainings.designpatterns.creational.builder;

import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBody;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SaleEntry;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SoldItem;

import java.util.Iterator;

public class HTMLReportBody implements ReportBody {

	private StringBuffer delegate = new StringBuffer();

	@Override
	public Object getAsString() {
		return this.delegate .toString();
	}

	public void putContent(Object content) {
		this.delegate.append(content);
	}

	@Override
	public void assembleReport(SaleEntry saleEntry) {
		this.putContent("<span class=\"customerName\">");
		this.putContent(saleEntry.getCustomer().getName());
		this.putContent("</span><span class=\"customerPhone\">");
		this.putContent(saleEntry.getCustomer().getPhone());
		this.putContent("</span>");

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
		this.putContent("</items>");
	}

}
