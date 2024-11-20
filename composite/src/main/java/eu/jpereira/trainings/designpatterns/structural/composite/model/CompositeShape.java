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
package eu.jpereira.trainings.designpatterns.structural.composite.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author Joao Pereira
 * 
 */
public abstract class CompositeShape extends Shape {

	private final List<Shape> shapes;

	public CompositeShape() {
		this.shapes = createShapesList();
	}

	/**
	 * Remove a shape from this shape childrens
	 * 
	 * @param shape
	 *            the shape to remove
	 * @return true if the shape was present and was removed, false if the shape
	 *         was not present
	 */
	public boolean removeShape(Shape shape) {
		if (this.shapes.contains(shape)) {
			shapes.remove(shape);
			return true;
		}
		return false;
	}

	/**
	 * Return the total shapes count in case this is a composite
	 * 
	 * @return the total count of shapes if the shape is composite. -1 otherwise
	 */
	public int getShapeCount() {
		return shapes.size();
	}

	/**
	 * Add a shape to this shape.
	 * 
	 * @param shape
	 *            The shape to add
	 * @throws ShapeDoesNotSupportChildren
	 *             if this shape is not a composite
	 */
	public void addShape(Shape shape) {
		if (shape==null){
			throw new IllegalArgumentException("Shape cannot be null");
		}
		this.shapes.add(shape);
	}

	public List<Shape> getShapes() {
		ArrayList<Shape> shapesRet = new ArrayList<Shape>();

		return this.shapes;
	}

	/**
	 * @param circle
	 * changes: self explanatory, added code
	 */
	public List<Shape> getShapesByType(ShapeType type) {
		ArrayList<Shape> shapesRet = new ArrayList<Shape>();
		for (Shape s : this.shapes) {
			if (s.getType()==type){
				shapesRet.add(s);
			}
		}
		return shapesRet;
	}

	/**
	 * Return all shapes that are leafs in the tree
	 * 
	 * @return
	 * changes: added code
	 */
	public List<Shape> getLeafShapes() {
		List<Shape> shapes = new ArrayList<Shape>();
		for (Shape shape : getShapes()) {
			if (shape instanceof LeafShape) {
				shapes.add(shape);
			}
		}
		return shapes;
	}

	/**
	 * Factory method for the list of children of this shape
	 * 
	 * @return
	 */
	protected List<Shape> createShapesList() {
		return new ArrayList<Shape>();
	}

	/**
	 *
	 * @param dx
	 * @param dy
	 * changes: added code for moving child shapes of composite shape
	 */
	public void moveShapes(int dx, int dy) {
		for (Shape shape : shapes) {
			shape.move(dx, dy);
		}
	}
}
