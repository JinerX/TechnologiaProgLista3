package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class XMLReportCreator implements ReportCreator {


  @Override
  public Report createReport() {
    return new XMLReport();
  }
}
