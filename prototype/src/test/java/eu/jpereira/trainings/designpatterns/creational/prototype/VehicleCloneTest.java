package eu.jpereira.trainings.designpatterns.creational.prototype;

import eu.jpereira.trainings.designpatterns.creational.prototype.exception.VehicleDoesNotHavePartsException;
import eu.jpereira.trainings.designpatterns.creational.prototype.model.Shell;
import eu.jpereira.trainings.designpatterns.creational.prototype.model.Tire;
import eu.jpereira.trainings.designpatterns.creational.prototype.model.Vehicle;
import eu.jpereira.trainings.designpatterns.creational.prototype.model.VehiclePartEnumeration;
import org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VehicleCloneTest {
  @Test
  public void testClone() throws CloneNotSupportedException, VehicleDoesNotHavePartsException {
    Vehicle vehicle = new Vehicle();
    ArrayList<VehiclePart> vehicleParts = new ArrayList<VehiclePart>();
    vehicleParts.add(new Shell());
    vehicleParts.add(new Tire());
    vehicle.setParts(vehicleParts);
    Object clone = vehicle.clone();

    assertTrue(clone instanceof Vehicle);
    assertEquals(((Vehicle) clone).getAllParts().size(),vehicle.getAllParts().size());
  }
}
