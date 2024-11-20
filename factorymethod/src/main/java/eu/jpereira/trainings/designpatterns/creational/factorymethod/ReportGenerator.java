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
package eu.jpereira.trainings.designpatterns.creational.factorymethod;

import java.util.HashMap;
import java.util.Map;

/**
 * The Report Generator will create reports based on a given type
 * @author jpereira
 *
 */
public class ReportGenerator {
	private final Map<String, ReportCreator> creatorMap;

	public ReportGenerator() {
		creatorMap = new HashMap<String, ReportCreator>();
		creatorMap.put("JSON",new JSONReportCreator());
		creatorMap.put("XML",new XMLReportCreator());
		creatorMap.put("HTML",new HTMLReportCreator());
		creatorMap.put("PDF", new PDFRecordCreator());
	}

	/**
	 * Generate a new report.
	 * @param data The report data
	 * @param type the type of report
	 * @return the generated report, or null of type is unknown
	 * Changes:
	 * - added Hashmap instead of if-else for ease of modification
	 * - added <type>RecordCreator() classes for handling concrete implementation of making records
	 * - added polymorphic implementation of ReportCreator
	 */
	public Report generateReport(ReportData data, String type) {

		Report generatedReport = null;
		try {
			generatedReport = creatorMap.get(type).createReport();
		}
		catch (Exception e) {
			return generatedReport;
		}

		generatedReport.generateReport(data);
		return generatedReport;
	}
}
