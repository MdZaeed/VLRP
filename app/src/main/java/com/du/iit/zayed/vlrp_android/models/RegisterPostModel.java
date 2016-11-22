
package com.du.iit.zayed.vlrp_android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterPostModel {

    public RegisterPostModel(String fullName,String universityName,String department,String institute,String username,String email,String fid,String password)
    {
        this.fullName=fullName;
        this.universityName="University of Dhaka";
        this.department=department;
        this.institute=institute;
        this.username=username;
        this.email=email;
        this.fid=fid;
        this.password=password;
    }

    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("UniversityName")
    @Expose
    private String universityName;
    @SerializedName("Department")
    @Expose
    private String department;
    @SerializedName("Institute")
    @Expose
    private String institute;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Fid")
    @Expose
    private String fid;
    @SerializedName("Password")
    @Expose
    private String password;

    /**
     * 
     * @return
     *     The fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 
     * @param fullName
     *     The FullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 
     * @return
     *     The universityName
     */
    public String getUniversityName() {
        return universityName;
    }

    /**
     * 
     * @param universityName
     *     The UniversityName
     */
    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    /**
     * 
     * @return
     *     The department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * 
     * @param department
     *     The Department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * 
     * @return
     *     The institute
     */
    public String getInstitute() {
        return institute;
    }

    /**
     * 
     * @param institute
     *     The Institute
     */
    public void setInstitute(String institute) {
        this.institute = institute;
    }

    /**
     * 
     * @return
     *     The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param username
     *     The Username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     *     The Email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     *     The fid
     */
    public String getFid() {
        return fid;
    }

    /**
     * 
     * @param fid
     *     The Fid
     */
    public void setFid(String fid) {
        this.fid = fid;
    }

    /**
     * 
     * @return
     *     The password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password
     *     The Password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
