package de.is2.mtext.soap.demo.Junit5;

import de.is2.mtext.soap.demo.repository.RestServiceBase;
import org.json.JSONArray;
import org.json.JSONML;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TestGetVertragDetails {
    
    static RestServiceBase restServiceBase;
    static {
        restServiceBase = new RestServiceBase();
        restServiceBase.init();
    }
    
    
    private static final String JSON_CHILD_NODES = "childNodes";
    
    private static final String JSON_EXCL_ATTR_SICHTBARKEIT = "Sichtbarkeit";
    private static final String JSON_EXCL_ATTR_INTERN = "den internen Gebrauch";
    private static final String JSON_DATA_AREA_LINE = "DataAreaLines";
    private static final String JSON_DATA_AREA_ELEM = "DataAreaElement";
    private static final String JSON_DATA_AREA_NAME = "DataAreaElementName";
    private static final String JSON_DATA_AREA_VALUE = "DataAreaElementValue";
    private static final String JSON_DATA_AREA_TABLES = "DataAreaTables";
    
    private static final String JSON_TITLE = "title";
    private static final String JSON_HEADLINE = "headline";
    private static final String JSON_TAGNAME = "tagName";
    private static final String JSON_IS_TABLE = "isTable";
    private static final String JSON_TABLE_TITLE = "tableTitle";
    private static final String JSON_TABLE_ROWS = "rows";
    private static final String JSON_COLSPAN = "colspan";
    private static final String JSON_IS_DATA = "isData";
    private static final String JSON_LINES = "lines";
    private static final String JSON_NAME = "name";
    private static final String JSON_VALUE = "value";
    
    public String getVertragDetails(String serverUrl, String kundennr, String nodeId) {
        
        String xml = restServiceBase.request(serverUrl + "endkunden/kunden/" + kundennr +
                                              "/policen/nodeId-" + nodeId + "/bestandsauskunft");

        JSONObject jsonObjectOrigin = JSONML.toJSONObject(xml, true);
        JSONObject jsonObject = new JSONObject();
        JSONArray dataAreas = new JSONArray();
        
        jsonObjectOrigin = getNode(jsonObjectOrigin, "BBVISDataAreas");
        if ( jsonObjectOrigin == null ){
            return "";
        }
        
        jsonObject.put(JSON_TITLE, jsonObjectOrigin.getString(JSON_HEADLINE));
        
        JSONArray childNodes = jsonObjectOrigin.getJSONArray(JSON_CHILD_NODES);
        
        for( int i = 0; i < childNodes.length(); i++ ) {
            jsonObjectOrigin = getNode(childNodes.getJSONObject(i), "BBVISDataArea");
            JSONObject dataArea = getDataArea(jsonObjectOrigin);
            if ( dataArea != null ) dataAreas.put(dataArea);
        }
        
        jsonObject.put("data", dataAreas);
        return jsonObject.toString();
    }
    
    private JSONObject getNode(JSONObject originObj, String key){
        if ( !originObj.isNull(JSON_TAGNAME) && key.equals(originObj.getString(JSON_TAGNAME))) {
            return originObj;
        }
        JSONArray childNodes = originObj.getJSONArray(JSON_CHILD_NODES);
        for(int i = 0; i < childNodes.length(); i++ ){
            Object childNode = childNodes.get(i);
            if ( childNode instanceof JSONObject ){
                if ( !((JSONObject) childNode).isNull(JSON_TAGNAME) && key.equals(((JSONObject) childNode).getString(JSON_TAGNAME))) {
                    return (JSONObject)childNode;
                }
                if ( !((JSONObject) childNode).isNull(JSON_CHILD_NODES)){
                    childNode = getNode((JSONObject)childNode, key);
                    if ( childNode != null && !((JSONObject) childNode).isNull(JSON_TAGNAME) && key.equals(((JSONObject) childNode).getString(JSON_TAGNAME))) {
                        return (JSONObject)childNode;
                    }
                }
            }
        }
        return null;
    }
    
    private JSONObject getDataArea(JSONObject originDataArea){
        if( originDataArea.getString(JSON_HEADLINE).contains(JSON_EXCL_ATTR_SICHTBARKEIT) ) return null;
        JSONObject dataArea = new JSONObject();
        
        dataArea.put(JSON_TITLE, originDataArea.getString(JSON_HEADLINE));
        JSONArray lines = new JSONArray();
        JSONObject dataAreaLinesOrigin = getNode(originDataArea, JSON_DATA_AREA_LINE);
        
        if ( dataAreaLinesOrigin != null && !dataAreaLinesOrigin.isNull(JSON_CHILD_NODES)){
            JSONArray dataAreaLineOrigin = dataAreaLinesOrigin.getJSONArray(JSON_CHILD_NODES);
            for( int i = 0; i < dataAreaLineOrigin.length(); i++ ){
                JSONArray line = new JSONArray();
                JSONObject dataAreaLine = dataAreaLineOrigin.getJSONObject(i);
                if( !dataAreaLine.isNull(JSON_CHILD_NODES) ){
                    JSONArray elementsOrigin = dataAreaLine.getJSONArray(JSON_CHILD_NODES);
                    for(int k = 0; k < elementsOrigin.length(); k++){
                        JSONObject elem = elementsOrigin.getJSONObject(k);
                        if ( JSON_DATA_AREA_ELEM.equals(elem.getString(JSON_TAGNAME))){
                            JSONObject elemName = getNode(elem, JSON_DATA_AREA_NAME);
                            JSONObject elemValue = getNode(elem, JSON_DATA_AREA_VALUE);
                            Map<String, String> e = new HashMap<>();
                            e.put(JSON_NAME, elemName==null||elemName.isNull(JSON_CHILD_NODES)?"":elemName.getJSONArray(JSON_CHILD_NODES).getString(0));
                            e.put(JSON_VALUE, elemValue==null||elemValue.isNull(JSON_CHILD_NODES)?"":elemValue.getJSONArray(JSON_CHILD_NODES).getString(0));
                            line.put(e);
                        }
                    }
                }
                lines.put(line);
            }
        }
        
        JSONObject dataAreaTablesOrigin = getNode(originDataArea, JSON_DATA_AREA_TABLES);
        if (dataAreaTablesOrigin != null && !dataAreaTablesOrigin.isNull(JSON_CHILD_NODES)) {
            JSONArray dataAreaTablesChildNodes = dataAreaTablesOrigin.getJSONArray(JSON_CHILD_NODES);
            
            for( int i = 0; i < dataAreaTablesChildNodes.length(); i++) {
                JSONObject table = new JSONObject();
                table.put(JSON_IS_TABLE, true);
                JSONObject dataAreaTable = dataAreaTablesChildNodes.getJSONObject(i);
                if ( !dataAreaTable.isNull(JSON_HEADLINE) && dataAreaTable.getString(JSON_HEADLINE).length() > 0 ) {
                    table.put(JSON_TABLE_TITLE, dataAreaTable.getString(JSON_HEADLINE));
                }
                if ( !dataAreaTable.isNull(JSON_CHILD_NODES) ){
                    JSONArray tableLines = new JSONArray();
                    JSONArray dataAreaTableLines = dataAreaTable.getJSONArray(JSON_CHILD_NODES);
                    for( int k = 0; k < dataAreaTableLines.length(); k++ ) {
                        JSONArray line = new JSONArray();
                        JSONObject dataAreaTableLine = dataAreaTableLines.getJSONObject(k);
                        
                        if ( !dataAreaTableLine.isNull(JSON_CHILD_NODES) ) {
                            JSONArray elements = dataAreaTableLine.getJSONArray(JSON_CHILD_NODES);
                            
                            boolean ignore = false;
                            for( int j = 0; j < elements.length(); j++ ) {
                                JSONObject elem = elements.getJSONObject(j);// DataAreaTableElement
                                String value = elem.isNull(JSON_CHILD_NODES)||elem.getJSONArray(JSON_CHILD_NODES).length()==0?"":elem.getJSONArray(JSON_CHILD_NODES).getString(0);
                                ignore = value.contains(JSON_EXCL_ATTR_INTERN);
                                if ( ignore ) break;
                                JSONObject tableCell = new JSONObject();
                                tableCell.put(JSON_COLSPAN, elem.getString(JSON_COLSPAN));
                                tableCell.put(JSON_IS_DATA, elem.getBoolean(JSON_IS_DATA));
                                tableCell.put(JSON_VALUE, value);
                                line.put(tableCell);
                            }
                            if ( ignore ) break;
                            tableLines.put(line);
                        }
                        
                    }
                    table.put(JSON_TABLE_ROWS, tableLines);
                }
                
                
                lines.put(table);
                
            }
        }
        
        dataArea.put(JSON_LINES, lines);
        return dataArea;
    }
}
