package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class JSONReportCreator implements ReportCreator{

  @Override
  public Report createReport() {
    return new JSONReport();
  }
}
