package com.selenium.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.selenium.enums.Currency;
import com.selenium.enums.PaymentStatus;
import com.selenium.enums.Role;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Rayant on 18.04.2017.
 */
public class User {


    @Expose
    private Long id;

    @Expose
    @SerializedName("m")
    private Long merchantId;

    @Expose
    @SerializedName("l")
    private String login;

    @Expose
    @SerializedName("e")
    private String email;

    @Expose
    @SerializedName("s")
    private String skype;

    @Expose
    @SerializedName("a")
    private String area;


    @Expose
    @SerializedName("pos")
    private String position;

    @Expose(serialize = false)
    @SerializedName("p")
    private String password;

    @Expose
    @SerializedName("r")
    private Role role;

    @Expose
    @SerializedName("n")
    private String name;

    @Expose
    @SerializedName("ph")
    private String phone;


    @Expose
    @SerializedName("imgs")
    private ArrayList<Long> imgesIds;

    @Expose
    @SerializedName("t")
    private Tariff tariff;

    @Expose
    @SerializedName("newTariff")
    private Tariff newTariff;

    @Expose
    @SerializedName("ed")
    private String tarifExpirityDate;

    @Expose
    @SerializedName("rd")
    private String registrationDate;


    @Expose
    @SerializedName("discount")
    private Integer discount = 0;

    @Expose
    @SerializedName("currentPrice")
    private Integer currentPrice;

    @Expose
    @SerializedName("currentDebt")
    private Integer currentDebt = 0;

    @Expose
    @SerializedName("frozen")
    private Boolean frozen = false;

    @Expose
    @SerializedName("disabled")
    private Boolean disabled = false;

    @Expose
    @SerializedName("overlimit")
    private Boolean overlimit = false;

    @Expose
    @SerializedName("fid")
    private Long facebookId;

    @Expose
    @SerializedName("gid")
    private String googleId;

    @Expose
    @SerializedName("vid")
    private Long vkId;

    @Expose
    @SerializedName("dc")
    private String dealerCode;

    @Expose
    @SerializedName("fa")
    private Long followersAmount;

    @Expose
    @SerializedName("jid")
    private Long jeapieId;

    @Expose
    @SerializedName("salt")
    private String salt;

    @Expose
    @SerializedName("lang")
    private String lang = "EN";

    @Expose
    @SerializedName("pushesSend")
    private long pushesSend;

    @Expose
    @SerializedName("pushesClicked")
    private long pushesClicked;

    @Expose
    @SerializedName("pushesDelivered")
    private long pushesDelivered;

    @Expose
    @SerializedName("messagesSend")
    private long messagesSend;

    @Expose
    @SerializedName("special")
    private Boolean special;

    @Expose
    @SerializedName("currency")
    private Currency currency;

    @Expose
    @SerializedName("paymentStatus")
    private PaymentStatus paymentStatus = PaymentStatus.OK;

    @Expose
    @SerializedName("lastPaymentDate")
    private Date lastPaymentDate;

    @Expose
    @SerializedName("nextPaymentDate")
    private String nextPaymentDate;

    @Expose
    @SerializedName("lastPaymentAmount")
    private Long lastPaymentAmount = 0L;

    private String rectoken;
    /**
     * rectoken_lifetime	string(19)	Token lifetime in format DD.MM.YYYY hh:mm:ss 01.01.2018 00:00:00
     */
    private String rectokenLifetime;

