package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class PDFRecordCreator implements ReportCreator{

  @Override
  public Report createReport() {
    return new PDFReport();
  }
}
