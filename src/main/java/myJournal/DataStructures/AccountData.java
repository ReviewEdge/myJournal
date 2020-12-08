package myJournal.DataStructures;

import myJournal.util.JSON.*;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Abstraction for all of the profile information of an account.
 */
public class AccountData implements JSONSerializable {
    private String firstName;
    private String lastName;
    private Date accountCreation;
    private Date dateOfBirth;
    private String bio;
    private String livingLocation;

    public AccountData(String firstName, String lastName, Date accountCreation, Date dateOfBirth, String bio, String livingLocation) {
        this.firstName = firstName;
        this.lastName = lastName;
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
		
		return (s.firstName.equals(this.firstName) && s.lastName.equals(this.lastName) && s.accountCreation.equals(this.accountCreation)
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

    public static AccountData fromJson(String jsonString) {
        JSONElement j = JSONParser.parse(jsonString);
        if(j instanceof JSONObject) {
            JSONObject accountJson = (JSONObject) j;
            String firstName = accountJson.getAsStringOrNull("firstName");
            String lastName = accountJson.getAsStringOrNull("lastName");
            Date accountCreation;
            try {
                accountCreation = (new SimpleDateFormat("yyyy-MM-dd")).parse(accountJson.getAsString("accountCreation"));
            }
            catch(ParseException p) {
                throw new IllegalArgumentException();
            }
            Date dateOfBirth = null;
            try {
                dateOfBirth = (new SimpleDateFormat("yyyy-MM-dd")).parse(accountJson.getAsString("dateOfBirth"));
            }
            catch(ParseException | NullPointerException e) {
                //ha ha
            }
            String bio = accountJson.getAsStringOrNull("bio");
            String livingLocation = accountJson.getAsStringOrNull("livingLocation");
            return new AccountData(firstName, lastName, accountCreation, dateOfBirth, bio, livingLocation);
        }
        throw new IllegalArgumentException();

    }
}
