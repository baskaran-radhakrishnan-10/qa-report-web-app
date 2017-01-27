package com.equiniti.qa_report.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="restore_data")
public class RestoreDataWebController {
	
	@RequestMapping(value = "/show")
	public String showDSRPage(Model model){
		return "restore_data_page";
	}

}
