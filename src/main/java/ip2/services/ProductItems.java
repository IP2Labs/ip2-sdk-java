package ip2.services;

import java.math.BigDecimal;

public class ProductItems
{
	public String productId;
	public String thumbNail;
	public String image;
	public String category1;
	public String category2;
	public String category3;
	public String category4;
	public String name;
	public String description;
	public BigDecimal minimumPrice;
	public BigDecimal maximumPrice;
	public BigDecimal wholeSalePrice;
	public String currencyCode;
	public BigDecimal productFee;
	public BigDecimal discount;
	public BigDecimal tax;
	
	public String getProductId()
	{
		return productId;
	}
	public void setProductId(String productId)
	{
		this.productId = productId;
	}
	
	public String getThumbNail() {
		return thumbNail;
	}
	
	public void setThumbNail(String thumbNail) {
		this.thumbNail = thumbNail;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getCategory1() {
		return category1;
	}
	
	public void setCategory1(String category1) {
		this.category1 = category1;
	}
	
	public String getCategory2() {
		return category2;
	}
	
	public void setCategory2(String category2) {
		this.category2 = category2;
	}
	
	public String getCategory3() {
		return category3;
	}
	
	public void setCategory3(String category3) {
		this.category3 = category3;
	}
	
	public String getCategory4() {
		return category4;
	}
	
	public void setCategory4(String category4) {
		this.category4 = category4;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public BigDecimal getMinimumPrice() {
		return minimumPrice;
	}
	
	public void setMinimumPrice(BigDecimal minimumPrice) {
		this.minimumPrice = minimumPrice;
	}
	
	public BigDecimal getMaximumPrice() {
		return maximumPrice;
	}
	
	public void setMaximumPrice(BigDecimal maximumPrice) {
		this.maximumPrice = maximumPrice;
	}
	
	public BigDecimal getWholeSalePrice() {
		return wholeSalePrice;
	}
	
	public void setWholeSalePrice(BigDecimal wholeSalePrice) {
		this.wholeSalePrice = wholeSalePrice;
	}
	
	public String getCurrencyCode() {
		return currencyCode;
	}
	
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	public BigDecimal getProductFee() {
		return productFee;
	}
	
	public void setProductFee(BigDecimal productFee) {
		this.productFee = productFee;
	}
	
	public BigDecimal getDiscount() {
		return discount;
	}
	
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
	public BigDecimal getTax() {
		return tax;
	}
	
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	
	/**
	 * 
	 * @param productId - the id of the product
	 * @param thumbNail - uri to the image thumbnail
	 * @param image
	 * @param category2
	 * @param category3
	 * @param category4
	 * @param name
	 * @param description
	 * @param minimumPrice
	 * @param maximumPrice
	 * @param wholeSalePrice
	 * @param currencyCode
	 * @param productFee
	 * @param discount
	 * @param tax
	 */
	/*public Products(String productId, String thumbNail, String image, String category1, String category2, String category3,
	 String category4, String name, String description, double minimumPrice, double maximumPrice, double wholeSalePrice,
	 String currencyCode, double productFee, double discount, double tax)
	{
		this.productId = productId;
		this.thumbNail = thumbNail;
		this.image = image;
		this.category1 = category1;
		this.category2 = category2;
		this.category3 = category3;
		this.category4 = category4;
		this.name = name;
		this.description = description;
		this.minimumPrice = minimumPrice;
		this.maximumPrice = maximumPrice;
		this.wholeSalePrice = wholeSalePrice;
		this.currencyCode = currencyCode;
		this.productFee = productFee;
		this.discount = discount;
		this.tax = tax;
		
	}
*/
}
