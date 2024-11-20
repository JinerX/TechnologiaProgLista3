package eu.jpereira.trainings.designpatterns.creational.abstractfactory.xml;

import eu.jpereira.trainings.designpatterns.creational.abstractfactory.Report;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.ReportBuilder;

public class XMLReportBuilder implements ReportBuilder {

  @Override
  public void buildReport(Report report) {
    report.setBody(new XMLReportBody());
    report.setHeader(new XMLReportHeader());
    report.setFooter(new XMLReportFooter());
  }
}
