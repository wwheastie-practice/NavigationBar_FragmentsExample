package com.prototype.swgoh;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Character extends RealmObject {

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    @SerializedName("image")
    private String image;

    @SerializedName("power")
    private Integer power;

    @SerializedName("description")
    private String description;

    @SerializedName("combat_type")
    private Integer combatType;

    @SerializedName("gear_levels")
    private RealmList<GearLevel> gearLevels;

    @SerializedName("alignment")
    private String alignment;

    @SerializedName("categories")
    private RealmList<String> categories;

    @SerializedName("ability_classes")
    private RealmList<String> abilityClasses;

    @SerializedName("role")
    private String role;

    @SerializedName("ship")
    private String ship;

    @SerializedName("ship_slot")
    private Integer shipSlot;

    @SerializedName("activate_shard_count")
    private Integer activateShardCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCombatType() {
        return combatType;
    }

    public void setCombatType(Integer combatType) {
        this.combatType = combatType;
    }

    public RealmList<GearLevel> getGearLevels() {
        return gearLevels;
    }

    public void setGearLevels(RealmList<GearLevel> gearLevels) {
        this.gearLevels = gearLevels;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public RealmList<String> getCategories() {
        return categories;
    }

    public void setCategories(RealmList<String> categories) {
        this.categories = categories;
    }

    public RealmList<String> getAbilityClasses() {
        return abilityClasses;
    }

    public void setAbilityClasses(RealmList<String> abilityClasses) {
        this.abilityClasses = abilityClasses;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getShip() {
        return ship;
    }

    public void setShip(String ship) {
        this.ship = ship;
    }

    public Integer getShipSlot() {
        return shipSlot;
    }

    public void setShipSlot(Integer shipSlot) {
        this.shipSlot = shipSlot;
    }

    public Integer getActivateShardCount() {
        return activateShardCount;
    }

    public void setActivateShardCount(Integer activateShardCount) {
        this.activateShardCount = activateShardCount;
    }

}
