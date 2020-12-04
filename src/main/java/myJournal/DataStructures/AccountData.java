package myJournal.DataStructures;

import myJournal.util.JSON.JSONBuilder;
import myJournal.util.JSON.JSONElement;
import myJournal.util.JSON.JSONSerializable;
import myJournal.util.JSON.JSONValue;

import javax.swing.*;
import java.util.Date;

/**
 * Abstraction for all of the profile information of an account.
 */
public class AccountData implements JSONSerializable {
    private String firstName;
    private String lastName;
    private String username;
    private String passwordHash;
    private Date accountCreation;
    private Date dateOfBirth;
    private String bio;
    private String livingLocation;

    public AccountData(String firstName, String lastName, String username, String passwordHash, Date accountCreation, Date dateOfBirth, String bio, String livingLocation) {
        if(username == null || username.equals("") || passwordHash == null)
            throw new IllegalArgumentException();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.passwordHash = passwordHash;
        this.accountCreation = accountCreation;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.livingLocation = livingLocation;
    }

    /**
     *
     * @return the first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return the account's username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param passwordHash the new password hash
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     *
     * @return the account's password's hash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return the date of account creation
     */
    public Date getAccountCreation() {
        return accountCreation;
    }

    /**
     *
     * @return the date of birth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     *
     * @param dateOfBirth the new date of birth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     *
     * @return the profile's bio information
     */
    public String getBio() {
        return bio;
    }

    /**
     *
     * @param bio new bio information
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     *
     * @return where the person lives
     */
    public String getLivingLocation() {
        return livingLocation;
    }

    /**
     *
     * @param livingLocation where the person lives
     */
    public void setLivingLocation(String livingLocation) {
        this.livingLocation = livingLocation;
    }

	/**
	 * @param o
	 * @return if they are equal
	 * @Override
	 */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof AccountData)) {
			return false;
		}
		AccountData s = (AccountData) o;
		
		return (s.firstName.equals(this.firstName) && s.lastName.equals(this.lastName) && s.username.equals(this.username) 
				&& s.passwordHash.equals(this.passwordHash) && s.accountCreation.equals(this.accountCreation)
				&& s.dateOfBirth.equals(this.dateOfBirth) && s.bio.equals(this.bio) && s.livingLocation.equals(this.livingLocation));
	}
	
	/**
	 * @return the HashCode of the object
	 * @Override
	 */
	public int hashCode() {
		int result = 17;
		result = result*37 + firstName.hashCode();
		result = result*37 + lastName.hashCode();	
		result = result*37 + username.hashCode();
		result = result*37 + passwordHash.hashCode();
		result = result*37 + accountCreation.hashCode();
		result = result*37 + dateOfBirth.hashCode();
		result = result*37 + bio.hashCode();
		result = result*37 + livingLocation.hashCode();
		return result;
	}
    
	/**
	 * @return the object as a JSONElement
	 * @Override
	 */
    public JSONElement asJsonElement() {
        JSONBuilder jb = JSONBuilder.object()
                .pair("firstName", firstName)
                .pair("lastName", lastName)
                .pair("username", username)
                .pair("passwordHash", passwordHash)
                .pair("accountCreation", accountCreation)
                .pair("dateOfBirth", dateOfBirth)
                .pair("bio", bio)
                .pair("livingLocation", livingLocation);
        return jb.toJSONElement();
    }

	/**
	 * @return the JSON string of the object
	 * @Override
	 */
    public String asJson() {
        return asJsonElement().toJSONString();
    }
}
