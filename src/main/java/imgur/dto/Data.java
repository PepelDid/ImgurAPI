
package imgur.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "deletehash",
    "account_id",
    "account_url",
    "ad_type",
    "ad_url",
    "title",
    "description",
    "name",
    "type",
    "width",
    "height",
    "size",
    "views",
    "section",
    "vote",
    "bandwidth",
    "animated",
    "favorite",
    "in_gallery",
    "in_most_viral",
    "has_sound",
    "is_ad",
    "nsfw",
    "link",
    "tags",
    "datetime",
    "mp4",
    "hls"
})
@Generated("jsonschema2pojo")
public class Data implements Serializable
{

    @JsonProperty("id")
    private String id;
    @JsonProperty("deletehash")
    private String deletehash;
    @JsonProperty("account_id")
    private Integer accountId;
    @JsonProperty("account_url")
    private String accountUrl;
    @JsonProperty("ad_type")
    private Object adType;
    @JsonProperty("ad_url")
    private Object adUrl;
    @JsonProperty("title")
    private Object title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("width")
    private Integer width;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("views")
    private Integer views;
    @JsonProperty("section")
    private Object section;
    @JsonProperty("vote")
    private Object vote;
    @JsonProperty("bandwidth")
    private Integer bandwidth;
    @JsonProperty("animated")
    private Boolean animated;
    @JsonProperty("favorite")
    private Boolean favorite;
    @JsonProperty("in_gallery")
    private Boolean inGallery;
    @JsonProperty("in_most_viral")
    private Boolean inMostViral;
    @JsonProperty("has_sound")
    private Boolean hasSound;
    @JsonProperty("is_ad")
    private Boolean isAd;
    @JsonProperty("nsfw")
    private Object nsfw;
    @JsonProperty("link")
    private String link;
    @JsonProperty("tags")
    private List<Object> tags = null;
    @JsonProperty("datetime")
    private Integer datetime;
    @JsonProperty("mp4")
    private String mp4;
    @JsonProperty("hls")
    private String hls;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 4916527608330379372L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param link
     * @param adUrl
     * @param description
     * @param section
     * @param title
     * @param type
     * @param deletehash
     * @param adType
     * @param datetime
     * @param id
     * @param isAd
     * @param vote
     * @param views
     * @param accountUrl
     * @param height
     * @param bandwidth
     * @param nsfw
     * @param hasSound
     * @param hls
     * @param tags
     * @param mp4
     * @param accountId
     * @param size
     * @param name
     * @param width
     * @param animated
     * @param inGallery
     * @param favorite
     * @param inMostViral
     */
    public Data(String id, String deletehash, Integer accountId, String accountUrl, Object adType, Object adUrl, Object title, String description, String name, String type, Integer width, Integer height, Integer size, Integer views, Object section, Object vote, Integer bandwidth, Boolean animated, Boolean favorite, Boolean inGallery, Boolean inMostViral, Boolean hasSound, Boolean isAd, Object nsfw, String link, List<Object> tags, Integer datetime, String mp4, String hls) {
        super();
        this.id = id;
        this.deletehash = deletehash;
        this.accountId = accountId;
        this.accountUrl = accountUrl;
        this.adType = adType;
        this.adUrl = adUrl;
        this.title = title;
        this.description = description;
        this.name = name;
        this.type = type;
        this.width = width;
        this.height = height;
        this.size = size;
        this.views = views;
        this.section = section;
        this.vote = vote;
        this.bandwidth = bandwidth;
        this.animated = animated;
        this.favorite = favorite;
        this.inGallery = inGallery;
        this.inMostViral = inMostViral;
        this.hasSound = hasSound;
        this.isAd = isAd;
        this.nsfw = nsfw;
        this.link = link;
        this.tags = tags;
        this.datetime = datetime;
        this.mp4 = mp4;
        this.hls = hls;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public Data withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("deletehash")
    public String getDeletehash() {
        return deletehash;
    }

    @JsonProperty("deletehash")
    public void setDeletehash(String deletehash) {
        this.deletehash = deletehash;
    }

    public Data withDeletehash(String deletehash) {
        this.deletehash = deletehash;
        return this;
    }

    @JsonProperty("account_id")
    public Integer getAccountId() {
        return accountId;
    }

    @JsonProperty("account_id")
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Data withAccountId(Integer accountId) {
        this.accountId = accountId;
        return this;
    }

    @JsonProperty("account_url")
    public String getAccountUrl() {
        return accountUrl;
    }

    @JsonProperty("account_url")
    public void setAccountUrl(String accountUrl) {
        this.accountUrl = accountUrl;
    }

    public Data withAccountUrl(String accountUrl) {
        this.accountUrl = accountUrl;
        return this;
    }

    @JsonProperty("ad_type")
    public Object getAdType() {
        return adType;
    }

    @JsonProperty("ad_type")
    public void setAdType(Object adType) {
        this.adType = adType;
    }

    public Data withAdType(Object adType) {
        this.adType = adType;
        return this;
    }

    @JsonProperty("ad_url")
    public Object getAdUrl() {
        return adUrl;
    }

    @JsonProperty("ad_url")
    public void setAdUrl(Object adUrl) {
        this.adUrl = adUrl;
    }

    public Data withAdUrl(Object adUrl) {
        this.adUrl = adUrl;
        return this;
    }

    @JsonProperty("title")
    public Object getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(Object title) {
        this.title = title;
    }

    public Data withTitle(Object title) {
        this.title = title;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Data withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Data withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public Data withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("width")
    public Integer getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(Integer width) {
        this.width = width;
    }

    public Data withWidth(Integer width) {
        this.width = width;
        return this;
    }

    @JsonProperty("height")
    public Integer getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(Integer height) {
        this.height = height;
    }

    public Data withHeight(Integer height) {
        this.height = height;
        return this;
    }

    @JsonProperty("size")
    public Integer getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(Integer size) {
        this.size = size;
    }

    public Data withSize(Integer size) {
        this.size = size;
        return this;
    }

    @JsonProperty("views")
    public Integer getViews() {
        return views;
    }

    @JsonProperty("views")
    public void setViews(Integer views) {
        this.views = views;
    }

    public Data withViews(Integer views) {
        this.views = views;
        return this;
    }

    @JsonProperty("section")
    public Object getSection() {
        return section;
    }

    @JsonProperty("section")
    public void setSection(Object section) {
        this.section = section;
    }

    public Data withSection(Object section) {
        this.section = section;
        return this;
    }

    @JsonProperty("vote")
    public Object getVote() {
        return vote;
    }

    @JsonProperty("vote")
    public void setVote(Object vote) {
        this.vote = vote;
    }

    public Data withVote(Object vote) {
        this.vote = vote;
        return this;
    }

    @JsonProperty("bandwidth")
    public Integer getBandwidth() {
        return bandwidth;
    }

    @JsonProperty("bandwidth")
    public void setBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
    }

    public Data withBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
        return this;
    }

