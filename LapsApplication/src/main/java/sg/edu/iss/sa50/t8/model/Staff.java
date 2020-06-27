package sg.edu.iss.sa50.t8.model;

import java.util.List;

public class Staff extends Employee{
	private int managerid;
	//the above managerid is the FK 
	private List<Leaves> leaves;
	private List<CompensationLeave> compensationleaves;
	private int annualLeaveDays;
	private int medicalLeaveDays; 
	
	public Staff() {
		// TODO Auto-generated constructor stub
	}


	public int getManagerid() {
		return managerid;
	}


	public void setManagerid(int managerid) {
		this.managerid = managerid;
	}


	public List<CompensationLeave> getCompensationleaves() {
		return compensationleaves;
	}


	public void setCompensationleaves(List<CompensationLeave> compensationleaves) {
		this.compensationleaves = compensationleaves;
	}


	public int getAnnualLeaveDays() {
		return annualLeaveDays;
	}


	public void setAnnualLeaveDays(int annualLeaveDays) {
		this.annualLeaveDays = annualLeaveDays;
	}


	public int getMedicalLeaveDays() {
		return medicalLeaveDays;
	}


	public void setMedicalLeaveDays(int medicalLeaveDays) {
		this.medicalLeaveDays = medicalLeaveDays;
	}

}
