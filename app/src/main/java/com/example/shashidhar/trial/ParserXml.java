package com.example.shashidhar.trial;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shashidhar on 08-11-2017.
 */

/**
 * Controller - To parse the Xml inputstream and create RssFeedModels
 */
public class ParserXml {

    /**
     * Function to extract RSSFeedModel details from input stream
     * @param inputStream
     * @return List<RssFeedModel>
     * @throws XmlPullParserException
     * @throws IOException
     */
    public List<RssFeedModel> parseFeed(InputStream inputStream) throws XmlPullParserException,
            IOException {
        String title = null;
        String link = null;
        String description = null;
        String imgurl = null;
        boolean isItem = false;
        List<RssFeedModel> items = new ArrayList<>();

        try {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlPullParser.setInput(inputStream, null);

            xmlPullParser.nextTag();
            while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT) {
                int eventType = xmlPullParser.getEventType();

                String name = xmlPullParser.getName();
                if(name == null)
                    continue;

                if(eventType == XmlPullParser.END_TAG) {
                    if(name.equalsIgnoreCase("item")) {
                        isItem = false;
                    }
                    continue;
                }

                if (eventType == XmlPullParser.START_TAG) {
                    if(name.equalsIgnoreCase("item")) {
                        isItem = true;
                        continue;
                    }
                }

                Log.d("MyXmlParser", "Parsing name ==> " + name);
                String result = "";
                if (xmlPullParser.next() == XmlPullParser.TEXT ) {
                    result = xmlPullParser.getText();
                    }
                else {
                    if (name.equalsIgnoreCase("media:thumbnail")) {
                        imgurl =  xmlPullParser.getAttributeValue(null,"url");

                    }
                }
                xmlPullParser.nextTag();
                if (name.equalsIgnoreCase("title")&& isItem) {
                    title = result;
                } else if (name.equalsIgnoreCase("link")&& isItem) {
                    link = result;
                } else if (name.equalsIgnoreCase("description")&& isItem) {
                    description = result;
                }


                if (title != null && link != null && description != null && imgurl!= null) {
                    if(isItem ) {
                        RssFeedModel item = new RssFeedModel(title, link, description,imgurl);
                        items.add(item);
                    }
                    title = null;
                    link = null;
                    description = null;
                    imgurl = null;
                    isItem = false;
                }
            }

            return items;
        } finally {
            inputStream.close();
        }
    }
}