    @JsonProperty("animated")
    public Boolean getAnimated() {
        return animated;
    }

    @JsonProperty("animated")
    public void setAnimated(Boolean animated) {
        this.animated = animated;
    }

    public Data withAnimated(Boolean animated) {
        this.animated = animated;
        return this;
    }

    @JsonProperty("favorite")
    public Boolean getFavorite() {
        return favorite;
    }

    @JsonProperty("favorite")
    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Data withFavorite(Boolean favorite) {
        this.favorite = favorite;
        return this;
    }

    @JsonProperty("in_gallery")
    public Boolean getInGallery() {
        return inGallery;
    }

    @JsonProperty("in_gallery")
    public void setInGallery(Boolean inGallery) {
        this.inGallery = inGallery;
    }

    public Data withInGallery(Boolean inGallery) {
        this.inGallery = inGallery;
        return this;
    }

    @JsonProperty("in_most_viral")
    public Boolean getInMostViral() {
        return inMostViral;
    }

    @JsonProperty("in_most_viral")
    public void setInMostViral(Boolean inMostViral) {
        this.inMostViral = inMostViral;
    }

    public Data withInMostViral(Boolean inMostViral) {
        this.inMostViral = inMostViral;
        return this;
    }

    @JsonProperty("has_sound")
    public Boolean getHasSound() {
        return hasSound;
    }

    @JsonProperty("has_sound")
    public void setHasSound(Boolean hasSound) {
        this.hasSound = hasSound;
    }

    public Data withHasSound(Boolean hasSound) {
        this.hasSound = hasSound;
        return this;
    }

    @JsonProperty("is_ad")
    public Boolean getIsAd() {
        return isAd;
    }

    @JsonProperty("is_ad")
    public void setIsAd(Boolean isAd) {
        this.isAd = isAd;
    }

    public Data withIsAd(Boolean isAd) {
        this.isAd = isAd;
        return this;
    }

    @JsonProperty("nsfw")
    public Object getNsfw() {
        return nsfw;
    }

    @JsonProperty("nsfw")
    public void setNsfw(Object nsfw) {
        this.nsfw = nsfw;
    }

    public Data withNsfw(Object nsfw) {
        this.nsfw = nsfw;
        return this;
    }

    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(String link) {
        this.link = link;
    }

    public Data withLink(String link) {
        this.link = link;
        return this;
    }

    @JsonProperty("tags")
    public List<Object> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public Data withTags(List<Object> tags) {
        this.tags = tags;
        return this;
    }

    @JsonProperty("datetime")
    public Integer getDatetime() {
        return datetime;
    }

    @JsonProperty("datetime")
    public void setDatetime(Integer datetime) {
        this.datetime = datetime;
    }

    public Data withDatetime(Integer datetime) {
        this.datetime = datetime;
        return this;
    }

    @JsonProperty("mp4")
    public String getMp4() {
        return mp4;
    }

    @JsonProperty("mp4")
    public void setMp4(String mp4) {
        this.mp4 = mp4;
    }

    public Data withMp4(String mp4) {
        this.mp4 = mp4;
        return this;
    }

    @JsonProperty("hls")
    public String getHls() {
        return hls;
    }

    @JsonProperty("hls")
    public void setHls(String hls) {
        this.hls = hls;
    }

    public Data withHls(String hls) {
        this.hls = hls;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Data withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
