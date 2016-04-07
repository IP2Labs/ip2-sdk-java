package ip2.services;

public class Products
{
	private String productId;
	private String name;
	private String username;
	private String summary;
	private String description;
	private String website;
	private String contactPhone;
	private String contactEmail;
	private String serviceUri;
	private String currencyCode;
	private String countryCode;
	private String thumbNail;
	private String largeImage;
	private boolean isActive;
	private boolean isPrivate;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	
	public String getProductId() {
		return productId;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
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
	
	public String getWebsite() {
		return website;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public String getContactEmail() {
		return contactEmail;
	}
	
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	
	public String getServiceUri() {
		return serviceUri;
	}
	
	public void setServiceUri(String serviceUri) {
		this.serviceUri = serviceUri;
	}
	
	public String getCurrencyCode() {
		return currencyCode;
	}
	
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public String getThumbNail() {
		return thumbNail;
	}
	
	public void setThumbNail(String thumbNail) {
		this.thumbNail = thumbNail;
	}
	
	public String getLargeImage() {
		return largeImage;
	}
	
	public void setLargeImage(String largeImage) {
		this.largeImage = largeImage;
	}

}
