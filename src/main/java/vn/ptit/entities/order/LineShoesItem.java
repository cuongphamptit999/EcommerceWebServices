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

import vn.ptit.entities.shoes.ShoesItem;

@Entity
@Table(name = "LineShoesItem")
public class LineShoesItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "Quanity")
	private int quanity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ShoesItemBarCode")
	private ShoesItem shoesItem;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CartID")
	private ShoppingCart shoppingCart;
}
