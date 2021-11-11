package vn.ptit.entities.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import vn.ptit.entities.electronics.ElectronicsItem;

@Entity
@Table(name = "LineElectronicsItem")
public class LineElectronicsItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "Quanity")
	private int quanity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ElectronicsItemBarCode")
	private ElectronicsItem electronicsItem;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CartID")
	private ShoppingCart shoppingCart;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuanity() {
		return quanity;
	}

	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}

	public ElectronicsItem getElectronicsItem() {
		return electronicsItem;
	}

	public void setElectronicsItem(ElectronicsItem electronicsItem) {
		this.electronicsItem = electronicsItem;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
	
}
