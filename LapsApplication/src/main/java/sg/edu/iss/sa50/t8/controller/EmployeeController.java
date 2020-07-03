package sg.edu.iss.sa50.t8.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import sg.edu.iss.sa50.t8.model.*;
import sg.edu.iss.sa50.t8.model.Staff;
import sg.edu.iss.sa50.t8.service.IEmployeeService;
import sg.edu.iss.sa50.t8.service.LeaveServiceImpl;
import sg.edu.iss.sa50.t8.service.StaffService;
//split to architecture design controller
//need to discuss to shift methods to respective controllers
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	//move 2 login methods into LoginControllers
	// admin
	// rest of methods all moved into admin controller

	//employee
	@Autowired
	@Qualifier("staffService")
	protected IEmployeeService sservice;

	@Autowired
	public void setIStaffService(StaffService sservice) {
		this.sservice = sservice;
	}
	@Autowired
	protected LeaveServiceImpl leaveservice;

	//employee
	@RequestMapping("/leaves")
	public String Leaves() {
		return "leaves";
	}

	@RequestMapping("/employeelogin")
	public String LoginSuccessful(@ModelAttribute("employee") Employee emp, Model model,HttpSession session) {
		for(Employee e: ((StaffService)sservice).findAllNonAdminStaff()){
			System.out.println(e);
			if(emp.getName().equals(e.getName())){
				System.out.println("staff exist");
				if (emp.getPassword().equals(e.getPassword())){
					System.out.println("staff password correct");
					session.setAttribute("user",e);					
					return "leaves";
				}
				model.addAttribute("errorMsg","Name or Password is not correct. Pls try again. OR U ARE ADMIN :)");
				return "error";
			}
		}
		return "employeelogin";
	}

	@RequestMapping("/movement-register")
	public String movementregister(@SessionAttribute("user") Employee emp,Model model) {
		if(!emp.getDiscriminatorValue().equals("Admin")) {
			if(emp.getDiscriminatorValue().equals("staff")){
				Staff staff = (Staff) emp;			
				List<Staff> staffs = ((StaffService) sservice).findAllStaffbyManager(staff.getManager().getId());
				List<Leaves> leaves = new ArrayList<>();
				for(Staff s : staffs) {
					for(Leaves l: leaveservice.findAllLeavesByStaff(s)) {
						leaves.add(l);
					}
				}
				for(Leaves l: leaveservice.findAllLeavesByStaff(staff.getManager())) {
					leaves.add(l);
				}
				model.addAttribute("movelist",leaves);
				return "movement-register";
			}
			if(emp.getDiscriminatorValue().equals("Manager")){
				Manager manager = (Manager) emp;
				List<Staff> staffs = ((StaffService) sservice).findAllStaffbyManager(manager.getId());
				List<Leaves> leaves = new ArrayList<>();
				for(Staff s : staffs) {
					for(Leaves l: leaveservice.findAllLeavesByStaff(s)) {
						leaves.add(l);
					}
				}
				for(Leaves l: leaveservice.findAllLeavesByStaff(manager)) {
					leaves.add(l);
				}
				model.addAttribute("movelist",leaves);
				return "movement-register";
			}

		}
		model.addAttribute("errorMsg","You have no access");
		return "error";

	}
}