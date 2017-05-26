package com.example.gerda.mybusinessnews;

/**
 * @{Link News} represents a list of News reached by the Guardian API.
 */

public class News {

    // Title of the news
    private String mTitle;
    // Name of the relevant section related to the news
    private String mSectionName;
    // Date when the news has been published
    private String mPublicationDate;
    // Web url of the particular news
    private String mWebUrl;


    /** Create a new news object
     *
     * @param sectionName is the section in which the news categorized
     * @param publicationDate is the date date when the news has been published
     * @param title of the news
     * @param webUrl of the news
     */
    public News(String title, String sectionName, String publicationDate,  String webUrl) {

        mTitle = title;
        mSectionName = sectionName;
        mPublicationDate = publicationDate;
        mWebUrl = webUrl;
    }

    // Return the title of the news
    public String getTitle() { return  mTitle; }

    // Return the section name of the news
    public String getSectionName() { return mSectionName; }

    // Return the publication date of the news
    public String getPublicationDate() { return  mPublicationDate; }

    // Return the web Url of the news
    public String getWebUrl() { return mWebUrl; }
}
