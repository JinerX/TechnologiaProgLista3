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
package eu.jpereira.trainings.designpatterns.creational.prototype.model;

import java.util.ArrayList;
import java.util.List;

import eu.jpereira.trainings.designpatterns.creational.prototype.VehiclePart;
import eu.jpereira.trainings.designpatterns.creational.prototype.exception.VehicleDoesNotHavePartsException;

/**
 * @author jpereira
 * 
 */
public class Vehicle implements Cloneable {

	private List<VehiclePart> parts;

	/**
	 * @param vehicleParts
	 */
	public void setParts(List<VehiclePart> vehicleParts) {
		// copy the reference for a builded list of created parts
		this.parts = vehicleParts;

	}

	/**
	 * @return
	 */
	public Object countParts() throws VehicleDoesNotHavePartsException {
		if (this.parts != null) {
			return this.parts.size();
		} else {
			 throw new VehicleDoesNotHavePartsException();
		}

	}

	/**
	 * @param class1
	 * @return
	 * @throws VehicleDoesNotHavePartsException 
	 */
	public List<VehiclePart> getParts(VehiclePartEnumeration type) throws VehicleDoesNotHavePartsException {
		if (this.parts == null) {
			 throw new VehicleDoesNotHavePartsException();
		}
		List<VehiclePart> match = new ArrayList<VehiclePart>();
		for (VehiclePart vehiclePart : this.parts ) {
			if ( vehiclePart.is(type)) {
				match.add(vehiclePart);
			}
		}
		return match;
	}

	public List<VehiclePart> getAllParts() throws VehicleDoesNotHavePartsException {
		if (this.parts == null) {
			throw new VehicleDoesNotHavePartsException();
		}
		ArrayList<VehiclePart> retParts = new ArrayList<VehiclePart>();
		retParts.addAll(this.parts);
		return retParts;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		//copying the vehicle itself
		Vehicle cloned = (Vehicle) super.clone(); // Perform a shallow copy

		//ensuring that the parts copied are new objects, changing object in the copied class will not change values in the copy
		if (this.parts != null) {
			List<VehiclePart> clonedParts = new ArrayList<VehiclePart>();
			for (VehiclePart part : this.parts) {
				try {
					clonedParts.add((VehiclePart) part.clone());
				} catch (CloneNotSupportedException e) {
					throw new RuntimeException("VehiclePart does not support cloning", e);
				}
			}
			cloned.setParts(clonedParts);
		} else {
			cloned.setParts(null);;
		}

		return cloned;
	}

}
