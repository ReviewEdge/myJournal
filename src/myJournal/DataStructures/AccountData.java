package myJournal.DataStructures;

import javax.swing.*;
import java.util.Date;

/**
 * Abstraction for all of the profile information of an account.
 */
public class AccountData {
    private String firstName;
    private String lastName;
    private String username;
    private Date accountCreation;
    private Date dateOfBirth;
    private String bio;
    private String livingLocation;

    public AccountData(String firstName, String lastName, String username, Date accountCreation, Date dateOfBirth, String bio, String livingLocation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
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
}
