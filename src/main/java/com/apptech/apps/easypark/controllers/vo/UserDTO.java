package com.apptech.apps.easypark.controllers.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.apptech.apps.easypark.constants.Status;
import com.apptech.apps.easypark.dao.entity.Address;
import com.apptech.apps.easypark.dao.entity.User;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "firstName", "middleName", "lastName", "email", "mobile",
		"username", "password", "address" })
public class UserDTO {
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("middleName")
	private String middleName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("email")
	private String email;
	@JsonProperty("mobile")
	private String mobile;
	@JsonProperty("userType")
	private String userType;
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;
	@JsonProperty("address")
	private List<AddressDTO> address = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	@JsonProperty("ownerId")
	private String ownerID;
	
	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("middleName")
	public String getMiddleName() {
		return middleName;
	}

	@JsonProperty("middleName")
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("mobile")
	public String getMobile() {
		return mobile;
	}

	@JsonProperty("mobile")
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@JsonProperty("userType")
	public String getUserType() {
		return userType;
	}

	@JsonProperty("userType")
	public void setUserType(String userType) {
		this.userType = userType;
	}

	@JsonProperty("username")
	public String getUsername() {
		return username;
	}

	@JsonProperty("username")
	public void setUsername(String username) {
		this.username = username;
	}

	@JsonProperty("password")
	public String getPassword() {
		return password;
	}

	@JsonProperty("password")
	public void setPassword(String password) {
		this.password = password;
	}

	@JsonProperty("address")
	public List<AddressDTO> getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(List<AddressDTO> address) {
		this.address = address;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@JsonProperty("ownerId")
	public String getOwnerID() {
		return ownerID;
	}

	@JsonProperty("ownerId")
	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}

	public static  List<UserDTO> buildResUserDTOs(Collection<User> users){
		List<UserDTO> responseDto = new ArrayList<>();
		for(User user:users){
			responseDto.add(buldUserDTO(user));
		}
		return responseDto;
	}

	private static UserDTO buldUserDTO(User user){
		UserDTO userDto = new UserDTO();
		userDto.setUsername(user.getUsername());
		userDto.setEmail(user.getEmail());
		userDto.setFirstName(user.getFirstName());
		userDto.setMiddleName(user.getMiddleName());
		userDto.setLastName(user.getLastName());
		userDto.setMobile(user.getMobile());
		List<AddressDTO> address = new ArrayList<>();
		
		for (Address addr : user.getAddress()) {
			AddressDTO addrDto =AddressDTO.populateAddressDTO(addr);
			address.add(addrDto);
		}
		userDto.setAddress(address);
		
		return userDto;
		
	}

	@Override
	public String toString() {
		return "UserDTO [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", email="
				+ email + ", mobile=" + mobile + ", userType=" + userType + ", username=" + username + ", password="
				+ password + ", address=" + address + ", additionalProperties=" + additionalProperties + ", ownerID="
				+ ownerID + "]";
	}
}
