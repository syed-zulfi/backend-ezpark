package com.apptech.apps.easypark.dao.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.apptech.apps.easypark.constants.AppConstants;
import com.apptech.apps.easypark.constants.Status;
import com.apptech.apps.easypark.controllers.vo.AddressDTO;
import com.apptech.apps.easypark.controllers.vo.UserDTO;
import com.apptech.apps.easypark.exceptions.ObjectCreationException;

@Entity
@Table(name = "USER", uniqueConstraints = { @UniqueConstraint(columnNames = "ID"),
		@UniqueConstraint(columnNames = "USER_NAME") })
public class User extends BaseEntity implements Serializable, UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2881150949059297825L;

	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "MIDDLE_NAME")
	private String middleName;
	@Column(name = "EMAIL_ID")
	private String email;
	@Column(name = "USER_NAME")
	private String username;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "MOBILE")
	private String mobile;
	@Column(name = "STATUS")
	private String status;
	@OneToMany(mappedBy = "user",cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	private Set<Address> address = new HashSet<Address>();
	@Column(name = "ROLE")
	String role;
	@OneToMany(mappedBy = "lctnOwner")
	private Set<ParkLocation> parkLocations = new HashSet<ParkLocation>();
	@ManyToOne
	@JoinColumn(name = "OWNER_ID")
	private User owner;
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Set<User> getAgents() {
		return agents;
	}

	public void setAgents(Set<User> agents) {
		this.agents = agents;
	}

	@OneToMany(mappedBy = "owner", cascade=CascadeType.PERSIST)
	private Set<User> agents = new HashSet<User>();

	private User() {

	}

	public Set<Address> getAddress() {
		return address;
	}

	public void addAddress(Address address) {
		this.address.add(address);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName + ", email="
				+ email + ", username=" + username + ", password=" + password + ", mobile=" + mobile + ", address="
				+ address + ", role=" + role + "]";
	}

	/**
	 * 
	 * @param dto
	 * @return
	 * @throws ObjectCreationException
	 */
	public static User buildDomainObject(UserDTO uDTO) throws ObjectCreationException {
		try {
			User user = new User();
			user.setUsername(uDTO.getUsername());
			user.setEmail(uDTO.getEmail());
			user.setFirstName(uDTO.getFirstName());
			user.setMiddleName(uDTO.getMiddleName());
			user.setLastName(uDTO.getLastName());
			user.setMobile(uDTO.getMobile());
			user.setPassword(new BCryptPasswordEncoder().encode(uDTO.getPassword()));
			user.setStatus(Status.ACTIVE.toString());
			user.setRole(uDTO.getUserType());
			for (AddressDTO aDTO : uDTO.getAddress()) {
				Address address = Address.createAddress(aDTO);
				address.setUser(user);
				user.addAddress(address);
			}
			return user;
		} catch (Exception e) {
			throw new ObjectCreationException("Exception occured while cration user domain object", e);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role != other.role)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;

		return true;
	}

	public UserDetails createUserSecurityDetails() {
		GrantedAuthority gAthority = new SimpleGrantedAuthority(this.getRole().toString());
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(this.username, this.password,
				Arrays.asList(gAthority));

		return userDetails;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		GrantedAuthority gAthority = new SimpleGrantedAuthority(this.getRole().toString());
		authorities.add(gAthority);

		return authorities;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	public String getFullName() {
		return this.firstName + AppConstants.SPACE + this.middleName + AppConstants.SPACE + this.lastName;
	}

}
