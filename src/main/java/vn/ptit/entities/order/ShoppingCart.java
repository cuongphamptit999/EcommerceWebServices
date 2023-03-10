package vn.ptit.entities.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Cart")
public class ShoppingCart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "DateCreate", nullable = false)
	private Date dateCreate;
	
	@Column(name = "TotalAmount", nullable = false)
	private double totalAmount;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shoppingCart", fetch = FetchType.LAZY)
	private List<LineElectronicsItem> lineElectronicsItems = new ArrayList<>();
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shoppingCart", fetch = FetchType.LAZY)
	private List<LineBookItem> lineBookItems = new ArrayList<>();
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shoppingCart", fetch = FetchType.LAZY)
	private List<LineShoesItem> lineShoesItems = new ArrayList<>();
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shoppingCart", fetch = FetchType.LAZY)
	private List<LineClothesItem> lineClothesItems = new ArrayList<>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<LineElectronicsItem> getLineElectronicsItems() {
		return lineElectronicsItems;
	}

	public void setLineElectronicsItems(List<LineElectronicsItem> lineElectronicsItems) {
		this.lineElectronicsItems = lineElectronicsItems;
	}

	public List<LineBookItem> getLineBookItems() {
		return lineBookItems;
	}

	public void setLineBookItems(List<LineBookItem> lineBookItems) {
		this.lineBookItems = lineBookItems;
	}

	public List<LineShoesItem> getLineShoesItems() {
		return lineShoesItems;
	}

	public void setLineShoesItems(List<LineShoesItem> lineShoesItems) {
		this.lineShoesItems = lineShoesItems;
	}

	public List<LineClothesItem> getLineClothesItems() {
		return lineClothesItems;
	}

	public void setLineClothesItems(List<LineClothesItem> lineClothesItems) {
		this.lineClothesItems = lineClothesItems;
	}

	@Override
	public String toString() {
		return "ShoppingCart [id=" + id + ", dateCreate=" + dateCreate + ", totalAmount=" + totalAmount
				+ ", lineElectronicsItems=" + lineElectronicsItems + ", lineBookItems=" + lineBookItems
				+ ", lineShoesItems=" + lineShoesItems + ", lineClothesItems=" + lineClothesItems + "]";
	}	
	
}
