package com.equiniti.qa_report.enums;

public enum ExportFileEnum {

	BTP_SUMMARY("BTPSummaryReport.xlsx"),
	BTP_WEEKLY("BTPWeeklyReport.xlsx"),
	BTP_MONTHLY("BTPMonthlyReport.xlsx"),
	SINGLE_BTP_ROW("BTPSelectedRowReport.xlsx"),
	DSR_SUMMARY("DSRSummaryReport.xlsx"),
	DSR_DAY_REPORT("DSRSelectedRowReport.xlsx"),
	USER_SUMMARY("UserSummaryReport.xlsx");

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
