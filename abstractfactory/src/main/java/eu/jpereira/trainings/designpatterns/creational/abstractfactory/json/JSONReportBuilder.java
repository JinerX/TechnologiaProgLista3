package eu.jpereira.trainings.designpatterns.creational.abstractfactory.json;

import eu.jpereira.trainings.designpatterns.creational.abstractfactory.Report;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.ReportBuilder;

public class JSONReportBuilder  implements ReportBuilder {

  @Override
  public void buildReport(Report report) {
    report.setBody(new JSONReportBody());
    report.setHeader(new JSONReportHeader());
    report.setFooter(new JSONReportFooter());
  }
}
