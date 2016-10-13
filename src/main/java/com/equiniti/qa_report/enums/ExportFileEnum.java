package com.equiniti.qa_report.enums;

public enum ExportFileEnum {

	BTP_SUMMARY("BTPSummaryReport.xlsx"),BTP_WEEKLY("BTPWeeklyReport.xlsx"),BTP_MONTHLY("BTPMonthlyReport.xlsx");

	private final String name;       

	private ExportFileEnum(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return (otherName == null) ? false : name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}

}