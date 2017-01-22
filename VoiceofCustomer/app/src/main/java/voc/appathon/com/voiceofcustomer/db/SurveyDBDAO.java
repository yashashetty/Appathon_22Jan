package voc.appathon.com.voiceofcustomer.db;

public class SurveyDBDAO extends BaseDBDAO {

   /* public SurveyDBDAO(Context context) {
        super(context);
    }



    static final String WHERE_ID_EQUALS = DBHelper.KEY_TILESINFO_ITEMID
            + " =?";*//*



    public long updateBusinessValues(Survey webData) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_SURVEYID, webData.getSurveyID());
        values.put(DBHelper.KEY_SURVEYQUESTION, webData.getSurveyQ());
        values.put(DBHelper.KEY_SURVEYANS, webData.getSurveyAns());
        values.put(DBHelper.KEY_SURVEYSTATUS, webData.getSurveyStatus());
        values.put(DBHelper.KEY_SURVEYTYPE, webData.getSurveyType());


        long result = database.update(DBHelper.TABLE_SURVEY, values,
                WHERE_ID_EQUALS,
                new String[]{String.valueOf(webData.getItemID())});
        //Log.d("Update Result:", "=" + result);
        return result;

    }

    public int deleteBusinessValue(WebData data) {
        return database.delete(DBHelper.TABLE_BusinessValuesandTrends,
                WHERE_ID_EQUALS, new String[]{data.getItemID() + ""});
    }

    public List<WebData> getBusinessValues() {
        List<WebData> businessValue = new ArrayList<WebData>();
        Cursor cursor = database.query(DBHelper.TABLE_BusinessValuesandTrends,
                new String[]{DBHelper.KEY_BUSSVAL_TRENDS_BUSINESSHEADER,
                        DBHelper.KEY_BUSSVAL_TRENDS_BUSINESSMESSAGE, DBHelper.KEY_BUSSVAL_TRENDS_IMAGEURL, DBHelper.KEY_TILESINFO_CREATEDTIMESTAMP, DBHelper.KEY_TILESINFO_ISREAD, DBHelper.KEY_TILESINFO_ITEMID, DBHelper.KEY_TILESINFO_TILEID, DBHelper.KEY_TILESINFO_UPDATEDTIMESTAMP, DBHelper.KEY_TILESINFO_USERGROUP, DBHelper.KEY_TILESINFO_ISSYNCED, DBHelper.KEY_STATUS_CODE, DBHelper.KEY_READ_MODIFIEDTIME, DBHelper.KEY_WORKFLOWSTATUS_ID}, null, null, null, null,
                *//*DBHelper.KEY_TILESINFO_ISREAD + " DESC, " +*//* DBHelper.KEY_TILESINFO_UPDATEDTIMESTAMP + " DESC ");
        while (cursor.moveToNext()) {
            WebData data = new WebData(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12));
            businessValue.add(data);
        }
        return businessValue;
    }

    public List<WebData> getBusinessValues(String ID) {
        List<WebData> businessValue = new ArrayList<WebData>();
        Cursor cursor = database.query(DBHelper.TABLE_BusinessValuesandTrends,
                new String[]{DBHelper.KEY_BUSSVAL_TRENDS_BUSINESSHEADER,
                        DBHelper.KEY_BUSSVAL_TRENDS_BUSINESSMESSAGE, DBHelper.KEY_BUSSVAL_TRENDS_IMAGEURL, DBHelper.KEY_TILESINFO_CREATEDTIMESTAMP, DBHelper.KEY_TILESINFO_ISREAD, DBHelper.KEY_TILESINFO_ITEMID, DBHelper.KEY_TILESINFO_TILEID, DBHelper.KEY_TILESINFO_UPDATEDTIMESTAMP, DBHelper.KEY_TILESINFO_USERGROUP, DBHelper.KEY_TILESINFO_ISSYNCED, DBHelper.KEY_STATUS_CODE, DBHelper.KEY_READ_MODIFIEDTIME, DBHelper.KEY_WORKFLOWSTATUS_ID}, WHERE_ID_EQUALS, new String[]{}, null, null,
                *//*DBHelper.KEY_TILESINFO_ISREAD + " DESC, " +*//* DBHelper.KEY_TILESINFO_UPDATEDTIMESTAMP + " DESC ");
        while (cursor.moveToNext()) {
            WebData data = new WebData(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12));
            businessValue.add(data);
        }
        return businessValue;
    }


    public synchronized void insertBusinessValues(ArrayList<?> responseList) {


        for (int i = 0; i < responseList.size(); i++) {
            ContentValues values = new ContentValues();
            WebData webData = (WebData) responseList.get(i);

            Log.d("tanu", "ispresent-----------------" + !(isPresent(DBHelper.TABLE_BusinessValuesandTrends, DBHelper.KEY_TILESINFO_ITEMID, webData.getItemID())));

            if (!(webData.getStatusCode().equals("2")) && !(isPresent(DBHelper.TABLE_BusinessValuesandTrends, DBHelper.KEY_TILESINFO_ITEMID, webData.getItemID()))) {
                Log.d("tanu", "come inside===============");
                values.put(DBHelper.KEY_BUSSVAL_TRENDS_BUSINESSHEADER, webData.getTitle());
                values.put(DBHelper.KEY_BUSSVAL_TRENDS_BUSINESSMESSAGE, webData.getDescription());
                values.put(DBHelper.KEY_BUSSVAL_TRENDS_IMAGEURL, webData.getImage());
                values.put(DBHelper.KEY_TILESINFO_CREATEDTIMESTAMP, webData.getCreatedTimeStamp());
                values.put(DBHelper.KEY_TILESINFO_ISREAD, webData.getIsRead());
                values.put(DBHelper.KEY_TILESINFO_ITEMID, webData.getItemID());
                values.put(DBHelper.KEY_TILESINFO_TILEID, webData.getTileID());
                values.put(DBHelper.KEY_TILESINFO_UPDATEDTIMESTAMP, webData.getUpdateTimeStamp());
                values.put(DBHelper.KEY_TILESINFO_USERGROUP, webData.getUserGroup());
                values.put(DBHelper.KEY_STATUS_CODE, defaultStatusCode);
                values.put(DBHelper.KEY_WORKFLOWSTATUS_ID, webData.getWorkflowStatusId());
                values.put(DBHelper.KEY_WORKFLOWSTATUS_ID, webData.getWorkflowStatusId());
                database.insert(DBHelper.TABLE_BusinessValuesandTrends, null, values);
            }

        }
    }


    public synchronized void insertBusinessValues(WebData webData) {
        ContentValues values = new ContentValues();
        //WebData webData = (WebData) responseList.get(i);
        values.put(DBHelper.KEY_BUSSVAL_TRENDS_BUSINESSHEADER, webData.getTitle());
        values.put(DBHelper.KEY_BUSSVAL_TRENDS_BUSINESSMESSAGE, webData.getDescription());
        values.put(DBHelper.KEY_BUSSVAL_TRENDS_IMAGEURL, webData.getImage());
        values.put(DBHelper.KEY_TILESINFO_CREATEDTIMESTAMP, webData.getCreatedTimeStamp());
        values.put(DBHelper.KEY_TILESINFO_ISREAD, webData.getIsRead());
        values.put(DBHelper.KEY_TILESINFO_ITEMID, webData.getItemID());
        values.put(DBHelper.KEY_TILESINFO_TILEID, webData.getTileID());
        values.put(DBHelper.KEY_TILESINFO_UPDATEDTIMESTAMP, webData.getUpdateTimeStamp());
        values.put(DBHelper.KEY_TILESINFO_USERGROUP, webData.getUserGroup());
        values.put(DBHelper.KEY_STATUS_CODE, webData.getStatusCode());
        values.put(DBHelper.KEY_WORKFLOWSTATUS_ID, webData.getWorkflowStatusId());
        database.insert(DBHelper.TABLE_BusinessValuesandTrends, null, values);
    }

    public void updateIsread(String status, String rowId) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_TILESINFO_ISREAD, status);
        long result = database.update(DBHelper.TABLE_BusinessValuesandTrends, values,
                WHERE_ID_EQUALS,
                new String[]{String.valueOf(rowId)});
        //int i = database.update(DBHelper.TABLE_BusinessValuesandTrends, values,DBHelper.KEY_TILESINFO_ISREAD+"="+rowId, null);
        //Log.d("tanu","updated row======"+result);
    }*/
}