    public Integer getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Integer currentPrice) {
        this.currentPrice = currentPrice;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public User setTariff(Tariff tariff) {
        this.tariff = tariff;
        return this;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public User setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
        return this;
    }

    public Boolean isFrozen() {
        return frozen;
    }

    public User setFrozen(Boolean frozen) {
        this.frozen = frozen;
        return this;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public Boolean isDisabled() {
        return disabled;
    }

    public User setDisabled(Boolean disabled) {
        this.disabled = disabled;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSkype() {
        return skype;
    }

    public User setSkype(String skype) {
        this.skype = skype;
        return this;
    }

    public String getArea() {
        return area;
    }

    public User setArea(String area) {
        this.area = area;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public User setPosition(String position) {
        this.position = position;
        return this;
    }


    public Integer getCurrentDebt() {
        return currentDebt == null ? 0 : currentDebt;
    }

    public void addDebt(Integer currentDebt) {
        if (this.currentDebt == null || this.currentDebt == 0) {
            this.currentDebt = currentDebt;
        } else {
            this.currentDebt += currentDebt;
        }
    }

    public void setCurrentDebt(Integer currentDebt) {
        this.currentDebt = currentDebt;
    }

    public ArrayList<Long> getImgesUrl() {
        return imgesIds;
    }

    public User setImgesUrl(ArrayList<Long> imgesUrl) {
        this.imgesIds = imgesUrl;
        return this;
    }

    public Long getFacebookId() {
        return facebookId;
    }

    public User setFacebookId(Long facebookId) {
        this.facebookId = facebookId;
        return this;
    }

    public String getGoogleId() {
        return googleId;
    }

    public User setGoogleId(String googleId) {
        this.googleId = googleId;
        return this;
    }

    public Long getVkId() {
        return vkId;
    }

    public User setVkId(Long vkId) {
        this.vkId = vkId;
        return this;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public User setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
        return this;
    }

    public Boolean isOverlimit() {
        return overlimit;
    }

    public User setOverlimit(Boolean overlimit) {
        this.overlimit = overlimit;
        return this;
    }

    public Tariff getNewTariff() {
        return newTariff;
    }

    public void setNewTariff(Tariff newTariff) {
        this.newTariff = newTariff;
    }

    public Long getFollowersAmount() {
        return followersAmount;
    }

    public void addFollowers(Long followersAmount) {
        this.followersAmount += followersAmount;
    }

    public void delFollower() {
        this.followersAmount--;
    }

    public Long getJeapieId() {
        return jeapieId;
    }

    public void setJeapieId(Long jeapieId) {
        this.jeapieId = jeapieId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setImgesIds(ArrayList<Long> imgesIds) {
        this.imgesIds = imgesIds;
    }

    public ArrayList<Long> getImgesIds() {
        return imgesIds;
    }

    public Boolean getFrozen() {
        return frozen;
    }

    public Boolean getOverlimit() {
        return overlimit;
    }

    public void setFollowersAmount(Long followersAmount) {
        this.followersAmount = followersAmount;
    }

    public Boolean isSpecial() {
        return special;
    }

    public void setSpecial(Boolean special) {
        this.special = special;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(Date lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public Long getLastPaymentAmount() {
        return lastPaymentAmount;
    }

    public void setLastPaymentAmount(long lastPaymentAmount) {
        this.lastPaymentAmount = lastPaymentAmount;
    }

    public Long getPushesSend() {
        return pushesSend;
    }

    public void setPushesSend(long pushesSend) {
        this.pushesSend = pushesSend;
    }

    public Long getPushesClicked() {
        return pushesClicked;
    }

    public void setPushesClicked(long pushesClicked) {
        this.pushesClicked = pushesClicked;
    }

    public Long getPushesDelivered() {
        return pushesDelivered;
    }

    public void setPushesDelivered(long pushesDelivered) {
        this.pushesDelivered = pushesDelivered;
    }

    public void setLastPaymentAmount(Long lastPaymentAmount) {
        this.lastPaymentAmount = lastPaymentAmount;
    }

    public Long getMessagesSend() {
        return messagesSend;
    }

    public void setMessagesSend(long messagesSend) {
        this.messagesSend = messagesSend;
    }


    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getRectokenLifetime() {
        return rectokenLifetime;
    }

    public void setRectokenLifetime(String rectokenLifetime) {
        this.rectokenLifetime = rectokenLifetime;
    }

    public String getRectoken() {
        return rectoken;
    }

    public void setRectoken(String rectoken) {
        this.rectoken = rectoken;
    }

    public void changeTariff() {
        if (newTariff != null) {
            tariff = newTariff;
            newTariff = null;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", merchantId=" + merchantId +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", skype='" + skype + '\'' +
                ", area='" + area + '\'' +
                ", position='" + position + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", imgesIds=" + imgesIds +
                ", tariff=" + tariff +
                ", newTariff=" + newTariff +
                ", tarifExpirityDate=" + tarifExpirityDate +
                ", registrationDate=" + registrationDate +
                ", discount=" + discount +
                ", currentPrice=" + currentPrice +
                ", currentDebt=" + currentDebt +
                ", frozen=" + frozen +
                ", disabled=" + disabled +
                ", overlimit=" + overlimit +
                ", facebookId=" + facebookId +
                ", googleId='" + googleId + '\'' +
                ", vkId=" + vkId +
                ", dealerCode='" + dealerCode + '\'' +
                ", followersAmount=" + followersAmount +
                ", jeapieId=" + jeapieId +
                ", salt='" + salt + '\'' +
                ", lang='" + lang + '\'' +
                ", pushesSend=" + pushesSend +
                ", pushesClicked=" + pushesClicked +
                ", pushesDelivered=" + pushesDelivered +
                ", messagesSend=" + messagesSend +
                ", special=" + special +
                ", currency=" + currency +
                ", paymentStatus=" + paymentStatus +
                ", lastPaymentDate=" + lastPaymentDate +
                ", nextPaymentDate=" + nextPaymentDate +
                ", lastPaymentAmount=" + lastPaymentAmount +
                ", rectoken='" + rectoken + '\'' +
                ", rectokenLifetime='" + rectokenLifetime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id.equals(user.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Boolean getSpecial() {
        return special;
    }

    public LocalDate getTariffExpiringDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(tarifExpirityDate, formatter);
        return localDate;
    }

    public void setTariffExpiringDate(String tariffExpiringDate) {
        this.tarifExpirityDate = tariffExpiringDate;
    }

    public LocalDate getRegistrationDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(tarifExpirityDate.split("T")[0], formatter);
        return localDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDate getNextPaymentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(nextPaymentDate.split("T")[0], formatter);
        return localDate;
    }

    public void setNextPaymentDate(String nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
    }
}
