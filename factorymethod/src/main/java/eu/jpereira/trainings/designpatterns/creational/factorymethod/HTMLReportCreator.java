package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class HTMLReportCreator implements ReportCreator {

  @Override
  public Report createReport() {
    return new HTMLReport();
  }
}
