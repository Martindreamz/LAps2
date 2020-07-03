package sg.edu.iss.sa50.t8.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity
@DiscriminatorValue("Staff")
public class Staff extends Employee{
	
	//Check & Edit by Bianca: add validation and mapping setting
	
	//managerid is the FK need to build mapping 
	@ManyToOne  @JoinColumn(name="manager_id")
	private Manager manager;
	
	@OneToMany(mappedBy="staff") 
	private List<Leaves> leaves;
 
	private int TotalAnnualLeaves;
	private int TotalMedicalLeaves;
	private long CurrentAnnualLeaves;
	private long CurrentMedicalLeaves;
	
	
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Staff() {
	}
	public Staff(String name, String email) {
		super(name,email);
	}
	//Bianca New constructors
	public Staff(String name, String email,Manager manager, Long annualLeaveDays, Long medicalLeaveDays) {
		super(name,email,annualLeaveDays,medicalLeaveDays);
		this.manager = manager;
	}



	public int getTotalAnnualLeaves() {
		return TotalAnnualLeaves;
	}

	public void setTotalAnnualLeaves(int totalAnnualLeaves) {
		TotalAnnualLeaves = totalAnnualLeaves;
	}

	public int getTotalMedicalLeaves() {
		return TotalMedicalLeaves;
	}

	public void setTotalMedicalLeaves(int totalMedicalLeaves) {
		TotalMedicalLeaves = totalMedicalLeaves;
	}

	
}
