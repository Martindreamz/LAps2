package sg.edu.iss.sa50.t8.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.sa50.t8.model.Admin;
import sg.edu.iss.sa50.t8.model.Employee;
import sg.edu.iss.sa50.t8.model.Staff;
import sg.edu.iss.sa50.t8.service.AdminService;
import sg.edu.iss.sa50.t8.service.IEmployeeService;

// a) Manage Leave Type (CRUD) - Joe 
// b) Manage Staff (CRUD)- Joe 
// c) Manage Leave Entitlement (CRUD) - Daryl 
// d) Manage Role and Approval Hierarchy (CRUD) - Manager, Staff and Admin - by Daryl 
// e) Enter / Update Employee annual leave entitlement for the year 
// f) Enter in calendar of public holidays (so that leave will not count in Public Holidays that happen on weekdays) 


@Controller
@RequestMapping("/employee")
public class AdminController {

	@Autowired
	@Qualifier("adminService")
	protected IEmployeeService aservice;

	@Autowired
	public void setILeaveService(AdminService aservice) {
		this.aservice = aservice;
	}

	//admin
	@RequestMapping("/admin")
	public String admin(@ModelAttribute("employee") Employee emp,HttpSession session,Model model) {
		for(Admin a :((AdminService) aservice).findallAdmin()){
			System.out.println(a);
			if(emp.getName().equals(a.getName())){
				System.out.println("admin name exist");
				if (emp.getPassword().equals(a.getPassword())){
					System.out.println("admin password correct");

					session.setAttribute("user",a);					
					return "admin";
				}
			}
		}

		model.addAttribute("errorMsg","Password is not correct. Pls try again.");
		return "error";
	}





//	@RequestMapping("/admin-delete/{id}")
//	public String delete(@PathVariable("id") int id, Model model) {
//		model.addAttribute("employee", ((AdminService) aservice).findById(id));
//		return "dashboard";
//	}

	@RequestMapping("/admin-edit/{id}")
	public String editAdmin(@PathVariable("id") int id, Model model) {
		model.addAttribute("admin", ((AdminService) aservice).findAdminById(id));
		return "admin-edit";
	}
	
	@RequestMapping("/staff-edit/{id}")
	public String editStaff(@PathVariable("id") int id, Model model) {
		model.addAttribute("staff", ((AdminService) aservice).findStaffById(id));
		return "staff-edit";
	}


	@RequestMapping("/search-employee")
	public String searchEmployee(@RequestParam("searchTerm") String searchTerm, Model model) {
		model.addAttribute("employeeList", ((AdminService) aservice).searchEmployee(searchTerm));
		return "dashboard";
	}
	@RequestMapping("/dashboard")
	public String dashboard(Model model) {
		model.addAttribute("employeeList", ((AdminService) aservice).findAll());
		return "dashboard";
	}


	@RequestMapping("/admin-create")
	public String create(Model model) {
		model.addAttribute("employee", new Staff()); 
		return "admin-create";
	}
	
	@RequestMapping("/admin-createadmin")
	public String createadmin(Model model) {
		model.addAttribute("employee", new Admin()); 
		return "admin-createadmin";
	}






	@RequestMapping("/save-admin")
	public String saveAdmin(@ModelAttribute("employee") Employee employee, Model model) {
		Admin toSave = ((AdminService) aservice).findAdminById(employee.getId());
		toSave.setName(employee.getName());
		toSave.setPassword(employee.getPassword());
		toSave.setEmail(employee.getEmail());
		if(((AdminService) aservice).save(toSave)) {
			model.addAttribute("employeeList", ((AdminService) aservice).findAll());
			return "dashboard";
		}
		else {
			model.addAttribute("employee", toSave);
			return "admin-edit";
		}
		
	}
	
	@RequestMapping("/save-staff")
	public String savestaff(@ModelAttribute("employee") Employee employee, Model model) {
		Admin toSave = ((AdminService) aservice).findAdminById(employee.getId());
		toSave.setName(employee.getName());
		toSave.setPassword(employee.getPassword());
		toSave.setEmail(employee.getEmail());
		if(((AdminService) aservice).save(toSave)) {
			model.addAttribute("employeeList", ((AdminService) aservice).findAll());
			return "dashboard";
		}
		else {
			model.addAttribute("employee", toSave);
			return "admin-edit";
		}
		
	}
	
	@RequestMapping("/save-adminnew")
	public String saveAdminnew(@ModelAttribute("employee") Employee employee, Model model) {
		Admin toSave = ((AdminService) aservice).findAdminById(employee.getId());
		toSave.setName(employee.getName());
		toSave.setPassword(employee.getPassword());
		toSave.setEmail(employee.getEmail());
		if(((AdminService) aservice).save(toSave)) {
			model.addAttribute("employeeList", ((AdminService) aservice).findAll());
			return "dashboard";
		}
		else {
			model.addAttribute("employee", toSave);
			return "admin-edit";
		}
		
	}
	
	@RequestMapping("/save-staffnew")
	public String savestaffnew(@ModelAttribute("employee") Employee employee, Model model) {
		Admin toSave = ((AdminService) aservice).findAdminById(employee.getId());
		toSave.setName(employee.getName());
		toSave.setPassword(employee.getPassword());
		toSave.setEmail(employee.getEmail());
		if(((AdminService) aservice).save(toSave)) {
			model.addAttribute("employeeList", ((AdminService) aservice).findAll());
			return "dashboard";
		}
		else {
			model.addAttribute("employee", toSave);
			return "admin-edit";
		}
		
	}
		
//		@RequestMapping("/create")
	//	public String createStaff(@ModelAttribute("staff") Staff staff, Model model) {
//			Staff toCreate = ((AdminService) aservice).findStaffById(staff.getId());
//			toCreate.setName(staff.getName());
//			toCreate.setPassword(staff.getPassword());
//			toCreate.setEmail(staff.getEmail());
//			toCreate.setAnnualLeaveDays(staff.getAnnualLeaveDays());
//			if(((AdminService) aservice).save(toCreate)) {
//				return "forward:/employee/dashboard";
//			}
//			else {
//				model.addAttribute("staff", toCreate);
//				return "staff-edit";
//			}
//		}
			
			@RequestMapping("admin-delete/{id}")
			public String deleteStaff(@ModelAttribute("staff") Staff staff, Model model) {
				Staff toDelete = ((AdminService) aservice).findStaffById(staff.getId());
				if(((AdminService) aservice).delete(toDelete)) {
					model.addAttribute("employeeList", ((AdminService) aservice).findAll());
					return "dashboard";
				}
				else {
					model.addAttribute("staff", toDelete);
					return "dashboard";
				}
	}


}


